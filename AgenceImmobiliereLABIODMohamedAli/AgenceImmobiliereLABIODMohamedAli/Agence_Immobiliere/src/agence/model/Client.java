package agence.model;

public class Client implements Comparable<Client> {

    private int id;
    private String nom;
    private String prenom;

    public Client(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public int compareTo(Client c) {
        int res = this.nom.compareToIgnoreCase(c.nom);
        if (res == 0) {
            return this.prenom.compareToIgnoreCase(c.prenom);
        }
        return res;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }
}
