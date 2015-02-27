package ruokareseptit.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import ruokareseptit.logiikka.LisayksetJaPoistot;
import ruokareseptit.logiikka.StringUtils;
import ruokareseptit.logiikka.Tulostus;

/**
 * Luokka käsittelee reseptin haku -tapahtuman
 *
 * @author susisusi
 */
public class ReseptinHakuKuuntelija implements ActionListener {

    private JTextField haettava;
    private Container container;
    private Tulostus tulostus;
    private LisayksetJaPoistot lisayksetJaPoistot;

    /**
     * Konstruktori saa parametrikseen ValikkoNappaintenKuuntelija tai
     * KategorianHakuKuuntelija -luokalta saadut tiedot
     *
     * @param haettava Käyttäjän antama syöte
     * @param container
     * @param tulostus
     * @param lisayksetJaPoistot
     */
    public ReseptinHakuKuuntelija(JTextField haettava, Container container,
            Tulostus tulostus, LisayksetJaPoistot lisayksetJaPoistot) {
        this.haettava = haettava;
        this.container = container;
        this.tulostus = tulostus;
        this.lisayksetJaPoistot = lisayksetJaPoistot;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.haettava.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kirjoita resepti jonka haluat hakea!");
        } else {
            container.remove(2);
            etsiResepti();
            container.validate();
        }
    }

    private void etsiResepti() {
        JPanel paneeli = new JPanel(new BorderLayout());

        String reseptinTiedot = this.tulostus.tulostaResepti(haettava.getText());

        JTextPane tuloste = new JTextPane();
        tuloste.setContentType("text/html");
        tuloste.setText("<html><b>" + new StringUtils().htmlRiviVaihtoja(4) + reseptinTiedot.replace("\n", "<br>") + "</b></html>");
        tuloste.setBorder(null);
        tuloste.setOpaque(false);
        tuloste.setEditable(false);

        tuloste.setSelectionStart(0); // jotta scrollaus alkaisi ylhäältä
        tuloste.setSelectionEnd(0);

        JScrollPane scrollaaResepti = new JScrollPane(tuloste);
        paneeli.add(scrollaaResepti);

        JButton poista = new JButton("Poista resepti");

        if (!reseptinTiedot.equals("Reseptiä ei löytynyt.")) {
            paneeli.add(poista, BorderLayout.PAGE_END);
            poista.addActionListener(new ReseptinPoistonKuuntelija(haettava,
                    this.container, this.tulostus, this.lisayksetJaPoistot));
        }
        container.add(paneeli);
    }

}
