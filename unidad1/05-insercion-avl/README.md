# Inserción AVL y retorno recursivo

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../unidad1/04-rotaciones-dobles/README.md)

## Objetivo

Seguir el descenso de búsqueda y el retorno que actualiza alturas y repara el árbol.

## Desarrollo conceptual

La inserción comienza como en un árbol binario de búsqueda. Una clave menor desciende por el hijo izquierdo; una mayor por el derecho. Al encontrar un enlace vacío se crea una hoja de altura 1. Si la clave ya existe, esta implementación conserva el nodo y no agrega un duplicado.

La parte de AVL ocurre durante el retorno de la recursión. Cada llamada recibe la nueva raíz de su hijo, recalcula su propia altura y comprueba el factor. Si encuentra un desequilibrio aplica la rotación simple o doble adecuada. La raíz que devuelve puede ser diferente de la que recibió. La llamada pública debe guardar ese retorno como raíz del árbol completo.

El algoritmo visita únicamente el camino de búsqueda. No necesita recalcular las alturas de ramas que no cambiaron. Cuando una rotación repara el primer desequilibrio causado por una inserción, la altura del subárbol recupera su valor anterior; aun así la implementación puede seguir recalculando al retornar para mantener una rutina uniforme y clara.

Separar la inserción de la función balancear reduce duplicaciones y permite reutilizar la reparación en eliminación. Esta separación es útil solo si cada llamada mantiene el contrato: hijos válidos antes de actualizar el padre, y una raíz reparada como resultado. Las pruebas deben incluir secuencias crecientes, decrecientes, alternadas, repetidas y claves en los extremos del tipo int.

## Caso paso a paso

Inserta 10, 20, 30, 40, 50 y 25. Después de cada inserción ejecuta verificar y observa que el recorrido coincide con las claves únicas ordenadas. La altura final es 3; un BST sin balanceo sobre una secuencia creciente puede alcanzar altura n.

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
            10,20,30,40,50,25
        }){
            a.insertar(k);
            a.verificar();
            System.out.println(k+" -> "+a.orden());
        }System.out.println("altura="+a.altura());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/05-insercion-avl
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/05-insercion-avl
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
10 -> [10]
20 -> [10, 20]
30 -> [10, 20, 30]
40 -> [10, 20, 30, 40]
50 -> [10, 20, 30, 40, 50]
25 -> [10, 20, 25, 30, 40, 50]
altura=3
```


## Costos y límites

O(log n) tiempo por inserción y O(log n) pila recursiva. Cada nodo conserva una altura adicional.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Modificar un hijo sin asignar el retorno; actualizar la altura antes del descenso; introducir duplicados sin definir su ubicación.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Agrega 25 nuevamente y después 5. Compara el contenido con un TreeSet y registra la altura después de cada paso.

<details>
<summary>Ver una solución razonada</summary>

25 repetido no cambia el contenido. Al agregar 5 aparece una clave nueva y se reparan solo sus ancestros. La comparación debe usar claves únicas ordenadas, no el historial completo de entradas.

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

[Anterior](../../unidad1/04-rotaciones-dobles/README.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad1/06-eliminacion-avl/README.md)
