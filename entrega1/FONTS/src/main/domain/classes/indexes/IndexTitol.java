package FONTS.src.main.domain.classes.indexes;

import FONTS.src.main.domain.classes.exceptions.DocumentJaExisteix;
import FONTS.src.main.domain.classes.exceptions.NoExisteixDocument;
import FONTS.src.main.domain.classes.exceptions.NoExisteixAutor;

import java.util.TreeMap;

public class IndexTitol {
    private TreeMap<String, TreeMap<String, String>> index;
    /**
     * Constructora de la clase
     */
    public IndexTitol(){
        index = new TreeMap<>();
    }
     /**
     * Operació per afegir un titol
     *
     * @param autor Nom de l'autor del nou document.
     * @param titol Titol del nou document.
     * @param path Direcció on es troba guardat el contingut del document.
     * @throws DocumentJaExisteix Excepció que es llença quan ja existeix un document amb mateix títol i autor.
     */
    public void afegirTitol(String autor, String titol, String path) throws DocumentJaExisteix {
        if (index.containsKey(autor)) {
            TreeMap<String, String> titols = index.get(autor);
            if (titols.containsKey(titol)) throw new DocumentJaExisteix();
            titols.put(titol, path);
        }
        else {
            TreeMap<String, String> titols = new TreeMap<>();
            titols.put(titol, path);
            index.put(autor, titols);
        }
    }

    /**
     * Operació per tenir els títols d'un autor
     * @param autor autor a fer la cerca
     * @return Es retornen els títols del autor
     * @throws NoExisteixAutor Excepció que es llença quan l'autor introduït no existeix
     */
    public TreeMap<String, String> getTitols(String autor) throws NoExisteixAutor {
        if (!index.containsKey(autor)) throw new NoExisteixAutor("L'autor no existeix.");
        TreeMap<String, String> titols = index.get(autor);
        return titols;
    }

    /**
     * Operació per obtenir el path del Document amb autor autor i títol titol.
     * @param autor Autor del Document a buscar.
     * @param titol Titol del Document a buscar.
     * @return Direcció del document a buscar.
     * @throws NoExisteixAutor Excepció que es llença quan l'autor introduït no existeix.
     * @throws NoExisteixDocument Excepció que es llença quan el document introduït no existeix.
     */
    public String getTitol(String autor, String titol) throws NoExisteixAutor, NoExisteixDocument {
        TreeMap<String, String> titols = getTitols(autor);
        if (!titols.containsKey(titol)) throw new NoExisteixDocument();
        String path =  titols.get(titol);
        return path;
    }
    /**
     * Operació per modificar l'autor d'un Document.
     * @param autorAnterior Antic autor del document a modificar.
     * @param nouAutor Nou autor del Document.
     * @param titol Títol del Document a modificar.
     */
    public void modificarAutor(String autorAnterior, String nouAutor, String titol) {
        TreeMap<String, String> titolsAutorAnterior;
        try {
            titolsAutorAnterior = getTitols(autorAnterior);
            String path = titolsAutorAnterior.remove(titol);
            if (titolsAutorAnterior.isEmpty()) index.remove(autorAnterior);

            if (index.containsKey(nouAutor)) index.get(nouAutor).put(titol, path);
            else {
                TreeMap<String, String> tit = new TreeMap<>();
                tit.put(titol, path);
                index.put(nouAutor, tit);
            }
        } catch (NoExisteixAutor e) {
            System.out.println("Això mai passa ja que autorAnterior sempre existeix.");
        }
    }

    /**
     * Operació per modificar el títol d'un Document.
     * @param nouTitol Nou títol del Document.
     * @param titolAnterior Antic títol del Document a modificar.
     * @param autor Autor del Document a modificar.
     */
    public void modificarTitol(String nouTitol, String titolAnterior, String autor) {
        try {
            TreeMap<String, String> titols = getTitols(autor);
            String path = titols.remove(titolAnterior);
            titols.put(nouTitol, path);
        } catch (NoExisteixAutor e) {
            System.out.println("Això mai passa ja que autor sempre existeix 6345.");
        }
    }

    /**
     * Operació per eliminar un Autor
     * @param autor autor a eliminar
     */
    private void eliminarAutor(String autor) {
        if (index.containsKey(autor))index.remove(autor);

    }
    /**
     * Operació per eliminar un Document.
     * @param autor Autor del Document a eliminar.
     * @param titol Títol del Document a eliminar.
     */
    public void eliminarTitol(String autor, String titol) {
        try {
            TreeMap<String, String> t = getTitols(autor);
            if (t.containsKey(titol)) t.remove(titol);
            if (t.isEmpty()) eliminarAutor(autor);
        } catch (Exception e) {
            System.out.println("Això mai passa IndexTitol.java 432");
        }

    }
    /**
     * Operació per comprovar si existeix un Autor a l'índex
     * @param autor Autor a buscar
     * @return Retorna si existeix l'autor en l'index.
     */
    public boolean existeixAutor(String autor){
        return index.containsKey(autor);
    }
    /**
     * Operació per veure si existeix un Document l'índex
     * @param titol titol del Document a comprovar
     * @param autor autor del Document a comprovar
     * @return Retorna si existeix el Document amb autor i titol.
    */
    public boolean existeixDocument(String titol, String autor) {
        TreeMap<String, String> titols = index.get(autor);
        if (titols == null) return false;
        return titols.containsKey(titol);
    }
}
