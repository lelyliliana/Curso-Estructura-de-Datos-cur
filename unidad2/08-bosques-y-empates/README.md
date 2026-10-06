# Grafos desconectados y bosques mínimos

[Inicio](../../README.md) · [Unidad](../../unidad2/README.md) · [Anterior](../../unidad2/07-kruskal/README.md)

## Objetivo

Interpretar la salida de Kruskal cuando no existe un árbol que conecte todos los vértices.

## Desarrollo conceptual

Si el grafo está desconectado, no existe un árbol de expansión que incluya todos los vértices. Kruskal sigue funcionando y obtiene un bosque de expansión mínimo: un árbol mínimo por cada componente que tenga conexiones y vértices aislados para las componentes de tamaño uno.

La salida informa aristas, costo y cantidad de componentes. Si el bosque tiene C componentes, contiene V-C aristas. Esta igualdad es útil para comprobar la estructura, pero no demuestra por sí sola que su costo sea mínimo. También deben verificarse ausencia de ciclos y cobertura de cada componente original.

Los empates no invalidan el algoritmo. En un cuadrado con todos los pesos iguales, varias elecciones de tres aristas conectan sus cuatro vértices con el mismo costo. Una prueba que compare únicamente una lista exacta de aristas puede rechazar una solución igualmente correcta. Para grafos pequeños se puede enumerar árboles candidatos y comprobar el costo óptimo.

Un costo total bajo tampoco garantiza que exista conexión global. El ejemplo tiene una componente de tres vértices, otra de dos y un vértice aislado. El bosque representa esas limitaciones; no inventa una conexión para completar el número de aristas de un árbol. En el caso V=0 hay cero componentes, cero aristas y costo cero.

## Caso paso a paso

Construye seis vértices con 0–1=2, 1–2=3 y 3–4=1. El bosque contiene tres aristas, costo 6 y tres componentes. El vértice 5 permanece aislado.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Grafo](../../src/curso/Grafo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(6,false);
        g.agregar(0,1,2);
        g.agregar(1,2,3);
        g.agregar(3,4,1);
        Grafo.Bosque b=g.kruskal();
        System.out.println("aristas="+b.aristas().size());
        System.out.println("costo="+b.costo()+", componentes="+b.componentes());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad2/08-bosques-y-empates
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad2/08-bosques-y-empates
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
aristas=3
costo=6, componentes=3
```


## Costos y límites

Mismo costo que Kruskal: O(V+E log(E+1)). La salida almacena V-C aristas.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Presentar un bosque como una red totalmente conectada; exigir V-1 aristas en un grafo desconectado; confundir empates con error.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Agrega 2–3=10. Calcula componentes, cantidad de aristas y costo. ¿Qué falta para conectar todos?

<details>
<summary>Ver una solución razonada</summary>

Quedan dos componentes, cuatro aristas y costo 16. Falta alguna arista que conecte el vértice 5 con la componente principal.

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

[Anterior](../../unidad2/07-kruskal/README.md) · [Índice de la unidad](../../unidad2/README.md) · [Siguiente recurso](../../unidad2/09-ruta-versus-red/README.md)
