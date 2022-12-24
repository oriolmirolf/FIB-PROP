package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.NoExisteixAutor;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe del JPanel que es mostra quan volem cercar Documents donat un Autor.
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class PanelCercarDocAutor extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Cercar Documents per Autor");
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JComboBox de l'Autor */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el botó de Cercar */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** Label per l'Autor */
    private JLabel lAutor = new JLabel("Selecciona l'autor que vols cercar:");
    /** ComboBox per l'Autor */
    private JComboBox<String> cb;
    /** Button per realitzar la cerca */
    private JButton bAcceptar = new JButton("Cercar Documents");
    /** Separador de la zona d'input de la consulta i la zona on es mostren els seus resultats */
    private JSeparator separator = new JSeparator();
    /** Panel on es mostraran els resultats de la consulta */
    private JPanel pDocuments = new JPanel(new CardLayout());
    /** Panel auxiliar per poder fer la cerca de Documents de manera dinàmica */
    private JPanel pAux = new JPanel();

    /**
     * Creadora per defecte.
     */
    public PanelCercarDocAutor() {
        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        // Label
        p1.add(lAutor);

        // ComboBox
        ArrayList<ArrayList<String>> llistaAutors = CtrlPresentacio.llistarAutorsPerPrefix("");
        String[] autors = new String[llistaAutors.size()];
        for (int i = 0; i < llistaAutors.size(); ++i) autors[i] = llistaAutors.get(i).get(0);
        cb = new JComboBox(autors);
        cb.setSelectedIndex(-1); // Fem que no seleccioni a cap per defecte
        p1.add(cb);

        // bAcceptar
        p2.add(bAcceptar, BorderLayout.CENTER);
        bAcceptar.addActionListener(e -> {
            if (cb.getSelectedIndex() == -1) return;
            try {
                ArrayList<ArrayList<String>> arr = CtrlPresentacio.llistarTitolsAutors(cb.getSelectedItem().toString());
                CardLayout cl = (CardLayout)(pDocuments.getLayout());
                String[] metodesOrdenacioDocuments = {"Alfabèticament per títol", "Per mida", "Per data de creació"};
                pAux = CtrlPresentacio.panelDocuments(arr, metodesOrdenacioDocuments);
                pDocuments.add(pAux, "aux");
                cl.show(pDocuments, "aux");
            } catch (NoExisteixAutor ex) {
                // No pot passar
            }
        });

        // Zona dels Resultats de la Consulta
        pDocuments.add(pAux, "aux");

        // Panell Principal
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(p0); this.add(p1);
        this.add(p2); this.add(separator);
        this.add(pDocuments);
    }
}
