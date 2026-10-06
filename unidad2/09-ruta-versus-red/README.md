# Ruta mínima frente a red mínima

[Inicio](../../README.md) · [Unidad](../../unidad2/README.md) · [Anterior](../../unidad2/08-bosques-y-empates/README.md)

## Objetivo

Elegir el algoritmo según la función objetivo y demostrar que un MST puede alargar una ruta.

## Desarrollo conceptual

Una ruta mínima reduce la suma de pesos para una pareja origen–destino. Una red mínima reduce la suma de conexiones necesarias para mantener conectada la red completa. Son funciones objetivo distintas y sus soluciones pueden diferir incluso en un grafo muy pequeño.

Supón un triángulo con 0–1=2, 1–2=2 y 0–2=3. La ruta mínima de 0 a 2 usa la conexión directa y cuesta 3. El árbol de expansión mínimo utiliza las dos conexiones de costo 2, con costo total 4. Dentro de ese árbol, viajar de 0 a 2 cuesta 4. El árbol ahorra costo total de infraestructura respecto a incluir todas las conexiones, pero no conserva cada ruta óptima.

La interpretación depende del negocio: diseñar un cableado básico puede pedir conexión total de menor costo; planear un viaje pide una ruta; garantizar redundancia exige más que un árbol, porque retirar una sola conexión de un árbol separa la red. Ni Dijkstra ni Kruskal resuelven automáticamente todos esos requisitos adicionales.

Antes de implementar, escribe qué se minimiza, qué se debe conectar y qué restricciones existen. Después escoge la estructura auxiliar: Dijkstra usa distancias y prioridad; Kruskal usa orden de aristas y DSU. Que ambos empleen decisiones de menor peso no significa que estén resolviendo el mismo problema.

## Caso paso a paso

Calcula la ruta sobre el grafo completo y después el costo total del MST. La salida 3 frente a 4 no es una contradicción: el primer valor corresponde a una pareja y el segundo a una red con todos los vértices.

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
        g.agregar(0,1,2);
        g.agregar(1,2,2);
        g.agregar(0,2,3);
        System.out.println("ruta 0 a 2="+g.dijkstra(0).distancia(2));
        System.out.println("red mínima="+g.kruskal().costo());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad2/09-ruta-versus-red
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad2/09-ruta-versus-red
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
ruta 0 a 2=3
red mínima=4
```


## Costos y límites

Dijkstra y Kruskal conservan sus costos respectivos. La elección depende del objetivo, no solo de la complejidad.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Usar el MST como sustituto de todas las rutas mínimas; presentar un árbol como red con tolerancia a fallos.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Propón un problema que requiera ruta mínima, otro MST y otro que necesite una restricción adicional.

<details>
<summary>Ver una solución razonada</summary>

Viaje de a a b: ruta mínima. Cableado que conecte todos los equipos con costo total mínimo: MST. Red que permanezca conectada después de fallar una conexión: exige redundancia y otro modelo.

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

[Anterior](../../unidad2/08-bosques-y-empates/README.md) · [Índice de la unidad](../../unidad2/README.md) · [Siguiente recurso](../../unidad2/10-floyd-warshall/README.md)
