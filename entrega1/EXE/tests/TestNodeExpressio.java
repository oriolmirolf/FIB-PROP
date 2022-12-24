package EXE.tests;

import org.junit.Test;

import FONTS.src.main.domain.classes.individual_classes.NodeExpressio;
import static org.junit.Assert.*;


public class TestNodeExpressio {

    /*
       =================
        TESTS CREADORES
       =================
    */

    /**
     * Objecte de la prova: Test del mètode NodeExpressio() de la classe NodeExpressio
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte NodeExpressio.
     * Operativa: En aquest test es comprova que es crea correctament un objecte NodeExpressio fill dret = null, fill esquerre = null, valor = null i op = '0'
     * Primer es crea un objecte NodeExpressio, es crida als mètodes getFillDret(), getFillEsquerre(), getValor() i getOp() i es comprova que el resultat és
     * el correcte.
     */
    @Test
    public void creadoraBuida() {
        NodeExpressio node = new NodeExpressio();

        assertEquals(null, node.getFillDret());
        assertEquals(null, node.getFillEsquerre());
        assertEquals(null, node.getValor());
        assertEquals('0', node.getOp());
    }

    /**
     * Objecte de la prova: Test del mètode NodeExpressio(valor) de la classe NodeExpressio
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte NodeExpressio.
     * Operativa: En aquest test es comprova que es crea correctament un objecte NodeExpressio fill dret = null, fill esquerre = null, valor = v i op = '0'
     * Primer es crea un objecte NodeExpressio, es crida als mètodes getFillDret(), getFillEsquerre(), getValor() i getOp() i es comprova que el resultat és
     * el correcte.
     */
    @Test
    public void creadora() {
        String v = "hola";
        NodeExpressio node = new NodeExpressio(v);

        assertEquals(null, node.getFillDret());
        assertEquals(null, node.getFillEsquerre());
        assertEquals(v, node.getValor());
        assertEquals('0', node.getOp());
    }



    /*
       =========================
        TESTS SETTERS & GETTERS
       =========================
    */

    // Comentari: S'han decidit testejar setters i getters conjuntament ja que ambdos son trivials, i el testeig de un es análog i equivalent que el testeig del altre

    /**
     * Objecte de la prova: Test del mètodes setFillDret(NodeExpressio d) i getFillDret() de la classe NodeExpressio
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S'afegeix un fill a un NodeExpressio.
     * Operativa: En aquest test es comprova que s'asigna a NodeExpressio un fill dret = d
     * Primer es crean dos objectes NodeExpressio, es crida als mètodes setFillDret(NodeExpressio d) i després es crida al mètode getFillDret() per comprovar que el resultat es el correcte.     * el correcte.
     */
    @Test
    public void SetterGetterFillDret() {
        NodeExpressio node = new NodeExpressio();
        NodeExpressio d = new NodeExpressio();
        node.setFillDret(d);
        assertEquals(d, node.getFillDret());
    }

    /**
     * Objecte de la prova: Test del mètodes setFillEsquerre(NodeExpressio e) i getFillEsquerre() de la classe NodeExpressio
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S'afegeix un fill a un NodeExpressio.
     * Operativa: En aquest test es comprova que s'asigna a NodeExpressio un fill esquerre = e
     * Primer es crean dos objectes NodeExpressio, es crida als mètodes setFillEsquerre(NodeExpressio e) i després es crida al mètode getFillEsquerre() per comprovar que el resultat es el correcte.     * el correcte.
     */
    @Test
    public void SetterFillEsquerre() {
        NodeExpressio node = new NodeExpressio();
        NodeExpressio e = new NodeExpressio();
        node.setFillEsquerre(e);
        assertEquals(e, node.getFillEsquerre());
    }

    /**
     * Objecte de la prova: Test del mètodes setValor(String s) i getValor() de la classe NodeExpressio
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S'afegeix un valor a un NodeExpressio.
     * Operativa: En aquest test es comprova que s'asigna a NodeExpressio un valor = s
     * Primer es crea un objecte NodeExpressio, es crida als mètodes setValor(String s) i després es crida al mètode getValor() per comprovar que el resultat es el correcte.     * el correcte.
     */
    @Test
    public void SetterValor() {
        String s = "valoresperat";
        NodeExpressio node = new NodeExpressio();
        node.setValor(s);
        assertEquals(s, node.getValor());
    }

    /**
     * Objecte de la prova: Test del mètodes setOp(char c) i getOp() de la classe NodeExpressio
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S'asigna una operació a un NodeExpressio.
     * Operativa: En aquest test es comprova que s'asigna a NodeExpressio un op = c
     * Primer es crea un objecte NodeExpressio, es crida als mètodes setOp(char c) i després es crida al mètode getOp() per comprovar que el resultat es el correcte.     * el correcte.
     */
    @Test
    public void SetterOperacio() {
        char c = '&';
        NodeExpressio node = new NodeExpressio();
        node.setOp(c);
        assertEquals(c, node.getOp());
    }
}
