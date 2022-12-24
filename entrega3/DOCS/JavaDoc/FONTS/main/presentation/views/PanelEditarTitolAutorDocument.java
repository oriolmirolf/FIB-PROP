package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.DocumentJaExisteix;
import FONTS.src.main.domain.classes.exceptions.DocumentNoGuardat;
import FONTS.src.main.domain.classes.exceptions.DocumentNoObert;
import FONTS.src.main.domain.classes.exceptions.JaExisteixDocumentObert;
import FONTS.src.main.persistencia.excepcions.NoExisteixPath;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Classe del panel que es mostra quan volem editar el títul o autor d'un document.
 * @author Oriol Miró López-Feliu (oriol.miro.lopez-feliu@estudiantat.upc.edu)
 */
public class PanelEditarTitolAutorDocument extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Editar Titol/Autor Document");
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panell on s'agrupa el JLabel i el JTextField del Títol */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JTextField del Títol */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JTextField de l'Autor */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JComboBox i el JTextField per escollir qué editar i entrar el nou valor */
    private JPanel p3 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel que informa que s'ha editat satisfactòriament */
    private JPanel p4 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton d'Editar */
    private JPanel p5 = new JPanel(new FlowLayout());
    /** Label pel Títol */
    private JLabel lTitol = new JLabel("Introdueix el títol:");
    /** TextField pel Títol */
    private JTextField tfTitol = new JTextField(20);
    /** Label per l'Autor */
    private JLabel lAutor = new JLabel("Introdueix l'autor:");
    /** TextField per l'Autor */
    private JTextField tfAutor = new JTextField(20);
    /** JComboBox per escollir si es vol editar el títol o l'autor */
    private JComboBox cbFuncionalitat = new JComboBox<>(new String[]{"Editar titol", "Editar autor"});
    /** JTextField per entra el nou valor de títol o d'autor */
    private JTextField tfEdicio = new JTextField(20);
    /** Label per informar que s'ha editat correctament el títol o l'autor  */
    private JLabel informar = new JLabel();
    /** JButton per fer l'edició */
    private JButton bAcceptar = new JButton("Editar");

    /** Creadora per defecte */
    public PanelEditarTitolAutorDocument() {

        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        p1.add(lTitol); p1.add(tfTitol);

        p2.add(lAutor); p2.add(tfAutor);

        p3.add(cbFuncionalitat);
        p3.add(tfEdicio);

        cbFuncionalitat.setSelectedIndex(-1);
        cbFuncionalitat.addActionListener(e -> {
            if (cbFuncionalitat.getSelectedItem() ==  "Editar autor") bAcceptar.setText("Editar autor");
            if (cbFuncionalitat.getSelectedItem() ==  "Editar titol") bAcceptar.setText("Editar titol");
        });


        informar.setVisible(false);
        p4.add(informar);

        p5.add(bAcceptar);

        bAcceptar.addActionListener(e -> {
            if (tfTitol.getText().isEmpty() || tfAutor.getText().isEmpty() || cbFuncionalitat.getSelectedIndex() == -1 || tfEdicio.getText().isEmpty()) return;

            String titolActual = tfTitol.getText();
            String autorActual = tfAutor.getText();
            String nouValor = tfEdicio.getText();
            if (cbFuncionalitat.getSelectedItem() == "Editar autor")
                try {
                    CtrlPresentacio.editarAutor(autorActual, nouValor, titolActual);
                    informar.setText("El document s'ha editat correctament.");
                    informar.setVisible(true);
                    tfAutor.setText(nouValor);
                } catch (NoExisteixPath ex) {
                    informar.setVisible(false);
                    JOptionPane.showMessageDialog(this, "ERROR: No existeix cap document amb títol " + titolActual + " i autor " + autorActual + ".", "Error Editar Autor", JOptionPane.ERROR_MESSAGE);
                } catch (DocumentJaExisteix ex) {
                    informar.setVisible(false);
                    JOptionPane.showMessageDialog(this, "ERROR: Ja existeix un document amb títol " + titolActual + " i autor " + nouValor + ".", "Error Editar Autor", JOptionPane.ERROR_MESSAGE);
                }

            if (cbFuncionalitat.getSelectedItem() == "Editar titol")
                try {
                    CtrlPresentacio.editarTitol(nouValor, titolActual, autorActual);
                    informar.setText("El document s'ha editat correctament.");
                    informar.setVisible(true);
                    tfTitol.setText(nouValor);
                } catch (NoExisteixPath ex) {
                    informar.setVisible(false);
                    JOptionPane.showMessageDialog(this, "ERROR: No existeix cap document amb títol " + titolActual + " i autor " + autorActual + ".", "Error Editar Autor", JOptionPane.ERROR_MESSAGE);
                } catch (DocumentJaExisteix ex) {
                    informar.setVisible(false);
                    JOptionPane.showMessageDialog(this, "ERROR: Ja existeix un document amb títol " + nouValor + " i autor " + autorActual + ".", "Error Editar Titol", JOptionPane.ERROR_MESSAGE);
                }
        });

        pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.PAGE_AXIS));
        pPrincipal.add(p0);
        pPrincipal.add(p1);
        pPrincipal.add(p2);
        pPrincipal.add(p3);
        pPrincipal.add(p4);
        pPrincipal.add(p5);
        this.add(pPrincipal);
    }
}


