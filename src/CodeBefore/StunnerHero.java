package CodeBefore;
import java.util.*;

class StunnerHero extends Hero {

    public StunnerHero(String name) {
        super(name, 100, 20, 10, 25);
    }
    
    public void useSkill(List<Hero> team, List<Monster> enemies) {
        Monster target = selectTarget(enemies);
        if (target != null) {
            System.out.println(name + " uses Shock Strike to stun " + target.name);
            System.out.println("Monster is stunned for 1 turn!");
            target.takeDamage(attack + 10);
        }
    }

    public void useUltimate(List<Hero> team, List<Monster> enemies) {
        System.out.println(name + " uses Thunderquake on all enemies!");
        for (Monster m : enemies) {
            if (m.isAlive()) {
                m.takeDamage(attack + 10);
                System.out.println("All Monster is stunned for 1 turns! (not implemented)");
            }
        }
    }
}
