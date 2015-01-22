package ruokareseptit;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruokareseptit.Resepti;

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
        Resepti kakku = new Resepti("Kakku");
        assertEquals("Ainesosia ei ole vielä talletettu.", kakku.getAinesosat());
        assertEquals("Ohjetta ei ole talletettu.", kakku.getOhje());
    }

    @Test
    public void eiTalletaSamaaAinesosaaUudestaan() {
        res.setAinesosa("Jauheliha", "100 g");
        assertEquals("Jauheliha, 400 g\nMakaroni, 4 dl\nkahvi, 1 kuppi\n", res.getAinesosat());
    }

    @Test
    public void ohjeTulostuuRiveille() {
        assertEquals("Vatkaa munat ja lämmitä uuni. Lihat paistetaan jossain \n"
                + "ja niitä kypsytetään sipulin kanssa pannulla. Jos halua-\n"
                + "t, lisää joukkoon kanelia. 10min uunissa ja sitten ruok-\n"
                + "a on valmis.", res.getOhje());
    }

    @Test
    public void ohjeenVoiYlikirjoittaa() {
        res.setOhje("Laita uuni päälle ja tee tarvittavat temput. Katsotaan myös "
                + "että rivityskin menisi kivasti ja mahdolliset viivat yms..");
        assertEquals("Laita uuni päälle ja tee tarvittavat temput. Katsotaan \n"
                + "myös että rivityskin menisi kivasti ja mahdolliset viiv-\n"
                + "at yms..", res.getOhje());
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
                + "Jauheliha, 400 g\n"
                + "Makaroni, 4 dl\n"
                + "kahvi, 1 kuppi\n"
                + "\n"
                + "Vatkaa munat ja lämmitä uuni. Lihat paistetaan jossain \n"
                + "ja niitä kypsytetään sipulin kanssa pannulla. Jos halua-\n"
                + "t, lisää joukkoon kanelia. 10min uunissa ja sitten ruok-\n"
                + "a on valmis.", res.toString());
    }

    @Test
    public void toStringTulostusToimiiVaikkaOhjettaJaAineitaEiOlisi() {
        Resepti tee = new Resepti("Tee");
        assertEquals("Tee\n"
                + "\n"
                + "Ainesosat: \n"
                + "Ainesosia ei ole vielä talletettu.\n"
                + "Ohjetta ei ole talletettu.", tee.toString());
    }
}
