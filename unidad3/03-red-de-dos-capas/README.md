# Red de dos capas y no linealidad

[Inicio](../../README.md) · [Unidad](../../unidad3/README.md) · [Anterior](../../unidad3/02-neurona-y-activacion/README.md)

## Objetivo

Ejecutar una propagación hacia adelante y explicar por qué varias capas lineales siguen siendo lineales.

## Desarrollo conceptual

La propagación hacia adelante calcula una capa y utiliza su salida como entrada de la siguiente. Las dimensiones deben coincidir: si la primera produce m valores, cada fila de la matriz siguiente necesita m pesos. No se actualizan parámetros durante esa propagación.

Si todas las capas usan identidad, su composición es otra transformación afín. Agregar más capas de ese tipo no crea una frontera no lineal. Una activación como ReLU introduce cambios de régimen que permiten combinar regiones distintas del espacio de entrada.

El ejemplo representa XOR en las cuatro entradas binarias con parámetros elegidos a mano. La capa oculta calcula h1=max(0,x1+x2) y h2=max(0,x1+x2-1). La salida usa sigmoide(2h1-4h2-1). Con umbral 0.5 clasifica [0,0] y [1,1] como 0, y las otras dos como 1. No demuestra que un algoritmo haya aprendido XOR.

El diagrama conceptual es un grafo dirigido de dependencias: las entradas alimentan unidades ocultas y estas alimentan la salida. Los pesos pertenecen a conexiones de cálculo, no a distancias para Dijkstra. Las redes artificiales son modelos matemáticos inspirados en ciertos principios biológicos; este programa no reproduce un cerebro ni permite inferir capacidades generales a partir de cuatro ejemplos.

## Caso paso a paso

Para [1,1], h1=2 y h2=1; z=4-4-1=-1 y la clase es 0. Para [1,0], h1=1 y h2=0; z=1 y la clase es 1. Comprueba las cuatro entradas.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Modelos](../../src/curso/Modelos.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        for(double[] x:new double[][]{
            {
                0,0
            },{
                0,1
            },{
                1,0
            },{
                1,1
            }
        }){
            double[] h=Modelos.capa(x,new double[][]{
                {
                    1,1
                },{
                    1,1
                }
            },new double[]{
                0,-1
            },true);
            double p=Modelos.sigmoide(Modelos.producto(h,new double[]{
                2,-4
            },-1));
            System.out.println(Arrays.toString(x)+" -> "+(p>=0.5?1:0));
        }
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad3/03-red-de-dos-capas
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad3/03-red-de-dos-capas
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[0.0, 0.0] -> 0
[0.0, 1.0] -> 1
[1.0, 0.0] -> 1
[1.0, 1.0] -> 0
```


## Costos y límites

Capa d→m y m→r: O(dm+mr) por ejemplo; pesos O(dm+mr).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Presentar inferencia como entrenamiento; creer que dos capas identidad aportan no linealidad; interpretar cuatro resultados como validación general.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Reemplaza ambas activaciones ocultas por identidad. Explica por qué no puedes conservar la frontera XOR con una sola salida lineal y umbral.

<details>
<summary>Ver una solución razonada</summary>

La composición afín produce una única frontera lineal. XOR no es linealmente separable en sus cuatro puntos binarios; necesita una representación no lineal para esta clasificación.

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

[Anterior](../../unidad3/02-neurona-y-activacion/README.md) · [Índice de la unidad](../../unidad3/README.md) · [Siguiente recurso](../../unidad3/04-aprendizaje-del-perceptron/README.md)
