package Main;
import java.util.*;

public class HeroBattleUI {
    
    public static void displayTeamStatus(List<Hero> team) {
        System.out.println("\n-- Team Status --");
        for (Hero h : team) {
            h.printStatus();
        }
        System.out.println("-------------------");
    }

    public static void displayTurnInfo(Hero hero) {
        System.out.println("\n-- " + hero.name + "'s turn --");
        System.out.println("Team Skill Points: " + TeamSkillPointManager.getSkillPoints());
        System.out.println("1. Basic Attack\n2. Skill (-1 SP)\n3. Ultimate (requires full gauge)");
    }
}