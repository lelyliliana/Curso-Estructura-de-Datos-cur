# GeoJSON: intercambio y validación

[Inicio](../../README.md) · [Unidad](../../unidad3/README.md) · [Anterior](../../unidad3/07-sistemas-geograficos/README.md)

## Objetivo

Exportar geometrías con coordenadas longitud–latitud y mantener los atributos del grafo separados.

## Desarrollo conceptual

GeoJSON es un formato de intercambio basado en JSON. RFC 7946 define geometrías, Feature y FeatureCollection. No es un algoritmo de grafos ni establece automáticamente relaciones topológicas entre objetos. Una aplicación puede almacenar códigos de extremos en properties, pero debe definir cómo interpreta esos atributos.

La posición geográfica utiliza longitud primero y latitud después, en grados decimales sobre WGS 84. El exportador del curso acepta Point y LineString de dos puntos; valida longitud entre -180 y 180, latitud entre -90 y 90 y valores finitos. No implementa todos los tipos de geometría ni un importador general.

Cada lugar se exporta como Feature con id en sus propiedades. Cada conexión se exporta como LineString con origen, destino y costo. Las líneas son representaciones rectas entre los puntos y no trazados reales de vías. Los datos del proyecto están cerca unos de otros y no cruzan el antimeridiano; otros casos requieren tratamiento geográfico adicional.

Los identificadores se escapan como cadenas JSON, incluyendo comillas, barras inversas y caracteres de control. NaN e infinito no son números JSON válidos y se rechazan. Una prueba analiza el resultado con un parser independiente, verifica tipos y revisa cada conexión. Que un texto tenga llaves no demuestra que sea JSON válido ni que sus coordenadas estén en el orden correcto.

## Caso paso a paso

Exporta dos puntos a y b y una conexión de costo 3. La FeatureCollection contiene tres features: dos Point y una LineString. Observa que [-75.6,6.2] empieza con longitud, no latitud.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de GeoJSON](../../src/curso/GeoJSON.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(GeoJSON.exportar(List.of(new GeoJSON.Lugar("a",-75.6,6.2),new GeoJSON.Lugar("b",-75.59,6.21)),List.of(new GeoJSON.Conexion("a","b",3))));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad3/08-geojson
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad3/08-geojson
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
{"type":"FeatureCollection","features":[{"type":"Feature","properties":{"id":"a"},"geometry":{"type":"Point","coordinates":[-75.6,6.2]}},{"type":"Feature","properties":{"id":"b"},"geometry":{"type":"Point","coordinates":[-75.59,6.21]}},{"type":"Feature","properties":{"origen":"a","destino":"b","costo":3},"geometry":{"type":"LineString","coordinates":[[-75.6,6.2],[-75.59,6.21]]}}]}
```


## Costos y límites

Exportación O(V+E+S) tiempo y memoria, con S como tamaño de cadenas y salida. El texto completo se construye en memoria.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Llamar grafo a GeoJSON; invertir coordenadas; producir NaN; olvidar escapar comillas; asumir que un segmento representa una vía real.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Cambia un identificador a una cadena con comillas. Comprueba que el JSON siga siendo válido. ¿Qué ocurre con latitud 95?

<details>
<summary>Ver una solución razonada</summary>

Las comillas aparecen escapadas y el parser recupera la cadena original. Latitud 95 se rechaza al construir Lugar, antes de exportar.

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

[Anterior](../../unidad3/07-sistemas-geograficos/README.md) · [Índice de la unidad](../../unidad3/README.md) · [Siguiente recurso](../../unidad3/09-procesamiento-en-flujo/README.md)
