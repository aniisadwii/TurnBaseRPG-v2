// TurnManager.java
package Main;

import java.util.*;

public class TurnManager {
    public static void executeTurns(List<Object> queue, List<Hero> team, List<Monster> monsters, int level) {
        for (Object obj : queue) {
            if (!isBattleOngoing(team, monsters)) return;

            if (obj instanceof Hero hero && hero.isAlive()) {
                handleHeroTurn(hero, team, monsters, level);
            } else if (obj instanceof Monster monster && monster.isAlive()) {
                handleMonsterTurn(monster, team, level);
            }
        }
    }

    private static boolean isBattleOngoing(List<Hero> team, List<Monster> monsters) {
        return team.stream().anyMatch(Hero::isAlive) && monsters.stream().anyMatch(Monster::isAlive);
    }

    private static void handleHeroTurn(Hero hero, List<Hero> team, List<Monster> monsters, int level) {
        String input = BattleUIManager.getHeroInput(hero, level);

        if (input.equalsIgnoreCase("x")) {
            System.out.println("\nGame ended by player.");
            System.exit(0);
        } else if (!input.equalsIgnoreCase("s")) {
            hero.performAction(team, monsters);
        } else {
            System.out.println(hero.name + " skipped the turn.");
        }
    }

    private static void handleMonsterTurn(Monster monster, List<Hero> team, int level) {
        List<Hero> aliveHeroes = team.stream()
                                     .filter(Hero::isAlive)
                                     .toList();

        if (!aliveHeroes.isEmpty()) {
            Hero target = aliveHeroes.get(new Random().nextInt(aliveHeroes.size()));
            System.out.println("\n[Wave " + level + "] " + monster.name + " attacks " + target.name);
            monster.attackHero(target);
        }
    }

}
