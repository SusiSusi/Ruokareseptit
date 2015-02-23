package ruokareseptit.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import ruokareseptit.logiikka.LisayksetJaPoistot;
import ruokareseptit.logiikka.Tulostus;
import ruokareseptit.tietokanta.Tietovarasto;

/**
 *
 * @author susisusi
 */
public class ReseptinHakuKuuntelija implements ActionListener {

    private JTextField haettava;
    private Container container;
    private Tulostus tulostus;
    private LisayksetJaPoistot lisayksetJaPoistot;

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

    public void etsiResepti() {
        JPanel paneeli = new JPanel(new BorderLayout());

        String reseptinTiedot = this.tulostus.tulostaResepti(haettava.getText());
        JLabel tuloste = new JLabel("<html>" + reseptinTiedot.replace("\n", "<br>") + "</html>");
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
