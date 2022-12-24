package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.ExpressioNoValida;
import FONTS.src.main.domain.classes.exceptions.NoExisteixExpressio;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Classe del panel que es mostra quan volem consultar el contingut d'una Expressió Booleana.
 * @author Oriol Miró López-Feliu (oriol.miro.lopez-feliu@estudiantat.upc.edu)
 */
public class PanelConsultarExpressio extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Consultar Expressió");
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JComboBox per escollir l'expressió */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell per mostrar el contingut actual de l'expressio seleccionada */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** Label per informar de la funcionalitat del selector d'expressió */
    JLabel lNom = new JLabel("Cerca l'expressio:");
    /** JComboBox per escollir l'expressió */
    private JComboBox cbContingut;
    /** Label per informar de la funcionalitat de la funcionalitat del camp de text */
    private JLabel lContingut = new JLabel("Contingut: ");
    /** JTextField per mostrar el contingut de l'expressió escollida */
    private JTextField tfContingut = new JTextField(20);


    /**
     * Creadora per defecte.
     */
    public PanelConsultarExpressio() {

        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        //Seleccio
        cbContingut = new JComboBox(CtrlPresentacio.llistarExpressions().toArray(new String[0]));
        cbContingut.setSelectedIndex(-1);

        p1.add(lNom); p1.add(cbContingut);

        //Per cambiar el label mostrant el contingut
        cbContingut.addItemListener(e -> {
            try {
                String nomExpressio = cbContingut.getSelectedItem().toString();
                String expressioAssociada = CtrlPresentacio.consultarExpressio(nomExpressio);
                tfContingut.setText(expressioAssociada);
            } catch (NoExisteixExpressio ex) {} //Impossible
        });

        // Contingut
        tfContingut.setEditable(false);
        p2.add(lContingut); p2.add(tfContingut);


        pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.PAGE_AXIS));
        pPrincipal.add(p0);
        pPrincipal.add(p1);
        pPrincipal.add(p2);
        this.add(pPrincipal);
    }
}
