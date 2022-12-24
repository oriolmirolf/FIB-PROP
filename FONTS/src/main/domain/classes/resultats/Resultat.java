package FONTS.src.main.domain.classes.resultats;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Resultat {
    protected ArrayList<String> arr;

    public Resultat() {
        this.arr = null;
    }

    public ArrayList<String> getResultat() {
        return arr;
    }

    public void setContingut(ArrayList<String> c){}


    public String getAutor(){return null;};
    public String getTitol() {return null;}
    public String getPath() {
        return null;
    }
    public int getMida() {
        return 0;
    }
    public  int getNTitols(){return 0;}
    public LocalDate getDataCreacio() {return null;}
    public void setAutor(String s){return;};
    public void setTitol(String s) {
        return;
    }
    public void setPath(String s) {
        return;
    }
    public void setMida(int m) {
        return;
    }
    public void setNTitols(int x){return;};
    public void setDataCreacio(LocalDate data) {
        return;
    }
}
