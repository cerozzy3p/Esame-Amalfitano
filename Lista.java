package esercitazione02;

import java.util.ArrayList;

public class Lista {
    
    private class Nodo {
        private Messaggio data;
        private Nodo next;

        Nodo(Messaggio elem) {
            data = elem;
            next = null;
        }
    }

    private Nodo testa;

    public Lista() {
        testa = null;
    }

    public boolean empty() {
        return testa == null;
    }

    // Inserimento ordinato in base all'orario (compareTo)
    public void inserisciOrdinato(Messaggio nuovo) {
        Nodo nuovoNodo = new Nodo(nuovo);

        if (testa == null || nuovo.compareTo(testa.data) < 0) {
            nuovoNodo.next = testa;
            testa = nuovoNodo; 
            return;
        }

        Nodo corrente = testa;
        while (corrente.next != null && nuovo.compareTo(corrente.next.data) >= 0) {
            corrente = corrente.next; 
        }

        nuovoNodo.next = corrente.next;
        corrente.next = nuovoNodo;
    }

    // Stampa tutti i messaggi
    public void stampa() {
        Nodo temp = testa;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
    
    //alternativa a stampa.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo temp = testa;

        while (temp != null) {
            sb.append(temp.data).append("\n");
            temp = temp.next;
        }

        return sb.toString();
    }

    public void RicercaTesti(String s){
        Nodo temp = testa;
        while(temp!=null) {
             if (temp.data instanceof Testo) {
                 Testo t = (Testo) temp.data;
                 if(t.getContenuto().contains(s)) {
                     System.out.println(t.toString());
                 }
             }
             temp = temp.next;
        }
    }
    
    public ArrayList<Vocale> contattiMaxVocali() throws NessunVocaleException {
        int massimaDurata = 0;
        int numVocali = 0;
        //cerchiamo la massima durata dei vocali. 
        //per costruzione la durata è positiva quindi possiamo imporre
        //il massimo locale pari a 0
        Nodo temp = testa;
        while(temp!=null) {
             if (temp.data instanceof Vocale) {
                 numVocali++;
                 Vocale v = (Vocale) temp.data;
                 if(v.getDurata()>massimaDurata) massimaDurata= v.getDurata();
             }
             temp = temp.next;
        }
        //se non ci sono vocali lanciamo una eccezione altrimenti creiamo 
        //un container contenenti i vocali di durata massima
        if (numVocali==0){
            throw new NessunVocaleException("no Vocali");
        } else {
            ArrayList<Vocale> maxVocali = new ArrayList<Vocale>();
            temp = testa;
            while(temp!=null) {
                if (temp.data instanceof Vocale) {
                    Vocale v = (Vocale) temp.data;
                    if(v.getDurata()==massimaDurata) maxVocali.add(v);
                }
            temp = temp.next;
            }
            return maxVocali;
        }
        
    }

    public ArrayList<Messaggio> esportaChat() {
        ArrayList<Messaggio> copia = new ArrayList<>();
        Nodo temp = testa;
    
        while (temp != null) {
            try {
                copia.add((Messaggio) temp.data.clone()); // deep clone
            } catch (CloneNotSupportedException e) {
                // Puoi gestire l'eccezione come preferisci, qui la rilanciamo come unchecked
                throw new RuntimeException("Errore nella clonazione del messaggio", e);
            }
            temp = temp.next;
        }
        return copia;
    }
    
}

