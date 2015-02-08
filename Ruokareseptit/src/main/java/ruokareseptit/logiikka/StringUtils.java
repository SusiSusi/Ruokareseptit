package ruokareseptit.logiikka;

import java.util.*;
import ruokareseptit.domain.Ainesosa;

public class StringUtils {

    public static boolean sisaltaa(String sana, String haettava) {
        if (sana.isEmpty() || haettava.isEmpty()) {
            return false;
        }
        String trimSana = sana.trim();
        String trimHaettava = haettava.trim(); // poistetaan turhat välilyönnit

        trimSana = trimSana.toUpperCase();
        trimHaettava = trimHaettava.toUpperCase(); // muutetaan kaikki kirjaimet isoiksi
        return trimSana.equals(trimHaettava);
    }

    public static String muutaAinesosatTiedostoonSopiviksi(List<Ainesosa> ainesosat) {
        String muutettu = "";
        for (Ainesosa ainesosa : ainesosat) {
              String maara = ainesosa.getMaara();
              String aines = ainesosa.getNimi();
              muutettu = muutettu + maara + ":" + aines + "\n";
        }
        return muutettu;
    }

}
