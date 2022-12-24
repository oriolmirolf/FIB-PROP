package FONTS.src.main.domain.classes.resultats;

import java.util.ArrayList;
import java.util.Comparator;

public class ResultatAutor extends Resultat {

    public ResultatAutor() {
        this.arr = new ArrayList<>();
        // Inicialitzem l'Array amb 2 elements
        for (int i = 0; i < 2; ++i) arr.add("-1");
    }

    public String getAutor() {
        return this.arr.get(0);
    }

    public int getNTitols() {
        return Integer.parseInt(this.arr.get(1));
    }

    public void setAutor(String s) {
        this.arr.set(0, s);
    }

    public void setNTitols(int x) {
        this.arr.set(1, Integer.toString(x));
    }
}
