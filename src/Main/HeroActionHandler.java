package Main;
import java.util.*;

public class HeroActionHandler {
    private final Hero hero;
    private final Scanner sc = new Scanner(System.in);

    public HeroActionHandler(Hero hero) {
        this.hero = hero;
    }

    public void handleTurn(List<Hero> team, List<Monster> enemies) {
        HeroBattleUI.displayTeamStatus(team);
        HeroBattleUI.displayTurnInfo(hero);
        
        int choice = getPlayerChoice();
        handleActionChoice(choice, team, enemies);
    }

    private int getPlayerChoice() {
        return sc.nextInt();
    }

    private void handleActionChoice(int choice, List<Hero> team, List<Monster> enemies) {
        switch (choice) {
            case 1 -> handleBasicAttack(enemies);
            case 2 -> handleSkillAction(team, enemies);
            case 3 -> handleUltimateAction(team, enemies);
            default -> handleInvalidChoice(enemies);
        }
    }

    private void handleBasicAttack(List<Monster> enemies) {
        hero.basicAttack(enemies);
        hero.gainUltimateGauge(0.5);
        TeamSkillPointManager.gainSkillPoint();
    }

    private void handleSkillAction(List<Hero> team, List<Monster> enemies) {
        if (TeamSkillPointManager.canUseSkill()) {
            hero.useSkill(team, enemies);
            TeamSkillPointManager.useSkillPoint();
            hero.gainUltimateGauge(1.5);
        } else {
            System.out.println("Not enough Skill Points. Using Basic Attack instead.");
            handleBasicAttack(enemies);
        }
    }

    private void handleUltimateAction(List<Hero> team, List<Monster> enemies) {
        if (hero.isUltimateReady()) {
            hero.useUltimate(team, enemies);
            hero.resetUltimateGauge();
        } else {
            System.out.println("Ultimate not ready! Using Basic Attack instead.");
            handleBasicAttack(enemies);
        }
    }

    private void handleInvalidChoice(List<Monster> enemies) {
        System.out.println("Invalid. Defaulting to Basic Attack.");
        handleBasicAttack(enemies);
    }
}