package Main;

import java.util.List;
import java.util.Scanner;

public class BattleUIManager {
    private static final Scanner sc = new Scanner(System.in);

    public static String getHeroInput(Hero hero, int level) {
        String ultStatus = (hero.turnCount % 3 == 0 && hero.turnCount != 0) ? "READY" : "NOT READY";
        System.out.println("\n[Wave " + level + "] " + hero.name + "'s turn [HP: " + hero.stats.hp + "/" + hero.stats.maxHp + ", ULT: " + ultStatus + "]");
        System.out.println("Enter 's' to skip this turn, 'x' to end the game, or any other key to continue:");
        return sc.nextLine();
    }

    public static void displayBattleResult(List<Hero> team, int level) {
        if (team.stream().anyMatch(Hero::isAlive)) {
            System.out.println("\nYou cleared Wave " + level + "!");
        } else {
            System.out.println("\nYour team was defeated at Wave " + level + ".");
        }
    }
}
