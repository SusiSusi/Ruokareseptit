package ruokareseptit.logiikka;

import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.tietokanta.Tietovarasto;

public class LisayksetTest {

    Lisaykset lisaykset;
    Tietovarasto varasto;

    @Before
    public void setUp() {
        this.varasto = new Tietovarasto();
        this.lisaykset = new Lisaykset(varasto.haeKategoriat(), varasto);
    }

    @Test
    public void lisaaReseptinTiedostoonOikein() throws IOException {
        int montaReseptia = 0;
        List<Kategoria> ko = varasto.haeKategoriat();
        for (Kategoria ka : ko) {
            montaReseptia = montaReseptia + ka.getKaikkiReseptit().size();
        }
        Resepti jauhelihakastike = new Resepti("Jauhelihakastike");
        assertEquals(true, lisaykset.lisaaUusiResepti("alkupala", jauhelihakastike));
        int montaReseptiaLisayksenJalkeen = 0;
        for (Kategoria ka : ko) {
            montaReseptiaLisayksenJalkeen = montaReseptiaLisayksenJalkeen + ka.getKaikkiReseptit().size();
        }
        assertEquals(true, montaReseptiaLisayksenJalkeen > montaReseptia);
        varasto.poistaReseptiTiedostosta("alkupala", jauhelihakastike);
    }

    @Test
    public void reseptinLisaaminenEiOnnistu() throws IOException {
        Resepti jauhelihakastike = new Resepti("Jauhelihakastike");
        assertEquals(false, lisaykset.lisaaUusiResepti("", jauhelihakastike));
        assertEquals(false, lisaykset.lisaaUusiResepti("alkupala", new Resepti("")));
    }

    @Test
    public void kategoriaKirjoitetaanOikein() {
        assertEquals(true, lisaykset.kirjoitetaankoKategoriaOikein("alkupala"));
    }

    @Test
    public void kategoriaKirjoitetaanVaarinJaPalauttaaFalseArvon() {
        assertEquals(false, lisaykset.kirjoitetaankoKategoriaOikein("mika-mika-maa"));
        assertEquals(false, lisaykset.kirjoitetaankoKategoriaOikein(""));
    }

}
