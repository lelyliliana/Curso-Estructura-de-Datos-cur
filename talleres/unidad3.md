# Taller 3. Aplicaciones de estructuras

[Inicio](../README.md) · [Unidad 3](../unidad3/README.md)

## Propósito

Explica qué estructura responde a cada consulta y qué evidencia permite sostener un resultado. Los datos son pequeños y ficticios; declara el alcance de cada demostración.

## Actividades

1. Calcula una capa con entrada [2,3], filas de pesos [1,2] y [-1,1], sesgos [0,-2] y ReLU. Explica dimensiones y resultado.
2. Compara sigmoide para z=0,1000,-1000. Separa la propiedad matemática del redondeo double. Agrega una prueba que rechace NaN.
3. Traza las cuatro entradas del ejemplo XOR de dos capas. Indica qué parámetros se fijaron a mano y por qué no demuestra entrenamiento.
4. Entrena un perceptrón sobre OR y evalúa después de terminar. Repite sobre XOR y explica su limitación sin atribuir el fallo al sistema operativo.
5. Agrega ejemplos de prueba a la regla de corte. Calcula exactitud y una matriz de confusión. Explica por qué no debes escoger el corte usando esas pruebas.
6. Genera veinte puntos cartesianos e indexa con k-d. Compara diez vecinos y cinco rectángulos con búsqueda lineal. Incluye un empate y el índice vacío.
7. Diseña una red geográfica con un cruce visual sin conexión. Explica cómo el grafo conserva esa diferencia y qué algoritmo resuelve una ruta.
8. Exporta GeoJSON con un identificador que contenga comillas. Analízalo con un parser y comprueba longitud antes de latitud. Rechaza coordenadas fuera del dominio.
9. Resume por grupo un archivo de cien líneas sin conservar todos los valores. Compara media y varianza con un cálculo directo. Indica qué sucede si las cien líneas tienen cien grupos distintos.
10. Amplía la conversación para ofrecer ayuda en cualquier estado. Conserva cancelar y valida códigos. Prueba al menos dos recorridos exitosos y dos entradas inválidas.

## Resultados orientativos

<details>
<summary>Comprobaciones razonadas</summary>

La primera capa produce [8,0]: la segunda suma es -2+3-2=-1 y ReLU la convierte a cero. La sigmoide matemática está en (0,1) para reales finitos, pero double puede redondear extremos a 0 y 1.

El ejemplo XOR clasifica 0,1,1,0 con pesos fijos. OR puede ser aprendido por el perceptrón simple; XOR no es linealmente separable. Evalúa las predicciones finales, sin confundirlas con errores registrados durante la última época.

El vecino k-d debe coincidir en distancia y desempate con el recorrido lineal. GeoJSON conserva caracteres mediante escape JSON y no crea topología automática. Un cruce puede representarse sin un vértice compartido si no hay intercambio.

Con cien grupos se almacenan cien acumuladores: la memoria depende de G, no es constante. Para agregar ayuda al bot, responde sin avanzar el estado actual; después el usuario debe poder continuar la solicitud pendiente.

</details>

## Criterios (100 puntos)

| Evidencia | Puntos |
|---|---|
| Dimensiones, activación y alcance de aprendizaje | 25 |
| Evaluación y separación de datos | 15 |
| Índice espacial y validación geográfica | 25 |
| Flujo y análisis de memoria | 15 |
| Conversación, casos límite y pruebas | 20 |

Después del taller desarrolla el [proyecto integrador](../proyecto/README.md). Entrega resultados que puedas reproducir y evita conclusiones predictivas que los datos utilizados no permiten sostener.
