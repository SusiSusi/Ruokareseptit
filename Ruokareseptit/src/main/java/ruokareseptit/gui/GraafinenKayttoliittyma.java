package ruokareseptit.gui;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.logiikka.Tulostus;
import ruokareseptit.tietokanta.Tietovarasto;

public class GraafinenKayttoliittyma implements Runnable {

    private JFrame frame;
    private Tulostus tulostus;
    private Tietovarasto tietovarasto;
//    private JLabel sisalto;
    private JPanel toinen;
//    private JScrollPane scrollPerustiedotKentat;

    public GraafinenKayttoliittyma() {
        this.tietovarasto = new Tietovarasto();

        this.tulostus = new Tulostus(this.tietovarasto.haeKategoriat());
    }

    @Override
    public void run() {
        frame = new JFrame("Ruokareseptit");
        frame.setPreferredSize(new Dimension(1000, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
//        GridLayout leiska = new GridLayout(3, 1);
//        container.setLayout(leiska);
//
//        JPanel valikko = new JPanel(new GridLayout(1, 5));
//        JButton haeResepti = new JButton("Hae resepti");
//        JButton haeKategoria = new JButton("Hae kategoria");
//        JButton lisaa = new JButton("Lisää uusi");
//        JButton kaikkiReseptit = new JButton("Kaikki reseptit");
//        JButton kaikkiKategoriat = new JButton("Kaikki kategoriat");

//        this.sisalto = new JLabel();
        this.toinen = new JPanel();
//        tulostusKentta.setEnabled(false);
//       scrollPerustiedotKentat = new JScrollPane(this.sisalto);
//        this.sisalto.add(scrollPerustiedotKentat);
//        scrollPerustiedotKentat.setViewportView(this.sisalto);

//        valikko.add(haeResepti);
//        valikko.add(haeKategoria);
//        valikko.add(lisaa);
//        valikko.add(kaikkiReseptit);
//        valikko.add(kaikkiKategoriat);
//
//        ViestiKuuntelija kuulija = new ViestiKuuntelija(this.sisalto, this.toinen, container, haeKategoria, haeResepti, lisaa,
//                kaikkiReseptit, kaikkiKategoriat, this.tulostus);
//
//        haeResepti.addActionListener(kuulija);
//        haeKategoria.addActionListener(kuulija);
//        lisaa.addActionListener(kuulija);
//        kaikkiReseptit.addActionListener(kuulija);
//        kaikkiKategoriat.addActionListener(kuulija);
        container.add(new JLabel("TERVETULOA"), BorderLayout.NORTH);
//        container.add(scrollPerustiedotKentat, BorderLayout.CENTER);
        container.add(luoValikko(container), BorderLayout.SOUTH);
//        container.add(this.toinen, BorderLayout.CENTER);
        container.add(new JLabel("dlfkdlfk"), BorderLayout.CENTER);

//        JTextButton b = new JText("k");
//        JTextField kl = new JTextField(teksti.tulostaToiminnot());
    }

    private JPanel luoValikko(Container container) {
        JPanel panel = new JPanel(new GridLayout(1, 5));
        JButton haeKategoria = new JButton("Hae kategoria");
        JButton haeResepti = new JButton("Hae repseti");
        JButton lisaa = new JButton("Lisää uusi");
        JButton kaikkiReseptit = new JButton("Kaikki reseptit");
        JButton kaikkiKategoriat = new JButton("Kaikki kategoriat");

        panel.add(haeResepti);
        panel.add(haeKategoria);
        panel.add(lisaa);
        panel.add(kaikkiReseptit);
        panel.add(kaikkiKategoriat);

        ViestiKuuntelija kuulija = new ViestiKuuntelija(this.toinen, container, haeKategoria, haeResepti, lisaa,
                kaikkiReseptit, kaikkiKategoriat, this.tulostus);

        haeKategoria.addActionListener(kuulija);
        haeResepti.addActionListener(kuulija);
        lisaa.addActionListener(kuulija);
        kaikkiReseptit.addActionListener(kuulija);
        kaikkiKategoriat.addActionListener(kuulija);

        return panel;
    }

    public void luoLisaysLomake(Container container) {
        this.toinen = new JPanel(new GridLayout(10, 1));
        this.toinen.add(new JLabel("Uuden tuotteen lisäys"));

        this.toinen.add(new JLabel("Nimi: "));
        JTextField nimi = new JTextField();
        this.toinen.add(nimi);

        this.toinen.add(new JLabel("Kuvaus: "));
        JTextField kuvaus = new JTextField();
        this.toinen.add(kuvaus);

        JButton lisaa = new JButton("Lisaa tuote!");
//        LisaysLomakeKasittelija kasittelija = new LisaysLomakeKasittelija(nimi,kuvaus);
//        lisaa.addActionListener(kasittelija);
        this.sisalto.add(lisaa);

        container.add(this.sisalto, BorderLayout.CENTER);
    }

    public JFrame getFrame() {
        return frame;
    }

}
