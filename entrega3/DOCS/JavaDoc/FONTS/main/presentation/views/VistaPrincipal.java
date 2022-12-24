package FONTS.src.main.presentation.views;

import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe de la Vista Principal de l'Aplicació.
 * @author Joan Caballero Castro (joan.caballero@estudiantat.upc.edu)
 */
public class VistaPrincipal extends JFrame {
    /** Barra de menús que inclou la majoria de funcionalitats del gestor classificades en menús desplegables */
    private JMenuBar menuBar = new JMenuBar();


    /** Menú principal de Documents */
    private JMenu mDocuments = new JMenu("Documents");
    /** Ítem del menú desplegable de Documents per a donar d'alta un Document */
    private JMenuItem bAltaDocument = new JMenuItem("Alta Document");
    /** Ítem del menú desplegable de Documents per a obrir un Document */
    private JMenuItem bObrirDocument = new JMenuItem("Obrir Document");
    /** Ítem del menú desplegable de Documents per a importar un Document */
    private JMenuItem bImportarDocument = new JMenuItem("Importar Document");
    /** Ítem del menú desplegable de Documents per a exportar un Document */
    private JMenuItem bExportarDocument = new JMenuItem("Exportar Document");
    /** Ítem del menú desplegable de Documents per a donar de baixa un Document */
    private JMenuItem bBaixaDocument = new JMenuItem("Baixa Document");
    /** Ítem del menú desplegable de Documents per a editar el títol i l'autor d'un Document */
    private JMenuItem bEditarTitolAutorDocument = new JMenuItem("Editar Títol i Autor Document");


    /** Menú principal d'Expressions */
    private JMenu mExpressions = new JMenu("Expressions");
    /** Ítem del menú desplegable d'Expressions per a afegir una expressió */
    private JMenuItem bAfegirExpressio = new JMenuItem("Afegir Expressió");
    /** Ítem del menú desplegable d'Expressions per a editar una expressió */
    private JMenuItem bModificarExpressio = new JMenuItem("Modificar Expressió");
    /** Ítem del menú desplegable d'Expressions per a eliminar una expressió */
    private JMenuItem bEliminarExpressio = new JMenuItem("Eliminar Expressió");
    /** Ítem del menú desplegable d'Expressions per a consultar el contingut d'una expressió pel seu nom */
    private JMenuItem bConsultarExpressio = new JMenuItem("Consultar Expressió");


    /** Menú principal de Cerca de Documents */
    private JMenu mCercarD = new JMenu("Cercar Documents");
    /** Ítem del menú desplegable de Cercar Documents per cercar Documents donat un autor */
    private JMenuItem bCercarDocAutor = new JMenuItem("Cercar Documents per Autor");
    /** Ítem del menú desplegable de Cercar Documents per llistar el contingut d'un Document donat el seu títol i autor */
    private JMenuItem bCercarDocContingut = new JMenuItem("Cercar Contingut per Títol i Autor");
    /** Ítem del menú desplegable de Cercar Documents per cercar Documents semblants a un altre Document */
    private JMenuItem bCercarDocSemblants = new JMenuItem("Cercar Documents Semblants");
    /** Ítem del menú desplegable de Cercar Documents per cercar Documents donada una Expressió Booleana */
    private JMenuItem bCercarDocExp = new JMenuItem("Cercar Documents per Expressió Booleana");
    /** Ítem del menú desplegable de Cercar Documents per cercar Documents rellevants a una sèrie de paraules */
    private JMenuItem bCercarDocRellevants = new JMenuItem("Cercar Documents Rellevants");


    /** Menú principal de Cerca d'Autors */
    private JMenuItem bCercarAutors = new JMenuItem("Cercar Autors");


    /** Panell principal que contindrà a tota la resta de panells */
    private JPanel pPrincipal = new JPanel(new CardLayout());


    /** Panell per Alta Document */
    private PanelAltaDocument pAltaDocument;
    /** Panell per Obrir Document */
    private PanelObrirDocument pObrirDocument;
    /** Panell per Importar Document */
    private PanelImportarDocument pImportarDocument;
    /** Panell per Exportar Document */
    private PanelExportarDocument pExportarDocument;
    /** Panell per Baixa Document */
    private PanelBaixaDocument pBaixaDocument;
    /** Panell per Editar Títol Autor Document */
    private PanelEditarTitolAutorDocument pEditarTitolAutorDocument;


