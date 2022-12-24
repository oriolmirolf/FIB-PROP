package FONTS.src.main.domain.classes.resultats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ResultatDocument extends Resultat {

    public ResultatDocument() {
        this.arr = new ArrayList<>();
        // Inicialitzem l'Array amb 5 elements
        for (int i = 0; i < 5; ++i) arr.add("-1");
    }

    public String getAutor() {
        return this.arr.get(0);
    }

    public String getTitol() {
        return this.arr.get(1);
    }

    public String getPath() {
        return this.arr.get(2);
    }

    public int getMida() {
        return Integer.parseInt(this.arr.get(3));
    }

    public LocalDateTime getDataCreacio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(this.arr.get(4), formatter);
        return date;
    }

    public void setAutor(String s) {
        this.arr.set(0, s);
    }

    public void setTitol(String s) {
        this.arr.set(1, s);
    }

    public void setPath(String s) {
        this.arr.set(2, s);
    }

    public void setMida(int m) {
        this.arr.set(3, Integer.toString(m));
    }

    public void setDataCreacio(LocalDateTime data) {
        String formattedDate = data.format(DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm:ss"));
        this.arr.set(4, formattedDate);
    }
}
