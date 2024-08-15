package pravna.com.myapp.domain.enumeration;

/**
 * The ImovinskoStanje enumeration.
 */
public enum ImovinskoStanje {
    LOSE("Loše"),
    SREDNJE("Srednje"),
    DOBRO("Dobro");

    private final String value;

    ImovinskoStanje(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
