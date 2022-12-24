package FONTS.src.drivers;

import FONTS.src.main.domain.classes.exceptions.*;
import FONTS.src.main.domain.controllers.CtrlDomini;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Driver del Controlador de Domini.
 */
public class DriverDomini {
    /** Controlador de Domini */
    private CtrlDomini cd;
    /** Scanner per l'entrada de dades */
    private static Scanner in;

    /**
     * Creadora per defecte.
     */
    public DriverDomini() {
        cd = new CtrlDomini();
        in = new Scanner(System.in);
    }

    /**
     * Main
     * @param args Arguments.
     */
    public static void main(String[] args) {
        DriverDomini dd = new DriverDomini();
        System.out.println("---------------------------------------");
        System.out.println("Driver de Domini (PROP Grup 32.3).\n");
        System.out.println("Per veure les funcionalitats prem la tecla '0'.");

        int input = Integer.parseInt(in.nextLine());
        while (input != -1) {
            switch (input) {
                case 0: {
                    dd.mostrarMenu();
                    break;
                }
                case 1: {
                    dd.testAltaDocument();
                    break;
                }
                case 2: {
                    dd.testBaixaDocument();
                    break;
                }
                case 3: {
                    dd.testObrirDocument();
                    break;
                }
                case 4: {
                    dd.testTancarDocument();
                    break;
                }
                case 5: {
                    dd.testEditarAutor();
                    break;
                }
                case 6: {
                    dd.testEditarTitol();
                    break;
                }
                case 7: {
                    dd.testEditarContingut();
                    break;
                }
                case 8: {
                    dd.testLlistarAutorsPerPrefix();
                    break;
                }
                case 9: {
                    dd.testLlistarKDocumentsSemblantsD();
                    break;
                }
                case 10: {
                    dd.testLlistarKDocumentsRellevantsPParaules();
                    break;
                }
                case 11: {
                    dd.testLlistarTitolsAutors();
                    break;
                }
                case 12: {
                    dd.testLlistarTitolsPerExpressioGuardada();
                    break;
                }
                case 13: {
                    dd.testLlistarTitolsPerExpressioNoGuardada();
                    break;
                }
                case 14: {
                    dd.testLlistarContingutDocumentPerAutorTitol();
                    break;
                }
                case 15: {
                    dd.testAfegirExpressio();
                    break;
                }
                case 16: {
                    dd.testEliminarExpressio();
                    break;
                }
                case 17: {
                    dd.testModificarExpressio();
                    break;
                }
                case 18: {
                    dd.testConsultarExpressio();
                    break;
                }
                default: {
                    System.out.println("Comanda invàlida");
                    break;
                }
            }
            input = Integer.parseInt(in.nextLine());
        }
    }

    /**
     * Mostra les funcionalitats del Driver.
     */
    private void mostrarMenu() {
        System.out.println("Introdueix el número de l'esquerra per provar el mètode de la part dreta.");
        System.out.println("0  — Menú");
        System.out.println("1  — Alta Document");
        System.out.println("2  — Baixa Document");
        System.out.println("3  — Obrir Document");
        System.out.println("4  — Tancar Document");
        System.out.println("5  — Editar Autor Document");
        System.out.println("6  — Editar Títol Document");
        System.out.println("7  — Editar Contingut Document");
        System.out.println("8  — Llistar Autors Per Prefix");
        System.out.println("9  — Llistar K Documents Semblants a D");
        System.out.println("10 — Llistar K Documents Més Rellevants a P Paraules");
        System.out.println("11 — Llistar Titols Autors");
        System.out.println("12 — Llistar Documents Per Expressió Guardada");
        System.out.println("13 — Llistar Documents Per Expressió No Guardada");
        System.out.println("14 — Llistar Contingut Document Donat Títol i Autor");
        System.out.println("15 — Afegir Expressió Booleana");
        System.out.println("16 — Eliminar Expressió Booleana");
        System.out.println("17 — Modificar Expressió Booleana");
        System.out.println("18 — Consultar Expressió Booleana");
        System.out.println("-1  — Sortir");
    }

    /**
     * Test del mètode altaDocument.
     */
    public void testAltaDocument() {
        System.out.println("S'està provant l'alta d'un nou document.");
        System.out.print("Introdueix el nom de l'autor: ");
        String autor = in.nextLine();
        System.out.print("Introdueix el títol: ");
        String titol = in.nextLine();
        System.out.print("Introdueix el path: ");
        String path = in.nextLine();

        cd.AltaDocument(autor, titol, path + ".ojmj");
    }

