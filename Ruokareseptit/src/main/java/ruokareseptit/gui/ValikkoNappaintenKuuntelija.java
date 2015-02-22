package ruokareseptit.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION;
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
    private String[] ainesosat;
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

    public void lisaaResepti() {
        AinesosienLisays li = new AinesosienLisays(this.valikko, this.container, this.tulostus,
        this.lisayksetJaPoistot);
        li.alusta();
//        this.valikko = new JPanel(new BorderLayout());
//        this.valikko = new JPanel(new GridLayout(16, 1));
//        this.valikko.add(new JLabel("Uuden reseptin lisäys"));
//        valikko.add(new JLabel(""));
//
//        this.valikko.add(new JLabel("Nimi: "));
//        JTextField nimi = new JTextField();
//        nimi.setSize(20, 40);
//        this.valikko.add(nimi);
//
//        this.valikko.add(new JLabel("Kategoria: "));
//        String[] kategoriat = this.tulostus.tulostaKaikkiKategoriatValikkoonSopiviksi();
//        JComboBox kategoriaValikko = new JComboBox(kategoriat);
//        kategoriaValikko.setSelectedIndex(kategoriat.length);
//        this.valikko.add(kategoriaValikko);
//
//        JLabel raakaAineet = new JLabel("Raaka-aine");
//        JTextField raakaAine = new JTextField("");
//        JLabel maarat = new JLabel("Määrä");
//        JTextField maara = new JTextField("");
//        DefaultListModel lista = new DefaultListModel();
////        lista.set(0, new JTextField(""));
//        JList raakaAineLista = new JList(lista);
//        raakaAineLista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        raakaAineLista.setSelectedIndex(0);
//        JScrollPane vierittaja = new JScrollPane(raakaAineLista);
//        JButton lisaa = new JButton("Lisää");
//        lisaa.addActionListener(new AinesosaLisaaNappula());
//        this.valikko.add(lisaa);
//
//        valikko.add(raakaAineLista);
//        JLabel ohjeenLisays = new JLabel("Valmistusohje");
//        JTextArea ohje = new JTextArea();
//        JScrollPane rullaus = new JScrollPane(ohje);
//        JButton lisaaNappi = new JButton("Lisää resepti");
//
//        this.valikko.add(raakaAineet);
//        this.valikko.add(raakaAine);
//        this.valikko.add(maarat);
//        this.valikko.add(maara);
//        this.valikko.add(ohjeenLisays);
//        this.valikko.add(rullaus);
//        this.valikko.add(raakaAineLista);
//        this.valikko.add(vierittaja);
//        for (int i = 0; i < 4; i++) {
//        valikko.add(new JLabel(""));
//    }
//
//        this.valikko.add(lisaaNappi);
//        this.valikko.add(poistaNappi, BorderLayout.EAST);
//        this.container.add(valikko);
    }
    
    public void palautaAinesosat(String[] ainesosat) {
        this.ainesosat = ainesosat;
    }

//    public class AinesosaLisaaNappula implements ActionListener {

//        public void actionPerformed(ActionEvent e) {
//            AinesosienLisays li = new AinesosienLisays();
//            JComponent newContentPane = new AinesosienLisays();
//            newContentPane.setMinimumSize(
//                    new Dimension(
//                            newContentPane.getPreferredSize().width,
//                            100));
//            valikko.add(li);
//            container.add(valikko);
//        }

//        public void actionPerformed(ActionEvent e) {
//            JFrame frame = new JFrame("Ainesosien lisäys");
////        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            //Create and set up the content pane.
//            JComponent newContentPane = new AinesosienLisays();
//            newContentPane.setOpaque(true); //content panes must be opaque
//            frame.setContentPane(newContentPane);
//
//        //Don't let the content pane get too small.
//            //(Works if the Java look and feel provides
//            //the window decorations.)
//            newContentPane.setMinimumSize(
//                    new Dimension(
//                            newContentPane.getPreferredSize().width,
//                            100));
//
//            //Display the window.
//            frame.pack();
//            frame.setVisible(true);
//        }
//    }

}
