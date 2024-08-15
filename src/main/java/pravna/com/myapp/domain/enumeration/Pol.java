package pravna.com.myapp.domain.enumeration;

/**
 * The Pol enumeration.
 */
public enum Pol {
    MUSKI("Muški"),
    ZENSKI("Ženski");

    private final String value;

    Pol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
