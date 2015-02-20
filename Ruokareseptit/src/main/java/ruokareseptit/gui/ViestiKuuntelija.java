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
    private JLabel tulostusKentta;

    public ViestiKuuntelija(JLabel tulostusKentta, JPanel valikko, Container container, JButton kategoria, JButton resepti,
            JButton lisaa, JButton kaikkiReseptit, JButton kaikkiKategoriat, Tulostus tulostus) {
        this.valikko = valikko;
        this.container = container;
        this.haeKategoria = kategoria;
        this.haeResepti = resepti;
        this.lisaa = lisaa;
        this.kaikkiReseptit = kaikkiReseptit;
        this.kaikkiKategoriat = kaikkiKategoriat;
        this.tulostusKentta = tulostusKentta;
        this.tulostus = tulostus;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == haeKategoria) {
//            kayttis.tulostaKategorianReseptienNimet("LIHA");
            kategorianHakuValikko();
        } else if (ae.getSource() == haeResepti) {
            tulostaResepti();

        } else if (ae.getSource() == lisaa) {

        } else if (ae.getSource() == kaikkiReseptit) {
            kaikkiReseptit();
        }
    }

    public void kategorianHakuValikko() {
        this.tulostusKentta.setText("<html>" + this.tulostus.tulostaKaikkiKategoriat().replace("\n", "<br>") + "</html>");

    }

    public void kaikkiReseptit() {
        this.tulostusKentta.setText("<html>" + this.tulostus.tulostaKaikkiReseptit().replace("\n", "<br>") + "</html>");
    }

    public void tulostaResepti() {
       JPanel v = new JPanel(new GridLayout(3,3));
       v.add(new JLabel("nimi: "));
       container.add(v);
       
        
    }
}
