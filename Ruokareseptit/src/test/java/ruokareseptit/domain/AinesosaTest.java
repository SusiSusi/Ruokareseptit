package ruokareseptit.domain;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruokareseptit.domain.Ainesosa;


public class AinesosaTest {
    
    Ainesosa jauheliha;
    
    @Before
    public void setUp() {
        jauheliha = new Ainesosa("Jauheliha", "400 g");
    }

    @Test
    public void tulostaaNimenJaMaaranOikein() {
        assertEquals("400 g, Jauheliha", jauheliha.toString());
    }
    
    @Test
    public void asettaaUudenMaaran() {
        jauheliha.setMaara("500 g");
        assertEquals("500 g", jauheliha.getMaara());
    }
    
    @Test
    public void asettaaUudenNimen() {
        jauheliha.setNimi("Naudan jauheliha");
        assertEquals("Naudan jauheliha", jauheliha.getNimi());
    }
    
    @Test
    public void tulostaaAinesosanNimenOikeinKunAinesosallaEiOleMaaraa() {
        Ainesosa pippuri = new Ainesosa("pippuri", "");
        assertEquals("pippuri", pippuri.toString());
    }
}
