package ruokareseptit.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ruokareseptit.domain.Resepti;
import ruokareseptit.logiikka.LisayksetJaPoistot;
import ruokareseptit.logiikka.Tulostus;

/**
 * Luokka käsittelee reseptin lisäys -tapahtuman
 *
 * @author susisusi
 */
public class ReseptinLisayksenKuuntelija implements ListSelectionListener {

    private JList lista;
    private DefaultListModel listModel;

    private JButton lisaa;
    private JButton poista;
    private JButton tallennaJaLopeta;
    private JTextField ainesosaKentta;
    private JTextArea reseptinOhje;
//    static private String newline = "\n";

    private Container container;
    private Tulostus tulostus;
    private LisayksetJaPoistot lisayksetJaPoistot;

    private JTextField reseptinNimi;
    private String kategoriaValikko;

    /**
     * Konstruktori saa parametrikseen ValikkoNappaintenKuuntelija-luokalta
     * saadut tiedot
     *
     * @param container
     * @param tulostus
     * @param lisayksetJaPoistot
     */
    public ReseptinLisayksenKuuntelija(Container container, Tulostus tulostus, LisayksetJaPoistot lisayksetJaPoistot) {
        this.container = container;
        this.tulostus = tulostus;
        this.lisayksetJaPoistot = lisayksetJaPoistot;

        listModel = new DefaultListModel();
        lista = new JList(listModel);
        lista.setSelectionMode(
                ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lista.setSelectedIndex(0);
        lista.addListSelectionListener(this);

        lisaa = new JButton("Lisää");
//        addButton.setActionCommand(addString);
        lisaa.addActionListener(new AddButtonListener());

        poista = new JButton("Poista");
//        deleteButton.setActionCommand(deleteString);
        poista.addActionListener(new PoistaNappulanKuuntelija());
        poista.setEnabled(false);

        tallennaJaLopeta = new JButton("Tallenna ja lopeta");
//        tallennaJaLopeta.setActionCommand(tallennaJaLopetaString);
        tallennaJaLopeta.addActionListener(new TallennaJaLopetaKuuntelija());

        ainesosaKentta = new JTextField(30);
        ainesosaKentta.addActionListener(new AddButtonListener());

    }

    /**
     * Metodi alustaa paneelin ulkonäön
     */
    public void alusta() {
        JPanel paneeli = new JPanel(new BorderLayout());
//        this.valikko = new JPanel(new BorderLayout());
        JPanel nappulaPaneeli = new JPanel();
        nappulaPaneeli.add(new JLabel("Ainesosat (esim. 1 kpl, tomaatti) "));
        nappulaPaneeli.add(ainesosaKentta);
        nappulaPaneeli.add(lisaa);
        nappulaPaneeli.add(poista);

        JPanel tallennaPaneeli = new JPanel();
        tallennaPaneeli.add(tallennaJaLopeta);

        JLabel nimiKentta = new JLabel("Reseptin nimi: ");
        this.reseptinNimi = new JTextField();

        JLabel kategorianValikko = new JLabel("Kategoria: ");
        String[] kategoriat = this.tulostus.tulostaKaikkiKategoriatValikkoonSopiviksi();
        this.kategoriaValikko = kategoriat[0];
        JComboBox kategorioidenValikko = new JComboBox(kategoriat);
        kategorioidenValikko.addActionListener(new ValikonKuuntelija());

        reseptinOhje = new JTextArea("Kirjoita tähän valmistusohje");

        JScrollPane scrollaajaListaan = new JScrollPane(lista);
        JScrollPane scrollaajaOhjeeseen = new JScrollPane(reseptinOhje);

        JSplitPane nimiKentat = new JSplitPane(JSplitPane.WIDTH, nimiKentta, reseptinNimi);
        JSplitPane kategoriaKentat = new JSplitPane(JSplitPane.WIDTH, kategorianValikko, kategorioidenValikko);
        JSplitPane nimiJaKategoriaKentat = new JSplitPane(JSplitPane.VERTICAL_SPLIT, nimiKentat, kategoriaKentat);

        JSplitPane listaJaOhjeKentat = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                scrollaajaListaan, scrollaajaOhjeeseen);
        JSplitPane ainesosatJaOhje = new JSplitPane(JSplitPane.VERTICAL_SPLIT, nappulaPaneeli, listaJaOhjeKentat);

        listaJaOhjeKentat.setResizeWeight(0.3);

        JScrollPane scrollataanPaneeli = new JScrollPane(paneeli);
        paneeli.add(nimiJaKategoriaKentat, BorderLayout.PAGE_START);
        paneeli.add(ainesosatJaOhje, BorderLayout.CENTER);
        paneeli.add(tallennaPaneeli, BorderLayout.SOUTH);
        container.add(scrollataanPaneeli);
        container.validate();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (lista.getSelectedIndex() == -1) {
                poista.setEnabled(false);
                ainesosaKentta.setText("");

            } else if (lista.getSelectedIndices().length > 1) {
                poista.setEnabled(true);

            } else {
                poista.setEnabled(true);
            }
        }
    }

    class PoistaNappulanKuuntelija implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            ListSelectionModel lsm = lista.getSelectionModel();
            int ensimmainenValittu = lsm.getMinSelectionIndex();
            int viimeinenValittu = lsm.getMaxSelectionIndex();
            listModel.removeRange(ensimmainenValittu, viimeinenValittu);

            int koko = listModel.size();

            if (koko == 0) {
                poista.setEnabled(false);

            } else {
                if (ensimmainenValittu == listModel.getSize()) {
                    ensimmainenValittu--;
                }
                lista.setSelectedIndex(ensimmainenValittu);
            }
        }
    }

    class TallennaJaLopetaKuuntelija implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String uudenReseptinNimi = reseptinNimi.getText();
            String onkoReseptinNimiVarattu = tulostus.tulostaResepti(uudenReseptinNimi);
            if (uudenReseptinNimi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Reseptin nimi on pakollinen tieto!");
            } else if (listModel.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lisää reseptiin ainesosat!");
            } else if (!onkoReseptinNimiVarattu.equals("Reseptiä ei löytynyt.")){
                JOptionPane.showMessageDialog(null, "Resepti nimellä " + uudenReseptinNimi 
                + " löytyy jo tietokannasta. Muuta reseptin nimeä.");     
            }else {
                String[] ainesosat = new String[listModel.size()];
                listModel.copyInto(ainesosat);

                Resepti uusiResepti = new Resepti(uudenReseptinNimi);
                lisayksetJaPoistot.lisaaReseptiinAinesosat(uusiResepti, ainesosat);
                uusiResepti.setOhje(reseptinOhje.getText().replaceAll("[\\t\\n\\r]+", " "));

                try {
                    lisayksetJaPoistot.lisaaUusiResepti(kategoriaValikko, uusiResepti);
                } catch (IOException ex) {
                    Logger.getLogger(ReseptinLisayksenKuuntelija.class.getName()).log(Level.SEVERE, null, ex);
                }

                container.remove(2);
                JPanel paneeli = new JPanel(new BorderLayout());
                paneeli.add(new JLabel("Resepti lisätty onnistuneesti!"));
                container.add(paneeli);
                container.validate();
            }

        }
    }

    public class ValikonKuuntelija implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            kategoriaValikko = (String) cb.getSelectedItem();
        }
    }

    class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (ainesosaKentta.getText().equals("")) {
                return;
            }
            if (listModel.contains(ainesosaKentta.getText())) {
                JOptionPane.showMessageDialog(null, "Ainesosa on jo lisätty!");

            } else {
                int size = listModel.getSize();
                listModel.addElement(ainesosaKentta.getText());
                lista.setSelectedIndex(size);
                ainesosaKentta.setText("");
            }
        }
    }
}
