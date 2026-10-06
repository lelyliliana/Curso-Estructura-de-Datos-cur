# Reconstrucción de rutas y vértices inaccesibles

[Inicio](../../README.md) · [Unidad](../../unidad2/README.md) · [Anterior](../../unidad2/03-dijkstra/README.md)

## Objetivo

Reconstruir un camino a partir de predecesores sin inventar rutas hacia vértices inaccesibles.

## Desarrollo conceptual

Guardar solo una distancia no permite explicar por dónde pasa una ruta. En cada mejora estricta, Dijkstra almacena el vértice previo que produjo ese costo. Para reconstruir se empieza en el destino, se siguen predecesores hasta el origen y se invierte la lista resultante.

El arreglo previo se inicializa con -1. El origen conserva -1 y su distancia cero. Un vértice inaccesible conserva INF y no tiene predecesor. Su camino debe ser una lista vacía; no debe devolverse una lista que contenga únicamente el destino, porque parecería una ruta válida.

Cuando hay varias rutas de costo igual, esta implementación conserva la primera que logró una mejora estricta. No reemplazar el predecesor en igualdad también evita formar ciclos de predecesores entre vértices unidos por costo cero. El costo mínimo no depende de cuál de las rutas empatadas se elija, siempre que la reconstrucción sea coherente.

Para comprobar una ruta, se verifica que empieza y termina donde corresponde, que cada paso es una arista real y que la suma coincide con la distancia. En grafos con aristas paralelas, una lista de vértices no identifica por sí sola cuál arista se eligió; este laboratorio puede comprobar el costo con la menor arista entre cada pareja. Un sistema que necesite identificar cada conexión debe guardar también el identificador de la arista.

## Caso paso a paso

Con 0→1 de costo 0 y 1→2 de costo 4, el camino a 2 es [0,1,2]. El vértice 3 queda inaccesible. Consultar camino(0) devuelve [0], mientras camino(3) devuelve [].

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de Grafo](../../src/curso/Grafo.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Grafo g=new Grafo(4,true);
        g.agregar(0,1,0);
        g.agregar(1,2,4);
        Grafo.Rutas r=g.dijkstra(0);
        System.out.println(r.camino(0));
        System.out.println(r.camino(2));
        System.out.println(r.distancia(3)==Grafo.INF?"inaccesible":"alcanzable");
        System.out.println(r.camino(3));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad2/04-predecesores
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad2/04-predecesores
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
[0]
[0, 1, 2]
inaccesible
[]
```


## Costos y límites

Reconstruir una ruta simple cuesta O(L) tiempo y memoria, con L≤V. Consultar la distancia almacenada cuesta O(1).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Reconstruir antes de comprobar INF; mostrar INF como un costo real; reemplazar predecesores en empates sin controlar ciclos.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Agrega 2→1 con costo 0. ¿Debe aparecer un ciclo en la ruta reconstruida? Explica la mejora estricta.

<details>
<summary>Ver una solución razonada</summary>

No debe aparecer un ciclo. Una relajación en igualdad no cambia predecesores. Solo un costo estrictamente menor sustituye la ruta previa.

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

[Anterior](../../unidad2/03-dijkstra/README.md) · [Índice de la unidad](../../unidad2/README.md) · [Siguiente recurso](../../unidad2/05-validacion-de-pesos/README.md)
