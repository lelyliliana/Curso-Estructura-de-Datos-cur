# Proyecto integrador: red de lugares

[Inicio](../README.md) · [Lección integradora](../unidad3/12-proyecto-integrador/README.md)

## Problema

Representa una red ficticia de lugares, consulta rutas de menor costo, calcula un bosque de conexiones mínimas y exporta una representación geográfica. Agrega una conversación de comandos controlados para pedir una ruta. Mantén separados códigos, coordenadas, conectividad y costos.

## Ejecutar

Todos los comandos parten de la raíz del repositorio. En Windows sustituye python3 por py -3.

Demostración:

```bash
python3 scripts/ejecutar.py unidad3/12-proyecto-integrador
```

Menú con red de ejemplo:

```bash
python3 scripts/ejecutar.py unidad3/12-proyecto-integrador --menu
```

Menú con archivos propios (las rutas con espacios requieren comillas):

```bash
python3 scripts/ejecutar.py unidad3/12-proyecto-integrador --menu proyecto/datos/lugares.txt proyecto/datos/conexiones.txt
```

## Formato y validación

Los archivos son UTF-8, sin encabezados, líneas vacías ni campos entrecomillados. El separador es punto y coma y el decimal usa punto. No son archivos CSV de propósito general.

| Archivo | Campos | Ejemplo |
|---|---|---|
| lugares.txt | codigo;longitud;latitud | a;-75.6;6.2 |
| conexiones.txt | origen;destino;costo | a;b;3 |

Los códigos únicos contienen a-z, 0-9, guion o guion bajo y tienen entre 1 y 20 caracteres. La red admite hasta 500 lugares. Las coordenadas son finitas y están dentro de los rangos geográficos. Los extremos deben existir. No se admiten lazos ni duplicados no dirigidos; a;b y b;a designan la misma conexión. Los costos van de 0 a 10⁹ y se acumulan en long.

La carga valida un candidato antes de devolverlo. Un archivo inválido produce una excepción; no entrega registros parcialmente aceptados. La aplicación inicia con la carga elegida: una carga fallida impide iniciar el menú y permite corregir el archivo.

## Uso del menú

1. Ruta: introduce origen y destino exactos en minúsculas. La respuesta contiene códigos y costo, o Sin ruta.
2. Bosque: muestra costo, componentes y aristas con vértices numéricos. La posición de cada lugar en lugares.txt corresponde a ese número.
3. Exportar: escribe una ruta de salida; se genera FeatureCollection con puntos y segmentos. Puede reemplazar un archivo existente. La carpeta destino debe existir.
4. Bot: acepta ruta, códigos y cancelar. salir regresa al menú. Un código desconocido mantiene el estado de solicitud.
0. Salir: termina el programa.

## Casos de aceptación

| Caso | Resultado esperado |
|---|---|
| a→d en los datos incluidos | [a, b, c, d]; costo=9 |
| a→a | [a]; costo=0 |
| Bosque de los datos incluidos | Costo 9, tres aristas, una componente |
| Lugar sin conexiones | Sin ruta desde otros lugares; otra componente en el bosque |
| Conexión de peso cero | Se conserva como arista real |
| Código o conexión duplicada | Carga rechazada |
| Latitud 95 o NaN | Carga rechazada |
| Exportación | JSON válido, longitud antes de latitud, extremos existentes |

## Desarrollo por etapas

Primero diseña seis lugares y justifica costos en una unidad ficticia coherente. Después dibuja la topología y calcula cinco rutas a mano. Carga los archivos y contrasta los resultados. Calcula también el bosque y explica por qué su costo es un objetivo diferente. Exporta el mapa y comprueba las coordenadas con un parser JSON. Finalmente prueba la conversación, incluidos cancelar y un código inválido.

Entrega archivos de entrada, programa modificado si corresponde, tabla de resultados, análisis de costos y pruebas. Conserva una red válida y otra inválida para demostrar el rechazo. No se evalúa la apariencia del mapa como sustituto de la corrección del grafo.

## Criterios (100 puntos)

| Criterio | Puntos |
|---|---|
| Modelo e invariantes de carga | 25 |
| Rutas y bosque con evidencia | 25 |
| Exportación válida y separación de geometría/topología | 15 |
| Conversación y manejo de entradas | 10 |
| Casos límite, oráculos y análisis de costos | 25 |

## Extensiones

Agrega dirección por conexión o identificación de aristas y revisa los algoritmos compatibles. Si incorporas un índice espacial, declara proyección y métrica. Si agregas guardar/cargar un formato propio, define escritura segura y tratamiento de fallos antes de afirmar que es persistencia transaccional.

La implementación base es local, está en memoria y exporta mediante escritura directa. No incluye tráfico real, servicios de mapas, geocodificación, múltiples usuarios ni recuperación transaccional.

[Volver a la unidad](../unidad3/README.md)
