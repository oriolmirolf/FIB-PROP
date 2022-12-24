package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.FormatNoReconegut;
import FONTS.src.main.domain.classes.exceptions.NoExisteixAutor;
import FONTS.src.main.domain.classes.exceptions.NoExisteixDocument;
import FONTS.src.main.domain.classes.exceptions.NumDocumentsIncorrecte;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Classe del JPanel que es mostra quan volem cercar Documents semblants un donat.
 * @author Oriol Miró López-Feliu (oriol.miro.lopez-feliu@estudiantat.upc.edu)
 */
public class PanelCercarDocSemblants extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Cercar Documents Semblants");
    /** Panell pel JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el Jlabel i el JSpinner del Número de Documents */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton i el JTextField de la selecció del path */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JCombooBox del Mètode de Cerca */
    private JPanel p3 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton de Cercar */
    private JPanel p4 = new JPanel(new FlowLayout());
    /** Label pel Número de Documents */
    private JLabel lK = new JLabel("Selecciona el número de documents a cercar:");
    /** Spinner pel Número de Documents */
    private JSpinner sK = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
    /** Botó per obrir l'explorador d'arxius */
    private JButton bChooser = new JButton("Selecciona el path");
    /** FileChooser per escollir el path del Document */
    private JFileChooser chooser = new JFileChooser();
    /** TextField per mostrar el path escollit */
    private JTextField tfPath = new JTextField(20);
    /** Label pel Mètode de Cerca */
    private JLabel lMetode = new JLabel("Escull el mètode:");
    /** ComboBox pel Mètode de Cerca */
    private JComboBox cbMetode = new JComboBox(new String[]{"TFIDF", "BM25"});
    /** Button per confirmar la cerca de Documents */
    private JButton bCercar = new JButton("Cercar Documents");
    /** Separador de la zona d'input de la consulta i la zona on es mostren els seus resultats */
    private JSeparator separator = new JSeparator();
    /** Panel on es mostraran els resultats de la consulta */
    private JPanel pDocuments = new JPanel(new CardLayout());
    /** Panel auxiliar per poder fer la cerca de Documents de manera dinàmica */
    private JPanel pAux = new JPanel();

    /**
     * Creadora per defecte.
     */
    public PanelCercarDocSemblants() {

        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        // Panell 1
        p1.add(lK); p1.add(sK);
        // Augmentem la mida de l'Spinner
        // 1. Obtenir l'editor del nostre Spinner
        Component editor = sK.getEditor();
        // 2. Obtenim el text field de l'Editor
        JFormattedTextField jftf = ((JSpinner.DefaultEditor) editor).getTextField();
        // 3. Establim la mida del nostre text field
        jftf.setColumns(3);

        // bChooser
        p2.add(bChooser);
        bChooser.addActionListener(e -> {
            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File arxiu = chooser.getSelectedFile();
                String path = arxiu.getPath();
                tfPath.setText(path);
            }
        });

        // FileChooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("OJMJ", "ojmj");
        chooser.setDialogTitle("Selecciona el Document a eliminar");
        chooser.setFileFilter(filter);

        // TextField Path
        p2.add(tfPath);
        tfPath.setEditable(false);

        // Panell 2
        cbMetode.setSelectedIndex(-1);
        p3.add(lMetode);
        p3.add(cbMetode);


        p4.add(bCercar);

        bCercar.addActionListener(e -> {
            int metode = cbMetode.getSelectedIndex();
            if (metode == -1 || tfPath.getText().isBlank()) return;
            try {
                ArrayList<ArrayList<String>> arr = CtrlPresentacio.llistarKDocumentsSemblantsD((Integer)sK.getValue(), tfPath.getText(), metode);
                CardLayout cl = (CardLayout)(pDocuments.getLayout());
                String[] metodesOrdenacioDocuments = {"Per semblança", "Alfabèticament per autor i títol", "Alfabèticament per títol i autor", "Per mida", "Per data de creació"};
                pAux = CtrlPresentacio.panelDocuments(arr, metodesOrdenacioDocuments);
                pDocuments.add(pAux, "aux");
                cl.show(pDocuments, "aux");
            } catch (NumDocumentsIncorrecte ex) {
                JOptionPane.showMessageDialog(this, "ERROR: El número de documents a cercar ha de ser positiu.", "Error Cerca Documents Semblants", JOptionPane.ERROR_MESSAGE);
            } catch (NoExisteixDocument ex) {
                JOptionPane.showMessageDialog(this, "ERROR: No existeix cap document amb el path indicat.", "Error Cerca Documents Semblants", JOptionPane.ERROR_MESSAGE);
            } catch (FormatNoReconegut ex) {
                JOptionPane.showMessageDialog(this, "ERROR: El tipus de document no es reconegut pel sistema.", "Error Cerca Documents Semblants", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Zona dels Resultats de la Consulta
        pDocuments.add(pAux, "aux");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(p0);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(separator);
        this.add(pDocuments);
    }
}
