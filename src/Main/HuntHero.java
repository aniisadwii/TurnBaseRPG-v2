package Main;
import java.util.*;

class HuntHero extends Hero {
    public HuntHero(String name) {
        super(name, 100, 35, 10, 30, 10, 25);
    }

    public void useSkill(BattleContext context) {
        Monster target = TargetSelector.selectMonsterTarget(context.getAliveEnemies());
        if (target != null) {
            System.out.println(name + " uses Sniper Shot on " + target.name);
            target.takeDamage(stats.attack + skillVar);
        }
    }

    public void useUltimate(BattleContext context) {
        Monster target = TargetSelector.selectMonsterTarget(context.getAliveEnemies());
        if (target != null) {
            System.out.println(name + " uses Deadeye on " + target.name);
            target.takeDamage(stats.attack + ultVar);
        }
    }
}
