package FONTS.src.main.transversal;

public class Tupla <T1,T2,T3,T4,T5,T6> {
    /**Tipus 1 */
    private T1 first;
    /**Tipus 2 */
    private T2 second;
    /**Tipus 3 */
    private T3 third;
    /**Tipus 4 */
    private T4 fourth;
    /**Tipus 5 */
    private T5 fifth;
    /**Tipus 6 */
    private T6 sixth;
    /** Constructora buida */
    public Tupla(){
        first = null;
        second = null;
        third = null;
        fourth = null;
        fifth = null;
        sixth = null;
     
    }
    /** Constructora amb tots el parametres */
    public Tupla(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6){
        first = t1;
        second = t2;
        third = t3;
        fourth = t4;
        fifth = t5;
        sixth = t6;
       
    }
    
    /** 
     * @return T1 primer atribut de la tupla.
     */
    public T1 getFirst() {
        return first;
    }
    
    /** 
     * @param first nou primer atribut.
     */
    public void setFirst(T1 first) {
        this.first = first;
    }
    
    /** 
     * @return T2 segon atribut de la tupla.
     */
    public T2 getSecond() {
        return second;
    }
    
    /** 
     * @param second nou segon atribut.
     */
    public void setSecond(T2 second) {
        this.second = second;
    }
    
    /** 
     * @return T3 tercer atribut de la tupla.
     */
    public T3 getThird() {
        return third;
    }
    
    /** 
     * @param third nou tercer atribut de la tupla.
     */
    public void setThird(T3 third) {
        this.third = third;
    }
    
    /** 
     * @return T4 quart atribut de la tupla.
     */
    public T4 getFourth() {
        return fourth;
    }
    
    /** 
     * @param fourth nou quart atribut de la tupla.
     */
    public void setFourth(T4 fourth) {
        this.fourth = fourth;
    }
    
    /** 
     * @return T5 cinque atribut de la tupla.
     */
    public T5 getFifth() {
        return fifth;
    }
    
    /** 
     * @param fifth nou cinque atribut de la tupla.
     */
    public void setFifth(T5 fifth) {
        this.fifth = fifth;
    }
    
    /** 
     * @return T6 sise atribut de la tupla.
     */
    public T6 getSixth() {
        return sixth;
    }
    
    /** 
     * @param sixth nou sise atribut de la tupla.
     */
    public void setSixth(T6 sixth) {
        this.sixth = sixth;
    }
}
