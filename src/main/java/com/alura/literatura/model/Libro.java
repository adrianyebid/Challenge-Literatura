package com.alura.literatura.model;


import jakarta.persistence.*;

@Entity
@Table(name= "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;

    private String idioma;
    private Double numeroDescargas;


    // Constructor predeterminado necesario para las entidades
    public Libro() {
    }


    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();

        this.autor = new Autor(datosLibro.autor().getFirst());
        this.idioma = datosLibro.idiomas().getFirst();
        this.numeroDescargas = datosLibro.numeroDescargas();

    }

    @Override
    public String toString() {
        return "-----LIBRO----------\n" +
                "Titulo = " + titulo + "\n" +
                "Autor = " + autor.getNombre() + "\n" +
                "Idioma = " + idioma + "\n" +
                "NumeroDescargas = " + numeroDescargas + "\n" +
                "--------------------";
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
