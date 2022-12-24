package FONTS.src.main.domain.controllers;

import FONTS.src.main.domain.classes.resultats.*;
import FONTS.src.main.domain.classes.exceptions.*;
import FONTS.src.main.domain.classes.individual_classes.Document;
import FONTS.src.main.persistencia.CtrlPersistencia;
import FONTS.src.main.persistencia.excepcions.NoExisteixPath;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import FONTS.src.main.domain.classes.indexes.*;
import FONTS.src.main.transversal.Tupla;
/**
 * Classe del Controlador d'Índexs.
 */
public class CtrlIndex implements Serializable{
    /** Controlador de la Capa de Persistència */
    private CtrlPersistencia capaPers;
    /** Índex de Títols */
    private IndexTitol  titols_autors;
    /** Índex d'Autors */
    private IndexAutors prefixos_autors;
    /** Índex d'Expressions Booleanes */
    private IndexExpressionsBooleanes expressionsBooleanes;
    /** Índex de l'Espai Vectorial */
    private IndexEspaiVectorial espaiVectorial;

    private static final long serialVersionUID = 2L;
    /**
     * Operació que donat un conjunt de Documents, retorna els que contenen la frase especificada pel parametre "frase".
     * @param list Estructura que conté una serie de Documents.
     * @param frase Estructura que conté una frase per buscar.
     * @return Set amb tots els Documents que contenen la frase en el mateix ordre
     */
    private HashSet<String> getDocumentsAmbPatro(HashSet<String> list, ArrayList<String> frase) {
        HashSet<String> documents = new HashSet<>();
        for (String document : list) {
            Document d = null;
            try {
                Tupla<String, String, String ,LocalDateTime, LocalDateTime, String> info = capaPers.getDocument(document);
                d = new Document(info.getFirst(),info.getSecond(),info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
            } catch (NoExisteixPath e) {
                
            }
            TreeMap<Integer,String> frases =new TreeMap<>();
            for (String palabra : frase) {
                Set<Integer> posicions = d.getPosicionsParaula(palabra);
                for (Integer I : posicions) frases.put(I, palabra);
            }

            Integer num = 0;
            Integer pos = 0; 
            for (Map.Entry<Integer, String> ent : frases.entrySet()) {
                if (num.equals(frase.size())) {
                    documents.add(document);
                    break;
                }
                String p = frase.get(num);
                if (num.equals(0) && p.equals(ent.getValue())) {
                    pos = ent.getKey();
                    ++num;
                }
                else if (p.equals(ent.getValue()) && ent.getKey().equals(pos+1)) {
                    ++num;
                    pos = ent.getKey();
                }
                else {
                    num = 0;
                    pos = 0;
                }
            }
            if (num.equals(frase.size())) documents.add(document);
        }
        return documents;
    }
    /**
     * Operació per separar una frase en ordre d'aparició de les paraules i caracters especials
     * @param s String que conté una frase.
     * @return Es retorna una estructura que conté cada paraula de la frase en el mateix ordre d'aparició que a s.
     */
    private static ArrayList<String> allmatches(String s) {
        ArrayList<String> allM = new ArrayList<String>();
        Matcher m = Pattern.compile("((\\w+)|(\\W))").matcher(s);
        while (m.find()) {
            String cosa = m.group();
            if (!cosa.equals(" ") && ! cosa.equals("\n"))allM.add(cosa);
        }
        return allM;
    }

    /**
     * Operació que dona tots el documents que contenen un conjunt de paraules.
     * @param array Estructura de dades que conté les paraules que es vol fer la intersecció
     * @return Es retorna un Set amb tots el documents que contenen totes les paraules de l'array
     */
    private HashSet<String> getInterseccion(ArrayList<String> array) {
        HashSet<String> interseccion = new HashSet<>();
        for (int i = 0; i < array.size(); ++i) {
            if (i == 0) interseccion = getDocumentsParaula(array.get(i));
            else interseccion.retainAll(getDocumentsParaula(array.get(i)));
        }
        return interseccion;
    }

    /**
     * Operació per obtenir una Instancia de la clase ResultatDocument donats uns atributs vàlids
     * @param d Objecte que conté tota l'informació d'un Document
     * @return Es retorna una instancia que conté tota l'informació a mostrar del Document d
     */
    private ResultatDocument getResultatDocument(Document d) {
        ResultatDocument res = new ResultatDocument();
        res.setAutor(d.getAutor());
        res.setTitol(d.getTitol());
        res.setPath(d.getLocalitzacio());
        res.setMida(d.getContingut().size());
        res.setDataCreacio(d.getDataCreacio());
        return res;
    }

    /**
     * Operació per obtenir una Instancia de la clase ResultatAutor donats uns atributs vàlids
     * @param autor Nom d'un autor.
     * @param titols Estructura que conté tots els titols de l'autor.
     * @return Es retorna una instancia que conté tota l'informació a mostrar d'un Autor;
     */
    private ResultatAutor getResultatAutor(String autor, TreeMap<String, String> titols) {
        ResultatAutor res = new ResultatAutor();
        res.setAutor(autor);
        res.setNTitols(titols.size());
        return res;
    }

    /**
     * Constructora de la Classe
     * @param capa Parametre de la capa de persistencia.
     */
    public CtrlIndex(CtrlPersistencia capa) {
        capaPers = capa;
        if(!capaPers.existeixConfiguracioDom()){
            titols_autors = new IndexTitol();
            prefixos_autors = new IndexAutors();   
            expressionsBooleanes = new IndexExpressionsBooleanes(this);
            espaiVectorial = new IndexEspaiVectorial();
        }
        else{
            byte[] arr = capaPers.getConfiguracioDom();
            try {
                ByteArrayInputStream bytes = new ByteArrayInputStream(arr);
                ObjectInputStream f = new ObjectInputStream(bytes);
                titols_autors = (IndexTitol) f.readObject();
                prefixos_autors = (IndexAutors)f.readObject();
                expressionsBooleanes = (IndexExpressionsBooleanes)f.readObject();
                espaiVectorial = new IndexEspaiVectorial();
                ArrayList<String> docs = capaPers.getAllPath();
                for(String path : docs){
                    Document d = null;
                    try {
                        Tupla<String, String, String ,LocalDateTime, LocalDateTime, String> info = capaPers.getDocument(path);
                        d = new Document(info.getFirst(),info.getSecond(),info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
                    } catch (NoExisteixPath e) {
                    // Aixo mi passara
                    }

                    try {
                        espaiVectorial.afegirDocument(path, d.getContingut());
                    } catch (FormatNoReconegut e) {
                    
                    }
                } 
                expressionsBooleanes.setControlador(this);
            } catch (IOException e) {
        
            } catch (ClassNotFoundException e) {
                
            } catch (DocumentJaExisteix e) {
        
            }
        }
        
    }

    /**
     * Operació per obtenir els K Documents més semblants donat un altre Document.
     * @param k Nombre de documents que volem obtenir.
     * @param path Direcció associada al Document a comparar.
     * @param manera Indica si volem utilitzar el mètode de TFIDF (0) o BM25 (1).
     * @return Es retorna la informació del K elements més semblants al document que té com a clau Path. Si hi ha menys de K resultats, igualment es retornen aquest Documents.
     * @throws FormatNoReconegut Excepció que es llença quan D és un Document d'extensió no reconeguda pel gestor.
     * @throws NoExisteixDocument Excepció que es llença quan no existeix el Document.
     * @throws NumDocumentsIncorrecte Excepció que es llença quan el nombre de Documents a obtenir no és acceptat.
     */
    public ArrayList<ResultatDocument> llistarKDocumentsSemblants(int k, String path, Integer manera) throws FormatNoReconegut, NoExisteixDocument, NumDocumentsIncorrecte {
        ArrayList<String> resultats = espaiVectorial.llistarKDocumentsSemblants(k, path, manera);
        ArrayList<ResultatDocument> res_list = new ArrayList<>();
        for (String doc : resultats) {
            Tupla<String, String, String, LocalDateTime, LocalDateTime, String> info;
            try {
                info = capaPers.getDocument(doc);
                Document d = new Document(info.getFirst(),info.getSecond(),info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
                ResultatDocument res = getResultatDocument(d);
                res_list.add(res);
            } catch (NoExisteixPath e) {
    
            }
            
        }
        return res_list;
    }

    /**
     * Operació per obtenir els K Documents més rellevants donat un conjunt de paraules
     * @param k Nombre de documents que volem obtenir.
     * @param paraules Conjunt de paraules per trobar el Documents més rellevants
     * @param manera Indica si volem utilitzar el mètode de TFIDF (0) o BM25 (1).
     * @return Es retorna l'informació del K elements més semblants al conjunt de paraules. Si hi ha menys de K resultats, igualment es retornen aquest Documents.
     * @throws NumDocumentsIncorrecte Excepció que es llença quan el nombre de Documents a obtenir no és acceptat.
     */
    public ArrayList<ResultatDocument> llistarKDocumentsRellevantsPParaules(Integer k, TreeSet<String> paraules, Integer manera) throws NumDocumentsIncorrecte {
        ArrayList<String> resultats = espaiVectorial.llistarKDocumentsRellevants(k, paraules, manera);
        ArrayList<ResultatDocument> res_list= new ArrayList<>();
        for (String doc : resultats) {
            Tupla<String, String, String, LocalDateTime, LocalDateTime, String> info;
            try {
                info = capaPers.getDocument(doc);
                Document d = new Document(info.getFirst(),info.getSecond(),info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
                ResultatDocument res = getResultatDocument(d);
                res_list.add(res);
            } catch (NoExisteixPath e) {
                
            }
        }
        return res_list;
    }

    /**
     * Operació per obtenir tots els Documents d'un autor.
     * @param autor Nom de l'autor.
     * @return ArrayList: ArrayList amb la informació de cada Document obtingut, on per cada Document es dona el seu títol i path.
     * @throws NoExisteixAutor Excepció que es llença quan no existeix l'autor autor.
     */
    public ArrayList<ResultatDocument> llistarTitolsAutor(String autor) throws NoExisteixAutor {
        TreeMap<String, String> resultats = titols_autors.getTitols(autor);
        ArrayList<ResultatDocument> res_list = new ArrayList<>();
        for (Map.Entry<String, String> entry : resultats.entrySet()) {
            Tupla<String, String, String, LocalDateTime, LocalDateTime, String> info;
            try {
                info = capaPers.getDocument(entry.getValue());
                Document d = new Document(info.getFirst(),info.getSecond(),info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
                ResultatDocument res = getResultatDocument(d);
                res_list.add(res);
            } catch (NoExisteixPath e) {
                
            }
        }

        return res_list;
    }

    /**
     * Operació per veure el contingut d'un Document donat el seu autor i títol.
     * @param autor Autor del Document.
     * @param titol Títol del Document.
     * @return ArrayList: Contingut del Document.
     * @throws NoExisteixDocument Excepció que es llença quan no existeix cap Document amb el títol i l'autor proporcionats.
     */
    public String llistarContingutDocumentPerAutorTitol(String autor, String titol) throws NoExisteixDocument {
        try {
            String path = titols_autors.getTitol(autor, titol);
            Tupla<String, String, String ,LocalDateTime, LocalDateTime, String> info = capaPers.getDocument(path);
            return info.getSixth();
        } catch (NoExisteixAutor e) {
            throw new NoExisteixDocument();
        } catch (NoExisteixPath e) {

        }
        return null;
    }

    /**
     * Consultora d'autors donat un prefix.
     * @param prefix Prefix dels autors que volem buscar.
     * @return ArrayList: Array que conté per cada autor amb aquest prefix el seu nom i el nombre de documents escrits.
     */
    public ArrayList<ResultatAutor> llistarAutorsPerPrefix(String prefix) {
        ArrayList<String> autors = prefixos_autors.cercarAutors(prefix);
        ArrayList<ResultatAutor> res_list = new ArrayList<>();
        if (autors.isEmpty()) return res_list;

        for (String autor : autors) {
            try {
                TreeMap<String, String> titols = titols_autors.getTitols(autor);
                ResultatAutor res = getResultatAutor(autor, titols);
                res_list.add(res);
            } catch (NoExisteixAutor e) {
                
            }
        }
        return res_list;
    }

    /**
     * Operació per donar d'alta un document que ja ha estat creat prèviament pel CtrlDocument i es vol únicament introduir al gestor.
     * @param path Direcció on es troba guardat el contingut del document.
     * @throws DocumentJaExisteix Excepció que es llença quan ja existeix un document amb mateix títol i autor.
     */
    public void altaDocument(String path) throws DocumentJaExisteix {
        Document d = null;
        try {
            Tupla<String, String, String ,LocalDateTime, LocalDateTime, String> info = capaPers.getDocument(path);
            d = new Document(info.getFirst(),info.getSecond(),info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
        } catch (NoExisteixPath e) {
           // Aixo mi passara
        }
        titols_autors.afegirTitol(d.getAutor(), d.getTitol(), path);
        try {
            prefixos_autors.afegirAutor(d.getAutor());
        }
        catch (JaExisteixAutor e) {
            // Això pot passar però no és cap problema
        }

        try {
            espaiVectorial.afegirDocument(path, d.getContingut());
        } catch (FormatNoReconegut e) {
        
        }
    }

    /**
     * Operació per donar de baixa un Document.
     * @param autor Nom de l'autor del Document que es vol esborrar del gestor.
     * @param titol Títol del Document que es vol esborrar del gestor.
     * @param path Direcció on està guardat el Document que es vol esborrar del gestor.
     */
    public void baixaDocument(String autor, String titol, String path) {
        titols_autors.eliminarTitol(autor, titol);
        try {
            if (!titols_autors.existeixAutor(autor)) prefixos_autors.eliminarAutor(autor);
        } catch (Exception e) {
            // Això mai pot passar
        }
        try {
            espaiVectorial.eliminarDocument(path);
        } catch (NoExisteixDocument e) {
            
        } catch (FormatNoReconegut e) {
            
        }
    }

    /**
     * Consulta si existeix un Document amb el títol i l'autor proporcionats.
     * @param titol Nom de l'autor del Document.
     * @param autor Nom de l'autor del Document.
     * @return boolean: Retorna cert si existeix el Document i fals en cas contrari.
     */
    public boolean existeixDocument(String titol, String autor) {
        return titols_autors.existeixDocument(titol, autor);
    }

    /**
     * Operació per modificar el títol d'un Document.
     * @param nouTitol Nou títol del Document.
     * @param titolAnterior Antic títol del Document a modificar.
     * @param autor Autor del Document a modificar.
     */
    public void modificarTitol(String nouTitol, String titolAnterior, String autor) {
        titols_autors.modificarTitol(nouTitol, titolAnterior, autor);
        try {
            String path = titols_autors.getTitol(autor, nouTitol);
            Document d;
            Tupla<String, String, String ,LocalDateTime, LocalDateTime, String> info = capaPers.getDocument(path);
            d = new Document(info.getFirst(),info.getSecond(),info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
            d.canviarTitolDoc(nouTitol);
            capaPers.guardarDocumentExistent(d.getTitol(), d.getAutor(), d.getLocalitzacio(), d.getDataCreacio(), d.getDataDarreraModificacio(), d.getContingutRaw());
        } catch (NoExisteixAutor | NoExisteixDocument | NoExisteixPath e) {
            
        }
    }

    /**
     * Operació per modificar l'autor d'un Document.
     * @param autorAnterior Antic autor del document a modificar.
     * @param nouAutor Nou autor del Document.
     * @param titol Títol del Document a modificar.
     */
    public void modificarAutor(String autorAnterior, String nouAutor, String titol) {
        boolean abans1 = titols_autors.existeixAutor(autorAnterior);
        boolean abans2 = titols_autors.existeixAutor(nouAutor);
        titols_autors.modificarAutor(autorAnterior, nouAutor, titol);
        boolean despres1 = titols_autors.existeixAutor(autorAnterior);
        boolean despres2 = titols_autors.existeixAutor(nouAutor);

        if (abans1 && !despres1) {
            try {
                prefixos_autors.eliminarAutor(autorAnterior);
            } catch (NoExisteixAutor e) {
                // Això mai pot passar
            }
        }
        if (!abans2 && despres2) {
            try {
                prefixos_autors.afegirAutor(nouAutor);
            } catch (JaExisteixAutor e) {
                
            }
        }
        try {
            String path = titols_autors.getTitol(nouAutor, titol);
            Document d;
            Tupla<String, String, String ,LocalDateTime, LocalDateTime, String> info = capaPers.getDocument(path);
            d = new Document(info.getFirst(),info.getSecond(),info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
            d.canviarAutorDoc(nouAutor);
            capaPers.guardarDocumentExistent(d.getTitol(), d.getAutor(), d.getLocalitzacio(), d.getDataCreacio(), d.getDataDarreraModificacio(), d.getContingutRaw());
        } catch (NoExisteixAutor | NoExisteixDocument | NoExisteixPath e) {
           
        }

    }

    /**
     * Operació per modificar el contingut d'un Document al gestor.
     * @param path Direcció del Document a modificar.
     * @param nouContingut Nou contingut del Document
     */
    public void modificarContingut(String path, ArrayList<String> nouContingut) {
        try {
            espaiVectorial.modificarDocument(path, nouContingut);
        } catch (FormatNoReconegut e) {
            
        } catch (NoExisteixDocument e) {
            
        }
    }

    /**
     * Operació pero obtenir tots els documents del gestor.
     * @return Retorna una Estructura que conté tots els path del Documents que hi han en el Gestor.
     */
    public HashSet<String> getTotsDocuments() {
        ArrayList<String> res = espaiVectorial.getAllDocuments();
        HashSet<String> set = new HashSet<>(res);
        return set;
    }

    /**
     * Operació per obtenir el documents que contenen una paraula.
     * @param p Paraula
     * @return Retorna una estructura que conté tots els path dels Documents que contenen la paraula p.
     */
    public HashSet<String> getDocumentsParaula(String p) {
        ArrayList<String> res = espaiVectorial.getDocumentsContenenParaula(p);
        HashSet<String> set = new HashSet<>(res);
        return set;
    }

    /**
     * Operació per obtenir el documents que contenen una frase.
     * @param f String que conté la frase.
     * @return HashSet: Retorna una estructura que conté tots els path dels Documents que contenen la frase f.
     */
    public HashSet<String> getDocumentsFrase(String f) {
        ArrayList<String> array = allmatches(f);
        HashSet<String> interseccion;
        interseccion = getInterseccion(array);
        return getDocumentsAmbPatro(interseccion, array);
    }

    /**
     * Operació per buscar els documents que satisfan una Expressió booleana guardada en el gestor.
     * @param nom nom de la expressió
     * @return Per cada document es es retorna, autor, titol, Direccio, mida i data de creació
     * @throws NoExisteixExpressio Excepció que es llença quan la expressió no existeix
     */
    public ArrayList<ResultatDocument> llistarDocumentsExpressioBooleana(String nom) throws NoExisteixExpressio {
        ArrayList<String> resultats = expressionsBooleanes.cercaExpressioPerNom(nom);
        ArrayList<ResultatDocument> res_list = new ArrayList<>();
        for (String path : resultats) {
            try {
                Tupla<String, String, String ,LocalDateTime, LocalDateTime, String> info = capaPers.getDocument(path);
                Document d = new Document(info.getFirst(),info.getSecond(),info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
                ResultatDocument res = getResultatDocument(d);
                res_list.add(res);
            } catch (NoExisteixPath e) {
                
            }
        }
        return res_list;
    }

    /**
     * Operació per buscar els documents que satisfan una Expressió donada
     * @param exp expressió booleana
     * @return Per cada document es es retorna, autor, titol, Direccio, mida i data de creació
     * @throws ExpressioNoValida Excepció que es dona quan la expressió donada es erronea a nivell sintactic.
     */
    public ArrayList<ResultatDocument> llistarDocumentsExpressioBooleanaFora(String exp) throws ExpressioNoValida {
        ArrayList<String> resultats = expressionsBooleanes.cercaExpressio(exp);
        ArrayList<ResultatDocument> res_list = new ArrayList<>();
        for (String path : resultats) {
            try {
                Tupla<String, String, String ,LocalDateTime, LocalDateTime, String> info = capaPers.getDocument(path);
                Document d = new Document(info.getFirst(),info.getSecond(),info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
                ResultatDocument res = getResultatDocument(d);
                res_list.add(res);
            } catch (NoExisteixPath e) {
                
            }
        }
        return res_list;
    }

    /**
     * Operació per afegir una Expressió Booleana al gestor.
     * @param nom Nom de l'Expressió.
     * @param exp Contingut de l'Expressió.
     * @throws JaExisteixExpressio Excepció que es llença quan ja existeix una Expressió amb nom nom.
     * @throws ExpressioNoValida Excepció que es llença quan novaExp no és vàlida sintàcticament.
     */
    public void afegirExpressio(String nom, String exp) throws JaExisteixExpressio, ExpressioNoValida {
        expressionsBooleanes.afegirExpressio(nom, exp);
    }

    /**
     * Operació per eliminar una Expressió Booleana del gestor.
     * @param nom Nom de l'Expressió a eliminar.
     * @throws NoExisteixExpressio Excepció que es llença quan no existeix cap Expressió amb nom nom.
     */
    public void eliminarExpressio(String nom) throws NoExisteixExpressio {
        expressionsBooleanes.esborrarExpressio(nom);
    }

    /**
     * Operació per modificar una Expressió Booleana ja existent.
     * @param nom Nom de l'Expressió.
     * @param novaExp Nou contingut de l'Expressió.
     * @throws NoExisteixExpressio Excepció que es llença quan no existeix cap Expressió amb nom nom.
     * @throws ExpressioNoValida Excepció que es llença quan novaExp no és vàlida sintàcticament.
     */
    public void modificarExpressio(String nom, String novaExp) throws NoExisteixExpressio, ExpressioNoValida {
        expressionsBooleanes.modificarExpressio(nom, novaExp);
    }

    /** Operació per consultar l'Expressió Booleana donat el seu nom.
     * @param nom Nom de l'Expressió.
     * @return String: Contingut de l'Expressió consultada.
     * @throws NoExisteixExpressio Excepció que es llença quan no existeix cap Expressió amb nom nom.
     */
    public String consultarExpressio(String nom) throws NoExisteixExpressio {
        return  expressionsBooleanes.consultaExpressio(nom);
    }

    
    /** 
     * @return ArrayList que conté tots els noms de les expressions booleanes guardades.
     */
    public ArrayList<String> llistarExpressions() {
        return expressionsBooleanes.llistarExpressions();
    }
    /**
     * Operació per tancar la aplicació
     */
    public void TancarAplicacio(){
        
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutputStream f = new ObjectOutputStream(buffer);
            f.writeObject(titols_autors);
            f.writeObject(prefixos_autors);
            f.writeObject(expressionsBooleanes);
            f.flush();
            byte[] arr = buffer.toByteArray();
            f.close();
            capaPers.tancarAplicacio(arr);
        } catch (IOException e) {
            
        }

    }
} 
