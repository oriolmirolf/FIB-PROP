package FONTS.src.main.domain.classes.indexes;

import java.util.*;
import FONTS.src.main.domain.classes.exceptions.*;
import FONTS.src.main.domain.classes.individual_classes.*;


 /**
 * Classe IndexEspaiVectorial, ens permetrà representar els documents amb el model d'espai vectorial.
 * @author Marc Gonzalez
*/

public class IndexEspaiVectorial {
    private Map<String, ArrayList<Vector_EV>> index;
	private Map<String, Set<String>> documents_contenen_paraula;

    /**
     *  Creadora de la classe IndexEspaiVectorial.
     * 	Retorna una instancia de la classe IndexEspaiVectorial.
     */
    public IndexEspaiVectorial() {
		index = new TreeMap<String, ArrayList<Vector_EV>>();
		documents_contenen_paraula = new TreeMap<String, Set<String>>();
    }

	/**
	 * Mètode per recalcular totes les components del Vectors que hi ha a l'Espai Vectorial.
	 */
    private void recalcularVectors() {
		for (Map.Entry<String, ArrayList<Vector_EV>> entry : index.entrySet()) {
	    	for (int i = 0; i < entry.getValue().size(); ++i) entry.getValue().get(i).recalcularTotesCoordenades();
		}
    }

	/**
	 * Mètode per obtenir el Cosinus que formen 2 Vectors
	 * @param v1 Primer vector a partir del qual volem obtenir el Cosinus, si volem fer aquesta operació de forma eficient, v1 ha de ser el vector amb una dimensionalitat més petita.
	 * @param v1 Primer vector a partir del qual volem obtenir el Cosinus
	 * @return Retorna un double entre 0 i 1 indicant el Cosinus que formen els Vectors.
	 */
    private double obtenirCosinus(Vector_EV v1, Vector_EV v2) {
		double res = 0;
		ArrayList<String> aux = v1.interseccioVector(v2);
		double norma1 = v1.norma();
		double norma2 = v2.norma();
		for (int i = 0; i < aux.size(); ++i) {
	    	String w = aux.get(i);
	    	res += (v1.coordenadaParaula(w)/norma1) * (v2.coordenadaParaula(w)/norma2);
		}
		return res;
    }

	/**
	 * Mètode per comparar la semblança entre 2 documents
	 * @param path1 Path del document a fer la comparació
	 * @param path2 Path del segon document a fer la comparació
	 * @param op Sistema de representació que agafem per fer aquesta comparació (0 = TFIDF, 1 = BM25)
	 * @return Un double entre 0 i 1 indicant el Cosinus de l'angle que formen els Documents representats en el model d'espai vectorial.
	 */
    private double compararDocuments(String path1, String path2, int op) {
		Vector_EV v1 = index.get(path1).get(op);
		Vector_EV v2 = index.get(path2).get(op);
		int dv1 = v1.obtenirDimensionalitat();
		int dv2 = v2.obtenirDimensionalitat();
		double res = 0;
		if (dv1 < dv2) res = obtenirCosinus(v1, v2);
		else res = obtenirCosinus(v2, v1);
		return res;
    }

	/**
	 * Mètode per crear els Vectors de totes les representacions que tingui l'espai vectorial
	 * @return ArrayList: Retorna un ArrayList amb les representacions que tingui l'espai vectorial.
	 */
    private ArrayList<Vector_EV> crearDocument() {
		Vector_TFIDF vec_tfidf = new Vector_TFIDF(index.size()+1);
		Vector_BM25 vec_bm25 = new Vector_BM25(index.size()+1);
		ArrayList<Vector_EV> llista = new ArrayList<Vector_EV>();
		llista.add(vec_tfidf);
		llista.add(vec_bm25);
		return llista;
    }
	
	/**
	 * Mètode per comprovar que l'extensió del l'arxiu que se'ns a passat és reconegut.
	 * @param path String que indica el path de l'arxiu
	 * @return Retorna cert si l'extensio que te l'arxiu es reconeguda, en cas contrari retorna fals.
	 */
	private boolean extensioCorrecta(String path) {
		return path.endsWith(".ojmj");
	}

