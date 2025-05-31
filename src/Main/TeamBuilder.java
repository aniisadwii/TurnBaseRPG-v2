package Main;
import java.util.*;

class TeamBuilder {
	private String getHeroType(Hero hero) {
        return hero.getClass().getSimpleName();
    }
	
    public List<Hero> chooseTeam(List<Hero> pool, Scanner sc) {
        List<Hero> team = new ArrayList<>();
        while (team.size() < 5) {
            System.out.println("Select hero " + (team.size() + 1) + "/5:");
            for (int i = 0; i < pool.size(); i++) {
                Hero currentHero = pool.get(i);
                System.out.println((i + 1) + ". " + currentHero.name + " [" + getHeroType(currentHero) + "]");
            }
            int choice = sc.nextInt() - 1;
            if (choice >= 0 && choice < pool.size()) {
                team.add(pool.get(choice));
            }
        }
        return team;
    }
}