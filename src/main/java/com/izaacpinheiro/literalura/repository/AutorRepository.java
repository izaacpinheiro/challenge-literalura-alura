package com.izaacpinheiro.literalura.repository;

import com.izaacpinheiro.literalura.model.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoMorte >= :ano OR a.anoMorte IS NULL)")
    List<Autor> autoresVivosNoAno(int ano);
}
