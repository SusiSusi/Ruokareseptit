
package ruokareseptit.ruokareseptit;

public class Ainesosa {
    private String nimi; 
    private int maara;
    
    public Ainesosa(String nimi, int maara) {
        this.nimi = nimi;
        this.maara = maara;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public int getMaara() {
        return this.maara;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public void setMaara(int maara) {
        this.maara = maara;
    }
    
    public String toString() {
        return this.nimi + "," + this.maara + " g";
    }
}
