package ruokareseptit.logiikka;

import java.util.*;
import ruokareseptit.domain.Ainesosa;

/**
 * Luokka vertailee sanoja toisiinsa ja muokkaa ainesosa-listan tiedoston 
 * kirjoittamiseen sopivaksi
 * @author susisusi
 */

public class StringUtils {

    /**
     * Metodi vertaa annettuja sanoja toisiinsa tarkoittavatko ne samaa. Metodissa 
     * poistetaan sanojen turhat välilyönnit sekä muutetaan sanojen kirjaimet 
     * isoiksi kirjaimiksi.
     * @param sana
     * @param haettava
     * @return 
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
     * @param ainesosat
     * @return 
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

}
