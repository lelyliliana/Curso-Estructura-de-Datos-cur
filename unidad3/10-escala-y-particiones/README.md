# Escala, particiones y límites del laboratorio

[Inicio](../../README.md) · [Unidad](../../unidad3/README.md) · [Anterior](../../unidad3/09-procesamiento-en-flujo/README.md)

## Objetivo

Explicar qué cambia al pasar de datos pequeños a volúmenes que superan recursos locales.

## Desarrollo conceptual

Big Data no designa únicamente datos no estructurados. Los datos pueden ser tabulares, semiestructurados como JSON o no estructurados como documentos e imágenes. El problema de escala aparece cuando volumen, velocidad u otras características exigen procedimientos y recursos que el enfoque disponible no maneja adecuadamente.

Particionar divide el trabajo o almacenamiento entre partes. Un hash de la clave puede enviar cada grupo a una partición para que sus valores se agreguen juntos. Una partición desigual provoca un cuello de botella aunque el número total de máquinas parezca suficiente. El esquema del ejemplo usa claves enteras y floorMod para mostrar una asignación válida con negativos.

Combinar medias parciales exige ponderarlas por sus cantidades: (n1·m1+n2·m2)/(n1+n2). Promediar las dos medias sin esos pesos es incorrecto cuando las particiones tienen tamaños diferentes. Para combinar varianzas se necesitan también cantidades y sumas de desviaciones, con una fórmula específica.

Distribuir datos introduce cuestiones adicionales: orden de llegada, duplicados, fallos, persistencia, comunicación y consistencia. Este repositorio no implementa un clúster ni promete tolerancia a fallos. Se estudian decisiones de representación y agregación con ejemplos locales reproducibles. Un análisis de memoria debe incluir índices, texto de salida y cantidad de grupos, además del arreglo más visible.

## Caso paso a paso

Asigna -1, 0, 1, 2 y 3 a tres particiones. floorMod produce índices de 0 a 2. Combina un grupo de dos valores con media 10 y otro de ocho con media 20: la media global es 18, no 15.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Flujo](../../src/curso/Flujo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        for(int k:new int[]{
            -1,0,1,2,3
        })System.out.println(k+" -> "+Math.floorMod(k,3));
        long n1=2,n2=8;
        double m1=10,m2=20;
        System.out.println("media combinada="+(n1*m1+n2*m2)/(n1+n2));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad3/10-escala-y-particiones
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad3/10-escala-y-particiones
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
-1 -> 2
0 -> 0
1 -> 1
2 -> 2
3 -> 0
media combinada=18.0
```


## Costos y límites

Asignación escalar O(1); almacenar acumuladores O(G). El costo de un sistema distribuido incluye comunicación y recuperación, ausentes en este ejercicio.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Definir Big Data por formato; usar % y obtener particiones negativas; promediar medias sin cantidades; llamar distribuido a un único proceso.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Combina n1=1,m1=100 con n2=9,m2=0. Explica por qué la media de las medias falla.

<details>
<summary>Ver una solución razonada</summary>

La media global es 10. La media no ponderada sería 50 y daría el mismo peso a una muestra que a nueve.

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

[Anterior](../../unidad3/09-procesamiento-en-flujo/README.md) · [Índice de la unidad](../../unidad3/README.md) · [Siguiente recurso](../../unidad3/11-chatbot-por-estados/README.md)
