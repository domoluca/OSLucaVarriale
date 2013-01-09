/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osvarrialeluca;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
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
    Semaphore semaforo = new Semaphore(3);
    Lock richiesta = new ReentrantLock();
    
    
    public Negozio(int rc){
        this.richieste = rc;
        BabboNatale babboNatale = new BabboNatale("", this);
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
    
    public void help(int pid){
    //babboNatale.stampa();
    
    //BabboNatale babboNatale = new BabboNatale("", this);
       
    
   
    if (richieste > 0 && dimStack < 3){
    
    try{
        
        semaforo.acquire();  
        stack.push(pid);
        System.out.println("help1");
        dimStack++;
    }catch(Exception e){
            System.out.println(e);
        }  
    /* if(!stack.isEmpty()){ 
        System.out.println("stack non vuoto");}
        else{ System.out.println("stack vuoto");
        }*/
    //
    
    }
    else if(dimStack == 4){
        
        dormi = false;
        System.out.println("help2");
        //babboNatale.risolvi();
        
        }
    
    else {
        
        dormi = false;
        //babboNatale.risolvi(stack);
        //svuotare lo stack 
        // e riempire il nuovo stack con anche le ulteriori
        //richieste pervenute
    }
    }
    
    public void risolvi(){
     Lock risolvi = new ReentrantLock();
     risolvi.lock();
     System.out.println(" babbo natale aggiusta: "+stack.pop());
     //negozio.stack.pop();
     try{BabboNatale.sleep(200);
        }catch(Exception e){
        System.out.println(e);}
 
     //System.out.println(" babbo natale aggiusta: "+negozio.stack.get(1));
     //negozio.stack.pop();
     System.out.println(" babbo natale aggiusta: "+stack.pop());
     try{BabboNatale.sleep(200);
        }catch(Exception e){
        System.out.println(e);}
     
     //System.out.println(" babbo natale aggiusta: "+negozio.stack.get(0));
     //negozio.stack.pop();
     System.out.println(" babbo natale aggiusta: "+stack.pop());
     try{BabboNatale.sleep(200);
        }catch(Exception e){
        System.out.println(e);}
     
        
        try{
       semaforo.release(3);
       //semaforo.release();
       //semaforo.release();
        }catch(Exception e){
            System.out.println(e);
        }  
       dimStack = 0;
       dormi = true;
       risolvi.unlock();
}
   
    


}
