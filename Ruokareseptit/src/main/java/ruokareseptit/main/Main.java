package ruokareseptit.main;

import java.io.IOException;
import javax.swing.SwingUtilities;
import ruokareseptit.gui.GraafinenKayttoliittyma;

public class Main {

    public static void main(String[] args) throws IOException {
        
        GraafinenKayttoliittyma kayttoliittyma = new GraafinenKayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);

    }
}
