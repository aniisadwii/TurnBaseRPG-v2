package Main;

import java.util.ArrayList;
import java.util.List;

public class BattleContext {
    private final List<Hero> team;
    private final List<Monster> enemies;
    
    public BattleContext(List<Hero> team, List<Monster> enemies) {
        this.team = new ArrayList<>(team);
        this.enemies = new ArrayList<>(enemies);
    }
    
    public List<Hero> getTeam() {
        return team;
    }
    
    public List<Monster> getEnemies() {
        return enemies;
    }
    
    public List<Hero> getAliveTeam() {
        return team.stream().filter(Hero::isAlive).toList();
    }
    
    public List<Monster> getAliveEnemies() {
        return enemies.stream().filter(Monster::isAlive).toList();
    }
    
    public boolean isBattleOngoing() {
        return team.stream().anyMatch(Hero::isAlive) && 
               enemies.stream().anyMatch(Monster::isAlive);
    }
    
    public void removeDeadHero(Hero hero) {
        if (!hero.isAlive()) {
            team.remove(hero);
        }
    }
}
