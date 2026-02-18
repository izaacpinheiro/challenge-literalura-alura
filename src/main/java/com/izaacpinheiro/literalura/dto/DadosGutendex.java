package com.izaacpinheiro.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosGutendex(
        @JsonAlias("results") List<LivroRequestDTO> resultados
) {}
