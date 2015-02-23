package ruokareseptit.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ruokareseptit.logiikka.LisayksetJaPoistot;
import ruokareseptit.logiikka.Tulostus;

/**
 * Luokka käsittelee valikkonäppäinten kuuntelun
 *
 * @author susisusi
 */
public class ValikkoNappaintenKuuntelija implements ActionListener {

    private JPanel paneeli;
    private JButton haeKategoria;
    private JButton haeResepti;
    private JButton lisaa;
    private JButton kaikkiReseptit;
    private JButton kaikkiKategoriat;
    private Container container;
    private Tulostus tulostus;
    private LisayksetJaPoistot lisayksetJaPoistot;

    /**
     * Konstruktori saa parametrikseen GraafinenKayttoliittyma-luokalta saadut
     * tiedot
     *
     * @param container
     * @param kategoria
     * @param resepti
     * @param lisaa
     * @param kaikkiReseptit
     * @param kaikkiKategoriat
     * @param tulostus
     * @param lisayksetJaPoistot
     */
    public ValikkoNappaintenKuuntelija(Container container, JButton kategoria, JButton resepti,
            JButton lisaa, JButton kaikkiReseptit, JButton kaikkiKategoriat, Tulostus tulostus,
            LisayksetJaPoistot lisayksetJaPoistot) {
        this.container = container;
        this.haeKategoria = kategoria;
        this.haeResepti = resepti;
        this.lisaa = lisaa;
        this.kaikkiReseptit = kaikkiReseptit;
        this.kaikkiKategoriat = kaikkiKategoriat;
        this.tulostus = tulostus;
        this.lisayksetJaPoistot = lisayksetJaPoistot;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == kaikkiKategoriat) {
            container.remove(2);
            kaikkiKategoriat();
            container.validate();
        } else if (ae.getSource() == haeResepti) {
            container.remove(2);
            tulostaResepti();
            container.validate();
        } else if (ae.getSource() == lisaa) {
            container.remove(2);
            lisaaResepti();
            container.validate();
        } else if (ae.getSource() == kaikkiReseptit) {
            container.remove(2);
            kaikkiReseptit();
            container.validate();
        } else if (ae.getSource() == haeKategoria) {
            container.remove(2);
            tulostaKategoria();
            container.validate();
        }
    }

    private void kaikkiKategoriat() {
        this.paneeli = new JPanel(new BorderLayout());
        JLabel tuloste = new JLabel("<html>" + this.tulostus.tulostaKaikkiKategoriat().replace("\n", "<br>") + "</html>");
        ImageIcon kuva = new ImageIcon("src/main/java/ruokareseptit/gui/vintagehousewifecooking.jpg");
        paneeli.add(tuloste, BorderLayout.WEST);
        paneeli.add(new JLabel(kuva), BorderLayout.CENTER);
        container.add(paneeli);
    }

    private void kaikkiReseptit() {
        this.paneeli = new JPanel(new BorderLayout());
        JLabel tuloste = new JLabel("<html>" + this.tulostus.tulostaKaikkiReseptit().replace("\n", "<br>") + "</html>");
        JScrollPane scrollaaReseptit = new JScrollPane(tuloste);
        paneeli.add(scrollaaReseptit);
        container.add(paneeli);
    }

    private void tulostaResepti() {
        this.paneeli = new JPanel(new GridLayout(10, 1));
        this.paneeli.add(new JLabel("Reseptin haku"));

        this.paneeli.add(new JLabel("Haettavan reseptin nimi "));
        JTextField nimi = new JTextField();
        this.paneeli.add(nimi);

        JButton etsi = new JButton("Etsi");
        this.paneeli.add(etsi);

        this.container.add(paneeli);
        etsi.addActionListener(new ReseptinHakuKuuntelija(nimi,
                this.container, this.tulostus, this.lisayksetJaPoistot));
    }

    private void tulostaKategoria() {
        this.paneeli = new JPanel(new GridLayout(10, 1));
        this.paneeli.add(new JLabel("Kategorian haku"));

        this.paneeli.add(new JLabel("Haettavan kategorian nimi "));
        JTextField nimi = new JTextField();
        this.paneeli.add(nimi);

        JButton etsi = new JButton("Etsi");
        this.paneeli.add(etsi);

        this.container.add(paneeli);
        etsi.addActionListener(new KategorianHakuKuuntelija(nimi,
                this.container, this.tulostus, this.lisayksetJaPoistot));
    }

    private void lisaaResepti() {
        ReseptinLisayksenKuuntelija li = new ReseptinLisayksenKuuntelija(this.container, this.tulostus,
                this.lisayksetJaPoistot);
        li.alusta();
    }

}
