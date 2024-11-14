package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.nombre ILIKE %:nombre%")
    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a")
    List<Autor> findAllAuthors();

    @Query(value = "SELECT a FROM Autor a WHERE a.fechaFallecimiento IS NULL OR a.fechaFallecimiento > :year AND a.fechaNacimiento < :year")
    List<Autor> findAuthorsAliveInYear(String year);

}