package EXE.tests;

import org.junit.Test;

import java.util.ArrayList;
import FONTS.src.main.domain.classes.exceptions.*;
import EXE.classes_tests.IndexExpressionsBooleanesTest;
import static org.junit.Assert.*;

public class TestIndexExpressionsBooleanes {
    private IndexExpressionsBooleanesTest index = new IndexExpressionsBooleanesTest ();


    /*
       ===========================
        TESTS AL AFEGIR EXPRESSIO
       ===========================
    */

    /**
     * Objecte de la prova: Test del mètode afegirExpressio(String nom, String exp) de la classe IndexExpressionsBooleanes
     * Altres elements integrats a la prova: El mètode crearExpressio(String exp) de la classe ExpressioBooleana ja s'ha testejat exhaustivament, per això i aquest mètode nomès cridar aquest, el testeig aquí no testeja les coses que s'han testejat al altre.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S'afegeix una expressio a IndexExpressionsBooleanes.
     * Operativa: En aquest test s'afegeix correctament una expressio booleana amb nom = nom i expressio = exp.
     * Primer es crea un objecte IndexExpressionsBooleanes, despres es crida al mètode afegirExpressio() i finalment amb existeixNom i consultaExpressio es comprova es comprova que el resultat és el correcte.
     */
    @Test
    public void afegirExpressio() throws ExpressioNoValida, JaExisteixExpressio, NoExisteixExpressio {
        String nom = "nom1";
        String exp = "a&b";
        index.afegirExpressio(nom, exp);

        assertTrue(index.existeixNom(nom));
        assertEquals(index.consultaExpressio(nom), exp);
    }

    /**
     * Objecte de la prova: Test del mètode afegirExpressio(String nom, String exp) de la classe IndexExpressionsBooleanes
     * Altres elements integrats a la prova: El mètode crearExpressio(String exp) de la classe ExpressioBooleana ja s'ha testejat exhaustivament, per això i aquest mètode nomès cridar aquest, el testeig aquí no testeja les coses que s'han testejat al altre.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S'afegeix una expressio a IndexExpressionsBooleanes.
     * Operativa: En aquest test s'afegeix incorrectament una expressio booleana degut a que el nom ja està agafat
     * Primer es crida al mètode afegirExpressio() i després es torna a cridar, amb el mateix nom, per comprovar que salta l'excepció.
     */
    @Test (expected=JaExisteixExpressio.class)
    public void afegirExpressioInvalida() throws ExpressioNoValida, JaExisteixExpressio {
        index.afegirExpressio("t2", "a&b");
        index.afegirExpressio("t2", "a|b");
    }

    /**
     * Objecte de la prova: Test del mètode afegirExpressio(String nom, String exp) de la classe IndexExpressionsBooleanes
     * Altres elements integrats a la prova: El mètode crearExpressio(String exp) de la classe ExpressioBooleana ja s'ha testejat exhaustivament, per això i aquest mètode nomès cridar aquest, el testeig aquí no testeja les coses que s'han testejat al altre.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S'afegeix una expressio a IndexExpressionsBooleanes.
     * Operativa: En aquest test s'afegeix incorrectament una expressio booleana degut a que l'expressió es invàlida
     * Es crida al mètode afegirExpressio() amb una expressió invàlda per comprovar que salta l'excepció.
     */
    @Test (expected =ExpressioNoValida.class)
    public void AfegirExpressioInvalida1() throws ExpressioNoValida, JaExisteixExpressio {
        index.afegirExpressio("testInvalid", "a&&b");
    }



    /*
       ==============================
        TESTS AL MODIFICAR EXPRESSIO
       ==============================
    */

