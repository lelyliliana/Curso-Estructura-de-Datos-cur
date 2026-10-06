# Eliminación en árbol B: préstamo y fusión

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../unidad1/10-insercion-b/README.md)

## Objetivo

Explicar las reparaciones de ocupación antes del descenso y la contracción de la raíz.

## Desarrollo conceptual

Antes de descender para eliminar, el algoritmo procura que el hijo tenga al menos t claves. Si solo tiene t-1, puede pedir prestada una clave a un hermano que tenga al menos t. El separador del padre baja al hijo y una clave del hermano sube al padre. Si los nodos son internos también se mueve la referencia del hijo correspondiente.

Si ninguno de los hermanos puede prestar, se fusiona el hijo con un hermano y el separador que estaba entre ambos. La fusión reúne (t-1)+1+(t-1)=2t-1 claves, exactamente la capacidad máxima. El padre pierde una clave y una referencia. Por eso es importante preparar la ocupación del hijo antes de entrar en él.

Cuando la clave a eliminar está en un nodo interno, se usa el predecesor si el hijo izquierdo tiene al menos t claves, o el sucesor si el derecho tiene al menos t. Si ambos tienen el mínimo, se fusionan y la eliminación continúa dentro del nodo fusionado. En una hoja, borrar una clave es retirar su posición.

La raíz tiene una excepción de ocupación. Si queda sin claves y conserva un único hijo, ese hijo se convierte en raíz y la altura disminuye. Eliminar una clave ausente puede reorganizar nodos en este algoritmo descendente, pero el conjunto de claves debe permanecer idéntico. Forma y contenido no son el mismo contrato.

## Caso paso a paso

Inserta 1 a 12 con t=2 y elimina 2, 6, 9 y 1. Después de cada operación verifica ocupación e inorden. Finalmente elimina todas las claves: la raíz termina como una hoja vacía, sin hijos.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de ArbolB](../../src/curso/ArbolB.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        ArbolB b=new ArbolB(2);
        for(int k=1; k<=12; k++)b.insertar(k);
        for(int k:new int[]{
            2,6,9,1
        }){
            b.eliminar(k);
            b.verificar();
            System.out.println(b.orden());
        }
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/11-eliminacion-b
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/11-eliminacion-b
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
[1, 3, 4, 5, 7, 8, 9, 10, 11, 12]
[1, 3, 4, 5, 7, 8, 10, 11, 12]
[3, 4, 5, 7, 8, 10, 11, 12]
```


## Costos y límites

O(t log_t n), O(log n) para t fijo. La eliminación puede prestar o fusionar en varios niveles.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Fusionar sin bajar el separador; mover claves pero no hijos; no reducir una raíz vacía; exigir que borrar un ausente conserve la forma.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Ejecuta la secuencia completa y después elimina 100. ¿Qué debes comparar con un TreeSet?

<details>
<summary>Ver una solución razonada</summary>

Se compara el contenido ordenado y las consultas. Un TreeSet no sirve como oráculo de la forma interna. Además debe ejecutarse verificar para comprobar ocupación y profundidad uniforme.

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

[Anterior](../../unidad1/10-insercion-b/README.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad1/12-indices-b-mas/README.md)
