package FONTS.src.main.domain.controllers;

/**
 * @autor Jeremy
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import FONTS.src.main.domain.classes.exceptions.*;

import FONTS.src.main.domain.classes.individual_classes.Document;
import FONTS.src.main.domain.classes.resultats.ResultatDocument;
import FONTS.src.stubs.CtrlPersistencia;
/**
 * Classe Control de Document, farà la gestio de tant el document Obert, com interacció amb la capa de dades per obtenir Documents.
 */
public class CtrlDocument {
    //atributs
    /** Document obert */
    private Document documentObert;
    /** Controlador d'Índex */
    private CtrlIndex indexos;
    /** Capa de Persistència */
    private CtrlPersistencia capaPers;

    /**
     * Separa la frase s en ArrayList.
     * @param s Frase a separar.
     * @return ArrayList: Una ArrayList amb les paraules separades.
     */
    private static ArrayList<String> allmatches(String s) {
        ArrayList<String> allM = new ArrayList<>();
        Matcher m = Pattern.compile("((\\w+)|(\\W))")
                .matcher(s);
        while (m.find()) {
            String cosa = m.group();
            if (!cosa.equals(" "))allM.add(cosa);
        }
        return allM;
    }

    /**
     * Creadora per defecte.
     * @param index Controlador dels Indexos, creat previament pel CtrlDomini.
     * @param capa Capa de Persistència, creada prèviament pel CtrlDomini.
     */
    public CtrlDocument(CtrlIndex index, CtrlPersistencia capa) {
        documentObert = null;
        indexos = index;
        capaPers = capa;
    }

    /**
     * Obté el Document actualment Obert.
     * @return ArrayList: Retorna la informació del Document Obert.
     * @throws DocumentNoObert Excepció que es llença quan no hi ha cap Document Obert.
     */
    public ArrayList<String> getDocumentObert() throws DocumentNoObert {
        if (documentObert == null) throw new DocumentNoObert();

        ArrayList<String> arr = new ArrayList<String>();
        arr.add(documentObert.getAutor());
        arr.add(documentObert.getTitol());
        arr.add(documentObert.getLocalitzacio());
        arr.add(Integer.toString(documentObert.getContingut().size()));
        arr.add(documentObert.getDataCreacio().format(DateTimeFormatter.ofPattern("dd-MMM-yy")));
        return arr;
    }

    /**
     * Operació per donar d'alta un Document.
     * @param autor Nom de l'autor del nou Document.
     * @param titol Títol del nou Document.
     * @param path Direcció on es guardarà el nou Document.
     */
    public void AltaDocument(String autor, String titol, String path) {
        Document new_document = new Document(path, autor, titol);

        try {
            capaPers.guardarNouDocument(new_document);
            indexos.altaDocument(path);
            System.out.println("S'ha donat d'alta el nou document correctament.");
        }
        catch (DocumentJaExisteix e) {
            System.out.println("ERROR: Ja existeix un document amb títol " + titol + " i autor " + autor + ".");
            capaPers.eliminarDocument(path);
        } catch (PathJaExisteix e) {
            System.out.println("ERROR: Ja existeix un document amb path " + path + ".");
        }
    }

    /**
     * Operació per donar de baixa un Document.
     * @param path Direcció del Document a esborrar.
     * @param perm Booleà que indica si es vol eliminar el document només del gestor (false) o també del Sistema Operatiu (True).
     * @throws FormatNoReconegut Excepció que es llença quan el document que es vol eliminar no té un format reconegut pel gestor.
     * @throws EliminarDocumentObert Excepció que es llença quan s'està intentant donar de baixa el Document Obert.
     * @throws NoExisteixDocument Excepció que es llença quan es vol donar de baixa un Document que no existeix.
     */
    public void BaixaDocument(String path, boolean perm) throws FormatNoReconegut, EliminarDocumentObert, NoExisteixDocument {
        Document arxiuEliminar = capaPers.getDocument(path);

        if (documentObert != null) {
            String pathObert = documentObert.getLocalitzacio();
            if (path.equals(pathObert)) throw new EliminarDocumentObert();
        }
        if (!path.endsWith(".ojmj")) throw new FormatNoReconegut();

        String autor = arxiuEliminar.getAutor();
        String titol = arxiuEliminar.getTitol();
        if (perm) capaPers.eliminarDocument(path);
        indexos.baixaDocument(autor, titol, path);
    }

