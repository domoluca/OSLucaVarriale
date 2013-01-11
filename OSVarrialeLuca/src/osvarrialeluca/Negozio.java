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
    Stack stackFine;
    Lock lockStackFine = new ReentrantLock();
    int dimStack = 0;
    int permessi = 3;
    boolean dormi = true; 
    Semaphore semaforo = new Semaphore(0);
    Lock lockHelp = new ReentrantLock();
    Lock richiesta = new ReentrantLock();
    boolean puoiRichiedere = true;
    boolean elfoDormi = false;
    Semaphore semHelp = new Semaphore(0);
    
    public Negozio(int rc){
        this.semHelp = new Semaphore(3);
        this.richieste = rc;
        //BabboNatale babboNatale = new BabboNatale("", this);
        //Stack stack = new Stack();
        //Semaphore semaforo = new Semaphore(3);
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
    
    
    
    public void help(int pid, String nome){
     if(this.richieste > 0){
        this.lockHelp.lock();
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
             

   //  }
     //else { 
       // this.dormi = false;
        //System.out.println("help2");
        //lockHelp.lock();
     }
     // if (richieste == 0 && !stack.isEmpty()){
     //System.out.println("help3");
     //}
   }
    
  
    
    
    public void risolvi(){
     lockHelp.lock();
        try {
            while (stack.size() != 0){
            System.out.println(" babbo natale aggiusta: "+stack.lastElement());
            stack.pop();
            try{BabboNatale.sleep(200);
               }catch(Exception e){
               System.out.println(e);}
        
            //System.out.println(" babbo natale aggiusta: "+negozio.stack.get(1));
            //negozio.stack.pop();
            System.out.println(" babbo natale aggiusta: "+stack.lastElement());
            stack.pop();
            try{BabboNatale.sleep(200);
               }catch(Exception e){
               System.out.println(e);}
            
            //System.out.println(" babbo natale aggiusta: "+negozio.stack.get(0));
            //negozio.stack.pop();
            System.out.println(" babbo natale aggiusta: "+stack.lastElement());
            stack.pop();
            try{BabboNatale.sleep(200);
               }catch(Exception e){
               System.out.println(e);}
               //risolvi.unlock();
            }
            } finally {
            
            
            lockHelp.unlock();
            this.dormi=true;
            this.semHelp.release(3);
            }
}
  
   }
    



