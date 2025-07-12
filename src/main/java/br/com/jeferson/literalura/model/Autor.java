package br.com.jeferson.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Livro> livros = new ArrayList<>();

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();
    }

    public Autor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor1 = (Autor) o;
        return Objects.equals(id, autor1.id) && Objects.equals(nome, autor1.nome) && Objects.equals(anoNascimento, autor1.anoNascimento) && Objects.equals(anoFalecimento, autor1.anoFalecimento) && Objects.equals(livros, autor1.livros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, anoNascimento, anoFalecimento, livros);
    }

    @Override
    public String toString() {
        return "\n---------------Autor---------------\n" +
                "Autor: " + nome +
                "\nAno de nascimento: " + anoNascimento +
                "\nAno de falecimento: " + anoFalecimento +
                "\nLivros: " + livros.stream().map(Livro::getTitulo).toList();
    }
}
