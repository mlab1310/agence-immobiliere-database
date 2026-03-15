package agence.model;

import java.time.LocalDate;

public class Location {

    private Client client;
    private Bien bien;
    private LocalDate dateDebut;

    public Location(Client client, Bien bien, LocalDate dateDebut) {
        this.client = client;
        this.bien = bien;
        this.dateDebut = dateDebut;
        bien.louer(dateDebut);
    }

    public Client getClient() {
        return client;
    }

    public Bien getBien() {
        return bien;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void terminer() {
        bien.liberer();
    }
}
