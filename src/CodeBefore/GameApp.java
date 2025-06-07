package CodeBefore;
import java.util.*;

public class GameApp {
    static Scanner sc = new Scanner(System.in);
    static HeroManager manager = new HeroManager();
    static TeamBuilder teamBuilder = new TeamBuilder();
    static BattleManager battle = new BattleManager();
    static List<Hero> dummyTeam = DummyHeroFactory.createFiveTypeHeroes();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n-- HERO RPG GAME --");
            System.out.println("1. Add Hero");
            System.out.println("2. Show Heroes");
            System.out.println("3. Edit Hero");
            System.out.println("4. Delete Hero");
            System.out.println("5. Build Team and Battle");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addHero(); break;
                case 2:
                    manager.showHeroes(); break;
                case 3:
                    manager.showHeroes();
                    System.out.print("Select index to edit: ");
                    int i = sc.nextInt() - 1; sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();
                    manager.editHeroName(i, newName); break;
                case 4:
                    manager.showHeroes();
                    System.out.print("Index to delete: ");
                    int del = sc.nextInt() - 1;
                    manager.deleteHero(del); break;
                case 5:
                    List<Hero> team = teamBuilder.chooseTeam(manager.getHeroList(), sc);
                    for (int lvl = 1; lvl <= 20; lvl++) {
                        System.out.println("\n-- LEVEL " + lvl + " --");
                        battle.startBattle(team, lvl);
                        if (team.stream().noneMatch(Hero::isAlive)) break;
                        for (Hero h : team) h.hp = h.maxHp;
                    }
                    break;
                case 6:
                    return;
            }
        }
    }

    private static void addHero() {
        System.out.print("Hero Name: ");
        String name = sc.nextLine();
        System.out.println("Type: 1=Hunt 2=Destruction 3=Preservation 4=Healer 5=Support");
        int t = sc.nextInt();
        Hero h = null;
        switch (t) {
            case 1: h = new HuntHero(name); break;
            case 2: h = new DestructionHero(name); break;
            case 3: h = new PreservationHero(name); break;
            case 4: h = new HealerHero(name); break;
            case 5: h = new SupportHero(name); break;
        }
        if (h != null) manager.addHero(h);
    }
}
