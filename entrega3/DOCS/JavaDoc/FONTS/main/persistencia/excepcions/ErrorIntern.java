package FONTS.src.main.persistencia.excepcions;

/**
 * Classe per l'excepció de ErrorIntern.
 */
public class ErrorIntern extends Exception {
    /**
     * Creadora per defecte.
     */
    public ErrorIntern() {
        super("Hi ha hagut un error intern, perfavor torna-ho a intenter.");
    }
}