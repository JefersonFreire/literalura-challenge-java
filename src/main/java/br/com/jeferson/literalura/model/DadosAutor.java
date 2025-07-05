package br.com.jeferson.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
        @JsonAlias("name") String autor,
        @JsonAlias("birth_year") LocalDate anoNascimento,
        @JsonAlias("death_year") LocalDate anoFalecimento,
        @JsonAlias("bookshelves") List<Livro> livros
) {
}
