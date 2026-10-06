# Rangos y eliminación en B+

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../unidad1/12-indices-b-mas/README.md)

## Objetivo

Usar las hojas enlazadas y reparar ocupación, separadores y enlaces después de eliminar.

## Desarrollo conceptual

Una consulta de rango primero busca la hoja donde podría estar la clave inferior. Desde esa posición recorre registros y sigue el enlace a la hoja siguiente, deteniéndose cuando supera la clave superior. Los extremos son inclusivos. Si el inferior es mayor que el superior, se devuelve un resultado vacío según el contrato del curso.

Eliminar un registro puede dejar una hoja por debajo de su ocupación mínima. Primero se intenta redistribuir con un hermano que tenga entradas de sobra. Si no es posible, se fusionan dos hojas y se ajusta siguiente para que el recorrido no visite una hoja retirada. En nodos internos se redistribuyen o fusionan referencias a hijos; los separadores se recalculan usando los mínimos de esos hijos.

Cuando cambia la primera clave de una hoja, puede cambiar un separador de un ancestro aunque no haya ocurrido una fusión. Por ello hay que actualizar cada nodo en el retorno. Mantener separadores viejos puede causar búsquedas fallidas mientras el recorrido completo todavía parece correcto.

La raíz interna que conserva un único hijo se sustituye por ese hijo. El verificador comprueba ocupación, profundidad, mínimos, separadores, orden global y que la cadena de hojas coincide exactamente con las hojas del árbol. Una copia ordenada del resultado del rango evita entregar referencias que permitan modificar el índice desde fuera.

## Caso paso a paso

Inserta del 1 al 10, pide [3,7] y elimina 3, 4, 5 y 6. El nuevo rango [3,7] contiene solo 7. Las reparaciones deben conservar los registros fuera del intervalo y la cadena de hojas.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de ArbolBMas](../../src/curso/ArbolBMas.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        ArbolBMas b=new ArbolBMas(3);
        for(int k=1; k<=10; k++)b.poner(k,"v"+k);
        System.out.println(b.rango(3,7));
        for(int k:new int[]{
            3,4,5,6
        }){
            b.eliminar(k);
            b.verificar();
        }System.out.println(b.rango(3,7));
        System.out.println(b.rango(1,10));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/13-rangos-y-eliminacion-b-mas
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/13-rangos-y-eliminacion-b-mas
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
{3=v3, 4=v4, 5=v5, 6=v6, 7=v7}
{7=v7}
{1=v1, 2=v2, 7=v7, 8=v8, 9=v9, 10=v10}
```


## Costos y límites

Rango: O(M log_M n + k), donde k es la cantidad devuelta. Eliminación: O(M log_M n); espacio del resultado O(k).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Conservar un siguiente que apunta a una hoja fusionada; no actualizar separadores; confundir rango cerrado con exclusivo.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Elimina 7 y comprueba los rangos [3,7], [1,10] y [9,2]. Después vacía el índice.

<details>
<summary>Ver una solución razonada</summary>

[3,7] queda vacío; [1,10] conserva 1,2,8,9,10; [9,2] es vacío por contrato. Después de eliminar todo, buscar devuelve null y cualquier rango está vacío.

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

[Anterior](../../unidad1/12-indices-b-mas/README.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad1/14-colisiones-hash/README.md)
