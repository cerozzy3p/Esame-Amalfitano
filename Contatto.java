package esercitazione02;

import java.util.Objects;

/**
 *
 * @author domenico
 */
public class Contatto implements Cloneable{
    private String nome;
    private String numero;

    public Contatto(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contatto other = (Contatto) obj;
        return Objects.equals(nome, other.nome) &&
               Objects.equals(numero, other.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, numero);
    }

    @Override
    public String toString() {
        return "nome=" + nome + ", numero=" + numero;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // sufficiente per classi con soli campi primitivi/immutabili
    }
     
}
