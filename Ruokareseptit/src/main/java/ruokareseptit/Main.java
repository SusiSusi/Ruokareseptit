package ruokareseptit;

import java.util.*;
import javax.swing.SwingUtilities;
import static ruokareseptit.StringUtils.sisaltaa;
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
        
//        String sana = "Graavilohi-tartarleivät";
//        String sana2 = "Graavilohi-tartarleivät";
//        System.out.println(sisaltaa(sana, sana2));
//        Tietovarasto k = new Tietovarasto();
//        List<Kategoria> go = k.haeKategoriat();
//        Kategoria alkup = null;
//        for (Kategoria kk : go) {
//            if (StringUtils.sisaltaa(kk.getKategorianNimi(), "alkupala")) {
//                alkup = kk;
//            }
//        }
//        System.out.println(alkup.getKategorianNimi());
//        System.out.println(alkup.tulostaResepti("Graavilohi-tartarleivät"));
        
    }
}


