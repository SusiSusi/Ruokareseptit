package ruokareseptit.domain;

/**
 * Luokka luo ainesosia
 * 
 * @author susisusi
 */

public class Ainesosa {

    private String nimi;
    private String maara;

    /**
     * Konstruktori asettaa ainesosalle nimen ja määrän
     * @param nimi käyttäjän antama syöte
     * @param maara käyttäjän antama syöte
     */
    
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

    @Override
    public String toString() {
        if (this.maara.isEmpty()) {
            return this.nimi;
        }
        return this.maara + ", " + this.nimi;
    }
}
