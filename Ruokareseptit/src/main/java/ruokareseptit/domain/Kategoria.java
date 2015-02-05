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
        List<Resepti> palautettavaLista = new ArrayList<>();
        for (Resepti resepti : this.reseptit) {
            palautettavaLista.add(resepti);
        }
        return palautettavaLista;
    }

    public List tulostaReseptienNimet() {
        List<String> palautettavaLista = new ArrayList<>();
        for (Resepti resepti : this.reseptit) {
            palautettavaLista.add(resepti.getNimi());
        }
        return palautettavaLista;
    }

    public Resepti tulostaResepti(String nimi) {
        Resepti loytynytResepti = null;
        for (Resepti resep : this.reseptit) {
            System.out.println("TÄMÄ LOOPPI " + resep.getNimi());
            if (StringUtils.sisaltaa(resep.getNimi(), nimi)) {
                System.out.println("PÄÄSTÄÄNKÖ KOSKAAN TÄNNE");
                loytynytResepti = resep;
            }
        }
        return loytynytResepti;
    }
    
    public void poistaResepti(String nimi) {
        Iterator<Resepti> iteraattori = this.reseptit.iterator();
        while(iteraattori.hasNext()) {
            if (StringUtils.sisaltaa(iteraattori.next().getNimi(), nimi)) {
                iteraattori.remove();
            }
        }
    }
}
