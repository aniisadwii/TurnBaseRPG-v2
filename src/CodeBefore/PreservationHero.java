package CodeBefore;
import java.util.*;

class PreservationHero extends Hero {
    public PreservationHero(String name) {
        super(name, 180, 15, 30, 10);
    }

    public void useSkill(List<Hero> team, List<Monster> enemies) {
        System.out.println(name + " uses Shield Wall: +10 DEF to all allies");
        for (Hero h : team) h.defense += 10;
    }

    public void useUltimate(List<Hero> team, List<Monster> enemies) {
        System.out.println(name + " uses Fortress Aura: +20 DEF to all allies");
        for (Hero h : team) h.defense += 20;
    }
}
