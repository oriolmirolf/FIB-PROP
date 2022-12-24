package EXE.tests;

import org.junit.Test;
import FONTS.src.main.domain.classes.individual_classes.Vector_BM25;

import static org.junit.Assert.*;
import java.util.*;

public class Vector_BM25Test {
	private double k = 1.25, b = 0.75;

	private double calcCoord(int aparicions_paraula, int paraules_totals_document, int paraules_totals_n_documents, int n_documents, int n_documents_contenen_paraula) {
		return (((double)aparicions_paraula*(k+1.0))/((double)paraules_totals_document)+k*(1.0-b+b*((double)paraules_totals_document/((double)paraules_totals_n_documents/(double)n_documents))))*(Math.log(1+(((double)n_documents-(double)n_documents_contenen_paraula+0.5)/(((double)n_documents_contenen_paraula+0.5)))));
	}

	@Test
	public void recalcularTotesCoordenadesicoordenadaParaula1() {
		Vector_BM25 v = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		v.afegirParaules(p);
		v.recalcularTotesCoordenades();
		assertEquals(calcCoord(1,1,1,1,1), v.coordenadaParaula("hola"), 0.0);
	}

	@Test
	public void recalcularTotesCoordenadesicoordenadaParaula2() {
		Vector_BM25 v = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v.afegirParaules(p);
		v.recalcularTotesCoordenades();
		assertEquals(calcCoord(1,2,2,1,1), v.coordenadaParaula("hola"), 0.0);
		assertEquals(calcCoord(1,2,2,1,1), v.coordenadaParaula("adeu"), 0.0);
	}

	@Test
	public void recalcularTotesCoordenadesicoordenadaParaula3() {
		Vector_BM25 v = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v.afegirParaules(p);
		Vector_BM25 v2 = new Vector_BM25(2);
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("hola");
		p2.add("adeu");
		v2.afegirParaules(p2);
		v.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		assertEquals(calcCoord(1,2,4,2,2), v.coordenadaParaula("hola"), 0.0);
		assertEquals(calcCoord(1,2,4,2,2), v.coordenadaParaula("adeu"), 0.0);
	}

	@Test
	public void recalcularTotesCoordenadesicoordenadaParaula4() {
		Vector_BM25 v = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v.afegirParaules(p);
		Vector_BM25 v2 = new Vector_BM25(2);
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("hola");
		v2.afegirParaules(p2);
		v.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		assertEquals(calcCoord(1,2,3,2,2), v.coordenadaParaula("hola"), 0.0);
		assertEquals(calcCoord(1,2,3,2,1), v.coordenadaParaula("adeu"), 0.0);
		assertEquals(calcCoord(1,1,3,2,2), v2.coordenadaParaula("hola"), 0.0);
		assertEquals(0.0, v2.coordenadaParaula("adeu"), 0.0);
	}

	@Test
	public void recalcularTotesCoordenadesicoordenadaParaula5() {
		Vector_BM25 v = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v.afegirParaules(p);
		Vector_BM25 v2 = new Vector_BM25(2);
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("hola");
		v2.afegirParaules(p2);
		v.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		v2.destructora();
		v.recalcularTotesCoordenades();
		assertEquals(calcCoord(1,2,2,1,1), v.coordenadaParaula("hola"), 0.0);
		assertEquals(calcCoord(1,2,2,1,1), v.coordenadaParaula("adeu"), 0.0);
	}


	@Test
	public void norma1() {
		Vector_BM25 v = new Vector_BM25(1);
		assertEquals(0.0, v.norma(), 0.0);
	}

	@Test
	public void norma2() {
		Vector_BM25 v = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		v.afegirParaules(p);
		v.recalcularTotesCoordenades();
		assertEquals(calcCoord(1,1,1,1,1), v.norma(), 0.0);
	}

	@Test
	public void norma3() {
		Vector_BM25 v = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v.afegirParaules(p);
		v.recalcularTotesCoordenades();
		double coord = calcCoord(1,2,2,1,1);
		double res = Math.sqrt(Math.pow(coord, 2)*2);
		assertEquals(res, v.norma(), 0.0);
	}

