package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.DocumentJaExisteix;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;
import FONTS.src.main.persistencia.excepcions.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Classe del JPanel que es mostra quan volem donar d'alta un Document.
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class PanelAltaDocument extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Alta Document");
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JTextField del Títol */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JTextField de l'Autor */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton i el JTextField del Path */
    private JPanel p3 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel que informa que s'ha donat d'alta satisfactòriament */
    private JPanel p4 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton de Donar d'Alta */
    private JPanel p5 = new JPanel(new FlowLayout());
    /** Label pel Títol */
    private JLabel lTitol = new JLabel("Introdueix el títol:");
    /** TextField pel Títol */
    private JTextField tfTitol = new JTextField(20);
    /** Label per l'Autor */
    private JLabel lAutor = new JLabel("Introdueix l'autor:");
    /** TextField per l'Autor */
    private JTextField tfAutor = new JTextField(20);
    /** Button per obrir el JFileChooser */
    private JButton bChooser = new JButton("Selecciona el path");
    /** FileChooser per escollir el path del Document */
    private JFileChooser chooser = new JFileChooser();
    /** TextField per mostrar el path escollit */
    private JTextField tfPath = new JTextField(20);
    /** Label per informar que s'ha donat d'alta un Document  */
    private JLabel informar = new JLabel();
    /** Button per donar d'alta el nou Document */
    private JButton bAcceptar = new JButton("Donar d'alta");

    /**
     * Creadora per defecte.
     */
    public PanelAltaDocument() {
        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        // Títol
        p1.add(lTitol); p1.add(tfTitol);

        // Autor
        p2.add(lAutor); p2.add(tfAutor);

        // Button Chooser
        p3.add(bChooser);
        bChooser.addActionListener(e -> {
            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File arxiu = chooser.getSelectedFile();
                String path = arxiu.getPath();
                if (!path.endsWith(".ojmj")) path = path.concat(".ojmj");
                tfPath.setText(path);
            }
        });

        // FileChooser
        chooser.setDialogTitle("Selecciona el directori i el nom de l'arxiu");

        // TextField Path
        p3.add(tfPath);
        tfPath.setEditable(false);

        // Informar
        informar.setVisible(false);
        p4.add(informar);

        // Button Acceptar
        p5.add(bAcceptar);
        bAcceptar.addActionListener(e -> {
            if (tfTitol.getText().isEmpty() || tfAutor.getText().isEmpty() || tfPath.getText().isEmpty()) return;
                try {
                    CtrlPresentacio.altaDocument(tfTitol.getText(), tfAutor.getText(), tfPath.getText());
                    informar.setText("El document (" + tfTitol.getText() + ", " + tfAutor.getText() + ") s'ha afegit correctament.");
                    informar.setVisible(true);
                } catch (DocumentJaExisteix ex) {
                    informar.setVisible(false);
                    JOptionPane.showMessageDialog(this, "ERROR: Ja existeix un document amb títol " + tfTitol.getText() + " i autor " + tfAutor.getText() + ".", "Error Alta Document", JOptionPane.ERROR_MESSAGE);
                }
                catch (PathJaExisteix ex) {
                    informar.setVisible(false);
                    JOptionPane.showMessageDialog(this, "ERROR: Ja existeix un document amb path " + tfPath.getText() + ".", "Error Alta Document", JOptionPane.ERROR_MESSAGE);
                }
        });

        // Panell Principal
        pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.PAGE_AXIS));
        pPrincipal.add(p0); pPrincipal.add(p1);
        pPrincipal.add(p2); pPrincipal.add(p3);
        pPrincipal.add(p4); pPrincipal.add(p5);
        this.add(pPrincipal);
    }
}
