package pravna.com.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A ClanZakona.
 */
@Document(collection = "clan_zakona")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClanZakona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("broj")
    private Integer broj;

    @NotNull
    @Field("glava")
    private Integer glava;

    @NotNull
    @Field("naziv")
    private String naziv;

    @Field("tekst")
    private String tekst;

    @DBRef
    @Field("zakon")
    @JsonIgnoreProperties(value = { "clanovis" }, allowSetters = true)
    private Zakon zakon;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ClanZakona id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getBroj() {
        return this.broj;
    }

    public ClanZakona broj(Integer broj) {
        this.setBroj(broj);
        return this;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

    public Integer getGlava() {
        return this.glava;
    }

    public ClanZakona glava(Integer glava) {
        this.setGlava(glava);
        return this;
    }

    public void setGlava(Integer glava) {
        this.glava = glava;
    }

    public String getNaziv() {
        return this.naziv;
    }

    public ClanZakona naziv(String naziv) {
        this.setNaziv(naziv);
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTekst() {
        return this.tekst;
    }

    public ClanZakona tekst(String tekst) {
        this.setTekst(tekst);
        return this;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Zakon getZakon() {
        return this.zakon;
    }

    public void setZakon(Zakon zakon) {
        this.zakon = zakon;
    }

    public ClanZakona zakon(Zakon zakon) {
        this.setZakon(zakon);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClanZakona)) {
            return false;
        }
        return id != null && id.equals(((ClanZakona) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClanZakona{" +
            "id=" + getId() +
            ", broj=" + getBroj() +
            ", glava=" + getGlava() +
            ", naziv='" + getNaziv() + "'" +
            ", tekst='" + getTekst() + "'" +
            "}";
    }
}
