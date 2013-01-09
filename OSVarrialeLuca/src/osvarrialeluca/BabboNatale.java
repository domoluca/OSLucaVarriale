/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package osvarrialeluca;
import java.util.*;


public class BabboNatale extends Thread{
Negozio negozio;


public BabboNatale(String nome, Negozio negozio){
super(nome);

this.negozio = negozio;
    }
public void run(){
    System.out.println("sono babbo natale e sono partito");
    while (negozio.dormi == true){
           try {
                Thread.sleep(1);
            }catch (Exception ex) {
                System.out.println(ex);
            }
    }
    System.out.println("Babbo natale si è svegliato");
    negozio.risolvi();

}


}