package EsercitazionePreEsame;

import java.util.Comparator;

public class ComparatorPerTarga implements Comparator<Veicolo> {
    public int compare(Veicolo v1, Veicolo v2) {
        return v1.getTarga().compareTo(v2.getTarga());
    }
}
