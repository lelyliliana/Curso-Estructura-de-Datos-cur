# Taller 1. Árboles e índices

[Inicio](../README.md) · [Unidad 1](../unidad1/README.md)

## Propósito y entrega

Demuestra que puedes escoger y mantener una estructura por sus operaciones y garantías. Entrega trazas a mano, fuentes de los cambios, casos de prueba y una tabla de costos. Ejecuta primero los ejemplos base y después trabaja sobre una copia de los archivos que modificarás.

## Actividades

1. **Convención de altura.** Dibuja un árbol con raíz 40, hijos 20 y 60 y nietos 10 y 30 bajo 20. Calcula alturas y factores en todos los nodos. Explica por qué el balance de la raíz no basta como prueba.
2. **Cuatro reparaciones AVL.** Construye 30,20,10; 10,20,30; 30,10,20; 10,30,20. Para cada secuencia muestra el enlace intermedio que cambia, el tipo de caso y la raíz final.
3. **Eliminación AVL.** Inserta 1 a 20 y elimina primero pares y después impares. Comprueba después de cada operación contenido e invariantes. No compares una forma exacta con TreeSet.
4. **Montículo.** Inserta 9,3,8,1,5,1. Registra el arreglo después de cada inserción y cada extracción. Muestra por qué el arreglo no es una lista ordenada.
5. **Prioridad con empates.** Modela tareas con prioridad y número de llegada. Construye un comparador que preserve llegada entre prioridades iguales. Evita restar enteros para comparar.
6. **Árbol B.** Con t=2 inserta 10,20,30,40,50,60,70. Dibuja los nodos después de cada división y señala cuál clave se promovió. Comprueba cantidad de hijos y profundidad de hojas.
7. **Eliminación B.** Usa el árbol de la actividad anterior y elimina todas las claves en orden inverso. Localiza una fusión y la contracción de la raíz. Después elimina una clave ausente.
8. **B+ y rangos.** Con M=3 inserta 1 a 15 con valores de texto. Solicita [4,10], actualiza 7 y elimina 4,5,6. Comprueba separadores y cadena de hojas. El valor actualizado debe aparecer una sola vez.
9. **Hash y tumbas.** Inserta 1,9,17; elimina 9; busca 17; agrega claves que fuerzan reconstrucción. Explica por qué una tumba no debe detener la consulta.
10. **Selección.** Diseña un catálogo que consulte igualdad, rangos y tareas prioritarias. Decide si conviene más de un índice. Declara qué actualizaciones deben mantenerlos coherentes.

## Orientación para contrastar

<details>
<summary>Resultados y razonamiento de control</summary>

En la actividad 1, alturas de hojas=1, de 20=2, de 60=1 y de 40=3; factor de 40=+1. Los cuatro ejemplos AVL de la actividad 2 terminan con raíz 20 y altura 2, mediante LL, RR, LR y RL respectivamente.

Al eliminar todo, AVL y B deben quedar vacíos; no deben conservar una raíz interna sin claves. Las extracciones del montículo son 1,1,3,5,8,9. El comparador de tareas usa prioridad primero y llegada después.

El rango inicial B+ contiene 4,5,6,7,8,9,10. Después de eliminar 4,5,6 contiene 7,8,9,10, con el valor actualizado de 7. Cada separador dirige al mínimo del hijo derecho; no es otro registro.

La búsqueda de 17 atraviesa la tumba de 9. Una reconstrucción recalcula posiciones y elimina tumbas; no copia celdas sin considerar la capacidad nueva. Para el catálogo pueden coexistir mapa hash y árbol ordenado si la aplicación exige ambas consultas, con memoria y mantenimiento adicionales.

</details>

## Criterios (100 puntos)

| Evidencia | Puntos |
|---|---|
| Trazas y convenciones explícitas | 25 |
| Implementación y conservación de invariantes | 30 |
| Casos límite y comparación con oráculos | 25 |
| Selección de estructuras y análisis de costos | 20 |

Una captura sin entradas ni razonamiento no demuestra la propiedad. Incluye un caso vacío, una clave ausente, duplicados y una secuencia que haga crecer y reducir el árbol.

[Siguiente recurso: unidad 2](../unidad2/README.md)
