package FONTS.src.stubs;

import FONTS.src.main.domain.classes.exceptions.DocumentJaExisteix;
import FONTS.src.main.domain.classes.exceptions.PathJaExisteix;
import FONTS.src.main.domain.classes.individual_classes.Document;
import java.util.TreeMap;
import FONTS.src.main.domain.classes.exceptions.NoExisteixDocument;

/**
 * Classe del Controlador de Persistència.
 */
public class CtrlPersistencia {
    /** Estructura de dades on s'emmagatzema el path com a clau i el document com a valor */
    private TreeMap<String, Document> pers;

    /**
     * Constructora per defecte.
     */
    public CtrlPersistencia(){
        pers = new TreeMap<>();
    }

    /**
     * Operació per guardar un nou Document a la Capa de Persistència.
     * @param d Document que volem guardar.
     * @throws PathJaExisteix Excepció que es llença quan s'inenta guardar un Document que ja existeix en la Capa de Persistència.
     */
    public void guardarNouDocument(Document d) throws PathJaExisteix {
        String path = d.getLocalitzacio();
        if (pers.containsKey(path)) throw new PathJaExisteix();
        pers.put(d.getLocalitzacio(), d);
    }

    /**
     * Operació per guardar un document a la Capa de Persistència.
     * @param d Document que volem guardar.
     */
    public void guardarDocument(Document d) {
        pers.put(d.getLocalitzacio(), d);
    }

    /**
     * Operació per esborrar el document amb path path de la Capa de Persistència.
     * @param path Direcció on està guardat el Document.
     */
    public void eliminarDocument(String path){
        pers.remove(path);
    }

    /**
     * Operació per obtenir el document amb path path de la Capa de Persistència.
     * @param path Direcció on està guardat el Document.
     * @throws NoExisteixDocument Excepció que es llença quan no existeix cap Document amb path path.
     */
    public Document getDocument(String path) throws NoExisteixDocument {
        if (pers.get(path) == null) throw new NoExisteixDocument();
        return pers.get(path);
    }
}
