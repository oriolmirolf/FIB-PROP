package FONTS.src.main.domain.controllers;

import FONTS.src.main.domain.classes.exceptions.*;
import FONTS.src.main.persistencia.CtrlPersistencia;
import FONTS.src.main.persistencia.excepcions.ErrorIntern;
import FONTS.src.main.persistencia.excepcions.NoExisteixPath;
import FONTS.src.main.persistencia.excepcions.PathJaExisteix;

import java.util.ArrayList;
import java.util.TreeSet;

import FONTS.src.main.domain.classes.resultats.*;

/**
 * Classe del Controlador de Domini.
 */
public class CtrlDomini {
    /** Controlador de Document */
    private CtrlDocument ctrlDocument;
    /** Controlador d'Índex */
    private CtrlIndex ctrlIndex;
    /** Controlador d'Ordenació de Resultats */
    private CtrlOrdenacioResultats ctrlOrdenacio;

    /**
     * Operació per pasar a un format primitiu el resultats
     * @param list Llista que conté tots els resultats d'una consulta
     * @return es retorna la llista en format formats primitius
     */
    private ArrayList<ArrayList<String>> getResultat(ArrayList<Resultat> list) { // ES POT ELIMINAR !!!!!!!!!
        ArrayList<ArrayList<String>> res_list = new ArrayList<>();
        for(Resultat i : list){
            res_list.add(i.getResultat());
        }
        return res_list;
    }

    /**
     * Constructora de la Classe
     */
    public CtrlDomini() {
        CtrlPersistencia pers = new CtrlPersistencia();
        ctrlIndex = new CtrlIndex(pers);
        ctrlDocument = new CtrlDocument(ctrlIndex,pers);
        ctrlOrdenacio = new CtrlOrdenacioResultats();
    }

    /**
     * Operació per afegir un nou document
     * @param autor Nom de l'autor del nou document.
     * @param titol Titol del nou document.
     * @param path Direcció on es guardarà el nou document.
     */
    public void AltaDocument(String autor, String titol, String path) throws PathJaExisteix, DocumentJaExisteix {
        ctrlDocument.AltaDocument(autor, titol, path);
    }

    /**
     * Operació per donar de baixa un Document
     * @param path Direcció del Document a esborrar.
     * @param perm Booleà que indica si es vol eliminar el document només del gestor (false) o també del Sistema Operatiu (True).
     * @throws FormatNoReconegut Excepció que es llença quan el document que es vol eliminar no té un format reconegut pel gestor.
     * @throws EliminarDocumentObert Excepció que es llença quan s'està intentant donar de baixa el Document Obert.
     * @throws NoExisteixPath Excepció que es llença quan es vol donar de baixa un Document que no existeix.
     */
    public void BaixaDocument(String path, boolean perm) throws FormatNoReconegut, EliminarDocumentObert, NoExisteixPath {
        ctrlDocument.BaixaDocument(path, perm);
    }

    /**
     * Operació per obrir un Document.
     * @param path Direcció del Document a obrir.
     * @throws NoExisteixPath Excepció que es llença quan es vol obrir un Document que no existeix.
     * @throws JaExisteixDocumentObert Excepció que es llença quan es vol obrir un Document però ja hi ha un Document obert actualment.
     */
    public void ObrirDocument(String path) throws NoExisteixPath, JaExisteixDocumentObert {
        ctrlDocument.ObrirDocument(path);
    }

    /**
     * Operació per tancar el Document obert.
     * @throws DocumentNoObert Excepció que s'executa quan no hi ha cap Document obert.
     * @throws DocumentNoGuardat Excepció que s'executa quan es vol tancar el Document obert i no s'han guardat els canvis.
     */
    public void TancarDocument() throws DocumentNoObert, DocumentNoGuardat {
        ctrlDocument.TancarDocument();
    }

    /**
     * Operació pel DriverDomini
     * @return Retorna el Document Obert actual.
     * @throws DocumentNoObert Excepció que s'executa quan no hi ha cap Document obert.
     */
    public ArrayList<String> getDocumentObert() throws DocumentNoObert {
        return ctrlDocument.getDocumentObert();
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
        ctrlDocument.EditarTitol(nouTitol, titolAnterior, autor);
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
        ctrlDocument.EditarAutor(autorAnterior, nouAutor, titol);
    }

