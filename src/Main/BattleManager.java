// Refactored BattleManager.java
package Main;

import java.util.*;

public class BattleManager {
    public void startBattle(List<Hero> team, int level) {
        List<Monster> monsters = MonsterFactory.createMonsters(level);
        Hero.resetTeamSkillPoints();

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
