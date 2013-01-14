/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osvarrialeluca;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BabboNatale extends Thread{
Negozio negozio;


public BabboNatale(String nome, Negozio negozio){
super(nome);

this.negozio = negozio;
    }
public void run(){
    
    while(this.negozio.lavora){
        try {
          this.negozio.SemBN.acquire(); 
        } catch (InterruptedException e) {
            this.negozio.termina();
        }
           this.negozio.risolvi();
        }
    
}}
