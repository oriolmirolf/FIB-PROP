package FONTS.src.main.persistencia;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

import FONTS.src.main.persistencia.excepcions.*;
import FONTS.src.main.transversal.Tupla;
public class GestorDocument{

    private TreeSet<String> paths;

    
    /** 
     * @param data array de bytes que representa un objecte.
     * @return Objecte que s'agafa de fer la deserialització.
     * @throws IOException Excepció de Input/Output.
     * @throws ClassNotFoundException Excepció quan no es sap la clase que s'está deserialitzant.
     */
    private static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        Object a = is.readObject();
        is.close();
        return a;
    }
    /** Constructora que li arriba en forma de byte array la seva configuració */
    public GestorDocument(byte[] seriBytes){
        try {
            paths = (TreeSet<String>) deserialize(seriBytes);
            
        } catch (Exception e) {
           //en teoria no hay falla
        }
        

    }
    /** Constructora buida */
    public GestorDocument(){
        paths = new TreeSet<>();
    }
    
    /** 
     * @param a Objecte a serialitzar
     * @return byte[] que sorgeix de serialitzar l'objecte donat
     * @throws IOException Excepció de Input/Output.
     */
    private static byte[] serialize(Object a) throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(a);
        out.flush();
        byte[] b = bos.toByteArray();
        out.close();
        return b;
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
        if(paths.contains(loc)) throw new PathJaExisteix();

        Container container = new Container(tit, aut,loc, dataC, dataD,cont);
    
        try {
            FileOutputStream out = new FileOutputStream(loc);
            ObjectOutputStream ser = new ObjectOutputStream(out);
            ser.writeObject(container);
            ser.flush();
            ser.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            
        }
        paths.add(loc);
        

    }
    
    /** 
     * @param tit Titol del document
     * @param aut Autor del document
     * @param loc Path on es guarda el document
     * @param dataC Data de creació del Document
     * @param dataD Data de la darrera modificació del document
     * @param cont Contingut del Document
     */
    public void guardarDocumentExistent (String tit, String aut, String loc, LocalDateTime dataC, LocalDateTime dataD, String cont){
        
        Container container = new Container(tit, aut,loc, dataC, dataD, cont);
        try {
            FileOutputStream out = new FileOutputStream(loc);
            ObjectOutputStream ser = new ObjectOutputStream(out);
            ser.writeObject(container);
            ser.flush();
            ser.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            
        }
    }
    
    /** 
     * @param path path del document a eliminar
     * @throws NoExisteixPath Excepció que es llença quan el path donat no existeix en la base de dades del gestor
     */
    public void eliminarDocument(String path)throws NoExisteixPath{
        if(! paths.contains(path)) throw new NoExisteixPath();
        File fichero = new File(path);
        fichero.delete();
        paths.remove(path);
    }
    
    /** 
     * @return byte[] que representa la configuració actual del gestor
     */
    public byte[] getState(){
        try {
            return serialize(paths);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //no pasa nada
        }
        //este return nunca se va a hacer
        return null;

    }
    
    /** 
     * @param path localització del document a fer la cerca.
     * @return Tupla(String, String, String, LocalDateTime, LocalDateTime, String)
     * @throws NoExisteixPath Excepció que es llença quan path donat no existeix en la base de dades del gestor
     */
    public Tupla<String,String, String, LocalDateTime, LocalDateTime, String> getDocument(String path) throws NoExisteixPath{
        if(! paths.contains(path)) throw new NoExisteixPath();
        
        try {
            FileInputStream f = new FileInputStream(path);
            ObjectInputStream of = new ObjectInputStream(f);
            Container con = (Container) of.readObject();
            Tupla <String,String, String, LocalDateTime, LocalDateTime, String> ret = new Tupla<>();
            ret.setFirst(con.getTitol());
            ret.setSecond(con.getAutor());
            ret.setThird(con.getLocalitzacio());
            ret.setFourth(con.getDataCreacio());
            ret.setFifth(con.getDataDarreraModificacio());
            ret.setSixth(con.getContingut());
            return ret;
        } catch (FileNotFoundException e) {
            
            
        } catch (ClassNotFoundException e) {
            
            
        } catch (IOException e) {
            
        }
        return null;
    }
    
    /** 
     * @return ArrayList que conté tot els path de la persistencia
     */
    public ArrayList<String> getAllPath(){
        return new ArrayList<>(paths);
    } 
    
}
