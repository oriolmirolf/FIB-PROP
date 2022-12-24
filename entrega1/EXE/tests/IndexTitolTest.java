package EXE.tests;

import FONTS.src.main.domain.classes.exceptions.*;
import FONTS.src.main.domain.classes.indexes.IndexTitol;
import java.util.TreeMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jeremy
 */
public class IndexTitolTest {
    
    public IndexTitolTest() {
    }
    

    /**
     * Objecte de la prova: Test del mètode afegirTitol(autor) de IndexTitol
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexTitol.
     * Operativa: Creem un IndexTitol i afegim un títol, posteriorment comprovem si el títol esta en l'índex.
     */
    @Test
    public void testAfegirTitol() throws Exception {
        System.out.println("afegirTitol");
        String autor = "Juan";
        String titol = "EL MAN";
        String path = "EL WOMAN.ojmj";
        IndexTitol instance = new IndexTitol();
        instance.afegirTitol(autor, titol, path);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(instance.existeixAutor(autor));
    }

   /**
     * Objecte de la prova: Test del mètode getTitols(autor) de IndexTitol.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexTitol.
     * Operativa: Creem un IndexTitol, afegim un títol i finalment fem una consulta on no existeix un autor per fer executar l'excepció.
     */
    @Test(expected=NoExisteixAutor.class)
    public void testGetTitols() throws Exception {
        System.out.println("getTitols");
        String autor = "Juan";
        String titol = "EL MAN";
        String path = "EL WOMAN.ojmj";
        IndexTitol instance = new IndexTitol();
        instance.afegirTitol(autor, titol, path);
        TreeMap<String, String> result = instance.getTitols("maria");
    }
    
    /**
     * Objecte de la prova: Test del mètode getTitols(autor) de IndexTitol.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexTitol.
     * Operativa: Creem un IndexTitol, afegim un títol i finalment fem una consulta on si existeix l'autor i comprovem si són els mateixos.
     */
    @Test
    public void testGetTitols2() throws Exception {
        System.out.println("getTitols2");
        String autor = "Juan";
        String titol = "EL MAN";
        String path = "EL WOMAN.ojmj";
        IndexTitol instance = new IndexTitol();
        instance.afegirTitol(autor, titol, path);
        instance.afegirTitol(autor,"hola", "hola.ojmj");
        TreeMap<String, String> result = instance.getTitols(autor);
        TreeMap<String, String> comp = new TreeMap<>();
        comp.put(titol,path);
        comp.put("hola","hola.ojmj");
        assertEquals(comp,result);

    }
    
     /**
     * Objecte de la prova: Test del mètode getTitol(autor, titol) de IndexTitol.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexTitol.
     * Operativa: Creem un IndexTitol, afegim un títol i finalment fem una consulta on si existeix l'autor i no el títol. Així, executem l'excepció.
     */
    @Test(expected=NoExisteixDocument.class)
    public void testGetTitol() throws Exception {
        System.out.println("getTitol");
        String autor = "Juan";
        String titol = "EL MAN";
        String path = "EL WOMAN.ojmj";
        IndexTitol instance = new IndexTitol();
        instance.afegirTitol(autor, titol, path);
        String result = instance.getTitol(autor, "El wOMAN");
    }
    
    /**
     * Objecte de la prova: Test del mètode modificarAutor(anticAutor, nouAutor, titol) de IndexTitol.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexTitol.
     * Operativa: Creem un IndexTitol, afegim un títol i canviem el nom de l'autor introduït, finalment comprovem que no existeix antic autor i existeix nouAutor.
     */

    @Test
    public void testModificarAutor() throws Exception {
        System.out.println("modificarAutor");
        String autor = "Juan";
        String titol = "EL MAN";
        String path = "EL WOMAN.ojmj";
        IndexTitol instance = new IndexTitol();
        instance.afegirTitol(autor, titol, path);
        instance.modificarAutor(autor, "Pedro",titol);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(instance.existeixAutor("Pedro"));
        assertFalse(instance.existeixAutor(autor));
    }
    
    /**
     * Objecte de la prova: Test del mètode modificarAutor(autor, antic, noutitol) de IndexTitol.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexTitol.
     * Operativa: Creem un IndexTitol, afegim un títol i canviem el titol. Finalment comprovem si el nouTitol está asociat al Document.
     */
    @Test
    public void testModificarTitol() {
        try{
            System.out.println("modificarTitol");
        String autor = "Juan";
        String antic_titol = "EL MAN";
        String nou_titol = "EL WOMAN";
        String path = "si.ojmj";
        IndexTitol instance = new IndexTitol();
        instance.afegirTitol(autor, antic_titol, path);
        instance.modificarTitol(nou_titol, antic_titol, autor);
        
        assertEquals(path, instance.getTitol(autor, nou_titol));
        }
        catch(Exception e){
            System.out.println(e);
        }
       
    }
/**
     * Objecte de la prova: Test del mètode modificarAutor(autor, antic, noutitol) de IndexTitol.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexTitol.
     * Operativa: Creem un IndexTitol, afegim un títol i canviem el titol. Finalment comprovem si el nouTitol no está asociat a un altre autor i executem l'excepció.
     */
    @Test(expected = NoExisteixAutor.class)
    public void testModificarTitol2() throws Exception{

            System.out.println("modificarTitol2");
            String autor = "Juan";
            String antic_titol = "EL MAN";
            String nou_titol = "EL WOMAN";
            String path = "si.ojmj";
            IndexTitol instance = new IndexTitol();
            instance.afegirTitol(autor, antic_titol, path);
            instance.modificarTitol(nou_titol, antic_titol, autor);

            assertEquals(path, instance.getTitol("pedro", nou_titol));


    }
   
   /**
     * Objecte de la prova: Test del mètode eliminarTitol(autor, titol) de IndexTitol.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexTitol.
     * Operativa: Creem un IndexTitol, afegim un títol i posteriorment l'eliminem. Finalment comprovem si no existeix l'autor
     */
    @Test
    public void testEliminarTitol() throws Exception {
        System.out.println("eliminarTitol");
        String autor = "Juan";
        String titol = "EL MAN";
        String path = "EL WOMAN.ojmj";
        
        IndexTitol instance = new IndexTitol();
        instance.afegirTitol(autor, titol, path);
        instance.eliminarTitol(autor, titol);
        // TODO review the generated test code and remove the default call to fail.
        assertFalse(instance.existeixAutor(autor));
    }
      /**
     * Objecte de la prova: Test del mètode eliminarTitol(autor, titol) de IndexTitol.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexTitol.
     * Operativa: Creem un IndexTitol, afegim dos títols que correponen al mateix autor i posteriorment eliminem un. Finalment comprovem si existeix l'autor
     */
    @Test
    public void testEliminarTitol2() throws Exception {
        System.out.println("eliminarTitol2");
        String autor = "Juan";
        String titol = "EL MAN";
        String path = "EL WOMAN.ojmj";

        IndexTitol instance = new IndexTitol();
        instance.afegirTitol(autor, titol, path);
        instance.afegirTitol(autor, "hola", "hola.ojmj");
        instance.eliminarTitol(autor, titol);

        // TODO review the generated test code and remove the default call to fail.
        assertTrue(instance.existeixAutor(autor));
    }

    
}
