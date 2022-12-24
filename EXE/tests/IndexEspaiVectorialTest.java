package EXE.tests;

import FONTS.src.main.domain.classes.exceptions.*;
import org.junit.Test;

import FONTS.src.main.domain.classes.indexes.IndexEspaiVectorial;

import java.util.*;

import static org.junit.Assert.*;

public class IndexEspaiVectorialTest {

    @Test
    public void afegirDocument1() throws DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        iev.afegirDocument("/Disc/prova.ojmj", paraules);
        assertEquals("/Disc/prova.ojmj", iev.getAllDocuments().get(0));
    }

    @Test(expected = FormatNoReconegut.class)
    public void afegirDocument2() throws DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        iev.afegirDocument("/Disc/prova.ojmh", paraules);
    }

    @Test(expected = FormatNoReconegut.class)
    public void afegirDocument3() throws DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        iev.afegirDocument("/Disc/provaojmj", paraules);
    }

    @Test(expected = DocumentJaExisteix.class)
    public void afegirDocument4() throws DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        iev.afegirDocument("/Disc/prova.ojmj", paraules);
        iev.afegirDocument("/Disc/prova.ojmj", paraules);
    }

    @Test
    public void afegirDocument5() throws DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        iev.afegirDocument("/Disc/prova2.ojmj", paraules);
        ArrayList<String> res = new ArrayList<String>();
        res.add("/Disc/prova1.ojmj");
        res.add("/Disc/prova2.ojmj");
        assertEquals(res, iev.getAllDocuments());
    }

    @Test
    public void afegirDocument6() throws DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("avui");
        paraules.add("he");
        paraules.add("avançat");
        paraules.add("prop");
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        ArrayList<String> res = new ArrayList<String>();
        res.add("/Disc/prova1.ojmj");
        assertEquals(res, iev.getAllDocuments());
    }

    @Test
    public void eliminarDocument1() throws DocumentJaExisteix, FormatNoReconegut, NoExisteixDocument {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        iev.eliminarDocument("/Disc/prova1.ojmj");
        //Aprofito la variable paraules ja que es un ArrayList<String> sense cap element.
        assertEquals(paraules, iev.getAllDocuments());
    }

    @Test(expected = NoExisteixDocument.class)
    public void eliminarDocument2() throws NoExisteixDocument, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        iev.eliminarDocument("/Disc/prova1.ojmj");
    }

    @Test(expected = FormatNoReconegut.class)
    public void eliminarDocument3() throws NoExisteixDocument, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        iev.eliminarDocument("/Disc/prova1.ojm");
    }

    @Test(expected = FormatNoReconegut.class)
    public void eliminarDocument4() throws NoExisteixDocument, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        iev.eliminarDocument("/Disc/prova1ojmj");
    }

    @Test
    public void eliminarDocument5() throws DocumentJaExisteix, FormatNoReconegut, NoExisteixDocument {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        iev.afegirDocument("/Disc/prova2.ojmj", paraules);
        iev.eliminarDocument("/Disc/prova1.ojmj");
        ArrayList<String> res = new ArrayList<String>();
        res.add("/Disc/prova2.ojmj");
        assertEquals(res, iev.getAllDocuments());
    }

    @Test
    public void modificarDocument1() throws DocumentJaExisteix, FormatNoReconegut, NoExisteixDocument {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        ArrayList<String> noves_paraules = new ArrayList<String>();
        noves_paraules.add("sóc");
        noves_paraules.add("una");
        noves_paraules.add("prova");
        iev.modificarDocument("/Disc/prova1.ojmj", noves_paraules);
        assertEquals("/Disc/prova1.ojmj", iev.getDocumentsContenenParaula("sóc").get(0));
    }

    @Test(expected = NoExisteixDocument.class)
    public void modificarDocument2() throws FormatNoReconegut, NoExisteixDocument, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        iev.modificarDocument("/Disco/prova1.ojmj", paraules);
    }

    @Test(expected = FormatNoReconegut.class)
    public void modificarDocument3() throws FormatNoReconegut, NoExisteixDocument, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        iev.modificarDocument("/Disco/prova1.ojm", paraules);
    }

    @Test(expected = FormatNoReconegut.class)
    public void modificarDocument4() throws FormatNoReconegut, NoExisteixDocument, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        iev.modificarDocument("/Disco/prova1ojmj", paraules);
    }

    @Test
    public void modificarDocument5() throws DocumentJaExisteix, FormatNoReconegut, NoExisteixDocument {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        ArrayList<String> noves_paraules = new ArrayList<String>();
        noves_paraules.add("sóc");
        noves_paraules.add("una");
        noves_paraules.add("prova");
        iev.modificarDocument("/Disc/prova1.ojmj", noves_paraules);
        ArrayList<String> noves_paraules2 = new ArrayList<String>();
        noves_paraules2.add("jo");
        noves_paraules2.add("no");
        noves_paraules2.add("estic");
        iev.modificarDocument("/Disc/prova1.ojmj", noves_paraules2);
        ArrayList<String> no = new ArrayList<String>();
        assertEquals(no, iev.getDocumentsContenenParaula("sóc"));
        assertEquals("/Disc/prova1.ojmj", iev.getDocumentsContenenParaula("no").get(0));
    }

    @Test
    public void modificarDocument6() throws DocumentJaExisteix, FormatNoReconegut, NoExisteixDocument {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("ahir");
        paraules.add("vaig");
        paraules.add("fer");
        paraules.add("document");
        ArrayList<String> paraules2 = new ArrayList<String>();
        paraules2.add("ahir");
        paraules2.add("vaig");
        paraules2.add("fer");
        paraules2.add("document");
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        iev.afegirDocument("/Disc/prova2.ojmj", paraules2);
        assertEquals(2, iev.getDocumentsContenenParaula("document").size());
        ArrayList<String> paraules3 = new ArrayList<String>();
        paraules3.add("ahir");
        paraules3.add("vaig");
        paraules3.add("fer");
        iev.modificarDocument("/Disc/prova2.ojmj", paraules3);
        assertEquals(1, iev.getDocumentsContenenParaula("document").size());
    }

    @Test(expected = NumDocumentsIncorrecte.class)
    public void llistarKDocumentsSemblants1() throws NumDocumentsIncorrecte, NoExisteixDocument, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        iev.llistarKDocumentsSemblants(1, "/Disc/prova.ojmj", 0);
    }

    @Test(expected = NumDocumentsIncorrecte.class)
    public void llistarKDocumentsSemblants2() throws NumDocumentsIncorrecte, NoExisteixDocument, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        iev.llistarKDocumentsSemblants(-1, "/Disc/prova.ojmj", 0);
    }

    @Test(expected = NoExisteixDocument.class)
    public void llistarKDocumentsSemblants5() throws NumDocumentsIncorrecte, NoExisteixDocument, FormatNoReconegut, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        iev.afegirDocument("/Disc/prova.ojmj", new ArrayList<String>());
        iev.llistarKDocumentsSemblants(0, "/Disc/prova2.ojmj", 0);
    }

    @Test(expected = FormatNoReconegut.class)
    public void llistarKDocumentsSemblants6() throws NumDocumentsIncorrecte, NoExisteixDocument, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        iev.llistarKDocumentsSemblants(0, "/Disc/prova.pdf", 0);
    }

    @Test(expected = FormatNoReconegut.class)
    public void llistarKDocumentsSemblants7() throws NumDocumentsIncorrecte, NoExisteixDocument, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        iev.llistarKDocumentsSemblants(0, "/Disc/provaojmj", 0);
    }

    @Test
    public void llistarKDocumentsSemblants8() throws NumDocumentsIncorrecte, NoExisteixDocument, FormatNoReconegut, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("ahir");
        paraules.add("vaig");
        paraules.add("fer");
        paraules.add("document");
        ArrayList<String> paraules2 = new ArrayList<String>();
        paraules2.add("document");
        paraules2.add("vaig");
        paraules2.add("anar");
        paraules2.add("buscar");
        ArrayList<String> paraules3 = new ArrayList<String>();
        paraules3.add("fa");
        paraules3.add("bon");
        paraules3.add("dia");
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        iev.afegirDocument("/Disc/prova2.ojmj", paraules2);
        iev.afegirDocument("/Disc/prova3.ojmj", paraules3);
        assertEquals("/Disc/prova2.ojmj", iev.llistarKDocumentsSemblants(1, "/Disc/prova1.ojmj", 0).get(0));
    }

    @Test
    public void llistarKDocumentsSemblants9() throws NumDocumentsIncorrecte, NoExisteixDocument, FormatNoReconegut, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("soc");
        paraules.add("el");
        paraules.add("millor");
        paraules.add("document");
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        ArrayList<String> res = new ArrayList<String>();
        assertEquals(res, iev.llistarKDocumentsSemblants(0, "/Disc/prova1.ojmj", 0));
    }

    @Test
    public void llistarKDocumentsSemblants10() throws NumDocumentsIncorrecte, NoExisteixDocument, FormatNoReconegut, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("ahir");
        paraules.add("vaig");
        paraules.add("fer");
        paraules.add("document");
        ArrayList<String> paraules2 = new ArrayList<String>();
        paraules2.add("document");
        paraules2.add("vaig");
        paraules2.add("anar");
        paraules2.add("buscar");
        ArrayList<String> paraules3 = new ArrayList<String>();
        paraules3.add("fa");
        paraules3.add("bon");
        paraules3.add("dia");
        paraules3.add("dia");
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        iev.afegirDocument("/Disc/prova2.ojmj", paraules2);
        iev.afegirDocument("/Disc/prova3.ojmj", paraules3);
        ArrayList<String> res = new ArrayList<String>();
        res.add("/Disc/prova2.ojmj");
        res.add("/Disc/prova3.ojmj");
        assertEquals(res, iev.llistarKDocumentsSemblants(2, "/Disc/prova1.ojmj", 0));
    }

    @Test
    public void llistarKDocumentsSemblants11() throws NumDocumentsIncorrecte, NoExisteixDocument, FormatNoReconegut, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("ahir");
        paraules.add("vaig");
        paraules.add("fer");
        paraules.add("document");
        ArrayList<String> paraules2 = new ArrayList<String>();
        paraules2.add("document");
        paraules2.add("vaig");
        paraules2.add("anar");
        paraules2.add("buscar");
        ArrayList<String> paraules3 = new ArrayList<String>();
        paraules3.add("fa");
        paraules3.add("bon");
        paraules3.add("dia");
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        iev.afegirDocument("/Disc/prova2.ojmj", paraules2);
        iev.afegirDocument("/Disc/prova3.ojmj", paraules3);
        assertEquals("/Disc/prova2.ojmj", iev.llistarKDocumentsSemblants(1, "/Disc/prova1.ojmj", 1).get(0));
    }

    @Test
    public void llistarKDocumentsSemblants12() throws NumDocumentsIncorrecte, NoExisteixDocument, FormatNoReconegut, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("soc");
        paraules.add("el");
        paraules.add("millor");
        paraules.add("document");
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        ArrayList<String> res = new ArrayList<String>();
        assertEquals(res, iev.llistarKDocumentsSemblants(0, "/Disc/prova1.ojmj", 1));
    }

    @Test
    public void llistarKDocumentsSemblants13() throws NumDocumentsIncorrecte, NoExisteixDocument, FormatNoReconegut, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("ahir");
        paraules.add("vaig");
        paraules.add("fer");
        paraules.add("document");
        ArrayList<String> paraules2 = new ArrayList<String>();
        paraules2.add("document");
        paraules2.add("vaig");
        paraules2.add("anar");
        paraules2.add("buscar");
        ArrayList<String> paraules3 = new ArrayList<String>();
        paraules3.add("fa");
        paraules3.add("bon");
        paraules3.add("dia");
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        iev.afegirDocument("/Disc/prova2.ojmj", paraules2);
        iev.afegirDocument("/Disc/prova3.ojmj", paraules3);
        ArrayList<String> res = new ArrayList<String>();
        res.add("/Disc/prova2.ojmj");
        res.add("/Disc/prova3.ojmj");
        assertEquals(res, iev.llistarKDocumentsSemblants(2, "/Disc/prova1.ojmj", 1));
    }

    @Test(expected = NumDocumentsIncorrecte.class)
    public void llistarKDocumentsRellevants1() throws NumDocumentsIncorrecte {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        Set<String> query = new HashSet<String>();
        iev.llistarKDocumentsRellevants(1, query,0);
    }

    @Test(expected = NumDocumentsIncorrecte.class)
    public void llistarKDocumentsRellevants2() throws NumDocumentsIncorrecte {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        Set<String> query = new HashSet<String>();
        iev.llistarKDocumentsRellevants(-1, query,0);
    }

    @Test
    public void llistarKDocumentsRellevants5() throws NumDocumentsIncorrecte, DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        Set<String> query = new HashSet<String>();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("ahir");
        paraules.add("vaig");
        paraules.add("fer");
        paraules.add("mates");
        ArrayList<String> paraules2 = new ArrayList<String>();
        paraules2.add("document");
        paraules2.add("vaig");
        paraules2.add("anar");
        paraules2.add("buscar");
        ArrayList<String> paraules3 = new ArrayList<String>();
        paraules3.add("fa");
        paraules3.add("bon");
        paraules3.add("dia");
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        iev.afegirDocument("/Disc/prova2.ojmj", paraules2);
        iev.afegirDocument("/Disc/prova3.ojmj", paraules3);
        query.add("document");
        assertEquals("/Disc/prova2.ojmj", iev.llistarKDocumentsRellevants(1, query, 0).get(0));
    }

    @Test
    public void llistarKDocumentsRellevants6() throws NumDocumentsIncorrecte, FormatNoReconegut, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("ahir");
        paraules.add("vaig");
        paraules.add("fer");
        paraules.add("document");
        ArrayList<String> paraules2 = new ArrayList<String>();
        paraules2.add("document");
        paraules2.add("vaig");
        paraules2.add("anar");
        paraules2.add("buscar");
        ArrayList<String> paraules3 = new ArrayList<String>();
        paraules3.add("fa");
        paraules3.add("bon");
        paraules3.add("dia");
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        iev.afegirDocument("/Disc/prova2.ojmj", paraules2);
        iev.afegirDocument("/Disc/prova3.ojmj", paraules3);
        ArrayList<String> res = new ArrayList<String>();
        res.add("/Disc/prova1.ojmj");
        res.add("/Disc/prova2.ojmj");
        Set<String> query = new HashSet<String>();
        query.add("document");
        query.add("document");
        assertEquals(res, iev.llistarKDocumentsRellevants(2, query, 0));
    }

    @Test
    public void llistarKDocumentsRellevants7() throws NumDocumentsIncorrecte, FormatNoReconegut, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("ahir");
        paraules.add("vaig");
        paraules.add("fer");
        paraules.add("document");
        ArrayList<String> paraules2 = new ArrayList<String>();
        paraules2.add("document");
        paraules2.add("vaig");
        paraules2.add("anar");
        paraules2.add("buscar");
        ArrayList<String> paraules3 = new ArrayList<String>();
        paraules3.add("fa");
        paraules3.add("bon");
        paraules3.add("dia");
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        iev.afegirDocument("/Disc/prova2.ojmj", paraules2);
        iev.afegirDocument("/Disc/prova3.ojmj", paraules3);
        Set<String> query = new HashSet<String>();
        query.add("document");
        assertEquals("/Disc/prova1.ojmj", iev.llistarKDocumentsRellevants(1, query, 1).get(0));
    }

    @Test
    public void llistarKDocumentsRellevants9() throws NumDocumentsIncorrecte, FormatNoReconegut, DocumentJaExisteix {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("ahir");
        paraules.add("vaig");
        paraules.add("fer");
        paraules.add("document");
        ArrayList<String> paraules2 = new ArrayList<String>();
        paraules2.add("document");
        paraules2.add("vaig");
        paraules2.add("anar");
        paraules2.add("buscar");
        ArrayList<String> paraules3 = new ArrayList<String>();
        paraules3.add("fa");
        paraules3.add("bon");
        paraules3.add("dia");
        iev.afegirDocument("/Disc/prova1.ojmj", paraules);
        iev.afegirDocument("/Disc/prova2.ojmj", paraules2);
        iev.afegirDocument("/Disc/prova3.ojmj", paraules3);
        ArrayList<String> res = new ArrayList<String>();
        res.add("/Disc/prova2.ojmj");
        res.add("/Disc/prova1.ojmj");
        Set<String> query = new HashSet<String>();
        query.add("anar");
        query.add("document");
        assertEquals(res, iev.llistarKDocumentsRellevants(2, query, 1));
    }

    @Test
    public void getAllDocuments1() throws DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> res = new ArrayList<String>();
        assertEquals(res, iev.getAllDocuments());
    }

    @Test
    public void getAllDocuments2() throws DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> res = new ArrayList<String>();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("hola");
        paraules.add("adeu");
        iev.afegirDocument("/Disco/prova.ojmj", paraules);
        res.add("/Disco/prova.ojmj");
        assertEquals(res, iev.getAllDocuments());
    }

    @Test
    public void getAllDocuments3() throws DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> res = new ArrayList<String>();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("hola");
        paraules.add("adeu");
        iev.afegirDocument("/Disco/prova1.ojmj", paraules);
        iev.afegirDocument("/Disco/prova2.ojmj", paraules);
        iev.afegirDocument("/Disco/prova3.ojmj", paraules);
        res.add("/Disco/prova1.ojmj");
        res.add("/Disco/prova2.ojmj");
        res.add("/Disco/prova3.ojmj");
        assertEquals(res, iev.getAllDocuments());
    }

    @Test
    public void getDocumentsContenenParaula1() {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> l = new ArrayList<String>();
        assertEquals(l, iev.getDocumentsContenenParaula("hola"));
    }

    @Test
    public void getDocumentsContenenParaula2() throws DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("hola");
        iev.afegirDocument("/Disco/prova.ojmj", paraules);
        ArrayList<String> l = new ArrayList<String>();
        l.add("/Disco/prova.ojmj");
        assertEquals(l, iev.getDocumentsContenenParaula("hola"));
    }

    @Test
    public void getDocumentsContenenParaula3() throws DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("hola");
        ArrayList<String> paraules2 = new ArrayList<String>();
        paraules2.add("hola");
        iev.afegirDocument("/Disco/prova1.ojmj", paraules);
        iev.afegirDocument("/Disco/prova2.ojmj", paraules2);
        ArrayList<String> l = new ArrayList<String>();
        l.add("/Disco/prova1.ojmj");
        l.add("/Disco/prova2.ojmj");
        assertEquals(l, iev.getDocumentsContenenParaula("hola"));
    }

    @Test
    public void getDocumentsContenenParaula4() throws DocumentJaExisteix, FormatNoReconegut {
        IndexEspaiVectorial iev = new IndexEspaiVectorial();
        ArrayList<String> paraules = new ArrayList<String>();
        paraules.add("hola");
        ArrayList<String> paraules2 = new ArrayList<String>();
        paraules2.add("hola");
        ArrayList<String> paraules3 = new ArrayList<String>();
        paraules3.add("adeu");
        iev.afegirDocument("/Disco/prova1.ojmj", paraules);
        iev.afegirDocument("/Disco/prova2.ojmj", paraules2);
        iev.afegirDocument("/Disco/prova3.ojmj", paraules3);
        ArrayList<String> l = new ArrayList<String>();
        l.add("/Disco/prova1.ojmj");
        l.add("/Disco/prova2.ojmj");
        assertEquals(l, iev.getDocumentsContenenParaula("hola"));
    }

}