package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.ExpressioNoValida;
import FONTS.src.main.domain.classes.exceptions.JaExisteixExpressio;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

/**
 * Classe del JPanel que es mostra quan volem afegir una nova Expressió Booleana.
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class PanelAfegirExpressio extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Afegir Expressió");
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JTextField del Nom */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JTextField del Contingut */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel que informa que s'ha afegit l'expressió satisfactòriament */
    private JPanel p3 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton d'Afegir Expressió */
    private JPanel p4 = new JPanel(new FlowLayout());
    /** Label pel Nom */
    private JLabel lNom = new JLabel("Introdueix el nom:");
    /** TextField pel Nom */
    private JTextField tfNom = new JTextField(20);
    /** Label pel Contingut */
    private JLabel lContingut = new JLabel("Introdueix el contingut:");
    /** TextField del Contingut */
    private JTextField tfContingut = new JTextField(20);
    /** Label per informar que s'ha afegit una nova Expressió  */
    private JLabel informar = new JLabel();
    /** Button d'Afegir Expressió */
    private JButton button = new JButton("Afegir Expressió");

    /**
     * Creadora per defecte.
     */
    public PanelAfegirExpressio() {
        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        // Nom
        p1.add(lNom); p1.add(tfNom);

        // Contingut
        p2.add(lContingut); p2.add(tfContingut);

        // Informar
        informar.setVisible(false);
        p3.add(informar);

        // Button
        p4.add(button);

        button.addActionListener(e -> {
            if (tfNom.getText().isEmpty() || tfContingut.getText().isEmpty()) return;
            try {
                CtrlPresentacio.afegirExpressio(tfNom.getText(), tfContingut.getText());
                informar.setText("L'expressió " + tfNom.getText() + " s'ha afegit correctament.");
                informar.setVisible(true);
            } catch (JaExisteixExpressio ex) {
                informar.setVisible(false);
                JOptionPane.showMessageDialog(this, "ERROR: Ja existeix una expressió amb nom " + tfNom.getText() + ".", "Error Afegir Expressió", JOptionPane.ERROR_MESSAGE);
            } catch (ExpressioNoValida ex) {
                informar.setVisible(false);
                JOptionPane.showMessageDialog(this, "ERROR: L'expressió " + tfContingut.getText() + " no és vàlida.", "Error Afegir Expressió", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panell Principal
        pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.PAGE_AXIS));
        pPrincipal.add(p0); pPrincipal.add(p1);
        pPrincipal.add(p2); pPrincipal.add(p3);
        pPrincipal.add(p4);
        this.add(pPrincipal);
    }
}
