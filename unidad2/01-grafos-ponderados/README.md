# Grafos ponderados: representación y contrato

[Inicio](../../README.md) · [Unidad](../../unidad2/README.md) · [Anterior](../../unidad1/15-carga-y-eleccion-de-indices/README.md)

## Objetivo

Modelar dirección, peso y ausencia de conexión sin utilizar cero como señal de ausencia.

## Desarrollo conceptual

Un grafo ponderado asocia un costo a cada arista. El costo puede representar distancia, tiempo o una penalización, pero su unidad debe mantenerse uniforme dentro del problema. La dirección es una decisión independiente del peso: una calle de sentido único se representa con un arco; una conexión bidireccional puede representarse como arista no dirigida.

La biblioteca utiliza listas de adyacencia y vértices enteros de 0 a V-1. En un grafo no dirigido agrega una referencia de recorrido para cada sentido, pero conserva una sola arista original para Kruskal. Admite lazos y aristas paralelas; el proyecto integrador restringe esas posibilidades para definir una red simple.

Peso cero es un costo válido y no indica ausencia. Las matrices de caminos utilizan INF para una pareja sin ruta conocida. Un peso negativo también es válido para ciertos algoritmos, pero no para Dijkstra. En una red no dirigida, una arista negativa permite recorrer ida y vuelta con costo negativo, por lo que no tiene sentido buscar una ruta mínima permitiendo repeticiones ilimitadas.

La biblioteca limita V a 10000 y el valor absoluto del peso a mil millones. Esos límites hacen que los costos de caminos simples quepan en long. No se presentan como propiedades matemáticas de todos los grafos; son precondiciones concretas del laboratorio. Las vistas públicas copian las listas para impedir modificaciones externas.

## Caso paso a paso

Construye un grafo dirigido con 0→1 de costo 0 y 1→2 de costo 5. Consultar vecinos de 0 muestra una arista real de peso cero. No aparece automáticamente 1→0, porque el grafo fue declarado dirigido.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Grafo](../../src/curso/Grafo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(3,true);
        g.agregar(0,1,0);
        g.agregar(1,2,5);
        System.out.println(g.vecinos(0));
        System.out.println(g.vecinos(1));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad2/01-grafos-ponderados
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad2/01-grafos-ponderados
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[Arista[origen=0, destino=1, peso=0]]
[Arista[origen=1, destino=2, peso=5]]
```


## Costos y límites

Listas: espacio O(V+E), recorrer vecinos O(grado). Copiar una vista de vecinos cuesta O(grado). Matriz: O(V²) memoria.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Usar cero como ausencia; olvidar dirección; sumar costos de unidades distintas; llamar distancia geográfica a un costo arbitrario.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Agrega 0→2 de costo 8 y compara las dos rutas. ¿Qué cambia si el grafo es no dirigido?

<details>
<summary>Ver una solución razonada</summary>

La ruta 0→1→2 cuesta 5 y la directa cuesta 8. En el grafo no dirigido también se pueden recorrer las conexiones en sentido inverso; el peso de cada conexión sigue siendo el mismo.

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

[Anterior](../../unidad1/15-carga-y-eleccion-de-indices/README.md) · [Índice de la unidad](../../unidad2/README.md) · [Siguiente recurso](../../unidad2/02-frontera-con-prioridad/README.md)
