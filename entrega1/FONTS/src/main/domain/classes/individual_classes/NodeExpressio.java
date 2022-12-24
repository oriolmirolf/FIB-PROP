package FONTS.src.main.domain.classes.individual_classes;

/**
 * Clase node, representa l'abre d'expressions booleanes.
 */
public class NodeExpressio {
    /**
     * Punters als subarbres esquerra i dret.
     */
    NodeExpressio es, de;

    /**
     * Predicat final del subarbre.
     * Només té valor quan es fulla.
     */
    String valor;

    /**
     * Operació del subarbre.
     * Si == 0, no hi ha operació, valor == predicat final.
      * Si == ", l'operació es búsqueda de documents que contenen la sequencia a valor.
     * Si == {@literal !}, l'operació és !, valor == null, es == null, només DRET.
     * Si == {@literal & o |}, l'operació és la donada, valor == null.
     */
    char op;



    /*
       ===========
        CREADORES
       ===========
     */

    /**
     * Creadora buida.
     */
    public NodeExpressio() {
        es = null;
        de = null;
        valor = null;
        op = '0';
    }

    /**
     * Creadora amb parámetre.
     * @param s Expressió booleana, predicat final.
     */
    public NodeExpressio(String s) {
        es = null;
        de = null;
        valor = s;
        op = '0';
    }



    /*
       ==========
        SETTERS
       ==========
     */

    /**
     * Configurador de fill dret.
     * @param d Nodo a apuntar per fill dret.
     */
    public void setFillDret(NodeExpressio d) { de = d; }

    /**
     * Configurador de fill esquerre.
     * @param i Nodo a apuntar per fill esquerre.
     */
    public void setFillEsquerre(NodeExpressio i) { es = i; }

    /**
     * Configurador de valor.
     * @param s Valor, predicat, del subarbre.
     */
    public void setValor(String s) { valor = s; }

    /**
     * Configurador de l'operador.
     * @param c Valor de l'operador.
     */
    public void setOp(char c) { op = c; }



    /*
       =========
        GETTERS
       =========
     */

    /**
     * Consultor de fill dret.
     * @return Fill dret de l'arbre actual.
     */
    public NodeExpressio getFillDret() { return de; }

    /**
     * Consultor de fill esquerre.
     * @return Fill esquerre de l'arbre actual.
     */
    public NodeExpressio getFillEsquerre() { return es; }

    /**
     * Consultor del valor.
     * @return Valor, predicat, de la fulla actual.
     */
    public String getValor()  { return valor; }

    /**
     * Consultor de l'operador.
     * @return Valor de l'operador.
     */
    public char getOp() {return op; }
}

