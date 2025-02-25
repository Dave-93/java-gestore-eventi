import java.time.LocalDate;
import java.time.LocalTime;
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
            //Scelta per creazione oggetto
            System.out.println("Si tratta di un evento base o di un concerto? Digita 1 per EVENTO BASE o 2 per CONCERTO");
            int sceltaEvento = scan.nextInt();
            Evento evento = null;
            if(sceltaEvento == 1){
                //Oggetto Evento
                evento = new Evento(titolo, data, numeroPostiTotale);
            }else if(sceltaEvento == 2){
                //Ora
                System.out.println("Inserisci l'orario del concerto (formato ora HH:mm)");
                scan.nextLine();
                String oraInput = scan.nextLine();
                DateTimeFormatter formatOra = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime ora = LocalTime.parse(oraInput, formatOra);
                //Prezzo
                System.out.println("Inserisci il prezzo del biglietto");
                float prezzo = scan.nextFloat();
                //Oggetto Concerto
                evento = new Concerto(titolo, data, numeroPostiTotale, ora, prezzo);
            }else{
                throw new IllegalArgumentException("Evento non disponibile");
            }
            
            //Prenotazione singola
            System.out.println("Vuoi prenotare un posto? Digita 1 per SI o 0 per NO");
            int prenotazioneSingola = scan.nextInt();
            switch (prenotazioneSingola) {
                case 0:
                    System.out.println("Nessuna prenotazione effettuata");   
                    break;
                case 1:
                    evento.prenota();
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
                    evento.prenotazioniMultiple(prenotazioneMultipla);
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
                    evento.disdici();
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
                    evento.disdetteMultiple(disdettaMultipla);
                    break;
                default:
                    System.out.println("Scelta errata");//todo eccezione!!!
                    break;
            }
            //Stampa data - titolo     
            System.out.println(evento);
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