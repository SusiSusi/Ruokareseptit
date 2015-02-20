
package ruokareseptit.gui;

import java.awt.BorderLayout;
import java.awt.Container;
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
public class ReseptinHakuKuuntelija implements ActionListener{
    private JButton etsi;
    private JTextField haettava;
    private JPanel valikko;
    private Container container;
    private Tulostus tulostus;
    
    public ReseptinHakuKuuntelija(JButton etsi, JTextField haettava, JPanel valikko, Container container,
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
                JOptionPane.showMessageDialog(null, "Kirjoita resepti jonka haluat hakea!");
            } else {
            container.remove(2);
            etsiResepti();
            container.validate();
        }
    }
    
    public void etsiResepti() {
        this.valikko = new JPanel(new BorderLayout());
        System.out.println( this.tulostus.tulostaResepti(haettava.getText()));
        System.out.println("ja sana on  " + haettava.getText());
        JLabel text = new JLabel("<html>" + this.tulostus.tulostaResepti(haettava.getText()).replace("\n", "<br>") + "</html>");
        JScrollPane scrollPerustiedotKentat = new JScrollPane(text);
        valikko.add(scrollPerustiedotKentat);
        container.add(valikko);
    }
    
}
