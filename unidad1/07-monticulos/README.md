# Montículos binarios en arreglos

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../unidad1/06-eliminacion-avl/README.md)

## Objetivo

Relacionar la forma de árbol completo con los índices del arreglo y la propiedad de prioridad.

## Desarrollo conceptual

Un montículo mínimo es un árbol binario completo donde cada padre tiene un valor menor o igual que sus hijos. Completo significa que todos los niveles, salvo posiblemente el último, están llenos y que el último se ocupa de izquierda a derecha. Esta forma permite representarlo en un arreglo sin enlaces explícitos.

Con índices desde cero, el padre de i>0 está en (i-1)/2 usando división entera; los hijos están en 2i+1 y 2i+2 si esos índices existen. La raíz ocupa la posición cero y contiene un mínimo. Sin embargo, el arreglo completo no está ordenado y no cumple la propiedad de búsqueda de un BST. Consultar una clave arbitraria puede requerir recorrer todo el montículo.

Para insertar se agrega al final y se asciende mientras el padre sea mayor. Para extraer el mínimo se guarda la raíz, se mueve el último elemento a la posición cero y se desciende intercambiando con el hijo menor. Escoger siempre el hijo izquierdo es incorrecto cuando el derecho tiene menor prioridad.

Los duplicados son válidos. La extracción de un montículo vacío lanza una excepción explícita en esta implementación; no devuelve cero, porque cero puede ser un dato legítimo. El respaldo ArrayList puede aumentar su capacidad, por lo que una inserción individual también puede pagar una copia lineal de su arreglo interno.

### Mínimo, máximo e índices

Un montículo máximo invierte la relación: cada padre es mayor o igual que sus hijos y la raíz contiene un máximo. No obliga a que todos los niveles tengan un orden total. Para extraer de mayor a menor se usa esa prioridad; para obtener orden creciente con heapsort in situ se fija el máximo al final del arreglo.

| Representación | Raíz | Padre | Hijos |
|---|---|---|---|
| Índices desde 0 (este código) | 0 | (i-1)/2, para i>0 | 2i+1 y 2i+2 |
| Índices desde 1 | 1 | i/2, para i>1 | 2i y 2i+1 |

No mezcles fórmulas de ambas convenciones. Una celda cero reservada es una decisión de representación, no un requisito del montículo.

## Caso paso a paso

Agrega 7, 2, 9, 1 y 2. El arreglo representa una forma completa. Las extracciones producen 1, 2, 2, 7 y 9. El verificador compara cada nodo con su padre; no exige que todos los vecinos del arreglo estén ordenados.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Monticulo](../../src/curso/Monticulo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Monticulo m=new Monticulo();
        for(int x:new int[]{
            7,2,9,1,2
        })m.agregar(x);
        m.verificar();
        System.out.println(m.arreglo());
        while(m.tamano()>0)System.out.println(m.extraer());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/07-monticulos
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/07-monticulos
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[1, 2, 9, 7, 2]
1
2
2
7
9
```


## Costos y límites

Mínimo: O(1). Reparación por inserción/extracción: O(log n). Agregar con arreglo dinámico: O(log n) amortizado, O(n) si debe ampliar capacidad. Espacio: O(n).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Confundir montículo con AVL; buscar por menor/mayor como en un BST; elegir el hijo equivocado al descender.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Inserta -4 y 0. Indica las dos primeras extracciones y qué ocurre al extraer después de vaciarlo.

<details>
<summary>Ver una solución razonada</summary>

Las primeras extracciones son -4 y 0. Después de vaciarlo se produce NoSuchElementException. Usar 0 como señal de vacío impediría distinguir un valor almacenado.

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

[Anterior](../../unidad1/06-eliminacion-avl/README.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad1/08-colas-de-prioridad/README.md)
