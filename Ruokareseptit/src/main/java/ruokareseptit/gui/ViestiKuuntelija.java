package ruokareseptit.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ruokareseptit.logiikka.Tulostus;

public class ViestiKuuntelija implements ActionListener {

    private JPanel valikko;
    private JButton haeKategoria;
    private JButton haeResepti;
    private JButton lisaa;
    private JButton kaikkiReseptit;
    private JButton kaikkiKategoriat;
    private Container container;
    private Tulostus tulostus;

    public ViestiKuuntelija(JPanel valikko, Container container, JButton kategoria, JButton resepti,
            JButton lisaa, JButton kaikkiReseptit, JButton kaikkiKategoriat, Tulostus tulostus) {
        this.valikko = valikko;
        this.container = container;
        this.haeKategoria = kategoria;
        this.haeResepti = resepti;
        this.lisaa = lisaa;
        this.kaikkiReseptit = kaikkiReseptit;
        this.kaikkiKategoriat = kaikkiKategoriat;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == haeKategoria) {
//            kayttis.tulostaKategorianReseptienNimet("LIHA");
            kategorianHakuValikko();
        } else if (ae.getSource() == haeResepti) {

        } else if (ae.getSource() == lisaa) {

        }
    }

    public void kategorianHakuValikko() {
        JLabel kategoriat = new JLabel(tulostus.tulostaKaikkiKategoriat());
        System.out.println(tulostus.tulostaKaikkiKategoriat());
//        kayttis.tulostaKaikkiReseptit();
    }
}
