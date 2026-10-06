# Colas de prioridad y ordenación por extracción

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../unidad1/07-monticulos/README.md)

## Objetivo

Usar una cola de prioridad y distinguir su contrato del orden de iteración y de una política de estabilidad.

## Desarrollo conceptual

Una cola de prioridad permite consultar o retirar el elemento con mayor prioridad según un comparador. No garantiza el orden FIFO de una cola ordinaria. Un montículo es una representación habitual, pero el tipo abstracto no depende de esa representación. La prioridad puede ser un costo, una fecha o una combinación de campos.

PriorityQueue de Java retira el mínimo según su comparador. Recorrerla con un iterador o imprimirla no equivale a retirar sus elementos: el orden interno no está totalmente ordenado. Para obtener una secuencia por prioridad hay que hacer extracciones sucesivas o copiar los datos y ordenarlos.

Si varios elementos tienen la misma prioridad, la cola no promete estabilidad. Para un orden determinista se agrega un criterio secundario, por ejemplo el identificador. Si se requiere respetar la llegada entre empates, se almacena un contador de secuencia y se compara después de la prioridad. No se deben restar prioridades para comparar enteros, porque la resta puede desbordarse.

Insertar n datos y extraerlos todos permite ordenar en O(n log n), utilizando O(n) memoria adicional. Este ejemplo no es el heapsort in situ clásico: ese algoritmo construye un montículo en el mismo arreglo y fija el máximo al final de la región activa. Construir un montículo de abajo hacia arriba tiene costo O(n), a diferencia de n inserciones individuales.

## Caso paso a paso

Las tareas tienen prioridades 3, 1 y 1. El comparador primero usa prioridad y luego nombre, por lo que archivo sale antes de consulta y respaldo. Ese criterio de desempate se declara explícitamente.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Monticulo](../../src/curso/Monticulo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        record Tarea(String nombre,int prioridad){
        }
        PriorityQueue<Tarea> q=new PriorityQueue<>(Comparator.comparingInt(Tarea::prioridad).thenComparing(Tarea::nombre));
        q.add(new Tarea("respaldo",3));
        q.add(new Tarea("consulta",1));
        q.add(new Tarea("archivo",1));
        while(!q.isEmpty())System.out.println(q.remove());
        int[] datos={
            7,-1,3,3,0
        };
        OrdenMonticulo.ordenar(datos);
        System.out.println(Arrays.toString(datos));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/08-colas-de-prioridad
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/08-colas-de-prioridad
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
Tarea[nombre=archivo, prioridad=1]
Tarea[nombre=consulta, prioridad=1]
Tarea[nombre=respaldo, prioridad=3]
[-1, 0, 3, 3, 7]
```


## Heapsort en el mismo arreglo

La segunda parte del ejemplo utiliza [OrdenMonticulo](../../src/curso/OrdenMonticulo.java). Construye un montículo máximo de abajo hacia arriba en O(n). Después fija el máximo en el extremo derecho y repara la región restante. Ordena en O(n log n), usa O(1) espacio auxiliar y no es estable. La [lectura de implementaciones](../../docs/lectura-de-implementaciones.md) explica la región activa [0,fin).

## Costos y límites

ofrecer/retirar: O(log n) amortizado con respaldo dinámico; consultar cabeza: O(1). Ordenar por n extracciones: O(n log n), espacio O(n).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Esperar orden en el iterador; asumir estabilidad; comparar con a.prioridad-b.prioridad.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Cambia el segundo criterio por un contador de llegada. ¿En qué caso cambia el resultado?

<details>
<summary>Ver una solución razonada</summary>

Cambia cuando existen prioridades iguales: el contador conserva la llegada y el nombre usa orden lexicográfico. Ambas políticas son válidas si el contrato las declara.

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

[Anterior](../../unidad1/07-monticulos/README.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad1/09-arboles-b/README.md)