    /**
     * Objecte de la prova: Test del mètode modificarExpressio(String nom, String exp) de la classe IndexExpressionsBooleanes
     * Altres elements integrats a la prova: El mètode crearExpressio(String exp) de la classe ExpressioBooleana ja s'ha testejat exhaustivament, per això s'omet el testeig exhaustiu de la validessa de l'expressió booleana en aquests tests.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es modifica una expressio guardada a IndexExpressionsBooleanes.
     * Operativa: En aquest test es modifica correctament una expressio de la col·leccio.
     * Es crida al mètode modificarExpressio() amb una expressió vàlida i desprès es crida a consulaExpressio per comprovar que s'ha modificat correctament.
     */
    @Test
    public void modificarExpressioCorrecte() throws ExpressioNoValida, JaExisteixExpressio, NoExisteixExpressio {
        index.afegirExpressio("t4", "a&b");

        String newExp = "b | c";

        index.modificarExpressio("t4", newExp);

        assertEquals(index.consultaExpressio("t4"), newExp);
    }

    /**
     * Objecte de la prova: Test del mètode modificarExpressio(String nom, String exp) de la classe IndexExpressionsBooleanes
     * Altres elements integrats a la prova: El mètode crearExpressio(String exp) de la classe ExpressioBooleana ja s'ha testejat exhaustivament, per això s'omet el testeig exhaustiu de la validessa de l'expressió booleana en aquests tests.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es modifica una expressio guardada a IndexExpressionsBooleanes.
     * Operativa: En aquest test es modifica incorrectament una expressio de la col·leccio, fent saltar NoExisteixExpressio.
     * Es crida al mètode modificarExpressio() amb un nom i es comprova que salta l'excepció.
     */
    @Test (expected = NoExisteixExpressio.class)
    public void modificarExpressioNomInvalid() throws ExpressioNoValida, NoExisteixExpressio {
        index.modificarExpressio("nomNoUsatAbans", "a&b");
    }

    /**
     * Objecte de la prova: Test del mètode modificarExpressio(String nom, String exp) de la classe IndexExpressionsBooleanes
     * Altres elements integrats a la prova: El mètode crearExpressio(String exp) de la classe ExpressioBooleana ja s'ha testejat exhaustivament, per això s'omet el testeig exhaustiu de la validessa de l'expressió booleana en aquests tests.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es modifica una expressio guardada a IndexExpressionsBooleanes.
     * Operativa: En aquest test es modifica incorrectament una expressio de la col·leccio per expressio invàlida.
     * Es crida al mètode afegirExpressio() creant una expressio vàlida, per despres cridar modificarExpressio() amb una expressió no vàlida, i comprovar que salta l'excepció.
     */
    @Test (expected = ExpressioNoValida.class)
    public void modificarExpressioExpInvalida() throws ExpressioNoValida, NoExisteixExpressio, JaExisteixExpressio {
        index.afegirExpressio("t6", "a&b");
        index.modificarExpressio("t6", "a||b");
    }



    /*
       ===========================
        TESTS AL BORRAR EXPRESSIO
       ===========================
    */


    /**
     * Objecte de la prova: Test del mètode borrarExpressio(String nom) de la classe IndexExpressionsBooleanes
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S'esborra una expressio guardada a IndexExpressionsBooleanes.
     * Operativa: En aquest test s'esborra correctament una expressio de la col·leccio.
     * Es crida al mètode afegirExpxressio() amb una expressió vàlida, desprès al mètode borrarExpressio() per borrar l'anterior i finalment desprès es crida a existeixNom() per comprovar que s'ha borrat correctament.
     */
    @Test
    public void borrarExpCorrecte() throws ExpressioNoValida, JaExisteixExpressio, NoExisteixExpressio {
        index.afegirExpressio("t7", "a&b");
        index.esborrarExpressio("t7");

        assertFalse(index.existeixNom("t7"));
    }

    /**
     * Objecte de la prova: Test del mètode borrarExpressio(String nom) de la classe IndexExpressionsBooleanes
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. S'esborra una expressio guardada a IndexExpressionsBooleanes.
     * Operativa: En aquest test s'esborra incorrectament una expressio de la col·leccio per nom invàlid, saltant l'excepció NoExisteixExpressio
     * Es crida al mètodeborrarExpressio() amb un nom invàlid per comprovar que salta l'excepció correctament.
     */
    @Test (expected = NoExisteixExpressio.class)
    public void borrarExpNomInvalid() throws NoExisteixExpressio {
        index.esborrarExpressio("nomFals");
    }



