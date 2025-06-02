package Main;

import java.util.*;

public class MonsterFactory {
    public static List<Monster> createMonsters(int level) {
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 3 + level / 5; i++) {
            monsters.add(new Monster("Monster Lv" + level + "-" + (i + 1), 80 + level * 10, 20 + level * 2, 5 + level, 10 + level));
        }
        return monsters;
    }
}
