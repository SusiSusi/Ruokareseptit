package ruokareseptit;

import ruokareseptit.logiikka.Tekstikayttoliittyma;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ViestiKuuntelija implements ActionListener {

    private Tekstikayttoliittyma kayttis;
    private JPanel valikko;
    private JButton kategoria;
    private JButton resepti;
    private JButton lisaa;
    private Container container;

    public ViestiKuuntelija(JPanel valikko, Container container, Tekstikayttoliittyma kayttis, JButton kategoria, JButton resepti,
            JButton lisaa) {
        this.valikko = valikko;
        this.container = container;
        this.kayttis = kayttis;
        this.kategoria = kategoria;
        this.resepti = resepti;
        this.lisaa = lisaa;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == kategoria) {
//            kayttis.tulostaKategorianReseptienNimet("LIHA");
            kategorianHakuValikko();
        } else if (ae.getSource() == resepti) {

        } else if (ae.getSource() == lisaa) {

        }
    }

    public void kategorianHakuValikko() {
        JTextField u = new JTextField("");
//        kayttis.tulostaKaikkiReseptit();
    }
}
