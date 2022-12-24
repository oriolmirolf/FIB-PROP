package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.EliminarDocumentObert;
import FONTS.src.main.domain.classes.exceptions.FormatNoReconegut;
import FONTS.src.main.persistencia.excepcions.NoExisteixPath;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Classe del JPanel que es mostra quan volem donar de baixa un Document.
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class PanelBaixaDocument extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Baixa Document");
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton i el JTextField del Path */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel que informa que s'ha donat de baixa satisfactòriament */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton de Donar de Baixa */
    private JPanel p3 = new JPanel(new FlowLayout());
    /** Button per obrir el JFileChooser */
    private JButton bChooser = new JButton("Selecciona el path");
    /** Chooser on l'usuari selecciona el Document a eliminar */
    private JFileChooser chooser = new JFileChooser();
    /** TextField per mostrar el path escollit */
    private JTextField tfPath = new JTextField(20);
    /** Label per informar que s'ha donat de baixa un Document  */
    private JLabel informar = new JLabel();
    /** Button per confirmar l'eliminació del Document */
    private JButton bAcceptar = new JButton("Eliminar Document");

    /**
     * Creadora per defecte.
     */
    public PanelBaixaDocument() {
        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        // bChooser
        p1.add(bChooser);
        bChooser.addActionListener(e -> {
            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File arxiu = chooser.getSelectedFile();
                String path = arxiu.getPath();
                tfPath.setText(path);
            }
        });

        // FileChooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("OJMJ", "ojmj");
        chooser.setDialogTitle("Selecciona el Document a eliminar");
        chooser.setFileFilter(filter);

        // TextField Path
        p1.add(tfPath);
        tfPath.setEditable(false);

        // Informar
        informar.setVisible(false);
        p2.add(informar);

        // Button
        p3.add(bAcceptar);
        bAcceptar.addActionListener(e -> {
            try {
                CtrlPresentacio.baixaDocument(tfPath.getText());
                informar.setText("El document amb path " + tfPath.getText() + " s'ha eliminat correctament.");
                informar.setVisible(true);
            } catch (FormatNoReconegut ex) {
                informar.setVisible(false);
                JOptionPane.showMessageDialog(this, "ERROR: El format del document" + tfPath.getText() + " no és acceptat pel gestor.", "Error Baixa Document", JOptionPane.ERROR_MESSAGE);
            } catch (NoExisteixPath ex) {
                informar.setVisible(false);
                JOptionPane.showMessageDialog(this, "ERROR: El document amb path " + tfPath.getText() + " no existeix.", "Error Baixa Document", JOptionPane.ERROR_MESSAGE);
            } catch (EliminarDocumentObert ex) {
                informar.setVisible(false);
                JOptionPane.showMessageDialog(this, "ERROR: S'està intentant eliminar el document actualment obert.", "Error Baixa Document", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panell Principal
        pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.PAGE_AXIS));
        pPrincipal.add(p0); pPrincipal.add(p1);
        pPrincipal.add(p2); pPrincipal.add(p3);
        this.add(pPrincipal);
    }
}
