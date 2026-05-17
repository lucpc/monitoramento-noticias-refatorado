import service.NewsService;
import ui.Menu;

void main() {
    Scanner input = new Scanner(System.in);
    PrintStream output = new PrintStream(System.out);
    NewsService newsService = new NewsService();
    Menu menu = new Menu(newsService, output, input);

    menu.mainMenuCycle();
}