    /**
     * Test del mètode baixaDocument.
     */
    public void testBaixaDocument() {
        System.out.println("S'està provant la baixa d'un document");
        System.out.print("Introdueix el path del document a eliminar: ");
        String path = in.nextLine();
        System.out.print("Vols eliminar el document completament del Sistema Operatiu? si/no: ");
        String decisio = in.nextLine();
        Boolean perm = decisio.equals("si");

        try {
            cd.BaixaDocument(path + ".ojmj", perm);
            System.out.println("El document " + path + " s'ha eliminat correctament.");
        }  catch (NoExisteixDocument e) {
            System.out.println("ERROR: El document " + path + " no existeix.");
        } catch (EliminarDocumentObert e) {
            System.out.println("ERROR: S'està intentant donar de baixa el document actualment obert.");
            System.out.println("Tanca primer el document per poder donar-lo de baixa.");
        } catch (FormatNoReconegut e) {
            System.out.println("ERROR: El format del document " + path + " no és acceptat pel gestor.");
        }
    }

    /**
     * Test del mètode obrirDocument.
     */
    public void testObrirDocument() {
        System.out.println("S'està provant el mètode d'obrir un document.");
        System.out.print("Insereix el path del document a obrir: ");
        String path = in.nextLine();
        path += ".ojmj";

        try {
            cd.ObrirDocument(path);
            System.out.println("El document " + path + " s'ha obert correctament.");
        } catch (NoExisteixDocument e) {
            System.out.println("ERROR: No existeix cap document amb path " + path + ".");
        }
    }

    /**
     * Test del mètode tancarDocument.
     */
    public void testTancarDocument() {
        System.out.println("S'està provant el mètode de tancar el document actualment obert.");

        try {
            ArrayList<String> rd = cd.getDocumentObert();
            cd.TancarDocument();
            System.out.println("El document " + rd.get(2) + " s'ha tancat correctament.");
        } catch (DocumentNoObert e) {
            System.out.println("ERROR: No hi ha cap document obert.");
        }
    }

    /**
     * Test del mètode editarAutor.
     */
    public void testEditarAutor() {
        System.out.println("S'està provant l'edició de l'autor d'un document.");
        System.out.print("Introdueix el títol del document: ");
        String titol = in.nextLine();
        System.out.print("Introdueix el nom de l'autor del document a modificar: ");
        String autorAnterior = in.nextLine();
        System.out.print("Introdueix el nom del nou autor del document: ");
        String nouAutor = in.nextLine();

        try {
            cd.EditarAutor(autorAnterior, nouAutor, titol);
            System.out.println("L'autor del document s'ha canviat correctament a " + nouAutor + ".");
        } catch (DocumentJaExisteix e) {
            System.out.println("ERROR: Ja existeix un document amb títol " + titol + " i autor " + nouAutor + ".");
        } catch (NoExisteixDocument e) {
            System.out.println("ERROR: No existeix un document amb títol " + titol + " i autor " + autorAnterior + ".");
        }
    }

    /**
     * Test del mètode editarTitol.
     */
    public void testEditarTitol() {
        System.out.println("S'està provant l'edició del titol d'un document.");
        System.out.print("Introdueix el nou nom del document: ");
        String nouTitol = in.nextLine();
        System.out.print("Introdueix el nom antic del document: ");
        String titolAnterior = in.nextLine();
        System.out.print("Introdueix el nom de l'autor del document: ");
        String autor = in.nextLine();

        try {
            cd.EditarTitol(nouTitol, titolAnterior, autor);
            System.out.println("El títol del document s'ha canviat correctament a " + nouTitol + ".");
        } catch (DocumentJaExisteix e) {
            System.out.println("ERROR: Ja existeix un document amb títol " + nouTitol + " i autor " + autor + ".");
        } catch (NoExisteixDocument e) {
            System.out.println("ERROR: No existeix un document amb títol " + titolAnterior + " i autor " + autor + ".");
        }
    }

    /**
     * Test del mètode editarContingut.
     */
    public void testEditarContingut() {
        System.out.println("S'està provant la modificació del contingut del document actualment obert.");
        System.out.println("Insereix el nou contingut:");
        String contingut = in.nextLine();

        try {
            cd.EditarContingut(contingut);
            ArrayList<String> rd = cd.getDocumentObert();
            System.out.println("El contingut del document " + rd.get(2) + " s'ha modificat correctament.");
        } catch (DocumentNoObert e) {
            System.out.println("ERROR: No hi ha cap document obert.");
        }
    }

