package com.alura.literatura.repository;

import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// Esta interfaz extiende de JpaRepository, que es una interfaz de Spring Data JPA que nos permite realizar operaciones CRUD
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Traemos a todos los libros registrados de la base de datos
    @Query(value = "SELECT l FROM Libro l")
    List<Libro> findAllBooks();

    @Query("SELECT l FROM Libro l WHERE l.titulo ILIKE %:titulo%")
    Optional<Libro> findByTitulo(String titulo);

    // Buscamos los libros por idioma
    @Query(value = "SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> findByLanguage(String idioma);

    // Top 5 de libros más descargados de la base de datos
    @Query(value = "SELECT l FROM Libro l ORDER BY l.numeroDescargas DESC")
    List<Libro> findTop10Books();

}
