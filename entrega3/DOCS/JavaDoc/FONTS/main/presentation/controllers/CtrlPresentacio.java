package FONTS.src.main.presentation.controllers;

import FONTS.src.main.domain.classes.exceptions.*;
import FONTS.src.main.domain.classes.exceptions.DocumentJaExisteix;
import FONTS.src.main.domain.classes.exceptions.NoExisteixDocument;
import FONTS.src.main.domain.controllers.CtrlDomini;
import FONTS.src.main.persistencia.excepcions.*;

import FONTS.src.main.presentation.views.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Classe del Controlador de Presentació.
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class CtrlPresentacio {
    /** Instància del Controlador de Domini */
    private static CtrlDomini cd = new CtrlDomini();

    /**
     * Mostra per pantalla la finestra de la vista principal.
     */
    public static void iniciarPresentacio() {
        VistaPrincipal vp = new VistaPrincipal();
    }

    /**
     * Mostra per pantalla la finestra de l'editor de documents.
     */
    public static void vistaEditor() {
        VistaEditor ve = new VistaEditor();
    }


    /********************
           PANELLS
     ********************/

    // PANELLS DOCUMENTS

    /**
     * Funció per la funcionalitat d'Alta Document.
     * @return Retorna el panell encarregat de la funcionalitat d'Alta Document.
     */
    public static PanelAltaDocument panelAltaDocument() {
        return new PanelAltaDocument();
    }

    /**
     * Funció per la funcionalitat d'Importar Document.
     * @return Retorna el panell encarregat de la funcionalitat d'Importar Document.
     */
    public static PanelImportarDocument panelImportarDocument() {
        return new PanelImportarDocument();
    }

    /**
     * Funció per la funcionalitat d'Obrir Document.
     * @return Retorna el panell encarregat de la funcionalitat d'Obrir Document.
     */
    public static PanelObrirDocument panelObrirDocument() {
        return new PanelObrirDocument();
    }

    /**
     * Funció per la funcionalitat d'Exportar Document.
     * @return Retorna el panell encarregat de la funcionalitat d'Exportar Document.
     */
    public static PanelExportarDocument panelExportarDocument() {
        return new PanelExportarDocument();
    }

    /**
     * Funció per la funcionalitat de Baixa Document.
     * @return Retorna el panell encarregat de la funcionalitat de Baixa Document.
     */
    public static PanelBaixaDocument panelBaixaDocument() {
        return new PanelBaixaDocument();
    }

    /**
     * Funció per la funcionalitat d'Editar Títol i Autor Document.
     * @return Retorna el panell encarregat de la funcionalitat d'Editar Títol i Autor Document.
     */
    public static PanelEditarTitolAutorDocument panelEditarTitolAutorDocument() {
        return new PanelEditarTitolAutorDocument();
    }

    // PANELLS EXPRESSIONS

    /**
     * Funció per la funcionalitat d'Afegir Expressió.
     * @return Retorna el panell encarregat de la funcionalitat d'Afegir Expressió.
     */
    public static PanelAfegirExpressio panelAfegirExpressio() {
        return new PanelAfegirExpressio();
    }

    /**
     * Funció per la funcionalitat de Modificar Expressió.
     * @return Retorna el panell encarregat de la funcionalitat de Modificar Expressió.
     */
    public static PanelModificarExpressio panelModificarExpressio() {
        return new PanelModificarExpressio();
    }

    /**
     * Funció per la funcionalitat d'Eliminar Expressió.
     * @return Retorna el panell encarregat de la funcionalitat d'Eliminar Expressió.
     */
    public static PanelEliminarExpressio panelEliminarExpressio() {
        return new PanelEliminarExpressio();
    }

    /**
     * Funció per la funcionalitat de Consultar Expressió.
     * @return Retorna el panell encarregat de la funcionalitat de Conultar Expressió.
     */
    public static PanelConsultarExpressio panelConsultarExpressio() {
        return new PanelConsultarExpressio();
    }

    // PANELLS CERCAR DOCUMENTS

    /**
     * Funció per la funcionalitat de Cercar els Documents d'un Autor.
     * @return Retorna el panell encarregat de la funcionalitat de Cercar els Documents d'un Autor.
     */
    public static PanelCercarDocAutor panelCercarDocAutor() {
        return new PanelCercarDocAutor();
    }

    /**
     * Funció per la funcionalitat de Cercar el Contingut d'un Document.
     * @return Retorna el panell encarregat de la funcionalitat de Cercar el Contingut d'un Document.
     */
    public static PanelCercarDocContingut panelCercarDocContingut() {
        return new PanelCercarDocContingut();
    }

    /**
     * Funció per la funcionalitat de Cercar els Documents Semblants a un altre Document.
     * @return Retorna el panell encarregat de la funcionalitat de Cercar els Documents Semblants a un altre Document.
     */
    public static PanelCercarDocSemblants panelCercarDocSemblants() { return new PanelCercarDocSemblants(); }

    /**
     * Funció per la funcionalitat de Cercar els Documents que compleixen una Expressió Booleana.
     * @return Retorna el panell encarregat de la funcionalitat de Cercar els Documents que compleixen una Expressió Booleana.
     */
    public static PanelCercarDocExp panelCercarDocExp() { return new PanelCercarDocExp(); }

    /**
     * Funció per la funcionalitat de Cercar els Documents Rellevants a una Sèrie de Paraules.
     * @return Retorna el panell encarregat de la funcionalitat de Cercar els Documents Rellevants a una Sèrie de Paraules.
     */
    public static PanelCercarDocRellevants panelCercarDocRellevants() {
        return new PanelCercarDocRellevants();
    }

    // PANELL CERCAR AUTORS

    /**
     * Funció per la funcionalitat de Cercar els Autors que Comencen per un Prefix.
     * @return Retorna el panell encarregat de la funcionalitat de Cercar els Autors que Comencen per un Prefix.
     */
    public static PanelCercarAutors panelCercarAutors() { return new PanelCercarAutors(); }

    // PANELLS EXTRES

    /**
     * Funció que mostra els Documents que són resultats d'una consulta de Documents.
     * @param arr Array on es troba la informació de tots els documents que són resultat de la consulta.
     * @param metodesOrdenacioDocuments Array amb tots els mètodes d'ordenació de Documents.
     * @return Retorna el panell encarregat de mostra els Documents que són resultat d'una consulta de Documents.
     */
    public static PanelDocuments panelDocuments(ArrayList<ArrayList<String>> arr, String[] metodesOrdenacioDocuments) {
        return new PanelDocuments(arr, metodesOrdenacioDocuments);
    }

    /**
     * Funció que mostra els Autors que són resultats d'una consulta d'Autors.
     * @param arr Array on es troba la informació de tots els autors que són resultat de la consulta.
     * @return Retorna el panell encarregat de mostra els Autors que són resultat d'una consulta d'Autors.
     */
    public static PanelAutors panelAutors(ArrayList<ArrayList<String>> arr) {
        return new PanelAutors(arr);
    }


    /********************
      FUNCIONS ASOCIADES
     ********************/

    // FUNCIONS DOCUMENTS

    /**
     * Operació per afegir un nou Document
     * @param autor Nom de l'autor del nou document.
     * @param titol Titol del nou document.
     * @param path Direcció on es guardarà el nou document.
     * @throws PathJaExisteix Excepció que es llença quan s'intenta afegir un Document amb un path que ja existeix.
     * @throws DocumentJaExisteix Excepció qe es llença quan s'intenta afegir un Document amb un (titol, autor) que ja existeix.
     */
    public static void altaDocument(String titol, String autor, String path) throws PathJaExisteix, DocumentJaExisteix {
        cd.AltaDocument(autor, titol, path);
    }

    /**
     * Operació per importar un Document.
     * @param pOrigen Path origen del Document que es vol importar.
     * @param pDesti Path destí del Document que es vol importar.
     * @throws PathJaExisteix Excepció que es llença quan ja existeix un Document amb pDesti.
     * @throws DocumentJaExisteix Excepció qe es llença quan s'intenta afegir un Document amb un (titol, autor) que ja existeix.
     * @throws ErrorIntern Excepció que es llença quan es produeix un error intern a l'hora d'importar el Document.
     */
    public static void importarDocument(String pOrigen, String pDesti) throws PathJaExisteix, DocumentJaExisteix, ErrorIntern {
        cd.Importar(pOrigen, pDesti);
    }

    /**
     * Operació per obrir un Document.
     * @param path Direcció del Document a obrir.
     * @throws NoExisteixPath Excepció que es llença quan es vol obrir un Document que no existeix.
     * @throws JaExisteixDocumentObert Excepció que es llença quan es vol obrir un Document però ja hi ha un Document obert actualment.
     */
    public static void obrirDocument(String path) throws NoExisteixPath, JaExisteixDocumentObert {
        cd.ObrirDocument(path);
    }

    /**
     * Operació per tancar el Document obert.
     * @throws DocumentNoObert Excepció que s'executa quan no hi ha cap Document obert.
     * @throws DocumentNoGuardat Excepció que s'executa quan es vol tancar el Document obert i no s'han guardat els canvis.
     */
    public static void tancarDocument() throws DocumentNoObert, DocumentNoGuardat {
        cd.TancarDocument();
    }

    /**
     * Operació per guardar el Document obert.
     * @throws DocumentNoObert Excepció que es llença quan no hi ha cap Document obert per guardar.
     */
    public static void guardarDocument() throws DocumentNoObert {
        cd.GuardarDocument();
    }

    /**
     * Operació per obtenir la informació del Document obert.
     * @return Retorna una array amb la informació del Document obert.
     * @throws DocumentNoObert Excepció que es llença quan no hi ha cap Document obert.
     */
    public static ArrayList<String> getDocumentObert() throws DocumentNoObert {
        return cd.getDocumentObert();
    }

    /**
     * Operació per exportar un Document.
     * @param pOrigen Path origen del Document que es vol exportar.
     * @param pDesti Path destí del Document que es vol exportar.
     * @throws NoExisteixPath Excepció que es llença quan no hi ha cap Document amb pOrigen.
     * @throws ErrorIntern Excepció que es llença quan es produeix un error intern a l'hora d'exportar el Document.
     */
    public static void exportarDocument(String pOrigen, String pDesti) throws NoExisteixPath, ErrorIntern {
        cd.Exportar(pOrigen, pDesti);
    }

    /**
     * Operació per tancar el Document obert sense guardar els canvis.
     */
    public static void tancarDocumentForcat() {
        cd.TancarDocumentForcat();
    }

    /**
     * Operació per donar de baixa un Document
     * @param path Direcció del Document a esborrar.
     * @throws FormatNoReconegut Excepció que es llença quan el document que es vol eliminar no té un format reconegut pel gestor.
     * @throws EliminarDocumentObert Excepció que es llença quan s'està intentant donar de baixa el Document Obert.
     * @throws NoExisteixPath Excepció que es llença quan es vol donar de baixa un Document que no existeix.
     */
    public static void baixaDocument(String path) throws FormatNoReconegut, EliminarDocumentObert, NoExisteixPath {
        cd.BaixaDocument(path, true);
    }

    /**
     * Operació per modificar el títol d'un Document.
     * @param nouTitol Nou títol del Document a modificar.
     * @param titolAnterior Títol original del Document.
     * @param autor Autor del Document a modificar.
     * @throws DocumentJaExisteix Excepció que es llença quan ja existeix un document amb autor autor i títol nouTitol.
     * @throws NoExisteixPath Excepció que es llença quan no existeix un document amb autor autor i títol titol.
     */
    public static void editarTitol(String nouTitol, String titolAnterior, String autor) throws DocumentJaExisteix, NoExisteixPath {
        cd.EditarTitol(nouTitol, titolAnterior, autor);
    }

    /**
     * Operació per modificar l'autor d'un Document.
     * @param autorAnterior Antic autor del document a modificar.
     * @param nouAutor Nou autor del Document.
     * @param titol Títol del Document a modificar.
     * @throws DocumentJaExisteix Excepció que es llença quan ja existeix un document amb autor nouAutor i títol titol.
     * @throws NoExisteixPath Excepció que es llença quan no existeix un document amb autor autorAnterior i títol titol.
     */
    public static void editarAutor(String autorAnterior, String nouAutor, String titol) throws DocumentJaExisteix, NoExisteixPath {
        cd.EditarAutor(autorAnterior, nouAutor, titol);
    }

    /**
     * Operació per canviar  el contingut del Document Obert
     * @param nouContingut Nou Contingut a posar
     * @throws DocumentNoObert Excepció que es llença quan no hi ha cap document obert
     */
    public static void editarContingut(String nouContingut) throws DocumentNoObert {
        cd.EditarContingut(nouContingut);
    }

    // FUNCIONS EXPRESSIONS

    /**
     * Crida al mètode AfegirExpressio del Controlador de Domini per afegir una Expressió amb nom nom i contingut exp.
     * @param nom Nom de l'Expressió.
     * @param exp Contingut de l'Expressió.
     * @throws JaExisteixExpressio Excepció que es llença quan ja existeix una Expressió amb nom nom.
     * @throws ExpressioNoValida Excepció que es llença quan novaExp no és vàlida sintàcticament.
     */
    public static void afegirExpressio(String nom, String exp) throws JaExisteixExpressio, ExpressioNoValida {
        cd.AfegirExpressio(nom, exp);
    }

    /**
     * Crida al mètode ModificarExpressio del Controlador de Domini per
     * @param nom Nom de l'Expressió.
     * @param novaExp Nou contingut de l'Expressió.
     * @throws NoExisteixExpressio Excepció que es llença quan no existeix cap Expressió amb nom nom.
     * @throws ExpressioNoValida Excepció que es llença quan novaExp no és vàlida sintàcticament.
     */
    public static void modificarExpressio(String nom, String novaExp) throws NoExisteixExpressio, ExpressioNoValida {
        cd.ModificarExpressio(nom, novaExp);
    }

    /**
     * Crida al mètode EliminarExpressio del Controlador de Domini per eliminar una Expressió donada el seu nom.
     * @param nom Nom de l'Expressió a eliminar.
     * @throws NoExisteixExpressio Excepció que es llença quan no existeix cap Expressió amb nom nom.
     */
    public static void eliminarExpressio(String nom) throws NoExisteixExpressio {
        cd.EliminarExpressio(nom);
    }

    /** Operació per consultar l'Expressió Booleana donat el seu nom.
     * @param nom Nom de l'Expressió.
     * @return String: Contingut de l'Expressió consultada.
     * @throws NoExisteixExpressio Excepció que es llença quan no existeix cap Expressió amb nom nom.
     */
    public static String consultarExpressio(String nom) throws NoExisteixExpressio {
        return cd.ConsultarExpressio(nom);
    }

    /**
     * Operació per llistar totes les Expressions Booleanes.
     * @return ArrayList: Retorna una llista amb tots els noms de les Expressions Booleanes.
     */
    public static ArrayList<String> llistarExpressions() {
        return cd.LlistarExpressions();
    }

    // FUNCIONS CERQUES

    /**
     * Operació per obtenir tots els Documents d'un autor.
     * @param autor Nom de l'autor.
     * @return ArrayList: ArrayList amb la informació de cada Document obtingut, on per cada Document es dona el seu títol i path.
     * @throws NoExisteixAutor Excepció que es llença quan no existeix l'autor autor.
     */
    public static ArrayList<ArrayList<String>> llistarTitolsAutors(String autor) throws NoExisteixAutor {
        return cd.LlistarTitolsAutors(autor);
    }

    /**
     * Operació per veure el contingut d'un Document donat el seu autor i títol.
     * @param autor Autor del Document.
     * @param titol Títol del Document.
     * @return ArrayList: Contingut del Document.
     * @throws NoExisteixDocument Excepció que es llença quan no existeix cap Document amb el títol i l'autor proporcionats.
     */
    public static String llistarContingutDocumentPerAutorTitol(String autor, String titol) throws NoExisteixDocument {
        return cd.LlistarContingutDocumentPerAutorTitol(autor, titol);
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
    public static ArrayList<ArrayList<String>> llistarKDocumentsSemblantsD(int k, String path, int metode) throws NoExisteixDocument, FormatNoReconegut, NumDocumentsIncorrecte {
        return cd.LlistarKDocumentsSemblantsD(k, path, metode);
    }

    /**
     * Operació per buscar els documents que satisfan una Expressió booleana guardada en el gestor.
     * @param nom Nom de l'expressió.
     * @return Per cada document es retorna, autor, titol, Direccio, mida i data de creació
     * @throws NoExisteixExpressio Excepció que es llença quan la expressió no existeix
     */
    public static ArrayList<ArrayList<String>> llistarTitolsPerExpressioGuardada(String nom) throws NoExisteixExpressio {
        return cd.LlistarTitolsPerExpressioGuardada(nom);
    }

    /**
     * Operació per buscar els documents que satisfan una Expressió donada.
     * @param exp expressió booleana
     * @return Per cada document es es retorna, autor, titol, Direccio, mida i data de creació
     * @throws ExpressioNoValida Excepció que es dona quan la expressió donada es erronea a nivell sintactic.
     */
    public static ArrayList<ArrayList<String>> llistarTitolsPerExpressioNoGuardada(String exp) throws ExpressioNoValida {
        return cd.LlistarTitolsPerExpressioNoGuardada(exp);
    }

    /**
     * Operació per buscar els documents k més semblants a un conjunt de paraules donat.
     * @param k num de documents
     * @param conj_paraules conjunt de paraules a comparar
     * @param metode Indica si volem utilitzar el mètode de TFIDF (0) o BM25 (1).
     * @return Per cada document es es retorna, autor, titol, Direccio, mida i data de creació
     * @throws NumDocumentsIncorrecte Excepció que es llença quan k es un numero de documents existents es inferior al parametre donat
     */
    public static ArrayList<ArrayList<String>> llistarKDocumentsRellevantsPParaules(int k, String[] conj_paraules, int metode) throws NumDocumentsIncorrecte {
        TreeSet<String> arr = new TreeSet<>();
        arr.addAll(Arrays.asList(conj_paraules));
        return cd.LlistarKDocumentsRellevantsPParaules(k, arr, metode);
    }

    /**
     * Consultora d'autors donat un prefix.
     * @param prefix Prefix dels autors que volem buscar.
     * @return ArrayList: Array que conté per cada autor amb aquest prefix el seu nom i el nombre de documents escrits.
     */
    public static ArrayList<ArrayList<String>> llistarAutorsPerPrefix(String prefix) {
        return cd.LlistarAutorsPerPrefix(prefix);
    }

    // FUNCIONS ORDENAR

    /**
     * Operació per ordenar el resultat de l'última consulta de documents.
     * @param manera Indica si es volen ordenar els resultats per defecte (0), per nom d'autor (1), per títol (2), per mida (3) o per data de creació (4).
     * @return ArrayList: ArrayList del resultat de l'última consulta ordenada adientment.
     */
    public static ArrayList<ArrayList<String>> ordenarResultatDocuments(int manera) {
        return cd.OrdenarResultatDocuments(manera);
    }

    /**
     * Operació per ordenar el resultat de l'última consulta d'autors.
     * @param manera Indica si es volen ordenar els resultats alfabèticament per nom d'autor (0) o per nombre de documents escrits (1).
     * @return ArrayList: ArrayList del resultat de l'última consulta ordenada adientment.
     */
    public static ArrayList<ArrayList<String>> ordenarResultatAutors(int manera) {
        return cd.OrdenarResultatAutors(manera);
    }

    // TANCAR APLICACIÓ

    /**
     * Operació per guardar l'estat de l'aplicació actual i tancar-la.
     */
    public static void tancarAplicacio() {
        cd.TancarAplicacio();
    }

}
