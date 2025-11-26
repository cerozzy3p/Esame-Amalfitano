package EsercitazionePreEsame;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Parcheggio implements Serializable {
    private final int MAXAUTO = 3;
    private final int MAXMOTO = 2;
    //non c'è su UML l'ho aggiunto io per generalizzabilità
    private final int MAXCODA = MAXAUTO+MAXMOTO;
    
    private int numAuto;
    private int numMoto;
    private final Coda<Veicolo> codaIngresso;
    private final Veicolo[] veicoliPresenti;
    
    public Parcheggio(){
        this.numAuto=0;
        this.numMoto=0;
        this.codaIngresso = new Coda<Veicolo>();
        this.veicoliPresenti = new Veicolo[MAXAUTO+MAXMOTO];
    }
    
    /**
     * Restituisce una copia del vettore dei veicoli presenti nel parcheggio.
     * In questo modo si garantisce l’incapsulamento, evitando che l’esterno possa
     * modificare direttamente lo stato interno del parcheggio.
     */
    public Veicolo[] getVeicoliPresenti() {
        return Arrays.copyOf(veicoliPresenti, veicoliPresenti.length);
    }

    public Parcheggio(String nomefile) {
        this.codaIngresso = new Coda<Veicolo>();
        this.veicoliPresenti = new Veicolo[MAXAUTO + MAXMOTO];
    
        try (Scanner inputfile = new Scanner(new File(nomefile))) {
            int maxAutoDaFile = inputfile.nextInt();
            int maxMotoDaFile = inputfile.nextInt();
    
            if (maxAutoDaFile > MAXAUTO || maxMotoDaFile > MAXMOTO) {
                System.out.println("Numero di veicoli incompatibile con il parcheggio, verrà creato vuoto.");
                this.numAuto = 0;
                this.numMoto = 0;
            } else {
                int contaAuto = 0;
                int contaMoto = 0;
    
                for (int i = 0; i < maxAutoDaFile + maxMotoDaFile; i++) {
                    String classe = inputfile.next();
    
                    if (classe.equalsIgnoreCase("Motociclo")) {
                        String targa = inputfile.next();
                        int ingresso = inputfile.nextInt();
                        Motociclo m = new Motociclo(targa);
                        m.setIngresso(ingresso);
                        veicoliPresenti[i] = m;
                        contaMoto++;
                    } else if (classe.equalsIgnoreCase("Automobile")) {
                        String targa = inputfile.next();
                        int ingresso = inputfile.nextInt();
                        Automobile a = new Automobile(targa);
                        a.setIngresso(ingresso);
                        veicoliPresenti[i] = a;
                        contaAuto++;
                    } else {
                        System.out.println("Tipo di veicolo sconosciuto al rigo " + (i + 1));
                    }
                }
    
                this.numAuto = contaAuto;
                this.numMoto = contaMoto;
    
                System.out.println("File di configurazione letto correttamente.");
            }
    
        } catch (FileNotFoundException e) {
            System.out.println("Errore in apertura del file " + nomefile);
            System.out.println("Viene costruito un parcheggio vuoto.");
            this.numAuto = 0;
            this.numMoto = 0;
    
        } catch (Exception e) {
            System.out.println("Errore durante la lettura del file di configurazione: " + e.getMessage());
            System.out.println("Viene costruito un parcheggio vuoto.");
            this.numAuto = 0;
            this.numMoto = 0;
        }
    }
    

    public int getNumAuto() {
        return numAuto;
    }

    public int getNumMoto() {
        return numMoto;
    }
    
    
    public void accetta(Veicolo v)throws ParcheggioException {
            if(codaIngresso.size()<MAXCODA) {
                codaIngresso.append(v);
            } else {throw new ParcheggioException("Posti in coda terminati");}
    }
    
    // Il metodo preleva un veicolo dalla coda gli assegna un'ora di ingresso e lo
    // aggiunge all'elenco degli autoveicoli del parcheggio
    public Veicolo faiEntrare(int oraIngresso)throws ParcheggioException{
        if(codaIngresso.isEmpty())  throw new ParcheggioException("Coda vuota");
        Veicolo v = codaIngresso.top();
        if((v instanceof Automobile && numAuto < MAXAUTO) || (v instanceof Motociclo && numMoto < MAXMOTO)) {
            v = codaIngresso.pop();
            v.setIngresso(oraIngresso);
        }
        else throw new ParcheggioException("Impossibile estrarre, posti per auto o per moto esauriti!");
        return v;
    }
    
    public void parcheggia (Veicolo v) {
        veicoliPresenti[numAuto+numMoto]=v;
        if (v instanceof Automobile) numAuto++;
        else numMoto++;
    }
    
    public boolean postoDisponibile (Veicolo v) {
        if (v instanceof Automobile) return numAuto<MAXAUTO;
        else return numMoto<MAXMOTO;
    }
    
    public boolean codaAttesaPiena() {
       // return codaIngresso.isFull();
       return (codaIngresso.size() >= MAXAUTO+MAXMOTO);
    }
    
    //il metodo assegna un'ora di uscita e calcola il prezzo da pagare ad un veicolo che sta lasciando il parcheggio.
    public void uscita(Veicolo v, int oraUscita) {
        int posizione = cerca(v);
        if(posizione>-1) {
            veicoliPresenti[posizione].setUscita(oraUscita);
            System.out.println("Uscita");
            System.out.println(veicoliPresenti[posizione].toString());
            System.out.println("pagato: "+ veicoliPresenti[posizione].calcolaPrezzo());
            rimuoviVeicolo(posizione);
        }
        if (v instanceof Automobile) numAuto--;
        else numMoto--;
    }
    
    public void stampa_stato_interno() {
       System.out.println("Motociclette presenti nel parcheggio:" + numMoto);
       System.out.println("Autovetture presenti nel parcheggio:" + numAuto);
       System.out.println("Contenuto completo dell'array veicoliPresenti:");
        for (int i = 0; i < numAuto+numMoto; i++) {
             System.out.println("[" + i + "] = " + veicoliPresenti[i]);
}
    }
    
    //metodo privato che restituisce la posizione di un veicolo che sta sta lasciando il parcheggio.
    private int cerca(Veicolo v){
        int pos = 0;
        while(pos<numAuto+numMoto) {
            if(veicoliPresenti[pos].equals(v)){
                return pos;
            } else pos++;
        }
        return -1;
    }
    //metodo privato che libera la posizione di un veicolo che sta sta lasciando il parcheggio.
    private void rimuoviVeicolo (int posizione){
        for (int i=posizione; i<numAuto+numMoto-1; i++){
            veicoliPresenti[i]=veicoliPresenti[i+1];
        }
    }

    public void stampa_stato_coda() {
        codaIngresso.stampa();
    }

    // Questo metodo ordina i veicoli attualmente presenti nel parcheggio in base
    // al Comparator fornito. Si utilizza un ArrayList per raccogliere i soli veicoli
    // non nulli e sfruttare le API di ordinamento. Infine, i veicoli ordinati
    // vengono ricopiati nell'array originale.
    public void ordinaVeicoliParcheggiati(Comparator<Veicolo> comp, boolean crescente) {
        // Usiamo un ArrayList per raccogliere i soli veicoli non nulli.
        // Questo evita problemi di NullPointerException e ci permette di usare le API di List.
        ArrayList<Veicolo> presenti = new ArrayList<>();
        for (Veicolo v : veicoliPresenti) {
            if (v != null)
                presenti.add(v);
        }

        // Ordiniamo usando il comparator fornito
        presenti.sort(comp);

        // Se richiesto, invertiamo l'ordine per ottenere ordinamento decrescente
        if (!crescente)
            Collections.reverse(presenti);

        // Ricostruiamo l’array veicoliPresenti con i valori ordinati
        Arrays.fill(veicoliPresenti, null);
        for (int i = 0; i < presenti.size(); i++) {
            veicoliPresenti[i] = presenti.get(i);
        }
    }
}
