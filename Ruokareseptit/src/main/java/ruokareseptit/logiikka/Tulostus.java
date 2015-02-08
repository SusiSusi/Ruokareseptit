package ruokareseptit.logiikka;

import java.util.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;

public class Tulostus {

    private List<Kategoria> kategoriat;
    private Scanner lukija;

    public Tulostus(List<Kategoria> kategoriat, Scanner lukija) {
        this.kategoriat = kategoriat;
        this.lukija = lukija;
    }

    public void tulostaKaikkiReseptit() {
        for (Kategoria kategoria : this.kategoriat) {
            List<Resepti> reseptit = kategoria.tulostaKaikkiReseptit();
            for (Resepti resepti : reseptit) {
                System.out.println(resepti);
                System.out.println("***********************************");
            }
        }
    }

    public void tulostaKaikkiKategoriat() {
        int i = 1;
        for (Kategoria kategoria : this.kategoriat) {
            System.out.println("  " + i + ". " + kategoria.getKategorianNimi());
            i++;
        }
    }

    public void tulostaResepti(String resepti) {
        Resepti etsittavaResepti = null;
        for (Kategoria kategoria : this.kategoriat) {
            etsittavaResepti = kategoria.tulostaResepti(resepti);
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

    public void tulostaKategorianReseptienNimet(String kategorianNimi) {
        List<String> reseptienNimet = new ArrayList<>();
        for (Kategoria kategoria : this.kategoriat) {
            if (StringUtils.sisaltaa(kategoria.getKategorianNimi(), kategorianNimi)) {
                reseptienNimet = kategoria.tulostaReseptienNimet();
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
                        re = goria.tulostaResepti(haluttuResepti);
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
