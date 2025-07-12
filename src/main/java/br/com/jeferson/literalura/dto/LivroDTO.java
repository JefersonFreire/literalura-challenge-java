package br.com.jeferson.literalura.dto;

public record LivroDTO(
        String titulo,
        String autor,
        String idioma,
        Integer numeroDownloads
) {

    @Override
    public String toString() {
        return  "\n---------------Livro---------------\n" +
                "Título: " + titulo +
                "\nAutor: " + autor +
                "\nIdioma: " + idioma +
                "\nNúmero de downloads: " + numeroDownloads;
    }
}
