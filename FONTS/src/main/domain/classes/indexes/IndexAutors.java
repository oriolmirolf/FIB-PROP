package FONTS.src.main.domain.classes.indexes;

import FONTS.src.main.domain.classes.exceptions.JaExisteixAutor;
import FONTS.src.main.domain.classes.exceptions.NoExisteixAutor;
import FONTS.src.main.domain.classes.individual_classes.NodeAutors;

import java.util.ArrayList;

/**
 * Classe que representa l'Índex dels Autors.
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class IndexAutors {
    /** Node arrel */
    private NodeAutors root;

    /**
     * Constructora per defecte.
     */
    public IndexAutors() {
        this.root = null;
    }
    
    /**
     * Afegeix un autor.
     * @param autor Nom de l'autor que es vol afegir.
     * @exception JaExisteixAutor L'autor que es vol afegir ja existeix.
     */
    public void afegirAutor(String autor) throws JaExisteixAutor {
        root = afegirAutorRec(root, autor, 0);
    }
    
    /**
     * Elimina un autor.
     * @param autor Nom de l'autor que es vol eliminar.
     * @exception NoExisteixAutor L'autor que es vol eliminar no existeix.
     */
    public void eliminarAutor(String autor) throws NoExisteixAutor {
        root = eliminarAutorRec(root, autor, 0);
    }

    /**
     * Canvia el nom d'un autor.
     * @param autor1 Nom de l'autor original.
     * @param autor2 Nou nom de l'autor al qual es vol canviar.
     * @exception JaExisteixAutor Ja existeix un autor amb nom autor2.
     * @exception NoExisteixAutor No existeix cap autor amb nom autor1.
     */
    public void modificarAutor(String autor1, String autor2) throws JaExisteixAutor, NoExisteixAutor {
        if (autor1.equals(autor2)) return;
        root = eliminarAutorRec(root, autor1, 0);
        root = afegirAutorRec(root, autor2, 0);
    }
    
    /**
    * Cerca els autors que comencen per un cert prefix.
    * @param prefix Prefix dels autors que volem buscar.
    * @return ArrayList: Llista amb els noms dels autors que tenen com a prefix el paràmetre prefix.
    */
    public ArrayList<String> cercarAutors(String prefix) {
        ArrayList<String> arr = new ArrayList<>();
        if (prefix.isEmpty()) cercaExhaustiva(root, "", arr);
        else cercarAutorsRec(root, prefix, 0, arr);
        return arr;
    }
    
    /**
    * Mètode privat per inserir un autor.
    * @param n Node actual.
    * @param s Nom de l'autor que es vol afegir.
    * @param i Índex del caràcter actual del paràmetre s.
    * @return NodeAutors: Retorna el nou node modificat.
    * @exception JaExisteixAutor L'autor que es vol afegir ja existeix.
    */
    private NodeAutors afegirAutorRec(NodeAutors n, String s, int i) throws JaExisteixAutor {
        // Cas base: si no exsteix el node arrel creem un
        if (n == null) n = n.newNode(s.charAt(i));
        
        // Si la lletra és de valor inferior insertem la paraula en el subarbre esquerre
        if (s.charAt(i) < n.getData()) n.setLeft(afegirAutorRec(n.getLeft(), s, i));
    
        // Si la lletra és de valor superior insertem la paraula en el subarbre dret
        else if (s.charAt(i) > n.getData()) n.setRight(afegirAutorRec(n.getRight(), s, i));
    
        // Si la lletra té el mateix valor...
        else {
            // ...si encara hi ha una paraula per procesar, continuem la paraula en el subarbre del mig
            if (i + 1 < s.length()) n.setMid(afegirAutorRec(n.getMid(), s, i + 1));
            // ...en cas contrari hem arribat al final de la paraula
            else {
                if (n.getFinalParaula()) throw new JaExisteixAutor("Ja existeix l'autor " + s);
                else n.setFinalParaula(true);
            }
        }
        return n;
    }
    
    /**
    * Mètode privat per inserir un autor.
    * @param n Node actual.
    * @param s Nom de l'autor que es vol eliminar.
    * @param i Índex del caràcter actual del paràmetre s.
    * @return NodeAutors: Retorna el nou node modificat.
    * @exception NoExisteixAutor L'autor que es vol eliminar no existeix.
    */
    private NodeAutors eliminarAutorRec(NodeAutors n, String s, int i) throws NoExisteixAutor {
        // Cas base: si no existeix el node significa que la paraula no es troba a l'arbre
        if (n == null) throw new NoExisteixAutor("No existeix l'autor " + s);
            
        // Si la lletra és de valor inferior cerquem la paraula en el subarbre esquerre
        if (s.charAt(i) < n.getData()) n.setLeft(eliminarAutorRec(n.getLeft(), s, i));
    
        // Si la lletra és de valor superior cerquem la paraula en el subarbre dret
        else if (s.charAt(i) > n.getData()) n.setRight(eliminarAutorRec(n.getRight(), s, i));
        
        // Si la lletra té el mateix valor...
        else {
            //...si hem arribat al final de s
            if (i + 1 == s.length()) {
                if (!n.getFinalParaula()) throw new NoExisteixAutor("No existeix l'autor " + s);
                else n.setFinalParaula(false);
            }

            //...en cas contrari continuem buscant pel subarbre del mig
            else n.setMid(eliminarAutorRec(n.getMid(), s, i + 1));
        }
        // Eliminem recursivament els nodes que ja no ens fan falta
        if (n != null && !n.getFinalParaula() && n.getLeft() == null && n.getMid() == null && n.getRight() == null) n = null;
        return n;
    }
    
    /**
    * Mètode privat per cercar autors donat un prefix.
    * @param n Node actual.
    * @param s Nom de l'autor que es vol cercar.
    * @param i Índex del caràcter actual del paràmetre s.
    * @param arr Llista amb els noms dels autors que tenen com a prefix el paràmetre s fins a la posició i - 1.
    */
    private void cercarAutorsRec(NodeAutors n, String s, int i, ArrayList<String> arr) {
        // Cas base: si  l'arbre està buid no fem res
        if (n == null) return;

        // Si la lletra és de valor inferior cerquem la paraula en el subarbre esquerre
        if (s.charAt(i) < n.getData()) cercarAutorsRec(n.getLeft(), s, i, arr);
    
        // Si la lletra és de valor superior cerquem la paraula en el subarbre dret
        else if (s.charAt(i) > n.getData()) cercarAutorsRec(n.getRight(), s, i, arr);
        
        // Si la letra té el mateix valor...
        else {
            //...si hem arribat al final de s, comencem a fer una cerca exhaustiva per tots els fills d'aquest node
            if (i + 1 == s.length()) {
                if (n.getFinalParaula()) arr.add(s);
                cercaExhaustiva(n.getMid(), s, arr);
            }

            //...en cas contrari continuem buscant pel subarbre del mig
            else cercarAutorsRec(n.getMid(), s, i + 1, arr);
        }
    }
    
    /**
    * Mètode privat que ajuda a la cerca d'autors donat un prefix. S'encarrega d'afegir tots els autors.
    * que tenen com a prefix el paràmetre s.
    * @param n Node actual.
    * @param s Nom de l'autor que es vol cercar.
    * @param arr Llista amb els noms dels autors que tenen com a prefix el paràmetre s.
    */
    private void cercaExhaustiva(NodeAutors n, String s, ArrayList<String> arr) {
        // Cas base: si  l'arbre està buid no fem res
        if (n == null) return;

        String s2 = s + n.getData();
        // Si és final de paraula l'afegim als resultats
        if (n.getFinalParaula()) arr.add(s2);
        // Busquem més autors per tots els fills de n
        cercaExhaustiva(n.getLeft(), s, arr);
        cercaExhaustiva(n.getMid(), s2, arr);
        cercaExhaustiva(n.getRight(), s, arr);
    }
}
