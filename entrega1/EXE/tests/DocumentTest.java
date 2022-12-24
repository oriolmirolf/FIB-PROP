package EXE.tests;

import org.junit.Test;
import FONTS.src.main.domain.classes.individual_classes.Document;

import java.util.ArrayList;
import java.time.*;
import java.util.HashSet;

import static org.junit.Assert.*;

public class DocumentTest {

    @Test
    public void Document() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        assertEquals("/Disc/PROP/arxiu.ojmj", d.getLocalitzacio());
        assertEquals("marc", d.getAutor());
        assertEquals("Prova de prop", d.getTitol());
        assertEquals(LocalDate.now(), d.getDataCreacio());
        assertEquals(LocalDate.now(), d.getDataDarreraModificacio());
        assertEquals(new ArrayList<String>(), d.getContingut());
    }

    @Test
    public void canviarTitolDoc() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        d.canviarTitolDoc("Planetes del Sistema Solar");
        assertEquals("Planetes del Sistema Solar", d.getTitol());
    }

    @Test
    public void canviarAutorDoc() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        d.canviarAutorDoc("Jeremy");
        assertEquals("Jeremy", d.getAutor());
    }

    @Test
    public void modificarContingut() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        ArrayList<String> l = new ArrayList<String>();
        l.add("Fent"); l.add("testos"); l.add("de"); l.add("prop"); l.add("!");
        d.modificarContingut(l);
        ArrayList<String> l2 = new ArrayList<String>();
        l2.add("Fent"); l2.add("testos"); l2.add("de"); l2.add("prop"); l2.add("!");
        assertEquals(l2, d.getContingut());
    }

    @Test
    public void getTitol() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        assertEquals("Prova de prop", d.getTitol());
    }

    @Test
    public void getAutor() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        assertEquals("marc", d.getAutor());
    }

    @Test
    public void getLocalitzacio() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        assertEquals("/Disc/PROP/arxiu.ojmj", d.getLocalitzacio());
    }

    @Test
    public void getDataCreacio() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        assertEquals(LocalDate.now(), d.getDataCreacio());
    }

    @Test
    public void getDataDarreraModificacio() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        ArrayList<String> l = new ArrayList<String>();
        l.add("Fent"); l.add("testos"); l.add("de"); l.add("prop"); l.add("!");
        d.modificarContingut(l);
        assertEquals(LocalDate.now(), d.getDataDarreraModificacio());
    }

    @Test
    public void getContingut1() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        ArrayList<String> l1 = new ArrayList<String>();
        assertEquals(l1, d.getContingut());
    }

    @Test
    public void getContingut2() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        ArrayList<String> l1 = new ArrayList<String>();
        assertEquals(l1, d.getContingut());
        l1.add("Un"); l1.add("dia"); l1.add("al"); l1.add("bosc"); l1.add(".");
        d.modificarContingut(l1);
        ArrayList<String> l2 = new ArrayList<String>();
        l2.add("Un"); l2.add("dia"); l2.add("al"); l2.add("bosc"); l2.add(".");
        assertEquals(l2, d.getContingut());
    }

    @Test
    public void getContingut3() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        ArrayList<String> l1 = new ArrayList<String>();
        assertEquals(l1, d.getContingut());
        l1.add("Un"); l1.add("dia"); l1.add("al"); l1.add("bosc"); l1.add(".");
        d.modificarContingut(l1);
        ArrayList<String> l2 = new ArrayList<String>();
        l2.add("Un"); l2.add("dia"); l2.add("fent"); l2.add("prop"); l2.add(".");
        d.modificarContingut(l2);
        ArrayList<String> l3 = new ArrayList<String>();
        l3.add("Un"); l3.add("dia"); l3.add("fent"); l3.add("prop"); l3.add(".");
        assertEquals(l3, d.getContingut());
    }

    @Test
    public void conteParaula() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        ArrayList<String> l1 = new ArrayList<String>();
        l1.add("Un"); l1.add("dia"); l1.add("al"); l1.add("bosc"); l1.add(".");
        d.modificarContingut(l1);
        assertEquals(true, d.conteParaula("Un"));
        assertEquals(true, d.conteParaula("dia"));
        assertEquals(true, d.conteParaula("al"));
        assertEquals(true, d.conteParaula("bosc"));
        assertEquals(true, d.conteParaula("."));
        assertEquals(false, d.conteParaula("no"));
        assertEquals(false, d.conteParaula("àdàà`d$·$"));

        ArrayList<String> l2 = new ArrayList<String>();
        l2.add("Demà"); l2.add("farà"); l2.add("fred"); l2.add("!");
        d.modificarContingut(l2);
        assertEquals(false, d.conteParaula("Un"));
        assertEquals(false, d.conteParaula("dia"));
        assertEquals(false, d.conteParaula("al"));
        assertEquals(false, d.conteParaula("bosc"));
        assertEquals(false, d.conteParaula("."));
        assertEquals(true, d.conteParaula("Demà"));
        assertEquals(true, d.conteParaula("farà"));
        assertEquals(true, d.conteParaula("fred"));
        assertEquals(true, d.conteParaula("!"));
    }

    @Test
    public void getPosicionsParaula1() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        assertEquals(new HashSet<Integer>(), d.getPosicionsParaula("hola"));
    }

    @Test
    public void getPosicionsParaula2() {
        Document d = new Document("/Disc/PROP/arxiu.ojmj", "marc","Prova de prop");
        ArrayList<String> l1 = new ArrayList<String>();
        l1.add("Un"); l1.add("dia"); l1.add("al"); l1.add("bosc"); l1.add(".");
        l1.add("Un"); l1.add("dia"); l1.add("a"); l1.add("casa"); l1.add(".");
        d.modificarContingut(l1);
        HashSet<Integer> aparicionsUn = new HashSet<Integer>();
        aparicionsUn.add(0);
        aparicionsUn.add(5);
        HashSet<Integer> aparicionsdia = new HashSet<Integer>();
        aparicionsdia.add(1);
        aparicionsdia.add(6);
        HashSet<Integer> aparicionsal = new HashSet<Integer>();
        aparicionsal.add(2);
        HashSet<Integer> aparicionsbosc = new HashSet<Integer>();
        aparicionsbosc.add(3);
        HashSet<Integer> aparicionspunt = new HashSet<Integer>();
        aparicionspunt.add(4);
        aparicionspunt.add(9);
        HashSet<Integer> aparicionsa = new HashSet<Integer>();
        aparicionsa.add(7);
        HashSet<Integer> aparicionscasa = new HashSet<Integer>();
        aparicionscasa.add(8);
        assertEquals(aparicionsUn, d.getPosicionsParaula("Un"));
        assertEquals(aparicionsdia, d.getPosicionsParaula("dia"));
        assertEquals(aparicionsal, d.getPosicionsParaula("al"));
        assertEquals(aparicionsbosc, d.getPosicionsParaula("bosc"));
        assertEquals(aparicionspunt, d.getPosicionsParaula("."));
        assertEquals(aparicionsa, d.getPosicionsParaula("a"));
        assertEquals(aparicionscasa, d.getPosicionsParaula("casa"));
    }
}
