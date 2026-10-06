# Unidad lineal, sigmoide y perceptrón

[Inicio](../../README.md) · [Unidad](../../unidad3/README.md) · [Anterior](../../unidad3/01-vectores-y-matrices/README.md)

## Objetivo

Separar suma ponderada, activación e interpretación de la salida.

## Desarrollo conceptual

Una unidad calcula z=w·x+b y aplica una función de activación. Con identidad devuelve z. Con ReLU devuelve max(0,z). Con sigmoide devuelve 1/(1+exp(-z)). Las activaciones no son estructuras de búsqueda; son funciones que transforman un valor numérico.

En matemáticas, la sigmoide de un número real finito está estrictamente entre 0 y 1. En punto flotante puede redondearse a 0 o 1 para magnitudes grandes. La implementación utiliza dos fórmulas equivalentes según el signo de z para evitar calcular exp(-z) con un exponente positivo enorme. Rechaza NaN e infinito como entradas.

Una salida entre 0 y 1 no es por sí sola una probabilidad confiable. Su interpretación depende del modelo, los datos, el entrenamiento y la calibración. En este ejemplo los pesos se fijan manualmente, por lo que se demuestra inferencia y no aprendizaje ni desempeño predictivo real.

El perceptrón clásico usa un umbral para emitir una clase y una regla de actualización por errores. Una unidad logística utiliza una sigmoide; puede entrenarse con otra función de pérdida y otro procedimiento. Llamar a toda unidad con sigmoide perceptrón clásico oculta esa diferencia. Los nombres de los componentes deben acompañarse de la fórmula que realmente se ejecuta.

## Caso paso a paso

Para z=0, la sigmoide es 0.5. Con z=1000 redondea a 1; con z=-1000 a 0 en double. Esos extremos numéricos no invalidan la fórmula estable, pero deben distinguirse del intervalo abierto matemático.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Modelos](../../src/curso/Modelos.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        for(double z:new double[]{
            0,1000,-1000
        })System.out.println(z+" -> "+Modelos.sigmoide(z));
        System.out.println(Modelos.sigmoide(Modelos.producto(new double[]{
            1,2
        },new double[]{
            2,-1
        },0)));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad3/02-neurona-y-activacion
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad3/02-neurona-y-activacion
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
0.0 -> 0.5
1000.0 -> 1.0
-1000.0 -> 0.0
0.5
```


## Costos y límites

Activación escalar O(1); calcular z con d entradas O(d). Precisión limitada por double.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Llamar probabilidad a cualquier salida; afirmar que se entrenó al asignar pesos; usar una fórmula que desborde innecesariamente.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Calcula z para x=[1,2], w=[2,-1], b=0 y la salida sigmoide. ¿Se cambió algún peso?

<details>
<summary>Ver una solución razonada</summary>

z=0 y salida=0.5. No cambió ningún peso: es una evaluación con parámetros fijos.

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

[Anterior](../../unidad3/01-vectores-y-matrices/README.md) · [Índice de la unidad](../../unidad3/README.md) · [Siguiente recurso](../../unidad3/03-red-de-dos-capas/README.md)
