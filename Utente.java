package EsercitazionePreEsame;

import java.util.Scanner;
import java.io.*;

public class Utente {

    public static void main(String[] args) {
        Scanner tastiera = new Scanner(System.in);

        // Assicura che la cartella Files esista
        File dir = new File("Files");
        if (!dir.exists()) {
            dir.mkdir();
        }

        // Inizializza il parcheggio leggendo da file di configurazione
        Parcheggio p = new Parcheggio("Files/config.txt");

        // Crea veicoli
        Motociclo m1 = new Motociclo("ED564TY");
        Motociclo m2 = new Motociclo("ST999ZX");
        Motociclo m3 = new Motociclo("FW192YY");
        Motociclo m4 = new Motociclo("YU765YE");
        Motociclo m5 = new Motociclo("TT888ZZ");

        Automobile a1 = new Automobile("ED567KY");
        Automobile a2 = new Automobile("AA564TY");
        Automobile a3 = new Automobile("BC711UY");
        Automobile a4 = new Automobile("ZX111CV");

        System.out.println("Stato iniziale:");
        p.stampa_stato_interno();
        
        // Inserimento veicoli nella coda di attesa
        // Si inseriscono 6 veicoli per forzare l'eccezione (la coda ne supporta solo 5)
        System.out.println("Inserimento veicoli in coda:");
        inserisciInCoda(p, m1);
        inserisciInCoda(p, m2);
        inserisciInCoda(p, m3);
        inserisciInCoda(p, a1);
        inserisciInCoda(p, a2);
        inserisciInCoda(p, m4); // Questo dovrebbe generare un'eccezione

        System.out.println("Stato coda esterna:");
        p.stampa_stato_coda();

        // Fai entrare 3 veicoli e parcheggiali
        faiEntrareEParcheggia(p, 10); // m1
        faiEntrareEParcheggia(p, 10); // m2
        faiEntrareEParcheggia(p, 10); // m3
        
        // Forza il riempimento del parcheggio con veicoli fino al limite
        try {
            p.accetta(a3);
            p.accetta(a4);
        } catch (ParcheggioException e) {
            System.out.println("Errore inatteso durante accetta: " + e.getMessage());
        }
        
        faiEntrareEParcheggia(p, 11); // a1
        faiEntrareEParcheggia(p, 11); // a2
        faiEntrareEParcheggia(p, 11); // a3


        // Prova a far entrare un'altra auto
        try {
            Veicolo v = p.faiEntrare(12); // a4 dovrebbe causare eccezione
            p.parcheggia(v);
        } catch (ParcheggioException e) {
            System.out.println("Eccezione attesa durante faiEntrare: " + e.getMessage());
        }
        
        // Uscita di un veicolo
        p.uscita(a1, 16);

        // Ordinamento crescente per targa
        System.out.println("Ordinamento crescente per Targa");
        p.ordinaVeicoliParcheggiati(new ComparatorPerTarga(), true);
        p.stampa_stato_interno();
        // Ordinamento decrescente per ingresso
        System.out.println("Ordinamento Decrescente per Ingresso");
        p.ordinaVeicoliParcheggiati(new ComparatorPerIngresso(), false);
        p.stampa_stato_interno();

        
        // Serializzazione del parcheggio
        System.out.print("Inserisci il nome del file binario per la serializzazione del parcheggio: ");
        String nomefile = tastiera.next();
        salvaSuFileBinario(nomefile, p);

        // Deserializzazione del parcheggio
        Parcheggio p2 = caricaDaFileBinario(nomefile);
        if (p2 != null) {
            System.out.println("Stato del parcheggio deserializzato:");
            p2.stampa_stato_interno();
        }
        
        // Chiude lo scanner per evitare warning
        tastiera.close(); 
    }

    // Metodo che prova ad aggiungere un veicolo alla coda e stampa l'esito
    public static void inserisciInCoda(Parcheggio p, Veicolo v) {
        try {
            p.accetta(v);
            System.out.println("Accettato: " + v.getTarga());
        } catch (ParcheggioException e) {
            System.out.println("Eccezione durante accetta: " + e.getMessage());
        }
    }

    // Metodo che prova a far entrare e parcheggiare un veicolo
    public static void faiEntrareEParcheggia(Parcheggio p, int oraIngresso) {
        try {
            Veicolo v = p.faiEntrare(oraIngresso);
            p.parcheggia(v);
        } catch (ParcheggioException e) {
            System.out.println("Eccezione durante faiEntrare/parcheggia: " + e.getMessage());
        }
    }

    // Serializza l'oggetto Parcheggio nella cartella Files
    public static void salvaSuFileBinario(String nomeFile, Parcheggio p) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Files/" + nomeFile))) {
            oos.writeObject(p);
            System.out.println("Parcheggio salvato correttamente in Files/" + nomeFile);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    // Carica un oggetto Parcheggio dalla cartella Files
    public static Parcheggio caricaDaFileBinario(String nomeFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Files/" + nomeFile))) {
            return (Parcheggio) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errore durante il caricamento: " + e.getMessage());
            return null;
        }
    }
}

