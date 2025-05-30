package Main;
import java.util.*;

class DummyHeroFactory {
    public static List<Hero> createFiveTypeHeroes() {
        List<Hero> heroes = new ArrayList<>();
        heroes.add(new HuntHero("HawkEye"));           // Single target DPS
        heroes.add(new DestructionHero("Blaze"));      // AoE attacker
        heroes.add(new PreservationHero("Guardian"));  // Tank
        heroes.add(new HealerHero("Grace"));           // Healer
        heroes.add(new SupportHero("Echo"));           // Buffer
        return heroes;
    }
}
