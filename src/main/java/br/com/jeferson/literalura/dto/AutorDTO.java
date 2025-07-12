package br.com.jeferson.literalura.dto;

import br.com.jeferson.literalura.model.Livro;

import java.util.List;

public record AutorDTO(
        String nome,
        Integer anoNascimento,
        Integer anoFalecimento,
        List<Livro> livros
) {
    @Override
    public String toString() {
        return "\n---------------Autor---------------\n" +
                "Autor: " + nome +
                "\nAno de nascimento: " + anoNascimento +
                "\nAno de falecimento: " + anoFalecimento +
                "\nLivros: " + livros.stream().map(Livro::getTitulo).toList();
    }
}
