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
    
    public List tulostaKaikkiReseptit() {
        List<Resepti> palautettavaLista = new ArrayList<Resepti>();
        if (!this.reseptit.isEmpty()) {
            for (Resepti resepti : this.reseptit) {
                palautettavaLista.add(resepti);
            }
        }
        return palautettavaLista;
    }
    
    public List tulostaReseptienNimet() {
        List<String> palautettavaLista = new ArrayList<String>();
        if (!this.reseptit.isEmpty()) {
            for (Resepti resepti : this.reseptit) {
                palautettavaLista.add(resepti.getNimi());
            }
        }
        return palautettavaLista;
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
        }
        if (this.reseptit.isEmpty() || apu == 0) {
            System.out.println("Valitsemaasi reseptiä ei löytynyt.");
        }
    }
}
