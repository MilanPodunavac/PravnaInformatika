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
 * A Osoba.
 */
@Document(collection = "osoba")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Osoba implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("ime")
    private String ime;

    @DBRef
    @Field("presudeSudija")
    @JsonIgnoreProperties(
        value = { "radnja", "kaznes", "optuzeni", "sudija", "zapisnicar", "tuzilac", "branilac", "veces" },
        allowSetters = true
    )
    private Set<Presuda> presudeSudijas = new HashSet<>();

    @DBRef
    @Field("presudeZapisnicar")
    @JsonIgnoreProperties(
        value = { "radnja", "kaznes", "optuzeni", "sudija", "zapisnicar", "tuzilac", "branilac", "veces" },
        allowSetters = true
    )
    private Set<Presuda> presudeZapisnicars = new HashSet<>();

    @DBRef
    @Field("presudeTuzilac")
    @JsonIgnoreProperties(
        value = { "radnja", "kaznes", "optuzeni", "sudija", "zapisnicar", "tuzilac", "branilac", "veces" },
        allowSetters = true
    )
    private Set<Presuda> presudeTuzilacs = new HashSet<>();

    @DBRef
    @Field("presudeBranilac")
    @JsonIgnoreProperties(
        value = { "radnja", "kaznes", "optuzeni", "sudija", "zapisnicar", "tuzilac", "branilac", "veces" },
        allowSetters = true
    )
    private Set<Presuda> presudeBranilacs = new HashSet<>();

    @DBRef
    @Field("presudeVeces")
    @JsonIgnoreProperties(
        value = { "radnja", "kaznes", "optuzeni", "sudija", "zapisnicar", "tuzilac", "branilac", "veces" },
        allowSetters = true
    )
    private Set<Presuda> presudeVeces = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Osoba id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIme() {
        return this.ime;
    }

    public Osoba ime(String ime) {
        this.setIme(ime);
        return this;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Set<Presuda> getPresudeSudijas() {
        return this.presudeSudijas;
    }

    public void setPresudeSudijas(Set<Presuda> presudas) {
        if (this.presudeSudijas != null) {
            this.presudeSudijas.forEach(i -> i.setSudija(null));
        }
        if (presudas != null) {
            presudas.forEach(i -> i.setSudija(this));
        }
        this.presudeSudijas = presudas;
    }

    public Osoba presudeSudijas(Set<Presuda> presudas) {
        this.setPresudeSudijas(presudas);
        return this;
    }

    public Osoba addPresudeSudija(Presuda presuda) {
        this.presudeSudijas.add(presuda);
        presuda.setSudija(this);
        return this;
    }

    public Osoba removePresudeSudija(Presuda presuda) {
        this.presudeSudijas.remove(presuda);
        presuda.setSudija(null);
        return this;
    }

    public Set<Presuda> getPresudeZapisnicars() {
        return this.presudeZapisnicars;
    }

    public void setPresudeZapisnicars(Set<Presuda> presudas) {
        if (this.presudeZapisnicars != null) {
            this.presudeZapisnicars.forEach(i -> i.setZapisnicar(null));
        }
        if (presudas != null) {
            presudas.forEach(i -> i.setZapisnicar(this));
        }
        this.presudeZapisnicars = presudas;
    }

    public Osoba presudeZapisnicars(Set<Presuda> presudas) {
        this.setPresudeZapisnicars(presudas);
        return this;
    }

    public Osoba addPresudeZapisnicar(Presuda presuda) {
        this.presudeZapisnicars.add(presuda);
        presuda.setZapisnicar(this);
        return this;
    }

    public Osoba removePresudeZapisnicar(Presuda presuda) {
        this.presudeZapisnicars.remove(presuda);
        presuda.setZapisnicar(null);
        return this;
    }

    public Set<Presuda> getPresudeTuzilacs() {
        return this.presudeTuzilacs;
    }

    public void setPresudeTuzilacs(Set<Presuda> presudas) {
        if (this.presudeTuzilacs != null) {
            this.presudeTuzilacs.forEach(i -> i.setTuzilac(null));
        }
        if (presudas != null) {
            presudas.forEach(i -> i.setTuzilac(this));
        }
        this.presudeTuzilacs = presudas;
    }

    public Osoba presudeTuzilacs(Set<Presuda> presudas) {
        this.setPresudeTuzilacs(presudas);
        return this;
    }

    public Osoba addPresudeTuzilac(Presuda presuda) {
        this.presudeTuzilacs.add(presuda);
        presuda.setTuzilac(this);
        return this;
    }

    public Osoba removePresudeTuzilac(Presuda presuda) {
        this.presudeTuzilacs.remove(presuda);
        presuda.setTuzilac(null);
        return this;
    }

    public Set<Presuda> getPresudeBranilacs() {
        return this.presudeBranilacs;
    }

    public void setPresudeBranilacs(Set<Presuda> presudas) {
        if (this.presudeBranilacs != null) {
            this.presudeBranilacs.forEach(i -> i.setBranilac(null));
        }
        if (presudas != null) {
            presudas.forEach(i -> i.setBranilac(this));
        }
        this.presudeBranilacs = presudas;
    }

    public Osoba presudeBranilacs(Set<Presuda> presudas) {
        this.setPresudeBranilacs(presudas);
        return this;
    }

    public Osoba addPresudeBranilac(Presuda presuda) {
        this.presudeBranilacs.add(presuda);
        presuda.setBranilac(this);
        return this;
    }

    public Osoba removePresudeBranilac(Presuda presuda) {
        this.presudeBranilacs.remove(presuda);
        presuda.setBranilac(null);
        return this;
    }

    public Set<Presuda> getPresudeVeces() {
        return this.presudeVeces;
    }

    public void setPresudeVeces(Set<Presuda> presudas) {
        this.presudeVeces = presudas;
    }

    public Osoba presudeVeces(Set<Presuda> presudas) {
        this.setPresudeVeces(presudas);
        return this;
    }

    public Osoba addPresudeVece(Presuda presuda) {
        this.presudeVeces.add(presuda);
        presuda.getVeces().add(this);
        return this;
    }

    public Osoba removePresudeVece(Presuda presuda) {
        this.presudeVeces.remove(presuda);
        presuda.getVeces().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Osoba)) {
            return false;
        }
        return id != null && id.equals(((Osoba) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Osoba{" +
            "id=" + getId() +
            ", ime='" + getIme() + "'" +
            "}";
    }
}
