package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.*;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Classe del JPanel que es mostra quan volem cercar Documents per Expressió Booleana.
 * @author Oriol Miró López-Feliu (oriol.miro.lopez-feliu@estudiantat.upc.edu)
 */
public class PanelCercarDocExp extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Cercar Documents Expressió");

    /** Panell pel títol */
    private JPanel p0 = new JPanel(new FlowLayout());
    /**Panell per el desplegable de funcionalitats, desplegable d'expressions i el camp de text d'expressions */
    private JPanel p1 = new JPanel(new FlowLayout());
    /**Panell pel botó de cercar */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** Desplegable per escollir si cerquem per expressio guardada o entrada manual */
    private JComboBox cbFuncionalitat = new JComboBox(new String[]{"Expressio guardada", "Entrada manual d'expressio"});
    /** Desplegable per escollir l'expressió guardada*/
    private JComboBox cbExpressio;
    /** Camp de text per introduir manualment l'expressió */
    private JTextField tfExpressio = new JTextField(20);
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
    public PanelCercarDocExp() {

        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        //Panell 1

        //Deixem per defecte la primera opció
        cbFuncionalitat.setSelectedIndex(1);

        //La llogica per canviar el que es mostra
        cbFuncionalitat.addActionListener(e->{
            if (cbFuncionalitat.getSelectedIndex() == 0)
            {
                cbExpressio.setVisible(true);
                tfExpressio.setVisible(false);
            }
            if (cbFuncionalitat.getSelectedIndex() == 1)
            {
                cbExpressio.setVisible(false);
                tfExpressio.setVisible(true);
            }
        });

        //Afegim les expressions i posem sense ninguna per defecte
        cbExpressio = new JComboBox(CtrlPresentacio.llistarExpressions().toArray());
        cbExpressio.setSelectedIndex(-1);

        //Posem les visibilitats per defecte
        cbExpressio.setVisible(false);
        tfExpressio.setVisible(true);

        p1.add(cbFuncionalitat);
        p1.add(cbExpressio);
        p1.add(tfExpressio);

        p2.add(bCercar);
        String[] metodesOrdenacioDocuments = {"Per defecte", "Alfabèticament per autor i títol", "Alfabèticament per títol i autor", "Per mida", "Per data de creació"};
        bCercar.addActionListener(e -> {

            if (cbFuncionalitat.getSelectedIndex() == -1) return;

            if (cbFuncionalitat.getSelectedItem() == "Expressio guardada")
            {
                if (cbExpressio.getSelectedIndex() == -1) return;
                try {
                    String nom = cbExpressio.getSelectedItem().toString();
                    ArrayList<ArrayList<String>> arr = CtrlPresentacio.llistarTitolsPerExpressioGuardada(nom);

                    CardLayout cl = (CardLayout)(pDocuments.getLayout());
                    pAux = CtrlPresentacio.panelDocuments(arr, metodesOrdenacioDocuments);
                    pDocuments.add(pAux, "aux");
                    cl.show(pDocuments, "aux");
                } catch (NoExisteixExpressio ex) {}//Impossible
            }

            if (cbFuncionalitat.getSelectedItem() == "Entrada manual d'expressio")
            {
                String exp = tfExpressio.getText();
                if (exp.isBlank()) return;
                try {
                    ArrayList<ArrayList<String>> arr = CtrlPresentacio.llistarTitolsPerExpressioNoGuardada(exp);

                    CardLayout cl = (CardLayout)(pDocuments.getLayout());
                    pAux = CtrlPresentacio.panelDocuments(arr, metodesOrdenacioDocuments);
                    pDocuments.add(pAux, "aux");
                    cl.show(pDocuments, "aux");

                }
                catch (ExpressioNoValida ex) {
                    JOptionPane.showMessageDialog(this, "ERROR: L'expressió " + exp + " no és vàlida.", "Error Cerca per Expressió Booleana", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Zona dels Resultats de la Consulta
        pDocuments.add(pAux, "aux");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Separator
        this.add(p0);
        this.add(p1);
        this.add(p2);
        this.add(separator);
        this.add(pDocuments);
    }
}
