package ruokareseptit.tietokanta;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import ruokareseptit.logiikka.StringUtils;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;

/**
 * Luokka toimii tietovarastona - kaikki reseptit ja kategoriat ylläpidetään
 * tämän luokan kautta .txt-tiedostoihin
 *
 * @author susisusi
 */
public class Tietovarasto {

    private List<Kategoria> kategoriat;
    private File tiedosto;
    private File tiedostoReseptit;
    private Scanner lukija;

    /**
     * Konstruktori alustaa kategoriat-listan sekä hakee tiedostot
     * Kategoriat.txt ja Reseptit.txt.
     */
    public Tietovarasto() {
        this.kategoriat = new ArrayList<>();
        this.tiedosto = new File("src/main/java/ruokareseptit/tietokanta/Kategoriat.txt");
        this.tiedostoReseptit = new File("src/main/java/ruokareseptit/tietokanta/Reseptit.txt");
        lisaaKategoriat();
        lisaaKategorioihinReseptit();
    }

    /**
     * Konstruktoriin voi antaa muun tiedoston, joka luetaan. Tällä hetkellä
     * tätä käytetään testaukseen.
     *
     * @param tiedosto
     * @param tiedosto2
     */
    public Tietovarasto(String tiedosto, String tiedosto2) {
        this.kategoriat = new ArrayList<>();
        this.tiedosto = new File(tiedosto);
        this.tiedostoReseptit = new File(tiedosto2);
    }

    /**
     * Luetaan tiedosto Kategoriat.txt ja lisätään kaikki tiedostosta löytyvät
     * kategoriat listaan.
     */
    public void lisaaKategoriat() {
        boolean onnistuuko = lataaTiedosto(this.tiedosto);
        if (onnistuuko == false) {
            System.out.println("Virhe kategorioiden lisäämisessä.");
        } else {
            while (lukija.hasNextLine()) {
                String rivi = lukija.nextLine();
                kategoriat.add(new Kategoria(rivi));
            }
        }
    }

    public List haeKategoriat() {
        return this.kategoriat;
    }

    /**
     * Ladataan .txt-tiedosto Scanner lukijaan. Jos lataus ei onnistu,
     * tulostetaan virheen syy.
     *
     * @param tiedosto
     * @return
     */
    public boolean lataaTiedosto(File tiedosto) {
        this.lukija = null;
        try {
            lukija = new Scanner(tiedosto, "UTF-8");
        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen epäonnistui. Virhe: " + e.getMessage());
            return false; // poistutaan metodista
        }
        return true;
    }

    public Kategoria mihinKategoriaanReseptiLisataan(String kategoria) {
        Kategoria palautettavaKategoria = null;
        for (Kategoria kategori : this.kategoriat) {
            if (new StringUtils().sisaltaa(kategori.getKategorianNimi(), kategoria)) {
                palautettavaKategoria = kategori;
                return palautettavaKategoria;
            }
        }
        return palautettavaKategoria; // palauttaa null-arvon
    }

    public void aineidenLisaysReseptiin(Resepti resepti, String aineet) {
        String[] osat = aineet.split(":");
        String maara = osat[0];
        String aine = osat[1];
        resepti.setAinesosa(aine, maara);
    }

    /**
     * Luetaan tiedosto Reseptit.txt ja lisätään lisätään reseptit niille
     * määrättyihin kategorioihin. Tiedostossa lukee, mihin kategoriaan resepti
     * kuuluu.
     */
    public void lisaaKategorioihinReseptit() {
        boolean onnistuuko = lataaTiedosto(this.tiedostoReseptit);
        if (onnistuuko) {
            while (lukija.hasNextLine()) {
                String rivi = lukija.nextLine();
                if (rivi.equals("KATEGORIA")) {
                    String goria = lukija.nextLine();
                    Kategoria kategori = mihinKategoriaanReseptiLisataan(goria);
                    if (kategori == null) {
                        continue;
                    } else {
                        String reseptinNimi = lukija.nextLine();
                        Resepti uusiResepti = new Resepti(reseptinNimi);
                        String aineet = lukija.nextLine();
                        while (!aineet.equals("OHJEET")) {
                            aineidenLisaysReseptiin(uusiResepti, aineet);
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

    /**
     * Metodi poistaa kategorioista kaikki reseptit
     */
    public void poistaKategorioistaReseptit() {
        for (Kategoria kategoria : this.kategoriat) {
            kategoria.poistaKaikkiReseptit();
        }
    }

    /**
     * Lisätään resepti tiedoston Reseptit.txt loppuun.
     *
     * @param kategoria
     * @param resepti
     * @return
     * @throws IOException
     */
    public boolean lisaaReseptiTiedostoon(String kategoria, Resepti resepti) throws IOException {
        try (FileWriter kirjoittaja = new FileWriter(this.tiedostoReseptit, true)) {
            if (this.tiedostoReseptit.length() != 0) {
                kirjoittaja.write("\n");
            }
            kirjoittaja.write("KATEGORIA\n" + kategoria.toUpperCase() + "\n" + resepti.getNimi()
                    + "\n" + new StringUtils().muutaAinesosatTiedostoonSopiviksi(resepti.getAinesosat())
                    + "OHJEET\n" + resepti.getOhje());
        } catch (Exception e) {
//            System.out.println("Reseptin lisääminen epäonnistui: " + e.getMessage());
            return false; // poistutaan metodista
        }
//        poistaKategorioistaReseptit();
//        lisaaKategorioihinReseptit();
        return true;
    }

    public boolean poistaReseptiTiedostosta(String kategoria, Resepti resepti) throws IOException {
        File kopio = this.tiedostoReseptit;
        boolean onnistuuko = lataaTiedosto(kopio);
        if (onnistuuko == false) {
            return false;
        }
        this.tiedostoReseptit.delete();
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            if (rivi.equals("KATEGORIA")) {
                String katego = lukija.nextLine();
                String reseptinNimi = lukija.nextLine();
                if (kategoria.toUpperCase().equals(katego) && reseptinNimi.equals(resepti.getNimi())) {
                    String seuraava = lukija.nextLine();
                    while(!seuraava.equals("OHJEET")){
                        seuraava = lukija.nextLine();
                    }
                    seuraava = lukija.nextLine();
                    // mitä tehdään kun poisto
                } else {
                    Resepti uusiResepti = new Resepti(reseptinNimi);
                    String aineet = lukija.nextLine();
                    while (!aineet.equals("OHJEET")) {
                        aineidenLisaysReseptiin(uusiResepti, aineet);
                        aineet = lukija.nextLine();
                    }
                    String ohjeet = lukija.nextLine();
                    uusiResepti.setOhje(ohjeet);
                    lisaaReseptiTiedostoon(katego, uusiResepti);
                }
            }
        }
        poistaKategorioistaReseptit();
        lisaaKategorioihinReseptit();
        return true;
    }
}
