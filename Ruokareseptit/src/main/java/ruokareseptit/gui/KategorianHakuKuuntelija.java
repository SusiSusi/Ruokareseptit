package ruokareseptit.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import ruokareseptit.logiikka.LisayksetJaPoistot;
import ruokareseptit.logiikka.Tulostus;

/**
 * Luokka käsittelee kategorian haku -tapahtuman
 * @author susisusi
 */
public class KategorianHakuKuuntelija implements ActionListener {

    private JTextField haettava;
    private Container container;
    private Tulostus tulostus;
    private LisayksetJaPoistot lisayksetJaPoistot;

    /**
     * Konstruktori saa parametrikseen ValikkoNappaintenKuuntelija-luokalta saadut tiedot
     * @param haettava Käyttäjän antama syöte
     * @param container
     * @param tulostus
     * @param lisayksetJaPoistot 
     */
    public KategorianHakuKuuntelija(JTextField haettava, Container container,
            Tulostus tulostus, LisayksetJaPoistot lisayksetJaPoistot) {
        this.haettava = haettava;
        this.container = container;
        this.tulostus = tulostus;
        this.lisayksetJaPoistot = lisayksetJaPoistot;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.haettava.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kirjoita kategoria jonka haluat hakea!");
        } else {
            container.remove(2);
            etsiKategoria();
            container.validate();
        }
    }

    private void etsiKategoria() {
        JPanel paneeli = new JPanel(new GridLayout(8, 1));
        String reseptienNimet = this.tulostus.tulostaKategorianReseptienNimet(haettava.getText());
        JLabel tuloste = new JLabel("<html>" + reseptienNimet.replace("\n", "<br>") + "</html>");

        JScrollPane scrollaaKategoriat = new JScrollPane(tuloste);
        paneeli.add(scrollaaKategoriat);

        if (!reseptienNimet.equals("Kategoriaa ei löytynyt tai kategoriassa ei ole reseptejä")) {

            paneeli.add(new JLabel("Haluatko tulostaa reseptin? Kirjoita reseptin nimi"));
            JTextField nimi = new JTextField();
            paneeli.add(nimi);

            JButton hae = new JButton("Hae");
            paneeli.add(hae);
            hae.addActionListener(new ReseptinHakuKuuntelija(nimi,
                    this.container, this.tulostus, this.lisayksetJaPoistot));

            
        }
        container.add(paneeli);
    }
}
