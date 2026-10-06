# Unidad 1. Profundización de árboles

[Inicio](../README.md)

## Ruta de estudio

AVL y montículos → árboles B y B+ → dispersión y elección de índices. Sigue la secuencia: cada recurso incorpora las estructuras auxiliares que necesita el siguiente. Ejecuta los casos, calcula resultados antes de verlos y comprueba las invariantes correspondientes.

| Recurso | Tema | Resultado de aprendizaje |
|---|---|---|
| 01 | [Contratos, invariantes y costos](01-contratos-e-invariantes/README.md) | Distinguir el comportamiento de un tipo abstracto de datos de su representación y justificar las precondiciones de una operación. |
| 02 | [Alturas y factor de balance AVL](02-alturas-y-factor-avl/README.md) | Calcular alturas con una convención explícita y comprobar el balance en todos los nodos. |
| 03 | [Rotaciones simples LL y RR](03-rotaciones-simples/README.md) | Explicar cómo una rotación conserva el orden de búsqueda y repara el desequilibrio de una rama exterior. |
| 04 | [Rotaciones dobles LR y RL](04-rotaciones-dobles/README.md) | Descomponer un caso interior en dos rotaciones simples y conservar todos los subárboles. |
| 05 | [Inserción AVL y retorno recursivo](05-insercion-avl/README.md) | Seguir el descenso de búsqueda y el retorno que actualiza alturas y repara el árbol. |
| 06 | [Eliminación AVL y reparaciones sucesivas](06-eliminacion-avl/README.md) | Eliminar una clave y explicar por qué el rebalanceo puede propagarse hasta la raíz. |
| 07 | [Montículos binarios en arreglos](07-monticulos/README.md) | Relacionar la forma de árbol completo con los índices del arreglo y la propiedad de prioridad. |
| 08 | [Colas de prioridad y ordenación por extracción](08-colas-de-prioridad/README.md) | Usar una cola de prioridad y distinguir su contrato del orden de iteración y de una política de estabilidad. |
| 09 | [Árboles B: grado mínimo y páginas](09-arboles-b/README.md) | Interpretar las condiciones de ocupación y profundidad sin mezclar convenciones de orden. |
| 10 | [Búsqueda, división e inserción en árbol B](10-insercion-b/README.md) | Seguir una inserción descendente que divide hijos llenos antes de entrar en ellos. |
| 11 | [Eliminación en árbol B: préstamo y fusión](11-eliminacion-b/README.md) | Explicar las reparaciones de ocupación antes del descenso y la contracción de la raíz. |
| 12 | [Árbol B+: separadores y registros en hojas](12-indices-b-mas/README.md) | Distinguir claves separadoras de registros y seguir una división que conserva hojas enlazadas. |
| 13 | [Rangos y eliminación en B+](13-rangos-y-eliminacion-b-mas/README.md) | Usar las hojas enlazadas y reparar ocupación, separadores y enlaces después de eliminar. |
| 14 | [Hashing: colisiones, sondeo y tumbas](14-colisiones-hash/README.md) | Conservar una cadena de sondeo después de eliminar y distinguir colisión de clave duplicada. |
| 15 | [Carga, reconstrucción y elección de índices](15-carga-y-eleccion-de-indices/README.md) | Explicar el costo amortizado y seleccionar una estructura según igualdad, rangos y prioridad. |

## Cierre de la unidad

Resuelve el [taller](../talleres/unidad1.md), contrasta con sus criterios y vuelve a las lecciones donde encuentres dificultades. Utiliza las [referencias](../docs/referencias.md) para profundizar en las convenciones y modelos.