	/**
	 * Mètode que ens permet obtenir un Map ordenat decreixentment pel seu valor i no per la clau.
	 * @param unsortMap Map a partir del cual hem d'ordenar els seus elements de manera decreixent pel valor
	 * @return Map: Retorna un Map ordenat de forma decreixent per valor
	 */
    private static Map<String, Double> sortByValue(Map<String, Double> unsortMap) {

        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

	/**
	 * Mètode per afegir un Document a la representació de l'espai vectorial
	 * @param path Path del Document que es vol afegir
	 * @param paraules Contingut del Document que es vol afegir
	 * @throws DocumentJaExisteix
	 * @throws FormatNoReconegut
	 */
    public void afegirDocument(String path, ArrayList<String> paraules) throws DocumentJaExisteix, FormatNoReconegut {
		if (!extensioCorrecta(path)) throw new FormatNoReconegut();
		if (index.containsKey(path)) throw new DocumentJaExisteix();
		ArrayList<Vector_EV> llista = crearDocument();
		for (int i = 0; i < llista.size(); ++i) llista.get(i).afegirParaules(paraules);
		for (int i = 0; i < paraules.size(); ++i) {
			String paraula = paraules.get(i);
			if (documents_contenen_paraula.containsKey(paraula)) documents_contenen_paraula.get(paraula).add(path);
			else {
				Set<String> s = new HashSet<String>();
				s.add(path);
				documents_contenen_paraula.put(paraula, s);
			}
		}
		index.put(path, llista);
		this.recalcularVectors();
    }

	/**
	 * Mètode per eliminar un Document de la representació de l'espai vectorial
	 * @param path Path del Document que es vol eliminar
	 * @throws NoExisteixDocument
	 * @throws FormatNoReconegut
	 */
    public void eliminarDocument(String path) throws NoExisteixDocument, FormatNoReconegut {
		if (!extensioCorrecta(path)) throw new FormatNoReconegut();
		if (!index.containsKey(path)) throw new NoExisteixDocument();
		ArrayList<Vector_EV> vecs = index.get(path);
		ArrayList<String> paraules = vecs.get(0).paraules();
		for (int i = 0; i < paraules.size(); ++i) {
			Set<String> s = documents_contenen_paraula.get(paraules.get(i));
			s.remove(path);
		}
		for (int i = 0; i < vecs.size(); ++i) vecs.get(i).destructora();
		index.remove(path);
		this.recalcularVectors();
    }

	/**
	 * Mètode per modificar un Document de la representació de l'espai vectorial
	 * @param path Path del Document que es vol modificar
	 * @param paraules Contingut del Document que es vol sobreescriure
	 * @throws NoExisteixDocument
	 * @throws FormatNoReconegut
	 */
    public void modificarDocument(String path, ArrayList<String> paraules) throws NoExisteixDocument, FormatNoReconegut {
		eliminarDocument(path);
		try {
			afegirDocument(path, paraules);
		} catch (DocumentJaExisteix e) {
			System.out.println("Això mai passa.");
		}
	}

	/**
	 * Métode per llistar el K Documents més semblants donat un Document
	 * @param k Número de documents més semblants que es volen obtenir
	 * @param path Path del document que es vol comparar
	 * @param op Sistema de representació que agafem per fer aquesta comparació (0 = TFIDF, 1 = BM25)
	 * @throws NumDocumentsIncorrecte
	 * @throws FormatNoReconegut
	 * @throws NoExisteixDocument
	 */
    public ArrayList<String> llistarKDocumentsSemblants(int k, String path, int op) throws NumDocumentsIncorrecte, NoExisteixDocument, FormatNoReconegut {
		if (!extensioCorrecta(path)) throw new FormatNoReconegut();
		if (k < 0 || k > index.size()) throw new NumDocumentsIncorrecte();
		if (!index.containsKey(path)) throw new NoExisteixDocument();
		Map<String, Double> m = new HashMap<String, Double>();
		for (Map.Entry<String, ArrayList<Vector_EV>> entry : index.entrySet()) {
			String path_d = entry.getKey();
			if (path != path_d) m.put(path_d, compararDocuments(path, path_d, op));
		}
		Map<String, Double> sorted = sortByValue(m);
		ArrayList<String> res = new ArrayList<String>();
		int i = 0;
		for (Map.Entry<String, Double> entry : sorted.entrySet()) {
			if (i == k) break;
			res.add(entry.getKey());
			++i;
		}
		return res;
    }

	/**
	 * Mètode per llistar el K Documents més rellevants donades unes paraules
	 * @param k Número de documents més semblants que es volen obtenir
	 * @param paraules Paraules a partir de les quals em d'obtenir els documents de forma que la relevancia d'aquestes paraules sigui la major possible
	 * @param op Sistema de representació que agafem per fer aquesta comparació (0 = TFIDF, 1 = BM25)
	 * @throws NumDocumentsIncorrecte
	 */
    public ArrayList<String> llistarKDocumentsRellevants(int k, Set<String> paraules, int op) throws NumDocumentsIncorrecte {
		if (k < 0 || k > index.size()) throw new NumDocumentsIncorrecte();
		Map<String, Double> m = new HashMap<String, Double>();
		for (Map.Entry<String, ArrayList<Vector_EV>> entry : index.entrySet()) {
			double suma = 0;
			Vector_EV vec = entry.getValue().get(op);
			Iterator<String> it_paraula = paraules.iterator();
			while (it_paraula.hasNext()) suma += vec.coordenadaParaula(it_paraula.next());
			m.put(entry.getKey(), suma);
		}
		Map<String, Double> sorted = sortByValue(m);
		ArrayList<String> res = new ArrayList<String>();
		int i = 0;
		for (Map.Entry<String, Double> entry : sorted.entrySet()) {
			res.add(entry.getKey());
			++i;
			if (i == k) break;
		}
		return res;
    }

	/**
	 * Mètode per obtenir tots els documents que hi ha representats a l'espai vectorial
	 * @return ArrayList: ArrayList amb tots els paths dels Documents que hi ha representats a l'espai vectorial
	 */
    public ArrayList<String> getAllDocuments() {
		ArrayList<String> llista = new ArrayList<String>();
		for (Map.Entry<String, ArrayList<Vector_EV>> entry : index.entrySet()) llista.add(entry.getKey());
		return llista;
    }

	/**
	 * Mètode per obtenir tots els documents que contenen una certa paraula dels que hi ha representats a l'espai vectorial
	 * @param P Paraula a cercar dins dels Documents
	 * @return ArrayList: Retorna un ArrayList amb el path de tots els Documents que contenien la paraula cercada.
	 */
    public ArrayList<String> getDocumentsContenenParaula(String P) {
		ArrayList<String> llista = new ArrayList<String>();
		if (documents_contenen_paraula.containsKey(P)) {
			Set<String> s = documents_contenen_paraula.get(P);
			Iterator<String> iter_value = s.iterator();
			while(iter_value.hasNext()) llista.add(iter_value.next());
		}
		return llista;
    }
}
