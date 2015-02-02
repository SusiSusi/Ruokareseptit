package ruokareseptit.tietokanta;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import ruokareseptit.StringUtils;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;

public class TietoVarasto {

    private List<Kategoria> kategoriat;
    File tiedosto;
    File tiedostoReseptit;
    Scanner lukija;

    public TietoVarasto() {
        this.kategoriat = new ArrayList<Kategoria>();
        this.tiedosto = new File("src/main/java/ruokareseptit/tietokanta/Kategoriat.txt");
        this.tiedostoReseptit = new File("src/main/java/ruokareseptit/tietokanta/Reseptit.txt");
        lisaaKategoriat();
        lisaaKategoriaanResepti();
    }

    private void lisaaKategoriat() {
        this.lukija = null;
        try {
            lukija = new Scanner(this.tiedosto);
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

    public void lisaaKategoriaanResepti() {
        this.lukija = null;
        try {
            lukija = new Scanner(this.tiedostoReseptit);
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
                        while(!aineet.equals("OHJEET")) {
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

}
