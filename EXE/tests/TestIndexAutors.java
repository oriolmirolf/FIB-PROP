package EXE.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import FONTS.src.main.domain.classes.indexes.IndexAutors;
import java.util.ArrayList;

import FONTS.src.main.domain.classes.exceptions.JaExisteixAutor;
import FONTS.src.main.domain.classes.exceptions.NoExisteixAutor;

/**
 * Classe de testeig de IndexAutors.java
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class TestIndexAutors {

    /**
     * Objecte de la prova: Test del mètode afegirAutor(autor) de IndexAutors.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexAutors.
     * Operativa: Creem un IndexAutors, s'afegeix un autor amb el mètode afegirAutor(autor)
     * i es comprova que s'ha afegit correctament.
     */
    @Test
    public void testAfegirAutor() throws Exception {
        IndexAutors index = new IndexAutors();
        index.afegirAutor("autor");
        ArrayList<String> arr = index.cercarAutors("a");
        assertEquals("autor", arr.get(0));
    }

    /**
     * Objecte de la prova: Test del mètode afegirAutor(autor) de IndexAutors.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexAutors.
     * Operativa: Es crea un objecte IndexAutors, s'afegeixen dos autors amb el mateix nom amb el mètode
     * afegirAutor(autor) i es comprova que salta l'excepció.
     */
    @Test(expected = JaExisteixAutor.class)
    public void testAfegirAutorExistent() throws Exception {
        IndexAutors index = new IndexAutors();
        index.afegirAutor("autor");
        index.afegirAutor("autor");
    }

    /**
     * Objecte de la prova: Test del mètode eliminarAutor(autor) de IndexAutors.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexAutors.
     * Operativa: Es crea un objecte IndexAutors, s'afegeix un autor amb el mètode afegirAutor(autor)
     * i s'elimina el mateix autor amb el mètode eliminarAutor(autor), i es comprova que s'ha eliminat correctament.
     */
    @Test
    public void testEliminarAutor() throws Exception {
        IndexAutors index = new IndexAutors();
        index.afegirAutor("autor");
        index.eliminarAutor("autor");
        ArrayList<String> arr = index.cercarAutors("autor");
        assertTrue(arr.isEmpty());
    }

    /**
     * Objecte de la prova: Test del mètode eliminarAutor(autor) de IndexAutors.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexAutors.
     * Operativa: Es crea un objecte IndexAutors, s'afegeixen autors amb el mètode afegirAutor(autor)
     * i s'elimina un autor inexistent amb el mètode eliminarAutor(autor) i es comprova que salta l'excepció.
     */
    @Test(expected = NoExisteixAutor.class)
    public void testEliminarAutorNoExistent() throws Exception {
        IndexAutors index = new IndexAutors();
        index.afegirAutor("autora"); index.afegirAutor("auto");
        index.eliminarAutor("autor");
    }

    /**
     * Objecte de la prova: Test del mètode modificarAutor(autor1, autor2) de IndexAutors.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexAutors.
     * Operativa: Es crea un objecte IndexAutors, es crea un autor amb el mètode afegirAutor(autor) i es modifica el
     * seu nom amb el mètode modificarAutor(autor1, autor2) i es comprova que s'ha modificat correctament.
     */
    @Test
    public void testModificarAutor() throws Exception {
        IndexAutors index = new IndexAutors();
        index.afegirAutor("autor");
        index.modificarAutor("autor", "nouAutor");
        ArrayList<String> arr1 = index.cercarAutors("nouAutor");
        assertEquals("nouAutor", arr1.get(0));
        ArrayList<String> arr2 = index.cercarAutors("autor");
        assertTrue(arr2.isEmpty());
    }

    /**
     * Objecte de la prova: Test del mètode modificarAutor(autor1, autor2) de IndexAutors.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexAutors.
     * Operativa: Es crea un objecte IndexAutors, s'afegeixen uns autors i s'intenta modificar el nom d'un autor
     * inexistent amb el mètode modificarAutor(autor1, autor2) i es comprova que salta l'excepció.
     */
    @Test(expected = NoExisteixAutor.class)
    public void testModificarAutorNoExistent() throws Exception {
        IndexAutors index = new IndexAutors();
        index.afegirAutor("autora"); index.afegirAutor("auto");
        index.modificarAutor("autor", "nouAutor");
    }

    /**
     * Objecte de la prova: Test del mètode modificarAutor(autor1, autor2) de IndexAutors.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexAutors.
     * Operativa: Es crea un objecte IndexAutors, s'afegeixen uns autors i s'intenta modificar el nom d'un autor
     * existent al nom d'un altre autor existent amb el mètode modificarAutor(autor1, autor2) i es comprova que salta l'excepció.
     */
    @Test(expected = JaExisteixAutor.class)
    public void testModificarAutorAAutorJaExistent() throws Exception {
        IndexAutors index = new IndexAutors();
        index.afegirAutor("autora"); index.afegirAutor("auto");
        index.modificarAutor("autora", "auto");
    }

    /**
     * Objecte de la prova: Test del mètode cercarAutors(prefix) de IndexAutors.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexAutors.
     * Operativa: Es crea un objecte IndexAutors, es creen autors amb el mètode afegirAutor(autor)
     * i es comprova que el mètode cercarAutors(prefix) troba els autors corresponents.
     */
    @Test
    public void testCercarAutor() throws Exception {
        IndexAutors index = new IndexAutors();
        index.afegirAutor("autor"); index.afegirAutor("autores");
        index.afegirAutor("pilota"); index.afegirAutor("a");
        ArrayList<String> arr = index.cercarAutors("au");
        assertEquals(2, arr.size());
    }

    /**
     * Objecte de la prova: Test del mètode cercarAutors(prefix) de IndexAutors.
     * Fitxers de dades necessaris: Dades introduïdes manualment. No calen fitxers addicionals.
     * Valors estudiats: Estratègia caixa gris. Es crea un nou objecte IndexAutors.
     * Operativa: Es crea un objecte IndexAutors, es creen autors amb el mètode afegirAutor(autor)
     * i es comprova que el mètode cercarAutors(prefix) no troba cap autor.
     */
    @Test
    public void testCercarAutorNoExistent() throws Exception {
        IndexAutors index = new IndexAutors();
        index.afegirAutor("autor"); index.afegirAutor("autores");
        index.afegirAutor("pilota"); index.afegirAutor("a");
        ArrayList<String> arr = index.cercarAutors("messi");
        assertTrue(arr.isEmpty());
    }
}
