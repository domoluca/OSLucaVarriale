/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osvarrialeluca;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Negozio {
    public int richieste; //numero regali ancora da produrre
    public int richiestaAttuale; //contatore utilizzato per assegnare il pid
    BabboNatale babboNatale;
    Stack stack = new Stack();//stack in cui verranno inseriti i regali da aggiustare
    //boolean dormi = true; //variabile che cont
    Lock lockHelp = null; //lock che regola l'accesso allo stack            
    Lock richiesta = null; //lock che regola l'accesso al metodo richiestaRegalo()
    boolean risolto;//variabile su cui dormiranno gli elfi che hanno avuto accerro a Help()
    boolean lavora = true; //variabile per il ciclo while in cui opera BabboNatale
    //boolean elfoDormi = false;
    Semaphore semHelp = new Semaphore(0);//semaforo con 3 permessi che garantisce
                                         //che 3 elfi alla volta facciano richiesta di aiuto
    Semaphore SemBN = new Semaphore(0);
    
    public Negozio(int rc){
        this.semHelp = new Semaphore(3);
        this.SemBN = new Semaphore(0);
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
                System.out.println("++++sono l'"+nome+", c'è stato un guasto con il regalo "+pid);
             } finally {
               lockHelp.unlock();
            }
            if (stack.size() == 3){
            this.SemBN.release(1);
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
            try{BabboNatale.sleep(200);
               }catch(Exception e){
               System.out.println(e);}
            System.out.println(" babbo natale ha agiustato"
                    + "e aggiunto alla slitta: "+stack.lastElement());
            stack.pop();
            }
        } finally {
            
            this.semHelp.release(3);
            lockHelp.unlock();
            }
        this.risolto = true;
      
        }
    
    public void termina(){
    System.out.println("tutti i regali sono nella slitta!!!!!!!!!!!!!!!");
        this.lavora = false;
    
    }
    
    public void caricaInSlitta(String nome, int pid){
    System.out.println(nome+"ha caricato in slitta il regalo "+pid);
    
    }
  }



