package FONTS.src.main.domain.classes.individual_classes;

import java.util.*;


 /**
 * Classe Vector_TFIDF, ens permetrà representar un Document amb la forma de Vector amb una estrategia de pesos TFIDF.
 * @author Marc Gonzalez
*/

public class Vector_TFIDF extends Vector_EV {
    
    private Map<String,Integer> aparicions_paraula;
	private int paraules_totals_document;
    private static int n_documents;
    private static Map<String,Integer> n_documents_contenen_paraula;
	private static final long serialVersionUID = 8L;
	/**
     *  Creadora de la classe Vector_TFIDF.
	 *  Retorna una instància de la Classe amb els atributs passats per parametre i modifica els atributs static de forma que es mantingui la informació de l'espai vectorial actualitzada.
     * @param num Indica el número de Documents representats com a Vectors que hi ha a l'Espai Vectorial
     */
    public Vector_TFIDF(int num) {
		super();	
		aparicions_paraula = new HashMap<String, Integer>();
		n_documents = num;
		paraules_totals_document = 0;
		if (num == 1) n_documents_contenen_paraula = new HashMap<String, Integer>();
    }

	/**
	 * Métode per calcular les coordenades d'una paraula depenent del sistema que utilitzem.
	 * @param paraula Indica la paraula a la qual li calcularem la coordenada.
	 * @return Un double indicant la coordenada per a la paraula.
	 */
    protected double calcular_coordenades(String paraula) {
		Double coord = 0.0;
		if (aparicions_paraula.containsKey(paraula)) coord = ((double)aparicions_paraula.get(paraula)/(double)paraules_totals_document)*(1.0+(Math.log((double)n_documents/((double)n_documents_contenen_paraula.get(paraula)))));
		return coord;
    }

	/**
	 * Métode per afegir una paraula al Vector.
	 * @param paraula Indica la paraula a la qual volem afegir l'aparicio al vector.
	 */
    protected void afegirParaula(String paraula) {
		if (!stopWords.contains(paraula)) {
			++paraules_totals_document;
			if (aparicions_paraula.containsKey(paraula)) {
				Integer num_aparicions_paraula = aparicions_paraula.get(paraula);
				aparicions_paraula.replace(paraula, num_aparicions_paraula+1);
			}
			else {
				aparicions_paraula.put(paraula, 1);
				if (n_documents_contenen_paraula.containsKey(paraula)) {
					Integer fd_paraula = n_documents_contenen_paraula.get(paraula);
					n_documents_contenen_paraula.replace(paraula, fd_paraula+1);
				}
				else n_documents_contenen_paraula.put(paraula, 1);
			}
		}
    }

	 /**
	  * Mètode per saber si el Vector conté una paraula
	  * @param paraula Paraula la qual volem saber si es troba dins del Vector
	  * @return Retorna un bool indicant si la paraula es o no al Vector
	  */
	 public boolean conteParaula(String paraula) {
		 return aparicions_paraula.containsKey(paraula);
	 }

	/**
	 * Métode per actualitzar les coordenades d'un Vector i la seva norma.
	 */
    public void recalcularTotesCoordenades() {
		for (Map.Entry<String, Integer> entry : aparicions_paraula.entrySet()) {
			String paraula = entry.getKey();
			super.modificarCoordenada(paraula, calcular_coordenades(paraula));
		}
		super.recalcularTotesCoordenades();
    }

	/**
	 * Métode per simular una destructora y poder modificar els diferents vectors que queden afectats després de la destrucció d'un Vector.
	 */
    public void destructora() {
		--n_documents;
		for (Map.Entry<String, Integer> entry : aparicions_paraula.entrySet()) {
			String w = entry.getKey();
			int n = n_documents_contenen_paraula.get(w);
			n_documents_contenen_paraula.replace(w, n-1);
		}
    }
}