    /** Panell per Afegir Expressió */
    private PanelAfegirExpressio pAfegirExpressio;
    /** Panell per Modificar Expressió */
    private PanelModificarExpressio pModificarExpressio;
    /** Panell per Eliminar Expressió */
    private PanelEliminarExpressio pEliminarExpressio;
    /** Panell per Consultar Expressió */
    private PanelConsultarExpressio pConsultarExpressio;


    /** Panell per Cercar els Documents d'un Autor */
    private PanelCercarDocAutor pCercarDocAutor;
    /** Panell per Cercar el Contingut d'un Document donat Títol i Autor */
    private PanelCercarDocContingut pCercarDocContingut;
    /** Panell per Cercar els Documents més Semblants a un altre Document */
    private PanelCercarDocSemblants pCercarDocSemblants;
    /** Panell per Cercar Documents donada una Expressió Booleana */
    private PanelCercarDocExp pCercarDocExp;
    /** Panell per Cercar els Documents més Rellevants a una Sèrie de Paraules */
    private PanelCercarDocRellevants pCercarDocRellevants;


    /** Panell per Cercar Autors donat un Prefix */
    private PanelCercarAutors pCercarAutors;


    /**
     * Constructora per defecte de la finestra de la Vista Principal.
    */
    public VistaPrincipal() {
        // Frame
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setTitle("Gestor de Documents");
        setMinimumSize(new Dimension(500, 300));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                CtrlPresentacio.tancarAplicacio();
                System.exit(0);
            }
        });

        // Panells Funcionalitats
        // Documents
        pAltaDocument = CtrlPresentacio.panelAltaDocument();
        pPrincipal.add(pAltaDocument, "AltaDocument");
        pObrirDocument = CtrlPresentacio.panelObrirDocument();
        pPrincipal.add(pObrirDocument, "ObrirDocument");
        pImportarDocument = CtrlPresentacio.panelImportarDocument();
        pPrincipal.add(pImportarDocument, "ImportarDocument");
        pExportarDocument = CtrlPresentacio.panelExportarDocument();
        pPrincipal.add(pExportarDocument, "ExportarDocument");
        pBaixaDocument = CtrlPresentacio.panelBaixaDocument();
        pPrincipal.add(pBaixaDocument, "BaixaDocument");
        pEditarTitolAutorDocument = CtrlPresentacio.panelEditarTitolAutorDocument();
        pPrincipal.add(pEditarTitolAutorDocument, "EditarTitolAutorDocument");

        // Expressions
        pAfegirExpressio = CtrlPresentacio.panelAfegirExpressio();
        pPrincipal.add(pAfegirExpressio, "AfegirExpressio");
        pModificarExpressio = CtrlPresentacio.panelModificarExpressio();
        pPrincipal.add(pModificarExpressio, "ModificarExpressio");
        pEliminarExpressio = CtrlPresentacio.panelEliminarExpressio();
        pPrincipal.add(pEliminarExpressio, "EliminarExpressio");
        pConsultarExpressio = CtrlPresentacio.panelConsultarExpressio();
        pPrincipal.add(pConsultarExpressio, "ConsultarExpressio");

        // Cercar Documents
        pCercarDocAutor = CtrlPresentacio.panelCercarDocAutor();
        pPrincipal.add(pCercarDocAutor, "CercarDocAutor");
        pCercarDocContingut = CtrlPresentacio.panelCercarDocContingut();
        pPrincipal.add(pCercarDocContingut, "CercarDocContingut");
        pCercarDocSemblants = CtrlPresentacio.panelCercarDocSemblants();
        pPrincipal.add(pCercarDocSemblants, "CercarDocSemblants");
        pCercarDocExp = CtrlPresentacio.panelCercarDocExp();
        pPrincipal.add(pCercarDocExp, "CercarDocExp");
        pCercarDocRellevants = CtrlPresentacio.panelCercarDocRellevants();
        pPrincipal.add(pCercarDocRellevants, "CercarDocRellevants");

        // Cercar Autors
        pCercarAutors = CtrlPresentacio.panelCercarAutors();
        pPrincipal.add(pCercarAutors, "CercarAutors");

        // Panell Principal
        add(pPrincipal);


        // Menús
        // Documents
        mDocuments.add(bAltaDocument);
        bAltaDocument.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pAltaDocument = CtrlPresentacio.panelAltaDocument();
            cl.show(pPrincipal, "AltaDocument");
        });
        mDocuments.add(bObrirDocument);
        bObrirDocument.addActionListener(e-> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            cl.show(pPrincipal, "ObrirDocument");
        });
        mDocuments.add(bImportarDocument);
        bImportarDocument.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pImportarDocument = CtrlPresentacio.panelImportarDocument();
            cl.show(pPrincipal, "ImportarDocument");
        });
        mDocuments.add(bExportarDocument);
        bExportarDocument.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pExportarDocument = CtrlPresentacio.panelExportarDocument();
            cl.show(pPrincipal, "ExportarDocument");
        });
        mDocuments.add(bBaixaDocument);
        bBaixaDocument.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pBaixaDocument = CtrlPresentacio.panelBaixaDocument();
            cl.show(pPrincipal, "BaixaDocument");
        });
        mDocuments.add(bEditarTitolAutorDocument);
        bEditarTitolAutorDocument.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pEditarTitolAutorDocument = CtrlPresentacio.panelEditarTitolAutorDocument();
            cl.show(pPrincipal, "EditarTitolAutorDocument");
        });

        // Expressions
        mExpressions.add(bAfegirExpressio);
        bAfegirExpressio.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pAfegirExpressio = CtrlPresentacio.panelAfegirExpressio();
            pPrincipal.add(pAfegirExpressio, "AfegirExpressio");
            cl.show(pPrincipal, "AfegirExpressio");
        });
        mExpressions.add(bModificarExpressio);
        bModificarExpressio.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pModificarExpressio = CtrlPresentacio.panelModificarExpressio();
            pPrincipal.add(pModificarExpressio, "ModificarExpressio");
            cl.show(pPrincipal, "ModificarExpressio");
        });
        mExpressions.add(bEliminarExpressio);
        bEliminarExpressio.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pEliminarExpressio = CtrlPresentacio.panelEliminarExpressio();
            pPrincipal.add(pEliminarExpressio, "EliminarExpressio");
            cl.show(pPrincipal, "EliminarExpressio");
        });
        mExpressions.add(bConsultarExpressio);
        bConsultarExpressio.addActionListener(e -> {
            CardLayout cl = (CardLayout)pPrincipal.getLayout();
            pConsultarExpressio = CtrlPresentacio.panelConsultarExpressio();
            pPrincipal.add(pConsultarExpressio, "ConsultarExpressio");
            cl.show(pPrincipal, "ConsultarExpressio");
            
        });

        // Cercar Documents
        mCercarD.add(bCercarDocAutor);
        bCercarDocAutor.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pCercarDocAutor = CtrlPresentacio.panelCercarDocAutor();
            pPrincipal.add(pCercarDocAutor, "CercarDocAutor");
            cl.show(pPrincipal, "CercarDocAutor");
        });
        mCercarD.add(bCercarDocContingut);
        bCercarDocContingut.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pCercarDocContingut = CtrlPresentacio.panelCercarDocContingut();
            pPrincipal.add(pCercarDocContingut, "CercarDocContingut");
            cl.show(pPrincipal, "CercarDocContingut");
        });
        mCercarD.add(bCercarDocSemblants);
        bCercarDocSemblants.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pCercarDocSemblants = CtrlPresentacio.panelCercarDocSemblants();
            pPrincipal.add(pCercarDocSemblants, "CercarDocSemblants");
            cl.show(pPrincipal, "CercarDocSemblants");
        });
        mCercarD.add(bCercarDocExp);
        bCercarDocExp.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pCercarDocExp = CtrlPresentacio.panelCercarDocExp();
            pPrincipal.add(pCercarDocExp, "CercarDocExp");
            cl.show(pPrincipal, "CercarDocExp");
        });
        mCercarD.add(bCercarDocRellevants);
        bCercarDocRellevants.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pCercarDocRellevants = CtrlPresentacio.panelCercarDocRellevants();
            pPrincipal.add(pCercarDocRellevants, "CercarDocRellevants");
            cl.show(pPrincipal, "CercarDocRellevants");
        });

        // Cercar Autors
        bCercarAutors.addActionListener(e -> {
            CardLayout cl = (CardLayout)(pPrincipal.getLayout());
            pCercarAutors = CtrlPresentacio.panelCercarAutors();
            pPrincipal.add(pCercarAutors, "CercarAutors");
            cl.show(pPrincipal, "CercarAutors");
        });

        // Barra de menús
        menuBar.add(mDocuments);
        menuBar.add(mExpressions);
        menuBar.add(mCercarD);
        menuBar.add(bCercarAutors);
        setJMenuBar(menuBar);

        // Frame
        this.setVisible(true);
    }
}
