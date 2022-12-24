package FONTS.src.main.domain.controllers;

import java.time.LocalDateTime;
/**
 * @autor Jeremy
 */
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import FONTS.src.main.domain.classes.exceptions.*;
import FONTS.src.main.domain.classes.exceptions.DocumentJaExisteix;
import FONTS.src.main.persistencia.excepcions.*;
import FONTS.src.main.domain.classes.individual_classes.Document;

import FONTS.src.main.persistencia.CtrlPersistencia;
import FONTS.src.main.transversal.*;
/**
 * Classe Control de Document, farà la gestio de tant el document Obert, com interacció amb la capa de dades per obtenir Documents.
 */
public class CtrlDocument {
    //atributs
    /** Document obert */
    private Document documentObert;
    private Document documentModificat;
    private Boolean modificacio;
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
        documentModificat = null;
        indexos = index;
        capaPers = capa;
        modificacio = false;
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
        arr.add(documentObert.getContingutRaw());
        return arr;
    }

    /**
     * Operació per donar d'alta un Document.
     * @param autor Nom de l'autor del nou Document.
     * @param titol Títol del nou Document.
     * @param path Direcció on es guardarà el nou Document.
     * @throws DocumentJaExisteix El document existeix en el Domini
     * @throws PathJaExisteix Existeix un document amb el mateix path
     */
    public void AltaDocument(String autor, String titol, String path) throws PathJaExisteix, DocumentJaExisteix {
        Document new_document = new Document(path, autor, titol);

        try {
            capaPers.guardarNouDocument(new_document.getTitol(), new_document.getAutor(),new_document.getLocalitzacio(), new_document.getDataCreacio(),new_document.getDataDarreraModificacio(),new_document.getContingutRaw());
            indexos.altaDocument(path);
        }
        catch (DocumentJaExisteix e) {
            try {
                capaPers.eliminarDocument(path);
            } catch (NoExisteixPath e1) {
         
            }
            throw e;
        }
    }

    /**
     * Operació per donar de baixa un Document.
     * @param path Direcció del Document a esborrar.
     * @param perm Booleà que indica si es vol eliminar el document només del gestor (false) o també del Sistema Operatiu (True).
     * @throws FormatNoReconegut Excepció que es llença quan el document que es vol eliminar no té un format reconegut pel gestor.
     * @throws EliminarDocumentObert Excepció que es llença quan s'està intentant donar de baixa el Document Obert.
     * @throws NoExisteixPath Excepció que es llença quan es vol donar de baixa un Document que no existeix.
     */
    public void BaixaDocument(String path, boolean perm) throws FormatNoReconegut, EliminarDocumentObert, NoExisteixPath {
        
         Tupla<String,String, String, LocalDateTime, LocalDateTime, String> info = capaPers.getDocument(path);
         Document arxiuEliminar = new Document(info.getFirst(),info.getSecond(),info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
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
     * @throws NoExisteixPath Excepció que es llença quan es vol obrir un Document que no existeix.
     * @throws JaExisteixDocumentObert Excepció que es llença quan es vol obrir un Document però ja hi ha un Document obert actualment.
     */
    public void ObrirDocument(String path) throws NoExisteixPath, JaExisteixDocumentObert {
        if (documentObert != null) throw new JaExisteixDocumentObert();

        // Capa de persistencia pot donar excepció de si existeix el document o si el document té el format corresponent
        Tupla<String,String, String, LocalDateTime, LocalDateTime, String> info = capaPers.getDocument(path);
        documentObert = new Document(info.getFirst(),info.getSecond(),info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
        documentModificat = documentObert.clonar();
    }

    /**
     * Operació per tancar el Document obert.
     * @throws DocumentNoObert Excepció que s'executa quan no hi ha cap Document obert.
     * @throws DocumentNoGuardat Existeix un document obert sense guardar els canvis.
     */
    public void TancarDocument() throws DocumentNoObert, DocumentNoGuardat {
        if (documentObert == null) throw new DocumentNoObert();
        if (modificacio) throw new DocumentNoGuardat("El Document obert té canvis sense guardar.");

        documentObert = null;
        documentModificat = null;
    }

    /**
     * Operació per modificar el títol d'un Document.
     * @param nouTitol Nou títol del Document a modificar.
     * @param titolAnterior Títol original del Document.
     * @param autor Autor del Document a modificar.
     * @throws DocumentJaExisteix Excepció que es llença quan ja existeix un document amb autor autor i títol nouTitol.
     * @throws NoExisteixPath Excepció que es llença quan no existeix un document amb autor autor i títol titol.
     */
    public void EditarTitol(String nouTitol, String titolAnterior, String autor) throws DocumentJaExisteix, NoExisteixPath {
        if (!indexos.existeixDocument(titolAnterior, autor)) throw new NoExisteixPath();
        if (indexos.existeixDocument(nouTitol, autor)) throw new DocumentJaExisteix();

        indexos.modificarTitol(nouTitol, titolAnterior, autor);

        // Comprovem si el document a canviar és també el document obert
        if (documentObert != null && documentObert.getAutor().equals(autor) && documentObert.getTitol().equals(titolAnterior)) {
            documentObert.canviarTitolDoc(nouTitol);
            documentModificat.canviarTitolDoc(nouTitol);
        }
    }


    /**
     * Operació per modificar l'autor d'un Document.
     * @param autorAnterior Antic autor del document a modificar.
     * @param nouAutor Nou autor del Document.
     * @param titol Títol del Document a modificar.
     * @throws DocumentJaExisteix Excepció que es llença quan ja existeix un document amb autor nouAutor i títol titol.
     * @throws NoExisteixPath Excepció que es llença quan no existeix un document amb autor autorAnterior i títol titol.
     */
    public void EditarAutor(String autorAnterior, String nouAutor, String titol) throws DocumentJaExisteix, NoExisteixPath {
        if (!indexos.existeixDocument(titol, autorAnterior)) throw new NoExisteixPath();
        if (indexos.existeixDocument(titol, nouAutor)) throw new DocumentJaExisteix();

        indexos.modificarAutor(autorAnterior, nouAutor, titol);

        // Comprovem si el document a canviar és també el document obert
        if (documentObert != null && documentObert.getAutor().equals(autorAnterior) && documentObert.getTitol().equals(titol)) {
            documentObert.canviarAutorDoc(nouAutor);
            documentModificat.canviarAutorDoc(nouAutor); 
        }
    }


    /**
     * Operació per editar el Document actiu.
     * @param nouContingut Nou contingut del document, aquesta string conté ja totes les modificacions al contingut actual del Document.
     * @throws DocumentNoObert Excepció que es llença quan no hi ha cap Document obert.
    */
    public void EditarContingut(String nouContingut) throws DocumentNoObert {
        if (documentObert == null) throw new DocumentNoObert();
        documentModificat.modificarContingut(nouContingut);
        modificacio = true;

    }


    /**
    * Operació per guardar el Document Obert.
     * @throws DocumentNoObert
    */
    public void GuardarDocument() throws DocumentNoObert{
        if (documentObert == null) throw new DocumentNoObert();
        ArrayList<String> anticContigut = documentObert.getContingut();
        ArrayList<String> nouContigut = documentModificat.getContingut();
        if(! anticContigut.equals(nouContigut)) indexos.modificarContingut(documentObert.getLocalitzacio(), nouContigut);
        capaPers.guardarDocumentExistent(documentObert.getTitol(), documentModificat.getAutor(), documentModificat.getLocalitzacio(), documentModificat.getDataCreacio(), documentModificat.getDataDarreraModificacio(), documentModificat.getContingutRaw());
        documentObert = documentModificat;
        documentModificat = documentObert.clonar();
        modificacio = false;
    }
    
    
    /** 
     * @param pOrigen Path origen del fitxer.
     * @param pDesti Path desti on es vol guardar el .ojmj.
     * @throws ErrorIntern Excepció que es llença quan es hi ha un error intern
     * @throws PathJaExisteix Excepció que es llença quan es vol guardar un document amb el mateix path
     * @throws DocumentJaExisteix Excepció que es llença quan ja existeix el document en el Domini
     */
    public void Importar(String pOrigen, String pDesti) throws ErrorIntern, PathJaExisteix, DocumentJaExisteix{
        Tupla<String,String, String, LocalDateTime, LocalDateTime, String> tup = capaPers.importar(pOrigen, pDesti);
        if(indexos.existeixDocument(tup.getFirst(), tup.getSecond())){
            try {
                capaPers.eliminarDocument(pDesti);
            } catch (NoExisteixPath e) {
                
            }
            throw new DocumentJaExisteix();
        }
        indexos.altaDocument(pDesti);
    }
    
    /** 
     * @param pOrigen Path del fitxer .ojmj
     * @param pDesti path destinació 
     * @throws NoExisteixPath Excepció que es llença quan el fitxer .ojmj no exiteix
     * @throws ErrorIntern Excepció que es llença quan es hi ha un error intern
     */
    public void Exportar(String pOrigen, String pDesti) throws NoExisteixPath, ErrorIntern{
        capaPers.exportar(pOrigen, pDesti);
    }
    /**
     * Operació per tancar el document obert sense guardar
     */
    public void TancarDocumentForcat(){
        documentObert = null;
        documentModificat = null;
        modificacio = false;
    }
}