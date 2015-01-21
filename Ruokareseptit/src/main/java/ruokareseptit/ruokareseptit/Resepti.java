
package ruokareseptit.ruokareseptit;

import java.util.*;

public class Resepti {
    private String nimi; 
    private List<Ainesosa> ainesosat;
    private String ohje;
    
    public Resepti(String nimi) {
        this.nimi = nimi;
        this.ainesosat = new ArrayList<Ainesosa>();
        this.ohje = "";
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public void getAinesosat() {
        for (Ainesosa osa : this.ainesosat) {
            System.out.println(osa);
        }
    }
    
    public String getOhje() {
        return this.ohje;
    }
    
    public void setOhje(String ohje) {
        this.ohje = ohje;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public void setAinesosa(String ainesosa, int maara) {
        if (!this.ainesosat.isEmpty()) {
            for (Ainesosa osa : this.ainesosat) {
                if (osa.getNimi().equals(ainesosa)) {
                    System.out.println("Ainesosa on jo kirjattu määrällä " + osa.getMaara());
                    break;
                }
            }
        }
        this.ainesosat.add(new Ainesosa(ainesosa, maara));
    }
    
    public String toString() {
        System.out.println(this.nimi);
        System.out.println("Ainesosat: ");
        getAinesosat();
        return this.ohje;
    }
}
