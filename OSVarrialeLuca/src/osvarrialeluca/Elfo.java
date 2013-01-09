/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osvarrialeluca;
//import java.util.logging.Logger;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Elfo extends Thread{
    Negozio negozio;
    private String nome;
    Lock richieste = new ReentrantLock();
    
    public Elfo(Negozio ne, String nome){
    super(nome);
    this.negozio = ne;
    this.nome = nome;
    
    
    }
    
    public void run(){
    System.out.println("sono l'"+this.nome+" e sono partito");   
    while(negozio.richieste > 0){
        int possibileGuasto = (int)(Math.random()*99);
        negozio.richiestaRegalo();
        int pid = negozio.richiestaAttuale;
        if (possibileGuasto > 44){
        produci(pid);
        }
        else{
            System.out.println("sono l'"+nome+", c'è stato un guasto con il regalo "+pid);
            //negozio.richiestaAttuale++;
            //negozio.richieste--;
            richiediAiuto(pid);
        }
        
   
    }
    
    }
    
    
    public void produci(int pid){
        System.out.println("sono l'"+nome+" e servo la richiesta "+pid);
        int eseguo = (int) (Math.random() * 101) + 100;  
        try{         
            Thread.sleep(eseguo);
            }catch(Exception e){
            System.out.println(e);
            }
    }
    
    public void richiediAiuto(int pid){
        if (negozio.richieste > 0){
        try {
            negozio.semaforo.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Elfo.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        negozio.help(pid);
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Elfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
        
        
        }
        
    }
    

    

}