package FONTS.src.main.domain.classes.exceptions;

/**
 * Classe per l'excepció de NoExisteixExpressio.
 */
public class NoExisteixExpressio extends Exception{
    /**
     * Creadora per defecte.
     */
    public NoExisteixExpressio() {}
    /**
     * Creadora per defecte amb paràmetre String.
     */
    public NoExisteixExpressio(String str) {
        super(str);
    }
}
