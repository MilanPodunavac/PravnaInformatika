package pravna.com.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Optuznica.
 */
@Document(collection = "optuznica")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Optuznica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("kod")
    private String kod;

    @NotNull
    @Field("datum")
    private LocalDate datum;

    @Field("ustanova")
    private String ustanova;

    @DBRef
    private Presuda presuda;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Optuznica id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKod() {
        return this.kod;
    }

    public Optuznica kod(String kod) {
        this.setKod(kod);
        return this;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public LocalDate getDatum() {
        return this.datum;
    }

    public Optuznica datum(LocalDate datum) {
        this.setDatum(datum);
        return this;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getUstanova() {
        return this.ustanova;
    }

    public Optuznica ustanova(String ustanova) {
        this.setUstanova(ustanova);
        return this;
    }

    public void setUstanova(String ustanova) {
        this.ustanova = ustanova;
    }

    public Presuda getPresuda() {
        return this.presuda;
    }

    public void setPresuda(Presuda presuda) {
        if (this.presuda != null) {
            this.presuda.setOptuznica(null);
        }
        if (presuda != null) {
            presuda.setOptuznica(this);
        }
        this.presuda = presuda;
    }

    public Optuznica presuda(Presuda presuda) {
        this.setPresuda(presuda);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Optuznica)) {
            return false;
        }
        return id != null && id.equals(((Optuznica) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Optuznica{" +
            "id=" + getId() +
            ", kod='" + getKod() + "'" +
            ", datum='" + getDatum() + "'" +
            ", ustanova='" + getUstanova() + "'" +
            "}";
    }
}
