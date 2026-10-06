# Vectores, matrices y grafos de cálculo

[Inicio](../../README.md) · [Unidad](../../unidad3/README.md) · [Anterior](../../unidad2/12-seleccion-y-comprobacion/README.md)

## Objetivo

Relacionar dimensiones de arreglos con las entradas y salidas de una operación de cálculo.

## Desarrollo conceptual

Un vector reúne valores ordenados y una matriz organiza valores por filas y columnas. En una capa de una red, cada fila de la matriz de pesos corresponde a una salida y cada columna a una entrada. Si hay d entradas y m salidas, los pesos tienen forma m×d y el vector de sesgos tiene longitud m.

El producto de una fila con la entrada suma x[i]·w[i] y agrega el sesgo. Es importante validar dimensiones: Java permite arreglos de arreglos con filas de tamaños distintos, por lo que double[][] no garantiza por sí solo una matriz rectangular. La función del curso valida cada fila al procesarla.

Un tensor generaliza arreglos a más dimensiones. Esa palabra no implica automáticamente un grafo de búsqueda, un árbol o una base de datos. Un grafo de cálculo representa operaciones y dependencias entre resultados; un grafo vial representa lugares y conexiones. Ambos usan nodos y aristas, pero sus significados y algoritmos aplicables son diferentes.

Los ejemplos usan CPU y arreglos pequeños, sin bibliotecas externas ni GPU. Permiten observar dimensiones y costos; no sustituyen un sistema de entrenamiento con diferenciación automática. Cada entrada, peso y resultado debe ser finito. Un valor NaN puede propagarse silenciosamente y hacer que las comparaciones produzcan resultados difíciles de interpretar.

## Caso paso a paso

Con x=[2,3], w=[4,-1] y sesgo=1, el resultado lineal es 2·4+3·(-1)+1=6. La matriz de dos filas aplica dos productos separados sobre el mismo vector.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Modelos](../../src/curso/Modelos.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        double[] x={
            2,3
        };
        System.out.println(Modelos.producto(x,new double[]{
            4,-1
        },1));
        System.out.println(Arrays.toString(Modelos.capa(x,new double[][]{
            {
                4,-1
            },{
                -2,5
            }
        },new double[]{
            1,0
        },true)));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad3/01-vectores-y-matrices
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad3/01-vectores-y-matrices
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
6.0
[6.0, 11.0]
```


## Costos y límites

Producto de d componentes O(d). Capa m×d: O(md), salida O(m). Pesos almacenados O(md).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Intercambiar filas y columnas sin ajustar dimensiones; suponer que toda matriz double[][] es rectangular; confundir grafo de cálculo con grafo de rutas.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Calcula la salida para w=[-2,5] y sesgo=0. Explica qué ocurre con una fila de tres pesos y dos entradas.

<details>
<summary>Ver una solución razonada</summary>

La salida lineal es -4+15=11. Una fila de tres pesos es incompatible y producto lanza IllegalArgumentException antes de realizar un cálculo incorrecto.

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

[Anterior](../../unidad2/12-seleccion-y-comprobacion/README.md) · [Índice de la unidad](../../unidad3/README.md) · [Siguiente recurso](../../unidad3/02-neurona-y-activacion/README.md)
