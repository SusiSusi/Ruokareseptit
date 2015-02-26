package ruokareseptit.logiikka;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruokareseptit.domain.Ainesosa;
import ruokareseptit.logiikka.StringUtils;

public class StringUtilsTest {

    StringUtils etsija;

    @Before
    public void setUp() {
        etsija = new StringUtils();
    }

    @Test
    public void palauttaaFalseArvon() {
        assertEquals(false, etsija.sisaltaa("kakku", ""));
        assertEquals(false, etsija.sisaltaa("", "kakku"));
        assertEquals(false, etsija.sisaltaa("Jäätelö", "kakku"));
        assertEquals(false, etsija.sisaltaa("", ""));
    }

    @Test
    public void palauttaaTrueArvon() {
        assertEquals(true, etsija.sisaltaa("MakaRoNi    ", "makaroni"));
        assertEquals(true, etsija.sisaltaa("Kakku", "   kaKKu  "));
        assertEquals(true, etsija.sisaltaa("Jäätelö", "JÄÄTELÖ"));
    }
    
    @Test
    public void muuttaaAinesosaListaTiedostoonSopivaksi() {
        List<Ainesosa> osat = new ArrayList<>();
        osat.add(new Ainesosa("tomaatti", "2 kpl"));
        osat.add(new Ainesosa("kurkku", "0.5 kpl"));
        osat.add(new Ainesosa("salaatti", "5 lehteä"));
        String muutettu = etsija.muutaAinesosatTiedostoonSopiviksi(osat);
        assertEquals("2 kpl:tomaatti\n0.5 kpl:kurkku\n5 lehteä:salaatti\n", muutettu);
    }
    
    @Test
    public void tulostaaTahtiaOikein() {
        assertEquals("****", etsija.tulostaTahtia(4));
        assertEquals("", etsija.tulostaTahtia(0));
    }
    
    @Test
    public void htmlRiviVaihdotTulevatOikein() {
        assertEquals("<br><br><br>", etsija.htmlRiviVaihtoja(3));
        assertEquals("", etsija.htmlRiviVaihtoja(0));
    }
}
