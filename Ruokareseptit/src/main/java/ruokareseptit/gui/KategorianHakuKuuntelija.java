package ruokareseptit.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import ruokareseptit.logiikka.LisayksetJaPoistot;
import ruokareseptit.logiikka.StringUtils;
import ruokareseptit.logiikka.Tulostus;

/**
 * Luokka käsittelee kategorian haku -tapahtuman
 *
 * @author susisusi
 */
public class KategorianHakuKuuntelija implements ActionListener {

    private JTextField haettava;
    private Container container;
    private Tulostus tulostus;
    private LisayksetJaPoistot lisayksetJaPoistot;

    /**
     * Konstruktori saa parametrikseen ValikkoNappaintenKuuntelija-luokalta
     * saadut tiedot
     *
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
        JPanel paneeli = new JPanel(new BorderLayout());
        String reseptienNimet = this.tulostus.tulostaKategorianReseptienNimet(haettava.getText());

        JLabel otsake = new JLabel("Kategorian " + haettava.getText() + " reseptit:");

        JTextPane tuloste = new JTextPane();
        tuloste.setContentType("text/html");
        tuloste.setText("<html><b>" + new StringUtils().htmlRiviVaihtoja(6) + reseptienNimet.replace("\n", "<br>") + "</b></html>");
        tuloste.setBorder(null);
        tuloste.setOpaque(false);
        tuloste.setEditable(false);

        tuloste.setSelectionStart(0); // jotta scrollaus alkaisi ylhäältä
        tuloste.setSelectionEnd(0);

        JScrollPane scrollaaKategoriat = new JScrollPane(tuloste);
        JSplitPane reseptienNimetTulostettuna = new JSplitPane(JSplitPane.VERTICAL_SPLIT, otsake, scrollaaKategoriat);

        paneeli.add(reseptienNimetTulostettuna, BorderLayout.CENTER);

        if (!reseptienNimet.equals("Kategoriaa ei löytynyt tai kategoriassa ei ole reseptejä")) {

            JLabel kysytaanReseptinNimi = new JLabel("Haluatko tulostaa reseptin? Kirjoita reseptin nimi");
            JTextField nimi = new JTextField();
            JButton hae = new JButton("Hae");

            JSplitPane yhdiste = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                    kysytaanReseptinNimi, nimi);
            JSplitPane kaikkiYhdessa = new JSplitPane(JSplitPane.VERTICAL_SPLIT, yhdiste, hae);

            paneeli.add(kaikkiYhdessa, BorderLayout.SOUTH);
            hae.addActionListener(new ReseptinHakuKuuntelija(nimi,
                    this.container, this.tulostus, this.lisayksetJaPoistot));

        }
        container.add(paneeli);
    }
}
