package Main;
import java.util.*;

class HealerHero extends Hero {
    public HealerHero(String name) {
        super(name, 90, 10, 10, 25, 30, 50);
    }

    public void useSkill(List<Hero> team, List<Monster> enemies) {
        Hero target = selectAlly(team);
        if (target != null) {
            System.out.println(name + " heals " + target.name + " for 30 HP.");
            target.heal(skillVar);
        }
    }

    public void useUltimate(List<Hero> team, List<Monster> enemies) {
        System.out.println(name + " uses Full Recovery!");
        for (Hero h : team) h.heal(ultVar);
    }
}
