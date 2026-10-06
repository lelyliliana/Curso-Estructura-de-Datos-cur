# Hashing: colisiones, sondeo y tumbas

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../unidad1/13-rangos-y-eliminacion-b-mas/README.md)

## Objetivo

Conservar una cadena de sondeo después de eliminar y distinguir colisión de clave duplicada.

## Desarrollo conceptual

Una función hash transforma una clave en un valor usado para calcular una posición. Dos claves distintas pueden caer en la misma posición: eso es una colisión y no implica que las claves sean iguales. La estructura debe comparar la clave completa al buscar. En esta implementación, la función para int es su propio valor y floorMod lo convierte en un índice válido incluso para claves negativas.

El sondeo lineal avanza por posiciones consecutivas, con retorno al inicio del arreglo. La búsqueda termina al encontrar la clave, una celda nunca usada o después de revisar la capacidad completa. El agrupamiento de entradas cercanas puede aumentar el trabajo; una distribución favorable no elimina las colisiones.

Una celda eliminada no se marca como nunca usada, sino como tumba. Si se la tratara como vacía, la búsqueda terminaría antes de encontrar claves que se insertaron después de una colisión. Las tumbas permiten continuar el sondeo y pueden reutilizarse al insertar.

La tabla distingue estado libre, activo y tumba. buscar devuelve null para ausencia; los valores null están prohibidos. poner sobre una clave existente actualiza el valor sin aumentar el tamaño. La búsqueda de una clave existente debe realizarse antes de aprovechar una tumba, para evitar insertar un segundo registro de la misma clave más adelante.

### Encadenamiento y otras exploraciones

El [mapa encadenado](../../src/curso/TablaEncadenada.java) almacena una lista por cubeta. Varias claves pueden permanecer en la misma cubeta y la carga n/m puede superar 1. Buscar cuesta O(1+α) esperado con distribución favorable, donde α=n/m; en el peor caso todas las claves caen en la misma lista. Eliminar no necesita tumba porque la consulta recorre la lista completa. El ejemplo usa tres claves impares en dos cubetas y luego elimina una.

En exploración cuadrática se prueban posiciones floorMod(h(k)+c1·j+c2·j²,m), no el cuadrado de la clave como regla universal. Los coeficientes, la capacidad y el límite de carga deben escogerse juntos: una secuencia puede no visitar todas las celdas aunque haya espacio. En doble hashing, el salto h2(k) debe ser no nulo y coprimo con m para recorrer toda la tabla. Estas variantes se comparan conceptualmente; el mapa de sondeo ejecutable usa avance lineal.

Las colisiones se resuelven, no se eliminan por completo. Aumentar capacidad no garantiza que desaparezcan ni convierte por sí solo el peor caso en O(1).

## Caso paso a paso

Con capacidad inicial 8, las claves 1, 9 y 17 comienzan en la misma posición. Elimina 9 y consulta 17: debe seguir encontrándose después de la tumba. Poner 25 puede reutilizar espacio o desencadenar una reconstrucción según la ocupación usada.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de TablaHash](../../src/curso/TablaHash.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        TablaHash h=new TablaHash();
        h.poner(1,"uno");
        h.poner(9,"nueve");
        h.poner(17,"diecisiete");
        h.eliminar(9);
        h.verificar();
        System.out.println(h.buscar(17));
        System.out.println(h.buscar(9));
        System.out.println("tamaño="+h.tamano());
        TablaEncadenada c=new TablaEncadenada(2);
        c.poner(1,"uno");
        c.poner(3,"tres");
        c.poner(5,"cinco");
        c.eliminar(3);
        System.out.println(c.buscar(5)+"; carga="+c.carga());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/14-colisiones-hash
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/14-colisiones-hash
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
diecisiete
null
tamaño=2
cinco; carga=1.0
```


## Costos y límites

Costo esperado O(1) con distribución favorable y carga controlada; peor caso O(n). Reconstrucción O(n) esperada, puede llegar a O(n²) con colisiones adversas.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Marcar una eliminación como celda nunca usada; usar % con negativos sin corregir el índice; considerar hash igual como clave igual.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Elimina 1, actualiza 17 y consulta una clave ausente. ¿Qué contador cambia al actualizar?

<details>
<summary>Ver una solución razonada</summary>

El tamaño disminuye al eliminar 1. Actualizar 17 conserva el tamaño y cambia únicamente su valor. Una consulta ausente devuelve null; las tumbas no terminan prematuramente la búsqueda.

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

[Anterior](../../unidad1/13-rangos-y-eliminacion-b-mas/README.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad1/15-carga-y-eleccion-de-indices/README.md)
