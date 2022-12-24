package FONTS.src.main.domain.classes.exceptions;

/**
 * Classe per l'excepció de NoExisteixDocument.
 */
public class NoExisteixDocument extends Exception {
    /**
     * Creadora per defecte.
     */
    public NoExisteixDocument() {
	super("El document no existeix.");
    }
}
