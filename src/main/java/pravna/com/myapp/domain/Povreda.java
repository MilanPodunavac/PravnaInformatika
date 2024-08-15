package pravna.com.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Povreda.
 */
@Document(collection = "povreda")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Povreda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("oruzje")
    private String oruzje;

    @NotNull
    @Field("deo_tela")
    private String deoTela;

    @NotNull
    @Field("povrede")
    private String povrede;

    @DBRef
    @Field("radnja")
    @JsonIgnoreProperties(value = { "povredes", "presuda" }, allowSetters = true)
    private RadnjaPresude radnja;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Povreda id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOruzje() {
        return this.oruzje;
    }

    public Povreda oruzje(String oruzje) {
        this.setOruzje(oruzje);
        return this;
    }

    public void setOruzje(String oruzje) {
        this.oruzje = oruzje;
    }

    public String getDeoTela() {
        return this.deoTela;
    }

    public Povreda deoTela(String deoTela) {
        this.setDeoTela(deoTela);
        return this;
    }

    public void setDeoTela(String deoTela) {
        this.deoTela = deoTela;
    }

    public String getPovrede() {
        return this.povrede;
    }

    public Povreda povrede(String povrede) {
        this.setPovrede(povrede);
        return this;
    }

    public void setPovrede(String povrede) {
        this.povrede = povrede;
    }

    public RadnjaPresude getRadnja() {
        return this.radnja;
    }

    public void setRadnja(RadnjaPresude radnjaPresude) {
        this.radnja = radnjaPresude;
    }

    public Povreda radnja(RadnjaPresude radnjaPresude) {
        this.setRadnja(radnjaPresude);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Povreda)) {
            return false;
        }
        return id != null && id.equals(((Povreda) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Povreda{" +
            "id=" + getId() +
            ", oruzje='" + getOruzje() + "'" +
            ", deoTela='" + getDeoTela() + "'" +
            ", povrede='" + getPovrede() + "'" +
            "}";
    }
}
