"""Compila un único ejemplo con las estructuras compartidas y lo ejecuta."""
from pathlib import Path
import subprocess
import sys
import tempfile

ROOT=Path(__file__).resolve().parents[1]
JAVA=["java","-Dfile.encoding=UTF-8","-Dstdout.encoding=UTF-8","-Dstderr.encoding=UTF-8"]

def compilar(destino,fuentes):
    subprocess.run(JAVA+["com.sun.tools.javac.Main","-encoding","UTF-8","-d",str(destino)]+[str(f) for f in fuentes],check=True)

def main():
    if len(sys.argv)<2:
        raise SystemExit("Uso: python scripts/ejecutar.py unidad1/01-contratos-e-invariantes [argumentos]")
    carpeta=(ROOT/sys.argv[1]).resolve()
    if not carpeta.is_relative_to(ROOT) or not (carpeta/"Main.java").is_file():
        raise SystemExit("Selecciona una carpeta de ejemplo del curso")
    with tempfile.TemporaryDirectory() as temporal:
        compilar(Path(temporal),sorted((ROOT/"src/curso").glob("*.java"))+[carpeta/"Main.java"])
        subprocess.run(JAVA+["-cp",temporal,"Main"]+sys.argv[2:],cwd=ROOT,check=True)

if __name__=="__main__":main()
