package Main;
import java.util.*;

abstract class Hero {
    String name;
    int hp, maxHp, attack, defense, speed, skillVar, ultVar;
    double ultimateGauge = 0.0;
    int turnCount = 0;

    public Hero(String name, int hp, int attack, int defense, int speed, int skillVar, int ultVar) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.skillVar = skillVar;
        this.ultVar = ultVar;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int dmg) {
        hp -= Math.max(1, dmg - defense);
        if (hp < 0) hp = 0;
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    public void printStatus() {
        System.out.printf("%s [HP: %d/%d, ULT: %.1f/3.0]\n", name, hp, maxHp, ultimateGauge);
    }

    public void restoreFullHealth() {
        this.hp = this.maxHp;
    }

    public boolean isUltimateReady() {
        return ultimateGauge >= 3.0;
    }

    public void gainUltimateGauge(double amount) {
        ultimateGauge = Math.min(3.0, ultimateGauge + amount);
    }

    public void resetUltimateGauge() {
        ultimateGauge = 0.0;
    }

    public void basicAttack(List<Monster> enemies) {
        Monster target = TargetSelector.selectMonsterTarget(enemies);
        if (target != null) {
            System.out.println(name + " attacks " + target.name);
            target.takeDamage(attack);
        }
    }

    public void performAction(List<Hero> team, List<Monster> enemies) {
        if (!this.isAlive()) {
            team.remove(this);
            return;
        }
        
        HeroActionHandler handler = new HeroActionHandler(this);
        handler.handleTurn(team, enemies);
        turnCount++;
    }

    public abstract void useSkill(List<Hero> team, List<Monster> enemies);
    public abstract void useUltimate(List<Hero> team, List<Monster> enemies);
}