package FONTS.src.main.persistencia.excepcions;

/**
 * Classe per l'excepció de NoExisteixDocument.
 */
public class NoExisteixPath extends Exception {
    /**
     * Creadora per defecte.
     */
    public NoExisteixPath() {
	super("El document no existeix.");
    }
}
