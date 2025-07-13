package br.com.jeferson.literalura.model;

import jakarta.persistence.*;


@Entity
@Table(name = "livros", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"titulo", "autor_id"})})
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer numeroDownloads;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Autor autor;

    public Livro(DadosLivro dadosLivro) {

        this.titulo = dadosLivro.titulo();

        if (dadosLivro.idioma() != null && !dadosLivro.idioma().isEmpty()) {
            this.idioma = dadosLivro.idioma().get(0);
        } else {
            this.idioma = "Não existe livros nesse idioma no banco de dados.";
        }
        this.numeroDownloads = dadosLivro.numeroDownloads();
    }

    public Livro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "\n---------------Livro---------------\n" +
                "Título: " + titulo +
                "\nAutor: " + getAutor().getNome() +
                "\nIdioma: " + idioma +
                "\nNúmero de downloads: " + numeroDownloads;
    }
}

