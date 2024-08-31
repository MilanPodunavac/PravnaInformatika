package pravna.com.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import pravna.com.myapp.domain.enumeration.BracniStatus;
import pravna.com.myapp.domain.enumeration.ImovinskoStanje;
import pravna.com.myapp.domain.enumeration.Pol;
import pravna.com.myapp.domain.enumeration.TipObrazovanja;

public class OptuzeniFullDTO implements Serializable {

    private String id;

    @NotNull
    private String ime;

    @Pattern(regexp = "^[0-9]{13}")
    private String jmbg;

    private String imeOca;

    private String imeMajke;

    @NotNull
    private Pol pol;

    private LocalDate datumRodjenja;

    private String mestoRodjenja;

    private String drzavaRodjenja;

    private String prebivaliste;

    private String drzavljanstvo;

    private BracniStatus bracniStatus;

    private Integer brojDece;

    private Integer brojMaloletneDece;

    private ImovinskoStanje imovinskoStanje;

    private TipObrazovanja obrazovanje;

    private String zaposlenje;

    private String mestoZaposlenja;

    private PresudaFullDTO[] presude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getImeOca() {
        return imeOca;
    }

    public void setImeOca(String imeOca) {
        this.imeOca = imeOca;
    }

    public String getImeMajke() {
        return imeMajke;
    }

    public void setImeMajke(String imeMajke) {
        this.imeMajke = imeMajke;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getMestoRodjenja() {
        return mestoRodjenja;
    }

    public void setMestoRodjenja(String mestoRodjenja) {
        this.mestoRodjenja = mestoRodjenja;
    }

    public String getDrzavaRodjenja() {
        return drzavaRodjenja;
    }

    public void setDrzavaRodjenja(String drzavaRodjenja) {
        this.drzavaRodjenja = drzavaRodjenja;
    }

    public String getPrebivaliste() {
        return prebivaliste;
    }

    public void setPrebivaliste(String prebivaliste) {
        this.prebivaliste = prebivaliste;
    }

    public String getDrzavljanstvo() {
        return drzavljanstvo;
    }

    public void setDrzavljanstvo(String drzavljanstvo) {
        this.drzavljanstvo = drzavljanstvo;
    }

    public BracniStatus getBracniStatus() {
        return bracniStatus;
    }

    public void setBracniStatus(BracniStatus bracniStatus) {
        this.bracniStatus = bracniStatus;
    }

    public Integer getBrojDece() {
        return brojDece;
    }

    public void setBrojDece(Integer brojDece) {
        this.brojDece = brojDece;
    }

    public Integer getBrojMaloletneDece() {
        return brojMaloletneDece;
    }

    public void setBrojMaloletneDece(Integer brojMaloletneDece) {
        this.brojMaloletneDece = brojMaloletneDece;
    }

    public ImovinskoStanje getImovinskoStanje() {
        return imovinskoStanje;
    }

    public void setImovinskoStanje(ImovinskoStanje imovinskoStanje) {
        this.imovinskoStanje = imovinskoStanje;
    }

    public TipObrazovanja getObrazovanje() {
        return obrazovanje;
    }

    public void setObrazovanje(TipObrazovanja obrazovanje) {
        this.obrazovanje = obrazovanje;
    }

    public String getZaposlenje() {
        return zaposlenje;
    }

    public void setZaposlenje(String zaposlenje) {
        this.zaposlenje = zaposlenje;
    }

    public String getMestoZaposlenja() {
        return mestoZaposlenja;
    }

    public void setMestoZaposlenja(String mestoZaposlenja) {
        this.mestoZaposlenja = mestoZaposlenja;
    }

    public PresudaFullDTO[] getPresude() {
        return presude;
    }

    public void setPresude(PresudaFullDTO[] presude) {
        this.presude = presude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OptuzeniFullDTO)) {
            return false;
        }

        OptuzeniFullDTO optuzeniDTO = (OptuzeniFullDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, optuzeniDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OptuzeniDTO{" +
            "id='" + getId() + "'" +
            ", ime='" + getIme() + "'" +
            ", jmbg='" + getJmbg() + "'" +
            ", imeOca='" + getImeOca() + "'" +
            ", imeMajke='" + getImeMajke() + "'" +
            ", pol='" + getPol() + "'" +
            ", datumRodjenja='" + getDatumRodjenja() + "'" +
            ", mestoRodjenja='" + getMestoRodjenja() + "'" +
            ", drzavaRodjenja='" + getDrzavaRodjenja() + "'" +
            ", prebivaliste='" + getPrebivaliste() + "'" +
            ", drzavljanstvo='" + getDrzavljanstvo() + "'" +
            ", bracniStatus='" + getBracniStatus() + "'" +
            ", brojDece=" + getBrojDece() +
            ", brojMaloletneDece=" + getBrojMaloletneDece() +
            ", imovinskoStanje='" + getImovinskoStanje() + "'" +
            ", obrazovanje='" + getObrazovanje() + "'" +
            ", zaposlenje='" + getZaposlenje() + "'" +
            ", mestoZaposlenja='" + getMestoZaposlenja() + "'" +
            "}";
    }

    public OptuzeniDTO toOptuzeniDTO() {
        OptuzeniDTO optuzeniDTO = new OptuzeniDTO();
        optuzeniDTO.setIme(getIme());
        optuzeniDTO.setJmbg(getJmbg());
        optuzeniDTO.setImeOca(getImeOca());
        optuzeniDTO.setImeMajke(getImeMajke());
        optuzeniDTO.setPol(getPol());
        optuzeniDTO.setDatumRodjenja(getDatumRodjenja());
        optuzeniDTO.setMestoRodjenja(getMestoRodjenja());
        optuzeniDTO.setDrzavaRodjenja(getDrzavaRodjenja());
        optuzeniDTO.setPrebivaliste(getPrebivaliste());
        optuzeniDTO.setDrzavljanstvo(getDrzavljanstvo());
        optuzeniDTO.setBracniStatus(getBracniStatus());
        optuzeniDTO.setBrojDece(getBrojDece());
        optuzeniDTO.setBrojMaloletneDece(getBrojMaloletneDece());
        optuzeniDTO.setImovinskoStanje(getImovinskoStanje());
        optuzeniDTO.setObrazovanje(getObrazovanje());
        optuzeniDTO.setZaposlenje(getZaposlenje());
        optuzeniDTO.setMestoZaposlenja(getMestoZaposlenja());
        return optuzeniDTO;
    }
}
