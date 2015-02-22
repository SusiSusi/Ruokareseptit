package ruokareseptit.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ruokareseptit.logiikka.Lisaykset;
import ruokareseptit.logiikka.Tulostus;

public class RaakaAineenLisayksenKuuntelija implements ActionListener {

    private JButton lisaa;
    private JTextField reseptinNimo;
    private JTextField raakaAine;
    private JTextField maara;
    private JComboBox kategoria;
    private JPanel valikko;
    private Container container;
    private Tulostus tulostus;
    private Lisaykset lisayksetJaPoistot;
    private String[] raakaAineet;

    public RaakaAineenLisayksenKuuntelija(JButton lisaa, JTextField reseptinNimi, JTextField raakaAine, 
            JComboBox kategoria, JTextField maara, JPanel valikko, Container container,
            Tulostus tulostus, Lisaykset lisayksetJaPoistot) {
        this.lisaa = lisaa;
        this.reseptinNimo = reseptinNimi;
        this.raakaAine = raakaAine;
        this.maara = maara;
        this.kategoria = kategoria;
        this.valikko = valikko;
        this.container = container;
        this.tulostus = tulostus;
        this.lisayksetJaPoistot = lisayksetJaPoistot;
    }
    
    public RaakaAineenLisayksenKuuntelija(String[] raakaAineet) {
        this.raakaAineet = raakaAineet;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (this.raakaAine.getText().isEmpty() || this.reseptinNimo.getText().isEmpty() || this.maara.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Täytä kaikki kentät!");
        }
        int choice = JOptionPane.showOptionDialog(null,
                "Haluatko varmasti poistaa reseptin " + this.reseptinNimo.getText() + "?",
                "Reseptin poisto",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);

        // interpret the user's choice
        if (choice == JOptionPane.YES_OPTION) {
            container.remove(2);
//            poistetaanResepti();
            container.validate();
        }
    }
}
