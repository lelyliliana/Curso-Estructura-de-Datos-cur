# Floyd–Warshall: programación dinámica

[Inicio](../../README.md) · [Unidad](../../unidad2/README.md) · [Anterior](../../unidad2/09-ruta-versus-red/README.md)

## Objetivo

Interpretar el vértice intermedio k y calcular distancias entre todos los pares.

## Desarrollo conceptual

Floyd–Warshall calcula distancias entre todos los pares mediante una matriz. Inicialmente d[i][i]=0, las aristas directas aportan sus pesos y las parejas restantes quedan en INF. Si hay aristas paralelas se conserva el menor peso directo. Un lazo negativo puede reducir la diagonal y debe conservarse para detectar ciclos.

La recurrencia compara el camino conocido de i a j con uno que pasa por k: d[i][k]+d[k][j]. El ciclo externo debe ser k. Después de esa etapa, la matriz contempla caminos cuyos vértices intermedios pertenecen al conjunto ya procesado. Cambiar arbitrariamente el orden de los tres ciclos destruye esa interpretación de programación dinámica.

Solo se suma cuando ambas partes son alcanzables. La matriz siguiente guarda el primer paso de una ruta: si pasar por k mejora i→j, el primer paso será el de i→k. Así se reconstruye avanzando desde el origen, sin guardar una lista completa en cada celda.

El algoritmo admite aristas negativas, pero si hay un ciclo negativo relevante no existe un mínimo finito para las parejas afectadas. La detección se estudia en la siguiente lección. Este laboratorio limita Floyd a 500 vértices para hacer explícito que su tiempo cúbico y memoria cuadrática no son adecuados para cualquier tamaño.

## Caso paso a paso

Con 0→1=4, 1→2=-2, 2→3=3 y 0→3=10, la ruta de 0 a 3 mejora a 5 usando [0,1,2,3]. El grafo es dirigido y no tiene ciclos; el peso negativo es válido para Floyd.

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
        g.agregar(0,1,4);
        g.agregar(1,2,-2);
        g.agregar(2,3,3);
        g.agregar(0,3,10);
        Grafo.Floyd f=g.floyd();
        System.out.println(f.distancia(0,3));
        System.out.println(f.camino(0,3));
        System.out.println(f.camino(3,0));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad2/10-floyd-warshall
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad2/10-floyd-warshall
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
5
[0, 1, 2, 3]
[]
```


## Costos y límites

Tiempo O(V³), espacio O(V²). Reconstruir un camino no afectado por ciclos negativos cuesta O(L).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Poner k en un ciclo interior; sumar INF; sobrescribir la diagonal con un lazo positivo; olvidar minimizar aristas paralelas.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Agrega 0→2=1. Calcula la distancia de 0 a 3 y explica qué etapa puede descubrir la mejora.

<details>
<summary>Ver una solución razonada</summary>

La distancia pasa a 4 por [0,2,3]. La etapa k=2 permite usar 2 como intermedio para mejorar 0→3.

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

[Anterior](../../unidad2/09-ruta-versus-red/README.md) · [Índice de la unidad](../../unidad2/README.md) · [Siguiente recurso](../../unidad2/11-ciclos-negativos/README.md)
