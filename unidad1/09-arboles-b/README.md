# Árboles B: grado mínimo y páginas

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../unidad1/08-colas-de-prioridad/README.md)

## Objetivo

Interpretar las condiciones de ocupación y profundidad sin mezclar convenciones de orden.

## Desarrollo conceptual

Un árbol B almacena varias claves ordenadas en un nodo y divide el espacio de búsqueda entre sus hijos. Con s claves, un nodo interno tiene s+1 hijos. El primer hijo contiene claves menores que la primera; los hijos intermedios contienen claves entre dos separadores y el último contiene claves mayores que la última.

Usaremos el grado mínimo t, con t≥2. Un nodo no raíz tiene entre t-1 y 2t-1 claves. Un nodo interno no raíz tiene entre t y 2t hijos. La raíz puede tener menos claves; si no es hoja debe tener al menos una clave y dos hijos. Todas las hojas están a la misma profundidad. Esta definición evita ambigüedades: algunos textos llaman orden al máximo de hijos y otros utilizan otra convención.

El alto número de hijos reduce los niveles que se deben visitar. Por eso los árboles B son apropiados para índices organizados en páginas: una lectura puede traer varias claves y referencias. El código del curso vive completamente en memoria y no implementa un gestor de páginas, caché, concurrencia ni recuperación después de una caída. Estudiar su estructura no equivale a construir una base de datos completa.

En un árbol B los registros o claves de datos pueden aparecer también en nodos internos. Esa característica lo diferencia de la variante B+ que reserva los registros para las hojas. La búsqueda dentro de cada nodo puede ser secuencial o binaria; el costo depende de esa decisión y del tamaño de página.

### Repaso: árbol general y primer hijo/siguiente hermano

Un árbol general admite varios hijos por nodo sin imponer orden de búsqueda. Puede representarse con dos enlaces: primero apunta al primer hijo y siguiente apunta al hermano inmediato. Los otros hijos se encuentran siguiendo hermanos. Ese enlace derecho representa un hermano, no un valor mayor, y por eso no se aplican las reglas de un BST a esa representación.

Un árbol B usa múltiples hijos para separar intervalos de claves y exige ocupación y profundidad uniforme. Que ambos tengan varios hijos no los convierte en la misma estructura. Los recorridos de un árbol general deben seguir la relación padre–hijos original, no interpretar la cadena de hermanos como descendencia binaria.

## Caso paso a paso

Con t=2, el máximo es 3 claves por nodo y un nodo no raíz requiere al menos 1. Inserta del 1 al 9 y observa los niveles. Una promoción divide intervalos de búsqueda, no duplica la clave promovida.

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
        for(int k=1; k<=9; k++)b.insertar(k);
        b.verificar();
        System.out.println(b.niveles());
        System.out.println(b.orden());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/09-arboles-b
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/09-arboles-b
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[[4], [2, 6], [1, 3, 5, 7, 8, 9]]
[1, 2, 3, 4, 5, 6, 7, 8, 9]
```


## Costos y límites

Altura O(log_t n). Este código usa búsqueda e inserción lineal dentro del nodo: O(t log_t n) por operación, más O(t) si hay movimientos en cada nivel. Con t fijo: O(log n).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Usar orden sin definirlo; permitir hojas a profundidades distintas; afirmar que este código ya persiste páginas en disco.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Para t=3, determina mínimo y máximo de claves e hijos de un nodo interno no raíz. ¿Qué excepción tiene la raíz?

<details>
<summary>Ver una solución razonada</summary>

Claves: 2 a 5. Hijos: 3 a 6. La raíz interna puede tener una sola clave y dos hijos; la raíz hoja puede estar vacía.

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

[Anterior](../../unidad1/08-colas-de-prioridad/README.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad1/10-insercion-b/README.md)
