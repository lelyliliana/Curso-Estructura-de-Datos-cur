# Agregación en flujo y memoria por grupos

[Inicio](../../README.md) · [Unidad](../../unidad3/README.md) · [Anterior](../../unidad3/08-geojson/README.md)

## Objetivo

Procesar registros uno a uno sin almacenar todo el conjunto y declarar qué memoria sí crece.

## Desarrollo conceptual

Leer en flujo significa procesar los registros conforme llegan, conservando únicamente el estado necesario. El ejemplo resume cantidad, media y varianza poblacional por grupo. No guarda cada valor, pero mantiene una entrada por grupo distinto. Si casi todos los registros tienen un grupo diferente, la memoria puede crecer tanto como la cantidad de registros.

La media y la suma de desviaciones se actualizan con el método incremental de Welford. Evita restar dos sumas grandes casi iguales, como ocurre en una fórmula ingenua de varianza. La varianza poblacional divide entre n; una estimación de varianza muestral usaría n-1 y requeriría al menos dos observaciones. No deben mezclarse esos denominadores.

El formato del laboratorio tiene una línea grupo;valor, sin encabezado, comillas ni separadores dentro del grupo. No se anuncia como soporte completo de CSV. Cada grupo es no vacío y tiene hasta 80 caracteres; cada valor es finito y su magnitud no supera un millón. Una línea mal formada se rechaza con un mensaje; no se omite silenciosamente.

La función utiliza el Reader recibido y no lo cierra, porque su propiedad pertenece a quien la llama. El ejemplo usa StringReader; para un archivo se utiliza Files.newBufferedReader con UTF-8 dentro de try-with-resources. La lectura incremental reduce memoria, pero no convierte por sí sola un programa local en un sistema distribuido de Big Data.

## Caso paso a paso

Procesa norte;10, norte;20 y sur;5. norte tiene n=2, media=15 y varianza poblacional=25. sur tiene una observación y varianza poblacional cero.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Flujo](../../src/curso/Flujo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Map<String,Flujo.Estadistica> r=Flujo.resumir(new StringReader("norte;10\nnorte;20\nsur;5\n"));
        for(var e:r.entrySet())System.out.println(e.getKey()+": "+e.getValue()+", varianza="+e.getValue().varianzaPoblacional());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad3/09-procesamiento-en-flujo
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad3/09-procesamiento-en-flujo
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
norte: n=2, media=15.0, varianza=25.0
sur: n=1, media=5.0, varianza=0.0
```


## Costos y límites

Con N registros y G grupos: O(N log G) por usar TreeMap, espacio O(G) más la línea actual y sus cadenas. No almacena los N valores.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Afirmar memoria constante sin contar grupos; confundir varianza poblacional y muestral; ignorar entradas inválidas; cerrar un recurso ajeno.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Agrega norte;30. Calcula cantidad, media y varianza poblacional. ¿Cuánta memoria extra requiere otro valor del mismo grupo?

<details>
<summary>Ver una solución razonada</summary>

n=3, media=20 y varianza poblacional=200/3. El estado del grupo sigue con tres acumuladores; no se agrega un registro almacenado por cada valor.

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

[Anterior](../../unidad3/08-geojson/README.md) · [Índice de la unidad](../../unidad3/README.md) · [Siguiente recurso](../../unidad3/10-escala-y-particiones/README.md)
