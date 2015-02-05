
package ruokareseptit;

public class StringUtils {
    
    public static boolean sisaltaa(String sana, String haettava) {
        if (sana.isEmpty() || haettava.isEmpty()) {
            return false;
        }
        String trimSana = sana.trim();
        String trimHaettava = haettava.trim(); // poistetaan turhat välilyönnit
        
        trimSana = trimSana.toUpperCase();
        trimHaettava = trimHaettava.toUpperCase(); // muutetaan kaikki kirjaimet isoiksi
        
        if (trimSana.equals(trimHaettava)) {
            return true;
        }
        return false;
    }
    
}
