package Main;
import java.util.*;

class HeroManager {
    List<Hero> heroList = new ArrayList<>();

    public void addHero(Hero h) {
        heroList.add(h);
        System.out.println(h.name + " added.");
    }

    public void showHeroes() {
        for (int i = 0; i < heroList.size(); i++) {
            Hero h = heroList.get(i);
            System.out.println((i + 1) + ". " + h.name + " [" + h.getClass().getSimpleName() + "]");
        }
    }

    public void deleteHero(int index) {
        if (index >= 0 && index < heroList.size()) {
            System.out.println(heroList.get(index).name + " deleted.");
            heroList.remove(index);
        }
    }

    public List<Hero> getHeroList() {
        return heroList;
    }

    public void editHeroName(int index, String newName) {
        if (index >= 0 && index < heroList.size()) {
            heroList.get(index).name = newName;
        }
    }

    public Hero createHero(String name, HeroType type) {
        if (type == null) return null;

        switch (type) {
            case HUNT:
                return new HuntHero(name);
            case DESTRUCTION:
                return new DestructionHero(name);
            case PRESERVATION:
                return new PreservationHero(name);
            case HEALER:
                return new HealerHero(name);
            case SUPPORT:
                return new SupportHero(name);
            default:
                return null;
        }
    }


}
