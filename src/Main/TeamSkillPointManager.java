package Main;

public class TeamSkillPointManager {
    private static int teamSkillPoints = 5;
    private static final int MAX_SKILL_POINTS = 5;

    public static void resetTeamSkillPoints() {
        teamSkillPoints = 5;
    }

    public static boolean canUseSkill() {
        return teamSkillPoints > 0;
    }

    public static void useSkillPoint() {
        if (teamSkillPoints > 0) {
            teamSkillPoints--;
        }
    }

    public static void gainSkillPoint() {
        teamSkillPoints = Math.min(MAX_SKILL_POINTS, teamSkillPoints + 1);
    }

    public static int getSkillPoints() {
        return teamSkillPoints;
    }
}