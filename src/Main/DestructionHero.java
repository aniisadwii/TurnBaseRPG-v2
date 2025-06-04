package Main;
import java.util.*;

class DestructionHero extends Hero {
    public DestructionHero(String name) {
        super(name, 120, 30, 15, 20, 0, 20);
    }

    public void useSkill(List<Hero> team, List<Monster> enemies) {
        System.out.println(name + " casts Flame Wave on all enemies!");
        for (Monster m : enemies) if (m.isAlive()) m.takeDamage(stats.attack + skillVar);
    }

    public void useUltimate(List<Hero> team, List<Monster> enemies) {
        System.out.println(name + " casts Inferno Blast!");
        for (Monster m : enemies) if (m.isAlive()) m.takeDamage(stats.attack + ultVar);
    }
}
