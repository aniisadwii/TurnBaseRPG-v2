package Main;
import java.util.*;

class DestructionHero extends Hero {
    public DestructionHero(String name) {
        super(name, 120, 30, 15, 20, 0, 20);
    }

    public void useSkill(BattleContext context) {
        System.out.println(name + " casts Flame Wave on all enemies!");
        for (Monster m : context.getAliveEnemies()) {
            m.takeDamage(stats.attack + skillVar);
        }
    }

    public void useUltimate(BattleContext context) {
        System.out.println(name + " casts Inferno Blast!");
        for (Monster m : context.getAliveEnemies()) {
            m.takeDamage(stats.attack + ultVar);
        }
    }
}
