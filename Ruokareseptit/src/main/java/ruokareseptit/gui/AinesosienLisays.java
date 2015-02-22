package ruokareseptit.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ruokareseptit.domain.Resepti;
import ruokareseptit.logiikka.Lisaykset;
import ruokareseptit.logiikka.Tulostus;

public class AinesosienLisays implements ListSelectionListener {

    private JList list;
    private DefaultListModel listModel;
    private static final String addString = "Lisää";
    private static final String deleteString = "Poista";
    private static final String tallennaJaLopetaString = "Tallenna ja lopeta";

    private JButton addButton;
    private JButton deleteButton;
    private JButton tallennaJaLopeta;
    private JTextField nameField;
    private JTextArea log;
    static private String newline = "\n";

    private JScrollPane listScrollPane;

    private JPanel valikko;
    private Container container;
    private Tulostus tulostus;
    private Lisaykset lisayksetJaPoistot;

    private JTextField reseptinNimi;
    private String kategoriaValikko;

    public AinesosienLisays(JPanel valikko, Container container, Tulostus tulostus, Lisaykset lisayksetJaPoistot) {
//        super(new BorderLayout());

        this.valikko = valikko;
        this.container = container;
        this.tulostus = tulostus;
        this.lisayksetJaPoistot = lisayksetJaPoistot;

        listModel = new DefaultListModel();
//        listModel.addElement("1 kpl, tomaatti");

//        listModel.addListDataListener(new MyListDataListener());
        list = new JList(listModel);
        list.setSelectionMode(
                ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        listScrollPane = new JScrollPane(list);

        addButton = new JButton(addString);
        addButton.setActionCommand(addString);
        addButton.addActionListener(new AddButtonListener());

        deleteButton = new JButton(deleteString);
        deleteButton.setActionCommand(deleteString);
        deleteButton.addActionListener(
                new DeleteButtonListener());
        deleteButton.setEnabled(false);

        tallennaJaLopeta = new JButton(tallennaJaLopetaString);
        tallennaJaLopeta.setActionCommand(tallennaJaLopetaString);
        tallennaJaLopeta.addActionListener(
                new TallennaJaLopetaKuuntelija());

        nameField = new JTextField(30);
        nameField.addActionListener(new AddButtonListener());

//        JPanel buttonPane = new JPanel();
//        buttonPane.add(nameField);
//        buttonPane.add(addButton);
//        buttonPane.add(deleteButton);
//        buttonPane.add(tallennaJaLopeta);
//
//        log = new JTextArea(10, 20);
//        JScrollPane logScrollPane = new JScrollPane(log);
//        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
//                listScrollPane, logScrollPane);
//        splitPane.setResizeWeight(0.5);
//        this.valikko.add(buttonPane, BorderLayout.PAGE_START);
////        add(listScrollPane, BorderLayout.CENTER);
//        this.valikko.add(splitPane, BorderLayout.CENTER);
//        container.add(valikko);
    }

    public void alusta() {
//        container.remove(2);

        this.valikko = new JPanel(new BorderLayout());
        JPanel buttonPane = new JPanel();
        buttonPane.add(new JLabel("Ainesosat"));
        buttonPane.add(nameField);
        buttonPane.add(addButton);
        buttonPane.add(deleteButton);

        JPanel tallenna = new JPanel();
        tallenna.add(tallennaJaLopeta);

        JLabel nimi = new JLabel("Reseptin nimi: ");
        this.reseptinNimi = new JTextField();
//        nimi.setSize(2, 4);
//        this.valikko.add(nimi);

        JLabel valikko = new JLabel("Kategoria: ");
        String[] kategoriat = this.tulostus.tulostaKaikkiKategoriatValikkoonSopiviksi();
        JComboBox kategoriaValikko = new JComboBox(kategoriat);
        kategoriaValikko.addActionListener(new ValikonKuuntelija());
//        kategoriaValikko.setSelectedIndex(kategoriat.length);
//        this.valikko.add(kategoriaValikko);

        log = new JTextArea("Kirjoita tähän valmistusohje", 7, 4);
        JScrollPane logScrollPane = new JScrollPane(log);
        JSplitPane nimet = new JSplitPane(JSplitPane.WIDTH, nimi, reseptinNimi);
        JSplitPane split = new JSplitPane(JSplitPane.WIDTH, valikko, kategoriaValikko);
        JSplitPane slii = new JSplitPane(JSplitPane.VERTICAL_SPLIT, nimet, split);
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                listScrollPane, logScrollPane);
        JSplitPane ainesosatJaOhje = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPane, splitPane);
        splitPane.setResizeWeight(0.5);
        JScrollPane p = new JScrollPane(this.valikko);
        this.valikko.add(slii, BorderLayout.PAGE_START);
//        this.valikko.add(split, BorderLayout.NORTH);
        this.valikko.add(ainesosatJaOhje, BorderLayout.CENTER);
        this.valikko.add(tallenna, BorderLayout.SOUTH);

//        add(listScrollPane, BorderLayout.CENTER);
//        this.valikko.add(splitPane, BorderLayout.PAGE_END);
//        this.valikko.add(ohje, BorderLayout.CENTER);
//        this.valikko.add(logScrollPane, BorderLayout.SOUTH);
        container.add(p);
        container.validate();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection: disable delete, up, and down buttons.
                deleteButton.setEnabled(false);
                nameField.setText("");

            } else if (list.getSelectedIndices().length > 1) {
                //Multiple selection: disable up and down buttons.
                deleteButton.setEnabled(true);

            } else {
                //Single selection: permit all operations.
                deleteButton.setEnabled(true);
//                nameField.setText(list.getSelectedValue().toString());
            }
        }
    }

    class DeleteButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            ListSelectionModel lsm = list.getSelectionModel();
            int firstSelected = lsm.getMinSelectionIndex();
            int lastSelected = lsm.getMaxSelectionIndex();
            listModel.removeRange(firstSelected, lastSelected);

            int size = listModel.size();

            if (size == 0) {
                //List is empty: disable delete, up, and down buttons.
                deleteButton.setEnabled(false);

            } else {
                //Adjust the selection.
                if (firstSelected == listModel.getSize()) {
                    //Removed item in last position.
                    firstSelected--;
                }
                list.setSelectedIndex(firstSelected);

            }
        }
    }

    class TallennaJaLopetaKuuntelija implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (reseptinNimi.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Reseptin nimi on pakollinen tieto!");
            } else {

                String[] ainesosat = new String[listModel.size()];
                listModel.copyInto(ainesosat);
                for (int i = 0; i < ainesosat.length; i++) {
                    System.out.println(ainesosat[i]);
                }

                Resepti uusiResepti = new Resepti(reseptinNimi.getText());

                for (int i = 0; i < ainesosat.length; i++) {
                    String[] osat = ainesosat[i].split(",");
                    String maara = osat[0];
                    String aine = osat[1];
                    uusiResepti.setAinesosa(aine, maara);
                }

                uusiResepti.setOhje(log.getText());
                try {
                    lisayksetJaPoistot.lisaaUusiResepti(kategoriaValikko, uusiResepti);
                } catch (IOException ex) {
                    Logger.getLogger(AinesosienLisays.class.getName()).log(Level.SEVERE, null, ex);
                }

                container.remove(2);
                valikko = new JPanel(new BorderLayout());
                valikko.add(new JLabel("Resepti lisätty onnistuneesti!"));
                container.add(valikko);
                container.validate();
            }

        }
    }

    public class ValikonKuuntelija implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            kategoriaValikko = (String) cb.getSelectedItem();

        }

    }

    class AddButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (nameField.getText().equals("")) {
                //User didn't type in a name...
                Toolkit.getDefaultToolkit().beep();
                return;
            }

            int index = list.getSelectedIndex();
            int size = listModel.getSize();

            //If no selection or if item in last position is selected,
            //add the new one to end of list, and select new one.
            if (index == -1 || (index + 1 == size)) {
                listModel.addElement(nameField.getText());
                list.setSelectedIndex(size);

                //Otherwise insert the new one after the current selection,
                //and select new one.
            } else {
                listModel.insertElementAt(nameField.getText(), index + 1);
                list.setSelectedIndex(index + 1);
            }
        }
    }
}
