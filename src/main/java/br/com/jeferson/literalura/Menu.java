package br.com.jeferson.literalura;

import br.com.jeferson.literalura.service.LivroService;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class Menu {
    Scanner leitura = new Scanner(System.in);
    private final LivroService service;

    public Menu(LivroService service) {
        this.service = service;
    }

    public void exibirMenu() {
        var opcao = -1;
        var menu = """
                    \n---------------LiterAlura---------------
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                    6- Top 10 livros mais baixados
                    7- Buscar autor por nome
                    0- Sair
                    """;

        while (opcao != 0) {

            System.out.println(menu);

            try {
                opcao = leitura.nextInt();
                leitura.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada de dados invalida! Digite um número.");
                leitura.nextLine();
                continue;
            }

            switch (opcao) {
                case 1: service.obterDadosLivro();
                    break;
                case 2: service.listarTodosLivros();
                    break;
                case 3: service.listarTodosAutores();
                    break;
                case 4: service.listarAutoresVivosAnoDeterminado();
                    break;
                case 5: service.listarLivrosPorIdioma();
                    break;
                case 6: service.top10Livros();
                    break;
                case 7: service.buscarAutorPorNome();
                    break;
                case 0:  System.out.println("Saindo da aplicação...");
                leitura.close();
                    break;
                default: System.out.println("Digite uma opção válida!");
            }
        }
    }
}
