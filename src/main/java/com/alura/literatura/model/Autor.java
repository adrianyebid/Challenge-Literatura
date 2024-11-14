package com.alura.literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Genera un valor único para la clave primaria
    private Long Id;

    private String nombre;
    private String fechaNacimiento;
    private String fechaFallecimiento;

    // Relación uno a muchos con la entidad Libro
    // Un autor puede tener varios libros
    // mappedBy indica el nombre del atributo en la clase Libro que mapea esta relación
    // cascade = CascadeType.ALL asegura que las operaciones en Autor se apliquen también a los libros relacionados
    // fetch = FetchType.EAGER carga los libros junto con el autor para evitar múltiples accesos a la base de datos
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {}

    // Constructor predeterminado necesario para las entidades
    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaFallecimiento = datosAutor.fechaFallecimiento();
    }

    @Override
    public String toString() {

        List<String> titulos = libros.stream()
                .map(Libro::getTitulo)
                .toList();

        return  "Nombre = " + nombre + "\n" +
                "FechaNacimiento = " + fechaNacimiento + "\n" +
                "FechaFallecimiento = " + fechaFallecimiento + "\n" +
                "Libros = " + titulos+ "\n" +
                "--------------------";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(String fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }


}
