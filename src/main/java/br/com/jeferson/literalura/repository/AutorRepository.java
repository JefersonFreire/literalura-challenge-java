package br.com.jeferson.literalura.repository;

import br.com.jeferson.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {

    Autor findByNome(String nome);

    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(Integer anoNasc, Integer anoFalec);

    @Query("SELECT a FROM Autor a WHERE a.nome ILIKE %:nome%")
    Autor buscarAutorPorNome(String nome);

}
