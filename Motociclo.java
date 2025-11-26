/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EsercitazionePreEsame;

public class Motociclo extends Veicolo{
    public Motociclo(String targa) {
        super(targa);
    }
    
    @Override
    public int calcolaPrezzo() {
        return (this.getUscita()-this.getIngresso()+1)*2;
    }
    
    @Override
    public String toString() {
        return "Motociclo{" + super.toString();
    }
}
