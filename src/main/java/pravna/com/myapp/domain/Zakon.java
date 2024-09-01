package pravna.com.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Zakon.
 */
@Document(collection = "zakon")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Zakon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("naziv")
    private String naziv;

    @DBRef
    @Field("clanovi")
    @JsonIgnoreProperties(value = { "zakon", "presudes" }, allowSetters = true)
    private Set<ClanZakona> clanovis = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Zakon id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaziv() {
        return this.naziv;
    }

    public Zakon naziv(String naziv) {
        this.setNaziv(naziv);
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<ClanZakona> getClanovis() {
        return this.clanovis;
    }

    public void setClanovis(Set<ClanZakona> clanZakonas) {
        if (this.clanovis != null) {
            this.clanovis.forEach(i -> i.setZakon(null));
        }
        if (clanZakonas != null) {
            clanZakonas.forEach(i -> i.setZakon(this));
        }
        this.clanovis = clanZakonas;
    }

    public Zakon clanovis(Set<ClanZakona> clanZakonas) {
        this.setClanovis(clanZakonas);
        return this;
    }

    public Zakon addClanovi(ClanZakona clanZakona) {
        this.clanovis.add(clanZakona);
        clanZakona.setZakon(this);
        return this;
    }

    public Zakon removeClanovi(ClanZakona clanZakona) {
        this.clanovis.remove(clanZakona);
        clanZakona.setZakon(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Zakon)) {
            return false;
        }
        return id != null && id.equals(((Zakon) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Zakon{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            "}";
    }
}
