package pravna.com.myapp.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
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
    @Field("tip")
    private TipSuda tip;

    @NotNull
    @Field("naselje")
    private String naselje;

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

    public String getNaselje() {
        return this.naselje;
    }

    public Sud naselje(String naselje) {
        this.setNaselje(naselje);
        return this;
    }

    public void setNaselje(String naselje) {
        this.naselje = naselje;
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
            ", tip='" + getTip() + "'" +
            ", naselje='" + getNaselje() + "'" +
            "}";
    }
}
