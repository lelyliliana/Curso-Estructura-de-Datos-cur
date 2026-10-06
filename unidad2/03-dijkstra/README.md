# Dijkstra: relajación y caminos mínimos

[Inicio](../../README.md) · [Unidad](../../unidad2/README.md) · [Anterior](../../unidad2/02-frontera-con-prioridad/README.md)

## Objetivo

Ejecutar Dijkstra con pesos no negativos y justificar por qué puede finalizar una distancia.

## Desarrollo conceptual

Dijkstra resuelve las distancias mínimas desde un origen en un grafo cuyos pesos son no negativos. Inicializa el origen en cero y los demás vértices en INF. La frontera contiene el origen; cada vez se retira una entrada vigente de menor costo y se examinan sus aristas salientes.

Relajar u→v significa comparar d[u]+peso(u,v) con d[v]. Si el candidato es menor, se reemplaza d[v], se registra u como predecesor y se agrega una entrada nueva a la cola. La suma se calcula únicamente desde un vértice alcanzado, no desde INF.

La no negatividad es esencial para el argumento de corrección: prolongar un camino no puede reducir su costo. Cuando se procesa el menor costo vigente, una ruta que todavía pase por un vértice más caro no podrá mejorarlo. Una arista negativa destruye ese razonamiento, aunque algunas entradas particulares parezcan funcionar.

El algoritmo encuentra caminos de menor suma de pesos, no necesariamente con menos aristas. BFS resuelve el mínimo número de aristas en grafos sin ponderación, o costos uniformes positivos. Si los pesos representan minutos, una ruta con más conexiones puede tardar menos. El origen tiene distancia cero y su ruta contiene únicamente el propio origen.

## Caso paso a paso

Usa 0→1 de costo 10, 0→2 de costo 1, 2→1 de costo 2 y 1→3 de costo 1. Primero mejora 2, luego 1 pasa de 10 a 3 y finalmente 3 queda en 4. La ruta es [0,2,1,3].

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Grafo](../../src/curso/Grafo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(4,true);
        g.agregar(0,1,10);
        g.agregar(0,2,1);
        g.agregar(2,1,2);
        g.agregar(1,3,1);
        Grafo.Rutas r=g.dijkstra(0);
        for(int v=0; v<4; v++)System.out.println(v+": "+r.distancia(v)+" "+r.camino(v));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad2/03-dijkstra
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad2/03-dijkstra
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
0: 0 [0]
1: 3 [0, 2, 1]
2: 1 [0, 2]
3: 4 [0, 2, 1, 3]
```


## Costos y límites

Implementación con cola y duplicados: O(V+E log(E+1)), espacio O(V+E). En grafo simple: O((V+E)log V).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Aplicar Dijkstra a pesos negativos; confundir mínimo costo con mínimo número de conexiones; marcar definitivo al descubrir.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Cambia 0→1 a costo 2. Calcula la nueva distancia a 3 y una ruta óptima.

<details>
<summary>Ver una solución razonada</summary>

La distancia a 3 es 3 por [0,1,3]. La ruta vía 2 cuesta 4 y deja de ser óptima. Las distancias se comparan por suma de pesos.

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

[Anterior](../../unidad2/02-frontera-con-prioridad/README.md) · [Índice de la unidad](../../unidad2/README.md) · [Siguiente recurso](../../unidad2/04-predecesores/README.md)
