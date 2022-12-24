package EXE.tests;

import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;
import FONTS.src.main.domain.classes.individual_classes.Vector_TFIDF;

public class Vector_TFIDFTest {

    @Test
    public void recalcularTotesCoordenadesicoordenadaParaula1() {
		Vector_TFIDF v = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		v.afegirParaules(p);
		v.recalcularTotesCoordenades();
		assertEquals(1.0, v.coordenadaParaula("hola"), 0.0);
    }

	@Test
	public void recalcularTotesCoordenadesicoordenadaParaula2() {
		Vector_TFIDF v = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v.afegirParaules(p);
		v.recalcularTotesCoordenades();
		assertEquals(0.5, v.coordenadaParaula("hola"), 0.0);
		assertEquals(0.5, v.coordenadaParaula("adeu"), 0.0);
	}

	@Test
	public void recalcularTotesCoordenadesicoordenadaParaula3() {
		Vector_TFIDF v = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v.afegirParaules(p);
		Vector_TFIDF v2 = new Vector_TFIDF(2);
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("hola");
		p2.add("adeu");
		v2.afegirParaules(p2);
		v.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		assertEquals(0.5, v.coordenadaParaula("hola"), 0.0);
		assertEquals(0.5, v.coordenadaParaula("adeu"), 0.0);
	}

	@Test
	public void recalcularTotesCoordenadesicoordenadaParaula4() {
		Vector_TFIDF v = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v.afegirParaules(p);
		Vector_TFIDF v2 = new Vector_TFIDF(2);
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("hola");
		v2.afegirParaules(p2);
		v.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		assertEquals(0.5, v.coordenadaParaula("hola"), 0.0);
		assertEquals(0.5*(1+Math.log(2/1)), v.coordenadaParaula("adeu"), 0.0);
		assertEquals(1.0, v2.coordenadaParaula("hola"), 0.0);
		assertEquals(0.0, v2.coordenadaParaula("adeu"), 0.0);
	}

	@Test
	public void recalcularTotesCoordenadesicoordenadaParaula5() {
		Vector_TFIDF v = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v.afegirParaules(p);
		Vector_TFIDF v2 = new Vector_TFIDF(2);
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("hola");
		v2.afegirParaules(p2);
		v.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		v2.destructora();
		v.recalcularTotesCoordenades();
		assertEquals(0.5, v.coordenadaParaula("hola"), 0.0);
		assertEquals(0.5, v.coordenadaParaula("adeu"), 0.0);
	}

    @Test
    public void norma1() {
		Vector_TFIDF v = new Vector_TFIDF(1);
		assertEquals(0.0, v.norma(), 0.0);
    }

    @Test
    public void norma2() {
		Vector_TFIDF v = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		v.afegirParaules(p);
		v.recalcularTotesCoordenades();
		assertEquals(1.0, v.norma(), 0.0);
    }

	@Test
	public void norma3() {
		Vector_TFIDF v = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v.afegirParaules(p);
		v.recalcularTotesCoordenades();
		double res = Math.sqrt(Math.pow(0.5,2)*2);
		assertEquals(res, v.norma(), 0.0);
	}

