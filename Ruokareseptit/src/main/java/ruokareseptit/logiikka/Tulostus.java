package ruokareseptit.logiikka;

import java.util.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;

/**
 * Luokassa tapahtuu tulostus-toiminta
 *
 * @author susisusi
 */
public class Tulostus {

    private List<Kategoria> kategoriat;

    /**
     * Konstuktori saa parametreikseen Tekstikayttoliittyma-luokalta saadut
     * tiedot
     *
     * @param kategoriat
     * @param lukija
     */
    public Tulostus(List<Kategoria> kategoriat) {
        this.kategoriat = kategoriat;
    }

    /**
     * Konstruktori testejä varten.
     *
     * @param kategoriat
     */
//    public Tulostus(List<Kategoria> kategoriat) {
//        this.kategoriat = kategoriat;
////    }
    /**
     * Metodi tulostaa kaikki sovelluksessa olevat reseptit
     *
     * @return
     */
    public String tulostaKaikkiReseptit() {
        String kaikkiReseptit = "";
        String tahtia = "";
        for (int i = 0; i < 105; i++) {
            tahtia = tahtia + "*";
        }
        for (Kategoria kategoria : this.kategoriat) {
            List<Resepti> reseptit = kategoria.getKaikkiReseptit();
            for (Resepti resepti : reseptit) {
                kaikkiReseptit = kaikkiReseptit + resepti;
                kaikkiReseptit = kaikkiReseptit + "\n" + tahtia + "\n";
            }
        }
        return kaikkiReseptit;
    }

    /**
     * Metodi tulostaa kaikki sovelluksessa olevat kategoriat
     *
     * @return
     */
    public String tulostaKaikkiKategoriat() {
        int i = 1;
        String kaikkiKategoriat = "";
        for (Kategoria kategoria : this.kategoriat) {
            kaikkiKategoriat = kaikkiKategoriat + "  " + i + ". " + kategoria.getKategorianNimi() + "\n";
            i++;
        }
        return kaikkiKategoriat;
    }

    /**
     * Metodi tulostaa parametriksi saadun reseptin tiedot
     *
     * @param resepti
     * @return
     */
    public String tulostaResepti(String resepti) {
        Resepti etsittavaResepti = null;
        for (Kategoria kategoria : this.kategoriat) {
            etsittavaResepti = kategoria.getResepti(resepti);
            if (etsittavaResepti != null) {
                break;
            }
        }
        if (etsittavaResepti == null) {
            return "Reseptiä ei löytynyt.";
        }
        return etsittavaResepti.toString();
    }

    /**
     * Metodi tulostaa parametriksi saadun kategorian kaikki reseptien nimet
     *
     * @param kategorianNimi
     * @return
     */
    public String tulostaKategorianReseptienNimet(String kategorianNimi) {
        List<String> reseptienNimet = new ArrayList<>();
        String nimet = "";
        for (Kategoria kategoria : this.kategoriat) {
            if (new StringUtils().sisaltaa(kategoria.getKategorianNimi(), kategorianNimi)) {
                reseptienNimet = kategoria.getReseptienNimet();
            }
        }
        if (reseptienNimet.isEmpty()) {
            return "Kategoriaa ei löytynyt tai kategoriassa ei ole reseptejä";
        } else {
            for (String reseptinNimi : reseptienNimet) {
                nimet = nimet + "  " + reseptinNimi + "\n";
            }
        }
        return nimet;
    }

    /**
     * Metodi kysyy käyttäjältä haluaako hän tulostaa jonkun reseptin
     * hakemastaan kategoriasta. Tätä metodia ennen on aina kutsuttu metodia
     * void tulostaKategorianReseptienNimet(String kategorianNimi)
     *
     * @param haettavaKategoria
     * @param resepti
     * @return 
     */
    public String tulostaReseptiTietystaKategoriasta(String haettavaKategoria, String resepti) {
        Resepti loydetty = null;
        for (Kategoria goria : this.kategoriat) {
            if (new StringUtils().sisaltaa(goria.getKategorianNimi(), haettavaKategoria)) {
                loydetty = goria.getResepti(resepti);
                break;
            }
        }
        if (loydetty == null) {
            return "Reseptiä ei löytynyt";
        }
        return loydetty.toString();
    }
}
