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

        JPanel valikko = new JPanel(new GridLayout(1, 5));
        JButton haeResepti = new JButton("Hae resepti");
        JButton haeKategoria = new JButton("Hae kategoria");
        JButton lisaa = new JButton("Lisää uusi");
        JButton kaikkiReseptit = new JButton("Kaikki reseptit");
        JButton kaikkiKategoriat = new JButton("Kaikki kategoriat");
      
        valikko.add(haeResepti);
        valikko.add(haeKategoria);
        valikko.add(lisaa);
        valikko.add(kaikkiReseptit);
        valikko.add(kaikkiKategoriat);

        ViestiKuuntelija kuulija = new ViestiKuuntelija(valikko, container, haeKategoria, haeResepti, lisaa,
        kaikkiReseptit, kaikkiKategoriat, tulostus);

        haeResepti.addActionListener(kuulija);
        haeKategoria.addActionListener(kuulija);
        lisaa.addActionListener(kuulija);
        kaikkiReseptit.addActionListener(kuulija);
        kaikkiKategoriat.addActionListener(kuulija);

        container.add(new JLabel("TERVETULOA"), BorderLayout.NORTH);
        container.add(valikko, BorderLayout.SOUTH);
//        JTextButton b = new JText("k");
//        JTextField kl = new JTextField(teksti.tulostaToiminnot());
        
    }

//    private JPanel luoValikko() {
//        JPanel panel = new JPanel(new GridLayout(1, 3));
//        JButton kategoria = new JButton("Hae kategoria");
//        JButton resepti = new JButton("Hae repseti");
//        JButton lisaa = new JButton("Lisää uusi");
//        panel.add(kategoria);
//        panel.add(resepti);
//        panel.add(lisaa);
//        kategoria.addActionListener(new ViestiKuuntelija());
//        resepti.addActionListener(new ViestiKuuntelija());
//        lisaa.addActionListener(new ViestiKuuntelija());
//        
//        return panel;
//    }
    
    
    public JFrame getFrame() {
        return frame;
    }

}
