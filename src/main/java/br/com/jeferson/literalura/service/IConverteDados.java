package br.com.jeferson.literalura.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
