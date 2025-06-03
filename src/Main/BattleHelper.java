package Main;

import java.util.List;

public class BattleHelper {
    public static boolean isBattleOngoing(List<Hero> team, List<Monster> monsters) {
        return team.stream().anyMatch(Hero::isAlive) && monsters.stream().anyMatch(Monster::isAlive);
    }
}
