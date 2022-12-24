package FONTS.src.main.presentation.views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import FONTS.src.main.domain.classes.exceptions.NumDocumentsIncorrecte;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

/**
 * Classe del JPanel que es mostra quan volem cercar Documents rellevants a una sèrie de paraules.
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class PanelCercarDocRellevants extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Cercar Documents Rellevants a Paraules");
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el Jlabel i el JSpinner del Número de Documents */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JTextField de les Paraules */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JCombooBox del Mètode de Cerca */
    private JPanel p3 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton de Cercar */
    private JPanel p4 = new JPanel(new FlowLayout());
    /** Label pel Número de Documents */
    private JLabel lK = new JLabel("Selecciona el número de documents a cercar:");
    /** Spinner pel Número de Documents */
    private JSpinner sK = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
    /** Label per les Paraules */
    private JLabel lParaules = new JLabel("Escriu les paraules:");
    /** TextField per les Paraules */
    private JTextField tfParaules = new JTextField(20);
    /** Label pel Mètode de Cerca */
    private JLabel lMetode = new JLabel("Selecciona el mètode de cerca:");
    /** ComboBox pel Mètode de Cerca */
    private JComboBox cb = new JComboBox(new String[]{"TFIDF", "BM25"});
    /** Button per confirmar la Cerca de Documents */
    private JButton bCercar = new JButton("Cercar Documents");
    /** Separador de la zona d'input de la consulta i la zona on es mostren els seus resultats */
    private JSeparator separator = new JSeparator();
    /** Panel on es mostraràn els resultats de la consulta */
    private JPanel pDocuments = new JPanel(new CardLayout());
    /** Panel auxiliar per poder fer la cerca de Documents de manera dinàmica */
    private JPanel pAux = new JPanel();

    /**
     * Creadora per defecte.
     */
    public PanelCercarDocRellevants() {
        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        // Número de Documents
        p1.add(lK); p1.add(sK);
        // Augmentem la mida de l'Spinner
        // 1. Obtenir l'editor del nostre Spinner
        Component editor = sK.getEditor();
        // 2. Obtenim el text field de l'Editor
        JFormattedTextField jftf = ((JSpinner.DefaultEditor) editor).getTextField();
        // 3. Establim la mida del nostre text field
        jftf.setColumns(3);

        // Paraules
        p2.add(lParaules); p2.add(tfParaules);

        // Mètode de Cerca
        p3.add(lMetode); p3.add(cb);
        cb.setSelectedIndex(-1);

        // bCercar
        p4.add(bCercar);
        bCercar.addActionListener(e -> {
            if (cb.getSelectedIndex() == -1 || tfParaules.getText().isEmpty()) return;
            try {
                String[] arr = tfParaules.getText().split(" ");
                ArrayList<ArrayList<String>> res = CtrlPresentacio.llistarKDocumentsRellevantsPParaules((int)sK.getValue(), arr, cb.getSelectedIndex());
                CardLayout cl = (CardLayout)(pDocuments.getLayout());
                String[] metodesOrdenacioDocuments = {"Per rellevància", "Alfabèticament per autor i títol", "Alfabèticament per títol i autor", "Per mida", "Per data de creació"};
                pAux = CtrlPresentacio.panelDocuments(res, metodesOrdenacioDocuments);
                pDocuments.add(pAux, "aux");
                cl.show(pDocuments, "aux");
            } catch (NumDocumentsIncorrecte ex) {
                // Això mai passa
            }
        });

        // Zona dels Resultats de la Consulta
        pDocuments.add(pAux, "aux");

        // Panell Principal
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(p0); this.add(p1); this.add(p2);
        this.add(p3); this.add(p4); this.add(separator);
        this.add(pDocuments);
    }
}
