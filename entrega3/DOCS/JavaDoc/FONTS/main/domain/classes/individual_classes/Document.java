package FONTS.src.main.domain.classes.individual_classes;

import java.util.*;
import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    private LocalDateTime dataCreacio;
    /** Data de Darrera Modificació del Document */
    private LocalDateTime dataDarreraModificacio;
    /** Contingut del Document en una estructura de dades enfocada a la consulta */
    private Map<String, HashSet<Integer>> paraules;
    /** Contingut del Document */
    private ArrayList<String> contingut;
    /** Contingut en cru del document */
    private String contingut_raw;

    private Document (String titol, String autor, String localitzacio, LocalDateTime dataCreacio, LocalDateTime dataDarreraModificacio, Map<String, HashSet<Integer>> paraules, ArrayList<String> contingut, String contingutRaw){
        this.titol = new String(titol);
        this.autor = new String(autor);
        this.localitzacio = new String(localitzacio);
        this.dataCreacio = dataCreacio;
        this.dataDarreraModificacio = dataDarreraModificacio;
        this.paraules = new TreeMap<>();
        for(Map.Entry<java.lang.String, HashSet<Integer>> b : paraules.entrySet()){
            String a = new String(b.getKey());
            HashSet<Integer> c = new HashSet<>(b.getValue());
            this.paraules.put(a, c);
        }
        this.contingut = new ArrayList<>(contingut);
        this.contingut_raw = contingutRaw;
    }
    public Document(String titol, String autor, String localitzacio, LocalDateTime dataCreacio, LocalDateTime dataDarreraModificacio, String contingut){
        this.titol = titol;
        this.autor = autor;
        this.localitzacio = localitzacio;
        this.dataCreacio = dataCreacio;
        paraules = new TreeMap<String, HashSet<Integer>>();
        this.contingut = new ArrayList<String>();
        if(contingut != null) modificarContingut(contingut);
        else this.contingut_raw = null;
        this.dataDarreraModificacio = dataDarreraModificacio;   
    }
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
            if (!cosa.equals(" ") && ! cosa.equals("\n"))allM.add(cosa);
        }
        return allM;
    }

    /**
     * Guarda les posicions de les aparicions de cada paraula a un atribut
     * @param cont Contingut a indexar.
     */
    private void guardarPosicionsParules(ArrayList<String> cont) {
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
    }

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
        dataCreacio = LocalDateTime.now();
        dataDarreraModificacio = LocalDateTime.now();
        paraules = new TreeMap<String, HashSet<Integer>>();
        contingut = new ArrayList<String>();
        contingut_raw = null;
    }

    /**
     *	Canvia el títol del Document.
     *	@param nouTitol Indica el titol que tindrà el Document després de la modificació.
     */
    public void canviarTitolDoc(String nouTitol) {
	    titol = nouTitol;
	    dataDarreraModificacio = LocalDateTime.now();
    }

    /**
     *	Canvia l'autor del Document.
     *	@param nouAutor Indica el nom de l'autor que tindrà el Document després de la modificació.
     */
    public void canviarAutorDoc(String nouAutor) {
	    autor = nouAutor;
	    dataDarreraModificacio = LocalDateTime.now();
    }

    /**
     *	Modifica el contingut del Document.
     *	@param contin Conté totes les noves paraules que pasaràn a formar part del Document.
     */
    public void modificarContingut(String contin) {
        contingut_raw = contin;
        ArrayList<String> cont = allmatches(contin);
        paraules.clear();
        contingut = cont;
        guardarPosicionsParules(cont);
        dataDarreraModificacio = LocalDateTime.now();
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
    public LocalDateTime getDataCreacio() {
	    return dataCreacio;
    }

    /**
     * Getter de la data de la darrera modificació.
     * @return LocalDate: Data de la darrera modificació del Document.
     */
    public LocalDateTime getDataDarreraModificacio() {
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
     * Getter del contingut raw.
     * @return ArrayList: String amb tot el contingut raw del Document.
     */
    public String getContingutRaw() {
        return contingut_raw;
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
    public Document clonar(){
        return new Document(titol, autor, localitzacio, dataCreacio, dataDarreraModificacio, paraules, contingut, contingut_raw);
    }
}
