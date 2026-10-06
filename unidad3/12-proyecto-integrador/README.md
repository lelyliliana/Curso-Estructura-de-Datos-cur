# Proyecto: red, rutas, mapa y conversación

[Inicio](../../README.md) · [Unidad](../../unidad3/README.md) · [Anterior](../../unidad3/11-chatbot-por-estados/README.md)

## Objetivo

Integrar estructuras y algoritmos con un modelo de entrada validado y evidencia de resultados.

## Desarrollo conceptual

El proyecto reúne una red vial ficticia, consultas de rutas, un bosque de conexiones de menor costo, exportación GeoJSON y una conversación por estados. Los códigos identifican lugares; sus coordenadas se usan para el mapa; los costos enteros no negativos se usan para los algoritmos. No se infieren conexiones ni costos a partir de la posición.

Red mantiene copias de los registros, un TreeMap para resolver códigos y un grafo de listas. Los códigos son minúsculas, dígitos, guion o guion bajo, con longitud entre 1 y 20. Se rechazan duplicados, lazos, extremos desconocidos, conexiones repetidas y pesos fuera del dominio. El máximo del laboratorio es 500 lugares.

La carga usa dos archivos UTF-8 sin encabezados: lugares con codigo;longitud;latitud y conexiones con origen;destino;costo. Se construye un candidato completo antes de entregarlo. Si una línea es inválida, no se devuelve una red parcialmente cargada. No hay un importador general de GeoJSON; se exportan los datos previamente validados.

El modo demostración imprime resultados reproducibles. El modo interactivo ofrece ruta, bosque, exportación y bot, con manejo de errores de entrada. La exportación escribe directamente el archivo indicado y puede reemplazarlo; no es una transacción ni una escritura atómica. Las extensiones deben conservar invariantes y añadir pruebas: por ejemplo, dirección por conexión, identificación de aristas o índices espaciales de otra proyección.

## Caso paso a paso

Ejecuta la demostración, consulta a→d y comprueba costo 9. El bosque cuesta 9 y tiene una componente. Exporta y valida el JSON. Después modifica el archivo de conexiones para dejar un lugar aislado y comprueba sin ruta y más de una componente.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Red](../../src/curso/Red.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.nio.file.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Red r=Red.ejemplo();
        if(args.length==3 && args[0].equals("--menu"))r=Red.cargar(Path.of(args[1]),Path.of(args[2]));
        else if(args.length>0 && !(args.length==1 && args[0].equals("--menu")))throw new IllegalArgumentException("Uso: --menu [lugares conexiones]");
        if(args.length==0){
            System.out.println(r.ruta("a","d"));
            System.out.println("bosque="+r.bosque().costo()+", componentes="+r.bosque().componentes());
            Bot b=r.bot();
            for(String m:new String[]{
                "ruta","a","c"
            })System.out.println(b.responder(m));
            return;
        }
        Scanner entrada=new Scanner(System.in, java.nio.charset.StandardCharsets.UTF_8);
        while(true){
            System.out.println("1 Ruta | 2 Bosque | 3 Exportar | 4 Bot | 0 Salir");
            if(!entrada.hasNextLine())return;
            String opcion=entrada.nextLine().trim();
            try {
                switch(opcion){
                    case "0" -> {
                        System.out.println("Fin");
                        return;
                    }
                    case "1" -> {
                        System.out.println("Origen:");
                        if(!entrada.hasNextLine())return;
                        String a=entrada.nextLine().trim();
                        System.out.println("Destino:");
                        if(!entrada.hasNextLine())return;
                        String b=entrada.nextLine().trim();
                        System.out.println(r.ruta(a,b));
                    }
                    case "2" -> {
                        Grafo.Bosque b=r.bosque();
                        System.out.println("costo="+b.costo()+", componentes="+b.componentes());
                        System.out.println(b.aristas());
                    }
                    case "3" -> {
                        System.out.println("Archivo de salida (puede reemplazarlo):");
                        if(!entrada.hasNextLine())return;
                        r.exportar(Path.of(entrada.nextLine()));
                        System.out.println("Mapa exportado");
                    }
                    case "4" -> {
                        Bot b=r.bot();
                        System.out.println("Comandos: ruta, cancelar. Escribe salir para volver al menú.");
                        while(entrada.hasNextLine()){
                            String m=entrada.nextLine();
                            if(m.trim().equalsIgnoreCase("salir"))break;
                            System.out.println(b.responder(m));
                        }
                    }
                    default -> System.out.println("Opción inválida");
                }
            }catch(IllegalArgumentException | java.io.IOException e){
                System.out.println("Error: "+e.getMessage());
            }
        }
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad3/12-proyecto-integrador
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad3/12-proyecto-integrador
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[a, b, c, d]; costo=9
bosque=9, componentes=1
Escribe el código de origen
Escribe el código de destino
[a, b, c]; costo=5
```


Consulta la [guía del proyecto](../../proyecto/README.md) para cargar archivos y ejecutar el menú.

## Costos y límites

Construcción O(V log V+E log V). Ruta O(log V+V+E log(E+1)). Bosque O(V+E log(E+1)). Exportación O(V+E+S) memoria y tiempo de salida.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Confundir costo de infraestructura con costo de viaje; aceptar una carga parcial; ocultar una excepción como ruta inexistente; prometer persistencia atómica.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Entrega una red propia de seis lugares, una tabla de cinco consultas y pruebas para duplicados, aislamiento, costo cero y coordenadas inválidas. Explica las estructuras elegidas.

<details>
<summary>Ver una solución razonada</summary>

La evidencia debe incluir entradas, resultado esperado y observado, invariantes verificadas y costo. Un caso válido conserva códigos únicos; un caso inválido se rechaza antes de devolver Red. La comparación de rutas considera costo y pasos, no una única secuencia ante empates.

</details>

## Práctica y comprobación

1. Ejecuta el caso original y guarda su resultado.
2. Realiza el cambio propuesto en el ejercicio y contrasta con tu predicción.
3. Agrega un caso límite pertinente (vacío, ausencia, empate, extremo numérico o entrada inválida). Explica cuál aplica al contrato de esta lección.
4. Describe una propiedad comprobable y por qué una salida aparentemente correcta podría no ser suficiente.

Entrega la entrada utilizada, el resultado esperado y observado, y una explicación del costo de la operación principal. Si cambias un algoritmo, ejecuta las [pruebas del curso](../../tests/README.md).

## Preguntas de comprensión

- ¿Qué precondición necesita la operación y qué resultado promete?
- ¿Qué dato se modifica y qué propiedad debe conservarse?
- ¿Qué parte de la implementación explica el costo indicado?

[Anterior](../../unidad3/11-chatbot-por-estados/README.md) · [Índice de la unidad](../../unidad3/README.md) · [Siguiente recurso](../../proyecto/README.md)
