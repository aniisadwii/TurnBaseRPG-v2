package Main;

public enum HeroType {
    HUNT, DESTRUCTION, PRESERVATION, HEALER, SUPPORT;

    public static HeroType fromInt(int i) {
        return switch (i) {
            case 1 -> HUNT;
            case 2 -> DESTRUCTION;
            case 3 -> PRESERVATION;
            case 4 -> HEALER;
            case 5 -> SUPPORT;
            default -> null;
        };
    }
}