	@Test
	public void norma4() {
		Vector_BM25 v1 = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("avui");
		p.add("fa");
		p.add("bon");
		p.add("dia");
		v1.afegirParaules(p);
		Vector_BM25 v2 = new Vector_BM25(2);
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("demà");
		p2.add("farà");
		p2.add("bon");
		p2.add("dia");
		v2.afegirParaules(p2);
		v1.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		double coord1 = calcCoord(1,5,9,2,1);
		double coord2 = calcCoord(1,5,9,2,2);
		double coord3 = calcCoord(1,4,9,2,1);
		double coord4 = calcCoord(1,4,9,2,2);
		double res1 = Math.pow(coord1,2)*3 + Math.pow(coord2,2)*2;
		res1 = Math.sqrt(res1);
		double res2 = Math.pow(coord3,2)*2 + Math.pow(coord4,2)*2;
		res2 = Math.sqrt(res2);
		assertEquals(res1, v1.norma(), 0.0);
		assertEquals(res2, v2.norma(), 0.0);
	}

	@Test
	public void norma5() {
		Vector_BM25 v1 = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("avui");
		p.add("fa");
		p.add("bon");
		p.add("dia");
		p.add("hola");
		v1.afegirParaules(p);
		Vector_BM25 v2 = new Vector_BM25(2);
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("demà");
		p2.add("farà");
		p2.add("bon");
		p2.add("dia");
		p2.add("bon");
		v2.afegirParaules(p2);
		v1.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		double coord1 = calcCoord(1,6,11,2,1);
		double coord2 = calcCoord(1,6,11,2,2);
		double coord3 = calcCoord(2,6,11,2,1);
		double coord4 = calcCoord(1,5,11,2,1);
		double coord5 = calcCoord(1,5,11,2,2);
		double coord6 = calcCoord(2,5,11,2,2);
		double res1 = Math.pow(coord1,2)*2 + Math.pow(coord2,2)*2 + Math.pow(coord3, 2);
		res1 = Math.sqrt(res1);
		double res2 = Math.pow(coord4,2)*2 + Math.pow(coord5,2) + Math.pow(coord6, 2);
		res2 = Math.sqrt(res2);
		assertEquals(res1, v1.norma(), 0.0);
		assertEquals(res2, v2.norma(), 0.000001);
	}

    @Test
    public void interseccioVector1() {
		Vector_BM25 v1 = new Vector_BM25(1);
		Vector_BM25 v2 = new Vector_BM25(2);
		ArrayList<String> l = v1.interseccioVector(v2);
		assertEquals(new ArrayList<String>(), l);
    }

    @Test
    public void interseccioVector2() {
		Vector_BM25 v1 = new Vector_BM25(1);
		Vector_BM25 v2 = new Vector_BM25(2);
		ArrayList<String> paraules_v1 = new ArrayList<String>();
		paraules_v1.add("hola");
		paraules_v1.add("adeu");
		v1.afegirParaules(paraules_v1);
		ArrayList<String> l = v1.interseccioVector(v2);
		assertEquals(new ArrayList<String>(), l);
    }

    @Test
    public void interseccioVector3() {
		Vector_BM25 v1 = new Vector_BM25(1);
		Vector_BM25 v2 = new Vector_BM25(2);
		ArrayList<String> paraules_v2 = new ArrayList<String>();
		paraules_v2.add("hola");
		paraules_v2.add("adeu");
		v2.afegirParaules(paraules_v2);
		ArrayList<String> l = v1.interseccioVector(v2);
		assertEquals(new ArrayList<String>(), l);
    }

    @Test
    public void interseccioVector4() {
		Vector_BM25 v1 = new Vector_BM25(1);
		Vector_BM25 v2 = new Vector_BM25(2);
		ArrayList<String> paraules_v1 = new ArrayList<String>();
		ArrayList<String> paraules_v2 = new ArrayList<String>();
		paraules_v1.add("hola");
		paraules_v2.add("hola");
		paraules_v2.add("adeu");
		v1.afegirParaules(paraules_v1);
		v2.afegirParaules(paraules_v2);
		v1.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		ArrayList<String> l = v1.interseccioVector(v2);
		ArrayList<String> result = new ArrayList<String>();
		result.add("hola");
		assertEquals(result, l);
    }

