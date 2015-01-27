/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ruokareseptit;

import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;

/**
 *
 * @author susisusi
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Kategoria uu = new Kategoria("Pasta");
        uu.lisaaReseptiKategoriaan(new Resepti("Maksalaatikko"));
        
        Resepti kakku = new Resepti("Kakku");
        kakku.setAinesosa("kaneli", "1 tl");
        kakku.setAinesosa("jauho", "4 dl");
        kakku.setOhje("Lämmitä uuni 200c. Vatkaa munat ja lisää jauhot"
                + "sekä sokeri ja perunajauho.");
        uu.lisaaReseptiKategoriaan(kakku);
//        System.out.println(uu.tulostaReseptit());
//        System.out.println(uu.tulostaReseptienNimet());
        uu.tulostaResepti("kaikku");
//        uu.tulostaResepti("kakku");
    }
    
}
