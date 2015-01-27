package ruokareseptit.domain;

import ruokareseptit.domain.Resepti;
import java.util.*;
import ruokareseptit.StringUtils;

public class Kategoria {

    private String nimi;
    private List<Resepti> reseptit;

    public Kategoria(String nimi) {
        this.nimi = nimi;
        this.reseptit = new ArrayList<Resepti>();
    }

    public String getKategorianNimi() {
        return this.nimi;
    }

    public void lisaaReseptiKategoriaan(Resepti resepti) {
        this.reseptit.add(resepti);
    }
    
    public int reseptienMaaraKategoriassa() {
        return this.reseptit.size();
    }

    public void tulostaKaikkiReseptit() {
        if (this.reseptit.isEmpty()) {
            System.out.println("Kategoriaan " + this.nimi + " ei ole talletettu reseptejä.");
        }
        for (Resepti resepti : this.reseptit) {
            System.out.println(resepti);
        }
    }

    public void tulostaReseptienNimet() {
        if (this.reseptit.isEmpty()) {
            System.out.println("Kategoriaan " + this.nimi + " ei ole talletettu reseptejä.");
        } else {
            for (Resepti resepti : this.reseptit) {
                System.out.println(resepti.getNimi());
            }
        }
    }

    public void tulostaResepti(String nimi) {
        int apu = 0;
        if (!this.reseptit.isEmpty()) {
            for (Resepti resep : this.reseptit) {
                if (StringUtils.sisaltaa(resep.getNimi(), nimi)) {
                    System.out.println(resep);
                    apu = 1;
                }
            }
        } if (this.reseptit.isEmpty() || apu == 0) {
            System.out.println("Valitsemaasi reseptiä ei löytynyt.");
        }
    }
}
