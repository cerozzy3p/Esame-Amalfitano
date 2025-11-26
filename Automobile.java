/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EsercitazionePreEsame;


public class Automobile extends Veicolo {

    public Automobile(String targa) {
        super(targa);
    }
    
    @Override
    public int calcolaPrezzo() {
        return (this.getUscita()-this.getIngresso()+1)*4;
    }

    @Override
    public String toString() {
        return "Automobile{" + super.toString();
    }
    
    
}
