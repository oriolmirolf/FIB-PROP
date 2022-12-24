package FONTS.src.main.presentation.views;

import FONTS.src.main.domain.classes.exceptions.DocumentJaExisteix;
import FONTS.src.main.domain.classes.exceptions.DocumentNoGuardat;
import FONTS.src.main.domain.classes.exceptions.DocumentNoObert;
import FONTS.src.main.domain.classes.exceptions.JaExisteixDocumentObert;
import FONTS.src.main.persistencia.excepcions.ErrorIntern;
import FONTS.src.main.persistencia.excepcions.NoExisteixPath;
import FONTS.src.main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Classe de la Vista del Editor de Text.
 * @author Oriol Miró López-Feliu (oriol.miro.lopez-feliu@estudiantat.upc.edu)
 */
public class VistaEditor extends JFrame implements ActionListener {
    /** JMenuBar que engloba tots els menús */
    private JMenuBar mb = new JMenuBar();

    /** JMenu que engloba miObrir, miGuardar i miExportar */
    private JMenu mFitxer = new JMenu("Fitxer");
    /** JMenuItem que serveix per obrir un altre document */
    private JMenuItem miObrir = new JMenuItem("Obrir");
    /** JMenuItem que serveix per guardar el document actual document */
    private JMenuItem miGuardar = new JMenuItem("Guardar");
    /** JMenuItem que serveix per exportar el document actual document */
    private JMenuItem miExportar = new JMenuItem("Exportar");

    /** JMenu que engloba miEditarAutor i miEditarTitol */
    private JMenu mEdicio = new JMenu("Edicio");
    /** JMenuItem que serveix per editar l'autor del document actual document */
    private JMenuItem miEditarAutor = new JMenuItem("Editar Autor");
    /** JMenuItem que serveix per editar el títol del document actual document */
    private JMenuItem miEditarTitol = new JMenuItem("Editar Titol");

    /** JMenu que engloba miRetallar, miCopiar i miEnganxar */
    private JMenu mText = new JMenu("Text");
    /** JMenuItem que serveix per retallar el text seleccionat */
    private JMenuItem miRetallar = new JMenuItem("Retallar");
    /** JMenuItem que serveix per copiar el text seleccionat */
    private JMenuItem miCopiar = new JMenuItem("Copiar");
    /** JMenuItem que serveix per enganxar el text del porta-retalls */
    private JMenuItem miEnganxar = new JMenuItem("Enganxar");


    /** String on ens guardem el path del document actualment editan-se*/
    String pathDocumentActual;
    /** JTextArea que serveix per editar el contingut del document */
    private JTextArea text = new JTextArea();
    /** JFileChooser usat per seleccionar el document a obrir */
    private JFileChooser chooser = new JFileChooser();
    /** JScrollPane usat per poder fer scroll sobre el text */
    private JScrollPane sp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


