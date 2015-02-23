package ruokareseptit.logiikka;

import java.io.IOException;
import java.util.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.tietokanta.Tietovarasto;

/**
 * Luokka käsittelee reseptien lisäyksiä ja poistoja
 *
 * @author susisusi
 */
public class LisayksetJaPoistot {

    private List<Kategoria> kategoriat;
    private Tietovarasto tiedot;

    /**
     * Konstuktori saa parametreikseen Tekstikayttoliittyma-luokalta saadut
     * tiedot
     *
     * @param kategoriat
     * @param tiedot
     */
    public LisayksetJaPoistot(List<Kategoria> kategoriat, Tietovarasto tiedot) {
        this.kategoriat = kategoriat;
        this.tiedot = tiedot;

    }

    /**
     * Metodi varmistaa, että parametrit eivät ole tyhjiä ja välittää tiedot eteenpäin.
     * Jos reseptin lisäys tiedostoon onnistuu, antaa metodi käskyn poistaa kaikki reseptit 
     * kategorioista ja lisätä ne uudelleen.
     *
     * @param kategoria Käyttäjän antama syöte
     * @param uusiResepti Käyttääjän antama syöte
     * @return totuusarvon, onnistuuko reseptin lisäys vai ei
     * @throws IOException
     */
    public boolean lisaaUusiResepti(String kategoria, Resepti uusiResepti) throws IOException {
        if (kategoria.isEmpty() || uusiResepti.getNimi().isEmpty()) {
            return false;
        }
        boolean onnistuuko = tiedot.lisaaReseptiTiedostoon(kategoria, uusiResepti);
        if (onnistuuko == false) {
            return false;
        }
        this.tiedot.poistaKategorioistaReseptit();
        this.tiedot.lisaaKategorioihinReseptit();
        return true;
    }

    /**
     * Metodi varmistaa, että käyttäjä on syöttänyt kategorian nimen oikein,
     * mihin resepti tullaan lisäämään.
     *
     * @param syote käyttäjän antama syöte
     * @return totuusarvo
     */
    public boolean kirjoitetaankoKategoriaOikein(String syote) {
        for (Kategoria kategoria : this.kategoriat) {
            if (new StringUtils().sisaltaa(kategoria.getKategorianNimi(), syote)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi saa parametrinaan taulukon ainesosista ja kerää ainesosien tiedot. Jos käyttäjä 
     * ei ole erottanut määrää pilkulla, laittaa metodi ainesosan määräksi "-". Tätä metodia 
     * käytetään graafisen käyttöliittymän kanssa.
     * @param resepti Käyttäjän syöte
     * @param ainesosat Käyttäjän syöte
     */
    public void lisaaReseptiinAinesosat(Resepti resepti, String[] ainesosat) {
        for (int i = 0; i < ainesosat.length; i++) {
            if (!ainesosat[i].contains(",")) {
                resepti.setAinesosa(ainesosat[i], "-");
            } else {
                String[] osat = ainesosat[i].split(",");
                String maara = osat[0];
                String aine = osat[1];
                resepti.setAinesosa(aine, maara);
            }
        }
    }

    /**
     * Metodi etsii kategorian, missä resepti on ja välittää käskyn, että tiedostosta 
     * poistetaan kyseinen resepti.
     * @param reseptinNimi Käyttäjän antama syöte
     * @return totuusarvon, onnistuiko poisto vai ei
     * @throws IOException 
     */
    public boolean poistaResepti(String reseptinNimi) throws IOException {
        for (Kategoria kateg : this.kategoriat) {
            List<Resepti> reseptit = kateg.getKaikkiReseptit();
            for (Resepti resepti : reseptit) {
                if (new StringUtils().sisaltaa(resepti.getNimi(), reseptinNimi)) {
//                    return this.tiedot.poistaReseptiTiedostosta(resepti);
                    return this.tiedot.poistaReseptiTiedostosta(kateg.getKategorianNimi(), resepti);
                }
            }
        }
        return false;
    }
}
