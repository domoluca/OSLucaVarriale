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
    Stack stackFine = new Stack();
    Lock lockStackFine = new ReentrantLock();
    int dimStack = 0;
    int permessi = 3;
    boolean dormi = true; 
    Semaphore semaforo = new Semaphore(0);
    Lock lockHelp = new ReentrantLock();
    Lock richiesta = new ReentrantLock();
    boolean risolto;
    boolean elfoDormi = false;
    Semaphore semHelp = new Semaphore(0);
    Semaphore semExit = new Semaphore(0);
    
    public Negozio(int rc){
        this.semHelp = new Semaphore(3);
        this.semExit = new Semaphore(0);
        this.richieste = rc;
        }
    
    public void richiestaRegalo(){
    
    richiesta.lock();
        try {
            richieste--;
            richiestaAttuale++;
        } finally {
            richiesta.unlock();
        }
    }
    
    
    public void help(int pid){
     if(this.richieste > 0){
         try{
            Thread.sleep(2);
            }catch(Exception e){
             System.out.println(e);
            }
         this.lockHelp.lock();
         this.risolto = false;
            try {
                stack.push(pid);
                System.out.println("help1");
                System.out.println("inserisco nello stack: "+stack.lastElement());
                System.out.println("lo stack contiene : "+stack.size()+" elementi");
             } finally {
               lockHelp.unlock();
            }
            if (stack.size() == 3){
            this.dormi = false;
            System.out.println("help2");
            }
        }
      else{
          System.out.println("REGALI FINITI!!!!!");
              this.lockHelp.lock();
            try {
                stack.push(pid);
                System.out.println("ho aggiunto all'ulteriore stack il regalo "
                                    +stack.lastElement());
            } finally {
                this.lockHelp.unlock();
            }
            this.dormi = false;
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
      }
      
}
    



