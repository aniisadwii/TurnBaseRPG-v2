package Main;
import java.util.*;

class SupportHero extends Hero {
    public SupportHero(String name) {
        super(name, 100, 10, 15, 25, 10, 20);
    }

    public void useSkill(BattleContext context) {
        System.out.println(name + " buffs attack of all Hunt/Destruction heroes by 10");
        for (Hero h : context.getTeam())
            if (h instanceof HuntHero || h instanceof DestructionHero) h.stats.attack += skillVar;
    }

    public void useUltimate(BattleContext context) {
        System.out.println(name + " greatly buffs attack of all Hunt/Destruction heroes by 20");
        for (Hero h : context.getTeam())
            if (h instanceof HuntHero || h instanceof DestructionHero) h.stats.attack += ultVar;
    }
}
