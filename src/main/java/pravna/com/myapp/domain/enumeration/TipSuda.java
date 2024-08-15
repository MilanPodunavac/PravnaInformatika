package pravna.com.myapp.domain.enumeration;

/**
 * The TipSuda enumeration.
 */
public enum TipSuda {
    OSNOVNI("Osnovni"),
    VISI("Viši"),
    APELACIONI("Apelacioni"),
    VRHOVNI("Vrhovni");

    private final String value;

    TipSuda(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
