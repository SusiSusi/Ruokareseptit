/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruokareseptit.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ruokareseptit.logiikka.Lisaykset;
import ruokareseptit.logiikka.Tulostus;
import ruokareseptit.tietokanta.Tietovarasto;

/**
 *
 * @author susisusi
 */
public class ReseptinPoistonKuuntelija implements ActionListener {

    private JTextField haettava;
    private Container container;
    private Tulostus tulostus;
    private Lisaykset lisayksetJaPoistot;

    public ReseptinPoistonKuuntelija(JTextField haettava, Container container,
            Tulostus tulostus, Lisaykset lisayksetJaPoistot) {
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

    public void poistetaanResepti() {
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
