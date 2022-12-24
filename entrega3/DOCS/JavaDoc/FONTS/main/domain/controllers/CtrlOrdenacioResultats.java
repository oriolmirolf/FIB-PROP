package FONTS.src.main.domain.controllers;
import FONTS.src.main.domain.classes.resultats.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Classe del Controlador d'Ordenació de Resultats de Consulta.w
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class CtrlOrdenacioResultats {
    /** ArrayList que guarda l'últim resultat d'una consulta d'autors */
    private ArrayList<ResultatAutor> rAutor;
    /** ArrayList que guarda l'últim resultat d'una consulta de documents */
    private ArrayList<ResultatDocument> rDocument;

    /**
     * Constructora per defecte.
     */
    public CtrlOrdenacioResultats() {
        this.rAutor = null;
        this.rDocument = null;
    }

    /**
     * Setter de rAutor.
     * @param arr ArrayList que volem asignar a rAutor.
     */
    public void setResultatAutor(ArrayList<ResultatAutor> arr) {
        this.rAutor = arr;
    }

    /**
     * Setter de rDocument.
     * @param arr ArrayList que volem asignar a rDocument.
     */
    public void setResultatDocument(ArrayList<ResultatDocument> arr) {
        this.rDocument = arr;
    }

    /**
     * Getter de rAutor.
     * @return Retorna l'atribut rAutor.
     */
    public ArrayList<ResultatAutor> getResultatAutor() {
        return this.rAutor;
    }

    /**
     * Getter de rDocument.
     * @return Retorna l'atribut rDocument.
     */
    public ArrayList<ResultatDocument> getResultatDocument() {
        return this.rDocument;
    }

    /**
     * Mètode per ordenar una ArrayList de ResultatAutor segons el nom de l'autor.
     * @param arr ArrayList que es vol ordenar. Si és null significa que es vol ordenar l'últim resultat guardat (rAutor).
     */
    public ArrayList<ResultatAutor> OrdenarAlfabeticamentPerAutor(ArrayList<ResultatAutor> arr) {
        if (arr == null) arr = rAutor;
        Collections.sort(arr, new Comparator<ResultatAutor>() {
            public int compare(ResultatAutor r1, ResultatAutor r2) {
                return r1.getAutor().compareTo(r2.getAutor());
            }
        });
        return arr;
    }

    /**
     * Mètode per ordenar una ArrayList de ResultatAutor segons el nombre de títols dels autors.
     * @param arr ArrayList que es vol ordenar. Si és null significa que es vol ordenar l'últim resultat guardat (rAutor).
     */
    public ArrayList<ResultatAutor> OrdenarPerNTitolsAutor(ArrayList<ResultatAutor> arr) {
        if (arr == null) arr = rAutor;
        Collections.sort(arr, new Comparator<ResultatAutor>() {
            public int compare(ResultatAutor r1, ResultatAutor r2) {
                return r2.getNTitols() - r1.getNTitols();
            }
        });
        return arr;
    }

    /**
     * Mètode per ordenar l'ArrayList segons el nom de l'autor principalment i el títol secundàriament.
     * @param arr ArrayList que es vol ordenar. Si és null significa que es vol ordenar l'últim resultat guardat (rDocument).
     */
    public ArrayList<ResultatDocument> OrdenarAlfabeticamentPerAutorTitol(ArrayList<ResultatDocument> arr) {
        if (arr == null) arr = rDocument;
        Collections.sort(arr, new Comparator<ResultatDocument>() {
            public int compare(ResultatDocument a, ResultatDocument b) {
                String titol1 = a.getTitol(), titol2 = b.getTitol();
                String autor1 = a.getAutor(), autor2 = b.getAutor();
                if (autor1.equals(autor2)) return titol1.compareTo(titol2);
                else return autor1.compareTo(autor2);
            }
        });
        return arr;
    }

    /**
     * Mètode per ordenar l'ArrayList segons el títol principalment i el nom de l'autor secundàriament.
     * @param arr ArrayList que es vol ordenar. Si és null significa que es vol ordenar l'últim resultat guardat (rDocument).
     */
    public ArrayList<ResultatDocument> OrdenarAlfabeticamentPerTitolAutor(ArrayList<ResultatDocument> arr) {
        if (arr == null) arr = rDocument;
        Collections.sort(arr, new Comparator<ResultatDocument>() {
            public int compare(ResultatDocument a, ResultatDocument b) {
                String titol1 = a.getTitol(), titol2 = b.getTitol();
                String autor1 = a.getAutor(), autor2 = b.getAutor();
                if (titol1.equals(titol2)) return autor1.compareTo(autor2);
                else return titol1.compareTo(titol2);
            }
        });
        return arr;
    }

    /**
     * Mètode per ordenar l'ArrayList segons la mida dels documents.
     * @param arr ArrayList que es vol ordenar. Si és null significa que es vol ordenar l'últim resultat guardat (rDocument).
     */
    public ArrayList<ResultatDocument> OrdenarPerMidaDocument(ArrayList<ResultatDocument> arr) {
        if (arr == null) arr = rDocument;
        Collections.sort(arr, new Comparator<ResultatDocument>() {
            public int compare(ResultatDocument r1, ResultatDocument r2) {
                int mida1 = r1.getMida(), mida2 = r2.getMida();
                return mida2 - mida1;
            }
        });
        return arr;
    }

    /**
     * Mètode per ordenar l'ArrayList segons la data de creació dels documents.
     * @param arr ArrayList que es vol ordenar. Si és null significa que es vol ordenar l'últim resultat guardat (rDocument).
     */
    public ArrayList<ResultatDocument> OrdenarPerDataCreacio(ArrayList<ResultatDocument> arr) {
        if (arr == null) arr = rDocument;
        Collections.sort(arr, new Comparator<ResultatDocument>() {
            public int compare(ResultatDocument r1, ResultatDocument r2) {
                return r1.getDataCreacio().compareTo(r2.getDataCreacio());
            }
        });
        return arr;
    }
}