package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.NoExisteixDocument;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

/**
 * Classe del JPanel que es mostra quan volem cercar el contingut d'un Document donat el seu títol i autor.
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class PanelCercarDocContingut extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Cercar Contingut per Títol i Autor");
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa JLabel i el JTextField del Títol */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JTextField de l'Autor */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton de Cercar */
    private JPanel p3 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel que informa que s'ha donat llistat el contingut satisfactòriament */
    private JPanel p4 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JTextArea del Contingut */
    private JScrollPane p5 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    /** Label pel Títol */
    private JLabel lTitol = new JLabel("Introdueix el títol:");
    /** TextField pel Títol */
    private JTextField tfTitol = new JTextField(20);
    /** Label per l'Autor */
    private JLabel lAutor = new JLabel("Introdueix l'autor:");
    /** TextField per l'Autor */
    private JTextField tfAutor = new JTextField(20);
    /** Label per informar que s'ha mostrat el Contingut  */
    private JLabel informar = new JLabel();
    /** Button per llistar el Contingut del Document */
    private JButton bCercar = new JButton("Cercar");
    /** TextArea per mostrar el Contingut del Document */
    private JTextArea contingut = new JTextArea(10, 40);

    /**
     * Creadora per defecte.
     */
    public PanelCercarDocContingut() {
    // Títol Panel
    titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
    p0.add(titolPanel);

    // Títol
    p1.add(lTitol); p1.add(tfTitol);

    // Autor
    p2.add(lAutor); p2.add(tfAutor);

    // Botó Cercar
    p3.add(bCercar);
    bCercar.addActionListener(e -> {
        if (tfTitol.getText().isEmpty() || tfAutor.getText().isEmpty()) return;
        try {
            String s = CtrlPresentacio.llistarContingutDocumentPerAutorTitol(tfAutor.getText(), tfTitol.getText());
            contingut.setText(s);
            contingut.setCaretPosition(0); // Això fa que es mostri el principi del document i no el seu final
            informar.setText("S'està mostrant el contingut del document (" + tfTitol.getText() + ", " + tfAutor.getText() + ").");
            informar.setVisible(true);
        } catch (NoExisteixDocument ex) {
            informar.setVisible(false);
            contingut.setText("");
            JOptionPane.showMessageDialog(this, "ERROR: No existeix cap document amb títol " + tfTitol.getText() + " i autor " + tfAutor.getText() + ".", "Error Cercar Contingut per Títol i Autor", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Informar
    informar.setVisible(false);
    p4.add(informar);

    // Contingut
    contingut.setEditable(false);
    p5.setViewportView(contingut);

    // Panell Principal
    pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.PAGE_AXIS));
    pPrincipal.add(p0); pPrincipal.add(p1);
    pPrincipal.add(p2); pPrincipal.add(p3);
    pPrincipal.add(p4); pPrincipal.add(p5);
    this.add(pPrincipal);
    }
}
