package com.izaacpinheiro.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroRequestDTO(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<AutorRequestDTO> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDownloads
) {
}
