import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Inserisci il nome dell'evento a cui vuoi partecipare");
        String titolo = scan.nextLine();

        System.out.println("Inserisci la data dell'evento (formato data dd/MM/yyyy)");
        String dataInput = scan.nextLine();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");//permette di inserire una data come stringa
        LocalDate data = LocalDate.parse(dataInput, format);// Converte la stringa in un oggetto LocalDate
        
        System.out.println("Inserisci i posti presenti per l'evento");
        int numeroPostiTotale = scan.nextInt();

        try {
            Evento prova = new Evento(titolo, data, numeroPostiTotale);
            prova.prenota();
            prova.prenota();
            prova.prenota();
            
            prova.disdici();
            prova.disdici();
            prova.disdici();
            prova.disdici();

            prova.prenota();
            prova.prenota();
            prova.prenota();
            
            System.out.println(prova);
        } catch (IllegalArgumentException e) {
            switch (e.getMessage()) {
                case "Data passata":
                System.err.println(String.format("Mi dispiace, la data dell'evento %s è passata", titolo));
                break;
            case "Posti assenti":
                System.err.println(String.format("Mi dispiace, non ci sono più posti per l'evento %s", titolo));
                break;
            }
        }finally{
            scan.close();
        }
    }
}