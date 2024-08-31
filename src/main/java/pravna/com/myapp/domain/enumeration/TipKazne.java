package pravna.com.myapp.domain.enumeration;

/**
 * The TipKazne enumeration.
 */
public enum TipKazne {
    ZATVORSKA_KAZNA("Zatvorska kazna"),
    USLOVNA_KAZNA("Uslovna kazna"),
    NOVCANA_KAZNA("Novčana kazna"),
    IMOVINSKA_KAZNA("Imovinska kazna");

    private final String value;

    TipKazne(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
