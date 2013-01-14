/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osvarrialeluca;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Luca
 */
public class Negozio {
    public int richieste;
    public int richiestaAttuale;
    BabboNatale babboNatale;
    Stack stack = new Stack();
    Stack tempiAttesa = new Stack();
    Lock lockStackFine = new ReentrantLock();
    int dimStack = 0;
    int permessi = 3;
    boolean dormi = true; 
    Semaphore semaforo = new Semaphore(0);
    Lock attesa = null;
    Lock lockHelp = null;               
    Lock richiesta = null;
    boolean risolto;
    boolean elfoDormi = false;
    Semaphore semHelp = new Semaphore(0);
    Semaphore semExit = new Semaphore(0);
    long lunghezza;
    long tempoAttesa = 0;
    long tempoMedio;
    
    public Negozio(int rc){
        this.semHelp = new Semaphore(3);
        this.semExit = new Semaphore(0);
        this.richiesta = new ReentrantLock();
        this.lockHelp = new ReentrantLock();
        this.richiesta = new ReentrantLock();
        this.richieste = rc;
        }
    
    public void richiestaRegalo(){
        if(richieste > 0){
            
                richieste--;
                richiestaAttuale++;
        }
        
    }
    
    
    public void help(String nome, int pid){
     if(this.richieste >= 0){
         try{
            Thread.sleep(20);
            }catch(Exception e){
             System.out.println(e);
            }
         this.lockHelp.lock();
         this.risolto = false;
            try {
                stack.push(pid);
                System.out.println("sono l'"+nome+", c'è stato un guasto con il regalo "+pid);                
                System.out.println("inserisco nello stack: "+stack.lastElement());
                System.out.println("lo stack contiene : "+stack.size()+" elementi");
             } finally {
               lockHelp.unlock();
            }
            if (stack.size() == 3){
            this.dormi = false;
           
            }
            
            if(this.richieste == 0 && !this.stack.isEmpty()){
                 try{
                     Elfo.sleep(100);
                    }catch(Exception e){
                    System.out.println(e);
                    }
            this.risolvi();
            }
        }
    }       
         
    
   
    public void risolvi(){
            lockHelp.lock();
        try {
            while (!stack.isEmpty()){
            System.out.println(" babbo natale aggiusta: "+stack.lastElement());
            stack.pop();
            try{BabboNatale.sleep(200);
               }catch(Exception e){
               System.out.println(e);}
            }
        } finally {
            this.semHelp.release(3);
            lockHelp.unlock();
            }
        this.risolto = true;
        this.dormi = true;
        if(this.richieste == 0 && this.stack.isEmpty()){
        this.tempoMedio = this.tempoAttesa/this.lunghezza;
        System.out.println("il tempo medio di attesa degli elfi è di "
                           +this.tempoMedio+" millisecondi");
        }
    
        
      }
    
    public void attesa(long tempo){
    this.richiesta.lock();
        try {
             this.tempoAttesa = this.tempoAttesa + tempo;
             this.lunghezza ++;
        } finally {
            this.richiesta.unlock();
        }
    }
  }