    /*
       =============================
        TESTS AL CONSULTAR EXPRESSIO
       =============================
    */

    /**
     * Objecte de la prova: Test del mètode consultaExpressio(String nom) de la classe IndexExpressionsBooleanes
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es consulta una expressio guardada a IndexExpressionsBooleanes.
     * Operativa: En aquest test es consulta correctament una expressio de la col·leccio.
     * Es crida al mètode afegirExpxressio() amb una expressió vàlida, i desprès al mètode consultaExpressio() per comprovar que es consulta correctament.
     */
    @Test
    public void consultaExpCorrecte() throws ExpressioNoValida, JaExisteixExpressio, NoExisteixExpressio {

        String exp = "a&b";

        index.afegirExpressio("t8", exp);

        assertEquals(index.consultaExpressio("t8"), exp);
    }

    /**
     * Objecte de la prova: Test del mètode consultaExpressio(String nom) de la classe IndexExpressionsBooleanes
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es consulta una expressio guardada a IndexExpressionsBooleanes.
     * Operativa: En aquest test es consulta incorrectament una expressio de la col·leccio per nom invàlid, saltant l'excepció NoExisteixExpressio
     * Es crida al consultaExpressio() amb un nom invàlid per comprovar que salta l'excepció correctament.
     */
    @Test (expected = NoExisteixExpressio.class)
    public void consultaExpNomInvalid() throws NoExisteixExpressio {
        index.consultaExpressio("nomFals");
    }



    /*
       ===========================
        TESTS AL CERCAR EXPRESSIO
       ===========================
    */

    /**
     * Objecte de la prova: Test del mètode cercaExpressioPerNom(String nom) de la classe ExpressioBooleana
     * Altres elements integrats a la prova: El mètode getDocuments() de la classe ExpressioBooleana ja s'ha testejat exhaustivament, per això i aquest mètode nomès cridar aquest, el testeig aquí no testeja les coses que s'han testejat al altre.
     * Stubs: S'ha creat un stubDocument. L'objectiu d'aquest stub es simular l'existencia de documents per tal de que estigui correctament
     *  aïllat el testeig del mètode. Es simulen 5 documents, amb els seguents "paths" (identificador) i continguts:
     *      Document 1: path: d1, contingut: "a b c d e f g h i"
     *      Document 2: path: d2, contingut: "a! b! c!"
     *      Document 3: path: d3, contingut: "d? e?"
     *      Document 4: path: d4, contingut: "f, g. h, i."
     *      Document 5: path: d5, contingut: "palabra palabra2 palabra3 palabra4?"
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per obtenir els documents que cumpleixen una expressio booleana, donant el nom.
     * Operativa: En aquest test es comprova que s'obtenen correctament els documents amb una expressió booleana.
     * S'afegeix una expressio booleana vàlida cridant el mètode afegirExpressio() i posteriorment es crida el metode cercaExpressioPerNom() amb el nom de l'expressió anterior per comprovar que funciona correctament.
     */
    @Test
    public void cercaExpressioValidaPerNom() throws ExpressioNoValida, JaExisteixExpressio, NoExisteixExpressio {
        ArrayList<String> esperats = new ArrayList<>();
        esperats.add("d1"); esperats.add("d2");
        index.afegirExpressio("nomEntrat", "a&b");

        ArrayList<String> resultats = index.cercaExpressioPerNom("nomEntrat");
        assertEquals(resultats, esperats);
    }

