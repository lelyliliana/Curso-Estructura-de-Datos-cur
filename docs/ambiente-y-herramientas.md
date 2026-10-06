# Ambiente y herramientas

[Inicio](../README.md) · [Primera lección](../unidad1/01-contratos-e-invariantes/README.md)

## Herramientas comunes

Instala un **JDK 21** completo y Python 3.12 o superior. Java ejecuta los algoritmos; Python facilita la compilación de cada ejemplo y las comprobaciones. Los ejemplos también pueden compilarse manualmente sin Python. No necesitan cuentas, servicios de pago, GPU, Docker ni WSL.

Elige la arquitectura que corresponda al equipo. Puedes usar [Eclipse Temurin 21](https://adoptium.net/temurin/releases/?version=21) y sus [instrucciones de instalación](https://adoptium.net/installation/). No copies JAVA_HOME de otro computador: esa variable apunta a la carpeta del JDK instalado en el tuyo.

| Sistema | Terminal | JDK | Comando Python habitual |
|---|---|---|---|
| Windows | PowerShell | Instalador Temurin 21 para x64 o ARM64 | py -3 |
| Ubuntu | Terminal | openjdk-21-jdk si está disponible, o Temurin 21 | python3 |
| macOS | Terminal | Temurin 21 aarch64 para Apple Silicon o x64 para Intel | python3 |

## Windows

1. Descarga e instala el JDK 21 para tu arquitectura. Habilita agregar Java a PATH y configurar JAVA_HOME cuando el instalador ofrezca esas opciones.
2. Cierra y vuelve a abrir PowerShell. Ejecuta `java -version` y `javac -version`: ambos deben indicar 21.
3. Instala Python desde [python.org](https://www.python.org/downloads/). Activa el lanzador de Python si se ofrece y revisa las opciones de PATH.
4. Abre una terminal nueva y ejecuta `py -3 --version`. Si el lanzador no está disponible y `python --version` indica una versión adecuada, usa python en los comandos del curso.
5. Descarga o clona el repositorio. Entra en su raíz mediante `cd "ruta de Curso-Estructura-de-Datos-cur"`.

Prueba:

```powershell
py -3 scripts/ejecutar.py unidad1/01-contratos-e-invariantes
```

Si la consola muestra tildes alteradas, comprueba UTF-8 del editor y prueba `chcp 65001` antes de ejecutar. Los ejecutores indican codificación UTF-8 para los procesos Java.

## Ubuntu

1. Comprueba las herramientas existentes con `java -version`, `javac -version` y `python3 --version`.
2. Si falta Java y tu versión ofrece el paquete, actualiza el índice:

```bash
sudo apt update
```

Instala el JDK:

```bash
sudo apt install openjdk-21-jdk
```

Si ese paquete no está disponible, sigue la instalación oficial de Temurin para Linux. Si tienes varios JDK, revisa `sudo update-alternatives --config java` y `sudo update-alternatives --config javac`; selecciona la misma versión principal.

3. Si falta Python, usa el paquete oficial de tu distribución cuando satisfaga la versión requerida o consulta [la documentación de Python para Unix](https://docs.python.org/3/using/unix.html). No reemplaces el Python del sistema ni alteres sus enlaces para este curso.
4. Entra en la raíz del repositorio y prueba:

```bash
python3 scripts/ejecutar.py unidad1/01-contratos-e-invariantes
```

## macOS

1. Identifica Apple Silicon o Intel en Acerca de este Mac.
2. Instala el paquete Temurin 21 que corresponda y abre una terminal nueva.
3. Comprueba `java -version` y `javac -version`. Para inspeccionar JDK instalados usa `/usr/libexec/java_home -V`.
4. Si necesitas seleccionar Java 21 en la sesión actual:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
export PATH="$JAVA_HOME/bin:$PATH"
```

5. Instala Python desde su distribución oficial para macOS si el disponible no satisface la versión. Comprueba `python3 --version` y ejecuta el mismo comando indicado para Ubuntu. No necesitas Homebrew para esta ruta.

## Estructura y carpeta de trabajo

Cada carpeta de lección contiene Main.java y su salida esperada. Las clases compartidas están en src/curso y declaran el paquete curso. El ejecutor compila esas clases junto con un único Main en una carpeta temporal. Si compilas todos los Main juntos tendrás clases duplicadas.

Los comandos de los ejecutores se lanzan desde la raíz del repositorio. Las rutas con `/` funcionan también en PowerShell. Usa comillas cuando una ruta propia tenga espacios. Los archivos de datos se leen y escriben con UTF-8 mediante Path, sin rutas absolutas fijadas a un sistema.

## Compilar manualmente (los tres sistemas)

Desde la raíz, crea una carpeta out una sola vez:

```bash
mkdir out
```

Compila una lección y las clases compartidas. Este comando usa sourcepath para localizar los fuentes del paquete y no depende de que la terminal expanda comodines:

```bash
javac -encoding UTF-8 -sourcepath src -d out unidad1/01-contratos-e-invariantes/Main.java
```

Ejecuta:

```bash
java -Dfile.encoding=UTF-8 -cp out Main
```

Para otra lección cambia la ruta de Main.java y recompila. Guarda los cambios antes de compilar. La salida bytecode no se actualiza solo por editar el fuente.

## Comprobar todo

Ubuntu/macOS:

```bash
python3 scripts/verificar.py
```

Windows:

```powershell
py -3 scripts/verificar.py
```

Se ejecutan pruebas, se contrastan salidas y se revisan enlaces. No se instala ningún paquete Python adicional. Las comprobaciones automáticas del repositorio ejecutan el mismo proceso con JDK 21 en los tres sistemas.

## Editor y diagnóstico

Puedes usar un editor de texto o un IDE con JDK 21. Si importas todo el curso, excluye los Main de otras lecciones del módulo que estés ejecutando. El ejecutor de terminal sirve como referencia para distinguir una configuración del IDE de un defecto del algoritmo.

| Síntoma | Primera comprobación |
|---|---|
| java o javac no se reconoce | JDK completo, PATH y terminal nueva |
| Python no se reconoce | Comando correcto para la instalación, versión y PATH |
| package curso does not exist | Uso del ejecutor o sourcepath src desde la raíz |
| duplicate class: Main | Compilar una sola lección |
| Archivo de datos no encontrado | Carpeta de trabajo y ruta con comillas si tiene espacios |
| Resultado antiguo | Guardar y recompilar si ejecutas bytecode |
| Error en una línea editada | Mensaje completo y precondiciones de esa operación |

[Comenzar](../unidad1/01-contratos-e-invariantes/README.md)
