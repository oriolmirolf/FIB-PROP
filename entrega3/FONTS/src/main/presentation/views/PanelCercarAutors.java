package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.*;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Classe del JPanel que es mostra quan volem cercar Autors que començen per un prefix.
 * @author Oriol Miró López-Feliu (oriol.miro.lopez-feliu@estudiantat.upc.edu)
 */
public class PanelCercarAutors extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Cercar Autors");
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrua el JLabel i el JTextField per introduir el prefix a cercar */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton de Cercar */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** JLabel que informa de la funcionalitat del JTextField per introduir el prefix */
    private JLabel lPrefix = new JLabel("Introdueix el prefix:");
    /** JTextField que serveix per introduir el prefix d'autor a cercar */
    private JTextField tfPrefix = new JTextField(20);
    /** JButton per confirmar la cerca d'Autors */
    private JButton bCercar = new JButton("Cercar Autors");
    /** Separador de la zona d'input de la consulta i la zona on es mostren els seus resultats */
    private JSeparator separator = new JSeparator();
    /** Panel on es mostraran els resultats de la consulta */
    private JPanel pAutors = new JPanel(new CardLayout());
    /** Panel auxiliar per poder fer la cerca d'autors de manera dinàmica */
    private JPanel pAux = new JPanel();

    /**
     * Creadora per defecte.
     */
    public PanelCercarAutors() {
        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        //Panell 1
        p1.add(lPrefix);
        p1.add(tfPrefix);

        p2.add(bCercar);

        bCercar.addActionListener(e -> {
            String prefix = tfPrefix.getText();
            ArrayList<ArrayList<String>> arr = CtrlPresentacio.llistarAutorsPerPrefix(prefix);

            CardLayout cl = (CardLayout)(pAutors.getLayout());
            pAux = CtrlPresentacio.panelAutors(arr);
            pAutors.add(pAux, "aux");
            cl.show(pAutors, "aux");

        });

        // Zona dels Resultats de la Consulta
        pAutors.add(pAux, "aux");

        // Panell Principal
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(p0); this.add(p1); this.add(p2);
        this.add(separator); this.add(pAutors);
    }
}
