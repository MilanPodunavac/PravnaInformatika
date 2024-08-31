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
import pravna.com.myapp.domain.enumeration.TipSuda;

/**
 * A Sud.
 */
@Document(collection = "sud")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Sud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("naziv")
    private String naziv;

    @NotNull
    @Field("tip")
    private TipSuda tip;

    @NotNull
    @Field("mesto")
    private String mesto;

    @DBRef
    @Field("presudeSud")
    @JsonIgnoreProperties(
        value = { "radnja", "optuznica", "kaznes", "optuzeni", "sudija", "zapisnicar", "tuzilac", "branilac", "osteceni", "sud" },
        allowSetters = true
    )
    private Set<Presuda> presudeSuds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Sud id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaziv() {
        return this.naziv;
    }

    public Sud naziv(String naziv) {
        this.setNaziv(naziv);
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public TipSuda getTip() {
        return this.tip;
    }

    public Sud tip(TipSuda tip) {
        this.setTip(tip);
        return this;
    }

    public void setTip(TipSuda tip) {
        this.tip = tip;
    }

    public String getMesto() {
        return this.mesto;
    }

    public Sud mesto(String mesto) {
        this.setMesto(mesto);
        return this;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public Set<Presuda> getPresudeSuds() {
        return this.presudeSuds;
    }

    public void setPresudeSuds(Set<Presuda> presudas) {
        if (this.presudeSuds != null) {
            this.presudeSuds.forEach(i -> i.setSud(null));
        }
        if (presudas != null) {
            presudas.forEach(i -> i.setSud(this));
        }
        this.presudeSuds = presudas;
    }

    public Sud presudeSuds(Set<Presuda> presudas) {
        this.setPresudeSuds(presudas);
        return this;
    }

    public Sud addPresudeSud(Presuda presuda) {
        this.presudeSuds.add(presuda);
        presuda.setSud(this);
        return this;
    }

    public Sud removePresudeSud(Presuda presuda) {
        this.presudeSuds.remove(presuda);
        presuda.setSud(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sud)) {
            return false;
        }
        return id != null && id.equals(((Sud) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sud{" +
            "id=" + getId() +
            ", naziv='" + getNaziv() + "'" +
            ", tip='" + getTip() + "'" +
            ", mesto='" + getMesto() + "'" +
            "}";
    }
}
