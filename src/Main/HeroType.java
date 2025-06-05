package Main;

public enum HeroType {
    HUNT, DESTRUCTION, PRESERVATION, HEALER, SUPPORT;

    public static HeroType fromInt(int i) {
        HeroType[] values = HeroType.values();
        return (i >= 1 && i <= values.length) ? values[i - 1] : null;
    }
}
