package FONTS.src.main.domain.controllers;

import FONTS.src.main.domain.classes.exceptions.*;
import FONTS.src.stubs.CtrlPersistencia;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import FONTS.src.main.domain.classes.resultats.*;
import FONTS.src.main.domain.controllers.CtrlOrdenacioResultats;

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
    private ArrayList<ArrayList<String>> getResultat(ArrayList<Resultat> list) {
        ArrayList<ArrayList<String>> res_list = new ArrayList<>();
        for(Resultat i : list){
            res_list.add(i.getResultat());
        }
        return res_list;
    }

    /**
     * Constructora de la Classe
     */
    public CtrlDomini(){
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
    public void AltaDocument(String autor, String titol, String path) {
        ctrlDocument.AltaDocument(autor, titol, path);
    }

    /**
     * Operació per donar de baixa un Document
     * @param path Direcció del Document a esborrar.
     * @param perm Booleà que indica si es vol eliminar el document només del gestor (false) o també del Sistema Operatiu (True).
     * @throws FormatNoReconegut Excepció que es llença quan el document que es vol eliminar no té un format reconegut pel gestor.
     * @throws EliminarDocumentObert Excepció que es llença quan s'està intentant donar de baixa el Document Obert.
     * @throws NoExisteixDocument Excepció que es llença quan es vol donar de baixa un Document que no existeix.
     */
    public void BaixaDocument(String path, boolean perm) throws FormatNoReconegut, EliminarDocumentObert, NoExisteixDocument {
        ctrlDocument.BaixaDocument(path, perm);
    }

    /**
     * Operació per obrir un Document.
     * @param path Direcció del Document a obrir.
     * @throws NoExisteixDocument Excepció que es llença quan es vol obrir un Document que no existeix.
     */
    public void ObrirDocument(String path) throws NoExisteixDocument {
        ctrlDocument.ObrirDocument(path);
    }

    /**
     * Operació per tancar el Document obert.
     * @throws DocumentNoObert Excepció que s'executa quan no hi ha cap Document obert.
     */
    public void TancarDocument() throws DocumentNoObert {
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
     * @throws NoExisteixDocument Excepció que es llença quan no existeix un document amb autor autor i títol titol.
     */
    public void EditarTitol(String nouTitol, String titolAnterior, String autor) throws DocumentJaExisteix, NoExisteixDocument {
        ctrlDocument.EditarTitol(nouTitol, titolAnterior, autor);
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
        return ctrlIndex.llistarAutorsPerPrefix(prefix);
    }

    /**
     * Operació per buscar els documents k més semblants a un document donat
     * @param k num de documents
     * @param path Direcció del Document a comparar
     * @param manera Indica si volem utilitzar el mètode de TFIDF (0) o BM25 (1).
     * @return Per cada document es es retorna, autor, titol, Direccio, mida i data de creació
     * @throws NoExisteixDocument Excepció que es llença quan el document a comparar no existeix en el gestor
     * @throws FormatNoReconegut Excepció que es llença quan el document a comparar no té el format correcte
     * @throws NumDocumentsIncorrecte Excepció que es llença quan k es un numero de documents existents es inferior al parametre donat
     */
    public ArrayList<ArrayList<String>>LlistarKDocumentsSemblantsD(Integer k, String path, Integer manera) throws NoExisteixDocument, FormatNoReconegut, NumDocumentsIncorrecte {
        return getResultat(ctrlIndex.llistarKDocumentsSemblants(k,path,manera));
    }

    /**
     * Operació per buscar els documents k més semblants a un conjunt de paraules donat.
     * @param k num de documents
     * @param conj_paraules conjunt de paraules a comparar
     * @param manera Indica si volem utilitzar el mètode de TFIDF (0) o BM25 (1).
     * @return Per cada document es es retorna, autor, titol, Direccio, mida i data de creació
     * @throws NumDocumentsIncorrecte Excepció que es llença quan k es un numero de documents existents es inferior al parametre donat
     */
    public ArrayList<ArrayList<String>>LlistarKDocumentsRellevantsPParaules(Integer k, TreeSet<String> conj_paraules, Integer manera) throws NumDocumentsIncorrecte {
        return getResultat(ctrlIndex.llistarKDocumentsRellevantsPParaules(k, conj_paraules, manera));
    }

    /**
     * Operació per obtenir tots els Documents d'un autor.
     * @param autor Nom de l'autor.
     * @return ArrayList: ArrayList amb la informació de cada Document obtingut, on per cada Document es dona el seu títol i path.
     * @throws NoExisteixAutor Excepció que es llença quan no existeix l'autor autor.
     */
    public ArrayList<ArrayList<String>>LlistarTitolsAutors(String autor) throws NoExisteixAutor {
        return getResultat(ctrlIndex.llistarTitolsAutor(autor));
    }

    /**
     * Operació per buscar els documents que satisfan una Expressió booleana guardada en el gestor.
     * @param nom nom de la expressió
     * @return Per cada document es es retorna, autor, titol, Direccio, mida i data de creació
     * @throws NoExisteixExpressio Excepció que es llença quan la expressió no existeix
     */
    public ArrayList<ArrayList<String>> LlistarTitolsPerExpressioGuardada(String nom) throws NoExisteixExpressio {
        return getResultat(ctrlIndex.llistarDocumentsExpressioBooleana(nom));
    }

    /**
     * Operació per buscar els documents que satisfan una Expressió donada
     * @param exp expressió booleana
     * @return Per cada document es es retorna, autor, titol, Direccio, mida i data de creació
     * @throws ExpressioNoValida Excepció que es dona quan la expressió donada es erronea a nivell sintactic.
     */
    public ArrayList<ArrayList<String>> LlistarTitolsPerExpressioNoGuardada(String exp) throws ExpressioNoValida {
        return getResultat(ctrlIndex.llistarDocumentsExpressioBooleanaFora(exp));
    }

    /**
     * Operació per veure el contingut d'un Document donat el seu autor i títol.
     * @param autor Autor del Document.
     * @param titol Títol del Document.
     * @return ArrayList: Contingut del Document.
     * @throws NoExisteixDocument Excepció que es llença quan no existeix cap Document amb el títol i l'autor proporcionats.
     */
    public ArrayList<String> LlistarContingutDocumentPerAutorTitol(String autor, String titol) throws NoExisteixDocument {
        return ctrlIndex.llistarContingutDocumentPerAutorTitol(autor, titol).getResultat();
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
}
