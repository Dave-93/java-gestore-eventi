import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concerto extends Evento{
    
    //Attributi
    private LocalTime ora;
    private float prezzo;

    //Getters e Setters
    public LocalTime getOra() {
        return ora;
    }
    public void setOra(LocalTime ora) {
        this.ora = ora;
    }
    public float getPrezzo() {
        return prezzo;
    }
    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }
    
    //Costruttore
    public Concerto(String titolo, LocalDate data, int numeroPostiTotale, LocalTime ora, float prezzo){
        super(titolo, data, numeroPostiTotale);
        this.ora = ora;
        this.prezzo = prezzo;
    }

    //Metodi
    //Data e Ora formattata
    private String formattaDataOra(){
        LocalDate dataDaFormattare = getData();
        LocalTime oraDaFormattare = ora;

        LocalDateTime dataOra = dataDaFormattare.atTime(oraDaFormattare);
        DateTimeFormatter formatDataOra = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormattata = dataOra.format(formatDataOra);
        return dataFormattata;
    }

    //Prezzo formattato
    private String formattaPrezzo(){
        String prezzoFormattato = String.format("%.2f", prezzo).replace(".", ",");
        return prezzoFormattato;
    }

    @Override
    public String toString() {
        return formattaDataOra() + " - " + getTitolo() + " - " /* prezzo formattato */;
    }
}