# Prerrequisitos y diagnóstico

[Inicio](../README.md)

Este curso profundiza estructuras y algoritmos. Antes de comenzar debes poder manipular arreglos y referencias, implementar listas simples, pilas y colas, recorrer un BST, representar un grafo y explicar un costo lineal frente a uno logarítmico. Necesitas funciones, clases, recursión, comparadores y manejo básico de excepciones.

No necesitas conocimientos previos de aprendizaje automático ni cartografía. La tercera unidad introduce lo necesario para sus ejemplos limitados. Sí requiere seguir sumas, productos, dimensiones y condiciones booleanas.

## Diagnóstico

Intenta responder antes de abrir las soluciones.

1. Un arreglo contiene [2,4,6,8]. ¿Qué precondición requiere búsqueda binaria?
2. ¿Cuánto cuesta buscar un valor ausente en una lista simple sin índice?
3. ¿Qué elemento retira una pila y cuál una cola FIFO?
4. Inserta 1,2,3,4 en un BST sin balanceo. ¿Cuál es su altura con hoja=1?
5. ¿Qué conserva el recorrido inorden de un BST?
6. ¿Cuál es el caso base al recorrer un árbol recursivamente?
7. ¿Cómo se representa una conexión no dirigida en una lista de adyacencia?
8. ¿Qué problema resuelve BFS en un grafo sin ponderación?
9. ¿Por qué es peligroso comparar dos enteros restándolos?
10. ¿Por qué una copia inmutable de una lista protege un contrato?

<details>
<summary>Soluciones y rutas de repaso</summary>

1. La secuencia debe estar ordenada según el criterio utilizado. Cambiar el orden sin cambiar el algoritmo rompe la precondición.
2. O(n): puede revisar todos los nodos antes de confirmar ausencia.
3. La pila retira el último agregado; la cola FIFO retira el primero que sigue pendiente.
4. Altura 4: una cadena a la derecha. No todo BST tiene altura logarítmica.
5. Las claves en orden creciente según el comparador; duplicados dependen del contrato elegido.
6. Subárbol vacío: no procesa nada y termina esa llamada.
7. Una referencia de recorrido en cada sentido, con cuidado de no contar dos veces una única arista al enumerar conexiones.
8. Menor número de aristas desde un origen a los vértices alcanzables. No minimiza pesos arbitrarios.
9. La resta puede desbordarse. Usa Integer.compare, Long.compare o comparadores apropiados.
10. Impide que quien recibe la vista cambie la representación sin pasar por operaciones que preservan invariantes.

Repasa [Algoritmos 1](https://github.com/lelyliliana/Curso-Algoritmos-1) para búsquedas, control y análisis, y [Algoritmos 2](https://github.com/lelyliliana/Curso-Algoritmos-2) para listas, recursión, BST y grafos. Si fallaste las preguntas 4–6, realiza el repaso de árboles antes de AVL. Si fallaste 7–8, repasa representación y BFS antes de Dijkstra.

</details>

## Evidencia de preparación

Escribe un programa que elimine duplicados de una secuencia, muestre los valores ordenados y consulte una clave. Explica qué estructura usaste, cómo maneja duplicados y cuánto cuesta cada operación. Después dibuja un grafo de cinco vértices con uno aislado y realiza BFS desde un origen.

Si puedes justificar las respuestas y reproducir esas dos tareas, continúa con [contratos e invariantes](../unidad1/01-contratos-e-invariantes/README.md).
