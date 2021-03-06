
package ruokareseptit.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ruokareseptit.logiikka.LisayksetJaPoistot;
import ruokareseptit.logiikka.Tulostus;

/**
 * Luokka käsittelee reseptin poisto -tapahtuman
 * @author susisusi
 */
public class ReseptinPoistonKuuntelija implements ActionListener {

    private JTextField haettava;
    private Container container;
    private Tulostus tulostus;
    private LisayksetJaPoistot lisayksetJaPoistot;
    
    /**
     * Konstruktori saa parametrikseen ReseptinHakuKuuntelija-luokalta saadut tiedot
     * @param haettava Käyttäjän antama syöte
     * @param container
     * @param tulostus
     * @param lisayksetJaPoistot 
     */

    public ReseptinPoistonKuuntelija(JTextField haettava, Container container,
            Tulostus tulostus, LisayksetJaPoistot lisayksetJaPoistot) {
        this.haettava = haettava;
        this.container = container;
        this.tulostus = tulostus;
        this.lisayksetJaPoistot = lisayksetJaPoistot;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int valinta = JOptionPane.showOptionDialog(null,
                "Haluatko varmasti poistaa reseptin " + this.haettava.getText() + "?",
                "Reseptin poisto",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);

        if (valinta == JOptionPane.YES_OPTION) {
            container.remove(2);
            poistetaanResepti();
            container.validate();
        }
    }

    private void poistetaanResepti() {
        String reseptinNimi = this.haettava.getText();
        try {
            this.lisayksetJaPoistot.poistaResepti(reseptinNimi);
        } catch (IOException ex) {
            Logger.getLogger(ReseptinPoistonKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
        }
        JPanel paneeli = new JPanel(new BorderLayout());
        JLabel teksti = new JLabel("Resepti " + reseptinNimi + " on onnistuneesti poistettu tietokannasta.");
        paneeli.add(teksti);
        container.add(paneeli);
    }
}