	@Test
	public void norma4() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("avui");
		p.add("fa");
		p.add("bon");
		p.add("dia");
		v1.afegirParaules(p);
		Vector_TFIDF v2 = new Vector_TFIDF(2);
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("demà");
		p2.add("farà");
		p2.add("bon");
		p2.add("dia");
		v2.afegirParaules(p2);
		v1.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		double res1 = Math.pow(0.2*(1+Math.log(2/1)),2)*3 + Math.pow(0.2*(1+Math.log(2/2)),2)*2;
		res1 = Math.sqrt(res1);
		double res2 = Math.pow(0.25*(1+Math.log(2/1)),2)*2 + Math.pow(0.25*(1+Math.log(2/2)),2)*2;
		res2 = Math.sqrt(res2);
		assertEquals(res1, v1.norma(), 0.0);
		assertEquals(res2, v2.norma(), 0.0);
	}

	@Test
	public void norma5() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("avui");
		p.add("fa");
		p.add("bon");
		p.add("dia");
		p.add("hola");
		v1.afegirParaules(p);
		Vector_TFIDF v2 = new Vector_TFIDF(2);
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("demà");
		p2.add("farà");
		p2.add("bon");
		p2.add("dia");
		p2.add("bon");
		v2.afegirParaules(p2);
		v1.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		double res1 = Math.pow((1.0/6.0)*(1+Math.log(2/1)),2)*2 + Math.pow((2.0/6.0)*(1+Math.log(2/1)),2) + Math.pow((1.0/6.0)*(1+Math.log(2/2)),2)*2;
		res1 = Math.sqrt(res1);
		double res2 = Math.pow((1.0/5.0)*(1+Math.log(2/1)),2)*2 + Math.pow((2.0/5.0)*(1+Math.log(2/2)),2) + Math.pow((1.0/5.0)*(1+Math.log(2/2)),2);
		res2 = Math.sqrt(res2);
		assertEquals(res1, v1.norma(), 0.0);
		assertEquals(res2, v2.norma(), 0.0);
	}

    @Test
    public void interseccioVector1() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		Vector_TFIDF v2 = new Vector_TFIDF(2);
		ArrayList<String> l = v1.interseccioVector(v2);
		assertEquals(new ArrayList<String>(), l);
    }

    @Test
    public void interseccioVector2() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		Vector_TFIDF v2 = new Vector_TFIDF(2);
		ArrayList<String> paraules_v1 = new ArrayList<String>();
		paraules_v1.add("hola");
		paraules_v1.add("adeu");
		v1.afegirParaules(paraules_v1);
		ArrayList<String> l = v1.interseccioVector(v2);
		assertEquals(new ArrayList<String>(), l);
    }

    @Test
    public void interseccioVector3() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		Vector_TFIDF v2 = new Vector_TFIDF(2);
		ArrayList<String> paraules_v2 = new ArrayList<String>();
		paraules_v2.add("hola");
		paraules_v2.add("adeu");
		v2.afegirParaules(paraules_v2);
		ArrayList<String> l = v1.interseccioVector(v2);
		assertEquals(new ArrayList<String>(), l);
    }

    @Test
    public void interseccioVector4() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		Vector_TFIDF v2 = new Vector_TFIDF(2);
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
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		Vector_TFIDF v2 = new Vector_TFIDF(2);
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
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		Vector_TFIDF v2 = new Vector_TFIDF(2);
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
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		assertEquals(0, v1.obtenirDimensionalitat());
    }

    @Test
    public void obtenirDimensionalitat2() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		v1.afegirParaules(p);
		v1.recalcularTotesCoordenades();
		assertEquals(1, v1.obtenirDimensionalitat());
    }

    @Test
    public void obtenirDimensionalitat3() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("hola");
		v1.afegirParaules(p);
		v1.recalcularTotesCoordenades();
		assertEquals(1, v1.obtenirDimensionalitat());
    }

	@Test
	public void obtenirDimensionalitat4() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v1.afegirParaules(p);
		v1.recalcularTotesCoordenades();
		assertEquals(2, v1.obtenirDimensionalitat());
	}

    @Test
    public void conteParaulaIAfegirParaules1() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		assertEquals(false, v1.conteParaula("hola"));
    }

    @Test
    public void conteParaulaIAfegirParaules2() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		v1.afegirParaules(p);
		assertEquals(true, v1.conteParaula("hola"));
    }

    @Test
    public void conteParaulaIAfegirParaules3() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("hola");
		v1.afegirParaules(p);
		assertEquals(true, v1.conteParaula("hola"));
    }

	@Test
	public void paraules1() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		assertEquals(p, v1.paraules());
	}

	@Test
	public void paraules2() {
		Vector_TFIDF v1 = new Vector_TFIDF(1);
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
		Vector_TFIDF v1 = new Vector_TFIDF(1);
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
		Vector_TFIDF v1 = new Vector_TFIDF(1);
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
		Vector_TFIDF v1 = new Vector_TFIDF(1);
		ArrayList<String> p = new ArrayList<String>();
		p.add("hola");
		p.add("adeu");
		v1.afegirParaules(p);
		Vector_TFIDF v2 = new Vector_TFIDF(2);
		ArrayList<String> p2 = new ArrayList<String>();
		p2.add("hola");
		p2.add("arbre");
		v2.afegirParaules(p2);
		v1.recalcularTotesCoordenades();
		v2.recalcularTotesCoordenades();
		assertEquals(0.5*(1+Math.log(2/2)), v1.coordenadaParaula("hola"), 0.0);
		v2.destructora();
		v1.recalcularTotesCoordenades();
		assertEquals(0.5, v1.coordenadaParaula("hola"), 0.0);
    }
}
