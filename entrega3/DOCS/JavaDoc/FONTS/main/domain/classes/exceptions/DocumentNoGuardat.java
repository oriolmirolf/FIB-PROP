package FONTS.src.main.domain.classes.exceptions;

/**
 * Classe per l'excepció de DocumentNoGuardat.
 */
public class DocumentNoGuardat extends Exception {
    /**
     * Creadora per defecte.
     * @param str Missatge a mostrar.
     */
    public DocumentNoGuardat(String str){
        super(str);
    }
}
