package esercitazione02;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author domenico
 */
public class Gruppo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //1. test costruttori e tostring
        Testo t1 = new Testo("Ciao", 11, 15, "Luigi", "3321");
        //System.out.println(t1.toString());
        Vocale v1 = new Vocale(80, 11, 15, "Luigi", "3321");
        //System.out.println(v1.toString());
        
        //2. test equals
        Testo t2 = new Testo("Arrivederci", 11, 15, "Luigi", "3321");
        System.out.println(v1.equals(t1));
        System.out.println(t2.equals(t1));
        
        boolean risultato = t2.equals(t1);
        if(risultato) {
            System.out.println("uguali");
        } else {
            System.out.println("non uguali");
        }
        //3. Test carica da file
        Lista gruppo = new Lista();
        try {
            leggiDaFile(gruppo);
            //equivalente
            //gruppo.stampa();
            System.out.println(gruppo);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        /* 
        //4. Test RicercaTesti
        Scanner input = new Scanner (System.in);
        System.out.print("Inserisci testo da cercare: ");
        String stringa = input.next();
        gruppo.RicercaTesti(stringa);
        
        //5. Test ContattiMaxVocali
        ArrayList<Vocale> mV = new ArrayList<Vocale>();
        try {
            mV=gruppo.ContattiMaxVocali();
        } catch (MiaEccezione ex) {
            System.out.println(ex.getMessage());
        }
        for(int i=0; i<mV.size(); i++){
            System.out.println(mV.get(i));
        }*/

        //6. Test Clonazione
        // Copia della lista
        ArrayList<Messaggio> copia = gruppo.esportaChat();

        // Modifica del primo messaggio della copia
        if (!copia.isEmpty()) {
            Messaggio m = copia.get(0);
            System.out.println("\n=== Modifico il primo messaggio della copia ===");

            if (m instanceof Testo) {
                Testo t = (Testo) m;
                // Crea un nuovo messaggio modificato manualmente (non puoi modificare direttamente l'oggetto se è immutabile)
                copia.set(0, new Testo("MODIFICATO", t.getOraIstante(), t.getMinutoIstante(), t.getNomeContatto(), t.getNumeroContatto()));
            } else if (m instanceof Vocale) {
                Vocale v = (Vocale) m;
                copia.set(0, new Vocale(999, v.getOraIstante(), v.getMinutoIstante(), v.getNomeContatto(), v.getNumeroContatto()));
            }
        }

        // Mostra che la lista originale non è cambiata
        System.out.println("\n=== Lista originale dopo modifica della copia ===");
        System.out.println(gruppo);

        // Mostra la copia modificata
        System.out.println("=== Copia modificata ===");
        for (Messaggio m : copia) {
            System.out.println(m);
        }
    }
    
    public static void leggiDaFile(Lista l) throws IOException {
        Scanner input = new Scanner(Paths.get("File/gruppo.txt"));
        int messaggi = input.nextInt();
        input.nextLine(); // consuma il newline dopo il numero
    
        for (int i = 0; i < messaggi; i++) {
            String tipo = input.nextLine().trim();
    
            if (tipo.equals("text")) {
                String contenuto = input.next();
                int ora = input.nextInt();
                int minuto = input.nextInt();
                String nome = input.next();
                String numero = input.next();
                input.nextLine(); // consuma newline
                l.inserisciOrdinato(new Testo(contenuto, ora, minuto, nome, numero));
            } else if (tipo.equals("vocale")) {
                int durata = input.nextInt();
                int ora = input.nextInt();
                int minuto = input.nextInt();
                String nome = input.next();
                String numero = input.next();
                input.nextLine(); // consuma newline
                l.inserisciOrdinato(new Vocale(durata, ora, minuto, nome, numero));
            } else {
                System.err.println("Tipo messaggio non riconosciuto: " + tipo);
            }
        }
    
        input.close();
    }
    
    
}
