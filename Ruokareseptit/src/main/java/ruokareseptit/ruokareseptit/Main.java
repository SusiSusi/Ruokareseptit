

package ruokareseptit.ruokareseptit;

public class Main {

    public static void main(String[] args) {
        // TODO code application logic here
        
        Resepti re = new Resepti("Makaronilaatikko");
        re.setOhje("Vatkaa munat ja lämmitä uuni. Lihat paistetaan jossain"
                + "ja niitä kypsytetään sipulin kanssa pannulla. "
                + "Jos haluat, lisää joukkoon kanelia."
                + "10min uunissa ja sitten ruoka on valmis.");
        
        re.setAinesosa("Jauhelija", 400);
        re.setAinesosa("Makaroni", 4);
        re.setAinesosa("kahvi", 1);
        
        System.out.println(re);
    }
    
}
