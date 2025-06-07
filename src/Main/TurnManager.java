// TurnManager.java
package Main;

import java.util.*;

public class TurnManager {
    public static void executeTurns(List<Object> queue, BattleContext context, int level) {
        for (Object obj : queue) {
        	if (!context.isBattleOngoing()) return;

            if (obj instanceof Hero hero && hero.isAlive()) {
                handleHeroTurn(hero, context, level);
            } else if (obj instanceof Monster monster && monster.isAlive()) {
                handleMonsterTurn(monster, context, level);
            }
        }
    }

    private static void handleHeroTurn(Hero hero, BattleContext context, int level) {
        String input = BattleUIManager.getHeroInput(hero, level);

        if (input.equalsIgnoreCase("x")) {
            System.out.println("\nGame ended by player.");
            System.exit(0);
        } else if (!input.equalsIgnoreCase("s")) {
            hero.performAction(context);
        } else {
            System.out.println(hero.name + " skipped the turn.");
        }
    }

    private static void handleMonsterTurn(Monster monster, BattleContext context, int level) {
    	List<Hero> aliveHeroes = context.getAliveTeam();

        if (!aliveHeroes.isEmpty()) {
            Hero target = aliveHeroes.get(new Random().nextInt(aliveHeroes.size()));
            System.out.println("\n[Wave " + level + "] " + monster.name + " attacks " + target.name);
            monster.attackHero(target);
        }
    }

}
