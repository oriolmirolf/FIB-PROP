package FONTS.src.main.domain.classes.individual_classes;
import FONTS.src.main.domain.controllers.CtrlIndex;
import FONTS.src.main.domain.classes.exceptions.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Clase que conté la representació en string i en arbre d'aquesta.
 */
public class ExpressioBooleana implements Serializable{
    private static CtrlIndex cIndex;

    /**
     * Expressió booleana representada per l'instancia de la clase.
     */
    private String stringExpression;

    /**
     * NodeExpressio arbre de la  representació de l'expressió booleana.
     */
    private NodeExpressio root;

    private static final long serialVersionUID = 12L;

    /*
       ===========
        CREADORES
       ===========
     */

    /**
     * Creadora buida
     */
    public ExpressioBooleana() {}

    /**
     * Creadora donada una expressio
     * @param exp Expressio Booleana
     * @throws ExpressioNoValida Si l'expressio booleana que intentem introduir no es vàlida.
     */
    public ExpressioBooleana(String exp) throws ExpressioNoValida {
        stringExpression = exp;
        root = new NodeExpressio(exp);
        buildTree(root, exp);
    }



    /*
       ===============
        FUNCIONALITATS
       ===============
     */

    /** Crea una nova expressió booleana
     *
     * @param exp Expressió booleana
     * @throws ExpressioNoValida Si l'expressio booleana que intentem introduir no es vàlida.
     */
    public void crearExpressio(String exp) throws ExpressioNoValida {
        stringExpression = exp;
        root = new NodeExpressio(exp);
        buildTree(root, exp);
    }

    /** Crea recursivament l'arbre. Penja del nodo pare l'expressió booleana s.
     *
     * @param father Nodo pare actual
     * @param s Expressió booleana
     * @throws ExpressioNoValida Si l'expressio booleana que intentem introduir no es vàlida
     */
    private void buildTree(NodeExpressio father, String s) throws ExpressioNoValida{

        if (s.isBlank()) throw new ExpressioNoValida("Expressió booleana no válida");
        s = s.trim();


        //Borrador de parentesis innecesarios: (a&b) --> a&b
        int[] rp = new int[s.length()];
        Arrays.fill(rp, -1);
        int c = 1;
        for (int i = 0; i < s.length(); ++i){
            if (s.charAt(i) == '(') rp[i] = ++c;
            if (s.charAt(i) == ')') rp[i] = c--;
        }
        c = 0; //usaremos de indice ahora
        while (c < s.length() && s.charAt(0) == '(' && s.charAt(s.length()-1) == ')' && rp[c] == rp[rp.length-1-c]){
            s = s.substring(1, s.length()-1);
            ++c;
        }

        if (s.isBlank()) throw new ExpressioNoValida("Expressió booleana no válida");
        s = s.trim();

        /*
        //Testeamos por situaciones como (a)(b)
        String test = s.replaceAll(" ", "");
        for (int i = 1; i < test.length(); ++i) {
            if (test.charAt(i-1) == '(' && test.charAt(i) == ')') throw new ExpressioNoValida("Expressió booleana no válida");
        }*/


        int index = findHighestOpeartion(s);

        /*  Casos bases: -2 = invalido, -1 = valor final
            -1 : predicat final
            -2 : predicat final entre ""
            -3 : predicats entre {}, aplicarlis &
         */
        if (index == -3)
        {
            int test = 0;
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '{' || s.charAt(i) == '}') test++;
            }
            if (test != 2) throw new ExpressioNoValida("Expressió booleana no válida");

