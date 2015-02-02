package ruokareseptit;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import ruokareseptit.domain.Kategoria;
import ruokareseptit.domain.Resepti;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;

    public Kayttoliittyma() {

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
        container.add(new JTextArea());
        container.add(luoValikko(), BorderLayout.NORTH);
    }

    private JPanel luoValikko() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(new JButton("Hae kategoria"));
        panel.add(new JButton("Hae repseti"));
        panel.add(new JButton("Lisää uusi"));
        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }

}
