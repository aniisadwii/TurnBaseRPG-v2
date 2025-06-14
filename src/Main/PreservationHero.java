package Main;
import java.util.*;

class PreservationHero extends Hero {
    public PreservationHero(String name) {
        super(name, 180, 15, 30, 10, 10, 20);
    }

    public void useSkill(BattleContext context) {
        System.out.println(name + " uses Shield Wall: +10 DEF to all allies");
        for (Hero h : context.getTeam()) h.stats.defense += 10;
    }

    public void useUltimate(BattleContext context) {
        System.out.println(name + " uses Fortress Aura: +20 DEF to all allies");
        for (Hero h : context.getTeam()) h.stats.defense += 20;
    }
}
