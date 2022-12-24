package FONTS.src.main.domain.classes.resultats;

import java.util.ArrayList;

public class ResultatContingut extends Resultat {

    public ResultatContingut() {
        this.arr = new ArrayList<>();
        // Inicialitzem l'Array amb 1 element
        for (int i = 0; i < 1; ++i) arr.add("-1");
    }

    public void setContingut(ArrayList<String> c) {
        this.arr = c;
    }
}
