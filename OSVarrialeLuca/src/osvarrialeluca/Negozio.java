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
    int richieste;
    int richiestaAttuale;
    BabboNatale babboNatale;
    Stack stack;
    Stack stackFine;
    Lock lockStackFine = new ReentrantLock();
    int dimStack = 0;
    int permessi = 3;
    
    Semaphore semaforo = new Semaphore(permessi);    
    
    
    public Negozio(int rc){
        this.richieste = rc;
       // this.babboNatale = babboNatale;
    }
    
    public void richiestaRegalo(){
    richieste--;
    richiestaAttuale++;
    
    
    }
    
    public void help(int pid){
    Stack stack = new Stack();
    //BabboNatale babboNatale = new BabboNatale("", this);
    Semaphore semaforo = new Semaphore(permessi);    
    System.out.println("help1");
   
    if (richieste > 0 && dimStack < 2){
    try{
        //semaforo.acquire();
        stack.push(pid);
        dimStack++;
    }catch(Exception e){
            System.out.println(e);
        }  
     if(!stack.isEmpty()){ 
        System.out.println("stack non vuoto");}
        else{ System.out.println("stack vuoto");
        }
    
    
    }
    else if(richieste > 0 && dimStack == 2){
        System.out.println("help2");
        babboNatale.dormi = false;
        babboNatale.risolvi();
        
        }
    
    else {
        babboNatale.dormi = false;
        babboNatale.risolvi();
        //svuotare lo stack 
        // e riempire il nuovo stack con anche le ulteriori
        //richieste pervenute
    
    }
    }
   
    //public void helpFine(pid){
    
    
    //}


}
