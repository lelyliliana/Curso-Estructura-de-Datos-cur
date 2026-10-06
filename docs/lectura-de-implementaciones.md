# Lectura de las implementaciones

[Inicio](../README.md) · [Contratos y pruebas](contratos-y-pruebas.md)

Las clases de src/curso contienen los algoritmos compartidos. Estudia una operación por vez y relaciona cada asignación con una invariante. Este recorrido resume el orden de las decisiones; consulta los fuentes para los detalles ejecutables.

## AVL

insertar y eliminar reciben una raíz de subárbol y devuelven su nueva raíz. Un retorno diferente indica que se creó una hoja, se retiró un nodo o se realizó una rotación. La llamada superior debe asignar ese retorno.

```text
insertar(n,k):
    si n es vacío: devolver una hoja con k
    si k es menor: n.izq = insertar(n.izq,k)
    si k es mayor: n.der = insertar(n.der,k)
    si k es igual: devolver n
    devolver balancear(n)
```

balancear recalcula la altura y compara ramas. En una rotación derecha, primero p.izq recibe el antiguo q.der y después q.der recibe p. El orden evita perder el subárbol intermedio. actualizar(p) antecede a actualizar(q).

Después de borrar, verifica todos los ancestros. El sucesor usado con dos hijos se elimina de la rama derecha. Un hijo de factor cero puede requerir rotación simple: no lo excluyas por usar una regla incompleta de inserción.

## Montículo y heapsort

Monticulo agrega al final y asciende mientras el padre sea mayor. extraer guarda el mínimo antes de mover el último valor y desciende escogiendo el menor hijo. Es una estructura con crecimiento dinámico.

OrdenMonticulo, en cambio, usa un montículo máximo sobre el mismo arreglo que ordena. Primero aplica descensos desde el último padre hasta la raíz. Después intercambia el máximo con el final de la región activa y reduce esa región.

```text
construir un montículo máximo de abajo hacia arriba
para fin desde n-1 hasta 1:
    intercambiar a[0] y a[fin]
    reparar solo la región [0,fin)
```

La región [fin,n) ya queda ordenada y no participa en la reparación. Construir cuesta O(n), porque la mayoría de nodos están cerca de las hojas. La fase de extracción cuesta O(n log n). El algoritmo es in situ, usa O(1) memoria auxiliar y no garantiza estabilidad.

## Árbol B

dividir recibe un padre y el índice de un hijo lleno. Retira el separador central del hijo, mueve las claves mayores al nuevo hermano y reparte hijos si era interno. El padre recibe una clave y una referencia.

insertarNoLleno mantiene la precondición de que el nodo actual tiene espacio. Antes de bajar divide un hijo lleno y vuelve a elegir intervalo. eliminar prepara al hijo con al menos t claves, mediante préstamo o fusión, antes del descenso.

fusionar baja el separador del padre y reúne ambos nodos. Los hijos se concatenan en el mismo orden que sus intervalos. Al terminar, una raíz sin claves pero con hijo único se contrae. Los nodos no raíz conservan ocupación y hojas a profundidad uniforme.

## Árbol B+

La forma de representar nodos depende de hoja: una hoja tiene claves y valores; un interno tiene hijos y separadores. minimo almacena el menor registro descendiente. actualizar reconstruye separadores como mínimos de hijos desde el segundo.

poner puede devolver un hermano nuevo. Quien llamó agrega ese hermano junto al hijo original; si la raíz se divide, se crea otra raíz con dos hijos. En una división de hoja se conserva el registro separador y se ajusta siguiente.

eliminar repara ocupación de hijos, actualiza sus mínimos y después actualiza el padre. Al fusionar hojas, el enlace siguiente salta la hoja retirada. rango baja una sola vez y después recorre la cadena. verificar compara las hojas del árbol con esa cadena, para detectar enlaces sobrantes o perdidos.

## Dos estrategias hash

TablaHash usa tres estados. localizar continúa sobre tumbas y termina ante una celda nunca usada. poner busca una clave existente antes de reutilizar una posición. reconstruir crea arreglos nuevos y vuelve a insertar solo entradas activas.

TablaEncadenada utiliza una lista por cubeta. No necesita tumbas: borrar una entrada de la lista no interrumpe la búsqueda de las demás. La implementación usa ArrayList para las cubetas; una lista ligada es otra representación posible con el mismo contrato. Su capacidad fija sirve para estudiar el efecto de la carga; no ofrece redimensionamiento.

## Dijkstra

```text
d[origen]=0; las otras distancias son INF
agregar (origen,0) a la prioridad
mientras la prioridad no esté vacía:
    retirar (u,costo)
    si costo != d[u]: descartar la entrada vieja
    para cada u→v:
        si d[u]+peso < d[v]:
            actualizar distancia y predecesor
            agregar una entrada nueva
```

La precondición global son pesos no negativos. Las entradas repetidas no se modifican dentro de la cola; se descartan al salir. Camino reconstruye desde destino hasta origen y luego invierte.

## Kruskal y DSU

Kruskal ordena aristas originales, sin duplicar cada sentido. DSU confirma si los extremos tienen representantes diferentes. unir enlaza por tamaño, comprime caminos y reduce componentes solo cuando fusiona dos conjuntos distintos.

El resultado incluye costo y componentes. V-C aristas describen un bosque; C=1 con V>0 identifica conexión global. Los lazos no se aceptan y los empates pueden ofrecer varias selecciones óptimas.

## Floyd–Warshall

La matriz se inicializa con diagonal cero e INF fuera de conexiones. El ciclo k es externo. Si mejora i→k→j, siguiente[i][j] recibe siguiente[i][k]. Las sumas solo ocurren con ambos tramos alcanzables.

Después se recorren diagonales negativas. Una pareja se marca afectada si puede entrar a ese ciclo y salir hacia su destino. No se reconstruyen sus rutas ni se reporta una distancia finita. Las sumas saturadas evitan desborde, pero la saturación no sustituye esta clasificación.

## Aplicaciones

Modelos valida formas y finitud antes de operar. capa aplica una fila por salida; Perceptron actualiza por errores y debe evaluarse nuevamente al terminar. KD construye con medianas y poda según distancia al plano, incluyendo igualdad por el desempate.

GeoJSON separa escape de texto, validación de posiciones y construcción de features. Flujo conserva acumuladores por grupo y deja al llamador la propiedad del Reader. Bot mantiene estado y consulta una función externa. Red integra sin mezclar costo, ubicación y código, y valida el candidato completo al cargar.

[Volver a las unidades](../README.md)
