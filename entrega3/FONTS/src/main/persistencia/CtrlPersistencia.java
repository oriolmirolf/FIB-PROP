package FONTS.src.main.persistencia;

import java.time.LocalDateTime;
import java.util.ArrayList;

import FONTS.src.main.persistencia.excepcions.ErrorIntern;
import FONTS.src.main.persistencia.excepcions.NoExisteixPath;
import FONTS.src.main.persistencia.excepcions.PathJaExisteix;
import FONTS.src.main.transversal.Tupla;

public class CtrlPersistencia {
    private CtrlConfiguracio config;
    private GestorDocument gestor;
    private TransformadorArxius transformador;
    /** Constructora buida */
    public CtrlPersistencia (){
        config = new CtrlConfiguracio();
        transformador = null;
        if(config.existeixConfiguracioPers()){
            gestor = new GestorDocument(config.getConfiguracioPers());
        }
        else gestor = new GestorDocument();
    }
    
    /** 
     * @param tit Titol del document
     * @param aut Autor del document
     * @param loc Path on es guarda el document
     * @param dataC Data de creació del Document
     * @param dataD Data de la darrera modificació del document
     * @param cont Contingut del Document
     * @throws PathJaExisteix Excepció que es llença quan ja existeix un Document en la localització donada
     */
    public void guardarNouDocument(String tit, String aut, String loc, LocalDateTime dataC, LocalDateTime dataD, String cont) throws PathJaExisteix{
        gestor.guardarNouDocument(tit, aut, loc, dataC, dataD, cont);
    }
    
    /** 
     * @param tit Titol del document
     * @param aut Autor del document
     * @param loc Path on es guarda el document
     * @param dataC Data de creació del Document
     * @param dataD Data de la darrera modificació del document
     * @param cont Contingut del Document
     */
    public void guardarDocumentExistent(String tit, String aut, String loc, LocalDateTime dataC, LocalDateTime dataD, String cont){
        gestor.guardarDocumentExistent(tit, aut, loc, dataC, dataD, cont);
    }
    
    /** 
     * @param path path del document a eliminar
     * @throws NoExisteixPath Excepció que es llença quan el path donat no existeix en la base de dades de la persistencia
     */
    public void eliminarDocument(String path) throws NoExisteixPath{
        gestor.eliminarDocument(path);
    }
    
    /** 
     * @return Boolean que representa si existeix configuració de domini
     */
    public Boolean existeixConfiguracioDom(){
        return config.existeixConfiguracioDom();
    } 
    
    /** 
     * @param path localització del document a fer la cerca.
     * @return Tupla(String, String, String, LocalDateTime, LocalDateTime, String)
     * @throws NoExisteixPath Excepció que es llença quan path donat no existeix en la base de dades de la persistencia
     */
    public Tupla<String,String, String, LocalDateTime, LocalDateTime, String>getDocument(String path) throws NoExisteixPath{
        return gestor.getDocument(path);
    }
    
    /** 
     * @param info_dom array de bytes que representa la informació de la capa de domini
     */
    public void tancarAplicacio(byte[] info_dom){
        byte[] info_pers = gestor.getState();
        config.tancarAplicacio(info_pers, info_dom);
    }
    
    /** 
     * @return byte[] que representa la informació de la capa de domini
     */
    public byte[] getConfiguracioDom(){
        return config.getConfiguracioDom();
    }
    
    /** 
     * @param pOrigen Path origen del fitxer.
     * @param pDesti Path desti on es vol guardar el .ojmj.
     * @return Tupla(String, String, String, LocalDateTime, LocalDateTime, String) Informació del nou document creat
     * @throws ErrorIntern Excepció que es llença quan hi ha un error intern.
     * @throws PathJaExisteix Excepció que es llença quan el Document ja existeix a la persisténcia.
     */
    public Tupla<String,String, String, LocalDateTime, LocalDateTime, String> importar(String pOrigen, String pDesti) throws ErrorIntern, PathJaExisteix{
        if(pOrigen.endsWith(".txt")) transformador = new TransformadorTextPla();
        else if(pOrigen.endsWith(".json")) transformador = new TransformadorJSON();
        else if(pOrigen.endsWith(".xml"))  transformador =new TransformadorXML();
        else if(pOrigen.endsWith(".pdf"))  transformador = new TransformadorPDF();
        Container cont  = transformador.importar(pOrigen);
        transformador = null;
        gestor.guardarNouDocument(cont.getTitol(), cont.getAutor(), pDesti ,cont.getDataCreacio(), cont.getDataDarreraModificacio(), cont.getContingut());
        try {
            return gestor.getDocument(pDesti);
        } catch (NoExisteixPath e) {
            // Nunca pasará
           return null;
        }
        //Container
        
    }
    
    /** 
     * @param pOrigen Path del fitxer .ojmj
     * @param pDesti path destinació 
     * @throws NoExisteixPath Excepció que es llença quan el fitxer .ojmj no exiteix
     * @throws ErrorIntern Excepció que es llença quan es hi ha un error intern
     */
    public void exportar (String pOrigen, String pDesti) throws NoExisteixPath, ErrorIntern{
        Tupla<String,String, String, LocalDateTime, LocalDateTime, String> info = gestor.getDocument(pOrigen);
        Container cont = new Container(info.getFirst(), info.getSecond(), info.getThird(), info.getFourth(), info.getFifth(), info.getSixth());
        if(pDesti.endsWith(".txt")) transformador = new TransformadorTextPla();
        else if(pDesti.endsWith(".json")) transformador = new TransformadorJSON();
        else if(pDesti.endsWith(".xml"))  transformador =new TransformadorXML();
        else if(pDesti.endsWith(".pdf"))  transformador = new TransformadorPDF();
        transformador.exportar(pDesti, cont);
        transformador = null;
    }
    
    /** 
     * @return ArrayList que conté tot els path de la persistencia
     */
    public ArrayList<String> getAllPath(){
        return gestor.getAllPath();
    }
}
