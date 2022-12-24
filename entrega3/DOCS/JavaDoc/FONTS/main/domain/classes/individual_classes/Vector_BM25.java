package FONTS.src.main.domain.classes.individual_classes;

import java.util.*;


 /**
 * Classe Vector_BM25, ens permetrà representar un Document amb la forma de Vector amb una estrategia de pesos BM25.
 * @author Marc Gonzalez
*/

public class Vector_BM25 extends Vector_EV {
    
    private Map<String,Integer> aparicions_paraula;
	private int paraules_totals_document;
    private static int n_documents;
    private static Map<String,Integer> n_documents_contenen_paraula;
    private static int paraules_totals_n_documents;
    private static double k = 1.25, b = 0.75;
	private static final long serialVersionUID = 9L;

    /**
     *  Creadora de la classe Vector_BM25
     * @param num Indica el número de Documents representats com a Vectors que hi ha a l'Espai Vectorial
     * Retorna una instància de la Classe amb els atributs passats per parametre i modifica els atributs static de forma que es mantingui l'informació de l'espai vectorial actualitzada
     */
    public Vector_BM25(int num) {
		super();	
		aparicions_paraula = new HashMap<String, Integer>();
		paraules_totals_document = 0;
		n_documents = num;
		if (num == 1) {
			paraules_totals_n_documents = 0;
			n_documents_contenen_paraula = new HashMap<String, Integer>();
		}
    }

	/**
	 * Métode per calcular les coordenades d'una paraula depenent del sistema que utilitzem.
	 * @param paraula Indica la paraula a la qual li calcularem la coordenada.
	 * @return Un double indicant la coordenada per a la paraula.
	 */
    protected double calcular_coordenades(String paraula) {
		Double coord = 0.0;
		if (aparicions_paraula.containsKey(paraula)) coord = (((double)aparicions_paraula.get(paraula)*(k+1.0))
		/((double)aparicions_paraula.get(paraula))+k*(1.0-b+b*((double)paraules_totals_document/((double)paraules_totals_n_documents/(double)n_documents))))*(Math.log(1+(((double)n_documents-(double)n_documents_contenen_paraula.get(paraula)+0.5)/(((double)n_documents_contenen_paraula.get(paraula)+0.5)))));
		return coord;
    }

	/**
	 * Métode per afegir una paraula al Vector.
	 * @param paraula Indica la paraula a la qual volem afegir l'aparicio al vector.
	 */
    protected void afegirParaula(String paraula) {
		if (!stopWords.contains(paraula)) {
			++paraules_totals_document;
			++paraules_totals_n_documents;
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
	 * Métode per simular una destructora y poder modificar els diferents vectors que queden afectats després de la destrucció de un Vector.
	 */
    public void destructora() {
		--n_documents;
		paraules_totals_n_documents -= paraules_totals_document;
		for (Map.Entry<String, Integer> entry : aparicions_paraula.entrySet()) {
			String w = entry.getKey();
			int n = n_documents_contenen_paraula.get(w);
			n_documents_contenen_paraula.replace(w, n-1);
		}
    }

}
