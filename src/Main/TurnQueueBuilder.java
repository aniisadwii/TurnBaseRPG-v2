package Main;

import java.util.*;

public class TurnQueueBuilder {
    public static List<Object> build(List<Hero> team, List<Monster> monsters) {
        List<Object> turnQueue = new ArrayList<>();
        turnQueue.addAll(team);
        turnQueue.addAll(monsters);
        turnQueue.sort(Comparator.comparingInt(o -> {
            if (o instanceof Hero) return -((Hero) o).speed;
            else return -((Monster) o).speed;
        }));
        return turnQueue;
    }
}