    /**
     * Operació per obrir un Document.
     * @param path Direcció del Document a obrir.
     * @throws NoExisteixDocument Excepció que es llença quan es vol obrir un Document que no existeix.
     */
    public void ObrirDocument(String path) throws NoExisteixDocument {
        try {
            TancarDocument();
        } catch (DocumentNoObert e) {
            ; // No passa res
        }
        // Capa de persistencia pot donar excepció de si existeix el document o si el document té el format corresponent
        documentObert = capaPers.getDocument(path);
    }

    /**
     * Operació per tancar el Document obert.
     * @throws DocumentNoObert Excepció que s'executa quan no hi ha cap Document obert.
     */
    public void TancarDocument() throws DocumentNoObert {
        if (documentObert == null) throw new DocumentNoObert();
        GuardarDocument();
        documentObert = null;
    }

    /**
     * Operació per modificar el títol d'un Document.
     * @param nouTitol Nou títol del Document a modificar.
     * @param titolAnterior Títol original del Document.
     * @param autor Autor del Document a modificar.
     * @throws DocumentJaExisteix Excepció que es llença quan ja existeix un document amb autor autor i títol nouTitol.
     * @throws NoExisteixDocument Excepció que es llença quan no existeix un document amb autor autor i títol titol.
     */
    public void EditarTitol(String nouTitol, String titolAnterior, String autor) throws DocumentJaExisteix, NoExisteixDocument {
        if (!indexos.existeixDocument(titolAnterior, autor)) throw new NoExisteixDocument();
        if (indexos.existeixDocument(nouTitol, autor)) throw new DocumentJaExisteix();

        indexos.modificarTitol(nouTitol, titolAnterior, autor);

        // Comprovem si el document a canviar és també el document obert
        if (documentObert != null && documentObert.getAutor().equals(autor) && documentObert.getTitol().equals(titolAnterior)) {
            documentObert.canviarTitolDoc(nouTitol);
            GuardarDocument();
        }
    }

    /**
     * Operació per modificar l'autor d'un Document.
     * @param autorAnterior Antic autor del document a modificar.
     * @param nouAutor Nou autor del Document.
     * @param titol Títol del Document a modificar.
     * @throws DocumentJaExisteix Excepció que es llença quan ja existeix un document amb autor nouAutor i títol titol.
     * @throws NoExisteixDocument Excepció que es llença quan no existeix un document amb autor autorAnterior i títol titol.
     */
    public void EditarAutor(String autorAnterior, String nouAutor, String titol) throws DocumentJaExisteix, NoExisteixDocument {
        if (!indexos.existeixDocument(titol, autorAnterior)) throw new NoExisteixDocument();
        if (indexos.existeixDocument(titol, nouAutor)) throw new DocumentJaExisteix();

        indexos.modificarAutor(autorAnterior, nouAutor, titol);

        // Comprovem si el document a canviar és també el document obert
        if (documentObert != null && documentObert.getAutor().equals(autorAnterior) && documentObert.getTitol().equals(titol)) {
            documentObert.canviarAutorDoc(nouAutor);
            GuardarDocument();
        }
    }

    /**
     * Operació per editar el Document actiu.
     * @param nouContingut Nou contingut del document, aquesta string conté ja totes les modificacions al contingut actual del Document.
     * @throws DocumentNoObert Excepció que es llença quan no hi ha cap Document obert.
    */
    public void EditarContingut(String nouContingut) throws DocumentNoObert {
        if (documentObert == null) throw new DocumentNoObert();

        ArrayList<String> nouContigut = allmatches(nouContingut);
        // En teoria al hacer set contenido, el atributo debería apuntar a nouContigut
        documentObert.modificarContingut(nouContigut);
        GuardarDocument();
        indexos.modificarContingut(documentObert.getLocalitzacio(), nouContigut);
    }


    /**
    * Operació per guardar el Document Obert.
    */
    private void GuardarDocument() {
        // Guardem una còpia del document actual
        capaPers.guardarDocument(documentObert);
    }
}
