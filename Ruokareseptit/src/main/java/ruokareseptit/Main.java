package ruokareseptit;

import ruokareseptit.gui.Tekstikayttoliittyma;
import java.io.IOException;
import java.util.*;
import ruokareseptit.domain.Ainesosa;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.tietokanta.Tietovarasto;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner lukija = new Scanner(System.in, "UTF-8");
        Tekstikayttoliittyma kayttis = new Tekstikayttoliittyma(lukija);
        kayttis.kaynnista();
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
//        Kategoria n = new Kategoria("Keitto");
//        Resepti r = new Resepti("Kalakeitto");
//        n.lisaaReseptiKategoriaan(r);
//        Resepti k = new Resepti("Susikeitto");
//        System.out.println(r.getOhje());
//        
//        r.setOhje("päägldlfkldfkdlk lkdfl kdlkf dfkdl kld dlfkdl kdl dlfk ldkfl ");
//        System.out.println(r.getOhje());
////        n.lisaaReseptiKategoriaan(k);
//        System.out.println(n.reseptienMaaraKategoriassa());
//        n.poistaResepti("Kalakeitto");
//        n.poistaKaikkiReseptit();
//        System.out.println(n.reseptienMaaraKategoriassa());
//        Tietovarasto j = new Tietovarasto();
//        Resepti u = new Resepti("Kalakeitto");
//        u.setAinesosa("lohi", "1 kpl");
//        u.setOhje("Fileeraa lohi ja laita uuniin");
//        j.lisaaReseptiTiedostoon("keitto", u);
//        List<Ainesosa> os = u.getAinesosat();
//        for (Ainesosa o : os) {
//            System.out.println(o);
//        }
//        System.out.println("********");
//        System.out.println(StringUtils.muutaAinesosatTiedostoonSopiviksi(os));
    }
}
