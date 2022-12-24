package FONTS.src.main.persistencia.excepcions;

/**
 * Classe per l'excepció de PathJaExisteix.
 */
public class PathJaExisteix extends Exception {
    public PathJaExisteix() {
        super("El path ja existeix en l'aplicacio");
    }
}