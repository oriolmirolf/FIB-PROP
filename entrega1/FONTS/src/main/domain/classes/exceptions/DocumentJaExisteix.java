package FONTS.src.main.domain.classes.exceptions;

/**
 * Classe per l'excepció de DocumentJaExisteix.
 */
public class DocumentJaExisteix extends Exception {
    /**
     * Creadora per defecte.
     */
    public DocumentJaExisteix() {
	    super("El document no existeix.");
    }
}
