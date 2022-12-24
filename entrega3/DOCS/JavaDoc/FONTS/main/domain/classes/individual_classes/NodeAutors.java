package FONTS.src.main.domain.classes.individual_classes;

import java.io.Serializable;

/**
 * Classe que representa un node intern de l'Índex d'Autors
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class NodeAutors implements Serializable{
    /** Caràcter que representa el node */
    char data;
    /** Indica si existeix un autor amb el nom format des dels caràcters de l'arrel fins aquest node */
    boolean finalParaula;
    /** Fills esquerre, mig i dret */
    private NodeAutors left, mid, right;
    private static final long serialVersionUID = 5L;
    /**
     * Creadora per defecte.
     */
    public NodeAutors() {
        data = 0;
        finalParaula = false;
        left = null; mid = null; right = null;
    }

    /**
     * Crea un node
     * @param c Caràcter que es vol que representi el node
     * @return NodeAutors: Node creat
     */
    public static NodeAutors newNode(char c) {
        NodeAutors n = new NodeAutors();
        n.data = c;
        n.finalParaula = false;
        n.left = null; n.mid = null; n.right = null;
        return n;
    }

    /**
     * Getter de data.
     * @return char: Obté el caràcter emmagatzemat en el NodeAutors actual.
     */
    public char getData() {
        return data;
    }

    /**
     * Getter de finalParaula.
     * @return boolean: Retorna el finalParaula emmagatzemat en el NodeAutors actual.
     */
    public boolean getFinalParaula() {
        return finalParaula;
    }

    /**
     * Getter de left.
     * @return NodeAutors: Retorna el fill esquerre del NodeAutors actual.
     */
    public NodeAutors getLeft() {
        return left;
    }

    /**
     * Getter de mid.
     * @return NodeAutors: Retorna el fill del mig del NodeAutors actual.
     */
    public NodeAutors getMid() {
        return mid;
    }

    /**
     * Getter de right.
     * @return NodeAutors: Retorna el fill dret del NodeAutors actual.
     */
    public NodeAutors getRight() {
        return right;
    }

    /**
     * Setter de data.
     * @param d Nou valor de l'atribut data.
     */
    public void setData(char d) {
        data = d;
    }

    /**
     * Setter de finalParaula.
     * @param b Nou valor de l'atribut finalParaula.
     */
    public void setFinalParaula(boolean b) {
        finalParaula = b;
    }

    /**
     * Setter de left.
     * @param n Nou valor del fill esquerre left.
     */
    public void setLeft(NodeAutors n) {
        left = n;
    }

    /**
     * Setter de mid.
     * @param n Nou valor del fill del mig mid.
     */
    public void setMid(NodeAutors n) {
        mid = n;
    }

    /**
     * Setter de right.
     * @param n Nou valor del fill dret right.
     */
    public void setRight(NodeAutors n) {
        right = n;
    }
}
