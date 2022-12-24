package FONTS.src.main.domain.classes.resultats;

import java.util.ArrayList;

public class ResultatTitol extends Resultat {

    public ResultatTitol() {
        this.arr = new ArrayList<>();
        // Inicialitzem l'Array amb 2 elements
        for (int i = 0; i < 2; ++i) arr.add("-1");
    }

    public String getTitol() {
        return this.arr.get(0);
    }

    public String getPath() {
        return this.arr.get(1);
    }

    public void setTitol(String t) {
        this.arr.set(0, t);
    }

    public void setPath(String p) {
        this.arr.set(1, p);
    }
}
