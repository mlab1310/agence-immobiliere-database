package agence.model;

public class Appartement extends Bien {

    private int nbChambres;
    private int etage;
    private boolean meuble;
    private boolean balcon;

    public Appartement(String reference, String adresse, double tarifMensuel, double surfaceTotale,
                       int nbChambres, int etage, boolean meuble, boolean balcon) {
        super(reference, adresse, tarifMensuel, surfaceTotale);
        this.nbChambres = nbChambres;
        this.etage = etage;
        this.meuble = meuble;
        this.balcon = balcon;
    }

    @Override
    public String toString() {
        return "Appartement " + reference + " (" + surfaceTotale + " m²)";
    }
}
