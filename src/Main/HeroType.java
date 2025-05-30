package Main;

public enum HeroType {
    HUNT, DESTRUCTION, PRESERVATION, HEALER, SUPPORT;

    public static HeroType fromInt(int i) {
    	switch (i) {
	        case 1: return HUNT;
	        case 2: return DESTRUCTION;
	        case 3: return PRESERVATION;
	        case 4: return HEALER;
	        case 5: return SUPPORT;
	        default: return null;
    	}

    }
}
