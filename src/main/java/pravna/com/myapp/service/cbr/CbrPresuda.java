package pravna.com.myapp.service.cbr;

import java.util.ArrayList;
import java.util.List;
import pravna.com.myapp.domain.ClanZakona;
import pravna.com.myapp.domain.Kazna;
import pravna.com.myapp.domain.Povreda;
import pravna.com.myapp.domain.Presuda;
import pravna.com.myapp.service.dto.*;
import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class CbrPresuda implements CaseComponent {

    private String id;
    private String sud;
    private String kod;
    private String sudija;
    private String tuzilac;
    private String branilac;
    private String datum;

    private Boolean pokusaj;
    private String tip;
    private String povrede;
    private List<String> clanovi = new ArrayList<String>();

    private List<String> kazne = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSud() {
        return sud;
    }

    public void setSud(String sud) {
        this.sud = sud;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getSudija() {
        return sudija;
    }

    public void setSudija(String sudija) {
        this.sudija = sudija;
    }

    public String getTuzilac() {
        return tuzilac;
    }

    public void setTuzilac(String tuzilac) {
        this.tuzilac = tuzilac;
    }

    public String getBranilac() {
        return branilac;
    }

    public void setBranilac(String branilac) {
        this.branilac = branilac;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Boolean getPokusaj() {
        return pokusaj;
    }

    public void setPokusaj(Boolean pokusaj) {
        this.pokusaj = pokusaj;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPovrede() {
        return povrede;
    }

    public void setPovrede(String povrede) {
        this.povrede = povrede;
    }

    public List<String> getClanovi() {
        return clanovi;
    }

    public void setClanovi(List<String> clanovi) {
        this.clanovi = clanovi;
    }

    public List<String> getKazne() {
        return kazne;
    }

    public void setKazne(List<String> kazne) {
        this.kazne = kazne;
    }

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("id", this.getClass());
    }

    public static CbrPresuda create(PresudaFullDTO presudaFullDTO) {
        CbrPresuda cbrPresuda = new CbrPresuda();

        cbrPresuda.setId(presudaFullDTO.getId());
        cbrPresuda.setSud(presudaFullDTO.getSud() != null ? presudaFullDTO.getSud().getNaziv() : "");
        cbrPresuda.setKod(presudaFullDTO.getKod());
        cbrPresuda.setSudija(presudaFullDTO.getSudija() != null ? presudaFullDTO.getSudija().getIme() : "");
        cbrPresuda.setTuzilac(presudaFullDTO.getTuzilac() != null ? presudaFullDTO.getTuzilac().getIme() : "");
        cbrPresuda.setBranilac(presudaFullDTO.getBranilac() != null ? presudaFullDTO.getBranilac().getIme() : "");
        cbrPresuda.setDatum(presudaFullDTO.getDatum().toString());

        cbrPresuda.setPokusaj(presudaFullDTO.getPokusaj());
        cbrPresuda.setTip(presudaFullDTO.getTip().toString());
        if (presudaFullDTO.getRadnja() != null && presudaFullDTO.getRadnja().getPovrede() != null) {
            //    for (PovredaDTO povreda: presudaFullDTO.getRadnja().getPovrede()) {
            //        cbrPresuda.getPovrede().add(povreda.getDeoTela() + ", " + povreda.getOruzje());
            //    }
            if (presudaFullDTO.getRadnja().getPovrede().length > 1) {
                cbrPresuda.setPovrede("TESKE");
            } else {
                cbrPresuda.setPovrede("LAKE");
            }
        } else {
            cbrPresuda.setPovrede("LAKE");
        }

        if (presudaFullDTO.getClanoviZakonas() != null) {
            for (ClanZakonaDTO clanZakona : presudaFullDTO.getClanoviZakonas()) {
                cbrPresuda
                    .getClanovi()
                    .add(clanZakona.getBroj() + ", " + (clanZakona.getZakon() != null ? clanZakona.getZakon().getNaziv() : "Zakon CG"));
            }
        }
        if (presudaFullDTO.getKazne() != null) {
            for (KaznaDTO kazna : presudaFullDTO.getKazne()) {
                switch (kazna.getTip()) {
                    case ZATVORSKA_KAZNA:
                        cbrPresuda.getKazne().add(kazna.getTip().toString() + ", " + kazna.getDuzinaPritvora());
                    case USLOVNA_KAZNA:
                        cbrPresuda.getKazne().add(kazna.getTip().toString() + ", " + kazna.getDuzinaPritvora());
                    case NOVCANA_KAZNA:
                        cbrPresuda
                            .getKazne()
                            .add(kazna.getTip().toString() + ", " + kazna.getKolicinaNovca() + ", " + kazna.getPrimalacNovca());
                    case IMOVINSKA_KAZNA:
                        cbrPresuda.getKazne().add(kazna.getTip().toString());
                }
            }
        }

        return cbrPresuda;
    }

    public static CbrPresuda create(Presuda presuda, List<Povreda> povrede, List<ClanZakona> clanovi, List<Kazna> kazne) {
        CbrPresuda cbrPresuda = new CbrPresuda();

        cbrPresuda.setId(presuda.getId());
        cbrPresuda.setSud(presuda.getSud() != null ? presuda.getSud().getNaziv() : "");
        cbrPresuda.setKod(presuda.getKod());
        cbrPresuda.setSudija(presuda.getSudija() != null ? presuda.getSudija().getIme() : "");
        cbrPresuda.setTuzilac(presuda.getTuzilac() != null ? presuda.getTuzilac().getIme() : "");
        cbrPresuda.setBranilac(presuda.getBranilac() != null ? presuda.getBranilac().getIme() : "");
        cbrPresuda.setDatum(presuda.getDatum().toString());

        cbrPresuda.setPokusaj(presuda.getPokusaj());
        cbrPresuda.setTip(presuda.getNacin().toString());

        //for (Povreda povreda: povrede) {
        //    cbrPresuda.getPovrede().add(povreda.getDeoTela() + ", " + povreda.getOruzje());
        //}
        if (povrede.size() > 1) {
            cbrPresuda.setPovrede("TESKE");
        } else {
            cbrPresuda.setPovrede("LAKE");
        }
        for (ClanZakona clanZakona : clanovi) {
            cbrPresuda.getClanovi().add(clanZakona.getBroj().toString());
        }
        for (Kazna kazna : kazne) {
            switch (kazna.getTip()) {
                case ZATVORSKA_KAZNA:
                    cbrPresuda.getKazne().add(kazna.getTip().toString() + ", " + kazna.getDuzinaPritvora());
                case USLOVNA_KAZNA:
                    cbrPresuda.getKazne().add(kazna.getTip().toString() + ", " + kazna.getDuzinaPritvora());
                case NOVCANA_KAZNA:
                    cbrPresuda
                        .getKazne()
                        .add(kazna.getTip().toString() + ", " + kazna.getKolicinaNovca() + ", " + kazna.getPrimalacNovca());
                case IMOVINSKA_KAZNA:
                    cbrPresuda.getKazne().add(kazna.getTip().toString());
            }
        }

        return cbrPresuda;
    }
}
