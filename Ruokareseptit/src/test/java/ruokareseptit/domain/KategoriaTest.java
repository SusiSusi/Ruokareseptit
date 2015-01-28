package ruokareseptit.domain;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void tulostaaReseptienNimetOikein() {
        keitot.lisaaReseptiKategoriaan(new Resepti("Maksalaatikko"));
        keitot.lisaaReseptiKategoriaan(new Resepti("Risotto"));
        keitot.lisaaReseptiKategoriaan(new Resepti("Kastike"));
        keitot.lisaaReseptiKategoriaan(new Resepti("Jauheliha"));
        List<String> lista = new ArrayList<String>();
        lista.add("Maksalaatikko");
        lista.add("Risotto");
        lista.add("Kastike");
        lista.add("Jauheliha");
        assertEquals(lista, keitot.tulostaReseptienNimet());
    }

    @Test
    public void tulostaaReseptienMaaranNollaTapauksessa() {
        assertEquals(0, keitot.reseptienMaaraKategoriassa());
    }

    @Test
    public void tulostaaKaikkiReseptitOikein() {
        Resepti maksa = new Resepti("Maksalaatikko");
        Resepti risotto = new Resepti("Risotto");
        keitot.lisaaReseptiKategoriaan(maksa);
        keitot.lisaaReseptiKategoriaan(risotto);
        keitot.lisaaReseptiKategoriaan(kalakeitto);
        List<Resepti> lista = new ArrayList<Resepti>();
        lista.add(maksa);
        lista.add(risotto);
        lista.add(kalakeitto);
        assertEquals(lista, keitot.tulostaKaikkiReseptit());
    }

    @Test
    public void tulostaaHalutunReseptinJaKategorianOikein() {
        Resepti maksa = new Resepti("Maksalaatikko");
        Resepti risotto = new Resepti("Risotto");
        keitot.lisaaReseptiKategoriaan(maksa);
        keitot.lisaaReseptiKategoriaan(risotto);
        keitot.lisaaReseptiKategoriaan(kalakeitto);
        List<Resepti> lista = new ArrayList<Resepti>();
        lista.add(maksa);
        lista.add(risotto);
        lista.add(kalakeitto);
        assertEquals(kalakeitto, keitot.tulostaResepti("kalakeitto"));
        assertEquals("Keitot", keitot.getKategorianNimi());
    }

    @Test
    public void haluttuaReseptiaEiLoydyJaPalauttaaNullArvon() {
        Resepti maksa = new Resepti("Maksalaatikko");
        keitot.lisaaReseptiKategoriaan(maksa);
        keitot.lisaaReseptiKategoriaan(kalakeitto);
        List<Resepti> lista = new ArrayList<Resepti>();
        lista.add(maksa);
        lista.add(kalakeitto);
        assertEquals(null, keitot.tulostaResepti("Makaronilaatikko"));
        assertEquals(null, keitot.tulostaResepti(""));
    }
}
