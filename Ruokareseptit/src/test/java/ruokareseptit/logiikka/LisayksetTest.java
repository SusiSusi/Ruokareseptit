package ruokareseptit.logiikka;

import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruokareseptit.domain.Ainesosa;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.tietokanta.Tietovarasto;

public class LisayksetTest {

    Lisaykset lisaykset;
    Tietovarasto varasto;

    @Before
    public void setUp() {
        varasto = new Tietovarasto("src/test/java/ruokareseptit/tietokanta/KategoriatTest.txt",
                "src/test/java/ruokareseptit/tietokanta/ReseptitTest.txt");
        varasto.lisaaKategoriat();
        varasto.lisaaKategorioihinReseptit();
        this.lisaykset = new Lisaykset(varasto.haeKategoriat(), varasto);
    }

    @Test
    public void lisaaJaPoistaaReseptinTiedostostaOikein() throws IOException {
        int montaReseptia = 0;
        List<Kategoria> ko = varasto.haeKategoriat();
        for (Kategoria ka : ko) {
            montaReseptia = montaReseptia + ka.getKaikkiReseptit().size();
        }
        Resepti jauhelihakastike = new Resepti("Jauhelihakastike");
        assertEquals(true, lisaykset.lisaaUusiResepti("Alkupala", jauhelihakastike));
        int montaReseptiaLisayksenJalkeen = 0;
        for (Kategoria ka : ko) {
            montaReseptiaLisayksenJalkeen = montaReseptiaLisayksenJalkeen + ka.getKaikkiReseptit().size();
        }
        assertEquals(true, montaReseptiaLisayksenJalkeen > montaReseptia);
        lisaykset.poistaResepti(jauhelihakastike.getNimi());
        int montaReseptiaPoistonJalkeen = 0;
        for (Kategoria ka : ko) {
            montaReseptiaPoistonJalkeen = montaReseptiaPoistonJalkeen + ka.getKaikkiReseptit().size();
        }
        assertEquals(true, montaReseptiaLisayksenJalkeen > montaReseptiaPoistonJalkeen);
    }

    @Test
    public void reseptinLisaaminenEiOnnistu() throws IOException {
        Resepti jauhelihakastike = new Resepti("Jauhelihakastike");
        assertEquals(false, lisaykset.lisaaUusiResepti("", jauhelihakastike));
        assertEquals(false, lisaykset.lisaaUusiResepti("Alkupala", new Resepti("")));
    }

    @Test
    public void kategoriaKirjoitetaanOikein() {
        assertEquals(true, lisaykset.kirjoitetaankoKategoriaOikein("Alkupala"));
    }

    @Test
    public void kategoriaKirjoitetaanVaarinJaPalauttaaFalseArvon() {
        assertEquals(false, lisaykset.kirjoitetaankoKategoriaOikein("mika-mika-maa"));
        assertEquals(false, lisaykset.kirjoitetaankoKategoriaOikein(""));
    }

    @Test
    public void lisaaReseptiinAinesosat() {
        Resepti lohi = new Resepti("Lohi");
        String[] aineet = new String[2];
        aineet[0] = "1 kpl, tomaatti";
        aineet[1] = "1 kpl, lohi";
        lisaykset.lisaaReseptiinAinesosat(lohi, aineet);
        List<Ainesosa> maara = lohi.getAinesosat();
        assertEquals(2, maara.size());
    }

    @Test
    public void lisaaReseptiinAinesosatVaikkaMaaraaEiOleIlmoitettu() {
        Resepti lohi = new Resepti("Lohi");
        String[] aineet = new String[2];
        aineet[0] = "tomaatti";
        aineet[1] = "lohi";
        lisaykset.lisaaReseptiinAinesosat(lohi, aineet);
        List<Ainesosa> maara = lohi.getAinesosat();
        assertEquals(2, maara.size());
    }


}
