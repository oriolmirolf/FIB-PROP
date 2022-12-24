package EXE.tests;

//import FONTS.src.main.domain.controllers.CtrlIndex;
import org.junit.Test;

import java.util.ArrayList;
import FONTS.src.main.domain.classes.exceptions.*;
import EXE.classes_tests.ExpressioBooleanaTest;
import static org.junit.Assert.*;
import FONTS.src.stubs.CtrlPersistencia;

public class TestExpressioBooleana {

    /*
       =================
        TESTS CREADORES
       =================
    */

    /**
     * Objecte de la prova: Test del mètode ExpressioBooleana(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un objecte ExpressioBooleana.
     * Operativa: En aquest test es comprova que es crea correctament un objecte ExpressioBooleana amb stringExpression = exp i root = arbre resultant de l'expressio
     * Primer es crea un objecte ExpressioBooleana, i després es criden els mètodes contultaExpressio() i printArbre() i es comprova que el resultat és el correcte.
     */
    @Test
    public void creadora() throws Exception {
        String e = "a & b";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(e);

        String expressio_arbre = "(a&b)";

        assertEquals(e, EB.consultaExpressio());
        assertEquals(expressio_arbre, EB.printArbre());
    }



    /*
       ======================
        TESTS FUNCIONALITATS
       ======================
    */

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es crea correctament l'expressió booleana codificada com arbre quan es amb un &.
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida printArbre() per comprovar que el resultat es correcte.
     */
    @Test
    public void crearExpressioValida1() throws Exception {
        String  exp = "a&b";
        String expected = "(a&b)";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
        assertEquals(expected, EB.printArbre());
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es crea correctament l'expressió booleana codificada com arbre quan es amb un |.
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida printArbre() per comprovar que el resultat es correcte.
     */
    @Test
    public void crearExpressioValida2() throws Exception {
        String  exp = "a|b";
        String expected = "(a|b)";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
        assertEquals(expected, EB.printArbre());
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es crea correctament l'expressió booleana codificada com arbre quan es amb un !.
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida printArbre() per comprovar que el resultat es correcte.
     */
    @Test
    public void crearExpressioValida3() throws Exception {
        String  exp = "!a";
        String expected = "(!a)";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
        assertEquals(expected, EB.printArbre());
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es crea correctament l'expressió booleana codificada com arbre quan es amb {}.
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida printArbre() per comprovar que el resultat es correcte.
     */
    @Test
    public void crearExpressioValida4() throws Exception {
        String  exp = "{a b c}";
        String expected = "((a&b)&c)";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
        assertEquals(expected, EB.printArbre());
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es crea correctament l'expressió booleana codificada com arbre quan es amb "".
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida printArbre() per comprovar que el resultat es correcte.
     */
    @Test
    public void crearExpressioValida5() throws Exception {
        String  exp = "\"a b c d e!\"";
        String expected = "\"a b c d e!\"";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
        assertEquals(expected, EB.printArbre());
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es crea correctament l'expressió booleana codificada com arbre quan es nomès una paraula.
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida printArbre() per comprovar que el resultat es correcte.
     */
    @Test
    public void crearExpressioValida6() throws Exception {
        String  exp = "paraula";
        String expected = "paraula";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
        assertEquals(expected, EB.printArbre());
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es crea correctament l'expressió booleana codificada com arbre quan es combinen les operacions posibles.
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida printArbre() per comprovar que el resultat es correcte.
     */
    @Test
    public void crearExpressioValida7() throws Exception {
        String  exp = "{a b c} | !d & \"frase 12345\"";
        String expected = "((((a&b)&c)|(!d))&\"frase 12345\")";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
        assertEquals(expected, EB.printArbre());
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es crea correctament l'expressió booleana codificada com arbre quan es combinen les operacions posibles, habent-hi molts espais extranys.
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida printArbre() per comprovar que el resultat es correcte.
     */
    @Test
    public void crearExpressioValida8() throws Exception {
        String  exp = "a  &    b |       !d &    \"frase 12345\"     | !{  p1     p2      p3 }";
        String expected = "((((a&b)|(!d))&\"frase 12345\")|(!((p1&p2)&p3)))";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
        assertEquals(expected, EB.printArbre());
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es crea correctament l'expressió booleana codificada com arbre quan es combinen les operacions posibles, sent aquesta la del enunciat de la practica
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida printArbre() per comprovar que el resultat es correcte.
     */
    @Test
    public void crearExpressioValida9() throws Exception {
        String exp = "{p1 p2 p3} & (\"hola adéu\" | pep) & !joan";
        String expected = "((((p1&p2)&p3)&(\"hola adéu\"|pep))&(!joan))";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
        assertEquals(expected, EB.printArbre());
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es crea correctament l'expressió booleana codificada com arbre quan nomès tenim una paraula als corxetes
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida printArbre() per comprovar que el resultat es correcte.
     */
    @Test
    public void crearExpressioValida10() throws Exception {
        String exp = "{joan}";
        String expected = "joan";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
        assertEquals(expected, EB.printArbre());
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan tenim parentesis no tancats
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioParentesisInvalids1() throws Exception {
        String  exp = "((a & b) | c";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan tenim parentesis no oberts
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioParentesisInvalids2() throws Exception {
        String  exp = "(a & b) | c)";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan tenim parentesis buits
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioParentesisInvalids3() throws Exception {
        String  exp = "(a & b) & ()";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan tenim corxetes no tancats
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioCorxetesInvalids1() throws Exception {
        String  exp = "{{a b}";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan tenim corxetes no oberts
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioCorxetesInvalids2() throws Exception {
        String  exp = "{a b}}";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan tenim corxetes buits
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioCorxetesInvalids3() throws Exception {
        String  exp = "{}";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan tenim cometes no tancades
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioCometesInvalides1() throws Exception {
        String  exp = "\"a b & c | d";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan tenim cometes no obertes
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioCometesInvalides2() throws Exception {
        String  exp = "a b & c | d e f\"";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan tenim cometes buides
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioCometesInvalides3() throws Exception {
        String  exp = "\" \"";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan no tenim operador i ja està
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioInvalidaNoOperador1() throws Exception {
        String  exp = "paraula1 paraula2";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan no tenim operador
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioInvalidaNoOperador2() throws Exception {
        String  exp = "{a b}{c d}";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan no tenim operador amb cometes
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioInvalidaNoOperador3() throws Exception {
        String  exp = "\"a b\"\"c d\"";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan no tenim operador amb barreja de corchetes i cometes
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioInvalidaNoOperador4() throws Exception {
        String  exp = "\"a b\"{c d}";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan no tenim operador amb barreja de corchetes i cometes i paraules sueltes
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioInvalidaNoOperador5() throws Exception {
        String  exp = "{paraulacorchetes}paraula\"paraulacometes\"";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }


    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan no tenim operand per &
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioInvalidaNoOperand1() throws Exception {
        String  exp = "a&";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan no tenim operand per |
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioInvalidaNoOperand2() throws Exception {
        String  exp = "a&";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan no tenim un doble operador
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioInvalidaNoOperand3() throws Exception {
        String  exp = "a&&b";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }

    /**
     * Objecte de la prova: Test del mètode crearExpressio(String exp) de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per crear una expressió booleana.
     * Operativa: En aquest test es comprova que es llença l'excepció Exception quan no tenim operand !
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp invàlid i es comprova que salta l'excepció al ser incorrecte.
     */
    @Test (expected = Exception.class)
    public void crearExpressioInvalidaNoOperand4() throws Exception {
        String  exp = "a & !";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
    }


    /*
       ===============
        TESTS GETTERS
       ===============
    */

    /**
     * Objecte de la prova: Test del mètode getDocuments() de la classe ExpressioBooleana
     * Stubs: S'ha creat un stubDocument. L'objectiu d'aquest stub es simular l'existencia de documents per tal de que estigui correctament
     *  aïllat el testeig del mètode. Es simulen 5 documents, amb els seguents "paths" (identificador) i continguts:
     *      Document 1: path: d1, contingut: "a b c d e f g h i"
     *      Document 2: path: d2, contingut: "a! b! c!"
     *      Document 3: path: d3, contingut: "d? e?"
     *      Document 4: path: d4, contingut: "f, g. h, i."
     *      Document 5: path: d5, contingut: "palabra palabra2 palabra3 palabra4?"
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per obtenir els documents que cumpleixen l'expressio booleana definida a la classe.
     * Operativa: En aquest test es comprova que s'obtenen correctament els documents que cumpleixen una expressió bàsica
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida getDocuments() per comprovar que el resultat es correcte.
     */
    @Test
    public void cercaExpressio1() throws Exception {
        String  exp = "a & b";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);

        ArrayList<String> esperats = new ArrayList<>();
        esperats.add("d1"); esperats.add("d2");

        ArrayList<String> resultats = EB.getDocuments();
        assertEquals(resultats, esperats);
    }

    /**
     * Objecte de la prova: Test del mètode getDocuments() de la classe ExpressioBooleana
     * Stubs: S'ha creat un stubDocument. L'objectiu d'aquest stub es simular l'existencia de documents per tal de que estigui correctament
     *  aïllat el testeig del mètode. Es simulen 5 documents, amb els seguents "paths" (identificador) i continguts:
     *      Document 1: path: d1, contingut: "a b c d e f g h i"
     *      Document 2: path: d2, contingut: "a! b! c!"
     *      Document 3: path: d3, contingut: "d? e?"
     *      Document 4: path: d4, contingut: "f, g. h, i."
     *      Document 5: path: d5, contingut: "palabra palabra2 palabra3 palabra4?"
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per obtenir els documents que cumpleixen l'expressio booleana definida a la classe.
     * Operativa: En aquest test es comprova que s'obtenen correctament els documents amb distinció dels signes de puntuació
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida getDocuments() per comprovar que el resultat es correcte.
     */
    @Test
    public void cercaExpressio2() throws Exception {
        ExpressioBooleanaTest EB1 = new ExpressioBooleanaTest("a");
        ArrayList<String> esperats1 = new ArrayList<>();
        esperats1.add("d1"); esperats1.add("d2");

        ArrayList<String> resultats1 = EB1.getDocuments();
        assertEquals(esperats1, resultats1);


        ExpressioBooleanaTest EB2 = new ExpressioBooleanaTest("\"a!\"");
        ArrayList<String> esperats2 = new ArrayList<>();
        esperats2.add("d2");

        ArrayList<String> resultats2 = EB2.getDocuments();
        assertEquals(esperats2, resultats2);

        assertNotEquals(resultats1, resultats2);
    }

    /**
     * Objecte de la prova: Test del mètode getDocuments() de la classe ExpressioBooleana
     * Stubs: S'ha creat un stubDocument. L'objectiu d'aquest stub es simular l'existencia de documents per tal de que estigui correctament
     *  aïllat el testeig del mètode. Es simulen 5 documents, amb els seguents "paths" (identificador) i continguts:
     *      Document 1: path: d1, contingut: "a b c d e f g h i"
     *      Document 2: path: d2, contingut: "a! b! c!"
     *      Document 3: path: d3, contingut: "d? e?"
     *      Document 4: path: d4, contingut: "f, g. h, i."
     *      Document 5: path: d5, contingut: "palabra palabra2 palabra3 palabra4?"
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per obtenir els documents que cumpleixen l'expressio booleana definida a la classe.
     * Operativa: En aquest test es comprova que s'obtenen tots els documents amb una tautología
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida getDocuments() per comprovar que el resultat es correcte.
     */
    @Test
    public void cercaExpressio3() throws Exception {
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest("a | !a");
        ArrayList<String> esperats = new ArrayList<>();
        esperats.add("d4"); esperats.add("d5"); esperats.add("d1"); esperats.add("d2"); esperats.add("d3"); //Ordenats aixi per que encaixi, pero equivalent


        ArrayList<String> resultats = EB.getDocuments();
        assertEquals(esperats, resultats);
    }

    /**
     * Objecte de la prova: Test del mètode getDocuments() de la classe ExpressioBooleana
     * Stubs: S'ha creat un stubDocument. L'objectiu d'aquest stub es simular l'existencia de documents per tal de que estigui correctament
     *  aïllat el testeig del mètode. Es simulen 5 documents, amb els seguents "paths" (identificador) i continguts:
     *      Document 1: path: d1, contingut: "a b c d e f g h i"
     *      Document 2: path: d2, contingut: "a! b! c!"
     *      Document 3: path: d3, contingut: "d? e?"
     *      Document 4: path: d4, contingut: "f, g. h, i."
     *      Document 5: path: d5, contingut: "palabra palabra2 palabra3 palabra4?"
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per obtenir els documents que cumpleixen l'expressio booleana definida a la classe.
     * Operativa: En aquest test es comprova que no s'obtene capdocument amb una contradicció
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida getDocuments() per comprovar que el resultat es correcte.
     */
    @Test
    public void cercaExpressio4() throws Exception {
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest("a & !a");
        ArrayList<String> esperats = new ArrayList<>();

        ArrayList<String> resultats = EB.getDocuments();
        assertEquals(esperats, resultats);
    }

    /**
     * Objecte de la prova: Test del mètode getDocuments() de la classe ExpressioBooleana
     * Stubs: S'ha creat un stubDocument. L'objectiu d'aquest stub es simular l'existencia de documents per tal de que estigui correctament
     *  aïllat el testeig del mètode. Es simulen 5 documents, amb els seguents "paths" (identificador) i continguts:
     *      Document 1: path: d1, contingut: "a b c d e f g h i"
     *      Document 2: path: d2, contingut: "a! b! c!"
     *      Document 3: path: d3, contingut: "d? e?"
     *      Document 4: path: d4, contingut: "f, g. h, i."
     *      Document 5: path: d5, contingut: "palabra palabra2 palabra3 palabra4?"
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per obtenir els documents que cumpleixen l'expressio booleana definida a la classe.
     * Operativa: En aquest test es comprova que s'obtenen correctament els documents amb una expressió complexe, contenent paraules, cometes i corxetes
     * Es crea un objecte ExpressioBooleana vàlid amb parámetre exp, i es crida getDocuments() per comprovar que el resultat es correcte.
     */
    @Test
    public void cercaExpressio5() throws Exception {
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest("({b c} | {g i} | \"paraula1 paraula2\") & !a");
        ArrayList<String> esperats = new ArrayList<>();
        esperats.add("d4"); esperats.add("d5");

        ArrayList<String> resultats = EB.getDocuments();
        assertEquals(esperats, resultats);
    }

    /**
     * Objecte de la prova: Test del mètode consultaExpressio() de la classe ExpressioBooleana
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per obtenir l'expressio booleana definida a la classe.
     * Operativa: En aquest test es comprova que s'obtenen correctament l'expressio consultada.
     * Es crea un objecte ExpressioBooleana vàlid, i es comprova que l'expressió amb la que s'ha creat es la obtinguda.
     */
    @Test
    public void consultaExpressio() throws Exception {
        String exp = "{p1 p2 p3} & (\"hola adéu\" | pep) & !joan";
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);

        String result = EB.consultaExpressio();
        assertEquals(exp, result);
    }

    

}
