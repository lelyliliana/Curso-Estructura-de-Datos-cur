# Entrenamiento del perceptrón y sus límites

[Inicio](../../README.md) · [Unidad](../../unidad3/README.md) · [Anterior](../../unidad3/03-red-de-dos-capas/README.md)

## Objetivo

Actualizar pesos a partir de errores y distinguir convergencia de un límite de épocas.

## Desarrollo conceptual

El aprendizaje supervisado utiliza ejemplos con etiquetas. El perceptrón compara su predicción con la etiqueta y calcula delta=real-predicha. Si hay error, actualiza w[j] += tasa·delta·x[j] y b += tasa·delta. La tasa es positiva y controla el tamaño de la modificación.

Una época recorre todos los ejemplos. La implementación termina cuando una época no registra errores o cuando alcanza el máximo indicado. El retorno es la cantidad de errores encontrados durante la última pasada; no es necesariamente la cantidad de errores del modelo final evaluado después de todas las actualizaciones. Para medir esa última cantidad hay que volver a predecir el conjunto.

El teorema de convergencia del perceptrón requiere separabilidad lineal y condiciones adecuadas. OR es separable y sirve como caso pequeño. XOR no es separable para un perceptrón simple, de modo que aumentar indefinidamente las épocas no resuelve su limitación de representación.

Entrenar y evaluar sobre los mismos ejemplos solo comprueba ajuste sobre ese conjunto. No estima el desempeño en datos nuevos. Los datos sintéticos de la lección permiten entender la actualización, pero un proyecto predictivo necesita separación de entrenamiento y evaluación, control de sesgos y métricas relacionadas con el problema. La validación de dimensiones y etiquetas se hace antes de comenzar para evitar una actualización parcial por un registro mal formado.

## Caso paso a paso

Entrena OR con cuatro parejas binarias y etiquetas 0,1,1,1. Después imprime predicciones del modelo final. Repite conceptualmente con XOR y explica por qué el límite de épocas es necesario.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Modelos](../../src/curso/Modelos.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        double[][] x={
            {
                0,0
            },{
                0,1
            },{
                1,0
            },{
                1,1
            }
        };
        int[] y={
            0,1,1,1
        };
        Modelos.Perceptron p=new Modelos.Perceptron(2);
        p.entrenar(x,y,1,30);
        int[] pred=new int[x.length];
        for(int i=0; i<x.length; i++){
            pred[i]=p.predecir(x[i]);
            System.out.println(Arrays.toString(x[i])+" -> "+pred[i]);
        }System.out.println("exactitud de entrenamiento="+Modelos.exactitud(y,pred));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad3/04-aprendizaje-del-perceptron
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad3/04-aprendizaje-del-perceptron
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[0.0, 0.0] -> 0
[0.0, 1.0] -> 1
[1.0, 0.0] -> 1
[1.0, 1.0] -> 1
exactitud de entrenamiento=1.0
```


## Costos y límites

Hasta O(epocas·n·d) tiempo; parámetros O(d). No se garantiza convergencia para datos no separables.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Prometer convergencia para XOR; usar errores de la última pasada como exactitud final; evaluar generalización con los datos de entrenamiento.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Calcula una actualización con x=[1,0], etiqueta=1, predicción=0 y tasa=0.5. ¿Qué pesos cambian?

<details>
<summary>Ver una solución razonada</summary>

delta=1. w[0] aumenta 0.5, w[1] no cambia y b aumenta 0.5. La modificación usa cada componente de x.

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

[Anterior](../../unidad3/03-red-de-dos-capas/README.md) · [Índice de la unidad](../../unidad3/README.md) · [Siguiente recurso](../../unidad3/05-mineria-y-evaluacion/README.md)
