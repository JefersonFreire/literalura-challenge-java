package br.com.jeferson.literalura.service;

import br.com.jeferson.literalura.client.GutendexApiClient;
import br.com.jeferson.literalura.dto.AutorDTO;
import br.com.jeferson.literalura.dto.LivroDTO;
import br.com.jeferson.literalura.model.*;
import br.com.jeferson.literalura.repository.AutorRepository;
import br.com.jeferson.literalura.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LivroService {
    Scanner leitura = new Scanner(System.in);

    private final LivroRepository repository;
    private final AutorRepository autorRepository;
    private GutendexApiClient apiClient = new GutendexApiClient();
    private ConverteDados converteDados = new ConverteDados();

    public LivroService(LivroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public void obterDadosLivro() {
        System.out.println("Digite o título do livro que deseja buscar");
        var titulo = leitura.nextLine().replace(" ","+").trim();
        var endereco = "search=" + titulo;

        Optional<DadosLivro> optionalDadosLivro = getDadosLivro(endereco);

        if(optionalDadosLivro.isEmpty()) {
            System.out.println("Nenhum resultado encontrado para o título informado.");
            return;
        }

        DadosLivro dadosLivro = optionalDadosLivro.get();

        if(dadosLivro.autor() == null || dadosLivro.autor().isEmpty()){
            System.out.println("Livro sem autor definido. Operação cancelada.");
            return;
        }

        DadosAutor dadosAutor = dadosLivro.autor().get(0);

        Optional<Livro> livroExistente = repository.findByTituloIgnoreCaseAndAutorNomeIgnoreCase(dadosLivro.titulo(), dadosAutor.nome());
        if(livroExistente.isPresent()) {
            System.out.println("Livro já existe na base de dados.");
            return;
        }


        Optional<Autor> autorExistente = Optional.ofNullable(autorRepository.findByNome(dadosAutor.nome()));
        Autor autor = autorExistente.orElseGet(() -> {
            Autor novoAutor = new Autor();
            novoAutor.setNome(dadosAutor.nome());
            novoAutor.setAnoNascimento(dadosAutor.anoNascimento());
            novoAutor.setAnoFalecimento(dadosAutor.anoFalecimento());
            return novoAutor;
        });

        Livro livros = new Livro(dadosLivro);
        livros.setAutor(autor);
        autor.getLivros().add(livros);

        System.out.println(livros);
        System.out.println("\nDeseja salvar? Digite 'S' para sim, 'N' para não.");
        var salvar = leitura.nextLine();

        if (salvar.equalsIgnoreCase("s")) {
            repository.save(livros);
            System.out.println("Livro salvo com sucesso!");
        } else {
            System.out.println("Operação cancelada!");
        }

        System.out.println("\nDeseja buscar novo livro? Digite 'S' para sim, 'N' para não.");
        var novaBusca = leitura.nextLine();
        if (novaBusca.equalsIgnoreCase("s")) {
            obterDadosLivro();
        }
    }

    private Optional<DadosLivro> getDadosLivro(String endereco) {
        var json = apiClient.obterDadosApi(endereco);

        if(json == null || json.isBlank()){
            return Optional.empty();
        }
        RespostaApiClient resposta = converteDados.obterDados(json, RespostaApiClient.class);
        if (resposta.resultados() != null && !resposta.resultados().isEmpty()) {
            return Optional.of(resposta.resultados().getFirst());
        } else {
            return Optional.empty();
        }
    }

    public void listarTodosLivros() {
        List<Livro> livros = repository.findAll();
        List<LivroDTO> livrosDTO = livros.stream()
                .map(l -> new LivroDTO(l.getTitulo(), l.getAutor().getNome(),l.getIdioma(),l.getNumeroDownloads()))
                .toList();
        livrosDTO.forEach(System.out::println);
    }

    public void listarTodosAutores(){
        List<Autor> autores = autorRepository.findAll();
        List<AutorDTO>autorDTO = autores.stream()
                .map(a -> new AutorDTO(a.getNome(), a.getAnoNascimento(), a.getAnoFalecimento(),a.getLivros()))
                .toList();
        autorDTO.forEach(System.out::println);
    }

    public void listarAutoresVivosAnoDeterminado() {
        try {
            System.out.println("Insira o ano que deseja pesquisar:");
            int ano = leitura.nextInt();
            leitura.nextLine();
            List<Autor> autoresVivos = autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);
            if (autoresVivos.isEmpty()){
                System.out.println("Nenhum autor encontrado para essa data!");
            }
            List<AutorDTO> autoresVivosDTo = autoresVivos.stream()
                    .map(a -> new AutorDTO(a.getNome(), a.getAnoNascimento(), a.getAnoFalecimento(),a.getLivros()))
                    .toList();
            autoresVivosDTo.forEach(System.out::println);
        }catch (InputMismatchException e) {
            System.out.println("Entrada invalida. Digite um número para o ano ex '1990'.");
            leitura.nextLine();
            listarAutoresVivosAnoDeterminado();
        }

    }

    public void listarLivrosPorIdioma() {
        System.out.println("Digite o idioma para realizar a busca:");
        System.out.println("""
                pt - Português
                en - Inglês
                fr - Francês
                es - Espanhol
                it - Italiano
                """);
        var idioma = leitura.nextLine();
        List<Livro> listaDeLivros = repository.findByIdiomaEqualsIgnoreCase(idioma);
        if(listaDeLivros.isEmpty()){
            System.out.println("Não existem livros nesse idioma no banco de dados!");
        }
        List<LivroDTO> livrosDTO = listaDeLivros.stream()
                .map(l -> new LivroDTO(l.getTitulo(), l.getAutor().getNome(),l.getIdioma(),l.getNumeroDownloads()))
                .toList();
        livrosDTO.forEach(System.out::println);;
    }

    public void top10Livros () {
        var endereco = "popular";
        var json = apiClient.obterDadosApi(endereco);
        RespostaApiClient resposta = converteDados.obterDados(json, RespostaApiClient.class);
        System.out.println("Top 10 livros mais baixados");
        if (resposta.resultados() != null && !resposta.resultados().isEmpty()) {
            List<DadosLivro> dadosLivro = resposta.resultados();
            dadosLivro.stream()
                    .limit(10)
                    .toList()
                    .forEach(l -> System.out.println("\n---------------Livro---------------\n" +
                            "Título: " + l.titulo() +
                            "\nAutor: " + l.autor().getFirst().nome() +
                            "\nIdioma: " + l.idioma() +
                            "\nNúmero de downloads: " +l.numeroDownloads()));
        }
    }

    public void buscarAutorPorNome() {
        System.out.println("Digite o nome do autor que deseja buscar:");
        var nome = leitura.nextLine();
        Autor autor = autorRepository.buscarAutorPorNome(nome);

        if (autor == null) {
            System.out.println("Autor não encontrado.");
        } else {
            AutorDTO autorDTO = new AutorDTO(autor.getNome(), autor.getAnoNascimento(), autor.getAnoFalecimento(),autor.getLivros());
            System.out.println(autorDTO);
        }
    }
}
