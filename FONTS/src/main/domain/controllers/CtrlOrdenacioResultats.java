package FONTS.src.main.domain.controllers;
import FONTS.src.main.domain.classes.resultats.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CtrlOrdenacioResultats {

    /**
     * Mètode per ordenar l'ArrayList segons el nom de l'autor.
     * @param arr ArrayList que es vol ordenar.
     */
    public void OrdenarAlfabèticamentPerAutor(ArrayList<ResultatAutor> arr) {
        Collections.sort(arr, new Comparator<ResultatAutor>() {
            public int compare(ResultatAutor r1, ResultatAutor r2) {
                return r1.getAutor().compareTo(r2.getAutor());
            }
        });
    }

    /**
     * Mètode per ordenar l'ArrayList segons el nom de l'autor principalment i el títol secundàriament.
     * @param arr ArrayList que es vol ordenar.
     */
    public void OrdenarAlfabèticamentPerTitolAutor(ArrayList<ResultatDocument> arr) {
        Comparator<ResultatDocument> compararPerTitolAutor = new Comparator<ResultatDocument>() {
            @Override
            public int compare(ResultatDocument a, ResultatDocument b) {
                String titol1 = a.getTitol(), titol2 = b.getTitol();
                String autor1 = a.getAutor(), autor2 = b.getAutor();
                if (titol1 == titol2) return autor1.compareTo(autor2);
                else return titol1.compareTo(titol2);
            }
        };
        Collections.sort(arr, compararPerTitolAutor);
    }

    /**
     * Mètode per ordenar l'ArrayList segons la mida dels documents.
     * @param arr ArrayList que es vol ordenar.
     */
    public void OrdenarPerMidaDocument(ArrayList<ResultatDocument> arr) {
        Comparator<ResultatDocument> compararPerMidaDocument = new Comparator<ResultatDocument>() {
            @Override
            public int compare(ResultatDocument a, ResultatDocument b) {
                int mida1 = a.getMida(), mida2 = b.getMida();
                return mida1 - mida2;
            }
        };
        Collections.sort(arr, compararPerMidaDocument);
    }

    /**
     * Mètode per ordenar l'ArrayList segons el nombre de títols dels autors.
     * @param arr ArrayList que es vol ordenar.
     */
    public void OrdenarPerNTitolsAutor(ArrayList<ResultatAutor> arr) {
        Collections.sort(arr, new Comparator<ResultatAutor>() {
            public int compare(ResultatAutor r1, ResultatAutor r2) {
                return r1.getNTitols() - r2.getNTitols();
            }
        });
    }

    /**
     * Mètode per ordenar l'ArrayList segons la data de creació dels documents.
     * @param arr ArrayList que es vol ordenar.
     */
    public void OrdenarPerDataCreacio(ArrayList<ResultatDocument> arr) {
        Collections.sort(arr, new Comparator<ResultatDocument>() {
            public int compare(ResultatDocument r1, ResultatDocument r2) {
                return r1.getDataCreacio().compareTo(r2.getDataCreacio());
            }
        });
    }
}