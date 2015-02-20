package ruokareseptit.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import ruokareseptit.logiikka.Tulostus;

/**
 *
 * @author susisusi
 */
public class KategorianHakuKuuntelija implements ActionListener {

    private JButton etsi;
    private JTextField haettava;
    private JPanel valikko;
    private Container container;
    private Tulostus tulostus;

    public KategorianHakuKuuntelija(JButton etsi, JTextField haettava, JPanel valikko, Container container,
            Tulostus tulostus) {
        this.etsi = etsi;
        this.haettava = haettava;
        this.valikko = valikko;
        this.container = container;
        this.tulostus = tulostus;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.haettava.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Kirjoita kategoria jonka haluat hakea!");
        } else {
            container.remove(2);
            etsiKategoria();
            container.validate();
        }
    }

    public void etsiKategoria() {
        this.valikko = new JPanel(new GridLayout(8,1));
        System.out.println(this.tulostus.tulostaResepti(haettava.getText()));
        System.out.println("ja sana on  " + haettava.getText());
        JLabel text = new JLabel("<html>" + this.tulostus.tulostaKategorianReseptienNimet(haettava.getText()).replace("\n", "<br>") + "</html>");
                
        JScrollPane scrollPerustiedotKentat = new JScrollPane(text);
        valikko.add(scrollPerustiedotKentat);
        
        this.valikko.add(new JLabel("Haluatko tulostaa reseptin? Kirjoita reseptin nimi"));
        JTextField nimi = new JTextField();
        valikko.add(nimi);
        
        JButton hae = new JButton("Hae");
        this.valikko.add(hae);
        hae.addActionListener(new ReseptinHakuKuuntelija(hae, nimi, this.valikko,
                this.container, this.tulostus));

        container.add(valikko);
    }

}
