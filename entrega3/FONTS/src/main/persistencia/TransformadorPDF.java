package FONTS.src.main.persistencia;


import FONTS.src.main.persistencia.excepcions.ErrorIntern;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.FileOutputStream;
import java.time.LocalDateTime;

public class TransformadorPDF implements TransformadorArxius {
    /**
     *	Mètode per importar un arxiu amb format PDF
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
            PdfReader reader = new PdfReader(path);
            int pages = reader.getNumberOfPages();
            for (int i = 1; i <= pages; ++i) {
                String pageContent = PdfTextExtractor.getTextFromPage(reader, i);
                if (i == 1) {
                    //Busco les dues primeres lines on estaran l'autor i titol
                    int salts = 0, j = 0, inici_titol = 0, inici_contingut = 0;
                    while (salts != 2) {
                        if (pageContent.substring(j, j+1).contains("\n")) {
                            if (salts == 0) {
                                autor = pageContent.substring(0, j);
                                inici_titol = j+1;
                            }
                            if (salts == 1) {
                                titol = pageContent.substring(inici_titol, j);
                                inici_contingut = j+1;
                            }
                            ++salts;
                        }
                        ++j;
                    }
                    contingut += pageContent.substring(inici_contingut, pageContent.length());
                }
                else contingut += pageContent;
            }
        } catch (Exception err) {
            throw new ErrorIntern();
        }
        return new Container(titol, autor, path, dataCreacio, dataDarreraModificacio, contingut);
    }

    /**
     *	Mètode per exportar un arxiu amb format PDF
     *	@param dest Path de destinació de l'arxiu un cop exportat.
     *	@param c Container que conté l'informació a exportar.
     *	@throws ErrorIntern
     * */
    public void exportar(String dest, Container c) throws ErrorIntern {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(dest));
            document.open();

            Phrase autor = new Phrase(c.getAutor() + "\n");
            Phrase titol = new Phrase(c.getTitol() + "\n");
            Paragraph contingut = new Paragraph(c.getContingut());

            document.add(autor);
            document.add(titol);
            document.add(contingut);

            document.close();
        } catch (Exception err) {
            throw new ErrorIntern();
        }
    }
}
