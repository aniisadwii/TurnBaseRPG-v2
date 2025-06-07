package Main;
import java.util.*;

abstract class Hero {
    String name;
    Stats stats;
    int skillVar, ultVar;
    double ultimateGauge = 0.0;
    int turnCount = 0;

    public Hero(String name, int hp, int attack, int defense, int speed, int skillVar, int ultVar) {
        this.name = name;
        this.stats = new Stats(hp, hp, attack, defense, speed);
        this.skillVar = skillVar;
        this.ultVar = ultVar;
    }

    public boolean isAlive() {
        return stats.hp > 0;
    }

    public void takeDamage(int dmg) {
        stats.hp -= Math.max(1, dmg - stats.defense);
        if (stats.hp < 0) stats.hp = 0;
    }

    public void heal(int amount) {
        stats.hp = Math.min(stats.maxHp, stats.hp + amount);
    }

    public void printStatus() {
        System.out.printf("%s [HP: %d/%d, ULT: %.1f/3.0]\n", name, stats.hp, stats.maxHp, ultimateGauge);
    }

    public void restoreFullHealth() {
        this.stats.hp = this.stats.maxHp;
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

    public void basicAttack(BattleContext context) {
        Monster target = TargetSelector.selectMonsterTarget(context.getAliveEnemies());
        if (target != null) {
            System.out.println(name + " attacks " + target.name);
            target.takeDamage(stats.attack);
        }
    }

    public void performAction(BattleContext context) {
        if (!this.isAlive()) {
            context.removeDeadHero(this);
            return;
        }
        
        HeroActionHandler handler = new HeroActionHandler(this);
        handler.handleTurn(context);
        turnCount++;
    }

    public abstract void useSkill(BattleContext context);
    public abstract void useUltimate(BattleContext context);
}
