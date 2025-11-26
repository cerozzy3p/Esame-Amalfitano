
package esercitazione02;

/**
 *
 * @author domenico
 */
public class Testo extends Messaggio{
    private String contenuto;

    public Testo(String contenuto, int ora, int minuto, String nome, String numero) {
        super(ora, minuto, nome, numero);
        this.contenuto = contenuto;
    }

    @Override
    public String toString() {
        return super.toString() + ", contenuto=" + contenuto;
    }

    public String getContenuto() {
        return contenuto;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Orario o = clonaIstante();
        Contatto c = clonaMittente();
        return new Testo(contenuto, o.getOra(), o.getMinuto(), c.getNome(), c.getNumero());
    }
}