    /**
     * Operació per canviar  el contingut del Document Obert
     * @param nouContingut Nou Contingut a posar
     * @throws DocumentNoObert Excepció que es llença quan no hi ha cap document obert
     */
    public void EditarContingut(String nouContingut) throws DocumentNoObert {
        ctrlDocument.EditarContingut(nouContingut);
    }

    /**
     * Consultora d'autors donat un prefix.
     * @param prefix Prefix dels autors que volem buscar.
     * @return ArrayList: Array que conté per cada autor amb aquest prefix el seu nom i el nombre de documents escrits.
     */
    public ArrayList<ArrayList<String>> LlistarAutorsPerPrefix(String prefix) {
        // llistarAutorsPerPrefix() ja retorna els autors ordenats alfabèticament pel seu nom.
        ArrayList<ResultatAutor> arr = ctrlIndex.llistarAutorsPerPrefix(prefix);
        // Emmagatzemem en el controlador d'ordenació de resultats l'últim resultat d'una consulta d'autors.
        ctrlOrdenacio.setResultatAutor(arr);
        return OrdenarResultatAutors(0);
    }

    /**
     * Operació per buscar els documents k més semblants a un document donat
     * @param k num de documents
     * @param path Direcció del Document a comparar
     * @param metode Indica si volem utilitzar el mètode de TFIDF (0) o BM25 (1).
     * @return Per cada document es es retorna, autor, titol, path, mida i data de creació.
     * @throws NoExisteixDocument Excepció que es llença quan el document a comparar no existeix en el gestor
     * @throws FormatNoReconegut Excepció que es llença quan el document a comparar no té el format correcte
     * @throws NumDocumentsIncorrecte Excepció que es llença quan k es un numero de documents existents es inferior al parametre donat
     */
    public ArrayList<ArrayList<String>> LlistarKDocumentsSemblantsD(int k, String path, int metode) throws NoExisteixDocument, FormatNoReconegut, NumDocumentsIncorrecte {
        // llistarKDocumentsSemblants() ja retorna els documents ordenats per semblança.
        ArrayList<ResultatDocument> arr = ctrlIndex.llistarKDocumentsSemblants(k, path, metode);
        // Emmagatzemem en el controlador d'ordenació de resultats l'últim resultat d'una consulta de documents.
        ctrlOrdenacio.setResultatDocument(arr);
        // Si en canvi volem ordenar els documents per un altre mètode llavors cridem a la funció d'ordenar segons l'últim mètode escollit.
        if (metode != 0) return OrdenarResultatDocuments(metode);

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for (ResultatDocument rd : arr) {
            res.add(rd.getResultat());
        }
        return res;
    }

    /**
     * Operació per buscar els documents k més semblants a un conjunt de paraules donat.
     * @param k num de documents
     * @param conj_paraules conjunt de paraules a comparar
     * @param metode Indica si volem utilitzar el mètode de TFIDF (0) o BM25 (1).
     * @return Per cada document es es retorna, autor, titol, Direccio, mida i data de creació
     * @throws NumDocumentsIncorrecte Excepció que es llença quan k es un numero de documents existents es inferior al parametre donat
     */
    public ArrayList<ArrayList<String>>LlistarKDocumentsRellevantsPParaules(int k, TreeSet<String> conj_paraules, int metode) throws NumDocumentsIncorrecte {
        // llistarKDocumentsSemblants() ja retorna els documents ordenats per rellevància.
        ArrayList<ResultatDocument> arr = ctrlIndex.llistarKDocumentsRellevantsPParaules(k, conj_paraules, metode);
        // Emmagatzemem en el controlador d'ordenació de resultats l'últim resultat d'una consulta de documents.
        ctrlOrdenacio.setResultatDocument(arr);
        // Si en canvi volem ordenar els documents per un altre mètode llavors cridem a la funció d'ordenar segons l'últim mètode escollit.
        if (metode != 0) return OrdenarResultatDocuments(metode);

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for (ResultatDocument rd : arr) {
            res.add(rd.getResultat());
        }
        return res;
    }

    /**
     * Operació per obtenir tots els Documents d'un autor.
     * @param autor Nom de l'autor.
     * @return ArrayList: ArrayList amb la informació de cada Document obtingut, on per cada Document es dona el seu títol i path.
     * @throws NoExisteixAutor Excepció que es llença quan no existeix l'autor autor.
     */
    public ArrayList<ArrayList<String>>LlistarTitolsAutors(String autor) throws NoExisteixAutor {
        ArrayList<ResultatDocument> arr = ctrlIndex.llistarTitolsAutor(autor);
        // Emmagatzemem en el controlador d'ordenació de resultats l'últim resultat d'una consulta de documents.
        ctrlOrdenacio.setResultatDocument(arr);
        // Retornem els resultats ordenats alfabèticament per autor.
        return OrdenarResultatDocuments(1);
    }

