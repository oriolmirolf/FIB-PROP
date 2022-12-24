package FONTS.src.main.persistencia;

import FONTS.src.main.persistencia.excepcions.ErrorIntern;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class TransformadorJSON implements TransformadorArxius {

    private class DocumentAuxiliar {
        private String autor, titol, contingut;

        public DocumentAuxiliar(String a, String t, String c) {
            autor = a;
            titol = t;
            contingut = c;
        }

        public String getAutor() {
            return autor;
        }

        public String getTitol() {
            return titol;
        }

        public String getContingut() {
            return contingut;
        }
    }


    /**
     *	Mètode per importar un arxiu amb format JSON
     *	@param path Path de l'arxiu a importar
     *	@return Un Container amb els elements del container "setejats" amb els de l'arxiu.
     * */
    public Container importar(String path) throws ErrorIntern {
        String autor = "";
        String titol = "";
        String contingut = "";
        LocalDateTime dataCreacio = LocalDateTime.now();
        LocalDateTime dataDarreraModificacio = LocalDateTime.now();
        try {
            Gson gson = new Gson();
            DocumentAuxiliar da = gson.fromJson(new FileReader(path), DocumentAuxiliar.class);
            autor = da.getAutor();
            titol = da.getTitol();
            contingut = da.getContingut();
        } catch (Exception err) {
            throw new ErrorIntern();
        }
        return new Container(titol, autor, path, dataCreacio, dataDarreraModificacio, contingut);
    }


    /**
     *	Mètode per exportar un arxiu amb format JSON
     *	@param destinacio Path de destinació de l'arxiu un cop exportat.
     *	@param c Container que conté l'informació a exportar.
     *	@throws ErrorIntern
     * */
    public void exportar(String destinacio, Container c) throws ErrorIntern {
        try {
            Gson gson = new Gson();
            DocumentAuxiliar da = new DocumentAuxiliar(c.getAutor(), c.getTitol(), c.getContingut());
            String s = gson.toJson(da);
            File file = new File(destinacio);
            FileWriter fr = new FileWriter(file);
            fr.write(s);
            fr.close();
        } catch (Exception err) {
            throw new ErrorIntern();
        }
    }
}
