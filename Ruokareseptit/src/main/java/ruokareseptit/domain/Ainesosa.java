package ruokareseptit.domain;

public class Ainesosa {

    private String nimi;
    private String maara;

    public Ainesosa(String nimi, String maara) {
        this.nimi = nimi;
        this.maara = maara;
    }

    public String getNimi() {
        return this.nimi;
    }

    public String getMaara() {
        return this.maara;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setMaara(String maara) {
        this.maara = maara;
    }

    public String toString() {
        return this.maara + ", " + this.nimi;
    }
}
