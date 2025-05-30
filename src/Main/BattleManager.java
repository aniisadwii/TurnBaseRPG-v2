package Main;
import java.util.*;

class BattleManager {
    public void startBattle(List<Hero> team, int level) {
        List<Monster> monsters = generateMonsters(level);
        Hero.resetTeamSkillPoints(); // Reset SP at start of battle

        List<Object> turnQueue = new ArrayList<>();
        turnQueue.addAll(team);
        turnQueue.addAll(monsters);

        turnQueue.sort((a, b) -> {
            int sa = (a instanceof Hero) ? ((Hero) a).speed : ((Monster) a).speed;
            int sb = (b instanceof Hero) ? ((Hero) b).speed : ((Monster) b).speed;
            return Integer.compare(sb, sa);
        });

        Scanner sc = new Scanner(System.in);
        System.out.println("\n-- WAVE " + level + " START --");

        while (team.stream().anyMatch(Hero::isAlive) && monsters.stream().anyMatch(Monster::isAlive)) {
            for (Object obj : turnQueue) {
                if (!team.stream().anyMatch(Hero::isAlive) || !monsters.stream().anyMatch(Monster::isAlive)) break;

                if (obj instanceof Hero) {
                    Hero h = (Hero) obj;
                    if (h.isAlive()) {
                        String ultStatus = (h.turnCount % 3 == 0 && h.turnCount != 0) ? "READY" : "NOT READY";
                        System.out.println("\n[Wave " + level + "] " + h.name + "'s turn [HP: " + h.hp + "/" + h.maxHp + ", ULT: " + ultStatus + "]");
                        System.out.println("Enter 's' to skip this turn, 'x' to end the game, or any other key to continue:");
                        String input = sc.nextLine();
                        if (input.equalsIgnoreCase("x")) {
                            System.out.println("\nGame ended by player.");
                            System.exit(0);
                        } else if (!input.equalsIgnoreCase("s")) {
                            h.performAction(team, monsters);
                        } else {
                            System.out.println(h.name + " skipped the turn.");
                        }
                    }
                } else {
                    Monster m = (Monster) obj;
                    if (m.isAlive()) {
                        for (Hero h : team) {
                            if (h.isAlive()) {
                                System.out.println("\n[Wave " + level + "] " + m.name + " attacks " + h.name);
                                m.attackHero(h);
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (team.stream().anyMatch(Hero::isAlive)) {
            System.out.println("\nYou cleared Wave " + level + "!");
        } else {
            System.out.println("\nYour team was defeated at Wave " + level + ".");
        }
    }

    private List<Monster> generateMonsters(int level) {
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 3 + level / 5; i++) {
            monsters.add(new Monster("Monster Lv" + level + "-" + (i + 1), 80 + level * 10, 20 + level * 2, 5 + level, 10 + level));
        }
        return monsters;
    }
}
