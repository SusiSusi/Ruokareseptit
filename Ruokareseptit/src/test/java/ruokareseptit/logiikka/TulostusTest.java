
package ruokareseptit.logiikka;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.tietokanta.Tietovarasto;

public class TulostusTest {
    List<Kategoria> kategoriat;
    Tulostus tulostaja;
    
    @Before
    public void setUp() {
        kategoriat = new ArrayList<>();
        Kategoria keitto = new Kategoria("Keitto");
        Kategoria liha = new Kategoria("Liha");
        keitto.lisaaReseptiKategoriaan(new Resepti("Kalakeitto"));
        keitto.lisaaReseptiKategoriaan(new Resepti("Sosekeitto"));
        liha.lisaaReseptiKategoriaan(new Resepti("Jauhelihakastike"));
        kategoriat.add(keitto);
        kategoriat.add(liha);
        tulostaja = new Tulostus(kategoriat);
    }
    
    @Test
    public void tulostaaKaikkiKategoriat() {
        assertEquals(22, tulostaja.tulostaKaikkiKategoriat().length());        
    }
    
    @Test
    public void tulostaaKaikkiReseptit() {
        assertEquals(285, tulostaja.tulostaKaikkiReseptit().length());
    }
    
    @Test
    public void tulostaaHaetunReseptin() {
        assertEquals(51, tulostaja.tulostaResepti("Kalakeitto").length());
        assertEquals("Reseptiä ei löytynyt.", tulostaja.tulostaResepti("kakku"));
    }
    
    @Test
    public void tulostaaOikeinKategorianReseptienNimet() {
        assertEquals("  Kalakeitto\n  Sosekeitto\n", tulostaja.tulostaKategorianReseptienNimet("Keitto"));
        assertEquals("Kategoriaa ei löytynyt tai kategoriassa ei ole reseptejä",
        tulostaja.tulostaKategorianReseptienNimet("alkupalat"));
    }
    
    @Test
    public void tulostaaReseptinTietystaKategoriastaOikein() {
        assertEquals(57, tulostaja.tulostaReseptiTietystaKategoriasta("Liha", "Jauhelihakastike").length());
        assertEquals("Reseptiä ei löytynyt", tulostaja.tulostaReseptiTietystaKategoriasta("Liha", "Sosekeitto"));
    }

}
