package esercitazione02;

import java.util.Objects;

/**
 *
 * @author domenico
 */
public class Orario  implements Comparable<Orario>, Cloneable {
    private int ora;
    private int minuto;

    public Orario(int ora, int minuto) {
        this.ora = ora;
        this.minuto = minuto;
    }

    public int getOra() {
        return ora;
    }

    public int getMinuto() {
        return minuto;
    }

    @Override
    public String toString() {
        return "ora=" + ora + ", minuto=" + minuto;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Orario other = (Orario) obj;
        return ora == other.ora && minuto == other.minuto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ora, minuto);
    }

    @Override
    public int compareTo(Orario o) {
        if (this.ora != o.ora)
            return this.ora - o.ora;
        return this.minuto - o.minuto;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
