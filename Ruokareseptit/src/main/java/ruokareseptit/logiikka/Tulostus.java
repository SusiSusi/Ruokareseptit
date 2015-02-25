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
     */
    public Tulostus(List<Kategoria> kategoriat) {
        this.kategoriat = kategoriat;
    }

    /**
     * Metodi hakee kaikki sovelluksessa olevat reseptit ja erottelee reseptit
     * toisistaan "tähtiviivana". Metodi kertoo alussa minkä kategorian
     * reseptejä tulostetaan.
     *
     * @return kaikki tietokannassa olevat reseptit
     */
    public String tulostaKaikkiReseptit() {
        String kaikkiReseptit = "";
        List<Resepti> kaikkiReseptitListalla = new ArrayList<>();
        for (Kategoria kategoria : this.kategoriat) {
            List<Resepti> reseptit = kategoria.getKaikkiReseptit();
            for (Resepti resepti : reseptit) {
                kaikkiReseptitListalla.add(resepti);
            }
        }
        Collections.sort(kaikkiReseptitListalla);
        for (Resepti resepti : kaikkiReseptitListalla) {
            kaikkiReseptit = kaikkiReseptit + resepti;
            kaikkiReseptit = kaikkiReseptit + "\n" + new StringUtils().tulostaTahtia(105) + "\n";
        }
        return kaikkiReseptit;
    }

    /**
     * Metodi hakee kaikki sovelluksessa olevat kategoriat.
     *
     * @return kaikki tietokannassa olevat kategorioiden nimet
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
     * Metodi laittaa kategoriat taulukkoon, mitä käytetään graafisessa
     * käyttöliittymässä.
     *
     * @return kategorioiden nimet taulukossa
     */
    public String[] tulostaKaikkiKategoriatValikkoonSopiviksi() {
        String[] kategoriaValikko = new String[this.kategoriat.size()];
        int i = 0;
        for (Kategoria kategoria : this.kategoriat) {
            kategoriaValikko[i] = kategoria.getKategorianNimi();
            i++;
        }
        return kategoriaValikko;
    }

    /**
     * Metodi hakee parametriksi saadun reseptin tiedot
     *
     * @param resepti Käyttäjän antama syöte
     * @return resepti
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
     * Metodi hakee parametriksi saadun kategorian kaikki reseptien nimet
     *
     * @param kategorianNimi Käyttäjän antama syöte
     * @return kategoriassa olevien reseptien nimet
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
     * Metodi hakee parametrina saadusta kategoriasta reseptin, mitä haetaan.
     *
     * @param haettavaKategoria Käyttäjän antama syöte
     * @param resepti Käyttäjän antama syöte
     * @return resepti
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
