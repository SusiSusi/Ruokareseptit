package ruokareseptit;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;

public class GraafinenKayttoliittyma implements Runnable {

    private JFrame frame;
    private Tekstikayttoliittyma teksti;
    

    public GraafinenKayttoliittyma() {

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
        container.add(new JLabel("Tervetuloa SusiSusin ruokaresepteihin!"), BorderLayout.NORTH);
        container.add(new JLabel("Täältä löydät repset!"));

        JPanel valikko = new JPanel(new GridLayout(1, 3));
        JButton kategoria = new JButton("Hae kategoria");
        JButton resepti = new JButton("Hae repseti");
        JButton lisaa = new JButton("Lisää uusi");
        valikko.add(kategoria);
        valikko.add(resepti);
        valikko.add(lisaa);
        
        ViestiKuuntelija kuulija = new ViestiKuuntelija(teksti, kategoria, resepti, lisaa);
        
        kategoria.addActionListener(kuulija);
        resepti.addActionListener(kuulija);
        lisaa.addActionListener(kuulija);
        
        container.add(valikko, BorderLayout.SOUTH);

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
