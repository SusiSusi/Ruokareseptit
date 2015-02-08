package ruokareseptit.logiikka;

import java.io.IOException;
import java.util.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.tietokanta.Tietovarasto;

/**
 * Luokassa tapahtuu tekstikäyttöliittymän toiminta
 * @author susisusi
 */

public class Tekstikayttoliittyma {

    private List<Kategoria> kategoriat;
    private Tietovarasto tiedot;
    private Scanner lukija;

    /**
     * Konstuktori alustaa uuden tietovaraston, kategoriat sekä lukijan
     * @param lukija 
     */
    public Tekstikayttoliittyma(Scanner lukija) {
        tiedot = new Tietovarasto();
        this.kategoriat = tiedot.haeKategoriat();
        this.lukija = lukija;
    }

    /**
     * Metodi käynnistää sovelluksen ja käyttäjän syötteen mukaan ohjaa oikeisiin 
     * metodeihin
     * @throws IOException 
     */
    public void kaynnista() throws IOException {
        Lisaykset lisataan = new Lisaykset(this.lukija, this.kategoriat, this.tiedot);
        Tulostus tulostaja = new Tulostus(this.kategoriat, this.lukija);
        System.out.println("Ruokareseptit");
        System.out.println("*******************");
        while (true) {
            tulostaToiminnot();
            String vastaus = this.lukija.nextLine();
            if (vastaus.equals("1")) {
                System.out.print("Minkä reseptin haluat hakea? ");
                String resepti = this.lukija.nextLine();
//                System.out.println(resepti);
                tulostaja.tulostaResepti(resepti);
            } else if (vastaus.equals("2")) {
                System.out.print("Minkä kategorian haluat hakea? ");
                String haettavaKategoria = this.lukija.nextLine();
                System.out.println("");
                tulostaja.tulostaKategorianReseptienNimet(haettavaKategoria);
                System.out.println("");
                tulostaja.tulostetaankoKategoriastaResepti(haettavaKategoria);
            } else if (vastaus.equals("3")) {

                lisataan.lisaaUusiResepti();
            } else if (vastaus.equals("4")) {
                tulostaja.tulostaKaikkiReseptit();
            } else if (vastaus.equals("5")) {
                tulostaja.tulostaKaikkiKategoriat();
            } else if (vastaus.equals("6")) {
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
}
