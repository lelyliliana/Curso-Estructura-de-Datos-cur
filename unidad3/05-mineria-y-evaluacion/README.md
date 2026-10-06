# Minería de datos, reglas y evaluación

[Inicio](../../README.md) · [Unidad](../../unidad3/README.md) · [Anterior](../../unidad3/04-aprendizaje-del-perceptron/README.md)

## Objetivo

Construir una regla de clasificación pequeña y mantener separados entrenamiento y prueba.

## Desarrollo conceptual

La minería de datos busca patrones o relaciones útiles en conjuntos de datos. Incluye tareas como clasificación, agrupamiento y detección de anomalías. No toda tarea exige una red neuronal. Un árbol de decisión usa preguntas sobre atributos para dirigir un ejemplo hacia una hoja; su estructura y mecanismo de aprendizaje difieren de una red de unidades ponderadas.

El ejemplo ajusta un árbol de un solo corte, llamado stump. Los datos de entrenamiento contienen valores ordenados 1,2,5,6 con etiquetas 0,0,1,1. Se prueban puntos medios entre vecinos y se escoge el que comete menos errores con la regla valor>umbral. Esta regla fija la orientación de las clases y no es un entrenador general de árboles.

Los valores de prueba 1.5 y 5.5 no intervienen en la elección del umbral. La exactitud es proporción de predicciones correctas, pero con dos ejemplos ofrece evidencia muy limitada. Si hay clases desbalanceadas, una alta exactitud puede ocultar errores importantes sobre la clase minoritaria. También se requieren métricas como precisión y recuperación según el objetivo.

Los atributos deben estar disponibles al momento de predecir. Usar una variable que revela indirectamente la etiqueta o elegir parámetros con los datos de prueba produce fuga de información. La limpieza, el diccionario de atributos, los valores ausentes y los criterios de evaluación son parte del trabajo, no pasos opcionales después de entrenar.

## Caso paso a paso

Los candidatos son 1.5, 3.5 y 5.5. El corte 3.5 clasifica sin errores los cuatro ejemplos de entrenamiento. Los dos ejemplos separados de prueba se clasifican 0 y 1.

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
            1,2,5,6
        };
        int[] y={
            0,0,1,1
        };
        double mejor=0;
        int minimo=Integer.MAX_VALUE;
        for(int i=0; i<x.length-1; i++){
            double t=(x[i]+x[i+1])/2;
            int errores=0;
            for(int j=0; j<x.length; j++)if((x[j]>t?1:0)!=y[j])errores++;
            if(errores<minimo){
                minimo=errores;
                mejor=t;
            }
        }double[] prueba={
            1.5,5.5
        };
        int[] pred=new int[2];
        for(int i=0; i<2; i++)pred[i]=prueba[i]>mejor?1:0;
        System.out.println("umbral="+mejor);
        System.out.println("exactitud de prueba="+Modelos.exactitud(new int[]{
            0,1
        },pred));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad3/05-mineria-y-evaluacion
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad3/05-mineria-y-evaluacion
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
umbral=3.5
exactitud de prueba=1.0
```


## Costos y límites

Con n valores ya ordenados, esta búsqueda sencilla prueba n-1 cortes y evalúa n ejemplos: O(n²). Existen acumulaciones que reducen la evaluación, pero no se implementan aquí.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Llamar al stump red neuronal; elegir el umbral con datos de prueba; extrapolar una exactitud de dos muestras a una población.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Agrega una prueba de clase 1 con valor 2.5. Calcula la exactitud sobre tres pruebas y explica la limitación.

<details>
<summary>Ver una solución razonada</summary>

El modelo predice 0 para 2.5 y falla esa muestra; la exactitud queda 2/3. Una regla perfecta en entrenamiento puede equivocarse en pruebas nuevas.

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

[Anterior](../../unidad3/04-aprendizaje-del-perceptron/README.md) · [Índice de la unidad](../../unidad3/README.md) · [Siguiente recurso](../../unidad3/06-indice-espacial-kd/README.md)
