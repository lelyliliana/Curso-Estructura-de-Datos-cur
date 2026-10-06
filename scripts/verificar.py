"""Compara salidas, ejecuta oráculos e inspecciona enlaces locales."""
from pathlib import Path
import json
import re
import subprocess
import tempfile
from ejecutar import ROOT,JAVA,compilar

def correr(comando,carpeta,entrada=""):
    p=subprocess.run(comando,cwd=carpeta,input=entrada,capture_output=True,text=True,encoding="utf-8",timeout=120)
    if p.returncode:raise RuntimeError(f"Error:\n{p.stdout}\n{p.stderr}")
    return p.stdout.replace("\r\n","\n")

def main():
    ejemplos=json.loads((ROOT/"scripts/manifest.json").read_text(encoding="utf-8"))
    with tempfile.TemporaryDirectory() as temporal:
        salida=Path(temporal)
        compilar(salida,sorted((ROOT/"src/curso").glob("*.java"))+[ROOT/"tests/Pruebas.java"])
        print(correr(JAVA+["-cp",temporal,"Pruebas"],salida),end="",flush=True)
        geo=json.loads(correr(JAVA+["-cp",temporal,"Pruebas","--geo"],salida))
        assert geo["type"]=="FeatureCollection" and len(geo["features"])==3
        a,b,e=geo["features"]
        assert a["properties"]["id"]=='Salón "A"\n\\'
        assert a["geometry"]=={"type":"Point","coordinates":[-75.6,6.2]}
        assert e["geometry"]=={"type":"LineString","coordinates":[a["geometry"]["coordinates"],b["geometry"]["coordinates"]]}
        assert e["properties"]["origen"]==a["properties"]["id"] and e["properties"]["costo"]==0
        print("GeoJSON analizado y comprobado con parser independiente",flush=True)
        for ruta in ejemplos:
            p=subprocess.run(JAVA+["com.sun.tools.javac.Main","-encoding","UTF-8","-cp",temporal,"-d",temporal,str(ROOT/ruta/"Main.java")],capture_output=True,text=True,encoding="utf-8",timeout=60)
            if p.returncode:raise RuntimeError(p.stderr)
            actual=correr(JAVA+["-cp",temporal,"Main"],salida)
            esperado=(ROOT/ruta/"esperado.txt").read_text(encoding="utf-8")
            if actual!=esperado:raise RuntimeError(f"Salida diferente en {ruta}\n{actual}\n{esperado}")
            documento=(ROOT/ruta/"README.md").read_text(encoding="utf-8")
            fuente=re.search(r"```java\n(.*?)\n```",documento,re.S)
            salida_documentada=re.search(r"### Salida esperada.*?```text\n(.*?)\n```",documento,re.S)
            assert fuente and fuente.group(1)==(ROOT/ruta/"Main.java").read_text(encoding="utf-8").rstrip(),ruta
            assert salida_documentada and salida_documentada.group(1)==esperado.rstrip(),ruta
            if ruta.endswith("08-geojson"):
                objeto=json.loads(actual)
                assert objeto["type"]=="FeatureCollection" and len(objeto["features"])==3
        print(f"Ejemplos comprobados: {len(ejemplos)}",flush=True)
        guion="1\na\nd\n1\nx\nd\n2\n3\nmapa.geojson\n4\nruta\na\nc\nsalir\n9\n0\n"
        menu=correr(JAVA+["-cp",temporal,"Main","--menu"],salida,guion)
        for texto in ("[a, b, c, d]; costo=9","Error: Lugar desconocido","costo=9, componentes=1","Mapa exportado","[a, b, c]; costo=5","Opción inválida","Fin"):
            assert texto in menu,(texto,menu)
        mapa=json.loads((salida/"mapa.geojson").read_text(encoding="utf-8"))
        assert len(mapa["features"])==8
        print("Menú, exportación y conversación comprobados",flush=True)
    enlaces=0
    for archivo in ROOT.rglob("*.md"):
        texto=re.sub(r"```.*?```","",archivo.read_text(encoding="utf-8"),flags=re.S)
        for destino in re.findall(r"\[[^\]]*\]\(([^)]+)\)",texto):
            if re.match(r"[A-Za-z][A-Za-z0-9+.-]*:",destino) or destino.startswith("#"):continue
            ruta=destino.split("#",1)[0]
            if ruta and not (archivo.parent/ruta).exists():raise RuntimeError(f"Enlace roto {archivo}: {destino}")
            enlaces+=1
    print(f"Enlaces locales comprobados: {enlaces}")
    print("Verificación completa")

if __name__=="__main__":main()
