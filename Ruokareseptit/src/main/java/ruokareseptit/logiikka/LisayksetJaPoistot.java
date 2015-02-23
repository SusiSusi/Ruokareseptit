package ruokareseptit.logiikka;

import java.io.IOException;
import java.util.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.tietokanta.Tietovarasto;

/**
 * Luokka kuuntelee uuden reseptin tiedot käyttäjältä ja välittää ne eteenpäin
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
     * Metodi kysyy käyttäjältä tiedot uutta reseptiä varten ja välittää
     * reseptin tiedot eteenpäin, missä se lisätään tiedostoon.
     *
     * @param kategoria
     * @param uusiResepti
     * @return
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
     * @param syote
     * @return
     */
    public boolean kirjoitetaankoKategoriaOikein(String syote) {
        for (Kategoria kategoria : this.kategoriat) {
            if (new StringUtils().sisaltaa(kategoria.getKategorianNimi(), syote)) {
                return true;
            }
        }
        return false;
    }

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
