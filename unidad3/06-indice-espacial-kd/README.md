# Índice espacial k-d y vecino cercano

[Inicio](../../README.md) · [Unidad](../../unidad3/README.md) · [Anterior](../../unidad3/05-mineria-y-evaluacion/README.md)

## Objetivo

Relacionar particiones espaciales con poda y comprobar el vecino cercano mediante búsqueda exhaustiva.

## Desarrollo conceptual

Un árbol k-d organiza puntos de varias dimensiones alternando el eje de partición. El índice estático del curso trabaja en dos dimensiones: primero x, luego y y así sucesivamente. Ordena por el eje, escoge una mediana y construye las dos mitades. Esa construcción mantiene una altura logarítmica, aunque una consulta todavía puede visitar muchos nodos.

Para buscar el vecino más cercano se visita primero la mitad donde cae la consulta. Después se compara la distancia al plano separador con la mejor distancia encontrada. Si el plano está suficientemente cerca, la otra mitad aún puede contener una respuesta mejor y debe explorarse. La comparación usa distancias al cuadrado para evitar raíces cuadradas innecesarias.

Cuando dos puntos están a la misma distancia, se usa el identificador lexicográficamente menor. La igualdad en la prueba de poda es importante para respetar ese desempate. Los identificadores deben ser únicos. El árbol no ofrece inserción dinámica: si cambian los puntos, se construye un índice nuevo.

Las coordenadas son cartesianas y la distancia es euclidiana. Aplicar esta fórmula directamente a longitud y latitud no produce una distancia física uniforme sobre la Tierra. Un SIG real debe escoger una proyección adecuada o una fórmula geodésica. La prueba exhaustiva es un oráculo simple: calcula todas las distancias y elige con el mismo criterio.

## Caso paso a paso

Con a=(0,0), b=(4,0) y c=(0,4), la consulta q=(3,0) tiene vecino b. El rectángulo cerrado [0,0]–[1,4] incluye a y c. El índice vacío devuelve null para vecino cercano.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de KD](../../src/curso/KD.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        KD k=new KD(List.of(new KD.Punto("a",0,0),new KD.Punto("b",4,0),new KD.Punto("c",0,4)));
        System.out.println(k.cercano(new KD.Punto("q",3,0)).id());
        System.out.println(k.rectangulo(0,0,1,4));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad3/06-indice-espacial-kd
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad3/06-indice-espacial-kd
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
b
[a, c]
```


## Costos y límites

La construcción que reordena cada nivel cuesta O(n log² n); altura O(log n). Consulta: favorablemente poda muchas ramas, pero peor caso O(n). Espacio O(n), copias temporales durante construcción.

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Prometer O(log n) para toda consulta; podar con desigualdad estricta y perder empates; medir grados como metros.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Consulta q=(2,0). ¿Quién gana el empate entre a y b? Comprueba por búsqueda lineal.

<details>
<summary>Ver una solución razonada</summary>

Ambos tienen distancia²=4 y gana a por identificador. La búsqueda k-d debe coincidir con ese resultado; no se acepta un desempate distinto sin cambiar el contrato.

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

[Anterior](../../unidad3/05-mineria-y-evaluacion/README.md) · [Índice de la unidad](../../unidad3/README.md) · [Siguiente recurso](../../unidad3/07-sistemas-geograficos/README.md)
