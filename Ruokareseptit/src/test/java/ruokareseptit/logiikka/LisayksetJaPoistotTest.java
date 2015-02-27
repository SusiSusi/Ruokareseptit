package ruokareseptit.logiikka;

import java.io.File;
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

public class LisayksetJaPoistotTest {

    LisayksetJaPoistot lisaykset;
    Tietovarasto varasto;
    Resepti jauhelihakastike;
    File kopio;

    @Before
    public void setUp() {
        varasto = new Tietovarasto("/KategoriatTest.txt",
                "/ReseptitTest.txt");
        varasto.lisaaKategoriat();
        varasto.lisaaKategorioihinReseptit();
        this.lisaykset = new LisayksetJaPoistot(varasto.haeKategoriat(), varasto);
        jauhelihakastike = new Resepti("Jauhelihakastike");
    }

    @Test
    public void lisaaJaPoistaaReseptinTiedostostaOikein() throws IOException {
        int montaReseptia = 0;
        List<Kategoria> kategoria = varasto.haeKategoriat();
        for (Kategoria ka : kategoria) {
            montaReseptia = montaReseptia + ka.getKaikkiReseptit().size();
        }
        assertEquals(true, lisaykset.lisaaUusiResepti("Alkupala", jauhelihakastike));
        int montaReseptiaLisayksenJalkeen = 0;
        for (Kategoria ka : kategoria) {
            montaReseptiaLisayksenJalkeen = montaReseptiaLisayksenJalkeen + ka.getKaikkiReseptit().size();
        }
        assertEquals(true, montaReseptiaLisayksenJalkeen > montaReseptia);

    }
    
    @Test
    public void palauttaaFalseKunYritetaanPoistaaReseptiaMitaEiOleOlemassa() throws IOException {
        assertEquals(false, lisaykset.poistaResepti("kukkahattu"));
    }

    @Test
    public void poistaaTiedostostaReseptinOikein() throws IOException {
        int montaReseptia = 0;
        List<Kategoria> kategoria = varasto.haeKategoriat();
        for (Kategoria ka : kategoria) {
            montaReseptia = montaReseptia + ka.getKaikkiReseptit().size();
        }
        lisaykset.poistaResepti(jauhelihakastike.getNimi());
        int montaReseptiaPoistonJalkeen = 0;
        for (Kategoria ka : kategoria) {
            montaReseptiaPoistonJalkeen = montaReseptiaPoistonJalkeen + ka.getKaikkiReseptit().size();
        }
        assertEquals(true, montaReseptia > montaReseptiaPoistonJalkeen);
    }

    @Test
    public void reseptinLisaaminenEiOnnistu() throws IOException {
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
    public void listaaReseptiinAinesosanMillaOnMontaPilkkua() {
        Resepti lohi = new Resepti("Lohi");
        String[] aineet = new String[2];
        aineet[0] = "1 kpl, tomaatti, esim suomalainen, ruotsalainen, espanjalainen";
        aineet[1] = "1 kpl, lohi, esim norjalainen, ruotsalainen tai fileerattu, paistettu";
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
