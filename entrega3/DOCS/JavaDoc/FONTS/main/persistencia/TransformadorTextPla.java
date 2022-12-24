package FONTS.src.main.persistencia;
import FONTS.src.main.persistencia.excepcions.ErrorIntern;

import java.io.*;
import java.time.LocalDateTime;

public class TransformadorTextPla implements TransformadorArxius {

    /**
     *	Mètode per importar un arxiu amb format de Text Pla
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
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            //LLegim les 2 primeres línes que tenen títol i autor
            String line = br.readLine();
            sb.append(line);
            autor = sb.toString();
            sb.setLength(0);
            line = br.readLine();
            sb.append(line);
            titol = sb.toString();
            //Buidem el buffer i comencem a llegir el contingut
            sb.setLength(0);
            line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            sb.deleteCharAt(sb.length()-1); //Borra la línea en blanc que deixa al final.
            contingut = sb.toString();
        } catch (Exception err) {
            throw new ErrorIntern();
        }
        return new Container(titol, autor, path, dataCreacio, dataDarreraModificacio, contingut);
    }

    /**
     *	Mètode per exportar un arxiu amb format de Text Pla
     *	@param destinacio Path de destinació de l'arxiu un cop exportat.
     *	@param c Container que conté l'informació a exportar.
     *	@throws ErrorIntern
     * */
    public void exportar(String destinacio,Container c) throws ErrorIntern {
        File file = new File(destinacio);
        try {
            FileWriter fr = new FileWriter(file);
            fr.write(c.getAutor());
            fr.write("\n");
            fr.write(c.getTitol());
            fr.write("\n");
            fr.write(c.getContingut());
            fr.close();
        } catch (Exception err) {
            throw new ErrorIntern();
        }
    }
}
