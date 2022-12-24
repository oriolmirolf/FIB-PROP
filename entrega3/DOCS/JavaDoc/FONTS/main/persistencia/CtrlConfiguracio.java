package FONTS.src.main.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CtrlConfiguracio {
    private final String path_configuracio_persistencia;
    private final String path_configuracio_domini;
   /** Constructora buida */
    public  CtrlConfiguracio (){
        path_configuracio_domini = "./EXE/Dom.ojmj";
        path_configuracio_persistencia = "./EXE/Pers.ojmj";
    }
    
    /** 
     * @param path Path on es guardará l'array de bytes.
     * @param bytes Array de bytes a guardar.
     * @throws IOException Excepción de input/output.
     */
    private static void guardarConfiguracio(String path, byte[] bytes) throws IOException{
        FileOutputStream f = new FileOutputStream(path);
        f.write(bytes);
        f.flush();
        f.close();

    }
    
    /** 
     * @param pers array de bytes amb l'informació de la capa de persisténcia
     * @param dom array de bytes amb l'informació de la capa de domini
     */
    //private static byte[] get
    public void tancarAplicacio (byte[] pers, byte[] dom){
        try {
            guardarConfiguracio(path_configuracio_domini, dom);
            guardarConfiguracio(path_configuracio_persistencia, pers);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            //try que no se hará nunca
        }
    }
    
    /** 
     * @return boolean que indica si existeix configuració de persistencia o no.
     */
    public boolean existeixConfiguracioPers(){

        File fPers = new File(path_configuracio_persistencia);
        return fPers.exists();
    }
    
    /** 
     * @return boolean que indica si existeix configuració de domini o no.
     */
    public boolean existeixConfiguracioDom(){
        File fPers = new File(path_configuracio_domini);
        return fPers.exists();
    }
    
    /** 
     * @return byte[] corresponent a la configuració de domini.
     */
    public byte[] getConfiguracioDom(){
        try {
            FileInputStream f = new FileInputStream(path_configuracio_domini);
            return f.readAllBytes();
        } catch (FileNotFoundException e) {
            //no se ejecutarà si previamente no hay configuración

        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        byte[] a = new byte[0];
        return a;
    }
    
    /** 
     * @return byte[] corresponent a la configuració de persistencia.
     */
    public byte[] getConfiguracioPers(){
        try {
            FileInputStream f = new FileInputStream(path_configuracio_persistencia);
            return f.readAllBytes();
        } catch (FileNotFoundException e) {
            //no se ejecutarà si previamente no hay configuración

        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        byte[] a = new byte[0];
        return a;
    }
}