    @Test
    public void interseccioVector5() {
		Vector_BM25 v1 = new Vector_BM25(1);
		Vector_BM25 v2 = new Vector_BM25(2);
		ArrayList<String> paraules_v1 = new ArrayList<String>();
		ArrayList<String> paraules_v2 = new ArrayList<String>();
		paraules_v1.add("hola");
		paraules_v1.add("adeu");
		paraules_v2.add("hola");
		paraules_v2.add("adeu");
		v1.afegirParaules(paraules_v1);
		v2.afegirParaules(paraules_v2);
		v1.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		ArrayList<String> l = v1.interseccioVector(v2);
		ArrayList<String> result = new ArrayList<String>();
		result.add("adeu");
		result.add("hola");
		assertEquals(result, l);
    }

    @Test
    public void interseccioVector6() {
		Vector_BM25 v1 = new Vector_BM25(1);
		Vector_BM25 v2 = new Vector_BM25(2);
		ArrayList<String> paraules_v1 = new ArrayList<String>();
		ArrayList<String> paraules_v2 = new ArrayList<String>();
		paraules_v1.add("hola");
		paraules_v1.add("adeu");
		paraules_v1.add("casa");
		paraules_v2.add("hola");
		paraules_v2.add("adeu");
		v1.afegirParaules(paraules_v1);
		v2.afegirParaules(paraules_v2);
		v1.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		ArrayList<String> l = v1.interseccioVector(v2);
		ArrayList<String> result = new ArrayList<String>();
		result.add("adeu");
		result.add("hola");
		assertEquals(result, l);
    }

    @Test
    public void obtenirDimensionalitat1() {
		Vector_BM25 v1 = new Vector_BM25(1);
		assertEquals(0, v1.obtenirDimensionalitat());
    }

    @Test
    public void obtenirDimensionalitat2() {
		Vector_BM25 v1 = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		v1.afegirParaules(p);
		v1.recalcularTotesCoordenades();
		assertEquals(1, v1.obtenirDimensionalitat());
    }

    @Test
    public void obtenirDimensionalitat3() {
		Vector_BM25 v1 = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("hola");
		v1.afegirParaules(p);
		v1.recalcularTotesCoordenades();
		assertEquals(1, v1.obtenirDimensionalitat());
    }


	@Test
	public void obtenirDimensionalitat4() {
		Vector_BM25 v1 = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v1.afegirParaules(p);
		v1.recalcularTotesCoordenades();
		assertEquals(2, v1.obtenirDimensionalitat());
	}

    @Test
    public void conteParaulaIAfegirParaules1() {
		Vector_BM25 v1 = new Vector_BM25(1);
		assertEquals(false, v1.conteParaula("hola"));
    }

    @Test
    public void conteParaulaIAfegirParaules2() {
		Vector_BM25 v1 = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		v1.afegirParaules(p);
		assertEquals(true, v1.conteParaula("hola"));
    }

    @Test
    public void conteParaulaIAfegirParaules3() {
		Vector_BM25 v1 = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("hola");
		v1.afegirParaules(p);
		assertEquals(true, v1.conteParaula("hola"));
    }

	@Test
	public void paraules1() {
		Vector_BM25 v1 = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		assertEquals(p, v1.paraules());
	}

	@Test
	public void paraules2() {
		Vector_BM25 v1 = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		v1.afegirParaules(p);
		v1.recalcularTotesCoordenades();
		ArrayList<String> res = new ArrayList<String>();
		res.add("hola");
		assertEquals(res, v1.paraules());
	}

	@Test
	public void paraules3() {
		Vector_BM25 v1 = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("hola");
		v1.afegirParaules(p);
		v1.recalcularTotesCoordenades();
		ArrayList<String> res = new ArrayList<String>();
		res.add("hola");
		assertEquals(res, v1.paraules());
	}

	@Test
	public void paraules4() {
		Vector_BM25 v1 = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v1.afegirParaules(p);
		v1.recalcularTotesCoordenades();
		ArrayList<String> res = new ArrayList<String>();
		res.add("adeu");
		res.add("hola");
		assertEquals(res, v1.paraules());
	}

    @Test
    public void destructora() {
		Vector_BM25 v1 = new Vector_BM25(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v1.afegirParaules(p);
		Vector_BM25 v2 = new Vector_BM25(2);
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("hola");
		p2.add("arbre");
		v2.afegirParaules(p2);
		v1.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		v2.destructora();
		v1.recalcularTotesCoordenades();
		assertEquals(calcCoord(1,2,2,1,1), v1.coordenadaParaula("hola"), 0.0);
    }
}
