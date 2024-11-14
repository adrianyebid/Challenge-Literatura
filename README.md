# Aplicación de Gestión de Libros

Esta aplicación permite a los usuarios buscar libros utilizando la API de Gutendex, almacenar libros en una base de datos y realizar diversas consultas sobre los libros y autores registrados.

## Características

- **Buscar libro:** Permite buscar un libro por su nombre en la API de Gutendex y almacenarlo en la base de datos si no está presente.
- **Listar libros registrados:** Muestra todos los libros que se han almacenado en la base de datos.
- **Listar autores registrados:** Muestra todos los autores que se han almacenado en la base de datos.
- **Listar autores vivos en un determinado año:** Permite listar autores que estaban vivos en un año especificado.
- **Listar libros por idioma:** Permite listar libros almacenados en la base de datos filtrados por su idioma.
- **Buscar autor:** Permite buscar un autor por su nombre en la base de datos.
- **Listar top 5 libros más descargados:** Muestra los 5 libros más descargados según la base de datos.

## Uso

### Mostrar Menú

El método `mostrarMenu()` despliega un menú interactivo donde los usuarios pueden seleccionar diversas opciones para interactuar con la aplicación.

### Buscar Libro

El método `obtenerLibro()` permite a los usuarios buscar un libro por su nombre. Si el libro ya existe en la base de datos, se informa al usuario. De lo contrario, el libro se busca en la API de Gutendex, se muestran los detalles y se almacena en la base de datos junto con su autor.

### Listar Libros Registrados

El método `obtenerLibrosRegistrados()` muestra todos los libros que se han almacenado en la base de datos.

### Listar Autores Registrados

El método `obtenerAutoresRegistrados()` muestra todos los autores que se han almacenado en la base de datos.

### Listar Autores Vivos en un Determinado Año

El método `obtenerAutoresVivosEnAno()` permite listar autores que estaban vivos en un año especificado por el usuario.

### Listar Libros por Idioma

El método `obtenerLibrosPorIdioma()` permite listar libros almacenados en la base de datos filtrados por su idioma. Los usuarios pueden elegir entre Español, Inglés, Francés y Portugués.

### Buscar Autor

El método `obtenerAutor()` permite a los usuarios buscar un autor por su nombre en la base de datos. Si el autor se encuentra registrado, se muestran sus detalles.

### Listar Top 5 Libros Más Descargados

El método `obtenerTop5Libros()` muestra los 5 libros más descargados según la base de datos.

## Requisitos

- Java 8 o superior
- Dependencias adicionales (por ejemplo, bibliotecas para consumir la API, manejar JSON, etc.)

## Ejecución

1. Clona el repositorio: 
    ```bash
    git clone [URL del repositorio]
    ```
2. Compila el proyecto:
    ```bash
    javac Principal.java
    ```
3. Ejecuta la aplicación:
    ```bash
    java Principal
    ```

## Notas

- Asegúrate de configurar adecuadamente la conexión a la base de datos y las dependencias necesarias antes de ejecutar la aplicación.
- Puedes extender la funcionalidad agregando nuevas características o mejorando las existentes según tus necesidades.

¡Espero que esto te ayude! Si tienes alguna otra petición, ¡estaré aquí para asistirte!
