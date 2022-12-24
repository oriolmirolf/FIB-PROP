package FONTS.src.main.domain.classes.individual_classes;

import java.util.*;
import java.time.*;


/**
 * Clase utilitzada per representar els documents al programa.
 * @author Marc Gonzalez
*/
public class Document {
    /** Títol del Document */
    private String titol;
    /** Autor del Document */
    private String autor;
    /** Path del Document */
    private String localitzacio;
    /** Data de creació del Document */
    private LocalDate dataCreacio;
    /** Data de Darrera Modificació del Document */
    private LocalDate dataDarreraModificacio;
    /** Contingut del Document en una estructura de dades enfocada a la consulta */
    private Map<String, HashSet<Integer>> paraules;
    /** Contingut del Document */
    private ArrayList<String> contingut;

    /**
     * Creadora de la classe Document.
     * Retorna una instancia de la Classe amb els atributs passats per parametre i la dataCreacio i dataDarreraModificacio a la data actual.
     * @param path Indica la localitzacio del Document al sistema
     * @param aut Indica l'autor del Document
     * @param tit Indica el títol del Document
     */
    public Document(String path, String aut, String tit) {
        titol = tit;
        autor = aut;
        localitzacio = path;
        dataCreacio = LocalDate.now();
        dataDarreraModificacio = LocalDate.now();
        paraules = new TreeMap<String, HashSet<Integer>>();
        contingut = new ArrayList<String>();
    }

    /**
     *	Canvia el títol del Document.
     *	@param nouTitol Indica el titol que tindrà el Document després de la modificació.
     */
    public void canviarTitolDoc(String nouTitol) {
	    titol = nouTitol;
	    dataDarreraModificacio = LocalDate.now();
    }

    /**
     *	Canvia l'autor del Document.
     *	@param nouAutor Indica el nom de l'autor que tindrà el Document després de la modificació.
     */
    public void canviarAutorDoc(String nouAutor) {
	    autor = nouAutor;
	    dataDarreraModificacio = LocalDate.now();
    }

    /**
     *	Modifica el contingut del Document.
     *	@param cont Conté totes les noves paraules que pasaràn a formar part del Document.
     */
    public void modificarContingut(ArrayList<String> cont) {
        paraules.clear();
        contingut = cont;
        for (int i = 0; i < cont.size(); ++i) {
            String w = cont.get(i);
            if (paraules.containsKey(w)) {
                HashSet<Integer> s = paraules.get(w);
                s.add(i);
            }
            else {
                HashSet<Integer> s = new HashSet<Integer>();
                s.add(i);
                paraules.put(w, s);
            }
        }
        dataDarreraModificacio = LocalDate.now();
    }

    /**
     * Getter del títol.
     * @return String: Títol del document.
     */
    public String getTitol() {
	    return titol;
    }

    /**
     * Getter de l'autor
     * @return String: Autor del document.
     */
    public String getAutor() {
	    return autor;
    }

    /**
     * Getter de la localització.
     * @return String: Path del Document.
     * */
    public String getLocalitzacio() {
	    return localitzacio;
    }

    /**
     * Getter de la data de creació.
     * @return LocalDate: Data de creació del Document.
     */
    public LocalDate getDataCreacio() {
	    return dataCreacio;
    }

    /**
     * Getter de la data de la darrera modificació.
     * @return LocalDate: Data de la darrera modificació del Document.
     */
    public LocalDate getDataDarreraModificacio() {
	    return dataDarreraModificacio;
    }

    /**
     * Getter del contingut.
     * @return ArrayList: ArrayList amb tot el contingut del Document.
     */
    public ArrayList<String> getContingut() {
	    return contingut;
    }

    /**
     *  Busca si el document conté la paraula p.
     * @param p Paraula a buscar dins del Document.
     * @return boolean: Retorna si aquesta paraula està dins del Document.
     */
    public boolean conteParaula(String p) {
	    return paraules.containsKey(p);
    }

    /**
     * Obté les posicions en les quals apareix una paraula al Document.
     * @param paraula Paraula de la qual retornem les posicions dins del document.
     * @return HashShet: HashSet amb les posicions de la paraula passada per paràmetre tint en compte que la primera paraula ocupa la posició 0
     * */
    public HashSet<Integer> getPosicionsParaula(String paraula) {
        HashSet<Integer> res = new HashSet<Integer>();
	    if (paraules.containsKey(paraula)) res = paraules.get(paraula);
        return res;
    }
}
