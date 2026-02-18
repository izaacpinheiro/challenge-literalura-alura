package com.izaacpinheiro.literalura.model.livro;

import com.izaacpinheiro.literalura.dto.LivroRequestDTO;
import com.izaacpinheiro.literalura.model.autor.Autor;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;
    private Double numedoDownloads;

    @ManyToOne
    private Autor autor;

    public Livro() {}

    public Livro(LivroRequestDTO data, Autor autor) {
        this.titulo = data.titulo();
        this.idioma = !data.idiomas().isEmpty() ? data.idiomas().get(0) : null;
        this.numedoDownloads = data.numeroDownloads();
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumedoDownloads() {
        return numedoDownloads;
    }

    public void setNumedoDownloads(Double numedoDownloads) {
        this.numedoDownloads = numedoDownloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro: " + titulo + " | Autor: " + (autor != null ? autor.getNome() : "N/A") + " | Idioma: " + idioma;
    }
}
