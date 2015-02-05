package ruokareseptit.domain;

import java.util.*;
import ruokareseptit.logiikka.StringUtils;
import ruokareseptit.domain.Ainesosa;

public class Resepti {

    private String nimi;
    private List<Ainesosa> ainesosat;
    private String ohje;

    public Resepti(String nimi) {
        this.nimi = nimi;
        this.ainesosat = new ArrayList<Ainesosa>();
        this.ohje = "";
    }

    public String getNimi() {
        return this.nimi;
    }

    public List getAinesosat() {
        List<Ainesosa> palautettavatAinesosat = new ArrayList<>();
//        String osat = "";
        for (Ainesosa osa : this.ainesosat) {
//                osat = osat + osa + "\n";
            palautettavatAinesosat.add(osa);
        }
        return palautettavatAinesosat;
    }

    public String getOhje() {
        String muokattuOhje = "";
        if (ohje.isEmpty()) {
            return "Ohjetta ei ole talletettu.";
        } else {
            int pituus = ohje.length();
            int rivinPituus = 55;
            int apu = 0;
            String rivi = "";
            for (int i = 0; i < pituus; i++) {
                rivi = rivi + ohje.charAt(i);
                apu++;
                if (apu == rivinPituus) {
                    if (ohje.charAt(i) == ' ') {
                        muokattuOhje = muokattuOhje + rivi + "\n";
                    } else {
                        muokattuOhje = muokattuOhje + rivi + "-\n";
                    }
                    rivi = "";
                    apu = 0;
                }
            }
            muokattuOhje = muokattuOhje + rivi;
        }
        return muokattuOhje;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setAinesosa(String ainesosa, String maara) {
        if (!this.ainesosat.isEmpty()) {
            for (Ainesosa osa : this.ainesosat) {
                if (StringUtils.sisaltaa(osa.getNimi(), ainesosa)) {
                    return;
                }
            }
        }
        this.ainesosat.add(new Ainesosa(ainesosa, maara));
    }

    public String toString() {
        return this.nimi + "\n\nAinesosat: \n" + getAinesosat() + "\n" + getOhje();
    }
}
