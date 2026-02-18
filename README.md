# Challenge LITERALURA 📚
O **Literalura** é uma aplicação de linha de comando (CLI) desenvolvida em Java com Spring Boot. O objetivo do projeto é consumir a API externa [Gutendex](https://gutendex.com/) para buscar informações sobre livros, registrar esses dados em um banco de dados (PostgreSQL) e permitir consultas através de um menu interativo.

## ⚙️ Funcionalidades

- **Consumo de API:** Busca livros por título na base de dados do Projeto Gutenberg.
- **Persistência de Dados:** Salva livros e autores automaticamente no banco de dados.
- **Consultas:**
  1. Buscar livros pelo título.
  2. Listar todos os livros registrados.
  3. Listar autores registrados.
  4. Listar autores vivos em um determinado ano.
  5. Listar livros por idioma (EN, ES, PT, FR).

## 🛠️ Tecnologias Utilizadas

- **Java** (Linguagem principal)
- **Spring Boot** (Framework)
  - Spring Data JPA (Persistência)
- **PostgreSQL** (Banco de dados)
- **Hibernate** (ORM)
- **Jackson** (Deserialização de JSON)
- **Maven** (Gerenciador de dependências)
