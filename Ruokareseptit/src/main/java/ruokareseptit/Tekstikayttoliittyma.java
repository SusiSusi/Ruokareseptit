package ruokareseptit;

import java.util.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.tietokanta.Tietovarasto;

public class Tekstikayttoliittyma {

    private List<Kategoria> kategoria;
    private Tietovarasto tiedot;
    private Scanner lukija;

    public Tekstikayttoliittyma(Scanner lukija) {
        tiedot = new Tietovarasto();
        this.kategoria = tiedot.haeKategoriat();
        this.lukija = lukija;
    }

    public void kaynnista() {
        System.out.println("Ruokareseptit");
        System.out.println("*******************");
        while (true) {
            tulostaToiminnot();
            String vastaus = this.lukija.nextLine();
            if (vastaus.equals("1")) {
                System.out.print("Minkä reseptin haluat hakea? ");
                String resepti = this.lukija.nextLine();
                tulostaResepti(resepti);
            } else if (vastaus.equals("2")) {
                System.out.print("Minkä kategorian haluat hakea? ");
                String haettavaKategoria = this.lukija.nextLine();
                System.out.println("");
                tulostaKategorianReseptienNimet(haettavaKategoria);
                System.out.println("");
                tulostetaankoKategoriastaResepti(haettavaKategoria);
            } else if (vastaus.equals("3")) {
                System.out.println("Ei toimintoa vielä >:) ");
            } else if (vastaus.equals("4")) {
                tulostaKaikkiReseptit();
            } else if (vastaus.equals("5")) {
                tulostaKaikkiKategoriat();
            } else if (vastaus.equals("6")) {
                System.out.println("Kiitos ja näkemiin");
                break;
            } else {
                System.out.println("\nSyöte on virheellinen.");
            }
        }

    }

    public void tulostaToiminnot() {
        System.out.println("\nToiminnot: ");
        System.out.println("********************************");
        System.out.println("  1. Hae resepti");
        System.out.println("  2. Hae kategoria");
        System.out.println("  3. Lisää resepti");
        System.out.println("  4. Tulosta kaikki reseptit");
        System.out.println("  5. Tulosta kaikki kategoriat");
        System.out.println("  6. Lopeta ohjelma");
        System.out.println("********************************");
    }

    public void tulostaKaikkiReseptit() {
        for (Kategoria kategoriat : this.kategoria) {
            List<Resepti> reseptit = kategoriat.tulostaKaikkiReseptit();
            for (Resepti resepti : reseptit) {
                System.out.println(resepti);
                System.out.println("***********************************");
            }
        }
    }

    public void tulostaKaikkiKategoriat() {
        for (Kategoria kategoriat : this.kategoria) {
            System.out.println(kategoriat.getKategorianNimi() + "\n");
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
            System.out.println(etsittavaResepti + "\n");
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
                for (Kategoria goria : this.kategoria) {
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
