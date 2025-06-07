// Refactored BattleManager.java
package Main;

import java.util.*;

public class BattleManager {

    public void handleBattle(TeamBuilder teamBuilder, HeroManager manager, Scanner sc) {
        List<Hero> team = teamBuilder.chooseTeam(manager.getHeroList(), sc);
        for (int lvl = 1; lvl <= 20; lvl++) {
            System.out.println("\n-- LEVEL " + lvl + " --");
            startBattle(team, lvl);
            if (team.stream().noneMatch(Hero::isAlive)) break;
            for (Hero h : team) h.restoreFullHealth();
        }
    }

    public void startBattle(List<Hero> team, int level) {
        List<Monster> monsters = MonsterFactory.createMonsters(level);
        BattleContext context = new BattleContext(team, monsters);
        TeamSkillPointManager.resetTeamSkillPoints();

        List<Object> turnQueue = TurnQueueBuilder.build(team, monsters);

        System.out.println("\n-- WAVE " + level + " START --");
        battleLoop(context, turnQueue, level);
        BattleUIManager.displayBattleResult(team, level);
    }

    private void battleLoop(BattleContext context, List<Object> turnQueue, int level) {
        while (context.isBattleOngoing()) {
            TurnManager.executeTurns(turnQueue, context, level);
        }
    }
}
