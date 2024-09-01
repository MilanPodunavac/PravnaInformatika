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
import pravna.com.myapp.domain.enumeration.TipPresude;
import pravna.com.myapp.domain.enumeration.TipUbistva;

/**
 * A Presuda.
 */
@Document(collection = "presuda")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Presuda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("datum")
    private LocalDate datum;

    @Field("datum_pritvora")
    private LocalDate datumPritvora;

    @NotNull
    @Field("kod")
    private String kod;

    @NotNull
    @Field("tip")
    private TipPresude tip;

    @Field("broj")
    private Integer broj;

    @Field("godina")
    private Integer godina;

    @Field("pokusaj")
    private Boolean pokusaj;

    @Field("krivica")
    private Boolean krivica;

    @Field("nacin")
    private TipUbistva nacin;

    @DBRef
    @Field("radnja")
    private RadnjaPresude radnja;

    @DBRef
    @Field("optuznica")
    private Optuznica optuznica;

    @DBRef
    @Field("kazne")
    @JsonIgnoreProperties(value = { "presuda" }, allowSetters = true)
    private Set<Kazna> kaznes = new HashSet<>();

    @DBRef
    @Field("veces")
    @JsonIgnoreProperties(
        value = { "presudeSudijas", "presudeZapisnicars", "presudeTuzilacs", "presudeBranilacs", "presudeOstecenis", "presudeVeces" },
        allowSetters = true
    )
    private Set<Osoba> veces = new HashSet<>();

    @DBRef
    @Field("clanoviZakonas")
    @JsonIgnoreProperties(value = { "zakon", "presudes" }, allowSetters = true)
    private Set<ClanZakona> clanoviZakonas = new HashSet<>();

    @DBRef
    @Field("optuzeni")
    @JsonIgnoreProperties(value = { "presudeOptuzenis" }, allowSetters = true)
    private Optuzeni optuzeni;

    @DBRef
    @Field("sudija")
    @JsonIgnoreProperties(
        value = { "presudeSudijas", "presudeZapisnicars", "presudeTuzilacs", "presudeBranilacs", "presudeOstecenis", "presudeVeces" },
        allowSetters = true
    )
    private Osoba sudija;

    @DBRef
    @Field("zapisnicar")
    @JsonIgnoreProperties(
        value = { "presudeSudijas", "presudeZapisnicars", "presudeTuzilacs", "presudeBranilacs", "presudeOstecenis", "presudeVeces" },
        allowSetters = true
    )
    private Osoba zapisnicar;

    @DBRef
    @Field("tuzilac")
    @JsonIgnoreProperties(
        value = { "presudeSudijas", "presudeZapisnicars", "presudeTuzilacs", "presudeBranilacs", "presudeOstecenis", "presudeVeces" },
        allowSetters = true
    )
    private Osoba tuzilac;

    @DBRef
    @Field("branilac")
    @JsonIgnoreProperties(
        value = { "presudeSudijas", "presudeZapisnicars", "presudeTuzilacs", "presudeBranilacs", "presudeOstecenis", "presudeVeces" },
        allowSetters = true
    )
    private Osoba branilac;

    @DBRef
    @Field("osteceni")
    @JsonIgnoreProperties(
        value = { "presudeSudijas", "presudeZapisnicars", "presudeTuzilacs", "presudeBranilacs", "presudeOstecenis", "presudeVeces" },
        allowSetters = true
    )
    private Osoba osteceni;

    @DBRef
    @Field("sud")
    @JsonIgnoreProperties(value = { "presudeSuds" }, allowSetters = true)
    private Sud sud;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Presuda id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return this.datum;
    }

    public Presuda datum(LocalDate datum) {
        this.setDatum(datum);
        return this;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalDate getDatumPritvora() {
        return this.datumPritvora;
    }

    public Presuda datumPritvora(LocalDate datumPritvora) {
        this.setDatumPritvora(datumPritvora);
        return this;
    }

    public void setDatumPritvora(LocalDate datumPritvora) {
        this.datumPritvora = datumPritvora;
    }

    public String getKod() {
        return this.kod;
    }

    public Presuda kod(String kod) {
        this.setKod(kod);
        return this;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public TipPresude getTip() {
        return this.tip;
    }

    public Presuda tip(TipPresude tip) {
        this.setTip(tip);
        return this;
    }

    public void setTip(TipPresude tip) {
        this.tip = tip;
    }

    public Integer getBroj() {
        return this.broj;
    }

    public Presuda broj(Integer broj) {
        this.setBroj(broj);
        return this;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

    public Integer getGodina() {
        return this.godina;
    }

    public Presuda godina(Integer godina) {
        this.setGodina(godina);
        return this;
    }

    public void setGodina(Integer godina) {
        this.godina = godina;
    }

    public Boolean getPokusaj() {
        return this.pokusaj;
    }

    public Presuda pokusaj(Boolean pokusaj) {
        this.setPokusaj(pokusaj);
        return this;
    }

    public void setPokusaj(Boolean pokusaj) {
        this.pokusaj = pokusaj;
    }

    public Boolean getKrivica() {
        return this.krivica;
    }

    public Presuda krivica(Boolean krivica) {
        this.setKrivica(krivica);
        return this;
    }

    public void setKrivica(Boolean krivica) {
        this.krivica = krivica;
    }

    public TipUbistva getNacin() {
        return this.nacin;
    }

    public Presuda nacin(TipUbistva nacin) {
        this.setNacin(nacin);
        return this;
    }

    public void setNacin(TipUbistva nacin) {
        this.nacin = nacin;
    }

    public RadnjaPresude getRadnja() {
        return this.radnja;
    }

    public void setRadnja(RadnjaPresude radnjaPresude) {
        this.radnja = radnjaPresude;
    }

    public Presuda radnja(RadnjaPresude radnjaPresude) {
        this.setRadnja(radnjaPresude);
        return this;
    }

    public Optuznica getOptuznica() {
        return this.optuznica;
    }

    public void setOptuznica(Optuznica optuznica) {
        this.optuznica = optuznica;
    }

    public Presuda optuznica(Optuznica optuznica) {
        this.setOptuznica(optuznica);
        return this;
    }

    public Set<Kazna> getKaznes() {
        return this.kaznes;
    }

    public void setKaznes(Set<Kazna> kaznas) {
        if (this.kaznes != null) {
            this.kaznes.forEach(i -> i.setPresuda(null));
        }
        if (kaznas != null) {
            kaznas.forEach(i -> i.setPresuda(this));
        }
        this.kaznes = kaznas;
    }

    public Presuda kaznes(Set<Kazna> kaznas) {
        this.setKaznes(kaznas);
        return this;
    }

    public Presuda addKazne(Kazna kazna) {
        this.kaznes.add(kazna);
        kazna.setPresuda(this);
        return this;
    }

    public Presuda removeKazne(Kazna kazna) {
        this.kaznes.remove(kazna);
        kazna.setPresuda(null);
        return this;
    }

    public Set<Osoba> getVeces() {
        return this.veces;
    }

    public void setVeces(Set<Osoba> osobas) {
        this.veces = osobas;
    }

    public Presuda veces(Set<Osoba> osobas) {
        this.setVeces(osobas);
        return this;
    }

    public Presuda addVece(Osoba osoba) {
        this.veces.add(osoba);
        osoba.getPresudeVeces().add(this);
        return this;
    }

    public Presuda removeVece(Osoba osoba) {
        this.veces.remove(osoba);
        osoba.getPresudeVeces().remove(this);
        return this;
    }

    public Set<ClanZakona> getClanoviZakonas() {
        return this.clanoviZakonas;
    }

    public void setClanoviZakonas(Set<ClanZakona> clanZakonas) {
        this.clanoviZakonas = clanZakonas;
    }

    public Presuda clanoviZakonas(Set<ClanZakona> clanZakonas) {
        this.setClanoviZakonas(clanZakonas);
        return this;
    }

    public Presuda addClanoviZakona(ClanZakona clanZakona) {
        this.clanoviZakonas.add(clanZakona);
        clanZakona.getPresudes().add(this);
        return this;
    }

    public Presuda removeClanoviZakona(ClanZakona clanZakona) {
        this.clanoviZakonas.remove(clanZakona);
        clanZakona.getPresudes().remove(this);
        return this;
    }

    public Optuzeni getOptuzeni() {
        return this.optuzeni;
    }

    public void setOptuzeni(Optuzeni optuzeni) {
        this.optuzeni = optuzeni;
    }

    public Presuda optuzeni(Optuzeni optuzeni) {
        this.setOptuzeni(optuzeni);
        return this;
    }

    public Osoba getSudija() {
        return this.sudija;
    }

    public void setSudija(Osoba osoba) {
        this.sudija = osoba;
    }

    public Presuda sudija(Osoba osoba) {
        this.setSudija(osoba);
        return this;
    }

    public Osoba getZapisnicar() {
        return this.zapisnicar;
    }

    public void setZapisnicar(Osoba osoba) {
        this.zapisnicar = osoba;
    }

    public Presuda zapisnicar(Osoba osoba) {
        this.setZapisnicar(osoba);
        return this;
    }

    public Osoba getTuzilac() {
        return this.tuzilac;
    }

    public void setTuzilac(Osoba osoba) {
        this.tuzilac = osoba;
    }

    public Presuda tuzilac(Osoba osoba) {
        this.setTuzilac(osoba);
        return this;
    }

    public Osoba getBranilac() {
        return this.branilac;
    }

    public void setBranilac(Osoba osoba) {
        this.branilac = osoba;
    }

    public Presuda branilac(Osoba osoba) {
        this.setBranilac(osoba);
        return this;
    }

    public Osoba getOsteceni() {
        return this.osteceni;
    }

    public void setOsteceni(Osoba osoba) {
        this.osteceni = osoba;
    }

    public Presuda osteceni(Osoba osoba) {
        this.setOsteceni(osoba);
        return this;
    }

    public Sud getSud() {
        return this.sud;
    }

    public void setSud(Sud sud) {
        this.sud = sud;
    }

    public Presuda sud(Sud sud) {
        this.setSud(sud);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Presuda)) {
            return false;
        }
        return id != null && id.equals(((Presuda) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Presuda{" +
            "id=" + getId() +
            ", datum='" + getDatum() + "'" +
            ", datumPritvora='" + getDatumPritvora() + "'" +
            ", kod='" + getKod() + "'" +
            ", tip='" + getTip() + "'" +
            ", broj=" + getBroj() +
            ", godina=" + getGodina() +
            ", pokusaj='" + getPokusaj() + "'" +
            ", krivica='" + getKrivica() + "'" +
            ", nacin='" + getNacin() + "'" +
            "}";
    }
}
