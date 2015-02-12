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
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;

public class TietovarastoTest {

    Tietovarasto varasto;
    File reseptiTiedosto;

    @Before
    public void setUp() {
        varasto = new Tietovarasto("src/test/java/ruokareseptit/tietokanta/KategoriatTest.txt",
                "src/test/java/ruokareseptit/tietokanta/ReseptitTest.txt");
        reseptiTiedosto = new File("src/test/java/ruokareseptit/tietokanta/ReseptitTest.txt");
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
    public void reseptinLisaysOnnistuu() throws IOException {
        Resepti resep = new Resepti("Makaronilaatikko");
        long kokoEnnen = reseptiTiedosto.length();
        varasto.lisaaReseptiTiedostoon("Liha", resep);
        long kokoJalkeen = reseptiTiedosto.length();
        assertEquals(true, kokoEnnen < kokoJalkeen);
    }

    @Test
    public void tiedostonLukuEpaonnistuuJaKategoriaListaOnTyhja() {
        Tietovarasto toinen = new Tietovarasto("testi.txt", "tps.txt");
        toinen.lisaaKategoriat();
        assertEquals(0, toinen.haeKategoriat().size());
    }

    @Test
    public void reseptienLisaysKategorioihinOnnistuu() {
        Tietovarasto oikeaVarasto = new Tietovarasto();
        List<Kategoria> kategoriat = oikeaVarasto.haeKategoriat();
        int montaReseptia = 0;
        for (Kategoria kat : kategoriat) {
            montaReseptia = montaReseptia + kat.reseptienMaaraKategoriassa();
        }
        assertEquals(5, montaReseptia);
    }

    @Test
    public void reseptienPoistoKategorioistaOnnistuu() {
        varasto.lisaaKategorioihinReseptit();
        varasto.poistaKategorioistaReseptit();
        List<Kategoria> kategoriat = varasto.haeKategoriat();
        int montaReseptia = 0;
        for (Kategoria kat : kategoriat) {
            montaReseptia = montaReseptia + kat.reseptienMaaraKategoriassa();
        }
        assertEquals(0, montaReseptia);
    }
}
