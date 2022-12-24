package FONTS.src.main.domain.classes.exceptions;

/**
 * Classe per l'excepció de FormatNoReconegut.
 */
public class FormatNoReconegut extends Exception {
    public FormatNoReconegut() {
        super("El format de l'arxiu no es reconegut.");
    }
}

