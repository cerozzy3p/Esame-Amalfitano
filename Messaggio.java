
package esercitazione02;

import java.util.Objects;

/**
 *
 * @author domenico
 */
public abstract class Messaggio implements Comparable<Messaggio>, Cloneable {
    private Orario istante;
    private Contatto mittente;

    public Messaggio(int ora, int minuto, String nome, String numero) {
        this.istante = new Orario(ora, minuto);
        this.mittente = new Contatto(nome, numero);   
    }    
    
    public String getNomeContatto(){
        return this.mittente.getNome();
    }
    
    public String getNumeroContatto(){
        return this.mittente.getNumero();
    }
    
    public int getOraIstante(){
        return this.istante.getOra();
    }
    
    public int getMinutoIstante() {
        return this.istante.getMinuto();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Messaggio other = (Messaggio) obj;
        return Objects.equals(mittente, other.mittente) &&
               Objects.equals(istante, other.istante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mittente, istante);
    }

    @Override
    public int compareTo(Messaggio other) {
        return this.istante.compareTo(other.istante);
    }
    @Override
    public String toString() {
        return istante.toString() + ", " + mittente.toString();
    }

    // Metodi ausiliari per clonare i componenti, rispettando il contenimento
    protected Orario clonaIstante() throws CloneNotSupportedException {
        return (Orario) istante.clone();
    }

    protected Contatto clonaMittente() throws CloneNotSupportedException {
        return (Contatto) mittente.clone();
    }
    
    @Override
    public abstract Object clone() throws CloneNotSupportedException;
    
}
