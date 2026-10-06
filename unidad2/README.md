# Unidad 2. Profundización de grafos

[Inicio](../README.md)

## Ruta de estudio

Representación y Dijkstra → Kruskal y conjuntos disjuntos → Floyd–Warshall y comparación. Sigue la secuencia: cada recurso incorpora las estructuras auxiliares que necesita el siguiente. Ejecuta los casos, calcula resultados antes de verlos y comprueba las invariantes correspondientes.

| Recurso | Tema | Resultado de aprendizaje |
|---|---|---|
| 01 | [Grafos ponderados: representación y contrato](01-grafos-ponderados/README.md) | Modelar dirección, peso y ausencia de conexión sin utilizar cero como señal de ausencia. |
| 02 | [La frontera de Dijkstra y entradas desactualizadas](02-frontera-con-prioridad/README.md) | Usar una cola de prioridad para procesar el menor costo provisional y descartar versiones antiguas. |
| 03 | [Dijkstra: relajación y caminos mínimos](03-dijkstra/README.md) | Ejecutar Dijkstra con pesos no negativos y justificar por qué puede finalizar una distancia. |
| 04 | [Reconstrucción de rutas y vértices inaccesibles](04-predecesores/README.md) | Reconstruir un camino a partir de predecesores sin inventar rutas hacia vértices inaccesibles. |
| 05 | [Pesos negativos, límites y pruebas de rutas](05-validacion-de-pesos/README.md) | Definir precondiciones numéricas y rechazar entradas incompatibles con Dijkstra. |
| 06 | [Conjuntos disjuntos: unión y compresión](06-conjuntos-disjuntos/README.md) | Mantener componentes de conectividad sin recorrer todo el grafo en cada consulta. |
| 07 | [Kruskal y árbol de expansión mínimo](07-kruskal/README.md) | Seleccionar conexiones que minimicen el costo total sin introducir ciclos. |
| 08 | [Grafos desconectados y bosques mínimos](08-bosques-y-empates/README.md) | Interpretar la salida de Kruskal cuando no existe un árbol que conecte todos los vértices. |
| 09 | [Ruta mínima frente a red mínima](09-ruta-versus-red/README.md) | Elegir el algoritmo según la función objetivo y demostrar que un MST puede alargar una ruta. |
| 10 | [Floyd–Warshall: programación dinámica](10-floyd-warshall/README.md) | Interpretar el vértice intermedio k y calcular distancias entre todos los pares. |
| 11 | [Ciclos negativos y parejas afectadas](11-ciclos-negativos/README.md) | Distinguir un camino inexistente de uno sin mínimo finito y detectar las parejas afectadas. |
| 12 | [Selección y comprobación de algoritmos de grafos](12-seleccion-y-comprobacion/README.md) | Comparar resultados con un oráculo apropiado y decidir según dirección, pesos y cantidad de consultas. |

## Cierre de la unidad

Resuelve el [taller](../talleres/unidad2.md), contrasta con sus criterios y vuelve a las lecciones donde encuentres dificultades. Utiliza las [referencias](../docs/referencias.md) para profundizar en las convenciones y modelos.
