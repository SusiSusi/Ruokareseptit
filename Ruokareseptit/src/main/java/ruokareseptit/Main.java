package ruokareseptit;

import java.util.*;
import javax.swing.SwingUtilities;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.tietokanta.Tietovarasto;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
//
//        GraafinenKayttoliittyma kayttoliittyma = new GraafinenKayttoliittyma();
//        SwingUtilities.invokeLater(kayttoliittyma);

//        Tietovarasto va = new Tietovarasto();
//        List<Kategoria> ka = va.haeKategoriat();
//        List<Resepti> mah = new ArrayList<>();
//        for (Kategoria o : ka) {
////            System.out.println(o.getKategorianNimi());
//            if (o.reseptienMaaraKategoriassa() != 0) {
//                mah = o.tulostaKaikkiReseptit();
//                for (Resepti re : mah) {
//                   System.out.println(re);
//                    System.out.println("*****");
//                }
//            }
//        if (!mah.isEmpty()) {
//            for (Resepti re : mah) {
//                System.out.println(re);
//                System.out.println("");
//            }
//        }
//
//        }
        Scanner lukija = new Scanner(System.in);
        Tekstikayttoliittyma kayttis = new Tekstikayttoliittyma(lukija);
        kayttis.kaynnista();
    }
}


