package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.DocumentJaExisteix;
import FONTS.src.main.domain.classes.exceptions.JaExisteixDocumentObert;
import FONTS.src.main.persistencia.excepcions.ErrorIntern;
import FONTS.src.main.persistencia.excepcions.NoExisteixPath;
import FONTS.src.main.persistencia.excepcions.PathJaExisteix;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Classe del JPanel que es mostra quan volem importar un Document.
 * @author Oriol Miró López-Feliu (oriol.miro.lopez-feliu@estudiantat.upc.edu)
 */
public class PanelImportarDocument extends JPanel {

    private JLabel titolPanel = new JLabel("Importar Document");
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton i el JTextField del path d'origen */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton i el JTextField del path de destí */
    private  JPanel p2 = new JPanel(new FlowLayout());

    /** Panell on s'agrupa el JLabel que informa que s'ha exportat satisfactòriament */
    private JPanel p3 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton de Exportar */
    private JPanel p4 = new JPanel(new FlowLayout());
    /** JButton per seleccionar el path d'origen */
    private JButton bChooserOrigen = new JButton("Selecciona el path d'origen");
    /** FileChooser per escollir el path d'origen */
    private JFileChooser chooserOrigen = new JFileChooser();
    /** TextField per mostrar el path d'origen escollit */
    private JTextField tfPathOrigen = new JTextField(20);
    /** JButton per seleccionar el path de destí */
    private JButton bChooserDesti = new JButton("Selecciona el path de destí");
    /** FileChooser per escollir el path de destí */
    private JFileChooser chooserDesti = new JFileChooser();
    /** TextField per mostrar el path de destí escollit */
    private JTextField tfPathDesti = new JTextField(20);
    /** Label per informar que s'ha importat correctament el Document  */
    private JLabel informar = new JLabel();
    /** Button per importar el Document */
    private JButton bAcceptar = new JButton("Importar");


    /**
     * Creadora per defecte.
     */
    public PanelImportarDocument() {

        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT, XML, PDF & JSON FILES", "txt", "xml", "pdf", "json");
        chooserOrigen.setFileFilter(filter);

        // Button Chooser
        p1.add(bChooserOrigen);
        bChooserOrigen.addActionListener(e -> {
            int returnValue = chooserOrigen.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File arxiu = chooserOrigen.getSelectedFile();
                String path = arxiu.getPath();
                if (!path.endsWith(".txt") && !path.endsWith(".xml") && !path.endsWith(".pdf") && !path.endsWith(".json"))
                    JOptionPane.showMessageDialog(this, "ERROR: El arxiu amb path " + path + " no és vàlid.", "Error Importar Arxiu", JOptionPane.ERROR_MESSAGE);
                else tfPathOrigen.setText(path);
            }
        });

        // FileChooser
        chooserOrigen.setDialogTitle("Selecciona l'arxiu");

        // TextField Path
        p1.add(tfPathOrigen);
        tfPathOrigen.setEditable(false);


        // Button Chooser
        p2.add(bChooserDesti);
        bChooserDesti.addActionListener(e -> {
            int returnValue = chooserDesti.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File arxiu = chooserDesti.getSelectedFile();
                String path = arxiu.getPath();
                if (!path.endsWith(".ojmj")) path = path.concat(".ojmj");
                tfPathDesti.setText(path);
            }
        });

        // FileChooser
        chooserDesti.setDialogTitle("Introdueix el nom de l'arxiu");

        // TextField Path
        p2.add(tfPathDesti);
        tfPathDesti.setEditable(false);


        informar.setVisible(false);
        p3.add(informar);
        // Button
        p4.add(bAcceptar);
        bAcceptar.addActionListener(e -> {
            if (tfPathOrigen.getText().isEmpty() || tfPathDesti.getText().isEmpty()) return;
            try {
                CtrlPresentacio.importarDocument(tfPathOrigen.getText(), tfPathDesti.getText());
                informar.setText("El document s'ha importat correctament.");
                informar.setVisible(true);
            } catch (PathJaExisteix ex) {
                informar.setVisible(false);
                JOptionPane.showMessageDialog(this, "ERROR: Ja existeix un document amb path " + tfPathDesti.getText() + ".", "Error Importar Document", JOptionPane.ERROR_MESSAGE);
            } catch (DocumentJaExisteix ex) {
                informar.setVisible(false);
                JOptionPane.showMessageDialog(this, "ERROR: Ja existeix el document", "Error Importar Document", JOptionPane.ERROR_MESSAGE);
            } catch (ErrorIntern ex) {
                informar.setVisible(false);
                JOptionPane.showMessageDialog(this, "ERROR: Error intern", "Error Importar Document", JOptionPane.ERROR_MESSAGE);
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
