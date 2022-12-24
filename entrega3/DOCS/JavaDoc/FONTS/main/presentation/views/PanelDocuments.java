package FONTS.src.main.presentation.views;

import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe del JPanel que mostra els Documents que són resultats d'una consulta de Documents.
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class PanelDocuments extends JPanel {
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panel pel ComboBox */
    JPanel p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    /** Panel auxiliar per poder fer la cerca de Documents de manera dinàmica */
    JPanel pGrid = new JPanel(new GridLayout(0, 5));
    /** Panel pels Documents */
    private JPanel p2 = new JPanel(new CardLayout());
    private JLabel lOrdenar = new JLabel("Escolleix el mètode d'ordenació de documents:");
    private JComboBox cbOrdenarDocuments = new JComboBox();

    /** Retorna un ImageIcon, o null si el path és invàlid.
     * Source: https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html */
    private ImageIcon createImageIcon(String path, String desc) {
        return new ImageIcon(path, desc);
    }

    /**
     * Creadora per defecte.
     */
    public PanelDocuments(ArrayList<ArrayList<String>> arr, String[] metodesOrdenacioDocuments) {
        // Panel
        this.setLayout(new FlowLayout());
        pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.PAGE_AXIS));

        // ComboBox Ordenació
        cbOrdenarDocuments = new JComboBox(metodesOrdenacioDocuments);
        p1.add(lOrdenar); p1.add(cbOrdenarDocuments);
        pPrincipal.add(p1);

        // Taula Documents
        pPrincipal.add(p2);
        pGrid = new JPanel(new GridLayout(arr.size()/5, 5));
        p2.add(pGrid, "grid");

        // ComboBox
        cbOrdenarDocuments.addActionListener(e -> {
            int index = cbOrdenarDocuments.getSelectedIndex() + 5 - metodesOrdenacioDocuments.length;
            ArrayList<ArrayList<String>> arr2;
            if (cbOrdenarDocuments.getSelectedIndex() == 0) arr2 = arr; // En arr ja tenim els documents ordenats per defecte
            else arr2 = CtrlPresentacio.ordenarResultatDocuments(index);
            pGrid = new JPanel(new GridLayout(arr2.size()/5, 5));

            for (ArrayList<String> doc : arr2) {
                // Panells
                JPanel pDoc = new JPanel(), pIcona = new JPanel();
                JPanel pTitol = new JPanel(), pAutor = new JPanel(), pExtra = new JPanel();

                // Label Icona
                ImageIcon icon = createImageIcon("FONTS/src/main/presentation/views/document.png", "Foto Document");
                JLabel laux = new JLabel(icon); pIcona.add(laux);

                // Label Títol
                JLabel textTitol = new JLabel("Títol: " + doc.get(1));
                pTitol.add(textTitol);

                // Label Autor
                JLabel textAutor = new JLabel("Autor: " + doc.get(0));
                pAutor.add(textAutor);

                // Label Extra
                if (index == 3) {
                    JLabel lExtra = new JLabel("Mida: " + doc.get(3) + " paraules");
                    pExtra.add(lExtra);
                }
                else if (index == 4) {
                    JLabel lExtra = new JLabel("Data: " + doc.get(4));
                    pExtra.add(lExtra);
                }

                // Panell principal
                pDoc.setLayout(new BoxLayout(pDoc, BoxLayout.PAGE_AXIS));
                pDoc.add(pIcona); pDoc.add(pTitol); pDoc.add(pAutor);
                if (index == 3 || index == 4) pDoc.add(pExtra);
                pGrid.add(pDoc);
            }

            CardLayout cl = (CardLayout)(p2.getLayout());
            p2.add(pGrid, "grid");
            cl.show(p2, "grid");

        });

        for (ArrayList<String> doc : arr) {
            // Panells
            JPanel pDoc = new JPanel(), pIcona = new JPanel();
            JPanel pTitol = new JPanel(), pAutor = new JPanel();

            // Label Icona
            ImageIcon icon = createImageIcon("FONTS/src/main/presentation/views/document.png", "Foto Document");
            JLabel laux = new JLabel(icon); pIcona.add(laux);

            // Label Títol
            JLabel textTitol = new JLabel("Títol: " + doc.get(1));
            pTitol.add(textTitol);

            // Label Autor
            JLabel textAutor = new JLabel("Autor: " + doc.get(0));
            pAutor.add(textAutor);

            // Panell principal
            pDoc.setLayout(new BoxLayout(pDoc, BoxLayout.PAGE_AXIS));
            pDoc.add(pIcona); pDoc.add(pTitol);
            pDoc.add(pAutor); pGrid.add(pDoc);
        }

        this.add(pPrincipal);
    }
}
