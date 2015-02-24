package ruokareseptit.logiikka;

import java.util.*;
import ruokareseptit.domain.Ainesosa;

/**
 * Luokka vertailee sanoja toisiinsa ja muokkaa ainesosa-listan tiedoston
 * kirjoittamiseen sopivaksi sekä tavuttaa reseptin ohjeen. 
 * "Kielenhoito"-luokka
 *
 * @author susisusi
 */
public class StringUtils {

    /**
     * Metodi vertaa annettuja sanoja toisiinsa tarkoittavatko ne samaa.
     * Metodissa poistetaan sanojen turhat välilyönnit sekä muutetaan sanojen
     * kirjaimet isoiksi kirjaimiksi.
     *
     * @param sana
     * @param haettava Käyttäjän antama syöte
     * @return totuusarvon, onko sana sama vai ei
     */
    public boolean sisaltaa(String sana, String haettava) {
        if (sana.isEmpty() || haettava.isEmpty()) {
            return false;
        }
        String trimSana = sana.trim();
        String trimHaettava = haettava.trim(); // poistetaan turhat välilyönnit

        trimSana = trimSana.toUpperCase();
        trimHaettava = trimHaettava.toUpperCase(); // muutetaan kaikki kirjaimet isoiksi
        return trimSana.equals(trimHaettava);
    }

    /**
     * Metodi muuttaa annetun ainesosa-listan tiedostoon sopivaksi. Tiedostoon
     * kirjoittaminen edellyttää, että ainesosat ovat kirjoitettu muotoon
     * maara:aines, esim. 1 kpl:sipuli
     *
     * @param ainesosat
     * @return ainesosat String-muodossa
     */
    public String muutaAinesosatTiedostoonSopiviksi(List<Ainesosa> ainesosat) {
        String muutettu = "";
        for (Ainesosa ainesosa : ainesosat) {
            String maara = ainesosa.getMaara();
            String aines = ainesosa.getNimi();
            muutettu = muutettu + maara + ":" + aines + "\n";
        }
        return muutettu;
    }

    /**
     * Palauttaa rivitetyn version reseptin ohjeesta. Kun rivin pituus on 
     * 80-merkkiä, tulostaa metodi riville 80-merkissä olevan viimeisen sanan ja 
     * aloittaa uuden rivin eli metodi ei katkaise sanoja vaan rivi katkeaa kun seuraava
     * merkki on tyhjä.
     * @param ohje 
     * @return muokatun ohjeen
     */
    public String tavutaReseptinOhje(String ohje) {
        String muokattuOhje = "";
        if (ohje.equals("Ohjetta ei ole talletettu.")) {
            return ohje;
        } else if (ohje.equals("Kirjoita tähän valmistusohje")) {
            return "Ohjetta ei ole talletettu.";            
        }
        
        else {
            int pituus = ohje.length();
            int rivinPituus = 80;
            int apu = 0;
            String rivi = "";
            for (int i = 0; i < pituus; i++) {
                rivi = rivi + ohje.charAt(i);
                apu++;
                if (apu >= rivinPituus && ohje.charAt(i) == ' ') {
                        muokattuOhje = muokattuOhje + rivi + "\n";
                    rivi = "";
                    apu = 0;
                }
            }
            muokattuOhje = muokattuOhje + rivi;
        }
        return muokattuOhje;
    }  

}