    /**
     * Operació per buscar els documents que satisfan una Expressió booleana guardada en el gestor.
     * @param nom Nom de l'expressió.
     * @return Per cada document es retorna, autor, titol, Direccio, mida i data de creació
     * @throws NoExisteixExpressio Excepció que es llença quan la expressió no existeix
     */
    public ArrayList<ArrayList<String>> LlistarTitolsPerExpressioGuardada(String nom) throws NoExisteixExpressio {
        ArrayList<ResultatDocument> arr = ctrlIndex.llistarDocumentsExpressioBooleana(nom);
        // Emmagatzemem en el controlador d'ordenació de resultats l'últim resultat d'una consulta de documents.
        ctrlOrdenacio.setResultatDocument(arr);
        // Retornem els resultats ordenats alfabèticament per autor.
        return OrdenarResultatDocuments(1);
    }

    /**
     * Operació per buscar els documents que satisfan una Expressió donada
     * @param exp expressió booleana
     * @return Per cada document es es retorna, autor, titol, Direccio, mida i data de creació
     * @throws ExpressioNoValida Excepció que es dona quan la expressió donada es erronea a nivell sintactic.
     */
    public ArrayList<ArrayList<String>> LlistarTitolsPerExpressioNoGuardada(String exp) throws ExpressioNoValida {
        ArrayList<ResultatDocument> arr = ctrlIndex.llistarDocumentsExpressioBooleanaFora(exp);
        // Emmagatzemem en el controlador d'ordenació de resultats l'últim resultat d'una consulta de documents.
        ctrlOrdenacio.setResultatDocument(arr);
        // Retornem els resultats ordenats alfabèticament per títol.
        return OrdenarResultatDocuments(1);
    }

    /**
     * Operació per veure el contingut d'un Document donat el seu autor i títol.
     * @param autor Autor del Document.
     * @param titol Títol del Document.
     * @return ArrayList: Contingut del Document.
     * @throws NoExisteixDocument Excepció que es llença quan no existeix cap Document amb el títol i l'autor proporcionats.
     */
    public String LlistarContingutDocumentPerAutorTitol(String autor, String titol) throws NoExisteixDocument {
        return ctrlIndex.llistarContingutDocumentPerAutorTitol(autor, titol);
    }

    /**
     * Operació per afegir una Expressió Booleana al gestor.
     * @param nom Nom de l'Expressió.
     * @param exp Contingut de l'Expressió.
     * @throws JaExisteixExpressio Excepció que es llença quan ja existeix una Expressió amb nom nom.
     * @throws ExpressioNoValida Excepció que es llença quan novaExp no és vàlida sintàcticament.
     */
    public void AfegirExpressio(String nom, String exp) throws JaExisteixExpressio, ExpressioNoValida {
        ctrlIndex.afegirExpressio(nom, exp);
    }

    /**
     * Operació per eliminar una Expressió Booleana del gestor.
     * @param nom Nom de l'Expressió a eliminar.
     * @throws NoExisteixExpressio Excepció que es llença quan no existeix cap Expressió amb nom nom.
     */
    public void EliminarExpressio(String nom) throws NoExisteixExpressio {
        ctrlIndex.eliminarExpressio(nom);
    }

    /**
     * Operació per modificar una Expressió Booleana ja existent.
     * @param nom Nom de l'Expressió.
     * @param novaExp Nou contingut de l'Expressió.
     * @throws NoExisteixExpressio Excepció que es llença quan no existeix cap Expressió amb nom nom.
     * @throws ExpressioNoValida Excepció que es llença quan novaExp no és vàlida sintàcticament.
     */
    public void ModificarExpressio(String nom, String novaExp) throws NoExisteixExpressio, ExpressioNoValida {
        ctrlIndex.modificarExpressio(nom, novaExp);
    }

    /** Operació per consultar l'Expressió Booleana donat el seu nom.
     * @param nom Nom de l'Expressió.
     * @return String: Contingut de l'Expressió consultada.
     * @throws NoExisteixExpressio Excepció que es llença quan no existeix cap Expressió amb nom nom.
     */
    public String ConsultarExpressio(String nom) throws NoExisteixExpressio {
        return ctrlIndex.consultarExpressio(nom);
    }

