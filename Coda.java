/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EsercitazionePreEsame;
import java.io.Serializable;

public class Coda<T> implements Serializable{
    
    private class Nodo implements Serializable{
        T data;
        Nodo next;

        private Nodo(T elem) {
            data = elem;
            next = null;
        }
    }

    private Nodo testa;
    
    public Coda() {testa=null;}







    // predicati
    public boolean isEmpty() {
        return testa == null;
    }

    public boolean isFull() {
        return false; // mai piena (nessun limite interno)
    }
    
    public T top(){
        return testa.data; // controlla chi è il primo ad uscire
    }

    // inserimento in testa (non usato in coda ma mantenuto per coerenza)
    private void push(T e) {
        Nodo q = new Nodo(e);
        q.next = testa;
        testa = q;
    }

    // inserimento in coda
    public void append(T e) {
        if (isEmpty()) {
            push(e);
        } else {
            Nodo temp = testa;
            Nodo q = new Nodo(e);
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = q;
        }
    }

    // rimozione dalla testa
    public T pop() {
        T e = testa.data;
        testa = testa.next;
        return e;
    }

    // stampa tutti gli elementi
    public void stampa() {
        Nodo temp = testa;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    // conta il numero di elementi
    public int size() {
        int count = 0;
        Nodo temp = testa;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}
    
