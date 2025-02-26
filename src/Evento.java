import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
    
    //Attributi
    private String titolo;
    private LocalDate data;
    private final int numeroPostiTotale;//con FINAL essere assegnato solo una volta(nel costruttore) e non può più essere modificato
    private int numeroPostiPrenotati;

    //Setters
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public void setData(LocalDate data) {
        controlloData(data);//mi assicuro che se la data viene modificata dopo la creazione dell'oggetto non può essere antecedente
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
        return numeroPostiPrenotati;
    }

    //Costruttore
    public Evento(String titolo, LocalDate data, int numeroPostiTotale){
        this.titolo = titolo;
        this.data = data;
        this.numeroPostiTotale = numeroPostiTotale;
        this.numeroPostiPrenotati = 0;
        controlloData(data);
        controlloPosti(numeroPostiTotale);
    }

    //Metodi
    //Gestione data
    private void controlloData (LocalDate data) /*throws IllegalArgumentException*/{//essendo unchecked può NON essere dichiarato
        if(data.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Data passata");
        }
    }

    //Controllo posti
    private void controlloPosti(int numeroPostiTotale) /*throws IllegalArgumentException*/{//essendo unchecked può NON essere dichiarato
        if(numeroPostiTotale <= 0){
            throw new IllegalArgumentException("Posti assenti");
        }else{
            System.out.println(String.format("Puoi procedere con la prenotazione di %s", titolo));
        }
    }

    //Prenotazione
    public void prenota(){
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

    //Prenotazione multipla
    public void prenotazioniMultiple(int numeroPrenotazioni) {
        for(int i = 0; i < numeroPrenotazioni; i++) {
               prenota();//richiamo il metodo n volte ed così da fare l'inserimento e tutti i controlli necessari
        }
    }

    //Disdetta
    public void disdici(){
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

     //Disdetta multipla
     public void disdetteMultiple(int numeroPrenotazioni){
        for(int i = 0; i < numeroPrenotazioni; i++) {
            disdici();
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");//permette di formattare la data utilizzando un pattern
        String dataFormattata = data.format(format);// Converte l'oggetto LocalDate in una stringa con la formattazione voluta
        return dataFormattata + " - " + titolo; 
    }
}