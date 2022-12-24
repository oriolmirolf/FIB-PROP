package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.JaExisteixDocumentObert;
import FONTS.src.main.persistencia.excepcions.ErrorIntern;
import FONTS.src.main.persistencia.excepcions.NoExisteixPath;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Classe del JPanel que es mostra quan volem exportar un Document.
 * @author Oriol Miró López-Feliu (oriol.miro.lopez-feliu@estudiantat.upc.edu)
 */
public class PanelExportarDocument extends JPanel {
    /** Label amb el títol del panell */
    private JLabel titolPanel = new JLabel("Exportar Document");
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panell on s'agrupa el JLabel del Títol del Panell */
    private JPanel p0 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton i el JTextField del path d'origen */
    private JPanel p1 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton i el JTextField del path de destí */
    private  JPanel p2 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel i el JComboBox del format d'exportació */
    private JPanel p3 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JLabel que informa que s'ha exportat satisfactòriament */
    private JPanel p4 = new JPanel(new FlowLayout());
    /** Panell on s'agrupa el JButton d'Exportar */
    private JPanel p5 = new JPanel(new FlowLayout());
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
    /** JLabel per informar de la funcionalitat del JComboBox per escollir el format d'exportació */
    private JLabel lFormat = new JLabel("Escull el format al que exportar");
    /** JComboBox per escollir el format d'exportació */
    private JComboBox cbFormat = new JComboBox(new String[]{"TXT", "XML", "PDF", "JSON"});
    /** Label per informar que s'ha exportat correctament el Document  */
    private JLabel informar = new JLabel();
    /** JButton per exportar el Document */
    private JButton bAcceptar = new JButton("Exportar");


    /**
     * Creadora per defecte.
     */
    public PanelExportarDocument() {

        // Títol Panel
        titolPanel.setFont(new Font(titolPanel.getFont().getFontName(), Font.BOLD, 18));
        p0.add(titolPanel);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("OJMJ", "ojmj");
        chooserOrigen.setFileFilter(filter);

        // Button Chooser
        p1.add(bChooserOrigen);
        bChooserOrigen.addActionListener(e -> {
            int returnValue = chooserOrigen.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File arxiu = chooserOrigen.getSelectedFile();
                String path = arxiu.getPath();
                if (!path.endsWith(".ojmj"))
                    JOptionPane.showMessageDialog(this, "ERROR: El path " + path + " no és vàlid.", "Error Exportar Arxiu", JOptionPane.ERROR_MESSAGE);
                else tfPathOrigen.setText(path);
            }
        });

        // FileChooser
        chooserOrigen.setDialogTitle("Selecciona l'arxiu");

        // TextField Path
        p1.add(tfPathOrigen);
        tfPathOrigen.setEditable(false);

        cbFormat.setSelectedIndex(-1);
        cbFormat.addActionListener(e -> {
            informar.setVisible(false);
            if (!tfPathDesti.getText().isBlank()) {
                String seleccio = (String) cbFormat.getSelectedItem();
                String originalPath = tfPathDesti.getText();
                if (originalPath.lastIndexOf(".") != -1) originalPath = originalPath.substring(0, originalPath.lastIndexOf("."));
                if (seleccio == "TXT") tfPathDesti.setText(originalPath + ".txt");
                if (seleccio == "XML") tfPathDesti.setText(originalPath + ".xml");
                if (seleccio == "PDF") tfPathDesti.setText(originalPath + ".pdf");
                if (seleccio == "JSON") tfPathDesti.setText(originalPath + ".json");
            }
        });

        // P3

        // Button Chooser
        p2.add(bChooserDesti);
        bChooserDesti.addActionListener(e -> {
            int returnValue = chooserDesti.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File arxiu = chooserDesti.getSelectedFile();
                String path = arxiu.getPath();

                if (!path.endsWith(".txt") && !path.endsWith(".xml") && !path.endsWith(".pdf") && !path.endsWith(".json")) {
                    //Nomes cal posar el final si no ens l'han posat ells
                    String seleccio = (String) cbFormat.getSelectedItem();
                    if (seleccio == "TXT") tfPathDesti.setText(path + ".txt");
                    if (seleccio == "XML") tfPathDesti.setText(path + ".xml");
                    if (seleccio == "PDF") tfPathDesti.setText(path + ".pdf");
                    if (seleccio == "JSON") tfPathDesti.setText(path + ".json");

                }
                tfPathDesti.setText(path);
            }
        });

        // FileChooser
        chooserDesti.setDialogTitle("Introdueix el nom de l'arxiu");

        // TextField Path
        p2.add(tfPathDesti);
        tfPathDesti.setEditable(false);

        p3.add(lFormat); p3.add(cbFormat);

        informar.setVisible(false);
        p4.add(informar);
        // Button
        p5.add(bAcceptar);

        bAcceptar.addActionListener(e -> {
            if (tfPathOrigen.getText().isEmpty() || tfPathDesti.getText().isEmpty() || cbFormat.getSelectedIndex() == -1) return;
            String pathDocumentExportat = tfPathDesti.getText();
            try {
                CtrlPresentacio.exportarDocument(tfPathOrigen.getText(), pathDocumentExportat);
                informar.setText("El document s'ha exportat correctament.");
                informar.setVisible(true);
            } catch (NoExisteixPath ex) {
                informar.setVisible(false);
                JOptionPane.showMessageDialog(this, "ERROR: No existeix un document amb path " + tfPathOrigen.getText() + ".", "Error Exportar Document", JOptionPane.ERROR_MESSAGE);
            } catch (ErrorIntern ex) {
                informar.setVisible(false);
                JOptionPane.showMessageDialog(this, "ERROR: Error intern", "Error Exportar Document", JOptionPane.ERROR_MESSAGE);
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
