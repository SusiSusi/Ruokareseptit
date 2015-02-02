package ruokareseptit;

import java.util.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.tietokanta.TietoVarasto;

public class Tekstikayttoliittyma {

    private List<Kategoria> kategoria;
    private TietoVarasto tiedot;

    public Tekstikayttoliittyma() {
        this.kategoria = new ArrayList<Kategoria>();
        tiedot = new TietoVarasto();
    }

    public void kaynnista() {
        tulostaMenu();

    }
    
    public void lisaaKategoriat(Kategoria kategoriaa) {
        this.kategoria.add(kategoriaa);
    }

    public void tulostaMenu() {
        System.out.println("Ruokareseptit");
        System.out.println("*******************");
        System.out.println("Toiminnot: ");
        System.out.println("1. Hae resepti");
        System.out.println("2. Hae kategoria");
        System.out.println("3. Lisää resepti");
        System.out.println("4. Tulosta kaikki reseptit");
    }

    public void tulostaKaikkiReseptit() {
        for (Kategoria kategoriat : this.kategoria) {
            List<Resepti> reseptit = kategoriat.tulostaKaikkiReseptit();
            for (Resepti resepti : reseptit) {
                System.out.println(resepti);
            }
        }
    }

    public void tulostaResepti(String resepti) {
        Resepti etsittavaResepti = null;
        for (Kategoria kategoriat : this.kategoria) {
            etsittavaResepti = kategoriat.tulostaResepti(resepti);
            if (etsittavaResepti != null) {
                break;
            }
        }
        if (etsittavaResepti == null) {
            System.out.println("Reseptiä ei löytynyt");
        } else {
            System.out.println(etsittavaResepti);
        }
    }

    public void tulostaKategorianReseptienNimet(String kategorianNimi) {
        List<String> reseptienNimet = new ArrayList<String>();
        for (Kategoria kategoriat : this.kategoria) {
            if (StringUtils.sisaltaa(kategoriat.getKategorianNimi(), kategorianNimi)) {
                reseptienNimet = kategoriat.tulostaReseptienNimet();
            }
        }
        if (reseptienNimet.isEmpty()) {
            System.out.println("Kategoriaa ei löytynyt");
        } else {
            for (String reseptinNimi : reseptienNimet) {
                System.out.println(reseptinNimi);
            }
        }
    }
}
