package agence.main;

import agence.gui.FenetrePrincipale;
import agence.model.*;
import agence.service.AgenceService;

public class Main {

    public static void main(String[] args) {

        AgenceService agence = new AgenceService();

        // Donnees de test
        agence.ajouterClient(new Client(1, "Dupont", "Ali"));
        agence.ajouterClient(new Client(2, "Martin", "Sara"));

        agence.ajouterBien(new Appartement("A01", "Lyon 7", 900, 45, 2, 3, true, false));
        agence.ajouterBien(new Maison("M01", "Villeurbanne", 1400, 90, 2, true));
        agence.ajouterBien(new Garage("G01", "Lyon 3", 120, 12, 18));

        new FenetrePrincipale(agence);
    }
}
