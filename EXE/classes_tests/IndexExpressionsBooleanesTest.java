package EXE.classes_tests;


import EXE.classes_tests.ExpressioBooleanaTest;
import FONTS.src.main.domain.classes.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase on es guarden totes les expresions booleanes actualment definides i valides
 */
public class IndexExpressionsBooleanesTest {
    /**
     * Atribut amb totes les expresions booleanes, guardades pel seu nom.
     */
    private Map<String, ExpressioBooleanaTest> conjuntExp = new HashMap<>();



    /*
       ===========
        CREADORES
       ===========
     */

    /**
     * Creadora buida
     */
    public IndexExpressionsBooleanesTest() {}


    /*
       ===============
        FUNCIONALITATS
       ===============
     */

    /**
     * Operació per afegir una Expressió Booleana a la col·lecció.
     * @param nom Nom de l'Expressió.
     * @param exp Contingut de l'Expressió.
     * @throws NoExisteixExpressio Excepció que es llença quan ja existeix una Expressió amb nom nom.
     * @throws ExpressioNoValida Excepció que es llença quan novaExp no és vàlida sintàcticament.
     */
    public void afegirExpressio(String nom, String exp) throws JaExisteixExpressio, ExpressioNoValida {
        if (existeixNom(nom)) throw new JaExisteixExpressio("Ja existeix una expressió amb nom " + nom);

        ExpressioBooleanaTest EB = new ExpressioBooleanaTest();
        EB.crearExpressio(exp);
        conjuntExp.put(nom, EB);
    }

    /**
     * Operació per eliminar una Expressió Booleana de la col·lecció.
     * @param nom Nom de l'Expressió a eliminar.
     * @throws NoExisteixExpressio Excepció que es llença quan no existeix cap Expressió amb nom nom.
     */
    public void esborrarExpressio(String nom) throws NoExisteixExpressio {
        if (!existeixNom(nom)) throw new NoExisteixExpressio("No existeix cap expressió amb nom " + nom);
        conjuntExp.remove(nom);
    }

    /**
     * Operació per modificar una Expressió Booleana ja existent.
     * @param nom Nom de l'Expressió.
     * @param exp Contingut de l'Expressió.
     * @throws NoExisteixExpressio Excepció que es llença quan no existeix cap Expressió amb nom nom.
     * @throws ExpressioNoValida Excepció que es llença quan novaExp no és vàlida sintàcticament.
     */
    public void modificarExpressio(String nom, String exp) throws NoExisteixExpressio, ExpressioNoValida {
        if (!existeixNom(nom)) throw new NoExisteixExpressio("No existeix cap expressió amb nom " + nom);

        ExpressioBooleanaTest EB = new ExpressioBooleanaTest();
        EB.crearExpressio(exp);
        conjuntExp.replace(nom, EB);
    }



    /*
       =========
        GETTERS
       =========
     */

    /**
     * Cercadora per expressio booleana, donat el seu nom.
     * @param nom Nom de l'expressio booleana per la que es vol cercar
     * @return Una llista amb els paths de tots els documents que satisfan l'expressio booleana donada
     * @throws NoExisteixExpressio Quan l'expressio cercada no existeix
     */
    public ArrayList<String> cercaExpressioPerNom(String nom) throws NoExisteixExpressio {

        if (!existeixNom(nom)) throw new NoExisteixExpressio("No existeix ningúna cláusula amb el nom especificat");

        ExpressioBooleanaTest exp = conjuntExp.get(nom);

        return exp.getDocuments();
    }

    /**
     * Cercadora per expressio booleana
     * @param exp Nom de l'expressio booleana per la que es vol cercar
     * @return Una llista amb els paths de tots els documents que satisfan l'expressio booleana donada
     * @throws ExpressioNoValida Quan l'expressio donada es inválida
     */
    public ArrayList<String> cercaExpressio(String exp) throws ExpressioNoValida {
        ExpressioBooleanaTest EB = new ExpressioBooleanaTest(exp);
        return EB.getDocuments();
    }

    /** Retorna l'expressió booleana de la cláusula amb el nom donat.
     * @param nom Nom de la cláusula.
     * @return Expressió booleana associada.
     * @throws NoExisteixExpressio No existeix una expressio amb el nom donat
     */
    public String consultaExpressio(String nom) throws NoExisteixExpressio{
        if (!existeixNom(nom)) throw new NoExisteixExpressio("No existeix ningúna cláusula amb el nom especificat");
        return conjuntExp.get(nom).consultaExpressio();
    }

    /** Comprova si existeix alguna cláusula amb el nom a la col·lecció
     * @param nom Nom de la cláusula
     * @return True / False, si existeix / no existeix
     */
    public Boolean existeixNom(String nom) {
        return conjuntExp.containsKey(nom);
    }



    /*
       ===========
        DEBUGGING
       ===========

    public void print(String nom) throws NoExisteixExpressio {
        if (!existeixNom(nom)) throw new NoExisteixExpressio();
        conjuntExp.get(nom).printArbre();
    }

    */
}
