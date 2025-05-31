package Main;

import java.util.*;

class BattleManager {
	public void startBattle(List<Hero> team, int level) {
		List<Monster> monsters = generateMonsters(level);
		Hero.resetTeamSkillPoints();

		List<Object> turnQueue = createTurnQueue(team, monsters);
		Scanner sc = new Scanner(System.in);

		System.out.println("\n-- WAVE " + level + " START --");
		battleLoop(team, monsters, turnQueue, level, sc);
		displayBattleResult(team, level);
	}

	private List<Object> createTurnQueue(List<Hero> team, List<Monster> monsters) {
		List<Object> turnQueue = new ArrayList<>();
		turnQueue.addAll(team);
		turnQueue.addAll(monsters);
		turnQueue.sort(createSpeedComparator());
		return turnQueue;
	}

	private Comparator<Object> createSpeedComparator() {
		return (a, b) -> {
			int sa = (a instanceof Hero) ? ((Hero) a).speed : ((Monster) a).speed;
			int sb = (b instanceof Hero) ? ((Hero) b).speed : ((Monster) b).speed;
			return Integer.compare(sb, sa);
		};
	}

	private void battleLoop(List<Hero> team, List<Monster> monsters, List<Object> turnQueue, int level, Scanner sc) {
		while (isBattleOngoing(team, monsters)) {
			for (Object obj : turnQueue) {
				if (!isBattleOngoing(team, monsters))
					break;
				handleTurn(obj, team, monsters, level, sc);
			}
		}
	}

	private boolean isBattleOngoing(List<Hero> team, List<Monster> monsters) {
		return team.stream().anyMatch(Hero::isAlive) && monsters.stream().anyMatch(Monster::isAlive);
	}

	private void handleTurn(Object obj, List<Hero> team, List<Monster> monsters, int level, Scanner sc) {
		if (obj instanceof Hero) {
			handleHeroTurn((Hero) obj, team, monsters, level, sc);
		} else {
			handleMonsterTurn((Monster) obj, team, level);
		}
	}

	private void handleHeroTurn(Hero hero, List<Hero> team, List<Monster> monsters, int level, Scanner sc) {
		if (!hero.isAlive())
			return;

		String ultStatus = (hero.turnCount % 3 == 0 && hero.turnCount != 0) ? "READY" : "NOT READY";
		System.out.println("\n[Wave " + level + "] " + hero.name + "'s turn [HP: " + hero.hp + "/" + hero.maxHp
				+ ", ULT: " + ultStatus + "]");

		System.out.println("Enter 's' to skip this turn, 'x' to end the game, or any other key to continue:");
		String input = sc.nextLine();

		if (input.equalsIgnoreCase("x")) {
			System.out.println("\nGame ended by player.");
			System.exit(0);
		} else if (!input.equalsIgnoreCase("s")) {
			hero.performAction(team, monsters);
		} else {
			System.out.println(hero.name + " skipped the turn.");
		}
	}

	private void handleMonsterTurn(Monster monster, List<Hero> team, int level) {
		if (!monster.isAlive())
			return;

		for (Hero hero : team) {
			if (hero.isAlive()) {
				System.out.println("\n[Wave " + level + "] " + monster.name + " attacks " + hero.name);
				monster.attackHero(hero);
				break;
			}
		}
	}

	private void displayBattleResult(List<Hero> team, int level) {
		if (team.stream().anyMatch(Hero::isAlive)) {
			System.out.println("\nYou cleared Wave " + level + "!");
		} else {
			System.out.println("\nYour team was defeated at Wave " + level + ".");
		}
	}

	private List<Monster> generateMonsters(int level) {
		List<Monster> monsters = new ArrayList<>();
		for (int i = 0; i < 3 + level / 5; i++) {
			monsters.add(new Monster("Monster Lv" + level + "-" + (i + 1), 80 + level * 10, 20 + level * 2, 5 + level,
					10 + level));
		}
		return monsters;
	}
}





