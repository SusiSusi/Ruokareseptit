package ruokareseptit.domain;

import java.util.*;
import ruokareseptit.logiikka.StringUtils;

/**
 * Luokka luo erilaisia kategorioita, joihin voidaan liittää eri reseptejä
 * @author susisusi
 */

public class Kategoria implements Comparable<Kategoria> {

    private String nimi;
    private List<Resepti> reseptit;
    
    /**
     * Konstruktori asettaa kategorialle nimen ja alustaa kategoriaan kuuluvien
     * reseptien listan
     * @param nimi txt-tiedostosta tuleva nimi
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
        jarjestaReseptit();
    }

    /**
     * Palauttaa listassa olevien reseptien määrän
     * @return reseptien määrä
     */
    public int reseptienMaaraKategoriassa() {
        return this.reseptit.size();
    }
    
    /**
     * Metdori järjestää kategorian reseptit aakkosjärjestykseen.
     */
    public void jarjestaReseptit() {
        Collections.sort(this.reseptit);
    }
    
    /**
     * Metodi hakee kaikki kategorian reseptit.
     * @return reseptit listana
     */
    public List getKaikkiReseptit() {
        List<Resepti> palautettavaLista = new ArrayList<>();
        for (Resepti resepti : this.reseptit) {
            palautettavaLista.add(resepti);
        }
        return palautettavaLista;
    }

    /**
     * Metodi hakee kaikki kategorian reseptien nimet.
     * @return reseptien nimet listana
     */
    public List getReseptienNimet() {
        List<String> palautettavaLista = new ArrayList<>();
        for (Resepti resepti : this.reseptit) {
            palautettavaLista.add(resepti.getNimi());
        }
        return palautettavaLista;
    }

    /**
     * Metodi etsii parametrina olevan reseptin
     * @param nimi käyttäjän antama syöte
     * @return resepti-olio
     */
    public Resepti getResepti(String nimi) {
        Resepti loytynytResepti = null;
        for (Resepti resep : this.reseptit) {
            if (new StringUtils().sisaltaa(resep.getNimi(), nimi)) {
                loytynytResepti = resep;
            }
        }
        return loytynytResepti;
    }

    /**
     * Poistaa reseptit-listalta kaikki reseptit eli tyhjentää listan
     */
    public void poistaKaikkiReseptit() {
        this.reseptit.clear();
    }

    @Override
    public int compareTo(Kategoria t) {
        return this.getKategorianNimi().compareTo(t.getKategorianNimi());
    }
}
