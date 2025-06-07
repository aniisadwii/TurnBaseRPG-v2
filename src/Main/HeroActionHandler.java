package Main;
import java.util.*;

public class HeroActionHandler {
    private final Hero hero;
    private final Scanner sc = new Scanner(System.in);

    public HeroActionHandler(Hero hero) {
        this.hero = hero;
    }

    public void handleTurn(BattleContext context) {
        HeroBattleUI.displayTeamStatus(context.getTeam());
        HeroBattleUI.displayTurnInfo(hero);
        
        int choice = getPlayerChoice();
        handleActionChoice(choice, context);
    }

    private int getPlayerChoice() {
        return sc.nextInt();
    }

    private void handleActionChoice(int choice, BattleContext context) {
        switch (choice) {
            case 1 -> handleBasicAttack(context);
            case 2 -> handleSkillAction(context);
            case 3 -> handleUltimateAction(context);
            default -> handleInvalidChoice(context);
        }
    }

    private void handleBasicAttack(BattleContext context) {
        hero.basicAttack(context);
        hero.gainUltimateGauge(0.5);
        TeamSkillPointManager.gainSkillPoint();
    }

    private void handleSkillAction(BattleContext context) {
        if (TeamSkillPointManager.canUseSkill()) {
            hero.useSkill(context);
            TeamSkillPointManager.useSkillPoint();
            hero.gainUltimateGauge(1.5);
        } else {
            System.out.println("Not enough Skill Points. Using Basic Attack instead.");
            handleBasicAttack(context);
        }
    }

    private void handleUltimateAction(BattleContext context) {
        if (hero.isUltimateReady()) {
            hero.useUltimate(context);
            hero.resetUltimateGauge();
        } else {
            System.out.println("Ultimate not ready! Using Basic Attack instead.");
            handleBasicAttack(context);
        }
    }

    private void handleInvalidChoice(BattleContext context) {
        System.out.println("Invalid. Defaulting to Basic Attack.");
        handleBasicAttack(context);
    }
}