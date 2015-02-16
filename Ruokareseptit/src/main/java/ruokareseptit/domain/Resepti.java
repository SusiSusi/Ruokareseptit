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
    
    public String getOhje() {
        return this.ohje;
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
        String tavutettuOhje = new StringUtils().tavutaReseptinOhje(this.ohje);
        return this.nimi + "\n\nAinesosat: \n" + tuoAinesosat() + "\n" 
                + tavutettuOhje;
    }
}
