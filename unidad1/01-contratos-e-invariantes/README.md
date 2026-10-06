# Contratos, invariantes y costos

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../docs/prerrequisitos.md)

## Objetivo

Distinguir el comportamiento de un tipo abstracto de datos de su representación y justificar las precondiciones de una operación.

## Desarrollo conceptual

Un conjunto almacena claves únicas y ofrece operaciones como insertar, eliminar y consultar. Ese contrato no obliga a usar un arreglo, un árbol o una tabla hash. La representación es una decisión de implementación; el contrato describe lo que puede observar quien utiliza la estructura. Insertar una clave repetida en los conjuntos de esta unidad no cambia su contenido. En los índices clave–valor, en cambio, una clave repetida actualiza el registro asociado.

Una invariante es una propiedad que debe conservarse después de cada operación pública. En un árbol de búsqueda, las claves de la izquierda son menores que la clave del nodo y las de la derecha son mayores. Un AVL agrega una condición de balance. Una estructura puede imprimir datos ordenados y aun tener alturas almacenadas incorrectas; por eso las pruebas deben revisar tanto el resultado como la representación.

El costo depende del tamaño de la entrada y del modelo de trabajo. O(log n) no significa un número fijo de milisegundos. También interesa el espacio auxiliar, la peor secuencia posible y el costo amortizado de varias operaciones. Aquí n representa claves almacenadas, h la altura y t el grado mínimo de un árbol B. Antes de comparar dos implementaciones, hay que comprobar que cumplen el mismo contrato.

## Caso paso a paso

Inserta 30, 10 y 20. El conjunto tiene tres claves aunque se vuelva a insertar 20. El recorrido debe ser [10, 20, 30]. El verificador inspecciona límites de búsqueda, alturas y balance; su costo es lineal y se usa para comprobar, no para acompañar cada operación de producción.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de AVL](../../src/curso/AVL.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        AVL a=new AVL();
        for(int k:new int[]{
            30,10,20,20
        })a.insertar(k);
        a.verificar();
        System.out.println(a.orden());
        System.out.println(a.contiene(20));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/01-contratos-e-invariantes
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/01-contratos-e-invariantes
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[10, 20, 30]
true
```


## Costos y límites

Consulta, inserción y eliminación AVL: O(log n). Recorrido y verificación: O(n). Espacio almacenado: O(n).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Confundir un recorrido ordenado con una prueba suficiente de balance; medir solo un caso pequeño; olvidar definir duplicados.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Inserta 20 dos veces más, elimina 99 y explica qué debe cambiar. Diseña una precondición y una poscondición para contiene.

<details>
<summary>Ver una solución razonada</summary>

El conjunto permanece [10, 20, 30]. Eliminar una clave ausente no altera el contenido. contiene(k) devuelve verdadero exactamente cuando k pertenece al conjunto; no modifica el árbol.

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

[Anterior](../../docs/prerrequisitos.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad1/02-alturas-y-factor-avl/README.md)
