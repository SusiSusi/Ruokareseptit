package ruokareseptit.ruokareseptit;



import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruokareseptit.ruokareseptit.Ainesosa;


public class AinesosaTest {
    
    Ainesosa jauheliha;
    
    @Before
    public void setUp() {
        jauheliha = new Ainesosa("Jauheliha", 400);
    }

    @Test
    public void tulostaaNimenJaMaaranOikein() {
        assertEquals("Jauheliha, 400 g", jauheliha.toString());
    }
    
    @Test
    public void asettaaUudeksiMaaraksiNollan() {
        jauheliha.setMaara(0);
        assertEquals(0, jauheliha.getMaara());
    }
    
    @Test
    public void maaraEiMuutuKunSyotetaanNegatiivinenLuku() {
        jauheliha.setMaara(-1);
        assertEquals(400, jauheliha.getMaara());
    }
}
