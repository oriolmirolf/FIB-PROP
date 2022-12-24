package FONTS.src.main.domain.classes.resultats;

import java.util.ArrayList;

public abstract class Resultat {
    protected ArrayList<String> arr;

    /** Constructora per defecte */
    public Resultat() {
        this.arr = null;
    }

    /**
     * Getter d'arr.
     * @return
     */
    public ArrayList<String> getResultat() {
        return arr;
    }

    /**
     * Setter d'arr.
     * @param c
     */
    public void setContingut(ArrayList<String> c) {
        arr = c;
    }
}