    /**
     * Test del mètode llistarAutorsPerPrefix.
     */
    public void testLlistarAutorsPerPrefix() {
        System.out.println("S'està provant la cerca d'autors per prefix.");
        System.out.print("Introdueix el prefix a cercar: ");
        String prefix = in.nextLine();

        ArrayList<ArrayList<String>> arr = cd.LlistarAutorsPerPrefix(prefix);
        System.out.println("S'han trobat " + arr.size() + " autors.");
        System.out.println("Es mostraran els autors ordenats alfabèticament.");
        for (ArrayList<String> aux : arr) {
            System.out.println("Nom: " + aux.get(0) + ", Títols: " + aux.get(1) + ".");
        }
    }

    /**
     * Test del mètode llistarKDocumentsSemblantsD.
     */
    public void testLlistarKDocumentsSemblantsD() {
        System.out.println("S'està provant la consulta dels K documents més semblants a un document D.");
        System.out.print("Introdueix el nombre de documents K: ");
        int k = Integer.parseInt(in.nextLine());
        System.out.print("Introdueix el path del document D: ");
        String path = in.nextLine();
        int metode = -1;
        while (metode != 0 && metode != 1) {
            System.out.print("Escolleix si vols utilitzar el mètode de TFIDF (escriu 0) o BM25 (escriu 1): ");
            metode = Integer.parseInt(in.nextLine());
        }

        try {
            ArrayList<ArrayList<String>> arr = cd.LlistarKDocumentsSemblantsD(k, path + ".ojmj", metode);
            System.out.println("S'han trobat " + arr.size() + " documents:");
            for (ArrayList<String> aux : arr) {
                System.out.println("Títol: " + aux.get(1) + ", Autor: " + aux.get(0) + ".");
            }
        } catch (NoExisteixDocument e) {
            System.out.println("ERROR: No existeix cap document amb path " + path + ".");
        } catch (FormatNoReconegut e) {
            System.out.println("ERROR: El format de l'arxiu " + path + " no és acceptat pel gestor.");
        } catch (NumDocumentsIncorrecte e) {
            System.out.println("ERROR: El nombre de documents K introduït no és correcte.");
        }
    }

    /**
     * Test del mètode llistarKDocumentsRellevantsPParaules.
     */
    public void testLlistarKDocumentsRellevantsPParaules() {
        System.out.println("S'està provant la consulta de K documents rellevants a P paraules.");
        System.out.print("Introdueix el nombre de documents K: ");
        int k = Integer.parseInt(in.nextLine());
        System.out.print("Introdueix el nombre de paraules P: ");
        int p = Integer.parseInt(in.nextLine());
        TreeSet<String> conjParaules = new TreeSet<String>();
        System.out.println("Introdueix el conjunt de " + p + " paraules:");
        for (int i = 0; i < p; ++i) {
            String paraula = in.nextLine();
            conjParaules.add(paraula);
        }
        int metode = -1;
        while (metode != 0 && metode != 1) {
            System.out.print("Escolleix si vols utilitzar el mètode de TFIDF (escriu 0) o BM25 (escriu 1): ");
            metode = Integer.parseInt(in.nextLine());
        }
        try {
            ArrayList<ArrayList<String>> arr = cd.LlistarKDocumentsRellevantsPParaules(k, conjParaules, metode);
            System.out.println("S'han trobat " + arr.size() + " documents:");
            for (ArrayList<String> aux : arr) {
                System.out.println("Títol: " + aux.get(1) + ", Autor: " + aux.get(0) + ".");
            }
        } catch (NumDocumentsIncorrecte e) {
            System.out.println("ERROR: El nombre de documents K a cercar és incorrecte.");
        }
    }

    /**
     * Test del mètode llistarTitolsAutors.
     */
    public void testLlistarTitolsAutors() {
        System.out.println("S'està provant la cerca de documents donat el nom d'un autor.");
        System.out.print("Introdueix el nom de l'autor a cercar: ");
        String autor = in.nextLine();

        try {
            ArrayList<ArrayList<String>> arr = cd.LlistarTitolsAutors(autor);
            System.out.println("L'autor amb nom " + autor + " té un total de " + arr.size() + " títols.");
            for (ArrayList<String> aux : arr) {
                System.out.println("Títol: " + aux.get(0) + ", Path: " + aux.get(1));
            }
        } catch (NoExisteixAutor e) {
            System.out.println("ERROR: L'autor amb nom " + autor + " no existeix.");
        }
    }

    /**
     * Test del mètode llistarTitolsPerExpressioGuardada.
     */
    public void testLlistarTitolsPerExpressioGuardada() {
        System.out.println("S'està provant la cerca de documents donat el nom d'una expressió booleana guardada.");
        System.out.print("Introdueix el nom de l'expressió a cercar: ");
        String nom = in.nextLine();

        try {
            ArrayList<ArrayList<String>> arr = cd.LlistarTitolsPerExpressioGuardada(nom);
            System.out.println("S'han trobat " + arr.size() + " resultats:");
            for (ArrayList<String> aux : arr) {
                System.out.println("Títol: " + aux.get(1) + ", Autor: " + aux.get(0) + ".");
            }
        } catch (NoExisteixExpressio e) {
            System.out.println("ERROR: No existeix cap expressió guardada amb nom " + nom + ".");
        }
    }

