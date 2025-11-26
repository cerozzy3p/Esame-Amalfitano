package EsercitazionePreEsame;

import java.util.Comparator;

public class ComparatorPerIngresso implements Comparator<Veicolo> {
    public int compare(Veicolo v1, Veicolo v2) {
        return Integer.compare(v1.getIngresso(), v2.getIngresso());
    }
}

