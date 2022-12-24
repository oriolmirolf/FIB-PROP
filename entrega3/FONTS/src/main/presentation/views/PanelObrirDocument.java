package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.DocumentNoGuardat;
import FONTS.src.main.domain.classes.exceptions.JaExisteixDocumentObert;
import FONTS.src.main.domain.classes.exceptions.NoExisteixDocument;
import FONTS.src.main.persistencia.excepcions.NoExisteixPath;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Classe del JPanel que es mostra quan volem obrir un Document.
 * @author Oriol Miró López-Feliu
 */
public class PanelObrirDocument extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Obrir Document");
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupen el JButton i el JTextField per escollir el document a obrir */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell pel JButton d'obrir document */
    private JPanel p2 = new JPanel(new FlowLayout());
    /** JButton per obrir l'explorador d'arxius i escollir el document a obrir */
    private JButton bChooser = new JButton("Selecciona el path");
    /** FileChooser per escollir el path del document a obrir */
    private JFileChooser chooser = new JFileChooser();
    /** TextField per mostrar el path escollit */
    private JTextField tfPath = new JTextField(20);
    /** Button per donar obrir el Document */
    private JButton bAcceptar = new JButton("Obrir");

    /**
     * Creadora per defecte.
     */
    public PanelObrirDocument() {

        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("OJMJ", "ojmj");
        chooser.setFileFilter(filter);

        // Button Chooser
        p1.add(bChooser);
        bChooser.addActionListener(e -> {
            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File arxiu = chooser.getSelectedFile();
                String path = arxiu.getPath();
                if (!path.endsWith(".ojmj"))
                    JOptionPane.showMessageDialog(this, "ERROR: El path " + path + " no és vàlid.", "Error Obrir Arxiu", JOptionPane.ERROR_MESSAGE);
                else tfPath.setText(path);
            }
        });

        // FileChooser
        chooser.setDialogTitle("Selecciona l'arxiu a obrir");

        // TextField Path
        p1.add(tfPath);
        tfPath.setEditable(false);

        // Button
        p2.add(bAcceptar);
        bAcceptar.addActionListener(e -> {
            try {
                CtrlPresentacio.obrirDocument(tfPath.getText());
                CtrlPresentacio.vistaEditor();
            } catch (NoExisteixPath ex) {
                JOptionPane.showMessageDialog(this, "ERROR: No existeix cap document amb path " + tfPath.getText() + ".", "Error Obrir Document", JOptionPane.ERROR_MESSAGE);
            } catch (JaExisteixDocumentObert ex) {
                JOptionPane.showMessageDialog(this, "ERROR: S'ha intentat obrir un document quan ja hi ha un document actualment obert.", "Error Obrir Document", JOptionPane.ERROR_MESSAGE);
            }
        });
        pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.PAGE_AXIS));
        pPrincipal.add(p0);
        pPrincipal.add(p1);
        pPrincipal.add(p2);
        this.add(pPrincipal);
    }
}
