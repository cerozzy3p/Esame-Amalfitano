import java.util.Scanner;
import java.io.*;


public class ProcessManager {
    public static void main(String [] args){

       Scheduler sched1 = new Scheduler();
       
       
       
        try {
            caricaDaFile(sched1, "config.next");
        } catch (FileNotFoundException e) {
            System.out.println("Il file non esiste!");
        }

  
        //test dei costruttori 
        ProcessoNormale p1 = new ProcessoNormale("FC26", 3341, false);
        ProcessoNormale p2 = new ProcessoNormale("PES 2016", 3389, true);

        ProcessoPrioritario p3 = new ProcessoPrioritario("Caldaia", 7890, false);
        ProcessoPrioritario p4 = new ProcessoPrioritario("Bagno", 3321, false );
        ProcessoPrioritario p5 = new ProcessoPrioritario("Cesso", 3321, false);


        System.out.println("Stampo stato iniziale");

        sched1.stampaStato();


        try {
            sched1.push(p1);
            sched1.append(p2);
            sched1.append(p3);
            sched1.append(p4);
            sched1.aggiungiProcesso(p5); //dovrebbe darmi eccezione

        } catch (SamePidException e) {
            
            e.printStackTrace();
        }

        

        sched1.stampaStato();


        System.out.println("Eseguo il primo");

        try {
            sched1.eseguiProssimo();
        } catch (Exception e) {
            e.printStackTrace();
        }

       System.out.println("Nuova lista: ");
       
        sched1.stampaStato();

        System.out.println("Elimino l'ultimo della lista");

        sched1.prelevaUltimo();

        sched1.stampaStato();



    }


    public static void caricaDaFile(Scheduler sched, String nomefile) throws FileNotFoundException{

            Scanner scanner = new Scanner(new File("Files/" +nomefile));

            //immaginiamo un file testo
            //TIPO;NOME;PID;BOOLEAN

            while (scanner.hasNextLine()) {
                
                String rigo = scanner.nextLine();
                String righe[] = rigo.split(";");

                String tipo = righe[0];
                String nome = righe[1];
                int pid = Integer.parseInt(righe[2]);
                boolean stato = Boolean.valueOf(righe[3]);


                Processo p = null;

                
            if (tipo.equalsIgnoreCase("ProcessoNormale")) {
                p = new ProcessoNormale(nome, pid, stato);
            } else if (tipo.equalsIgnoreCase("ProcessoPrioritario")) {
                p = new ProcessoPrioritario(nome, pid, stato);
            } else System.err.println("TESTO NON RICONOSCIUTO");


            if (p != null) {
                
               sched.append(p);
            }

            }
        
            scanner.close();
    }

}




    

