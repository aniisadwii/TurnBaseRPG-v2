package Main;
import java.util.*;

public class GameApp {
    static Scanner sc = new Scanner(System.in);
    static HeroManager manager = new HeroManager();
    static HeroController heroController = new HeroController(manager);
    static BattleManager battle = new BattleManager();
    static TeamBuilder teamBuilder = new TeamBuilder();

    public static void main(String[] args) {
        while (true) {
            MenuUI.printMenu();
            int input = sc.nextInt(); sc.nextLine();
            MenuOption choice = MenuOption.fromInt(input);

            switch (choice) {
                case ADD_HERO -> heroController.addHero(sc);
                case SHOW_HEROES -> heroController.showHeroes();
                case EDIT_HERO -> heroController.editHero(sc);
                case DELETE_HERO -> heroController.deleteHero(sc);
                case BATTLE -> battle.handleBattle(teamBuilder, manager, sc); // ← pass dependencies here
                case EXIT -> { return; }
                case INVALID -> System.out.println("Invalid choice.");
            }
        }
    }
}

