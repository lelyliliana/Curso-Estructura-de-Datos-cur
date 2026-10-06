# Estructuras de Datos

Curso de profundización con teoría, casos paso a paso, implementaciones, ejercicios resueltos, talleres y un proyecto integrador. Los ejemplos utilizan Java 21, sin dependencias externas, y pueden ejecutarse en Windows, Ubuntu y macOS.

## Antes de comenzar

Este curso supone manejo de arreglos, listas, pilas, colas, recursión, árboles binarios de búsqueda, grafos y análisis básico de complejidad. Revisa el [diagnóstico y los prerrequisitos](docs/prerrequisitos.md). Para reforzar esos fundamentos puedes consultar [Algoritmos 1](https://github.com/lelyliliana/Curso-Algoritmos-1) y [Algoritmos 2](https://github.com/lelyliliana/Curso-Algoritmos-2).

1. Prepara el [ambiente y las herramientas](docs/ambiente-y-herramientas.md).
2. Recorre las unidades en orden y realiza cada ejercicio.
3. Completa los talleres y ejecuta las [comprobaciones](tests/README.md).
4. Integra lo aprendido en el [proyecto](proyecto/README.md).

## Unidades

| Unidad | Contenido | Recursos |
|---|---|---|
| [1. Profundización de árboles](unidad1/README.md) | AVL, montículos, prioridad, B, B+, hashing e índices | 15 |
| [2. Profundización de grafos](unidad2/README.md) | Dijkstra, DSU, Kruskal, bosques y Floyd–Warshall | 12 |
| [3. Aplicaciones](unidad3/README.md) | Álgebra lineal, redes pequeñas, minería, índices espaciales, SIG, GeoJSON, flujos y conversación por estados | 12 |

## Cómo trabajar una lección

Lee el objetivo y el desarrollo conceptual. Calcula a mano el caso antes de ejecutar. Examina tanto Main.java como la implementación que importa desde src/curso. Compara la salida con la esperada y modifica un dato por vez. Justifica el resultado con la invariante y la precondición, además de su costo.

Cada recurso contiene navegación anterior, índice y siguiente. Las soluciones desplegables permiten contrastar el razonamiento después de intentar el ejercicio. Los ejemplos comparten implementaciones para que una misma estructura conserve un único contrato durante el curso.

## Alcance

Se estudian estructuras avanzadas y sus aplicaciones sobre datos pequeños y reproducibles. Los árboles B y B+ están en memoria; las redes neuronales son demostraciones limitadas; el procesamiento en flujo es local; el bot usa reglas explícitas. El proyecto geográfico es ficticio. Cada lección declara qué implementa y qué restricciones tiene.

Otros temas de especialización, como árboles rojo-negro, tries, índices R-tree, concurrencia y almacenamiento transaccional, pueden estudiarse después. El curso no presupone que todas las estructuras compartan las mismas garantías ni que un ejemplo local represente un sistema de producción completo.

## Material complementario

- [Taller de árboles e índices](talleres/unidad1.md)
- [Taller de grafos ponderados](talleres/unidad2.md)
- [Taller de aplicaciones](talleres/unidad3.md)
- [Lectura de las implementaciones](docs/lectura-de-implementaciones.md)
- [Guía de contratos y pruebas](docs/contratos-y-pruebas.md)
- [Comparación de estructuras y algoritmos](docs/comparaciones.md)
- [Glosario](docs/glosario.md)
- [Referencias](docs/referencias.md)

[Comenzar la primera lección](unidad1/01-contratos-e-invariantes/README.md)
