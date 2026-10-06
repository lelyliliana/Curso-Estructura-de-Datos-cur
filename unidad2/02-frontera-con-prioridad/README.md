# La frontera de Dijkstra y entradas desactualizadas

[Inicio](../../README.md) · [Unidad](../../unidad2/README.md) · [Anterior](../../unidad2/01-grafos-ponderados/README.md)

## Objetivo

Usar una cola de prioridad para procesar el menor costo provisional y descartar versiones antiguas.

## Desarrollo conceptual

Una distancia provisional puede mejorar varias veces antes de que un vértice quede procesado con su costo mínimo. Una cola de prioridad debe reflejar esas mejoras. Algunas implementaciones modifican la prioridad de una entrada existente mediante decrease-key; PriorityQueue de Java no ofrece directamente esa operación con un índice por vértice.

La alternativa del curso inserta una entrada nueva cada vez que mejora una distancia y conserva la anterior. Al retirar una entrada, compara su costo con la distancia vigente. Si no coincide, la entrada está desactualizada y se descarta sin relajar vecinos. No es un error que la cola tenga varias entradas para el mismo vértice.

Ejemplo: se conoce 0→1 con costo 10, pero luego 0→2→1 cuesta 3. La cola puede contener (1,10) y (1,3). Cuando se retire (1,10), la comparación evita repetir trabajo con una distancia que ya fue reemplazada. Las mejoras deben ser estrictas; actualizar también en igualdad puede producir trabajo innecesario y predecesores problemáticos con ciclos de costo cero.

El comparador ordena por costo y luego por número de vértice para hacer los ejemplos deterministas. Un desempate determinista no garantiza una ruta lexicográficamente mínima entre todas las rutas óptimas; el contrato garantiza el costo mínimo y una ruta válida.

## Caso paso a paso

El ejemplo de cola muestra primero la entrada de costo 3 y después la de costo 10. En el algoritmo real, la segunda se omite porque ya no representa la distancia actual. Observa la comprobación paso.costo != d[u] en el código.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Grafo](../../src/curso/Grafo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        PriorityQueue<Grafo.Paso> q=new PriorityQueue<>(Comparator.comparingLong(Grafo.Paso::costo).thenComparingInt(Grafo.Paso::vertice));
        q.add(new Grafo.Paso(1,10));
        q.add(new Grafo.Paso(1,3));
        while(!q.isEmpty())System.out.println(q.remove());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad2/02-frontera-con-prioridad
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad2/02-frontera-con-prioridad
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
Paso[vertice=1, costo=3]
Paso[vertice=1, costo=10]
```


## Costos y límites

Con entradas duplicadas, pueden almacenarse O(E+1) entradas. El costo general es O(V+E log(E+1)); en grafos simples se expresa habitualmente como O((V+E)log V).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Marcar un vértice visitado cuando se inserta por primera vez; modificar la prioridad de un objeto ya en la cola; relajar usando una entrada vieja.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Agrega una tercera mejora de costo 2. Indica qué entradas pueden quedar en la cola y cuáles deben procesarse.

<details>
<summary>Ver una solución razonada</summary>

Pueden quedar costos 10, 3 y 2 para el mismo vértice. Solo la entrada cuyo costo coincide con d[v]=2 sirve para relajar; las otras se descartan cuando salen.

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

[Anterior](../../unidad2/01-grafos-ponderados/README.md) · [Índice de la unidad](../../unidad2/README.md) · [Siguiente recurso](../../unidad2/03-dijkstra/README.md)
