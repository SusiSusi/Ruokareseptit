package ruokareseptit.domain;

import java.util.*;
import ruokareseptit.logiikka.StringUtils;

/**
 * Luokka luo reseptejä
 * @author susisusi
 */

public class Resepti {

    private String nimi;
    private List<Ainesosa> ainesosat;
    private String ohje;
    
    /**
     * Konstruktori asettaa reseptille nimen ja alustaa reseptiin kuuluvien 
     * ainesosien listan sekä alustaa ohjeen ensin tyhjänä
     * @param nimi 
     */
    public Resepti(String nimi) {
        this.nimi = nimi;
        this.ainesosat = new ArrayList<>();
        this.ohje = "Ohjetta ei ole talletettu.";
    }

    public String getNimi() {
        return this.nimi;
    }

    public List getAinesosat() {
        List<Ainesosa> palautettavatAinesosat = new ArrayList<>();
        for (Ainesosa osa : this.ainesosat) {
            palautettavatAinesosat.add(osa);
        }
        return palautettavatAinesosat;
    }

    /** 
     * Tulostaa kaikki reseptiin kuuluvat ainesosat - metodia tarvitaan 
     * vai toString()-metodissa
     * @return 
     */
    public String tuoAinesosat() {
        String osat = "";
        for (Ainesosa osa : this.ainesosat) {
                osat = osat + osa + "\n";
        }
        return osat;
    }
    
    /**
     * Palauttaa reseptin ohjeen. Metodi katkaisee rivin 55-merkin jälkeen 
     * ja laittaa tavuviivan mikäli sana on kesken.
     * @return 
     */
    public String getOhje() {
        String muokattuOhje = "";
        if (ohje.equals("Ohjetta ei ole talletettu.")) {
            return this.ohje;
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
    
    /**
     * Asettaa reseptille ainesosan. Metodi pitää huolen, että samaa ainesosaa 
     * ainesosaa ei voi lisätä reseptille kahteen kertaan.
     * @param ainesosa
     * @param maara 
     */
    public void setAinesosa(String ainesosa, String maara) {
        if (!this.ainesosat.isEmpty()) {
            for (Ainesosa osa : this.ainesosat) {
                if (new StringUtils().sisaltaa(osa.getNimi(), ainesosa)) {
                    return;
                }
            }
        }
        this.ainesosat.add(new Ainesosa(ainesosa, maara));
    }

    @Override
    public String toString() {
        return this.nimi + "\n\nAinesosat: \n" + tuoAinesosat() + "\n" + getOhje();
    }
}
