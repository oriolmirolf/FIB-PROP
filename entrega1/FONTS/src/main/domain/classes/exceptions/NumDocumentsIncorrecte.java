package FONTS.src.main.domain.classes.exceptions;

/**
 * Classe per l'excepció de NumDocumentsIncorrecte.
 */
public class NumDocumentsIncorrecte extends Exception {
    public NumDocumentsIncorrecte() {
	    super("El nombre de documents a buscar introduït és incorrecte.");
    }
}
