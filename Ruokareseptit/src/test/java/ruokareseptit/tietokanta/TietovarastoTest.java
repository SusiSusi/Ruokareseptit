package ruokareseptit.tietokanta;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ruokareseptit.domain.Ainesosa;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;

public class TietovarastoTest {

    Tietovarasto varasto;
    File reseptiTiedosto;

    @Before
    public void setUp() {
        varasto = new Tietovarasto("/KategoriatTest.txt",
                "/ReseptitTest.txt");
        reseptiTiedosto = new File(this.getClass().getResource("/ReseptitTest.txt").getFile());

        varasto.lisaaKategoriat();
    }

    @Test
    public void tiedostoLuetaanOikeinjaOnoikeaMaaraKategorioita() {
        Tietovarasto oikeaVarasto = new Tietovarasto();
        List<Kategoria> lista = varasto.haeKategoriat();
        assertEquals(5, lista.size());
        assertEquals(7, oikeaVarasto.haeKategoriat().size());
    }

    @Test
    public void reseptinLisaysJaPoistoOnnistuu() throws IOException {
        Resepti resep = new Resepti("Makaronilaatikko");
        long kokoEnnen = reseptiTiedosto.length();
        varasto.lisaaReseptiTiedostoon("Liha", resep);
        long kokoJalkeen = reseptiTiedosto.length();
        assertEquals(true, kokoEnnen < kokoJalkeen);
        kokoEnnen = reseptiTiedosto.length();
        varasto.poistaReseptiTiedostosta("Liha", resep);
        kokoJalkeen = reseptiTiedosto.length();
        assertEquals(true, kokoEnnen > kokoJalkeen);
    }

    @Test
    public void reseptienPoistoKategorioistaOnnistuu() {
        List<Kategoria> kategoriat = varasto.haeKategoriat();
        varasto.lisaaKategorioihinReseptit();
        int montaReseptia = 0;

        for (Kategoria kat : kategoriat) {
            montaReseptia = montaReseptia + kat.reseptienMaaraKategoriassa();
        }
        assertEquals(3, montaReseptia);
        varasto.poistaKategorioistaReseptit();
        int montaReseptiaPoistonJalkeen = 0;
        for (Kategoria kat : kategoriat) {
            montaReseptiaPoistonJalkeen = montaReseptiaPoistonJalkeen + kat.reseptienMaaraKategoriassa();
        }
        assertEquals(true, montaReseptiaPoistonJalkeen < montaReseptia);
    }

    @Test
    public void lisataanKategoriaanJotaEiOleOlemassa() {
        assertEquals(null, varasto.mihinKategoriaanReseptiLisataan("EiTokkinsa"));
    }

    @Test
    public void aineidenLisaysReseptiinOikeassaMuodossaOnnistuu() {
        Resepti ja = new Resepti("Jauhelihakastike");
        String aineet = "400 g:jauheliha";
        int ainesosaMaaraEnnen = ja.getAinesosat().size();
        varasto.aineidenLisaysReseptiin(ja, aineet);
        int ainesosaMaaraJalkeen = ja.getAinesosat().size();
        assertEquals(true, ainesosaMaaraEnnen < ainesosaMaaraJalkeen);
    }

}
