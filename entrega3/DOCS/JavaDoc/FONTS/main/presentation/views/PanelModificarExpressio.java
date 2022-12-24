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

/**
 * Classe del panel que es mostra quan volem modificar una Expressió Booleana.
 * @author Oriol Miró López-Feliu (oriol.miro.lopez-feliu@estudiantat.upc.edu)
 */
public class PanelModificarExpressio extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Modificar Expressió");
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JComboBox d'escollir l'expressió */
    private JPanel p1 = new JPanel(new FlowLayout());

    /** Panell on s'agrupa el JLabel i el JTextField del nou Contingut */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel que informa que s'ha modificat l'expressió satisfactòriament */
    private JPanel p3 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton de Modificar Expressió */
    private JPanel p4 = new JPanel(new FlowLayout());
    /** Label per informar de la funcionalitat del JComboBox per escollir expressió */
    private JLabel lFuncionalitat = new JLabel("Cerca l'expressio:");
    /** JComboBox per escollir l'expressió a modificar */
    private JComboBox cbContingut = new JComboBox();
    /** JTextField per mostrar el contingut actual de l'expressió */
    private JTextField tfContingutActual = new JTextField(20);
    /** Label per informar de la funcionalitat de la caixa de text per introduir el nou Contingut */
    private JLabel lNouContingut = new JLabel("Introdueix el nou contingut:");
    /** JTextField del per introduir el nou Contingut */
    private JTextField tfNouContingut = new JTextField(20);
    /** Label per informar que s'ha modificat correctament l'Expressió  */
    private JLabel informar = new JLabel();
    /** Button d'acció */
    private JButton bAcceptar = new JButton("Modificar Expressió");

    /**
     * Creadora per defecte.
     */
    public PanelModificarExpressio() {

        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        //Seleccio
        for (String tmp : CtrlPresentacio.llistarExpressions())
            cbContingut.addItem(tmp);

        cbContingut.setSelectedIndex(-1);
        tfContingutActual.setEditable(false);
        p1.add(lFuncionalitat); p1.add(cbContingut); p1.add(tfContingutActual);

        //Per cambiar el label mostrant el contingut
        cbContingut.addItemListener(e -> {
            try {
                tfContingutActual.setText("Contingut: " + CtrlPresentacio.consultarExpressio(cbContingut.getSelectedItem().toString()));
                informar.setVisible(false);
            } catch (NoExisteixExpressio ex) {} //Impossible
        });


        // Nou Contingut
        p2.add(lNouContingut); p2.add(tfNouContingut);

        // Informar
        informar.setVisible(false);
        p3.add(informar);

        // Button
        p4.add(bAcceptar);

        bAcceptar.addActionListener(e -> {
            if (tfNouContingut.getText().isBlank() || cbContingut.getSelectedIndex() == -1) return;
            String nomExpressio = cbContingut.getSelectedItem().toString();
            String contingutNou = tfNouContingut.getText();
            try {
                CtrlPresentacio.modificarExpressio(nomExpressio, contingutNou);
                informar.setText("L'expressió " + nomExpressio + " s'ha modificat correctament.");
                informar.setVisible(true);
                tfContingutActual.setText("Contingut: " + contingutNou);
            }
            catch (NoExisteixExpressio ex) {} //Impossible
            catch (ExpressioNoValida ex) {
                informar.setVisible(false);
                JOptionPane.showMessageDialog(this, "ERROR: L'expressió " + contingutNou + " no és vàlida.", "Error Modificar Expressió", JOptionPane.ERROR_MESSAGE);
            }
        });

        pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.PAGE_AXIS));
        pPrincipal.add(p0);
        pPrincipal.add(p1);
        pPrincipal.add(p2);
        pPrincipal.add(p3);
        pPrincipal.add(p4);
        this.add(pPrincipal);
    }
}
