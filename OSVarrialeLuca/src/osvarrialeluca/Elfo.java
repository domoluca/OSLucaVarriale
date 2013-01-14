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
    long attesa = 0;
    long lunghezza;
    long mediaAttesa;
    Lock richieste = new ReentrantLock();
    
    public Elfo(Negozio ne, String nome){
    super(nome);
    this.negozio = ne;
    this.nome = nome;
    }
    
    public void run(){
    System.out.println("sono l'"+this.nome+" e sono partito");   
    while(this.negozio.richieste > 0){
        int possibileGuasto = (int)(Math.random()*99);
        this.negozio.richiesta.lock();
        this.negozio.richiestaRegalo();
        int pid = this.negozio.richiestaAttuale;
        this.negozio.richiesta.unlock();
        if (possibileGuasto > 44){
        produci(pid);
        }
        else{
            
            try {
                 this.negozio.semHelp.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Elfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            long temp = System.currentTimeMillis();
            this.negozio.help(nome, pid);
            while(this.negozio.risolto == false){
                try{
                     Elfo.sleep(0);
                    }catch(Exception e){
                    System.out.println(e);
                    }
          }
            long temp1 = System.currentTimeMillis();
            attesa = attesa + (temp1 - temp);
            lunghezza++;
            this.negozio.attesa(attesa);
    }  
    }
    if(!this.negozio.stack.isEmpty()){
            this.negozio.risolvi();
    }
            mediaAttesa = attesa/lunghezza;
            System.out.println("*************"+
                    " l'elf0 "+nome+"ha terminato "+
                    "in media ha atteso per "+mediaAttesa+" millisecondi");
            
    
    
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
    

}
