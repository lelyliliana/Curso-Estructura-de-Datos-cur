# Búsqueda, división e inserción en árbol B

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../unidad1/09-arboles-b/README.md)

## Objetivo

Seguir una inserción descendente que divide hijos llenos antes de entrar en ellos.

## Desarrollo conceptual

La inserción de esta implementación evita descender a un nodo lleno. Si la raíz ya tiene 2t-1 claves, se crea una raíz nueva con un hijo y se divide ese hijo. La clave central sube a la raíz; las t-1 claves menores permanecen en el nodo izquierdo y las t-1 mayores pasan a un nodo derecho nuevo.

En un nodo interno se encuentra el intervalo que corresponde a la clave. Si el hijo de ese intervalo está lleno, se divide antes de continuar. La clave promovida permite decidir si el descenso debe seguir por la mitad izquierda o la derecha. Como el padre no estaba lleno al entrar, tiene espacio para recibir el separador.

Al llegar a una hoja no llena se inserta la clave en su posición ordenada. Si la clave ya existe, el método público termina sin alterar el conjunto. La búsqueda previa hace que la política de duplicados sea sencilla, aunque agrega otro recorrido logarítmico.

Dividir un nodo interno también reparte sus hijos: un nodo con 2t-1 claves tiene 2t hijos, de los cuales t quedan a cada lado. Copiar solo claves y olvidar las referencias produce un árbol que puede imprimir algunos resultados correctos y perder otros. El verificador comprueba cantidad de hijos, intervalos, ocupación y profundidad de hojas.

## Caso paso a paso

Con t=2, las claves 10, 20 y 30 llenan la raíz. Antes de insertar 40 se promueve 20 y quedan hojas [10] y [30]; 40 entra en la hoja derecha. Inserciones posteriores pueden dividir hijos y, finalmente, aumentar otra vez la altura.

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
        for(int k:new int[]{
            10,20,30,40,5,15,25,35
        }){
            b.insertar(k);
            b.verificar();
        }System.out.println(b.niveles());
        System.out.println(b.contiene(25));
        System.out.println(b.contiene(99));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/10-insercion-b
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/10-insercion-b
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[[20, 30], [5, 10, 15, 25, 35, 40]]
true
false
```


## Costos y límites

O(t log_t n) en este código con movimientos en ArrayList; O(log n) para t fijo. Espacio de pila O(log_t n).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Promover una clave y dejarla duplicada en una hoja del árbol B; dividir después de descender sin reservar espacio en el padre.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Inserta 5, 15, 25 y 35 después de la secuencia del ejemplo. Comprueba consultas presentes y ausentes.

<details>
<summary>Ver una solución razonada</summary>

El recorrido debe ser [5, 10, 15, 20, 25, 30, 35, 40]. contiene(25) es verdadero y contiene(99) falso. La forma exacta depende de la secuencia, pero las invariantes no.

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

[Anterior](../../unidad1/09-arboles-b/README.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad1/11-eliminacion-b/README.md)
