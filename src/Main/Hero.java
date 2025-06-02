package Main;
import java.util.*;

abstract class Hero {
    String name;
    int hp, maxHp, attack, defense, speed;
    int ultimateGauge = 0;
    int turnCount = 0;
    static int teamSkillPoints = 5;
    Scanner sc = new Scanner(System.in);

    public Hero(String name, int hp, int attack, int defense, int speed) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
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
        System.out.println(name + " [HP: " + hp + "/" + maxHp + ", ATK: " + attack + ", DEF: " + defense + ", SPD: " + speed + "]");
    }

    public void performAction(List<Hero> team, List<Monster> enemies) {
        System.out.println("\n-- " + name + "'s turn --");
        System.out.println("Team Skill Points: " + teamSkillPoints);
        System.out.println("1. Basic Attack\n2. Skill (-1 SP)\n3. Ultimate (every 3rd turn)");
        int choice = sc.nextInt();
        turnCount++;

        switch (choice) {
            case 1:
                basicAttack(enemies);
                teamSkillPoints = Math.min(5, teamSkillPoints + 1);
                break;
            case 2:
                if (teamSkillPoints > 0) {
                    useSkill(team, enemies);
                    teamSkillPoints--;
                } else {
                    System.out.println("Not enough Skill Points. Using Basic Attack instead.");
                    basicAttack(enemies);
                    teamSkillPoints = Math.min(5, teamSkillPoints + 1);
                }
                break;
            case 3:
                if (turnCount % 3 == 0) {
                    useUltimate(team, enemies);
                } else {
                    System.out.println("Ultimate only available every 3rd turn! Using Basic Attack instead.");
                    basicAttack(enemies);
                    teamSkillPoints = Math.min(5, teamSkillPoints + 1);
                }
                break;
            default:
                System.out.println("Invalid. Defaulting to Basic Attack.");
                basicAttack(enemies);
                teamSkillPoints = Math.min(5, teamSkillPoints + 1);
        }
    }

    public void basicAttack(List<Monster> enemies) {
        Monster target = selectTarget(enemies);
        if (target != null) {
            System.out.println(name + " attacks " + target.name);
            target.takeDamage(attack);
        }
    }

    protected Monster selectTarget(List<Monster> enemies) {
        List<Monster> alive = new ArrayList<>();
        for (Monster m : enemies) {
            if (m.isAlive()) alive.add(m);
        }
        if (alive.isEmpty()) return null;
        System.out.println("Choose a target:");
        for (int i = 0; i < alive.size(); i++) {
            System.out.println((i + 1) + ". " + alive.get(i).name + " [HP: " + alive.get(i).hp + "]");
        }
        int idx = sc.nextInt() - 1;
        return alive.get(Math.max(0, Math.min(idx, alive.size() - 1)));
    }

    protected Hero selectAlly(List<Hero> team) {
        List<Hero> alive = new ArrayList<>();
        for (Hero h : team) {
            if (h.isAlive()) alive.add(h);
        }
        if (alive.isEmpty()) return null;
        System.out.println("Choose an ally:");
        for (int i = 0; i < alive.size(); i++) {
            System.out.println((i + 1) + ". " + alive.get(i).name + " [HP: " + alive.get(i).hp + "]");
        }
        int idx = sc.nextInt() - 1;
        return alive.get(Math.max(0, Math.min(idx, alive.size() - 1)));
    }

    public static void resetTeamSkillPoints() {
        teamSkillPoints = 5;
    }
    
    public void restoreFullHealth() {
        this.hp = this.maxHp;
    }


    public abstract void useSkill(List<Hero> team, List<Monster> enemies);
    public abstract void useUltimate(List<Hero> team, List<Monster> enemies);
}
