package br.com.jeferson.literalura.repository;

import br.com.jeferson.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByIdiomaEqualsIgnoreCase(String idioma);

    Optional<Livro> findByTituloIgnoreCaseAndAutorNomeIgnoreCase(String titulo, String nomeAutor);
}
