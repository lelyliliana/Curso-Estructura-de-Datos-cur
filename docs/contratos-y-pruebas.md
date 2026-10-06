# Contratos, invariantes y pruebas

[Inicio](../README.md) · [Pruebas ejecutables](../tests/README.md)

## Del resultado a la propiedad

Una salida esperada comprueba un caso concreto. Una invariante comprueba la validez de la representación. Un oráculo independiente calcula el resultado mediante otra estrategia. Combinar las tres formas aumenta la evidencia sin convertir una prueba finita en demostración universal.

| Estructura | Contrato público | Invariante comprobada | Oráculo |
|---|---|---|---|
| AVL | Conjunto de int únicos | Orden, altura guardada y factores | TreeSet |
| Montículo | Valores repetibles, mínimo | Padre ≤ hijos | PriorityQueue |
| B | Conjunto de int únicos | Intervalos, ocupación, hijos y hojas al mismo nivel | TreeSet |
| B+ | Mapa int→texto no null | Ocupación, separadores, mínimos y cadena de hojas | TreeMap y subMap |
| Hash | Mapa int→texto no null | Contadores, unicidad y localización | HashMap |
| DSU | Partición de elementos | Equivalencia y cantidad de componentes | Etiquetas por conjunto |
| Dijkstra/Floyd | Distancias y rutas válidas | Extremos, pasos, costo y ausencia | Bellman–Ford en grafos pequeños |
| Kruskal | Bosque de costo mínimo | V-C aristas y componentes | Enumeración de subconjuntos |
| k-d | Punto cercano y rango cartesiano | Respuesta y desempate | Recorrido lineal |

## Diseño de casos

Empieza con vacío y un elemento. Agrega casos que recorran cada decisión: LL, RR, LR y RL en AVL; préstamo izquierdo, derecho y fusión en B/B+; tumba antes de una clave en hashing; mejora de distancia que deje una entrada vieja en Dijkstra. Las secuencias aleatorias con semilla fija complementan estos casos y permiten repetir un fallo.

Las pruebas aleatorias del curso comparan el contenido después de cada operación. Eso localiza la primera operación que rompe el contrato. Al eliminar todo se revisa la contracción de raíces y el retorno al estado vacío. Las claves mínimas y máximas de int ayudan a detectar límites de búsqueda mal definidos.

Para rutas con empates, verifica costo y aristas reales en vez de exigir una única lista. Para un MST con empates, compara el costo mínimo y que no haya ciclos. Una prueba que exige una forma específica puede confundir una solución distinta con una incorrecta.

## Entradas inválidas

Una ausencia legítima no es lo mismo que un error. buscar en un mapa devuelve null cuando falta una clave; extraer de un montículo vacío lanza una excepción. Dijkstra rechaza negativos y Floyd rechaza consultas afectadas por ciclos negativos. Un archivo mal formado no produce una red parcial.

Las funciones documentan qué señal utilizan. No captures cualquier excepción para responder sin ruta: una ruta inexistente es una respuesta válida, mientras una coordenada o código mal formado requiere corrección.

## Interpretar costos

Si una prueba llama orden y verificar después de cada inserción, su tiempo incluye recorridos O(n). Eso no cambia el costo O(log n) de insertar en AVL. Una salida de k registros debe gastar al menos O(k) para construirla. Un ArrayList puede copiar datos al ampliar capacidad; por eso se distingue costo individual de costo amortizado.

El tamaño de valores de texto también importa. Comparar códigos puede costar según su longitud; el proyecto la limita a veinte caracteres. Las complejidades del modelo habitual tratan esas claves acotadas como costo constante. Si eliminas el límite, incorpora el tamaño del texto en el análisis.

## Al modificar

Escribe primero el contrato del cambio. Agrega un caso que falle con la implementación anterior si el comportamiento es nuevo. Conserva los casos anteriores que todavía correspondan. Ejecuta el verificador completo y explica cualquier cambio intencional de salida. No actualices esperado.txt para ocultar un defecto: contrasta el resultado con el razonamiento y el oráculo.

[Volver al curso](../README.md)
