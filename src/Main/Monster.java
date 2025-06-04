package Main;
import java.util.*;

class Monster {
    String name;
    Stats stats;

    public Monster(String name, int hp, int atk, int def, int spd) {
        this.name = name;
        this.stats = new Stats(hp, hp, atk, def, spd);
    }

    public boolean isAlive() {
        return stats.hp > 0;
    }

    public void takeDamage(int dmg) {
        stats.hp -= Math.max(1, dmg - stats.defense);
        if (stats.hp < 0) stats.hp = 0;
    }

    public void attackHero(Hero h) {
        System.out.println(name + " attacks " + h.name);
        h.takeDamage(stats.attack);
    }

    public void printStatus() {
        System.out.println(name + " [HP: " + stats.hp + "]");
    }
}
