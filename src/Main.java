import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Nome
        System.out.println("Inserisci il nome dell'evento a cui vuoi partecipare");
        String titolo = scan.nextLine();
        //Data
        System.out.println("Inserisci la data dell'evento (formato data dd/MM/yyyy)");
        String dataInput = scan.nextLine();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");//permette di inserire una data come stringa
        LocalDate data = LocalDate.parse(dataInput, format);// Converte la stringa in un oggetto LocalDate
        //Posti
        System.out.println("Inserisci i posti presenti per l'evento");
        int numeroPostiTotale = scan.nextInt();

        try {
            Evento eventoBase = new Evento(titolo, data, numeroPostiTotale);
            
            //Prenotazione singola
            System.out.println("Vuoi prenotare un posto? Digita 1 per SI o 0 per NO");
            int prenotazioneSingola = scan.nextInt();
            switch (prenotazioneSingola) {
                case 0:
                    System.out.println("Nessuna prenotazione effettuata");   
                    break;
                case 1:
                    eventoBase.prenota();
                    break;
                default:
                    System.out.println("Scelta errata");
            }
            //Prenotazione multipla
            System.out.println("Vuoi prenotare più posti? Digita 1 per SI o 0 per NO");
            int rispostaPrenotazione = scan.nextInt();
            switch (rispostaPrenotazione) {
                case 0:
                    System.out.println("Nessuna prenotazione aggiuntiva effettuata");
                    break;
                case 1:
                    System.out.println("Quanti posti vuoi aggiungere alla prenotazione?");
                    int prenotazioneMultipla = scan.nextInt();
                    eventoBase.prenotazioniMultiple(prenotazioneMultipla);
                    break;
                default:
                    System.out.println("Scelta errata");//todo eccezione!!!
                    break;
            }
            //Disdetta singola
            System.out.println("Vuoi disdire un posto? Digita 1 per SI o 0 per NO");
            int disdettaSingola = scan.nextInt();
            switch (disdettaSingola) {
                case 0:
                    System.out.println("Nessuna disdetta effettuata");   
                    break;
                case 1:
                    eventoBase.disdici();
                    break;
                default:
                    System.out.println("Scelta errata");
            }
            //Disdetta multipla
            System.out.println("Vuoi disdire più posti? Digita 1 per SI o 0 per NO");
            int rispostaDisdetta = scan.nextInt();
            switch (rispostaDisdetta) {
                case 0:
                    System.out.println("Nessuna disdetta aggiuntiva effettuata");
                    break;
                case 1:
                    System.out.println("Quanti posti vuoi rimuovere dalla prenotazione?");
                    int disdettaMultipla = scan.nextInt();
                    eventoBase.disdetteMultiple(disdettaMultipla);
                    break;
                default:
                    System.out.println("Scelta errata");//todo eccezione!!!
                    break;
            }
            //Stampa data - titolo     
            System.out.println(eventoBase);
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