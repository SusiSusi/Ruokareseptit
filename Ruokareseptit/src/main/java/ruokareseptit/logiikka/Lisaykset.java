package ruokareseptit.logiikka;

import java.io.IOException;
import java.util.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.tietokanta.Tietovarasto;

public class Lisaykset {

    private Scanner lukija;
    private List<Kategoria> kategoriat;
    private Tietovarasto tiedot;

    public Lisaykset(Scanner lukija, List<Kategoria> kategoriat, Tietovarasto tiedot) {
        this.lukija = lukija;
        this.kategoriat = kategoriat;
        this.tiedot = tiedot;
    }

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
                + "enterin painallus lopettaa ohjeen kirjoittamisen.");
        String ohje = this.lukija.nextLine();
        tiedot.lisaaReseptiTiedostoon(kategoria, uusiResepti);
    }

    public void tulostaKaikkiKategoriat() {
        int i = 1;
        for (Kategoria kategoria : this.kategoriat) {
            System.out.println("  " + i + ". " + kategoria.getKategorianNimi());
            i++;
        }
    }

    private String kirjoitetaankoKategoriaOikein() {
        String palautettavaSana = "";
        boolean ok = true;
        while (ok) {
            String mikaKategoria = this.lukija.nextLine();
            if (StringUtils.sisaltaa("LOPETA", mikaKategoria)) {
                palautettavaSana = "LOPETA";
            }
            for (Kategoria kategoria : this.kategoriat) {
                if (StringUtils.sisaltaa(kategoria.getKategorianNimi(), mikaKategoria)) {
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

    private void lisataanKaikkiAinesosat(Resepti resepti) {
        while (true) {
            System.out.print("Ainesosa: ");
            String ainesosa = this.lukija.nextLine();
            System.out.print("Määrä: ");
            String maara = this.lukija.nextLine();
            if (StringUtils.sisaltaa("LOPETA", ainesosa) || StringUtils.sisaltaa("LOPETA", maara)) {
                return;
            }
            resepti.setAinesosa(ainesosa, maara);
        }
    }
}