
package ruokareseptit.logiikka;

import java.io.File;
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
        assertEquals(528, tulostaja.tulostaKaikkiReseptit().length());
    }
    
    @Test
    public void tulostaaHaetunReseptin() {
        assertEquals(67, tulostaja.tulostaResepti("Kalakeitto").length());
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
        assertEquals(73, tulostaja.tulostaReseptiTietystaKategoriasta("Liha", "Jauhelihakastike").length());
        assertEquals("Reseptiä ei löytynyt", tulostaja.tulostaReseptiTietystaKategoriasta("Liha", "Sosekeitto"));
    }
    
    @Test
    public void tulostaaKategoriatValikkoonSopiviksiOikein() {
        String[] kategoriat = tulostaja.tulostaKaikkiKategoriatValikkoonSopiviksi();
        assertEquals(2, kategoriat.length);
    }

}