    /**
     * Operació per llistar totes les Expressions Booleanes.
     * @return ArrayList: Retorna una llista amb tots els noms de les Expressions Booleanes.
     */
    public ArrayList<String> LlistarExpressions() {
        // return ctrlIndex.llistarExpressions();
        ArrayList<String> arr = ctrlIndex.llistarExpressions();
        return arr;
    }

    /**
     * Operació per ordenar el resultat de l'última consulta d'autors.
     * @param manera Indica si es volen ordenar els resultats alfabèticament per nom d'autor (0) o per nombre de documents escrits (1).
     * @return ArrayList: ArrayList del resultat de l'última consulta ordenada adientment.
     */
    public ArrayList<ArrayList<String>> OrdenarResultatAutors(int manera) {
        // Fem les crides al Controlador d'Ordenació amb paràmetre null per indicar que volem ordenar l'últim resultat guardat en el controlador d'ordenació.
        ArrayList<ResultatAutor> arr = null;
        if (manera == 0) arr = ctrlOrdenacio.OrdenarAlfabeticamentPerAutor(null);
        else if (manera == 1) arr = ctrlOrdenacio.OrdenarPerNTitolsAutor(null);

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for (ResultatAutor ra : arr) res.add(ra.getResultat());
        return res;
    }

    /**
     * Operació per ordenar el resultat de l'última consulta de documents.
     * @param manera Indica si es volen ordenar els resultats per defecte (0), per nom d'autor (1), per títol (2), per mida (3) o per data de creació (4).
     * @return ArrayList: ArrayList del resultat de l'última consulta ordenada adientment.
     */
    public ArrayList<ArrayList<String>> OrdenarResultatDocuments(int manera) {
        // Fem les crides al Controlador d'Ordenació amb paràmetre null per indicar que volem ordenar l'últim resultat guardat en el controlador d'ordenació.
        ArrayList<ResultatDocument> arr = null;
        if (manera == 0) arr = ctrlOrdenacio.getResultatDocument();
        else if (manera == 1) arr = ctrlOrdenacio.OrdenarAlfabeticamentPerAutorTitol(null);
        else if (manera == 2) arr = ctrlOrdenacio.OrdenarAlfabeticamentPerTitolAutor(null);
        else if (manera == 3) arr = ctrlOrdenacio.OrdenarPerMidaDocument(null);
        else if (manera == 4) arr = ctrlOrdenacio.OrdenarPerDataCreacio(null);

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for (ResultatDocument rd : arr) {
            res.add(rd.getResultat());
        }
        return res;
    }

    /**
     * Operació per guardar el Document obert.
     * @throws DocumentNoObert Excepció que es llença quan no hi ha cap Document obert per guardar.
     */
    public void GuardarDocument() throws DocumentNoObert{
        ctrlDocument.GuardarDocument();
    }

    /**
     * Operació per guardar l'estat de l'aplicació actual i tancar-la.
     */
    public void TancarAplicacio(){
        ctrlIndex.TancarAplicacio();
    }

    /**
     * Operació per importar un Document.
     * @param pOrigen Path origen del Document que es vol importar.
     * @param pDesti Path destí del Document que es vol importar.
     * @throws PathJaExisteix Excepció que es llença quan ja existeix un Document amb pDesti.
     * @throws DocumentJaExisteix Excepció qe es llença quan s'intenta afegir un Document amb un (titol, autor) que ja existeix.
     * @throws ErrorIntern Excepció que es llença quan es produeix un error intern a l'hora d'importar el Document.
     */
    public void Importar(String pOrigen, String pDesti) throws PathJaExisteix, DocumentJaExisteix, ErrorIntern {
        ctrlDocument.Importar(pOrigen, pDesti);
    }

    /**
     * Operació per exportar un Document.
     * @param pOrigen Path origen del Document que es vol exportar.
     * @param pDesti Path destí del Document que es vol exportar.
     * @throws NoExisteixPath Excepció que es llença quan no hi ha cap Document amb pOrigen.
     * @throws ErrorIntern Excepció que es llença quan es produeix un error intern a l'hora d'exportar el Document.
     */
    public void Exportar(String pOrigen, String pDesti) throws NoExisteixPath, ErrorIntern {
        ctrlDocument.Exportar(pOrigen, pDesti);
    }

    /**
     * Operació per tancar el Document obert sense guardar els canvis.
     */
    public void TancarDocumentForcat(){
        ctrlDocument.TancarDocumentForcat();
    }
}
