
package ruokareseptit.logiikka;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.tietokanta.Tietovarasto;

public class TulostusTest {
    Tietovarasto varasto;
    List<Kategoria> kategoriat;
    Scanner lukija;
    Tulostus tulostaja;
    
    @Before
    public void setUp() {
        varasto = new Tietovarasto();
        kategoriat = varasto.haeKategoriat();
        lukija = new Scanner(System.in);
        tulostaja = new Tulostus(kategoriat, lukija);
    }
    
    @Test
    public void k() {
       
    }

}
