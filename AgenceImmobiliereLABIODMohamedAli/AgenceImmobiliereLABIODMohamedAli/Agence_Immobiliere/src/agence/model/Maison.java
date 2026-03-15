package agence.model;

public class Maison extends Bien {

    private int nbEtages;
    private boolean jardin;

    public Maison(String reference, String adresse, double tarifMensuel, double surfaceTotale,
                  int nbEtages, boolean jardin) {
        super(reference, adresse, tarifMensuel, surfaceTotale);
        this.nbEtages = nbEtages;
        this.jardin = jardin;
    }

    @Override
    public String toString() {
        return "Maison " + reference + " (" + surfaceTotale + " m²)";
    }
}
