package ui;

import exceptions.MenuException;
import enums.Classification;
import model.News;
import service.NewsService;

import java.io.PrintStream;
import java.util.Scanner;

public class Menu {
    private static final int ADICIONAR_MANUAL = 1;
    private static final int ADICIONAR_AUTOMATICO = 2;
    private static final int LISTAR = 3;
    private static final int SAIR = 4;

    private final NewsService newsService;
    private final PrintStream output;
    private final Scanner input;

    public Menu(NewsService newsService, PrintStream output, Scanner inputScanner) {
        this.newsService = newsService;
        this.output = output;
        this.input = inputScanner;
    }

    private String readText() {
        output.print("Digite o texto: ");
        String text = input.nextLine();

        if(text == null || text.isBlank()) {
            throw new MenuException("Texto inválido");
        }

        return text;
    }

    private Classification readClassification() {
        String classificationText;
        Classification classification;

        output.print("Digite a classificação: ");
        classificationText = input.nextLine();

        if(classificationText == null || classificationText.isBlank()) {
            classification = Classification.DUVIDOSA;
        } else {
            try {
                classification = Classification.valueOf(classificationText);
            } catch (IllegalArgumentException e) {
                throw new MenuException("Classificação inexistente. Utilize: CONFIAVEL, DUVIDOSA, FALSA");
            }
        }

        return classification;
    }

    private void addNewsManually() {
        String text;
        Classification classification;

        text = readText();
        classification = readClassification();

        newsService.addNews(new News(text, classification));
    }

    private void addNewsAutomatically() {
        String text;
        Classification classification;

        text = readText();
        classification = newsService.analyzeNewsText(text);

        newsService.addNews(new News(text, classification));
    }

    private void listNews() {
        newsService.getNewsList().forEach(
                news -> {
                    output.println("Texto: " + news.getText());
                    output.println("Classificacao: " + news.getClassification());
                    output.println("-------------------");
                }
        );
    }

    /**
     * @return true se o usuário deseja sair
     */
    private boolean selectMenuOptions() {
        output.println("1 - adicionar manual");
        output.println("2 - adicionar automatico");
        output.println("3 - listar");
        output.println("4 - sair");

        try {
            int option = Integer.parseInt(input.nextLine());

            if (option == ADICIONAR_MANUAL) {
                addNewsManually();
            } else if (option == ADICIONAR_AUTOMATICO) {
                addNewsAutomatically();
            } else if (option == LISTAR) {
                listNews();
            } else if (option == SAIR) {
                return true;
            } else {
                throw new MenuException("Opção inválida");
            }
        } catch (NumberFormatException exception) {
            throw new MenuException("Digite um número válido");
        }
        return false;
    }

    /**
     * Ciclo infinito para exibição do menu e execução de suas opções até que o usuário decida sair
     */
    public void mainMenuCycle() {
        boolean sair = false;
        while (!sair) {
            try {
                sair = selectMenuOptions();
            } catch (MenuException menuException) {
                output.println(menuException.getMessage());
            }
        }
    }
}