    /**
     * Creadora per defecte.
     */
    public VistaEditor() {
        // Frame
        setBounds(500, 300, 500, 300);
        setResizable(true);
        setMinimumSize(new Dimension(500, 300));
        setTitle("Editor de Text");

        //Redefinim el comportament de quan es tanca la finestra
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                try {
                    CtrlPresentacio.tancarDocument();
                    dispose();
                }
                catch (DocumentNoObert ignored) {} //Imposible
                catch (DocumentNoGuardat e) {
                    Object[] options = {"Guardar i sortir", "Sortir sense guardar", "Cancelar"};
                    int response = JOptionPane.showOptionDialog(null, "Segur que vol sortir sense guardar?", "SORTINT SENSE GUARDAR", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);

                    //Opcio guardar i sortir
                    if (response == 0) {
                        try {
                            CtrlPresentacio.guardarDocument();
                            CtrlPresentacio.tancarDocument();
                            dispose();
                        }
                        catch (DocumentNoObert | DocumentNoGuardat ignored) {} //Imposible
                    }

                    //Opcio sortir sense guardar
                    if (response == 1) {
                        CtrlPresentacio.tancarDocumentForcat();
                        dispose();
                    }

                }
            }
        });

        //La informacio del document: Contingut i
        try {
            ArrayList<String> infoDoc = CtrlPresentacio.getDocumentObert();
            pathDocumentActual = infoDoc.get(2);
            String contingutDocument = infoDoc.get(5);
            text.setText(contingutDocument);
        } catch (DocumentNoObert ignored) {} //Impossible


        //Este bloque de texto va guardando la informacion de todos los cambios
        text.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    CtrlPresentacio.editarContingut(text.getText());
                }
                catch (DocumentNoObert ignored) {} //Imposible
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    CtrlPresentacio.editarContingut(text.getText());
                }
                catch (DocumentNoObert ignored) {} //Imposible
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    CtrlPresentacio.editarContingut(text.getText());
                }
                catch (DocumentNoObert ignored) {} //Imposible
            }
        });

        //Este bloque de texto prepara el filtro i el chooser para abrir documentos
        FileNameExtensionFilter filter = new FileNameExtensionFilter("OJMJ FILES", "ojmj");
        chooser.setFileFilter(filter);



        //Afegim les coses als menus
        mFitxer.add(miObrir);
        mFitxer.add(miGuardar);
        mFitxer.add(miExportar);

        mEdicio.add(miEditarAutor);
        mEdicio.add(miEditarTitol);

        mText.add(miRetallar);
        mText.add(miCopiar);
        mText.add(miEnganxar);

        mb.add(mFitxer);
        mb.add(mEdicio);
        mb.add(mText);


        //Afegim els action listeners
        miObrir.addActionListener(this);
        miGuardar.addActionListener(this);
        miExportar.addActionListener(this);

        miEditarAutor.addActionListener(this);
        miEditarTitol.addActionListener(this);

        miRetallar.addActionListener(this);
        miCopiar.addActionListener(this);
        miEnganxar.addActionListener(this);

        //Cosas del panell actual
        this.setJMenuBar(mb);
        sp.setViewportView(text);
        this.add(sp);
        this.setSize(500, 500);
        this.setVisible(true);


        //Aqui afegim tots els acceleradors
        KeyStroke keyStrokeToOpen;

        //Obrir
        keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        miObrir.setAccelerator(keyStrokeToOpen);

        //Guardar
        keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK);
        miGuardar.setAccelerator(keyStrokeToOpen);

        //Exportar
        keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
        miExportar.setAccelerator(keyStrokeToOpen);

        //Editar autor
        keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK);
        miEditarAutor.setAccelerator(keyStrokeToOpen);

        //Editar titol
        keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK);
        miEditarTitol.setAccelerator(keyStrokeToOpen);

        //Retallar
        keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
        miRetallar.setAccelerator(keyStrokeToOpen);

        //Copiar
        keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
        miCopiar.setAccelerator(keyStrokeToOpen);

        //Enganxar
        keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
        miEnganxar.setAccelerator(keyStrokeToOpen);
    }

    /**
     * Funció per gestionar quan s'apreta algun JMenuItem.
     * @param e El event a processar.
     */
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        switch (s) {
            case "Obrir":

                //Fixem el filtre
                FileNameExtensionFilter filter = new FileNameExtensionFilter("OJMJ FILES", "ojmj");
                chooser.setFileFilter(filter);

                //Fixem el dialog
                chooser.setDialogTitle("Selecciona el directori i el nom de l'arxiu");

                //Obrir el chooser d'arxius
                int returnValue = chooser.showOpenDialog(null);
                if (returnValue != JFileChooser.APPROVE_OPTION) break; //Fem aixi per evitar els "nested ifs"

                File arxiu = chooser.getSelectedFile();
                String path = arxiu.getPath();
                if (!path.endsWith(".ojmj")) {
                    JOptionPane.showMessageDialog(this, "ERROR: El path " + path + " no és vàlid.", "Error Obrir Arxiu", JOptionPane.ERROR_MESSAGE);
                    break; //Fem aixi per evitar els "nested ifs"
                }

                //Intentem fer-ho be
                try {
                    CtrlPresentacio.tancarDocument();
                    CtrlPresentacio.obrirDocument(path);
                    dispose();
                    CtrlPresentacio.vistaEditor();
                }

                catch (DocumentNoObert | JaExisteixDocumentObert ignored) {} //Imposible

                catch (DocumentNoGuardat ex) {
                    //En el cas de que no estigui guardat, deixem escollir
                    Object[] options = {"Guardar i sortir", "Sortir sense guardar", "Cancelar"};
                    int response = JOptionPane.showOptionDialog(null, "Segur que vol sortir sense guardar?", "SORTINT SENSE GUARDAR", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);

                    //Opcio guardar i sortir
                    if (response == 0) {
                        try {
                            CtrlPresentacio.guardarDocument();
                            CtrlPresentacio.tancarDocument();
                            CtrlPresentacio.obrirDocument(path);
                            dispose();
                            CtrlPresentacio.vistaEditor();
                        } catch (NoExisteixPath exc) {
                            JOptionPane.showMessageDialog(this, "ERROR: El fitxer " + path + " no existeix.", "Error Obrir Arxiu", JOptionPane.ERROR_MESSAGE);
                            //Hem de tornar a obrir perque l'error succeeix després, revertim al estat anterior al try.
                            try {
                                CtrlPresentacio.obrirDocument(pathDocumentActual);
                            }
                            catch (NoExisteixPath | JaExisteixDocumentObert ignored) {} //Impossible
                        }
                        catch (JaExisteixDocumentObert | DocumentNoGuardat | DocumentNoObert ignored) {} //Impossible
                    }

                    //Opcio sortir sense guardar
                    if (response == 1) {
                        try {
                            CtrlPresentacio.tancarDocumentForcat();
                            CtrlPresentacio.obrirDocument(path);
                            dispose();
                            CtrlPresentacio.vistaEditor();
                        } catch (NoExisteixPath exc) {
                            JOptionPane.showMessageDialog(this, "ERROR: El fitxer " + path + " no existeix.", "Error Obrir Arxiu", JOptionPane.ERROR_MESSAGE);
                            //Hem de tornar a obrir perque l'error succeeix després, revertim al estat anterior al try.
                            try {
                                CtrlPresentacio.obrirDocument(pathDocumentActual);
                            }
                            catch (NoExisteixPath | JaExisteixDocumentObert ignored) {} //Impossible
                        } catch (JaExisteixDocumentObert ignored) {} //Impossible
                    }

                }

                catch (NoExisteixPath ex) {
                    JOptionPane.showMessageDialog(this, "ERROR: El fitxer " + path + " no existeix.", "Error Obrir Arxiu", JOptionPane.ERROR_MESSAGE);
                    //Hem de tornar a obrir perque l'error succeeix després, revertim al estat anterior al try.
                    try {
                        CtrlPresentacio.obrirDocument(pathDocumentActual);
                    }
                    catch (NoExisteixPath | JaExisteixDocumentObert ignored) {} //Impossible
                }

                break;
            case "Guardar":
                try {
                    CtrlPresentacio.guardarDocument();
                    JOptionPane.showMessageDialog(this, "El document s'ha guardat correctament");
                } catch (DocumentNoObert ex) {
                    //Impossible
                }
                break;
            case "Exportar":

                //Opcions de format
                String[] opcions = {"TXT", "XML", "PDF", "JSON"};

                //Fem escollir el format
                Object seleccio = JOptionPane.showInputDialog(null, "Escull el format d'exportació",
                        "Exportar Document", JOptionPane.QUESTION_MESSAGE, null, opcions, null);

                if (seleccio == null) break; //Fem aixi per evitar els "nested ifs"

                //Posteriorment fem excollir el path
                chooser.setDialogTitle("Selecciona el directori i el nom de l'arxiu");
                //Ens carreguem un possible filtre anterior
                chooser.setFileFilter(null);
                returnValue = chooser.showOpenDialog(null);

                //ssi escull un
                if (returnValue != JFileChooser.APPROVE_OPTION) break; //Fem aixi per evitar els "nested ifs"

                arxiu = chooser.getSelectedFile();
                String pathDocumentExportat = arxiu.getPath();

                if (!pathDocumentExportat.endsWith(".txt") && !pathDocumentExportat.endsWith(".xml") && !pathDocumentExportat.endsWith(".pdf") && !pathDocumentExportat.endsWith(".json")) {
                    //Nomes cal posar el final si no ens l'han posat ells
                    if (seleccio == "TXT") pathDocumentExportat = pathDocumentExportat + ".txt";
                    if (seleccio == "XML") pathDocumentExportat = pathDocumentExportat + ".xml";
                    if (seleccio == "PDF") pathDocumentExportat = pathDocumentExportat + ".pdf";
                    if (seleccio == "JSON") pathDocumentExportat = pathDocumentExportat + ".json";
                }

                try {
                    CtrlPresentacio.exportarDocument(pathDocumentActual, pathDocumentExportat);
                    JOptionPane.showMessageDialog(this, "El document s'ha exportat correctament");
                }
                catch (NoExisteixPath ignored) {} //Impossible
                catch (ErrorIntern ex) {
                    JOptionPane.showMessageDialog(this, "ERROR: Ha ocurrit un error intern.", "Error Exportar Arxiu", JOptionPane.ERROR_MESSAGE);
                }


                break;
            case "Editar Titol":
                ArrayList<String> infoDoc = null;
                try {
                    infoDoc = CtrlPresentacio.getDocumentObert();
                }
                catch (DocumentNoObert ignored) {} //Impossible

                String autorDocumentActual = infoDoc.get(0);
                String titolDocumentActual = infoDoc.get(1);
                String nouTitol = null;
                try {
                    nouTitol = JOptionPane.showInputDialog(this, "Entra el nou titol:");

                    if (nouTitol == null) break;

                    CtrlPresentacio.editarTitol(nouTitol, titolDocumentActual, autorDocumentActual);
                    JOptionPane.showMessageDialog(this, "El titol s'ha editat correctament");
                }
                catch (NoExisteixPath ignored) {} //Impossible
                catch (DocumentJaExisteix ex) {
                    JOptionPane.showMessageDialog(this, "ERROR: Ja existeix un document amb títol " + nouTitol + " i autor " + autorDocumentActual + ".", "Error Editar Titol", JOptionPane.ERROR_MESSAGE);
                }

                break;
            case "Editar Autor":
                infoDoc = null;
                try {
                    infoDoc = CtrlPresentacio.getDocumentObert();
                }
                catch (DocumentNoObert ignored) {} //Impossible
                autorDocumentActual = infoDoc.get(0);
                titolDocumentActual = infoDoc.get(1);
                String nouAutor = null;
                try {
                    nouAutor = JOptionPane.showInputDialog(this, "Entra el nou autor:");

                    if (nouAutor == null) break;

                    CtrlPresentacio.editarAutor(autorDocumentActual, nouAutor, titolDocumentActual);
                    JOptionPane.showMessageDialog(this, "L'autor s'ha editat correctament");
                }
                catch (NoExisteixPath ignored) {} //Impossible
                catch (DocumentJaExisteix ex) {
                    JOptionPane.showMessageDialog(this, "ERROR: Ja existeix un document amb títol " + titolDocumentActual + " i autor " + nouAutor + ".", "Error Editar Titol", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Retallar":
                text.cut();
                break;
            case "Copiar":
                text.copy();
                break;
            case "Enganxar":
                text.paste();
                break;
        }
    }
}
