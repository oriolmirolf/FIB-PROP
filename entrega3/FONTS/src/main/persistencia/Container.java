package FONTS.src.main.persistencia;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Container implements Serializable {
    /**Titol */
    private String titol;
    /**Autor */
    private String autor;
    /**localitzacio */
    private String localitzacio;
    /**data de creacio */
    private LocalDateTime dataCreacio;
    /**data de darrera modificacio*/
    private LocalDateTime dataDarreraModificacio;
    /**contingut*/
    private String contingut;
    /**atribut per serialitzar */
    private static final long serialVersionUID = 1L;

    public Container (String tit, String aut, String loc, LocalDateTime dataC, LocalDateTime dataD, String cont){
        titol = tit;
        autor = aut;
        localitzacio = loc;
        dataCreacio = dataC;
        dataDarreraModificacio = dataD;
        contingut = cont;
    }

    
    /** 
     * @return String que representa el titol.
     */
    public String getTitol() {
        return this.titol;
    }

    
    /** 
     * @param titol nou titol.
     */
    public void setTitol(String titol) {
        this.titol = titol;
    }

    
    /** 
     * @return String que representa el autor.
     */
    public String getAutor() {
        return this.autor;
    }

    
    /** 
     * @param autor nou autor.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    
    /** 
     * @return String que representa el path.
     */
    public String getLocalitzacio() {
        return this.localitzacio;
    }

    
    /** 
     * @param localitzacio path nou.
     */
    public void setLocalitzacio(String localitzacio) {
        this.localitzacio = localitzacio;
    }

    
    /** 
     * @return LocalDateTime que representa la data de creacio.
     */
    public LocalDateTime getDataCreacio() {
        return this.dataCreacio;
    }

    
    /** 
     * @param dataCreacio data de creacionova.
     */
    public void setDataCreacio(LocalDateTime dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    
    /** 
     * @return LocalDateTime que representa la darrera data de modificacio
     */
    public LocalDateTime getDataDarreraModificacio() {
        return this.dataDarreraModificacio;
    }

    
    /** 
     * @param dataDarreraModificacio data de modificacio nova.
     */
    public void setDataDarreraModificacio(LocalDateTime dataDarreraModificacio) {
        this.dataDarreraModificacio = dataDarreraModificacio;
    }


    
    /** 
     * @return String que representa el contingut
     */
    public String getContingut() {
        return this.contingut;
    }

    
    /** 
     * @param contingut contingut nou.
     */
    public void setContingut(String contingut) {
        this.contingut = contingut;
    }
    
}
