# Alturas y factor de balance AVL

[Inicio](../../README.md) · [Unidad](../../unidad1/README.md) · [Anterior](../../unidad1/01-contratos-e-invariantes/README.md)

## Objetivo

Calcular alturas con una convención explícita y comprobar el balance en todos los nodos.

## Desarrollo conceptual

En este curso la altura de un subárbol vacío es 0 y la de una hoja es 1. Con esa convención, altura(nodo) = 1 + máximo(altura(izquierda), altura(derecha)). Otros textos usan -1 para el vacío y 0 para una hoja; ambas convenciones funcionan si se mantienen de forma consistente.

El factor de balance se define como altura(izquierda) menos altura(derecha). Un AVL exige que cada nodo tenga factor -1, 0 o 1, además de cumplir el orden de un árbol binario de búsqueda. No basta con revisar la raíz. Un subárbol interno puede estar desequilibrado aunque la raíz tenga factor cero.

La restricción limita la altura a O(log n). La intuición es que un AVL de altura h necesita como mínimo un nodo raíz, un subárbol de altura h-1 y otro de altura h-2; esa cantidad crece de forma exponencial con h. Por ello las búsquedas recorren un número logarítmico de nodos. El balance no significa que el árbol sea perfecto ni que todos sus niveles estén llenos.

La implementación almacena la altura en cada nodo. Después de modificar un hijo debe recalcularla desde los hijos ya actualizados. Guardar alturas evita recorrer subárboles enteros cada vez que se calcula un factor.

## Caso paso a paso

Para las claves 20, 10, 30, 5 y 15, el subárbol izquierdo de 20 tiene altura 2 y el derecho altura 1: su factor es +1. Las hojas tienen factor 0. El recorrido sigue ordenado aunque las ramas tengan longitudes distintas.

Antes de ejecutar, escribe el resultado que esperas. Identifica los datos de entrada, la operación principal y la propiedad que debe conservarse. Después compara tu predicción con la salida y explica cualquier diferencia mediante una operación concreta.

## Ejemplo ejecutable

El [programa completo](Main.java) utiliza la [implementación compartida de AVL](../../src/curso/AVL.java). Abre ambos archivos: Main presenta el caso y la clase compartida contiene el algoritmo que debes estudiar. Los métodos no requieren servicios externos.

```java
import curso.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        AVL a=new AVL();
        for(int k:new int[]{
            20,10,30,5,15
        })a.insertar(k);
        System.out.println("altura="+a.altura());
        a.verificar();
        System.out.println(a.orden());
    }
}
```

Desde la carpeta raíz del repositorio, en Ubuntu o macOS:

```bash
python3 scripts/ejecutar.py unidad1/02-alturas-y-factor-avl
```

En PowerShell de Windows:

```powershell
py -3 scripts/ejecutar.py unidad1/02-alturas-y-factor-avl
```

El ejecutor compila solo este Main y las clases compartidas en una carpeta temporal. No mezcles los Main de otras lecciones en una sola compilación. También puedes usar [compilación manual](../../docs/ambiente-y-herramientas.md).

### Salida esperada

```text
altura=3
[5, 10, 15, 20, 30]
```


## Costos y límites

Leer una altura almacenada: O(1). Recalcular un nodo: O(1). Comprobar todas las alturas: O(n).

La salida impresa y las copias de listas tienen su propio costo. Cuando evalúes el algoritmo, distingue la operación central de la preparación del caso, el recorrido para mostrar resultados y las comprobaciones de prueba.

## Errores frecuentes

Exigir alturas idénticas en todos los hermanos; mezclar altura en nodos con altura en aristas; balancear solo la raíz.

Ante una discrepancia, reduce la entrada hasta obtener un caso pequeño que la reproduzca. Revisa primero el contrato y después la primera operación que rompe una invariante. Un mensaje de error debe ayudar a reconocer una entrada inválida; no debe transformarla silenciosamente en un resultado válido.

## Ejercicio resuelto

Dibuja el árbol de la secuencia y calcula la altura del vacío, de 10 y de 20. ¿Sería válido agregar 3 sin ninguna reparación?

<details>
<summary>Ver una solución razonada</summary>

Vacío: 0; nodo 10: 2; nodo 20: 3. Al insertar 3 sin reparar, 10 pasa a altura 3 y el factor de 20 queda +2, por lo que el árbol deja de ser AVL. Una rotación derecha sobre 20 repara este caso LL. No se decide el balance únicamente mirando la cantidad total de claves.

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

[Anterior](../../unidad1/01-contratos-e-invariantes/README.md) · [Índice de la unidad](../../unidad1/README.md) · [Siguiente recurso](../../unidad1/03-rotaciones-simples/README.md)
