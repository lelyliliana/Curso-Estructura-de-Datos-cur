# Chatbot de consulta con máquina de estados

[Inicio](../../README.md) · [Unidad](../../unidad3/README.md) · [Anterior](../../unidad3/10-escala-y-particiones/README.md)

## Objetivo

Controlar una conversación con estados explícitos y delegar la ruta a un algoritmo comprobable.

## Desarrollo conceptual

Un chatbot puede implementarse con reglas, recuperación de información o modelos generativos. El ejemplo utiliza una máquina de estados determinista: INICIO, ORIGEN y DESTINO. No llama a servicios externos, no genera texto libre y no utiliza un modelo de lenguaje.

En INICIO acepta el comando ruta y solicita un origen. En ORIGEN valida el código y solicita destino. En DESTINO valida el código, consulta la función de ruta y vuelve al inicio. cancelar funciona desde cualquier estado. Un código desconocido conserva el estado para permitir corregir la entrada.

La normalización aplica NFKC, elimina espacios exteriores, reduce repeticiones de espacios y convierte a minúsculas con Locale.ROOT. El límite de 80 caracteres evita entradas arbitrariamente largas en este laboratorio. La normalización no comprende el significado de una frase; por ejemplo, quiero ir a b no es el comando ruta ni un código.

La conversación se separa del grafo mediante una función origen,destino→respuesta. Esto permite probar los estados con una función sencilla y después usar Red.ruta. La estructura de control no convierte al bot en inteligencia general. Una interfaz útil debe explicar sus comandos, permitir cancelar y mostrar sin ruta cuando corresponda, en lugar de inventar una conexión.

## Caso paso a paso

Envía ruta, a y d a la red de ejemplo. El bot solicita los dos códigos y devuelve [a,b,c,d] con costo 9. Después vuelve a INICIO. Si recibe un código inexistente, conserva la solicitud pendiente.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Bot](../../src/curso/Bot.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Bot b=Red.ejemplo().bot();
        for(String m:new String[]{
            "RUTA","a","d","cancelar"
        })System.out.println(b.responder(m));
        System.out.println(b.estado());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad3/11-chatbot-por-estados
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad3/11-chatbot-por-estados
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
Escribe el código de origen
Escribe el código de destino
[a, b, c, d]; costo=9
Consulta cancelada
INICIO
```


## Costos y límites

Normalizar O(L) con L≤80. Consultar código: O(1) esperado en el conjunto. Resolver ruta añade el costo de Dijkstra.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Mezclar estados en variables sin contrato; cambiar al siguiente estado con código inválido; anunciar comprensión de lenguaje natural que no existe.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Prueba ruta, código inválido, a, cancelar. Indica el estado después de cada entrada.

<details>
<summary>Ver una solución razonada</summary>

ORIGEN, ORIGEN, DESTINO e INICIO. cancelar elimina el origen pendiente; una nueva consulta debe pedirlo otra vez.

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

[Anterior](../../unidad3/10-escala-y-particiones/README.md) · [Índice de la unidad](../../unidad3/README.md) · [Siguiente recurso](../../unidad3/12-proyecto-integrador/README.md)
