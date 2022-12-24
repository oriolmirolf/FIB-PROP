package FONTS.src.main.domain.classes.individual_classes;

import java.io.Serializable;
import java.util.*;


 /**
 * Classe Vector_EV, aquesta classe és abstracta i ens permetrà representar un document dins de l'espai vectorial on els fills hauran d'implementar l'estratègia de pesos que es vulgui utilitzar.
 * @author Marc Gonzalez
*/

public abstract class Vector_EV implements Serializable{
    private Map<String, Double> coordenades;
    private double norma;
	protected static Set<String> stopWords = new HashSet<String>(Arrays.asList("últim", "última", "últimes", "últims", "a", "abans", "això", "al", "algun", "alguna", "algunes", "alguns", "allà", "allí", "allò", "als", "altra", "altre", "altres", "amb", "aprop", "aquí", "aquell", "aquella", "aquelles", "aquells", "aquest", "aquesta", "aquestes", "aquests", "cada", "catorze", "cent", "cert", "certa", "certes", "certs", "cinc", "com", "cosa", "d'", "darrer", "darrera", "darreres", "darrers", "davant", "de", "del", "dels", "després", "deu", "dinou", "disset", "divuit", "dos", "dotze", "durant", "el", "ell", "ella", "elles", "ells", "els", "en", "encara", "et", "extra", "fins", "hi", "i", "jo", "l'",  "la", "les", "li", "llur", "lo", "los", "més", "m'", "ma", "massa", "mateix", "mateixa", "mateixes", "mateixos", "mes", "meu", "meva", "mig", "molt", "molta", "moltes", "molts", "mon", "mons", "n'", "na", "ni", "no", "nosaltres", "nostra", "nostre", "nou", "ns", "o", "on", "onze", "pel", "per", "però","perquè", "perque", "poc", "poca", "pocs", "poques", "primer", "primera", "primers", "primeres", "prop", "què", "qual", "quals", "qualsevol", "qualssevol", "quan", "quant", "quanta", "quantes", "quants", "quatre", "que", "qui", "quin", "quina", "quines", "quins", "quinze", "res", "s'", "sa", "segon", "segona", "segones", "segons", "sense", "ses", "set", "setze", "seu", "seus", "seva", "seves", "sino", "sis", "sobre", "son", "sons", "sota", "t'", "ta", "tal", "tals", "tan", "tant", "tanta", "tantes", "tants", "tes", "teu", "teus", "teva", "teves", "ton", "tons", "tot", "tota", "totes", "tots", "tres", "tretze", "tu", "un", "una", "unes", "uns", "vint", "vos", "vosaltres", "vosté", "vostés", "vostra", "vostre", "vuit"));
	private static final long serialVersionUID = 7L;
	/**
	 * Constructora de la classe Vector_EV
	 */
    public Vector_EV() {
		norma = 0.0;
		coordenades = new TreeMap<String, Double>();
    }

	/**
	 * Mètode per calcular o actualitzar l'atribut norma del Vector
	 */
    private void calcularNorma() {
		double result = 0.0;
		for (Map.Entry<String, Double> entry : coordenades.entrySet()) result += Math.pow(entry.getValue(), 2);
		norma = Math.sqrt(result);
    }

	/**
	 * Mètode abstracte per calcular les coordenades d'una paraula depenent del sistema que utilitzem.
	 * @param paraula Indica la paraula a la qual li calcularem la coordenada.
	 * @return Un double indicant la coordenada per a la paraula.
	 */
    protected abstract double calcular_coordenades(String paraula);

	/**
	 * Mètode abstracte per afegir una paraula al Vector.
	 * @param paraula Indica la paraula a la qual volem afegir l'aparició al vector.
	 */
    protected abstract void afegirParaula(String paraula);

	/**
	 * Mètode per a modificar una coordenada d'una paraula
	 * @param paraula Indica la paraula a la qual li hem de modificar la coordenada
	 * @param coord Indica la nova coordenada que tindrà la paraula
	 */
    protected void modificarCoordenada(String paraula, double coord) {
	    if (coordenades.containsKey(paraula)) coordenades.replace(paraula, coord);
	    else coordenades.put(paraula, coord);
    }

	 /**
	  * Mètode per actualitzar les coordenades d'un Vector i la seva norma.
	  */
	 public void recalcularTotesCoordenades() {
		 calcularNorma();
	 }

	/**
	 * Getter de la norma del Vector
	 * @return Un double amb la norma del Vector
	 */
    public double norma() {
		recalcularTotesCoordenades();
		return this.norma;
    }

	
	/**

	 * Mètode abstracte per simular una destructora i poder modificar els diferents vectors que queden afectats després de la destrucció d'un Vector.

	 */

    public abstract void destructora();


    // Retorna una llista de les paraules que estan als 2 vectors

	/**
	 * Mètode per calcular la intersecció de 2 Vectors
	 * @param v Vector amb el qual es farà la intersecció
	 * @return Retorna un ArrayList amb les paraules que estan als 2 Vectors.
	 */
    public ArrayList<String> interseccioVector(Vector_EV v) {
		ArrayList<String> res = new ArrayList<String>();
		for (Map.Entry<String, Double> entry : coordenades.entrySet()) if (v.coordenades.containsKey(entry.getKey())) res.add(entry.getKey());
		return res;
    }

	/**
	 * Getter de la dimensionalitat d'un Vector (número de paraules que conté)
	 * @return Un enter amb el número de paraules que conté.
	 */
    public int obtenirDimensionalitat() {
		return coordenades.size();
    }

	/**
	 * Mètode per saber si el Vector conté una paraula
	 * @param paraula Paraula la qual volem saber si es troba dins del Vector
	 * @return Retorna un bool indicant si la paraula es o no al Vector
	 */
    public boolean conteParaula(String paraula) {
		return coordenades.containsKey(paraula);
    }

	 /**
	  * Mètode per obtenir totes les paraules que té un vector
	  * @return Retorna un ArrayList amb totes les paraules que conté el Vector
	  */
	public ArrayList<String> paraules() {
		ArrayList<String> l = new ArrayList<String>();
		for (Map.Entry<String, Double> entry : coordenades.entrySet()) l.add(entry.getKey());
		return l;
	}

	/**
	 * Mètode per obtenir les coordenades d'una paraula.
	 * @param paraula Paraula la qual volem saber les seves coordenades.
	 * @return Retorna un double amb les coordenades que té aquella paraula al Vector.
	 */
    public double coordenadaParaula(String paraula) {
		double d;
		if (coordenades.containsKey(paraula)) d = coordenades.get(paraula);
		else d = 0.0;
		return d;
    }

	/**
	 * Mètode per afegir una llista de paraules al l'ArrayList.
	 * @param l ArrayList que conté les paraules a afegir al vector.
	 */
    public void afegirParaules(ArrayList<String> l) {
        if (l == null) return;
        for (int i = 0; i < l.size(); ++i) this.afegirParaula(l.get(i));
        this.calcularNorma();
    }
}

