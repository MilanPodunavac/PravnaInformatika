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
import pravna.com.myapp.domain.enumeration.BracniStatus;
import pravna.com.myapp.domain.enumeration.ImovinskoStanje;
import pravna.com.myapp.domain.enumeration.Pol;
import pravna.com.myapp.domain.enumeration.TipObrazovanja;

/**
 * A Optuzeni.
 */
@Document(collection = "optuzeni")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Optuzeni implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("ime")
    private String ime;

    @NotNull
    @Pattern(regexp = "^[0-9]{13}")
    @Field("jmbg")
    private String jmbg;

    @Field("ime_oca")
    private String imeOca;

    @Field("ime_majke")
    private String imeMajke;

    @Field("pol")
    private Pol pol;

    @Field("datum_rodjenja")
    private LocalDate datumRodjenja;

    @Field("mesto_rodjenja")
    private String mestoRodjenja;

    @Field("drzava_rodjenja")
    private String drzavaRodjenja;

    @Field("prebivaliste")
    private String prebivaliste;

    @Field("bracni_status")
    private BracniStatus bracniStatus;

    @Field("broj_dece")
    private Integer brojDece;

    @Field("broj_maloletne_dece")
    private Integer brojMaloletneDece;

    @Field("imovinsko_stanje")
    private ImovinskoStanje imovinskoStanje;

    @Field("obrazovanje")
    private TipObrazovanja obrazovanje;

    @Field("zaposlenje")
    private String zaposlenje;

    @Field("mesto_zaposlenja")
    private String mestoZaposlenja;

    @DBRef
    @Field("presudeOptuzeni")
    @JsonIgnoreProperties(
        value = { "radnja", "kaznes", "optuzeni", "sudija", "zapisnicar", "tuzilac", "branilac", "veces" },
        allowSetters = true
    )
    private Set<Presuda> presudeOptuzenis = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Optuzeni id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIme() {
        return this.ime;
    }

    public Optuzeni ime(String ime) {
        this.setIme(ime);
        return this;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getJmbg() {
        return this.jmbg;
    }

    public Optuzeni jmbg(String jmbg) {
        this.setJmbg(jmbg);
        return this;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getImeOca() {
        return this.imeOca;
    }

    public Optuzeni imeOca(String imeOca) {
        this.setImeOca(imeOca);
        return this;
    }

    public void setImeOca(String imeOca) {
        this.imeOca = imeOca;
    }

    public String getImeMajke() {
        return this.imeMajke;
    }

    public Optuzeni imeMajke(String imeMajke) {
        this.setImeMajke(imeMajke);
        return this;
    }

    public void setImeMajke(String imeMajke) {
        this.imeMajke = imeMajke;
    }

    public Pol getPol() {
        return this.pol;
    }

    public Optuzeni pol(Pol pol) {
        this.setPol(pol);
        return this;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public LocalDate getDatumRodjenja() {
        return this.datumRodjenja;
    }

    public Optuzeni datumRodjenja(LocalDate datumRodjenja) {
        this.setDatumRodjenja(datumRodjenja);
        return this;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getMestoRodjenja() {
        return this.mestoRodjenja;
    }

    public Optuzeni mestoRodjenja(String mestoRodjenja) {
        this.setMestoRodjenja(mestoRodjenja);
        return this;
    }

    public void setMestoRodjenja(String mestoRodjenja) {
        this.mestoRodjenja = mestoRodjenja;
    }

    public String getDrzavaRodjenja() {
        return this.drzavaRodjenja;
    }

    public Optuzeni drzavaRodjenja(String drzavaRodjenja) {
        this.setDrzavaRodjenja(drzavaRodjenja);
        return this;
    }

    public void setDrzavaRodjenja(String drzavaRodjenja) {
        this.drzavaRodjenja = drzavaRodjenja;
    }

    public String getPrebivaliste() {
        return this.prebivaliste;
    }

    public Optuzeni prebivaliste(String prebivaliste) {
        this.setPrebivaliste(prebivaliste);
        return this;
    }

    public void setPrebivaliste(String prebivaliste) {
        this.prebivaliste = prebivaliste;
    }

    public BracniStatus getBracniStatus() {
        return this.bracniStatus;
    }

    public Optuzeni bracniStatus(BracniStatus bracniStatus) {
        this.setBracniStatus(bracniStatus);
        return this;
    }

    public void setBracniStatus(BracniStatus bracniStatus) {
        this.bracniStatus = bracniStatus;
    }

    public Integer getBrojDece() {
        return this.brojDece;
    }

    public Optuzeni brojDece(Integer brojDece) {
        this.setBrojDece(brojDece);
        return this;
    }

    public void setBrojDece(Integer brojDece) {
        this.brojDece = brojDece;
    }

    public Integer getBrojMaloletneDece() {
        return this.brojMaloletneDece;
    }

    public Optuzeni brojMaloletneDece(Integer brojMaloletneDece) {
        this.setBrojMaloletneDece(brojMaloletneDece);
        return this;
    }

    public void setBrojMaloletneDece(Integer brojMaloletneDece) {
        this.brojMaloletneDece = brojMaloletneDece;
    }

    public ImovinskoStanje getImovinskoStanje() {
        return this.imovinskoStanje;
    }

    public Optuzeni imovinskoStanje(ImovinskoStanje imovinskoStanje) {
        this.setImovinskoStanje(imovinskoStanje);
        return this;
    }

    public void setImovinskoStanje(ImovinskoStanje imovinskoStanje) {
        this.imovinskoStanje = imovinskoStanje;
    }

    public TipObrazovanja getObrazovanje() {
        return this.obrazovanje;
    }

    public Optuzeni obrazovanje(TipObrazovanja obrazovanje) {
        this.setObrazovanje(obrazovanje);
        return this;
    }

    public void setObrazovanje(TipObrazovanja obrazovanje) {
        this.obrazovanje = obrazovanje;
    }

    public String getZaposlenje() {
        return this.zaposlenje;
    }

    public Optuzeni zaposlenje(String zaposlenje) {
        this.setZaposlenje(zaposlenje);
        return this;
    }

    public void setZaposlenje(String zaposlenje) {
        this.zaposlenje = zaposlenje;
    }

    public String getMestoZaposlenja() {
        return this.mestoZaposlenja;
    }

    public Optuzeni mestoZaposlenja(String mestoZaposlenja) {
        this.setMestoZaposlenja(mestoZaposlenja);
        return this;
    }

    public void setMestoZaposlenja(String mestoZaposlenja) {
        this.mestoZaposlenja = mestoZaposlenja;
    }

    public Set<Presuda> getPresudeOptuzenis() {
        return this.presudeOptuzenis;
    }

    public void setPresudeOptuzenis(Set<Presuda> presudas) {
        if (this.presudeOptuzenis != null) {
            this.presudeOptuzenis.forEach(i -> i.setOptuzeni(null));
        }
        if (presudas != null) {
            presudas.forEach(i -> i.setOptuzeni(this));
        }
        this.presudeOptuzenis = presudas;
    }

    public Optuzeni presudeOptuzenis(Set<Presuda> presudas) {
        this.setPresudeOptuzenis(presudas);
        return this;
    }

    public Optuzeni addPresudeOptuzeni(Presuda presuda) {
        this.presudeOptuzenis.add(presuda);
        presuda.setOptuzeni(this);
        return this;
    }

    public Optuzeni removePresudeOptuzeni(Presuda presuda) {
        this.presudeOptuzenis.remove(presuda);
        presuda.setOptuzeni(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Optuzeni)) {
            return false;
        }
        return id != null && id.equals(((Optuzeni) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Optuzeni{" +
            "id=" + getId() +
            ", ime='" + getIme() + "'" +
            ", jmbg='" + getJmbg() + "'" +
            ", imeOca='" + getImeOca() + "'" +
            ", imeMajke='" + getImeMajke() + "'" +
            ", pol='" + getPol() + "'" +
            ", datumRodjenja='" + getDatumRodjenja() + "'" +
            ", mestoRodjenja='" + getMestoRodjenja() + "'" +
            ", drzavaRodjenja='" + getDrzavaRodjenja() + "'" +
            ", prebivaliste='" + getPrebivaliste() + "'" +
            ", bracniStatus='" + getBracniStatus() + "'" +
            ", brojDece=" + getBrojDece() +
            ", brojMaloletneDece=" + getBrojMaloletneDece() +
            ", imovinskoStanje='" + getImovinskoStanje() + "'" +
            ", obrazovanje='" + getObrazovanje() + "'" +
            ", zaposlenje='" + getZaposlenje() + "'" +
            ", mestoZaposlenja='" + getMestoZaposlenja() + "'" +
            "}";
    }
}
