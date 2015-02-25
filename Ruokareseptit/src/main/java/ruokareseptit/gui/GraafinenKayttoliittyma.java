package ruokareseptit.gui;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;
import ruokareseptit.logiikka.LisayksetJaPoistot;
import ruokareseptit.logiikka.Tulostus;
import ruokareseptit.tietokanta.Tietovarasto;

/**
 * Graafinen käyttöliittymä reseptien lukua ja lisäystä varten.
 * @author susisusi
 */

public class GraafinenKayttoliittyma implements Runnable {

    private JFrame frame;
    private Tulostus tulostus;
    private Tietovarasto tietovarasto;
    private LisayksetJaPoistot lisayksetJaPoistot;

    /**
     * Konstruktori alustaa Tietovarasto-, Tulostus ja LisayksetJaPoistot -oliot
     */
    public GraafinenKayttoliittyma() {
        this.tietovarasto = new Tietovarasto();
        this.tulostus = new Tulostus(this.tietovarasto.haeKategoriat());
        this.lisayksetJaPoistot = new LisayksetJaPoistot(this.tietovarasto.haeKategoriat(), tietovarasto);
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
        JPanel paneeli = new JPanel(new BorderLayout());
        container.add(new JLabel("SuSin ruokareseptit"), BorderLayout.NORTH);
        container.add(luoValikko(container), BorderLayout.SOUTH);
        
        ImageIcon kuva = new ImageIcon("src/main/java/ruokareseptit/gui/vintagehousewifecooking.jpg");
        
        container.add(new JLabel(kuva), BorderLayout.CENTER);
    }

    private JPanel luoValikko(Container container) {
        JPanel paneeli = new JPanel(new GridLayout(1, 5));
        JButton haeKategoria = new JButton("Hae kategoria");
        JButton haeResepti = new JButton("Hae resepti");
        JButton lisaa = new JButton("Lisää uusi resepti");
        JButton kaikkiReseptit = new JButton("Kaikki reseptit");
        JButton kaikkiKategoriat = new JButton("Kaikki kategoriat");

        paneeli.add(haeResepti);
        paneeli.add(haeKategoria);
        paneeli.add(lisaa);
        paneeli.add(kaikkiReseptit);
        paneeli.add(kaikkiKategoriat);

        ValikkoNappaintenKuuntelija kuulija = new ValikkoNappaintenKuuntelija(container, haeKategoria, haeResepti, lisaa,
                kaikkiReseptit, kaikkiKategoriat, this.tulostus, this.lisayksetJaPoistot);

        haeKategoria.addActionListener(kuulija);
        haeResepti.addActionListener(kuulija);
        lisaa.addActionListener(kuulija);
        kaikkiReseptit.addActionListener(kuulija);
        kaikkiKategoriat.addActionListener(kuulija);

        return paneeli;
    }


    public JFrame getFrame() {
        return frame;
    }

}
