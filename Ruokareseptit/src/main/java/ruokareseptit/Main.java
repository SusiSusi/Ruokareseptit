package ruokareseptit;

import java.util.*;
import javax.swing.SwingUtilities;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.tietokanta.TietoVarasto;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
//        Kategoria keitot = new Kategoria("Keitot");
//        Resepti kalakeitto = new Resepti("Kalakeitto");
//        kalakeitto.setAinesosa("Seiti", "4 kpl");
//        kalakeitto.setOhje("Kiehauta vesi kattilassa ja keitä seitejä 10 min. Lisää "
//                + "suola ja pippuri. Anna hautua 30min miedolla lämmöllä.");
//        keitot.lisaaReseptiKategoriaan(kalakeitto);
//        Tekstikayttoliittyma tek = new Tekstikayttoliittyma();
//        tek.lisaaKategoriat(keitot);
//
//        GraafinenKayttoliittyma kayttoliittyma = new GraafinenKayttoliittyma();
//        SwingUtilities.invokeLater(kayttoliittyma);
        
        TietoVarasto va = new TietoVarasto();
        List<Kategoria> ka = va.haeKategoriat();
        List<Resepti> mah = new ArrayList<>();
        for (Kategoria o : ka) {
            System.out.println(o.getKategorianNimi());
            if (o.reseptienMaaraKategoriassa() != 0) {
                mah = o.tulostaKaikkiReseptit();
            }
        }
        if (!mah.isEmpty()) {
            for (Resepti re : mah) {
                System.out.println(re);
            }
        }
        

    }

}
