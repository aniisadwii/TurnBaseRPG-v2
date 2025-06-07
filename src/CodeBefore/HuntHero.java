package CodeBefore;
import java.util.*;

class HuntHero extends Hero {
    public HuntHero(String name) {
        super(name, 100, 35, 10, 30);
    }

    public void useSkill(List<Hero> team, List<Monster> enemies) {
        Monster target = selectTarget(enemies);
        if (target != null) {
            System.out.println(name + " uses Sniper Shot on " + target.name);
            target.takeDamage(attack + 10);
        }
    }

    public void useUltimate(List<Hero> team, List<Monster> enemies) {
        Monster target = selectTarget(enemies);
        if (target != null) {
            System.out.println(name + " uses Deadeye on " + target.name);
            target.takeDamage(attack + 25);
        }
    }
}
