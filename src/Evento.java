import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
    
    //Attributi
    private String titolo;
    private LocalDate data;
    private final int numeroPostiTotale;
    private int numeroPostiPrenotati;

    //Setters
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    //Getters
    public String getTitolo() {
        return titolo;
    }
    public LocalDate getData() {
        return data;
    }
    public int getNumeroPostiTotale() {
        return numeroPostiTotale;
    }
    public int getNumeroPostiPrenotati() {
        System.out.println("i posti prenotati sono " + numeroPostiPrenotati);
        return numeroPostiPrenotati;
    }

    //Costruttore
    public Evento(String titolo, LocalDate data, int numeroPostiTotale){
        this.titolo = titolo;
        this.data = data;
        this.numeroPostiTotale = numeroPostiTotale;
        this.numeroPostiPrenotati = 0;
        controlloData();
        controlloPosti(numeroPostiTotale);
    }

    //Metodi
    //Gestione data
    private void controlloData () /*throws IllegalArgumentException*/{//essendo unchecked può NON essere dichiarato
        if(data.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Data passata");
        }
    }

    //Controllo posti
    private void controlloPosti(int numeroPostiTotale) /*throws IllegalArgumentException*/{//essendo unchecked può NON essere dichiarato
        if(numeroPostiTotale <= 0){
            throw new IllegalArgumentException("Posti assenti");
        }else{
            System.out.println(String.format("Puoi procedere alla prenotazione per l'evento %s", titolo));
        }
    }

    //Prenotazione
    public void prenota(){
        //todo "Gestisco già "un’eccezione" (su controlloData e controlloPosti) che bloccano l'esecuzione del codice a monte se la data è passata o se non ci sono posti. Di conseguenza posso prenotare/disdire solo se DATA e POSTI sono corretti
        //*Gestendo l'eccezione direttamente nel metodo non blocca l'esecuzione del programma
        try{
            if(numeroPostiPrenotati >= numeroPostiTotale){
                throw new IllegalArgumentException();
            }else{
                numeroPostiPrenotati++;
                System.out.println(String.format("Hai prenotato 1 posti e ci sono ancora %d posti liberi", (numeroPostiTotale - numeroPostiPrenotati)));
            }
        } catch (IllegalArgumentException e){
            System.err.println(String.format("Mi dispiace ma ci sono %d posti liberi!", (numeroPostiTotale - numeroPostiPrenotati)));
        }
    }

    //Disdetta
    public void disdici(){
        //todo "Gestisco già "un’eccezione" (su controlloData e controlloPosti) che bloccano l'esecuzione del codice a monte se la data è passata o se non ci sono posti. Di conseguenza posso prenotare/disdire solo se DATA e POSTI sono corretti
        //*Gestendo l'eccezione direttamente nel metodo non blocca l'esecuzione del programma
        try{
            if(numeroPostiPrenotati <= 0){
                throw new IllegalArgumentException();
            }else{
                numeroPostiPrenotati--;
                System.out.println(String.format("Hai disdetto 1 posti e ci sono %d posti liberi", (numeroPostiTotale - numeroPostiPrenotati)));
            }
        }catch (IllegalArgumentException e){
            System.out.println(String.format("Tutti i posti sono liberi, disponibili %d posti", numeroPostiTotale));
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");//permette di formattare la data utilizzando un pattern
        String dataFormattata = data.format(format);// Converte l'oggetto LocalDate in una stringa con la formattazione voluta
        return dataFormattata + " - " + titolo; 
    }
}