package CodeBefore;
import java.util.*;

class Monster {
    String name;
    int hp, attack, defense, speed;

    public Monster(String name, int hp, int atk, int def, int spd) {
        this.name = name;
        this.hp = hp;
        this.attack = atk;
        this.defense = def;
        this.speed = spd;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int dmg) {
        hp -= Math.max(1, dmg - defense);
        if (hp < 0) hp = 0;
    }

    public void attackHero(Hero h) {
        System.out.println(name + " attacks " + h.name);
        h.takeDamage(attack);
    }

    public void printStatus() {
        System.out.println(name + " [HP: " + hp + "]");
    }
}
