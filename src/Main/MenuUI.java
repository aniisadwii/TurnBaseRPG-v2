package Main;

public class MenuUI {
    public static void printMenu() {
        System.out.println("\n-- HERO RPG GAME --");
        for (MenuOption option : MenuOption.values()) {
            if (option != MenuOption.INVALID)
                System.out.println(option.ordinal() + 1 + ". " + formatLabel(option));
        }
        System.out.print("Choose: ");
    }

    private static String formatLabel(MenuOption option) {
        return switch (option) {
            case ADD_HERO -> "Add Hero";
            case SHOW_HEROES -> "Show Heroes";
            case EDIT_HERO -> "Edit Hero";
            case DELETE_HERO -> "Delete Hero";
            case BATTLE -> "Build Team and Battle";
            case EXIT -> "Exit";
            default -> "";
        };
    }
}
