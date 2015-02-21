package ruokareseptit.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ruokareseptit.logiikka.Lisaykset;
import ruokareseptit.logiikka.Tulostus;
import ruokareseptit.tietokanta.Tietovarasto;

public class ValikkoNappaintenKuuntelija implements ActionListener {

    private JPanel valikko;
    private JButton haeKategoria;
    private JButton haeResepti;
    private JButton lisaa;
    private JButton kaikkiReseptit;
    private JButton kaikkiKategoriat;
    private Container container;
    private Tulostus tulostus;
    private Lisaykset lisayksetJaPoistot;
//    private JLabel tulostusKentta;
//    private JButton etsi;
//    private String re;
//    private JScrollPane scrollPerustiedotKentat;

    public ValikkoNappaintenKuuntelija(JPanel valikko, Container container, JButton kategoria, JButton resepti,
            JButton lisaa, JButton kaikkiReseptit, JButton kaikkiKategoriat, Tulostus tulostus,
            Lisaykset lisayksetJaPoistot) {
        this.valikko = valikko;
        this.container = container;
        this.haeKategoria = kategoria;
        this.haeResepti = resepti;
        this.lisaa = lisaa;
        this.kaikkiReseptit = kaikkiReseptit;
        this.kaikkiKategoriat = kaikkiKategoriat;
//        this.tulostusKentta = tulostusKentta;
        this.tulostus = tulostus;
        this.lisayksetJaPoistot = lisayksetJaPoistot;
//        this.scrollPerustiedotKentat = scrollPerustiedotKentat;
    }

//    public ValikkoNappaintenKuuntelija(JButton etsi, String reseptinNimi) {
//        this.etsi = etsi;
//        this.re = reseptinNimi;
//    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == kaikkiKategoriat) {
            container.remove(2);
            kaikkiKategoriat();
            container.validate();
        } else if (ae.getSource() == haeResepti) {
            container.remove(2);

            tulostaResepti();
//            container.remo;
            container.validate();

        } else if (ae.getSource() == lisaa) {
        } else if (ae.getSource() == kaikkiReseptit) {
            container.remove(2);
            kaikkiReseptit();
            container.validate();
        }
        else if (ae.getSource() == haeKategoria) {
             container.remove(2);
           tulostaKategoria();
            container.validate();
        }
    }

    public void kaikkiKategoriat() {
        this.valikko = new JPanel(new BorderLayout());
        JLabel text = new JLabel("<html>" + this.tulostus.tulostaKaikkiKategoriat().replace("\n", "<br>") + "</html>");
        valikko.add(text);
        container.add(valikko);
    }

    public void kaikkiReseptit() {
        this.valikko = new JPanel(new BorderLayout());
        JLabel text = new JLabel("<html>" + this.tulostus.tulostaKaikkiReseptit().replace("\n", "<br>") + "</html>");
        JScrollPane scrollPerustiedotKentat = new JScrollPane(text);
        valikko.add(scrollPerustiedotKentat);
//        valikko.add(text);
//        container.add(scrollPerustiedotKentat, BorderLayout.CENTER);
        container.add(valikko);
    }

    public void tulostaResepti() {
        this.valikko = new JPanel(new GridLayout(10, 1));
        this.valikko.add(new JLabel("Reseptin haku"));

        this.valikko.add(new JLabel("Haettavan reseptin nimi "));
        JTextField nimi = new JTextField();
        this.valikko.add(nimi);
        JButton etsi = new JButton("Etsi");
        this.valikko.add(etsi);
        this.container.add(valikko);
        etsi.addActionListener(new ReseptinHakuKuuntelija(etsi, nimi, this.valikko,
                this.container, this.tulostus, this.lisayksetJaPoistot));
//        container.validate();

    }

    public void tulostaKategoria() {
        this.valikko = new JPanel(new GridLayout(10, 1));
        this.valikko.add(new JLabel("Kategorian haku"));

        this.valikko.add(new JLabel("Haettavan kategorian nimi "));
        JTextField nimi = new JTextField();
        this.valikko.add(nimi);
        JButton etsi = new JButton("Etsi");
        this.valikko.add(etsi);
        this.container.add(valikko);
        etsi.addActionListener(new KategorianHakuKuuntelija(etsi, nimi, this.valikko,
                this.container, this.tulostus, this.lisayksetJaPoistot));
    }

//    public void resepti(String nimi) {
//        this.container.add(scrollPerustiedotKentat, BorderLayout.CENTER);
//        tulostusKentta.setText(this.tulostus.tulostaResepti(nimi));
//    }
}
