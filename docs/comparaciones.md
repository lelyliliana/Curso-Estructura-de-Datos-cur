# Comparación de estructuras y algoritmos

[Inicio](../README.md)

## Estructuras de la primera unidad

n es cantidad de entradas. t es grado mínimo del árbol B y M es máximo de claves B+. Los costos suponen claves enteras y valores de tamaño acotado; mostrar registros agrega el tamaño de la salida.

| Estructura | Consulta principal | Modificación | Garantía y límite |
|---|---|---|---|
| AVL | Igualdad O(log n), inorden O(n) | O(log n) | Altura logarítmica; no representa páginas |
| Montículo mínimo | Mínimo O(1), clave arbitraria O(n) | Reparación O(log n) | Inserción amortizada O(log n), ampliación individual O(n) |
| B | O(t log_t n) en este código | O(t log_t n) | t fijo produce O(log n); representación en memoria |
| B+ | O(M log_M n); rango agrega O(k) | O(M log_M n) | M fijo produce O(log n); registros solo en hojas |
| Hash con sondeo | O(1) esperado | O(1) esperado amortizado | Peor caso O(n); distribución y carga importan |
| k-d estático | Vecino/rango pueden visitar O(n) | Reconstrucción | Construcción de este código O(n log² n), métrica cartesiana |

Un árbol B de pocas páginas puede evitar muchas lecturas frente a un BST en disco, pero el ejemplo no realiza E/S de páginas. Comparar milisegundos del laboratorio no demuestra el comportamiento de una base de datos.

## Grafos

V es cantidad de vértices y E cantidad de aristas de entrada. En un grafo no dirigido cada arista tiene hasta dos referencias de recorrido. La biblioteca permite aristas paralelas; por eso se conserva la expresión con log(E+1) cuando corresponde.

| Algoritmo | Problema | Pesos y dirección | Tiempo | Memoria auxiliar |
|---|---|---|---|---|
| Dijkstra con entradas nuevas | Un origen | Pesos no negativos; ambos tipos de dirección | O(V+E log(E+1)) | O(V+E) |
| Kruskal con DSU | Bosque mínimo | No dirigido; admite negativos | O(V+E log(E+1)) | O(V+E) |
| Floyd–Warshall | Todos los pares | Admite negativos; detecta parejas afectadas | O(V³) | O(V²) |

En grafos simples, E≤V² y Dijkstra se presenta habitualmente como O((V+E)log V). Una variante con decrease-key tiene otra organización de cola y memoria. No se trasladan sus detalles a la implementación con entradas duplicadas.

## Aplicaciones

| Necesidad | Estructura o procedimiento | Advertencia concreta |
|---|---|---|
| Resolver código de lugar | TreeMap del proyecto | O(log V); no busca por proximidad |
| Consultar vecino en plano | k-d | No interpreta grados como metros |
| Compartir geometrías | GeoJSON | Formato, no topología automática |
| Media por grupo en flujo | TreeMap y Welford | Memoria O(G), G puede crecer con N |
| Clasificar ejemplo pequeño | Regla o perceptrón | Evaluar fuera del entrenamiento |
| Conversación controlada | Máquina de estados | Comandos explícitos, sin generación libre |

Selecciona según la operación dominante y el contrato. Si necesitas dos capacidades diferentes, como código y proximidad, puedes mantener dos índices coherentes sobre los mismos registros. Eso agrega memoria y obligaciones de actualización.
