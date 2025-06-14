package CodeBefore;
import java.util.*;

class SupportHero extends Hero {
    public SupportHero(String name) {
        super(name, 100, 10, 15, 25);
    }

    public void useSkill(List<Hero> team, List<Monster> enemies) {
        System.out.println(name + " buffs attack of all Hunt/Destruction heroes by 10");
        for (Hero h : team)
            if (h instanceof HuntHero || h instanceof DestructionHero) h.attack += 10;
    }

    public void useUltimate(List<Hero> team, List<Monster> enemies) {
        System.out.println(name + " greatly buffs attack of all Hunt/Destruction heroes by 20");
        for (Hero h : team)
            if (h instanceof HuntHero || h instanceof DestructionHero) h.attack += 20;
    }
}
