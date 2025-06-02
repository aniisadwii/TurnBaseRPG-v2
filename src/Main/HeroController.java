package Main;
import java.util.*;

public class HeroController {
    private final HeroManager manager;

    public HeroController(HeroManager manager) {
        this.manager = manager;
    }

    public void addHero(Scanner sc) {
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

    public void editHero(Scanner sc) {
        manager.showHeroes();
        System.out.print("Select index to edit: ");
        int i = sc.nextInt() - 1;
        sc.nextLine();
        System.out.print("New name: ");
        String newName = sc.nextLine();
        manager.editHeroName(i, newName);
    }

    public void deleteHero(Scanner sc) {
        manager.showHeroes();
        System.out.print("Index to delete: ");
        int del = sc.nextInt() - 1;
        sc.nextLine();
        manager.deleteHero(del);
    }

    public void showHeroes() {
        manager.showHeroes();
    }

    public List<Hero> getHeroList() {
        return manager.getHeroList();
    }
}
