package agence.model;

import java.time.LocalDate;

public abstract class Bien {

    protected String reference;
    protected String adresse;
    protected double tarifMensuel;
    protected double surfaceTotale;
    protected boolean enLocation;
    protected LocalDate dateDebutLocation;

    public Bien(String reference, String adresse, double tarifMensuel, double surfaceTotale) {
        this.reference = reference;
        this.adresse = adresse;
        this.tarifMensuel = tarifMensuel;
        this.surfaceTotale = surfaceTotale;
        this.enLocation = false;
        this.dateDebutLocation = null;
    }

    public String getReference() {
        return reference;
    }

    public double getSurfaceTotale() {
        return surfaceTotale;
    }

    public boolean isEnLocation() {
        return enLocation;
    }

    public LocalDate getDateDebutLocation() {
        return dateDebutLocation;
    }

    public void louer(LocalDate date) {
        enLocation = true;
        dateDebutLocation = date;
    }

    public void liberer() {
        enLocation = false;
        dateDebutLocation = null;
    }

    @Override
    public abstract String toString();
}
