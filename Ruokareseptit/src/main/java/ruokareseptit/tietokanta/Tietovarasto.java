package ruokareseptit.tietokanta;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import ruokareseptit.logiikka.StringUtils;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;

public class Tietovarasto {

    private List<Kategoria> kategoriat;
    File tiedosto;
    File tiedostoReseptit;
    Scanner lukija;

    public Tietovarasto() {
        this.kategoriat = new ArrayList<>();
        this.tiedosto = new File("src/main/java/ruokareseptit/tietokanta/Kategoriat.txt");
        this.tiedostoReseptit = new File("src/main/java/ruokareseptit/tietokanta/Reseptit.txt");
        lisaaKategoriat();
        lisaaKategorioihinReseptit();
    }

    private void lisaaKategoriat() {
        this.lukija = null;
        try {
            lukija = new Scanner(this.tiedosto, "UTF-8");
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui. Virhe: " + e.getMessage());
            return; // poistutaan metodista
        }
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            kategoriat.add(new Kategoria(rivi));
        }
    }

    public List haeKategoriat() {
        return this.kategoriat;
    }

    public void lisaaKategorioihinReseptit() {
        this.lukija = null;
        try {
            lukija = new Scanner(this.tiedostoReseptit, "UTF-8");
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui. Virhe: " + e.getMessage());
            return; // poistutaan metodista
        }
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            if (rivi.equals("KATEGORIA")) {
                String goria = lukija.nextLine();
                for (Kategoria kategori : this.kategoriat) {
                    if (StringUtils.sisaltaa(kategori.getKategorianNimi(), goria)) {
                        String nimi = lukija.nextLine();
                        Resepti uusiResepti = new Resepti(nimi);
                        String aineet = lukija.nextLine();
                        while (!aineet.equals("OHJEET")) {
                            String[] osat = aineet.split(":");
                            String maara = osat[0];
                            String aine = osat[1];
                            uusiResepti.setAinesosa(aine, maara);
                            aineet = lukija.nextLine();
                        }
                        String ohjeet = lukija.nextLine();
                        uusiResepti.setOhje(ohjeet);
                        kategori.lisaaReseptiKategoriaan(uusiResepti);
                    }
                }
            }
        }
    }

    public void poistaKategorioistaReseptit() {
        for (Kategoria kategoria : this.kategoriat) {
            kategoria.poistaKaikkiReseptit();
        }
    }

    public void lisaaReseptiTiedostoon(String kategoria, Resepti resepti) throws IOException {
        FileWriter kirjoittaja = new FileWriter(this.tiedostoReseptit, true);
        kirjoittaja.write("\nKATEGORIA\n" + kategoria.toUpperCase() + "\n" + resepti.getNimi() + 
                "\n" + StringUtils.muutaAinesosatTiedostoonSopiviksi(resepti.getAinesosat()) + "OHJEET\n" + resepti.getOhje());   
        kirjoittaja.close();
        poistaKategorioistaReseptit();
        lisaaKategorioihinReseptit();
    }
}
