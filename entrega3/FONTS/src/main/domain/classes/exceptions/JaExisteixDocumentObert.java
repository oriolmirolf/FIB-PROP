package FONTS.src.main.domain.classes.exceptions;

/**
 * Classe per l'excepció de JaExisteixDocumentObert.
 */
public class JaExisteixDocumentObert extends Exception {
    /**
     * Creadora per defecte.
     */
    public JaExisteixDocumentObert() {
        super("Ja existeix un document obert.");
    }
}
