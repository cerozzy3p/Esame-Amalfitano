package EsercitazionePreEsame;

import java.util.Objects;
import java.io.Serializable;

public abstract class Veicolo implements Serializable {
    private String targa;
    private int ingresso;
    private int uscita;

    public Veicolo(String targa) {
        this.targa = targa;
        this.ingresso = 0;
        this.uscita = 0;
    }

    public void setIngresso(int ingresso) {
        this.ingresso = ingresso;
    }

    public void setUscita(int uscita) {
        this.uscita = uscita;
    }

    public int getIngresso() {
        return ingresso;
    }

    public int getUscita() {
        return uscita;
    }

    public String getTarga() {
        return targa;
    }

    @Override
    public String toString() {
        return "targa=" + targa + ", ingresso=" + ingresso + ", uscita=" + uscita + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(targa);
    }

    /**
     * Ridefinisce il metodo equals per confrontare due veicoli sulla base della loro targa.
     * Il confronto è stretto: due veicoli sono considerati uguali solo se appartengono
     * esattamente alla stessa classe (non è consentito il confronto tra sottoclassi diverse).
     * Si assume che la targa non sia mai null. In alternativa, per rendere il metodo più robusto
     * contro eventuali valori null, si potrebbe usare:
     *     return Objects.equals(this.targa, other.targa);
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veicolo other = (Veicolo) obj;
        return this.targa.equals(other.targa);
    }
    
    public abstract int calcolaPrezzo();
}
