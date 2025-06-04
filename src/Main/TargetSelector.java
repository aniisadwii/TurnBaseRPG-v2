package Main;
import java.util.*;

public class TargetSelector {
    private static final Scanner sc = new Scanner(System.in);

    public static Monster selectMonsterTarget(List<Monster> enemies) {
        List<Monster> alive = getAliveMonsters(enemies);
        if (alive.isEmpty()) return null;
        
        displayMonsterTargets(alive);
        int idx = sc.nextInt() - 1;
        return alive.get(Math.max(0, Math.min(idx, alive.size() - 1)));
    }

    public static Hero selectAllyTarget(List<Hero> team) {
        List<Hero> alive = getAliveHeroes(team);
        if (alive.isEmpty()) return null;
        
        displayAllyTargets(alive);
        int idx = sc.nextInt() - 1;
        return alive.get(Math.max(0, Math.min(idx, alive.size() - 1)));
    }

    private static List<Monster> getAliveMonsters(List<Monster> enemies) {
        List<Monster> alive = new ArrayList<>();
        for (Monster m : enemies) {
            if (m.isAlive()) alive.add(m);
        }
        return alive;
    }

    private static List<Hero> getAliveHeroes(List<Hero> team) {
        List<Hero> alive = new ArrayList<>();
        for (Hero h : team) {
            if (h.isAlive()) alive.add(h);
        }
        return alive;
    }

    private static void displayMonsterTargets(List<Monster> alive) {
        System.out.println("Choose a target:");
        for (int i = 0; i < alive.size(); i++) {
            System.out.println((i + 1) + ". " + alive.get(i).name + " [HP: " + alive.get(i).stats.hp + "]");
        }
    }

    private static void displayAllyTargets(List<Hero> alive) {
        System.out.println("Choose an ally:");
        for (int i = 0; i < alive.size(); i++) {
            System.out.println((i + 1) + ". " + alive.get(i).name + " [HP: " + alive.get(i).stats.hp + "]");
        }
    }
}
