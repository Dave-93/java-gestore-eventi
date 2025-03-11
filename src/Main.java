import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static int controlloScelta(Scanner scan, String messaggio){
        int scelta;
        do {
            System.out.println(messaggio);
            while (!scan.hasNextInt()) { //while per verificare se l'input è un int
                System.out.println("Scelta errata. Inserisci un numero valido (1 per SI, 0 per NO)");
                scan.next(); // Scarta l'input errato
            }
            scelta = scan.nextInt();
            if (scelta != 0 && scelta != 1) {
                System.out.println("Scelta errata. Riprova.");
            }
        } while (scelta != 0 && scelta != 1);//il ciclo si ripete finchè non scelgo 0 o 1
        return scelta;
    }
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
            if(controlloScelta(scan, "Vuoi prenotare un posto? Digita 1 per SI o 0 per NO") == 1){
                evento.prenota();
            }else{
                System.out.println("Nessuna prenotazione effettuata");
            }
            //Prenotazione multipla
            if(controlloScelta(scan, "Vuoi prenotare più posti? Digita 1 per SI o 0 per NO") == 1){
                try {
                    System.out.println("Quanti posti vuoi aggiungere alla prenotazione?");
                    if(!scan.hasNextInt()){
                        scan.next();
                        throw new IllegalArgumentException();
                    }else{
                        int prenotazioneMultipla = scan.nextInt();
                        evento.prenotazioniMultiple(prenotazioneMultipla);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Valore non corretto");
                }
            }else{
                System.out.println("Nessuna prenotazione aggiuntiva effettuata");
            }
            //Disdetta singola
            if(controlloScelta(scan, "Vuoi disdire un posto? Digita 1 per SI o 0 per NO") == 1){
                evento.disdici();
            }else{
                System.out.println("Nessuna disdetta effettuata");
            }
            //Disdetta multipla
            if(controlloScelta(scan, "Vuoi disdire più posti? Digita 1 per SI o 0 per NO") == 1){
                try {
                    System.out.println("Quanti posti vuoi disdire dalla prenotazione?");
                    if(!scan.hasNextInt()){
                        scan.next();
                        throw new IllegalArgumentException();
                    }else{
                        int disdettaMultipla = scan.nextInt();
                        evento.disdetteMultiple(disdettaMultipla);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Valore non corretto");
                }
            }else{
                System.out.println("Nessuna disdetta aggiuntiva effettuata");
            }
            //Stampa data - titolo  || dataora - titolo - prezzo   
            System.out.println(evento);
        } catch (IllegalArgumentException e) {
            switch (e.getMessage()) {
                case "Data passata":
                System.err.println(String.format("Mi dispiace, la data dell'evento %s è passata", titolo));
                break;
            case "Posti assenti":
                System.err.println(String.format("Mi dispiace, non ci sono più posti per l'evento %s", titolo));
                break;
            case "Evento non disponibile":
                System.err.println("Mi dispiace ma non è presente questa tipologia di evento");
                break;
            }
        }finally{
            scan.close();
        }
    }
}