package FONTS.src.main.domain.classes.indexes;

import FONTS.src.main.domain.classes.individual_classes.ExpressioBooleana;
import FONTS.src.main.domain.classes.exceptions.*;
import FONTS.src.main.domain.controllers.CtrlIndex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase on es guarden totes les expresions booleanes actualment definides i valides
 */
public class IndexExpressionsBooleanes implements Serializable{
    /**
     * Atribut amb totes les expresions booleanes, guardades pel seu nom.
     */
    private Map<String, ExpressioBooleana> conjuntExp = new HashMap<>();
    private static final long serialVersionUID = 11L;


    /*
       ===========
        CREADORES
       ===========
     */

    /**
     * Creadora buida
     */
    public IndexExpressionsBooleanes() {}

    /**
     * Creadora amb paràmetre, per fer set del controlador d'inxex
     * @param index Controlador d'index
     */
    public IndexExpressionsBooleanes(CtrlIndex index) {
        ExpressioBooleana.setCtrlIndex(index);
    }
    
    /*
       ===============
        FUNCIONALITATS
       ===============
     */

    /**
     * Operació per afegir una Expressió Booleana a la col·lecció.
     * @param nom Nom de l'Expressió.
     * @param exp Contingut de l'Expressió.
     * @throws JaExisteixExpressio Excepció que es llença quan ja existeix una Expressió amb nom nom.
     * @throws ExpressioNoValida Excepció que es llença quan novaExp no és vàlida sintàcticament.
     */
    public void afegirExpressio(String nom, String exp) throws JaExisteixExpressio, ExpressioNoValida {
        if (existeixNom(nom)) throw new JaExisteixExpressio("Ja existeix una expressió amb nom " + nom);

        ExpressioBooleana EB = new ExpressioBooleana();
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

        ExpressioBooleana EB = new ExpressioBooleana();
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

        if (!existeixNom(nom)) throw new NoExisteixExpressio("No existeix ningúna expressio amb el nom especificat");

        ExpressioBooleana exp = conjuntExp.get(nom);

        return exp.getDocuments();
    }

    /**
     * Cercadora per expressio booleana
     * @param exp Nom de l'expressio booleana per la que es vol cercar
     * @return Una llista amb els paths de tots els documents que satisfan l'expressio booleana donada
     * @throws ExpressioNoValida Quan l'expressio donada es inválida
     */
    public ArrayList<String> cercaExpressio(String exp) throws ExpressioNoValida {
        ExpressioBooleana EB = new ExpressioBooleana(exp);
        return EB.getDocuments();
    }

    /** Retorna l'expressió booleana de la expressio amb el nom donat.
     * @param nom Nom de la expressio.
     * @return Expressió booleana associada.
     * @throws NoExisteixExpressio No existeix una expressio amb el nom donat
     */
    public String consultaExpressio(String nom) throws NoExisteixExpressio{
        if (!existeixNom(nom)) throw new NoExisteixExpressio("No existeix ningúna expressio amb el nom especificat");
        return conjuntExp.get(nom).consultaExpressio();
    }

    /**
     * Llistadora d'expressions booleanes
     * @return retorna totes les expressions booleanes actualment a la col·leccio
     */
    public ArrayList<String> llistarExpressions() {
        ArrayList<String> AL = new ArrayList<>();
        for (Map.Entry<String, ExpressioBooleana> pair : conjuntExp.entrySet()) {
            AL.add(pair.getKey());
        }
        return AL;
    }

    /** Comprova si existeix alguna expressio amb el nom a la col·lecció
     * @param nom Nom de la expressio
     * @return True / False, si existeix / no existeix
     */
    private Boolean existeixNom(String nom) {
        return conjuntExp.containsKey(nom);
    }

    /**
     * Assigna el Controlador dÍndexs.
     * @param index Controlador d'Índexs.
     */
    public void setControlador (CtrlIndex index){
        ExpressioBooleana.setCtrlIndex(index);
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
