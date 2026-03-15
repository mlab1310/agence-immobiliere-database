package agence.model;

public class Garage extends Bien {

    private int numeroEmplacement;

    public Garage(String reference, String adresse, double tarifMensuel, double surfaceTotale,
                  int numeroEmplacement) {
        super(reference, adresse, tarifMensuel, surfaceTotale);
        this.numeroEmplacement = numeroEmplacement;
    }

    @Override
    public String toString() {
        return "Garage " + reference + " (" + surfaceTotale + " m²)";
    }
}
