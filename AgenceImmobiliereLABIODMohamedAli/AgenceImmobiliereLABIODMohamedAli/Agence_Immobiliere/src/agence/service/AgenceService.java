package agence.service;

import agence.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class AgenceService {

    private ArrayList<Client> clients;
    private ArrayList<Bien> biens;
    private ArrayList<Location> locations;

    public AgenceService() {
        clients = new ArrayList<Client>();
        biens = new ArrayList<Bien>();
        locations = new ArrayList<Location>();
    }

    //CLIENT

    public void ajouterClient(Client c) {
        clients.add(c);
    }

    public void supprimerClient(Client c) {
        clients.remove(c);
    }

    public ArrayList<Client> getClientsTries() {
        Collections.sort(clients);
        return clients;
    }

    public Client rechercherClientParId(int id) {
        for (Client c : clients) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    //BIEN

    public void ajouterBien(Bien b) {
        biens.add(b);
    }

    public void supprimerBien(Bien b) {
        biens.remove(b);
    }

    public ArrayList<Bien> rechercherBiensParSurface(double surfaceMin) {
        ArrayList<Bien> resultat = new ArrayList<Bien>();
        for (Bien b : biens) {
            if (b.getSurfaceTotale() >= surfaceMin) {
                resultat.add(b);
            }
        }
        return resultat;
    }

    public ArrayList<Bien> getBiensLoues() {
        ArrayList<Bien> resultat = new ArrayList<Bien>();
        for (Bien b : biens) {
            if (b.isEnLocation()) {
                resultat.add(b);
            }
        }
        return resultat;
    }

    public ArrayList<Bien> getBiensNonLoues() {
        ArrayList<Bien> resultat = new ArrayList<Bien>();
        for (Bien b : biens) {
            if (!b.isEnLocation()) {
                resultat.add(b);
            }
        }
        return resultat;
    }

    //LOCATION

    public boolean ajouterLocation(Client c, Bien b, LocalDate dateDebut) {
        if (b.isEnLocation()) {
            return false;
        }
        Location l = new Location(c, b, dateDebut);
        locations.add(l);
        return true;
    }

    public void supprimerLocation(Location l) {
        l.terminer();
        locations.remove(l);
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
}
