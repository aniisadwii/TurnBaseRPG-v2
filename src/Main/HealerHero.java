package Main;
import java.util.*;

class HealerHero extends Hero {
    public HealerHero(String name) {
        super(name, 90, 10, 10, 25, 20, 50);
    }

    public void useSkill(BattleContext context) {
        Hero target = TargetSelector.selectAllyTarget(context.getAliveTeam());
        if (target != null) {
            System.out.println(name + " heals " + target.name + " for 30 HP.");
            target.heal(skillVar);
        }
    }

    public void useUltimate(BattleContext context) {
        System.out.println(name + " uses Full Recovery!");
        for (Hero h : context.getTeam()) {
            h.stats.maxHp += skillVar;
            h.heal(ultVar);
        }
    }
}
