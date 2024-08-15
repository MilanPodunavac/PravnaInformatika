package pravna.com.myapp.domain.enumeration;

/**
 * The TipObrazovanja enumeration.
 */
public enum TipObrazovanja {
    NEOBRAZOVAN("Neobrazovan"),
    OSNOVNA_SKOLA("Osnovna škola"),
    SREDNJA_SKOLA("Srednja škola"),
    VISOKA_SKOLA("Visoka škola"),
    FAKULTET("Fakultet");

    private final String value;

    TipObrazovanja(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
