/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osvarrialeluca;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Elfo extends Thread{
    Negozio negozio; //oggetto Negozio condiviso 
    private String nome; //stringa che identifica il nome dell'elfo
    long attesa = 0;   //
    long lunghezza;    //variabili utilizzate per il calcolo dei tempi di attesa
    long mediaAttesa;  //medi
    
    public Elfo(Negozio ne, String nome){
    super(nome);
    this.negozio = ne;
    this.nome = nome;
    }
    
    public void run(){
    System.out.println("sono l'"+this.nome+" e sono partito");   
    while(this.negozio.richieste > 0){
        //calcolo di un valore casuale tra 0 e 99
        //se il valore ' maggiore di 44 (45%), il regalo verrà prodotto
        //utilizzando il metodo produci()
        //altrimenti verrà chiesto aiuto
        int possibileGuasto = (int)(Math.random()*99);
        //acquisisco il lock che che protegge il metodo
        //richiestaRegalo()
        this.negozio.richiesta.lock();
        this.negozio.richiestaRegalo();
        //assegno un valore assegno un valore identificativo
        //al regalo preso, e chiamerò quel valore identificativo "pid"
        int pid = this.negozio.richiestaAttuale;
        this.negozio.richiesta.unlock();
        if (possibileGuasto > 44){
        produci(pid);
        this.negozio.caricaInSlitta(nome, pid);
        }
        //con una possibilità del 45% avviene un errore
        else{
            //tento di acquisire il semaforo (che avrà 3 permessi)
            //per utilizzare il metodo help()
            try {
                 this.negozio.semHelp.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Elfo.class.getName()).log(Level.SEVERE, null, ex);
            }
            //prima e dopo aver hiesto aiuto prendo il tempo
            //per poter calcolare il tempo medio di attesa dell'elfo
            long temp = System.currentTimeMillis();
            this.negozio.help(nome, pid);
            //finche babboNatale non ha concluso di aggiustare i regali
            //dormo in un ciclo while
            while(this.negozio.risolto == false){
                try{
                     Elfo.sleep(0);
                    }catch(Exception e){
                    System.out.println(e);
                    }
          }
            //calcolo la somma dei tempi di attesa
            //e incremento una variabile che mi indicherà
            //il numero di richieste di aiuto effettuate
            //per poter calcolare la media dei tempi di attesa
            long temp1 = System.currentTimeMillis();
            attesa = attesa + (temp1 - temp);
            lunghezza++;
    }  
    }
    //se quando sono finiti i regali dalla lista dei regali da posizionare
    //sulla slitta, ci sono ancora degli elemeni dello stack,
    //li aggiusto invocando il metodo risolvi
    //che mi permette di aggiustare gli ultmi regali
    if(!this.negozio.stack.isEmpty()){
            this.negozio.risolvi();
    }
            //calcolo il tempo medio di attesa e lo comunico
            mediaAttesa = attesa/lunghezza;
            System.out.println("*************"+
                    " l' "+nome+"ha terminato "+
                    "in media ha atteso per "+mediaAttesa+" millisecondi");
            
    
    
    }
    
    //metodo che simula la produzione del regalo da parte dell'elfo
    //che dormirà per un tempo casuale da 100 a 200 millisecondi per
    //simulare il tempo impiegato per produrre il regalo
    public void produci(int pid){
        
        System.out.println("sono l'"+nome+" e prendo dalla coda dei regali "
                +"il regalo "+pid);
        int eseguo = (int) (Math.random() * 101) + 100;  
        try{         
            Thread.sleep(eseguo);
            }catch(Exception e){
            System.out.println(e);
            }
      }
    

}
