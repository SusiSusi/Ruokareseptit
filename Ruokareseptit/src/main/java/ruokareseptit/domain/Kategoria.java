package ruokareseptit.domain;

import java.util.*;
import ruokareseptit.logiikka.StringUtils;

/**
 * Luokka luo erilaisia kategorioita, joihin voidaan liittää eri reseptejä
 * @author susisusi
 */

public class Kategoria {

    private String nimi;
    private List<Resepti> reseptit;
    
    /**
     * Konstruktori asettaa kategorialle nimen ja alustaa kategoriaan kuuluvien
     * reseptien listan
     * @param nimi 
     */
    public Kategoria(String nimi) {
        this.nimi = nimi;
        this.reseptit = new ArrayList<>();
    }

    public String getKategorianNimi() {
        return this.nimi;
    }
    
    /**
     * Lisätään kategorian resepti-listaan uusi resepti
     * @param resepti 
     */
    public void lisaaReseptiKategoriaan(Resepti resepti) {
        this.reseptit.add(resepti);
    }

    /**
     * Palauttaa listassa olevien reseptien määrän
     * @return 
     */
    public int reseptienMaaraKategoriassa() {
        return this.reseptit.size();
    }

    public List getKaikkiReseptit() {
        List<Resepti> palautettavaLista = new ArrayList<>();
        for (Resepti resepti : this.reseptit) {
            palautettavaLista.add(resepti);
        }
        return palautettavaLista;
    }

    public List getReseptienNimet() {
        List<String> palautettavaLista = new ArrayList<>();
        for (Resepti resepti : this.reseptit) {
            palautettavaLista.add(resepti.getNimi());
        }
        return palautettavaLista;
    }

    public Resepti getResepti(String nimi) {
        Resepti loytynytResepti = null;
        for (Resepti resep : this.reseptit) {
            if (StringUtils.sisaltaa(resep.getNimi(), nimi)) {
                loytynytResepti = resep;
            }
        }
        return loytynytResepti;
    }
    
    // oma muistutus: tarvitaanko tätä metodia
    public void poistaResepti(String nimi) {
        Iterator<Resepti> iteraattori = this.reseptit.iterator();
        while (iteraattori.hasNext()) {
            if (StringUtils.sisaltaa(iteraattori.next().getNimi(), nimi)) {
                iteraattori.remove();
            }
        }
    }

    /**
     * Poistaa reseptit-listalta kaikki reseptit eli tyhjentää listan
     */
    public void poistaKaikkiReseptit() {
        this.reseptit.clear();
    }
}
