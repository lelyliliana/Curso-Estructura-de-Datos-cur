# Rotaciones dobles LR y RL

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../unidad1/03-rotaciones-simples/README.md)

## Objetivo

Descomponer un caso interior en dos rotaciones simples y conservar todos los subárboles.

## Desarrollo conceptual

En LR, el nodo desequilibrado tiene un hijo izquierdo cuya rama más alta está a la derecha. Una rotación simple a la derecha no resuelve correctamente esa geometría. Primero se gira el hijo izquierdo a la izquierda y después se gira la raíz del subárbol a la derecha. En RL se realiza la secuencia simétrica: derecha sobre el hijo derecho e izquierda sobre la raíz.

La decisión se toma con las alturas actuales de los nietos. Si el nodo está cargado a la izquierda y el nieto derecho del hijo izquierdo es más alto que su nieto izquierdo, corresponde LR. Si el nodo está cargado a la derecha y el nieto izquierdo del hijo derecho es más alto, corresponde RL. Comparar alturas permite utilizar la misma reparación después de insertar y eliminar.

Una rotación doble sigue siendo una operación local de costo constante. No ordena de nuevo todas las claves ni construye un árbol desde cero. Debe conservar los cuatro subárboles externos y los enlaces intermedios que pueden existir en un árbol grande. Los ejemplos de tres claves muestran la forma, pero las pruebas con muchas claves comprueban que esos subárboles tampoco se pierdan.

Cada rotación devuelve una raíz nueva. La primera se asigna al hijo y la segunda devuelve la raíz reparada al nivel superior. El recorrido inorden proporciona una comprobación del contenido; el verificador agrega las comprobaciones de alturas y factores.

## Caso paso a paso

Para 30, 10, 20 aparece LR. Primero 20 sube sobre 10, luego sube sobre 30. Para 10, 30, 20 aparece RL. Ambos resultados tienen inorden [10, 20, 30] y altura 2.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de AVL](../../src/curso/AVL.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        for(int[] s:new int[][]{
            {
                30,10,20
            },{
                10,30,20
            }
        }){
            AVL a=new AVL();
            for(int k:s)a.insertar(k);
            a.verificar();
            System.out.println(a.orden()+"; altura="+a.altura());
        }
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/04-rotaciones-dobles
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/04-rotaciones-dobles
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[10, 20, 30]; altura=2
[10, 20, 30]; altura=2
```


## Costos y límites

Dos rotaciones locales: O(1). Una inserción AVL completa: O(log n).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Aplicar una única rotación a un caso interior; usar únicamente la clave recién insertada para decidir una reparación tras eliminar.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Construye los casos 80, 40, 60 y 40, 80, 60. Explica las dos asignaciones de raíces.

<details>
<summary>Ver una solución razonada</summary>

La raíz final es 60. En LR se reasigna primero p.izq y luego el subárbol p; en RL se reasigna p.der y luego p. Cada paso conserva el inorden [40, 60, 80].

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

[Anterior](../../unidad1/03-rotaciones-simples/README.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad1/05-insercion-avl/README.md)
