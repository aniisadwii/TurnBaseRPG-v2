package Main;

public enum MenuOption {
    ADD_HERO(1),
    SHOW_HEROES(2),
    EDIT_HERO(3),
    DELETE_HERO(4),
    BATTLE(5),
    EXIT(6),
    INVALID(-1);

    private final int value;

    MenuOption(int value) {
        this.value = value;
    }

    public static MenuOption fromInt(int input) {
        for (MenuOption option : MenuOption.values()) {
            if (option.value == input) return option;
        }
        return INVALID;
    }
}
