package pravna.com.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A RadnjaPresude.
 */
@Document(collection = "radnja_presude")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RadnjaPresude implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("vreme_radnje")
    private LocalDate vremeRadnje;

    @NotNull
    @Field("mesto_radnje")
    private String mestoRadnje;

    @Field("mesto_smrti")
    private String mestoSmrti;

    @Field("vreme_smrti")
    private LocalDate vremeSmrti;

    @DBRef
    @Field("povrede")
    @JsonIgnoreProperties(value = { "radnja" }, allowSetters = true)
    private Set<Povreda> povredes = new HashSet<>();

    @DBRef
    private Presuda presuda;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public RadnjaPresude id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getVremeRadnje() {
        return this.vremeRadnje;
    }

    public RadnjaPresude vremeRadnje(LocalDate vremeRadnje) {
        this.setVremeRadnje(vremeRadnje);
        return this;
    }

    public void setVremeRadnje(LocalDate vremeRadnje) {
        this.vremeRadnje = vremeRadnje;
    }

    public String getMestoRadnje() {
        return this.mestoRadnje;
    }

    public RadnjaPresude mestoRadnje(String mestoRadnje) {
        this.setMestoRadnje(mestoRadnje);
        return this;
    }

    public void setMestoRadnje(String mestoRadnje) {
        this.mestoRadnje = mestoRadnje;
    }

    public String getMestoSmrti() {
        return this.mestoSmrti;
    }

    public RadnjaPresude mestoSmrti(String mestoSmrti) {
        this.setMestoSmrti(mestoSmrti);
        return this;
    }

    public void setMestoSmrti(String mestoSmrti) {
        this.mestoSmrti = mestoSmrti;
    }

    public LocalDate getVremeSmrti() {
        return this.vremeSmrti;
    }

    public RadnjaPresude vremeSmrti(LocalDate vremeSmrti) {
        this.setVremeSmrti(vremeSmrti);
        return this;
    }

    public void setVremeSmrti(LocalDate vremeSmrti) {
        this.vremeSmrti = vremeSmrti;
    }

    public Set<Povreda> getPovredes() {
        return this.povredes;
    }

    public void setPovredes(Set<Povreda> povredas) {
        if (this.povredes != null) {
            this.povredes.forEach(i -> i.setRadnja(null));
        }
        if (povredas != null) {
            povredas.forEach(i -> i.setRadnja(this));
        }
        this.povredes = povredas;
    }

    public RadnjaPresude povredes(Set<Povreda> povredas) {
        this.setPovredes(povredas);
        return this;
    }

    public RadnjaPresude addPovrede(Povreda povreda) {
        this.povredes.add(povreda);
        povreda.setRadnja(this);
        return this;
    }

    public RadnjaPresude removePovrede(Povreda povreda) {
        this.povredes.remove(povreda);
        povreda.setRadnja(null);
        return this;
    }

    public Presuda getPresuda() {
        return this.presuda;
    }

    public void setPresuda(Presuda presuda) {
        if (this.presuda != null) {
            this.presuda.setRadnja(null);
        }
        if (presuda != null) {
            presuda.setRadnja(this);
        }
        this.presuda = presuda;
    }

    public RadnjaPresude presuda(Presuda presuda) {
        this.setPresuda(presuda);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RadnjaPresude)) {
            return false;
        }
        return id != null && id.equals(((RadnjaPresude) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RadnjaPresude{" +
            "id=" + getId() +
            ", vremeRadnje='" + getVremeRadnje() + "'" +
            ", mestoRadnje='" + getMestoRadnje() + "'" +
            ", mestoSmrti='" + getMestoSmrti() + "'" +
            ", vremeSmrti='" + getVremeSmrti() + "'" +
            "}";
    }
}
