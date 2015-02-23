package ruokareseptit.gui;

import java.io.IOException;
import java.util.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.logiikka.LisayksetJaPoistot;
import ruokareseptit.logiikka.StringUtils;
import ruokareseptit.logiikka.Tulostus;
import ruokareseptit.tietokanta.Tietovarasto;

/**
 * Luokassa tapahtuu tekstikäyttöliittymän toiminta
 *
 * @author susisusi
 */
public class Tekstikayttoliittyma {

    private List<Kategoria> kategoriat;
    private Tietovarasto tiedot;
    private Scanner lukija;
    private Tulostus tulostaja;
    private LisayksetJaPoistot lisataan;

    /**
     * Konstuktori alustaa uuden tietovaraston, kategoriat sekä lukijan
     *
     * @param lukija
     */
    public Tekstikayttoliittyma(Scanner lukija) {
        tiedot = new Tietovarasto();
        this.kategoriat = tiedot.haeKategoriat();
        this.lukija = lukija;
        this.tulostaja = new Tulostus(this.kategoriat);
        lisataan = new LisayksetJaPoistot(this.kategoriat, this.tiedot);
    }

    /**
     * Metodi käynnistää sovelluksen ja käyttäjän syötteen mukaan ohjaa
     * oikeisiin metodeihin
     *
     * @throws IOException
     */
    public void kaynnista() throws IOException {

        System.out.println("Ruokareseptit");
        System.out.println("*******************");
        while (true) {
            tulostaToiminnot();
            String vastaus = this.lukija.nextLine();
            if (vastaus.equals("1")) {
                System.out.print("Minkä reseptin haluat hakea? ");
                String resepti = this.lukija.nextLine();
                System.out.println(tulostaja.tulostaResepti(resepti));
            } else if (vastaus.equals("2")) {
                System.out.print("Minkä kategorian haluat hakea? ");
                String haettavaKategoria = this.lukija.nextLine();
                System.out.println("");
                String tulos = tulostaja.tulostaKategorianReseptienNimet(haettavaKategoria);
                System.out.println(tulos);
                if (!tulos.equals("Kategoriaa ei löytynyt tai kategoriassa ei ole reseptejä")) {
                    tulostetaankoKategoriastaResepti(haettavaKategoria);
                }
            } else if (vastaus.equals("3")) {
                lisaaUusiResepti();
            } else if (vastaus.equals("4")) {
                System.out.println(tulostaja.tulostaKaikkiReseptit());
            } else if (vastaus.equals("5")) {
                System.out.println(tulostaja.tulostaKaikkiKategoriat());
            } else if (vastaus.equals("6")) {
                boolean poistuuko = poistaResepti();
                if (poistuuko == false) {
                    System.out.println("Reseptin poisto epäonnistui.");
                } else {
                    System.out.println("Reseptin poisto onnistui.");
                }
            } else if (vastaus.equals("7")) {
                System.out.println("Kiitos ja näkemiin");
                break;
            } else {
                System.out.println("\nSyöte on virheellinen.");
            }
        }

    }

    /**
     * Metodi tulostaa toiminnot, mitä sovelluksessa voidaan tehdä
     */
    private void tulostaToiminnot() {
        System.out.println("\nToiminnot: ");
        System.out.println("********************************");
        System.out.println("  1. Hae resepti");
        System.out.println("  2. Hae kategoria");
        System.out.println("  3. Lisää resepti");
        System.out.println("  4. Tulosta kaikki reseptit");
        System.out.println("  5. Tulosta kaikki kategoriat");
        System.out.println("  6. Poista resepti");
        System.out.println("  7. Lopeta ohjelma");
        System.out.println("********************************");
    }

    private boolean poistaResepti() throws IOException {
        System.out.print("Minkä reseptin haluat poistaa? ");
        String poistettava = this.lukija.nextLine();
        return lisataan.poistaResepti(poistettava);
    }

    private void tulostetaankoKategoriastaResepti(String haettavaKategoria) {
        StringUtils muuttaja = new StringUtils();
        System.out.print("Haluatko tulostaa reseptin kategoriasta " + haettavaKategoria + "? Kirjoita K = kyllä tai E = ei ");
        while (true) {
            String haluaako = this.lukija.nextLine();
            if (muuttaja.sisaltaa("k", haluaako)) {
                System.out.print("Mikä resepti? ");
                String haluttuResepti = this.lukija.nextLine();
                System.out.println(tulostaja.tulostaReseptiTietystaKategoriasta(haettavaKategoria, haluttuResepti));
                break;
            } else if (muuttaja.sisaltaa("e", haluaako)) {
                break;
            } else {
                System.out.println("Syöte on virheellinen. Kirjoita k = kyllä tai e = ei");
            }
        }
    }

    /**
     * Metodi kysyy käyttäjältä tiedot uutta reseptiä varten ja välittää
     * reseptin tiedot eteenpäin, missä se lisätään tiedostoon.
     *
     * @throws IOException
     */
    private void lisaaUusiResepti() throws IOException {
        System.out.println("Mihin kategoriaan uusi resepti kuuluu? Valitse alla olevista vaihtoehdoista \n"
                + "ja kirjoita kategorian nimi.");
        System.out.println("*******************");
        System.out.println(tulostaja.tulostaKaikkiKategoriat());
        System.out.println("*******************");
        String mikaKategoria = "";
        while (true) {
            mikaKategoria = this.lukija.nextLine();
            if (new StringUtils().sisaltaa("LOPETA", mikaKategoria)) {
                return;
            }
            if (lisataan.kirjoitetaankoKategoriaOikein(mikaKategoria)) {
                break;
            } else {
                System.out.println("Syöte on virheellinen. Kirjoita kategoria uudestaan \n tai "
                        + "lopeta uuden reseptin luominen kirjoittamalla: LOPETA");
            }
        }
        System.out.print("Kirjoita reseptin nimi ");
        String reseptinNimi = this.lukija.nextLine();
        Resepti uusiResepti = new Resepti(reseptinNimi);
        System.out.println("Mitä ainesosia reseptiin kuuluu? Kun olet syöttänyt kaikki"
                + " ainesosat, kirjoita LOPETA");
        lisataanKaikkiAinesosat(uusiResepti);
        System.out.println("Miten resepti valmistetaa? Kirjoita ohje 'pötköön', koska"
                + " enterin painallus lopettaa ohjeen kirjoittamisen.");
        String ohje = this.lukija.nextLine();
        uusiResepti.setOhje(ohje);
        boolean onnistuuko = lisataan.lisaaUusiResepti(mikaKategoria, uusiResepti);
        if (onnistuuko == false) {
            System.out.println("\nReseptin lisäys epäonnistui.");
        } else {
            System.out.println("\nReseptin lisäys onnistui!");
        }
    }

    /**
     * Metodi kysyy loopissa käyttäjältä, mitä ainesosia reseptiin tarvitaan ja
     * lisää ainesosan ja määrän reseptiin. Kun käyttäjä kirjoittaa LOPETA,
     * metodi päättyy ja palaa takaisin metodiin lisaaUusiResepti()
     *
     * @param resepti
     */
    private void lisataanKaikkiAinesosat(Resepti resepti) {
        while (true) {
            System.out.print("Ainesosa: ");
            String ainesosa = this.lukija.nextLine();
            if (new StringUtils().sisaltaa("LOPETA", ainesosa)) {
                return;
            }
            System.out.print("Määrä: ");
            String maara = this.lukija.nextLine();
            if (new StringUtils().sisaltaa("LOPETA", maara)) {
                return;
            }
            resepti.setAinesosa(ainesosa, maara);
        }
    }
}