    /**
     * Test del mètode llistarTitolsPerExpressioNoGuardada.
     */
    public void testLlistarTitolsPerExpressioNoGuardada() {
        System.out.println("S'està provant la cerca de documents donada una expressió booleana introduïda per l'usuari.");
        System.out.print("Introdueix l'expressió a cercar: ");
        String exp = in.nextLine();

        try {
            ArrayList<ArrayList<String>> arr = cd.LlistarTitolsPerExpressioNoGuardada(exp);
            System.out.println("S'han trobat " + arr.size() + " resultats:");
            for (ArrayList<String> aux : arr) {
                System.out.println("Títol: " + aux.get(1) + ", Autor: " + aux.get(0) + ".");
            }
        } catch (ExpressioNoValida e) {
            System.out.println("ERROR: L'expressió " + exp + " no és vàlida.");
        }
    }

    /**
     * Test del mètode llistarContingutDocumentPerAutorTitol.
     */
    public void testLlistarContingutDocumentPerAutorTitol() {
        System.out.println("S'està provant la consulta d'un document donat el seu títol i autor.");
        System.out.print("Introdueix el nom de l'autor: ");
        String autor = in.nextLine();
        System.out.print("Introdueix el nom del títol: ");
        String titol = in.nextLine();

        try {
            ArrayList<String> arr = cd.LlistarContingutDocumentPerAutorTitol(autor, titol);
            for (String s : arr) {
                System.out.print(s + " ");
            }
            System.out.println();
        } catch (NoExisteixDocument e) {
            System.out.println("ERROR: No existeix cap document amb títol " + titol + " i autor " + autor + ".");
        }
    }

    /**
     * Test del mètode afegirExpressio.
     */
    public void testAfegirExpressio() {
        System.out.println("S'està provant l'addició d'una nova expressió.");
        System.out.print("Introdueix el nom de la nova expressió: ");
        String nom = in.nextLine();
        System.out.print("Introdueix la nova expressió: ");
        String exp = in.nextLine();

        try {
            cd.AfegirExpressio(nom, exp);
            System.out.println("Expressió afegida correctament.");
        } catch (JaExisteixExpressio e) {
            System.out.println("ERROR: Ja existeix una expressió amb nom " + nom + ".");
        } catch (ExpressioNoValida e) {
            System.out.println("ERROR: L'expressió " + exp + " no és vàlida.");
        }
    }

    /**
     * Test del mètode eliminarExpressio.
     */
    public void testEliminarExpressio() {
        System.out.println("S'està provant l'eliminació d'una expressió guardada.");
        System.out.print("Introdueix el nom de l'expressió a eliminar: ");
        String nom = in.nextLine();

        try {
            cd.EliminarExpressio(nom);
            System.out.println("Expressió eliminada correctament.");
        } catch (NoExisteixExpressio e) {
            System.out.println("ERROR: L'expressió amb nom " + nom + " no existeix.");
        }
    }

    /**
     * Test del mètode modificarExpressio.
     */
    public void testModificarExpressio() {
        System.out.println("S'està provant la modificació d'una expressió guardada.");
        System.out.print("Introdueix el nom de l'expressió a modificar: ");
        String nom = in.nextLine();
        System.out.print("Introdueix la nova expressió: ");
        String exp = in.nextLine();

        try {
            cd.ModificarExpressio(nom, exp);
            System.out.println("Expressió modificada correctament.");
        } catch (NoExisteixExpressio e) {
            System.out.println("ERROR: L'expressió amb nom " + nom + " no existeix.");
        } catch (ExpressioNoValida e) {
            System.out.println("ERROR: L'expressió " + exp + " no es vàlida.");
        }
    }

    /**
     * Test del mètode consultarExpressio.
     */
    public void testConsultarExpressio() {
        System.out.println("S'està provant la consulta d'una expressió guardada.");
        System.out.print("Introdueix el nom de l'expressió a consultar: ");
        String nom = in.nextLine();

        try {
            String exp = cd.ConsultarExpressio(nom);
            System.out.println("L'expressió " + nom + " té com a contingut: " + exp);
        } catch (NoExisteixExpressio e) {
            System.out.println("ERROR: L'expressió amb nom " + nom + " no existeix.");
        }
    }
}
