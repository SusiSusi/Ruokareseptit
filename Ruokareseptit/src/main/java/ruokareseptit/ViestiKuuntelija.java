package ruokareseptit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ViestiKuuntelija implements ActionListener {

    private Tekstikayttoliittyma kayttis;
    private JPanel sisalto;
    private JButton kategoria;
    private JButton resepti;
    private JButton lisaa;

    public ViestiKuuntelija(Tekstikayttoliittyma kayttis, JButton kategoria, JButton resepti,
            JButton lisaa) {
        this.kayttis = kayttis;
        this.kategoria = kategoria;
        this.resepti = resepti;
        this.lisaa = lisaa;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == kategoria) {
            kayttis.tulostaKaikkiReseptit();
        } else if (ae.getSource() == resepti) {

        } else if (ae.getSource() == lisaa) {

        }
    }

}
