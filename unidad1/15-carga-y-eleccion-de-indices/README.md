# Carga, reconstrucción y elección de índices

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../unidad1/14-colisiones-hash/README.md)

## Objetivo

Explicar el costo amortizado y seleccionar una estructura según igualdad, rangos y prioridad.

## Desarrollo conceptual

El factor de carga activo es cantidad de entradas activas dividida entre capacidad. En una tabla con tumbas también interesa la ocupación usada: activas más tumbas. Aunque haya pocos datos activos, muchas tumbas pueden alargar las búsquedas. La implementación reconstruye antes de superar 0.5 de ocupación usada; ese umbral es una política de este ejercicio, no una constante universal para todas las tablas hash.

Si hay muchas tumbas y pocas entradas activas, reconstruye con la misma capacidad. Si faltaría espacio para crecer, duplica la capacidad y vuelve a insertar las entradas activas. No basta con copiar cada celda a su antiguo índice: el módulo depende de la nueva capacidad y las posiciones pueden cambiar.

Una reconstrucción puede hacer lenta una operación individual. Con una distribución favorable y crecimiento geométrico, el costo esperado amortizado de poner es constante. Las garantías de peor caso son diferentes: claves adversas pueden provocar agrupamientos y costos lineales de consulta. La función del curso deja esas colisiones visibles para poder estudiarlas.

Para igualdad exacta suele convenir un mapa hash. Para consultas de rango ordenado conviene un árbol de búsqueda balanceado o un índice B+. Para retirar repetidamente la prioridad mínima conviene un montículo. Ninguna estructura es la más rápida para todas las operaciones. También importan memoria, orden determinista y garantías requeridas.

## Caso paso a paso

Inserta veinte claves y observa capacidad y tamaño. Actualiza una clave existente, elimina otra y comprueba que las restantes sigan accesibles después de redimensionar. Una capacidad mayor por sí sola no demuestra que las posiciones se hayan reconstruido correctamente.

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
        for(int k=0; k<20; k++)h.poner(k,"v"+k);
        h.poner(7,"nuevo");
        h.eliminar(3);
        h.verificar();
        System.out.println("tamaño="+h.tamano()+", capacidad="+h.capacidad());
        System.out.println(h.buscar(7));
        System.out.println(h.buscar(19));
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/15-carga-y-eleccion-de-indices
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/15-carga-y-eleccion-de-indices
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
tamaño=19, capacidad=64
nuevo
v19
```


## Costos y límites

Hash: O(1) esperado amortizado, O(n) peor caso de consulta. AVL: O(log n) garantizado. Rango B+: O(log n+k) con M fijo. Montículo: mínimo O(1), extracción O(log n).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Prometer O(1) garantizado por tener carga baja; copiar posiciones sin rehash; elegir hash para un rango sin reconocer que habría que escanear y ordenar.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Escoge estructuras para: buscar usuario por código, listar códigos entre dos límites y atender el trabajo de menor costo. Justifica cada elección.

<details>
<summary>Ver una solución razonada</summary>

Mapa hash para igualdad si su distribución es adecuada; árbol balanceado/B+ para el rango; cola de prioridad para el mínimo. Si se exige peor caso logarítmico en igualdad, un árbol balanceado es una alternativa razonable.

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

[Anterior](../../unidad1/14-colisiones-hash/README.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad2/01-grafos-ponderados/README.md)
