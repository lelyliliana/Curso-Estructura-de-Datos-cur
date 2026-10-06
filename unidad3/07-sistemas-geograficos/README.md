# SIG: geometría, atributos y topología

[Inicio](../../README.md) · [Unidad](../../unidad3/README.md) · [Anterior](../../unidad3/06-indice-espacial-kd/README.md)

## Objetivo

Separar la ubicación geográfica de la conectividad y del costo utilizado por un algoritmo.

## Desarrollo conceptual

Un sistema de información geográfica combina geometrías, atributos y operaciones de análisis espacial. Un lugar puede tener una geometría Point y atributos como código o nombre. Una vía puede mostrarse como LineString, pero su dibujo no determina automáticamente cómo se conecta con las demás.

Dos líneas que se cruzan visualmente pueden representar un puente sin intercambio. Dos puntos cercanos pueden estar separados por una barrera. Para calcular rutas se necesita una topología explícita: vértices, conexiones válidas, dirección y costos. El grafo del proyecto conserva esa información de forma independiente a las coordenadas.

El costo de cada conexión se carga como un entero no negativo. No se deduce del tramo recto entre coordenadas ni se afirma que represente minutos o kilómetros reales. La red es ficticia y pequeña; no utiliza cartografía oficial ni ofrece indicaciones de desplazamiento reales.

Una visualización permite inspeccionar datos, pero no prueba la validez del modelo. Antes de ejecutar Dijkstra hay que revisar códigos únicos, extremos existentes, límites de coordenadas y unidades coherentes. Un índice espacial puede ayudar a buscar geometrías cercanas; un índice por código ayuda a resolver vértices; ninguno reemplaza la lista de conexiones. Las estructuras se complementan porque responden a preguntas distintas.

## Caso paso a paso

La red de ejemplo tiene cuatro lugares y cuatro conexiones. La ruta a→d cuesta 9 por a,b,c,d, aunque exista un segmento directo a→c con costo 10. Las coordenadas se usan para exportar el mapa y los costos para resolver la ruta.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Red](../../src/curso/Red.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Red r=Red.ejemplo();
        System.out.println(r.ruta("a","d"));
        System.out.println("costo de red mínima="+r.bosque().costo());
        System.out.println("componentes="+r.bosque().componentes());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad3/07-sistemas-geograficos
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad3/07-sistemas-geograficos
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[a, b, c, d]; costo=9
costo de red mínima=9
componentes=1
```


## Costos y límites

Construir el índice de códigos TreeMap O(V log V); almacenar red O(V+E). Una ruta usa Dijkstra, más la resolución de códigos.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Crear conexión por cercanía visual; usar grados como costo en minutos; afirmar que un LineString define por sí solo una carretera transitable.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Agrega un lugar cercano sin conexiones. ¿Debe Dijkstra alcanzarlo? ¿Puede aparecer en el mapa?

<details>
<summary>Ver una solución razonada</summary>

No es alcanzable desde otros vértices, pero puede aparecer como Point en el mapa. Ubicación y conectividad son propiedades separadas.

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

[Anterior](../../unidad3/06-indice-espacial-kd/README.md) · [Índice de la unidad](../../unidad3/README.md) · [Siguiente recurso](../../unidad3/08-geojson/README.md)
