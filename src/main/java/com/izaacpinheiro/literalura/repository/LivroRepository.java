package com.izaacpinheiro.literalura.repository;

import com.izaacpinheiro.literalura.model.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(String idioma);
}
