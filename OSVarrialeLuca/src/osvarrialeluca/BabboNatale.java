/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osvarrialeluca;
import java.util.*;


public class BabboNatale extends Thread{
Negozio negozio;
boolean dormi = true;
public BabboNatale(String nome, Negozio negozio){
super(nome);

this.negozio = negozio;
    }
public void run(){
    System.out.println("sono babbo natale e sono partito");
    while (this.dormi == true){
        System.out.println("sono babbo natale e sto dormendo");
            try {
                Thread.sleep(100000000);
            }catch (Exception ex) {
                System.out.println(ex);
            }
    }
    System.out.println("Babbo natale si è svegliato");
    

}

public void risolvi(){
     System.out.println(" babbo natale aggiusta: "+negozio.stack.get(2));
     negozio.stack.pop();
     try{BabboNatale.sleep(200);
        }catch(Exception e){
        System.out.println(e);}
 
     System.out.println(" babbo natale aggiusta: "+negozio.stack.get(1));
     negozio.stack.pop();
     try{BabboNatale.sleep(200);
        }catch(Exception e){
        System.out.println(e);}
     
     System.out.println(" babbo natale aggiusta: "+negozio.stack.get(0));
     negozio.stack.pop();
     try{BabboNatale.sleep(200);
        }catch(Exception e){
        System.out.println(e);}
        dormi = true;
        try{
        negozio.semaforo.release();
        negozio.semaforo.release();
        negozio.semaforo.release();
        }catch(Exception e){
            System.out.println(e);
        }  
       negozio.dimStack = 0;
             
}}