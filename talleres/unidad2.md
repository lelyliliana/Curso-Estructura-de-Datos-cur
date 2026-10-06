# Taller 2. Grafos ponderados

[Inicio](../README.md) · [Unidad 2](../unidad2/README.md)

## Datos comunes

Trabaja con seis vértices a,b,c,d,e,f. Las conexiones no dirigidas son a–b=4, a–c=1, c–b=2, b–d=1, c–d=5 y d–e=3. f está aislado. Los costos representan una unidad ficticia uniforme.

## Actividades

1. Construye lista de adyacencia y matriz. Usa INF para ausencia y cero en la diagonal; explica por qué una conexión de costo cero sería válida.
2. Realiza Dijkstra desde a. Registra cada extracción vigente, las distancias y predecesores tras relajar. Señala una entrada de cola que queda desactualizada.
3. Reconstruye a→e, a→a y a→f. Comprueba cada paso y la suma de costos.
4. Cambia c–b a costo cero. Recalcula a→e y comprueba que no aparecen ciclos de predecesores.
5. Ejecuta DSU con las conexiones en el orden dado. Indica cuáles uniones son redundantes y cuántas componentes quedan.
6. Aplica Kruskal al grafo original. Ordena conexiones, identifica descartes por ciclo y calcula el bosque mínimo.
7. Conecta e–f=2. Determina el nuevo costo mínimo y comprueba V-1 aristas. Luego agrega otra conexión de igual costo y explica posibles empates.
8. Construye el grafo dirigido 0→1=4, 1→2=-2, 2→3=3, 0→3=10. Realiza las etapas k de Floyd y reconstruye 0→3. No uses Dijkstra sobre esta entrada.
9. Agrega 2→1=1 al grafo dirigido. Identifica el ciclo negativo y clasifica 0→3 y 3→0. Añade un vértice aislado y explica su consulta consigo mismo.
10. Diseña una tabla que escoja Dijkstra, Kruskal o Floyd según objetivo, dirección, pesos, tamaño y número de consultas. Agrega pruebas con un oráculo independiente.

## Resultados para contrastar

<details>
<summary>Costos y propiedades esperadas</summary>

En el grafo original, las distancias desde a son a=0,b=3,c=1,d=4,e=7,f=INF. La ruta a→e es [a,c,b,d,e], costo 7. La consulta a→a contiene solo a y a→f es vacía. Con c–b=0, a→e cuesta 5.

El bosque original utiliza a–c=1, b–d=1, c–b=2 y d–e=3. Su costo es 7 y tiene dos componentes, de modo que V-C=4 aristas. Con e–f=2 el árbol cuesta 9 y tiene cinco aristas.

El grafo dirigido sin el arco adicional tiene 0→3 de costo 5 por [0,1,2,3]. Al agregar 2→1=1 aparece el ciclo 1→2→1 de costo -1. 0→3 queda afectada y no tiene mínimo finito; 3→0 es inaccesible. Un vértice aislado tiene distancia cero a sí mismo.

Una lista exacta no es un criterio suficiente ante empates. Comprueba costo, validez de pasos y propiedades de árbol/bosque. Compara rutas no negativas con Bellman–Ford y árboles pequeños por enumeración.

</details>

## Entrega y evaluación

Entrega representación, trazas, fuentes, casos y tabla de decisión. Usa long para costos y documenta precondiciones.

| Criterio | Puntos |
|---|---|
| Representación y distancias | 25 |
| Reconstrucción y entradas viejas | 20 |
| DSU y bosque mínimo | 20 |
| Floyd y ciclos negativos | 20 |
| Pruebas, costos y elección del algoritmo | 15 |

[Siguiente recurso: unidad 3](../unidad3/README.md)
