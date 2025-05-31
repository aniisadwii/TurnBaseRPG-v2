package Main;
import java.util.*;

public class GameApp {
    static Scanner sc = new Scanner(System.in);
    static HeroManager manager = new HeroManager();
    static TeamBuilder teamBuilder = new TeamBuilder();
    static BattleManager battle = new BattleManager();
    static List<Hero> dummyTeam = DummyHeroFactory.createFiveTypeHeroes();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> handleAddHero();
                case 2 -> manager.showHeroes();
                case 3 -> handleEditHero();
                case 4 -> handleDeleteHero();
                case 5 -> handleBattle();
                case 6 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    
    private static void printMenu() {
        System.out.println("\n-- HERO RPG GAME --");
        System.out.println("1. Add Hero");
        System.out.println("2. Show Heroes");
        System.out.println("3. Edit Hero");
        System.out.println("4. Delete Hero");
        System.out.println("5. Build Team and Battle");
        System.out.println("6. Exit");
        System.out.print("Choose: ");
    }
    
    private static void handleEditHero() {
        manager.showHeroes();
        System.out.print("Select index to edit: ");
        int i = sc.nextInt() - 1;
        sc.nextLine();
        System.out.print("New name: ");
        String newName = sc.nextLine();
        manager.editHeroName(i, newName);
    }
    
    private static void handleDeleteHero() {
        manager.showHeroes();
        System.out.print("Index to delete: ");
        int del = sc.nextInt() - 1;
        sc.nextLine();
        manager.deleteHero(del);
    }
    
    private static void handleBattle() {
        List<Hero> team = teamBuilder.chooseTeam(manager.getHeroList(), sc);
        for (int lvl = 1; lvl <= 20; lvl++) {
            System.out.println("\n-- LEVEL " + lvl + " --");
            battle.startBattle(team, lvl);
            if (team.stream().noneMatch(Hero::isAlive)) break;
            for (Hero h : team) h.hp = h.maxHp;
        }
    }

    private static void handleAddHero() {
        System.out.print("Hero Name: ");
        String name = sc.nextLine();

        System.out.println("Choose Hero Type:");
        for (int i = 0; i < HeroType.values().length; i++) {
            System.out.printf("%d. %s%n", i + 1, HeroType.values()[i]);
        }

        int input = sc.nextInt(); sc.nextLine();

        HeroType type = HeroType.fromInt(input);
        Hero h = manager.createHero(name, type);

        if (h != null) {
            manager.addHero(h);
        } else {
            System.out.println("Invalid type selected");
        }

    }
}
