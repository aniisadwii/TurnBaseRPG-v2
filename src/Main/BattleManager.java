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
        TeamSkillPointManager.resetTeamSkillPoints();

        List<Object> turnQueue = TurnQueueBuilder.build(team, monsters);

        System.out.println("\n-- WAVE " + level + " START --");
        battleLoop(team, monsters, turnQueue, level);
        BattleUIManager.displayBattleResult(team, level);
    }

    private void battleLoop(List<Hero> team, List<Monster> monsters, List<Object> turnQueue, int level) {
        while (BattleHelper.isBattleOngoing(team, monsters)) {
            TurnManager.executeTurns(turnQueue, team, monsters, level);
        }
    }
}
