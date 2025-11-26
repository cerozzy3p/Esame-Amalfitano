package esercitazione02;

/**
 *
 * @author domenico
 */
public class Vocale extends Messaggio {
    private int durata;

    public Vocale(int durata, int ora, int minuto, String nome, String numero) {
        super(ora, minuto, nome, numero);
        this.durata = durata;
    }

    @Override
    public String toString() {
        return super.toString() + ", durata=" + durata;
    }

    public int getDurata() {
        return durata;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        Orario o = clonaIstante();
        Contatto c = clonaMittente();
        return new Vocale(durata, o.getOra(), o.getMinuto(), c.getNome(), c.getNumero());
    }
    
}
