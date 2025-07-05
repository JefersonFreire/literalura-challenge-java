package br.com.jeferson.literalura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("title") String titulo,
        @JsonAlias("languages") String idioma,
        @JsonAlias("download_count") Integer numeroDownloads,
        @JsonAlias("name") Autor autor
) {
}
