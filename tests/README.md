# Pruebas del curso

[Inicio](../README.md) · [Contratos y pruebas](../docs/contratos-y-pruebas.md)

Desde la raíz ejecuta `python3 scripts/verificar.py` en Ubuntu/macOS o `py -3 scripts/verificar.py` en Windows. Se requiere JDK 21 y Python 3.12 o superior, sin paquetes adicionales.

## Qué se comprueba

| Grupo | Evidencia |
|---|---|
| AVL | 12000 operaciones frente a TreeSet, balance, alturas y extremos |
| Montículo | 8000 operaciones frente a PriorityQueue y extracción vacía |
| B | 24000 operaciones, cuatro grados y eliminación completa |
| B+ | 30000 operaciones, seis capacidades, rangos y cadena de hojas |
| Heapsort | 1000 arreglos frente a Arrays.sort |
| Hash encadenado | 3000 operaciones y carga mayor que uno |
| Hash | 8000 operaciones con claves que fuerzan colisiones, tumbas y reconstrucción |
| DSU | 1000 uniones frente a una partición por etiquetas |
| Dijkstra/Floyd | 250 grafos no negativos frente a Bellman–Ford; costo de cada ruta |
| Floyd negativo | 200 grafos acíclicos, ciclos localizados y saturación numérica |
| Kruskal | Los 64 grafos simples de cuatro vértices, costo óptimo por enumeración |
| Límites de grafos | Costos mayores que int, vértices y pesos inválidos |
| Modelos | Dimensiones, sigmoide, XOR fijo y OR entrenado |
| k-d | 400 vecinos y 100 rangos frente a búsqueda lineal |
| Flujo | Media, varianza, vacío y registros inválidos |
| Bot | Estados, normalización, errores y cancelación |
| GeoJSON y Red | Dominio, carga completa, archivos UTF-8 y copias de vistas |

Además se contrastan los 39 ejemplos con esperado.txt, se analiza JSON con un parser independiente, se recorre el menú del proyecto y se revisan enlaces locales. Las semillas aleatorias están fijadas para reproducir los casos.

## Cómo interpretar el resultado

Un fallo muestra una propiedad o salida que no coincide. Reproduce la primera operación problemática antes de cambiar varias funciones. Pruebas correctas ofrecen evidencia sobre este dominio, no una garantía universal frente a cualquier modificación o uso fuera de las precondiciones.

Las pruebas se compilan con las mismas clases que importan las lecciones. El verificador no modifica salidas esperadas ni instala dependencias. Las comprobaciones del repositorio ejecutan este proceso en Windows, Ubuntu y macOS con Java 21.
