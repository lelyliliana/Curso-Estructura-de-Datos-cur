# Eliminación AVL y reparaciones sucesivas

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../unidad1/05-insercion-avl/README.md)

## Objetivo

Eliminar una clave y explicar por qué el rebalanceo puede propagarse hasta la raíz.

## Desarrollo conceptual

Eliminar en un AVL comienza con los casos de un BST. Una hoja se sustituye por vacío. Un nodo con un único hijo se sustituye por ese hijo. Cuando tiene dos hijos, se copia la clave de su sucesor inorden (el mínimo del subárbol derecho) y después se elimina ese sucesor en la rama derecha. También podría usarse el predecesor, si se implementa de forma coherente.

Al retornar, la altura puede disminuir. Esa disminución puede desequilibrar varios ancestros, incluso después de haber reparado un subárbol inferior. Por ello la eliminación debe continuar actualizando y balanceando hasta la raíz. La regla de detenerse después de una reparación no debe trasladarse indiscriminadamente desde la inserción.

El hijo más alto del nodo desequilibrado puede tener factor cero. En ese caso corresponde una rotación simple. La implementación decide con las alturas de los nietos y utiliza desigualdades estrictas únicamente para detectar los casos dobles. Así incluye los casos de igualdad necesarios al borrar.

Eliminar una clave ausente no modifica el conjunto. El algoritmo tampoco debe dejar referencias a nodos que ya no forman parte del árbol. Aunque Java gestiona la memoria, el programa sigue siendo responsable de mantener enlaces y alturas correctos. Una prueba útil elimina todas las claves de un árbol grande en distintos órdenes y comprueba el estado vacío final.

## Caso paso a paso

Construye el árbol con 20, 10, 30, 5, 15, 25 y 35. Eliminar 20 usa su sucesor 25. Después elimina 5 y 35. En cada paso el inorden pierde exactamente una clave y el verificador comprueba todos los ancestros.

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
            20,10,30,5,15,25,35
        })a.insertar(k);
        for(int k:new int[]{
            20,5,35
        }){
            a.eliminar(k);
            a.verificar();
            System.out.println(a.orden());
        }
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/06-eliminacion-avl
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/06-eliminacion-avl
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[5, 10, 15, 25, 30, 35]
[10, 15, 25, 30, 35]
[10, 15, 25, 30]
```


## Costos y límites

O(log n) tiempo y O(log n) espacio de pila. Pueden ocurrir reparaciones en varios niveles.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Borrar el sucesor de la rama equivocada; omitir las alturas al retornar; ignorar al hijo de factor cero.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Elimina las claves restantes y luego vuelve a eliminar 20. Comprueba altura, recorrido y contiene.

<details>
<summary>Ver una solución razonada</summary>

Al terminar, altura=0, recorrido=[] y contiene devuelve falso para cualquier clave. La eliminación adicional conserva ese estado. No debe producir excepción por buscar un nodo inexistente.

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

[Anterior](../../unidad1/05-insercion-avl/README.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad1/07-monticulos/README.md)
