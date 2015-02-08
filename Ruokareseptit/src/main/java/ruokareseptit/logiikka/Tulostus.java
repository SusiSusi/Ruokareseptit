package ruokareseptit.logiikka;

import java.util.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;

/**
 * Luokassa tapahtuu tulostus-toiminta
 * @author susisusi
 */

public class Tulostus {

    private List<Kategoria> kategoriat;
    private Scanner lukija;
    
    /**
     * Konstuktori saa parametreikseen Tekstikayttoliittyma-luokalta saadut tiedot
     * @param kategoriat
     * @param lukija 
     */
    public Tulostus(List<Kategoria> kategoriat, Scanner lukija) {
        this.kategoriat = kategoriat;
        this.lukija = lukija;
    }

    /**
     * Metodi tulostaa kaikki sovelluksessa olevat reseptit
     */
    public void tulostaKaikkiReseptit() {
        for (Kategoria kategoria : this.kategoriat) {
            List<Resepti> reseptit = kategoria.getKaikkiReseptit();
            for (Resepti resepti : reseptit) {
                System.out.println(resepti);
                System.out.println("***********************************");
            }
        }
    }

    /**
     * Metodi tulostaa kaikki sovelluksessa olevat kategoriat
     */
    public void tulostaKaikkiKategoriat() {
        int i = 1;
        for (Kategoria kategoria : this.kategoriat) {
            System.out.println("  " + i + ". " + kategoria.getKategorianNimi());
            i++;
        }
    }

    /**
     * Metodi tulostaa parametriksi saadun reseptin tiedot
     * @param resepti 
     */
    public void tulostaResepti(String resepti) {
        Resepti etsittavaResepti = null;
        for (Kategoria kategoria : this.kategoriat) {
            etsittavaResepti = kategoria.getResepti(resepti);
            if (etsittavaResepti != null) {
                break;
            }
        }
        if (etsittavaResepti == null) {
            System.out.println("Reseptiä ei löytynyt");
        } else {
            System.out.println(etsittavaResepti + "\n");
        }
    }

    /**
     * Metodi tulostaa parametriksi saadun kategorian kaikki reseptien nimet
     * @param kategorianNimi 
     */
    public void tulostaKategorianReseptienNimet(String kategorianNimi) {
        List<String> reseptienNimet = new ArrayList<>();
        for (Kategoria kategoria : this.kategoriat) {
            if (StringUtils.sisaltaa(kategoria.getKategorianNimi(), kategorianNimi)) {
                reseptienNimet = kategoria.getReseptienNimet();
            }
        }
        if (reseptienNimet.isEmpty()) {
            System.out.println("Kategoriassa ei ole reseptejä. \n");
        } else {
            for (String reseptinNimi : reseptienNimet) {
                System.out.println("  " + reseptinNimi);
            }
        }
    }

    /**
     * Metodi kysyy käyttäjältä haluaako hän tulostaa jonkun reseptin hakemastaan 
     * kategoriasta. Tätä metodia ennen on aina kutsuttu metodia 
     * void tulostaKategorianReseptienNimet(String kategorianNimi)
     * @param haettavaKategoria 
     */
    public void tulostetaankoKategoriastaResepti(String haettavaKategoria) {
        System.out.print("Haluatko tulostaa reseptin kategoriasta " + haettavaKategoria + "? Kirjoita K = kyllä tai E = ei ");
        while (true) {
            String haluaako = this.lukija.nextLine();
            if (StringUtils.sisaltaa("k", haluaako)) {
                System.out.print("Mikä resepti? ");
                String haluttuResepti = this.lukija.nextLine();
                Resepti re = null;
                for (Kategoria goria : this.kategoriat) {
                    if (StringUtils.sisaltaa(goria.getKategorianNimi(), haettavaKategoria)) {
                        re = goria.getResepti(haluttuResepti);
                        break;
                    }
                }
                if (re == null) {
                    System.out.println("Reseptiä ei löytynyt \n");
                } else {
                    System.out.println(re + "\n");
                }
                break;
            } else if (StringUtils.sisaltaa("e", haluaako)) {
                break;
            } else {
                System.out.println("Syöte on virheellinen. Kirjoita k = kyllä tai e = ei");
            }
        }
    }
}
