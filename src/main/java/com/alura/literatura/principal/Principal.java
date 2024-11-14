package com.alura.literatura.principal;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Datos;
import com.alura.literatura.model.DatosLibro;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import com.alura.literatura.service.ConsumirAPI;
import com.alura.literatura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    // Scanner para leer la entrada del usuario
    private Scanner entrada = new Scanner(System.in);
    // Objeto para consumir API
    private ConsumirAPI consumoApi = new ConsumirAPI();
    // URL base de la API Gutendex
    private final String URL_BASE = "https://gutendex.com/books/";
    // Objeto para convertir datos JSON a objetos Java
    private ConvierteDatos conversor = new ConvierteDatos();
    // Repositorio para almacenar los libros en una base de datos
    private LibroRepository repositorio;
    private AutorRepository repositorioAutor;

    // Constructor que inicializa los repositorios
    public Principal(LibroRepository repository, AutorRepository repositorioAutor) {
        this.repositorio = repository;
        this.repositorioAutor = repositorioAutor;
    }

    // Método para mostrar el menú principal
    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado ano
                    5 - Listar libros por idioma
                    6 - Buscar autor
                    7 - Listar top 5 libros más descargados
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = entrada.nextInt();
            entrada.nextLine();
            switch (opcion) {
                case 1 -> obtenerLibro();
                case 2 -> obtenerLibrosRegistrados();
                case 3 -> obtenerAutoresRegistrados();
                case 4 -> obtenerAutoresVivosEnAno();
                case 5 -> obtenerLibrosPorIdioma();
                case 6 -> obtenerAutor();
                case 7 -> obtenerTop5Libros();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida");
            }
        }
    }

    // Método para obtener un libro desde la API o la base de datos
    public void obtenerLibro(){
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var nombreLibro = entrada.nextLine();

        // Verificar si el libro ya existe en la base de datos
        Optional<Libro> libroExistente = repositorio.findByTitulo(nombreLibro);
        if (libroExistente.isPresent()) {
            System.out.println("El libro ya está en la base de datos");
            System.out.println(libroExistente.get());
            return;
        }
        // Obtener datos de la API buscando por nombre de libro
        String json = consumoApi.obtenerDatos(URL_BASE +"?search="+ nombreLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        // Buscar el primer libro que contenga el nombre ingresado (insensible a mayúsculas/minúsculas)
        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if(libroBuscado.isPresent()){
            // Si el libro es encontrado, mostrar los detalles
            System.out.println("Libro encontrado");
            System.out.println(libroBuscado.get());

            // Verificar si el autor ya existe en la base de datos
            Optional<Autor> autorExistente = repositorioAutor.findByNombre(libroBuscado.get().autor().getFirst().nombre());
            Autor autor;

            if (autorExistente.isPresent()) {
                // Si el autor ya existe, lo asignas al libro
                System.out.println("Autor ya esta en la bd");
                autor = autorExistente.get();
            } else {
                // Si no existe, creas un nuevo autor
                autor = new Autor(libroBuscado.get().autor().getFirst());
                repositorioAutor.save(autor);
                System.out.println("Autor guardado exitosamente");
            }

            // Crear y guardar el libro en la base de datos
            Libro libro = new Libro(libroBuscado.get());
            libro.setAutor(autor);
            repositorio.save(libro);
            System.out.println("Libro guardado exitosamente");
        } else {
            // Si no se encuentra el libro, informar al usuario
            System.out.println("Libro no encontrado");
        }
    }

    // Método para obtener y mostrar todos los libros registrados
    public void obtenerLibrosRegistrados(){
        System.out.println("Libros registrados");
        repositorio.findAllBooks().forEach(System.out::println);
    }

    // Método para obtener y mostrar todos los autores registrados
    public void obtenerAutoresRegistrados(){
        System.out.println("Autores registrados");
        repositorioAutor.findAllAuthors().forEach(System.out::println);
    }

    // Método para obtener y mostrar autores vivos en un determinado año
    public void obtenerAutoresVivosEnAno() {
        System.out.println("Ingrese el año");

        var year = entrada.nextLine();
        if (year.matches("\\d{1,4}"))
            repositorioAutor.findAuthorsAliveInYear(year).forEach(System.out::println);
        else {
            System.out.println("Año no válido");
        }

    }

    // Método para obtener y mostrar libros por idioma
    public void obtenerLibrosPorIdioma(){
        System.out.println("Elija la opcion del idioma de los libros que desea buscar");
        var menu = """
                1 - Español
                2 - Inglés
                3 - Francés
                4 - Portugués
                """;
        System.out.println(menu);
        var opcion = entrada.nextInt();
        switch (opcion){
            case 1 -> buscarPorIdioma("es");
            case 2 -> buscarPorIdioma("en");
            case 3 -> buscarPorIdioma("fr");
            case 4 -> buscarPorIdioma("pt");
            default -> System.out.println("Opción no válida");
        }
    }

    // Método auxiliar para buscar libros por idioma
    private void buscarPorIdioma(String idioma) {
        repositorio.findByLanguage(idioma).forEach(System.out::println);
    }

    private void obtenerAutor(){
        System.out.println("Ingrese el nombre del autor que desea buscar");
        var nombreAutor = entrada.nextLine();
        Optional<Autor> autorExistente = repositorioAutor.findByNombre(nombreAutor);
        if (autorExistente.isPresent()) {
            System.out.println("Autor encontrado");
            System.out.println(autorExistente.get());
        } else {
            System.out.println("Autor no encontrado");
        }
    }

    private void obtenerTop5Libros(){
        System.out.println("Top 5 de libros más descargados");
        repositorio.findTop10Books().stream().limit(5).forEach(System.out::println);
    }
}
