package FONTS.src.main.presentation.views;

import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe del JPanel que mostra els Autors que són resultats d'una consulta d'Autors.
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class PanelAutors extends JPanel {
    /** Panell principal on s'agrupen tots els altres panells */
    private JPanel pPrincipal = new JPanel();
    /** Panel pel ComboBox */
    JPanel p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    /** Panel auxiliar per poder fer la cerca de Documents de manera dinàmica */
    JPanel pGrid = new JPanel(new GridLayout(0, 5));
    /** Panel pels Documents */
    private JPanel p2 = new JPanel(new CardLayout());
    /** Array amb els mètodes d'ordenació dels autors */
    String[] metodesOrdenacioAutors = {"Alfabèticament per nom", "Per documents escrits"};
    /** Label per l'Ordenació */
    private JLabel lOrdenar = new JLabel("Escolleix el mètode d'ordenació d'autors:");
    /** ComboBox per l'ordenació */
    private JComboBox cbOrdenarAutors = new JComboBox(metodesOrdenacioAutors);

    /** Retorna un ImageIcon, o null si el path és invàlid.
     * Source: https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html */
    private ImageIcon createImageIcon(String path, String desc) {
        return new ImageIcon(path, desc);
    }

    /**
     * Creadora per defecte.
     * @param arr ArrayList amb tots els resultats de la consulta ordenats per defecte.
     */
    public PanelAutors(ArrayList<ArrayList<String>> arr) {
        // Panel
        this.setLayout(new FlowLayout());
        pPrincipal.setLayout(new BoxLayout(pPrincipal, BoxLayout.PAGE_AXIS));

        // ComboBox Ordenació
        p1.add(lOrdenar); p1.add(cbOrdenarAutors);
        pPrincipal.add(p1);

        // Taula Autors
        pPrincipal.add(p2);
        p2.add(pGrid, "grid");

        // ComboBox
        cbOrdenarAutors.addActionListener(e -> {
            int index = cbOrdenarAutors.getSelectedIndex();
            ArrayList<ArrayList<String>> arr2;
            if (index == 0) arr2 = arr; // En arr ja tenim els resultats ordenats alfabèticament
            else arr2 = CtrlPresentacio.ordenarResultatAutors(index);
            pGrid = new JPanel(new GridLayout(arr2.size()/5, 5));

            for (ArrayList<String> autor : arr2) {
                // Panells
                JPanel pAutor = new JPanel(), pIcona = new JPanel();
                JPanel pNom = new JPanel(), pEscrits = new JPanel();

                // Label Icona
                ImageIcon icon = createImageIcon("FONTS/src/main/presentation/views/autor.png", "Foto Autor");
                JLabel laux = new JLabel(icon); pIcona.add(laux);

                // Label Nom
                JLabel textNom = new JLabel("Nom: " + autor.get(0));
                pNom.add(textNom);

                // Label Escrits
                JLabel textEscrits = new JLabel("Escrits: " + autor.get(1));
                pEscrits.add(textEscrits);

                // Panell principal
                pAutor.setLayout(new BoxLayout(pAutor, BoxLayout.PAGE_AXIS));
                pAutor.add(pIcona); pAutor.add(pNom); pAutor.add(pEscrits);
                pGrid.add(pAutor);
            }

            CardLayout cl = (CardLayout)(p2.getLayout());
            p2.add(pGrid, "grid");
            cl.show(p2, "grid");
        });

        for (ArrayList<String> autor : arr) {
            // Panells
            JPanel pAutor = new JPanel(), pIcona = new JPanel();
            JPanel pNom = new JPanel(), pEscrits = new JPanel();

            // Label Icona
            ImageIcon icon = createImageIcon("FONTS/src/main/presentation/views/autor.png", "Foto Autor");
            JLabel laux = new JLabel(icon); pIcona.add(laux);

            // Label Nom
            JLabel textNom = new JLabel("Nom: " + autor.get(0));
            pNom.add(textNom);

            // Label Escrits
            JLabel textEscrits = new JLabel("Escrits: " + autor.get(1));
            pEscrits.add(textEscrits);

            // Panell principal
            pAutor.setLayout(new BoxLayout(pAutor, BoxLayout.PAGE_AXIS));
            pAutor.add(pIcona); pAutor.add(pNom); pAutor.add(pEscrits);
            pGrid.add(pAutor);
        }

        this.add(pPrincipal);
    }
}
