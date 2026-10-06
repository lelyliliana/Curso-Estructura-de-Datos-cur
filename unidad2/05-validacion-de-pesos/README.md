# Pesos negativos, límites y pruebas de rutas

[Inicio](../../README.md) · [Unidad](../../unidad2/README.md) · [Anterior](../../unidad2/04-predecesores/README.md)

## Objetivo

Definir precondiciones numéricas y rechazar entradas incompatibles con Dijkstra.

## Desarrollo conceptual

Un algoritmo correcto en números reales puede fallar en un programa que usa enteros finitos. Sumar un centinela muy grande con un peso puede desbordar y producir un número negativo que parezca una mejora. Por eso hay que separar ausencia, finitud y límites de entrada.

La biblioteca guarda costos en long, define INF como Long.MAX_VALUE/4 y restringe el valor absoluto de cada peso a 10⁹. Con un máximo de 10000 vértices, un camino simple tiene como máximo 9999 aristas y su costo absoluto queda muy por debajo de INF. Dijkstra suma desde entradas alcanzadas y no negativas, dentro de ese dominio.

Dijkstra revisa todas las aristas antes de comenzar. Si encuentra un peso negativo, rechaza el grafo incluso si esa arista está en otra componente. Esta es una precondición del contrato público que evita que el resultado dependa de si el origen alcanza la entrada problemática. Un algoritmo diferente, como Bellman–Ford, es apropiado para caminos desde un origen con aristas negativas y detección de ciclos; aquí se estudia Floyd–Warshall para todos los pares.

Las pruebas incluyen pesos cero, aristas paralelas, vértices aislados y entradas fuera del dominio. Comprobar solamente la ruta más visible de un ejemplo no es suficiente. Para grafos pequeños puede compararse Dijkstra con otro método independiente que resuelva el mismo problema.

## Caso paso a paso

Crea una arista de costo -1. agregar la admite para algoritmos que permiten negativos, pero dijkstra lanza IllegalArgumentException con un mensaje claro. El rechazo es comportamiento esperado y se captura en el ejemplo.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Grafo](../../src/curso/Grafo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(2,true);
        g.agregar(0,1,-1);
        try{
            g.dijkstra(0);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad2/05-validacion-de-pesos
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad2/05-validacion-de-pesos
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
Dijkstra requiere pesos no negativos
```


## Costos y límites

Validación global de pesos: O(E). Costos de camino usan long; el dominio declarado evita desbordes en Dijkstra.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Aceptar negativos porque un ejemplo funcionó; sumar INF; usar int para costos acumulados; esconder una entrada inválida como sin ruta.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Calcula el costo de un camino de cuatro aristas de 10⁹. ¿Cabe en int? ¿Cabe en long?

<details>
<summary>Ver una solución razonada</summary>

El costo es 4·10⁹. Supera Integer.MAX_VALUE y no cabe en int, pero sí cabe en long y queda dentro del dominio admitido.

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

[Anterior](../../unidad2/04-predecesores/README.md) · [Índice de la unidad](../../unidad2/README.md) · [Siguiente recurso](../../unidad2/06-conjuntos-disjuntos/README.md)
