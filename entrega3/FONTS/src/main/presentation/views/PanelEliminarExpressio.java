package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.NoExisteixExpressio;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe del panel que es mostra quan volem eliminar una Expressió Booleana.
 * @author Oriol Miró López-Feliu (oriol.miro.lopez-feliu@estudiantat.upc.edu)
 */
public class PanelEliminarExpressio extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Eliminar Expressió");
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JComboBox per escollir l'expressio */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel que informa que s'ha eliminat l'expressió satisfactòriament */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton d'ELiminar Expressió */
    private JPanel p3 = new JPanel(new FlowLayout());
    /** Label per informar de la funcionalitat del selector d'expressions */
    private JLabel lNom = new JLabel("Cerca l'expressio:");
    /** JComboBox per escollir l'expressio */
    private JComboBox cbContingut;

    /** Label per informar que s'ha eliminat correctament l'Expressió  */
    private JLabel informar = new JLabel();

    /** Button d'acció */
    private JButton bAcceptar = new JButton("Eliminar Expressió");

    /**
     * Creadora per defecte.
     */
    public PanelEliminarExpressio() {

        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        //Seleccio
        cbContingut = new JComboBox(CtrlPresentacio.llistarExpressions().toArray(new String[0]));
        cbContingut.setSelectedIndex(-1);

        p1.add(lNom); p1.add(cbContingut);

        p2.add(informar);
        // Button
        p3.add(bAcceptar);

        bAcceptar.addActionListener(e -> {
            if (cbContingut.getSelectedIndex() == -1) return;
            try {
                String nomExpressio = cbContingut.getSelectedItem().toString();
                CtrlPresentacio.eliminarExpressio(nomExpressio);
                informar.setVisible(true);
                informar.setText("L'expressió " + nomExpressio + " s'ha eliminat correctament.");
                cbContingut.removeItem(nomExpressio);
            } catch (NoExisteixExpressio ex) {} //Impossible
        });
        pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.PAGE_AXIS));
        pPrincipal.add(p0);
        pPrincipal.add(p1);
        pPrincipal.add(p2);
        pPrincipal.add(p3);
        this.add(pPrincipal);
    }
}