            if (s.substring(1, s.length()-1).isBlank()) throw new ExpressioNoValida("Expressió booleana no válida");
            s = convertCorchsToOps(s);
            index = findHighestOpeartion(s);
        }

        //En aquest ordre ja que al anterior pot donar que nomès era una paraula!

        if (index == -1)
        {
            //Caso de que sean dos palabras
            if (s.split(" ").length >= 2) throw new ExpressioNoValida("Expressió booleana no válida");

            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '"' || s.charAt(i) == '{' || s.charAt(i) == '}')
                    throw new ExpressioNoValida("Expressió booleana no válida");
                }

            father.setOp('0');
            father.setValor(s);
            return;
        }
        else if (index == -2)
        {
            if (s.substring(1, s.length()-1).isBlank()) throw new ExpressioNoValida("Expressió booleana no válida");

            //Test para casos tipo "a""b" o si se incluyen {}
            int test = 0;
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '"') test++;
            }
            if (test != 2) throw new ExpressioNoValida("Expressió booleana no válida");

            father.setOp('"');
            father.setValor(s.substring(1, s.length()-1).trim());
            return;
        }

        //Else, subpartimos
        father.setOp(s.charAt(index));

        String sleft = s.substring(0, index);
        String sright = s.substring(index + 1);
        if (s.charAt(index) == '!') //Nomes tindrem part dreta
        {
            if (!sleft.isBlank()) throw new ExpressioNoValida("Expressió booleana no válida");

            //System.out.println("sr: " + sright);
            NodeExpressio r = new NodeExpressio(sright);
            father.setFillDret(r);
            buildTree(r, sright);
        }
        else {
            //System.out.println("sl : " + sleft + ", sr: " + sright);

            NodeExpressio l = new NodeExpressio(sleft);
            NodeExpressio r = new NodeExpressio(sright);
            father.setFillEsquerre(l);
            father.setFillDret(r);
            father.setOp(s.charAt(index));

            buildTree(l, sleft);
            buildTree(r, sright);
        }
    }


    /** Busca l'operació de máxima prioritat a l'expressió booleana s.
     *
     * @param s Expressió booleana, pot ser no válida
     * @return  Si l'expressió es válida i conté operacions, retorna l'index 0 {@literal <} i {@literal >} s.length()-1 de la operació que s'ha de fer primer.
     *          "-1" si l'expressió només consisteix en un predicat.
     *          "-2" si l'expressió només consisteix en un predicat, dins de " ".
     *          "-3" si l'expressió son nomes predicats dins de {}.
     */
    private int findHighestOpeartion(String s) throws ExpressioNoValida{

        if (s.isBlank()) throw new ExpressioNoValida("Expressió booleana no válida");;

        int[] r = new int[s.length()];

        //ini todo a -1, nos ahorra trabajo luego
        Arrays.fill(r, -1);

        int rank = 0;

        for(int i = 0; i < s.length(); ++i)
        { // Increment through every character in your string.
            char c = s.charAt(i);
            if(c == '&' || c == '|' || c == '!') r[i] = rank;
            else
            {
                switch(c)
                {
                    case '"':   //Comprobar bien este caso: "" ""
                        ++i;
                        while (i < s.length() && s.charAt(i) != '"') ++i;
                        if (i == s.length()) throw new ExpressioNoValida("Expressió booleana no válida");;
                        break;
                    case '(': // Then the operators inside this '(' are deeper into your expression, so increment rank
                        rank++;
                        break;
                    case ')':
                        rank--;
                        if (rank < 0) throw new ExpressioNoValida("Expressió booleana no válida");
                        break;
                    case '{':
                        ++i;
                        while (i < s.length() && s.charAt(i) != '}') {
                            ++i;
                            if (i == s.length() || s.charAt(i) == '{') throw new ExpressioNoValida("Expressió booleana no válida"); //Es falso
                        }
                        break;
                    case '}':
                        //si arrivem aquí es per que abans no hi habia ningun '{'
                        throw new ExpressioNoValida("Expressió booleana no válida");
                    default:
                }
            }
        }

        if (rank != 0) throw new ExpressioNoValida("Expressió booleana no válida");

        int min = r.length + 1;
        int indexMin = -1;
        for (int i = r.length-1; i >= 0; --i) {
            if (r[i] != -1)
                if (r[i] < min) {
                    //System.out.println("value : " + r[i] + ", index : "+i+", char : "+s.charAt(i));
                    min = r[i];
                    indexMin = i;
                }
                else if(r[i] == min && s.charAt(indexMin) == '!') {
                    min = r[i];
                    indexMin = i;
                }
        }
        if (indexMin == -1) //cas no hem trobat cap operació
        {
            if (s.charAt(0) == '"' && s.charAt(s.length()-1) == '"') return -2;
            if (s.charAt(0) == '{' && s.charAt(s.length()-1) == '}') return -3;
        }
        return indexMin;
    }

    /** Converteix una expressió del tipus {a b c} a una del tipus {@literal a&b&c}
     *
     * @param s Expressió entre "{}" a evaluar
     * @return Expressió s convertida
     */
    private String convertCorchsToOps(String s) {
        return s.substring(1, s.length()-1).trim().replaceAll(" +", "&");
    }




    /*
       =========
        GETTERS
       =========
     */

    /**
     * Getter dels documents que compleixen l'expressio booleana definida a l'instancia de la classe actual.
     * @return Llista amb els paths de tots els documents que cumpleixen l'expressió booleana.
     */
    public ArrayList<String> getDocuments() {
        return new ArrayList<>(getDocumentsRecursiu(root));
    }

    /**
     * Getter recursiu dels documents que compleixen l'expressio booleana definida al subarbre de n actual
     * @param n NodeExpressio pare
     * @return Llista amb els paths de tots els documents que cumpleixen l'expressió booleana definida a partir de n.
     */
    private HashSet<String> getDocumentsRecursiu(NodeExpressio n) {
        char op = n.getOp();

        HashSet<String> fd = new HashSet<>();
        HashSet<String> fe = new HashSet<>();

        switch (op) {
            case '&': 
                //Intersecció dels dos fills

                fd = getDocumentsRecursiu(n.getFillDret());
                fe = getDocumentsRecursiu(n.getFillEsquerre());
                fd.retainAll(fe);
                return fd;
            
            case '|':
                //Unio dels dos fills

                fd = getDocumentsRecursiu(n.getFillDret());
                fe = getDocumentsRecursiu(n.getFillEsquerre());
                fd.addAll(fe);
                return fd;
            
            case '!':
                //Complement

                fd = getDocumentsRecursiu(n.getFillDret());

                fe = cIndex.getTotsDocuments();
                fe.removeAll(fd);

                return fe; //FALS! Descomentar primer
            
            case '"':
                //Cas base, predicat es una sequencia de paraules
                String f = n.getValor();
                fe = cIndex.getDocumentsFrase(f);
                return fe;
            
            case '0':
                //Cas base, es directament predicat

                String p = n.getValor();
                fe = cIndex.getDocumentsParaula(p);
                return fe; //Fals! Descomentar primer
            
        }
        //Nunca se podrá llegar, sirve para evitar warnings.
        return fd;
    }


    /** Expressió consultora
     *
     * @return L'expressió booleana asociada a l'instancia de la clase actual
     */
    public String consultaExpressio() {return stringExpression;}



    /*
       =========
        SETTERS
       =========
     */

    /**
     * Setter pel controlador d'index de les expressions booleanes, per tal de poder accedir als documents.
     * @param c
     */
    public static void setCtrlIndex(CtrlIndex c){
        cIndex = c;
    }


    /*
      ================================
       DEBUGGING (No incluir a fonts)
      ================================


    public CtrlIndex getCtlIndex() {
        return cIndex;
    }

    //Funcions per debugaar
    public String printArbre() {
        return printRecursiu(root);
    }

    public String  printRecursiu(NodeExpressio r) {
        String ret = "";

        NodeExpressio e = r.getFillEsquerre();
        NodeExpressio d = r.getFillDret();

        char op = r.getOp();

        if (op == '|' || op == '&') {
            ret += '(';
            ret += printRecursiu(e);
            ret += op;
            ret += printRecursiu(d);
            ret += ')';
        }
        else if (op == '!') {
            ret += '(';
            ret += op;
            ret += printRecursiu(d);
            ret += ')';
        }
        else if (op == '"') {
            ret += '"';
            ret += r.getValor();
            ret += '"';
        }
        else ret += r.getValor();

        return ret;
    }



    //A partir d'aquí, funcions descartades. No documentades per evitar tenir coses innecesaries a la documentació.

    /*
    //Returns true if f has a higher preference than s
    private Boolean hasLowerPreference(char iact, char imin) {
        if (imin == '|') return false;
        if (iact == '|') return true;
        if (imin == '&') return false;
        if (iact == '&') return true;
        else return false;
    }

    //Returns true if expression s is valid
    public Boolean isValidExpression(String s) {
        s = s.replaceAll(" ", "");
        if (s == "") return false;
        char prev = s.charAt(0);
        if (prev == ')' || prev == '}' || prev == '&' || prev == '|')
            return false;

        char[] x = s.toCharArray();
        int parCounter = 0;
        Boolean closedConj = true;
        Boolean closedComs = true;
        Boolean closedExpression = true;
        for (int i = 1; i < x.length; ++i) {
            char act = x[i];
            switch (act){
                case '(':
                    if (prev == ')') return false;
                    parCounter++;
                    break;
                case ')':
                    if (parCounter == 0 || prev == '&' || prev == '|') return false;

                    parCounter--;
                    break;
                case '"':   //Comprobar bien este caso: "" ""
                    ++i;
                    while (i < x.length && x[i] != '"') ++i;

                    if (i == x.length) return false;
                    break;
                case '{':
                    ++i;
                    while (i < x.length && x[i] != '}') ++i;
                    if (i == x.length) return false;
                    break;
                case '}':
                    if (!closedConj) closedConj = true;
                    else return false;
                    break;
                case '&':
                    if (prev == '&' || prev == '|' || prev == '!') return false;
                    break;
                case '|':
                    if (prev == '&' || prev == '|' || prev == '!') return false;
                    break;
                case '!':
                    if (prev == '"' || prev == ')' || prev == '}' || prev == '!') return false;
                    break;
                default: //entra una letra!
                    if  (prev == ')' || prev == '}' || prev == '"') return false;
                    break;
            }


            //Dos cerrados seguidos
            if (parCounter < 0) return false;
            prev = act;
        }
        return true;
    }

     */
}
