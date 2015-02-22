package ruokareseptit.domain;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruokareseptit.domain.Resepti;
import ruokareseptit.logiikka.StringUtils;

public class ReseptiTest {

    Resepti res;

    @Before
    public void setUp() {
        res = new Resepti("Makaronilaatikko");
        res.setOhje("Vatkaa munat ja lämmitä uuni. Lihat paistetaan jossain"
                + " ja niitä kypsytetään sipulin kanssa pannulla. "
                + "Jos haluat, lisää joukkoon kanelia."
                + " 10min uunissa ja sitten ruoka on valmis.");

        res.setAinesosa("Jauheliha", "400 g");
        res.setAinesosa("Makaroni", "4 dl");
        res.setAinesosa("kahvi", "1 kuppi");
    }

    @Test
    public void ohjelmaEiKaaduJosAinesosiaJaOhjettaEiOleIlmoitettu() {
        List<Ainesosa> osat = new ArrayList<>();
        Resepti kakku = new Resepti("Kakku");
        assertEquals(osat, kakku.getAinesosat());
        assertEquals("Ohjetta ei ole talletettu.", kakku.getOhje());
    }

    @Test
    public void eiTalletaSamaaAinesosaaUudestaan() {
        List<Ainesosa> osat = new ArrayList<>();
        res.setAinesosa("Jauheliha", "100 g");
        osat = res.getAinesosat();
        assertEquals(osat, res.getAinesosat());
    }

    @Test
    public void ohjeTulostuuRiveille() {
        assertEquals("Vatkaa munat ja lämmitä uuni. Lihat paistetaan jossain "
                + "ja niitä kypsytetään sipulin \nkanssa pannulla. Jos halua"
                + "t, lisää joukkoon kanelia. 10min uunissa ja sitten ruoka \n"
                + "on valmis.", new StringUtils().tavutaReseptinOhje(res.getOhje()));
    }

    @Test
    public void ohjeenVoiYlikirjoittaa() {
        res.setOhje("Laita uuni päälle ja tee tarvittavat temput. Katsotaan myös "
                + "että rivityskin menisi kivasti ja mahdolliset viivat yms..");
        assertEquals("Laita uuni päälle ja tee tarvittavat temput. Katsotaan "
                + "myös että rivityskin menisi \nkivasti ja mahdolliset viivat"
                + " yms..", new StringUtils().tavutaReseptinOhje(res.getOhje()));
    }

    @Test
    public void tulostaaNimen() {
        Resepti hauki = new Resepti("");
        assertEquals("", hauki.getNimi());
    }
    
    @Test
    public void asettaaNimenOikein() {
        res.setNimi("Naudan jauheliha");
        assertEquals("Naudan jauheliha", res.getNimi());
    }

    @Test
    public void toStringTulostusToimii() {
        assertEquals("Makaronilaatikko\n"
                + "\n"
                + "Ainesosat: \n"
                + "400 g, Jauheliha\n"
                + "4 dl, Makaroni\n"
                + "1 kuppi, kahvi\n"
                + "\n"
                + "Valmistusohje: \n"
                + "Vatkaa munat ja lämmitä uuni. Lihat paistetaan jossain "
                + "ja niitä kypsytetään sipulin \nkanssa pannulla. Jos halua"
                + "t, lisää joukkoon kanelia. 10min uunissa ja sitten ruoka \n"
                + "on valmis.", res.toString());
    }

//    @Test
//    public void toStringTulostusToimiiVaikkaOhjettaJaAineitaEiOlisi() {
//        List<Ainesosa> osat = new ArrayList<>();
//        Resepti tee = new Resepti("Tee");
//        assertEquals("Tee\n"
//                + "\n"
//                + "Ainesosat: \n"
//                + "Ainesosia ei ole vielä talletettu.\n"
//                + "Ohjetta ei ole talletettu.", tee.toString());
//    }
}
