package com.izaacpinheiro.literalura.main;

import com.izaacpinheiro.literalura.dto.AutorRequestDTO;
import com.izaacpinheiro.literalura.dto.DadosGutendex;
import com.izaacpinheiro.literalura.dto.LivroRequestDTO;
import com.izaacpinheiro.literalura.model.autor.Autor;
import com.izaacpinheiro.literalura.model.livro.Livro;
import com.izaacpinheiro.literalura.repository.AutorRepository;
import com.izaacpinheiro.literalura.repository.LivroRepository;
import com.izaacpinheiro.literalura.service.GutendexService;
import com.izaacpinheiro.literalura.service.JsonService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private Scanner scanner = new Scanner(System.in);

    private GutendexService consumo = new GutendexService();

    private JsonService conversor = new JsonService();

    private final String URL = "https://gutendex.com/books/?search=";

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Main(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void showMenu() {
        var opc = -1;

        while (opc != 0) {
            var menu = """
            1 - Buscar livro pelo título
            2 - Listar livros registrados
            3 - Listar autores registrados
            4 - Listar autores vivos em um determinado ano
            5 - Listar livros em um determinado idioma
            
            0 - Sair
            """;

            System.out.println(menu);

            try {
                opc = scanner.nextInt();
                scanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Opção inválida. Digite um número inteiro.");
                scanner.nextLine();
                opc = -1;
            }

            switch (opc) {
                case 1 -> buscarLivro();
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosEmDeterminadoAno();
                case 5 -> listarLivrosEmIdioma();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("opção inválida!");
            }
        }
    }

    public void buscarLivro() {
        System.out.println("Digite um livro para busca: ");
        var nomeLivro = scanner.nextLine();
        var json = consumo.getData(URL + nomeLivro.replace(" ", "%20"));
        var dados = conversor.getDados(json, DadosGutendex.class);

        if (dados.resultados() != null && !dados.resultados().isEmpty()) {
            // pega apenas o primeiro resultado
            LivroRequestDTO dadosLivro = dados.resultados().get(0);

            // verifica se o autor está no banco
            AutorRequestDTO dadosAutor = dadosLivro.autores().get(0);

            Autor autor = autorRepository.findByNome(dadosAutor.nome())
                    .orElseGet(() -> autorRepository.save(new Autor(dadosAutor)));

            // salva o livro no banco
            Livro livro = new Livro(dadosLivro, autor);

            // verifica duplicidade de livro antes de salvar
            try {
                livroRepository.save(livro);
                System.out.println("Livro salvo: " + livro);
            } catch (Exception e) {
                System.out.println("Erro ao salvar: " + e.getMessage());
            }
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    public void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    public void listarAutoresVivosEmDeterminadoAno() {
        System.out.println("Digite o ano para pesquisa:");
        var ano = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autores = autorRepository.autoresVivosNoAno(ano);
        autores.forEach(System.out::println);
    }

    public void listarLivrosEmIdioma() {
        System.out.println("Digite o idioma para pesquisa (es, en, fr, pt):");
        var idioma = scanner.nextLine();

        List<Livro> livros = livroRepository.findByIdioma(idioma);
        livros.forEach(System.out::println);
    }
}
