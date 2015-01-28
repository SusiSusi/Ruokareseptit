package ruokareseptit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

    public void palauttaaTrueArvon() {
        assertEquals(true, etsija.sisaltaa("MakaRoNi    ", "makaroni"));
        assertEquals(true, etsija.sisaltaa("Kakku", "   kaKKu  "));
        assertEquals(true, etsija.sisaltaa("Jäätelö", "JÄÄTELÖ"));
    }
}