    /**
     * Objecte de la prova: Test del mètode cercaExpressioPerNom(String nom) de la classe ExpressioBooleana
     * Altres elements integrats a la prova: El mètode getDocuments() de la classe ExpressioBooleana ja s'ha testejat exhaustivament, per això i aquest mètode nomès cridar aquest, el testeig aquí no testeja les coses que s'han testejat al altre.
     * Stubs: S'ha creat un stubDocument. L'objectiu d'aquest stub es simular l'existencia de documents per tal de que estigui correctament
     *  aïllat el testeig del mètode. Es simulen 5 documents, amb els seguents "paths" (identificador) i continguts:
     *      Document 1: path: d1, contingut: "a b c d e f g h i"
     *      Document 2: path: d2, contingut: "a! b! c!"
     *      Document 3: path: d3, contingut: "d? e?"
     *      Document 4: path: d4, contingut: "f, g. h, i."
     *      Document 5: path: d5, contingut: "palabra palabra2 palabra3 palabra4?"
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per obtenir els documents que cumpleixen una expressio booleana, donant el nom.
     * Operativa: En aquest test es comprova que el mètodo falla al introduir un nom invàlid, saltant l'excepció NoExisteixExpressio
     * Es crida el mètode cercaExpressioPerNom() amb el nom d'una expressió que no existeix al index per comprovar que salta correctament l'excepció.
     */
    @Test (expected = NoExisteixExpressio.class)
    public void cercaExpressioNomInvalid() throws NoExisteixExpressio {
        index.cercaExpressioPerNom("nomInexistent");
    }


    /**
     * Objecte de la prova: Test del mètode cercaExpressio(String exp) de la classe ExpressioBooleana
     * Altres elements integrats a la prova: El mètode getDocuments() de la classe ExpressioBooleana ja s'ha testejat exhaustivament, per això i aquest mètode nomès cridar aquest, el testeig aquí no testeja les coses que s'han testejat al altre.
     * Stubs: S'ha creat un stubDocument. L'objectiu d'aquest stub es simular l'existencia de documents per tal de que estigui correctament
     *  aïllat el testeig del mètode. Es simulen 5 documents, amb els seguents "paths" (identificador) i continguts:
     *      Document 1: path: d1, contingut: "a b c d e f g h i"
     *      Document 2: path: d2, contingut: "a! b! c!"
     *      Document 3: path: d3, contingut: "d? e?"
     *      Document 4: path: d4, contingut: "f, g. h, i."
     *      Document 5: path: d5, contingut: "palabra palabra2 palabra3 palabra4?"
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per obtenir els documents que cumpleixen una expressio booleana.
     * Operativa: En aquest test es comprova que s'obtenen correctament els documents amb una expressió booleana, donant l'expressio.
     * Es crida el metode cercaExpressio() amb una expressió vàlida per comprovar que funciona correctament.
     */
    @Test
    public void cercaExpressioVàlida() throws ExpressioNoValida {
        ArrayList<String> esperats = new ArrayList<>();
        esperats.add("d3");

        ArrayList<String> resultats = index.cercaExpressio("d & !a");
        assertEquals(esperats, resultats);
    }

    /**
     * Objecte de la prova: Test del mètode cercaExpressio(String exp) de la classe ExpressioBooleana
     * Altres elements integrats a la prova: El mètode getDocuments() de la classe ExpressioBooleana ja s'ha testejat exhaustivament, per això i aquest mètode nomès cridar aquest, el testeig aquí no testeja les coses que s'han testejat al altre.
     * Stubs: S'ha creat un stubDocument. L'objectiu d'aquest stub es simular l'existencia de documents per tal de que estigui correctament
     *  aïllat el testeig del mètode. Es simulen 5 documents, amb els seguents "paths" (identificador) i continguts:
     *      Document 1: path: d1, contingut: "a b c d e f g h i"
     *      Document 2: path: d2, contingut: "a! b! c!"
     *      Document 3: path: d3, contingut: "d? e?"
     *      Document 4: path: d4, contingut: "f, g. h, i."
     *      Document 5: path: d5, contingut: "palabra palabra2 palabra3 palabra4?"
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Mètode per obtenir els documents que cumpleixen una expressio booleana.
     * Operativa: En aquest test es comprova que el mètodo falla al introduir una expressió invàlid, saltant l'excepció ExpressioNoValida
     * Es crida el mètode cercaExpressio() amb una expressió invàlida per comprovar que salta correctament l'excepció.
     */
    @Test (expected = ExpressioNoValida.class)
    public void cercaExpressioInvalida() throws ExpressioNoValida {
       index.cercaExpressio("a&&b");
    }

}
