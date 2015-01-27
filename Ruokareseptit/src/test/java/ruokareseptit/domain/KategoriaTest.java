
package ruokareseptit.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KategoriaTest {
    
    Kategoria keitot;
    Resepti kalakeitto;
    
    @Before
    public void setUp() {
        keitot = new Kategoria("Keitot");
        kalakeitto = new Resepti("Kalakeitto");
        kalakeitto.setAinesosa("Seiti", "4 kpl");
        kalakeitto.setOhje("Kiehauta vesi kattilassa ja keitä seitejä 10 min. Lisää "
                + "suola ja pippuri. Anna hautua 30min miedolla lämmöllä.");
    }
    
    @Test
    public void asettaaUudenReseptin() {
        keitot.lisaaReseptiKategoriaan(kalakeitto);
        assertEquals(1, keitot.reseptienMaaraKategoriassa());
    }
    
    @Test
    public void tulostaaReseptienNimet() {
        keitot.lisaaReseptiKategoriaan(new Resepti("Maksalaatikko"));
        keitot.lisaaReseptiKategoriaan(new Resepti("Risotto"));
        keitot.lisaaReseptiKategoriaan(new Resepti("Kastike"));
        keitot.lisaaReseptiKategoriaan(new Resepti("Jauheliha"));
        assertEquals(4, keitot.reseptienMaaraKategoriassa());
    }
    
    @Test
    public void tulostaaReseptienMaaranNollaTapauksessa() {
        assertEquals(0, keitot.reseptienMaaraKategoriassa());
    }
}
