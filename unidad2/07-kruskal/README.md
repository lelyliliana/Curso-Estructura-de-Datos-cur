# Kruskal y árbol de expansión mínimo

[Inicio](../../README.md) · [Unidad](../../unidad2/README.md) · [Anterior](../../unidad2/06-conjuntos-disjuntos/README.md)

## Objetivo

Seleccionar conexiones que minimicen el costo total sin introducir ciclos.

## Desarrollo conceptual

Un árbol de expansión de un grafo no dirigido conectado incluye todos sus vértices y exactamente V-1 aristas, sin ciclos. Un árbol de expansión mínimo (MST) minimiza la suma de pesos entre todos esos árboles. No busca una ruta mínima entre dos lugares ni conserva necesariamente todas las distancias originales.

Kruskal ordena las aristas de menor a mayor peso. Examina cada una y la acepta si sus extremos pertenecen a componentes distintas de DSU. Al aceptar, une esas componentes. Si ya estaban conectados por las aristas seleccionadas, agregar la arista formaría un ciclo y se descarta.

La justificación se basa en cortes: una arista de menor peso que cruza entre componentes puede formar parte de alguna solución óptima compatible con las elecciones previas. Los empates pueden producir varios árboles mínimos con el mismo costo. El comparador agrega extremos como criterios secundarios para que el ejemplo sea reproducible, sin afirmar que la solución matemática sea única.

Kruskal permite pesos negativos: el problema exige un árbol sin ciclos y no admite repetir una arista ilimitadamente. Los lazos siempre se descartan porque sus extremos ya tienen el mismo representante. Las aristas paralelas compiten por costo. La biblioteca rechaza grafos dirigidos para no confundir este problema con una arborescencia dirigida.

## Caso paso a paso

Para el triángulo 0–1=1, 1–2=2 y 0–2=5, Kruskal acepta las dos primeras y descarta la tercera. El costo total es 3 y quedan V-1=2 aristas.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Grafo](../../src/curso/Grafo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(3,false);
        g.agregar(0,1,1);
        g.agregar(1,2,2);
        g.agregar(0,2,5);
        Grafo.Bosque b=g.kruskal();
        System.out.println(b.aristas());
        System.out.println("costo="+b.costo());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad2/07-kruskal
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad2/07-kruskal
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[Arista[origen=0, destino=1, peso=1], Arista[origen=1, destino=2, peso=2]]
costo=3
```


## Costos y límites

Ordenar O(E log(E+1)); DSU O(E α(V)); inicializar O(V). Memoria O(V+E).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Elegir todas las aristas baratas aunque formen ciclo; usar Kruskal para rutas; afirmar que la solución es única cuando hay empates.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Agrega una arista paralela 0–2 de costo 0. ¿Qué árbol queda y cuál es su costo?

<details>
<summary>Ver una solución razonada</summary>

Se aceptan 0–2=0 y 0–1=1, costo 1. La arista 1–2=2 queda descartada porque cerraría el ciclo.

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

[Anterior](../../unidad2/06-conjuntos-disjuntos/README.md) · [Índice de la unidad](../../unidad2/README.md) · [Siguiente recurso](../../unidad2/08-bosques-y-empates/README.md)
