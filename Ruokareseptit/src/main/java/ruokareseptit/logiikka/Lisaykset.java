package ruokareseptit.logiikka;

import java.io.IOException;
import java.util.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.tietokanta.Tietovarasto;

/**
 * Luokka kuuntelee uuden reseptin tiedot käyttäjältä ja välittää ne eteenpäin
 * @author susisusi
 */

public class Lisaykset {

    private Scanner lukija;
    private List<Kategoria> kategoriat;
    private Tietovarasto tiedot;
    private StringUtils muuttaja;

    /**
     * Konstuktori saa parametreikseen Tekstikayttoliittyma-luokalta saadut tiedot
     * @param lukija
     * @param kategoriat
     * @param tiedot 
     */
    public Lisaykset(Scanner lukija, List<Kategoria> kategoriat, Tietovarasto tiedot) {
        this.lukija = lukija;
        this.kategoriat = kategoriat;
        this.tiedot = tiedot;
        this.muuttaja = new StringUtils();
    }

    /**
     * Metodi kysyy käyttäjältä tiedot uutta reseptiä varten ja välittää reseptin 
     * tiedot eteenpäin, missä se lisätään tiedostoon.
     * @throws IOException 
     */
    public void lisaaUusiResepti() throws IOException {
        System.out.println("Mihin kategoriaan uusi resepti kuuluu? Valitse alla olevista vaihtoehdoista \n"
                + "ja kirjoita kategorian nimi.");
        System.out.println("*******************");
        tulostaKaikkiKategoriat();
        System.out.println("*******************");
        String kategoria = kirjoitetaankoKategoriaOikein();
        if (kategoria.equals("LOPETA")) {
            return;
        }
        System.out.print("Kirjoita reseptin nimi. ");
        String reseptinNimi = this.lukija.nextLine();
        Resepti uusiResepti = new Resepti(reseptinNimi);
        System.out.println("Mitä ainesosia reseptiin kuuluu? Kun olet syöttänyt kaikki"
                + "ainesosat, kirjoita LOPETA");
        lisataanKaikkiAinesosat(uusiResepti);
        System.out.println("Miten resepti valmistetaa? Kirjoita ohje 'pötköön', koska"
                + " enterin painallus lopettaa ohjeen kirjoittamisen.");
        String ohje = this.lukija.nextLine();
        uusiResepti.setOhje(ohje);
        boolean onnistuuko = tiedot.lisaaReseptiTiedostoon(kategoria, uusiResepti);
        if (onnistuuko == false) {
            System.out.println("Reseptin lisäys epäonnistui.");
            return;
        }
        this.tiedot.poistaKategorioistaReseptit();
        this.tiedot.lisaaKategorioihinReseptit();
    }
    
    /**
     * Tulostetaan kategorioiden nimet numerojärjestyksessä.
     */
    public void tulostaKaikkiKategoriat() {
        int i = 1;
        for (Kategoria kategoria : this.kategoriat) {
            System.out.println("  " + i + ". " + kategoria.getKategorianNimi());
            i++;
        }
    }

    /**
     * Metodi varmistaa, että käyttäjä on syöttänyt kategorian nimen oikein, mihin 
     * resepti tullaan lisäämään. Jos käyttäjä ei ole kirjoittanu kategorian nimeä 
     * oikein, jatkaa metodi kategorian nimen kysymystä niin kauan, kunnes käyttäjä kirjoittaa 
     * kategorian nimen oikein tai kirjoittaa LOPETA. Tämän jälkeen palataan
     * takaisin metodiin lisaaUusiResepti().
     * @return 
     */
    private String kirjoitetaankoKategoriaOikein() {
        String palautettavaSana = "";
        boolean ok = true;
        while (ok) {
            String mikaKategoria = this.lukija.nextLine();
            if (muuttaja.sisaltaa("LOPETA", mikaKategoria)) {
                palautettavaSana = "LOPETA";
            }
            for (Kategoria kategoria : this.kategoriat) {
                if (muuttaja.sisaltaa(kategoria.getKategorianNimi(), mikaKategoria)) {
                    palautettavaSana = kategoria.getKategorianNimi();
                    ok = false;
                }
            }
            if (ok) {
                System.out.println("Syöte on virheellinen. Kirjoita kategoria uudestaan \n tai "
                        + "lopeta uuden reseptin luominen kirjoittamalla: LOPETA");
            }
        }
        return palautettavaSana;
    }

    /**
     * Metodi kysyy loopissa käyttäjältä, mitä ainesosia reseptiin tarvitaan ja 
     * lisää ainesosan ja määrän reseptiin. Kun käyttäjä kirjoittaa LOPETA, 
     * metodi päättyy ja palaa takaisin metodiin lisaaUusiResepti()
     * @param resepti 
     */
    private void lisataanKaikkiAinesosat(Resepti resepti) {
        while (true) {
            System.out.print("Ainesosa: ");
            String ainesosa = this.lukija.nextLine();
            if (muuttaja.sisaltaa("LOPETA", ainesosa)) {
                return;
            }
            System.out.print("Määrä: ");
            String maara = this.lukija.nextLine();
            if (muuttaja.sisaltaa("LOPETA", maara)) {
                return;
            }
            resepti.setAinesosa(ainesosa, maara);
        }
    }
}
