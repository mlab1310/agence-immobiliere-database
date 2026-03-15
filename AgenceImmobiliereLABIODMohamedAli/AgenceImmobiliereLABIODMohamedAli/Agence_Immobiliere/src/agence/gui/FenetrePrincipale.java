package agence.gui;

import agence.model.*;
import agence.service.AgenceService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FenetrePrincipale extends JFrame implements ActionListener {

    private AgenceService agence;

    private JPanel panelGeneral;
    private JPanel panelGauche;
    private JPanel panelDroite;

    private JTextArea zoneAffichage;
    private JScrollPane scroll;

    private JMenuBar bar;
    private JMenu menuClient;
    private JMenu menuBien;
    private JMenu menuLocation;
    private JMenu menuApplication;

    private JMenuItem itemClientAjouter;
    private JMenuItem itemClientModifier;
    private JMenuItem itemClientSupprimer;
    private JMenuItem itemClientLister;

    private JMenuItem itemBienAjouter;
    private JMenuItem itemBienSupprimer;
    private JMenuItem itemBienRechercheSurface;
    private JMenuItem itemBienLoues;
    private JMenuItem itemBienNonLoues;

    private JMenuItem itemLocationAjouter;
    private JMenuItem itemLocationSupprimer;
    private JMenuItem itemLocationLister;

    private JMenuItem itemQuitter;

    private JButton btnClientAjouter;
    private JButton btnBienAjouter;
    private JButton btnLocationAjouter;
    private JButton btnRefresh;

    public FenetrePrincipale(AgenceService agence) {
        this.agence = agence;

        setTitle("Agence Immobiliere");
        setSize(900, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        construireMenu();
        construirePanel();

        setVisible(true);
    }

    private void construireMenu() {
        bar = new JMenuBar();

        menuClient = new JMenu("Client");
        menuBien = new JMenu("Bien");
        menuLocation = new JMenu("Location");
        menuApplication = new JMenu("Application");

        // Client
        itemClientAjouter = new JMenuItem("Ajouter");
        itemClientModifier = new JMenuItem("Modifier");
        itemClientSupprimer = new JMenuItem("Supprimer");
        itemClientLister = new JMenuItem("Lister (A->Z)");

        itemClientAjouter.addActionListener(this);
        itemClientModifier.addActionListener(this);
        itemClientSupprimer.addActionListener(this);
        itemClientLister.addActionListener(this);

        menuClient.add(itemClientAjouter);
        menuClient.add(itemClientModifier);
        menuClient.add(itemClientSupprimer);
        menuClient.add(itemClientLister);

        // Bien
        itemBienAjouter = new JMenuItem("Ajouter");
        itemBienSupprimer = new JMenuItem("Supprimer");
        itemBienRechercheSurface = new JMenuItem("Rechercher par surface");
        itemBienLoues = new JMenuItem("Afficher biens loues");
        itemBienNonLoues = new JMenuItem("Afficher biens non loues");

        itemBienAjouter.addActionListener(this);
        itemBienSupprimer.addActionListener(this);
        itemBienRechercheSurface.addActionListener(this);
        itemBienLoues.addActionListener(this);
        itemBienNonLoues.addActionListener(this);

        menuBien.add(itemBienAjouter);
        menuBien.add(itemBienSupprimer);
        menuBien.add(itemBienRechercheSurface);
        menuBien.add(itemBienLoues);
        menuBien.add(itemBienNonLoues);

        // Location
        itemLocationAjouter = new JMenuItem("Ajouter");
        itemLocationSupprimer = new JMenuItem("Supprimer");
        itemLocationLister = new JMenuItem("Lister");

        itemLocationAjouter.addActionListener(this);
        itemLocationSupprimer.addActionListener(this);
        itemLocationLister.addActionListener(this);

        menuLocation.add(itemLocationAjouter);
        menuLocation.add(itemLocationSupprimer);
        menuLocation.add(itemLocationLister);

        // Application
        itemQuitter = new JMenuItem("Quitter");
        itemQuitter.addActionListener(this);
        menuApplication.add(itemQuitter);

        bar.add(menuClient);
        bar.add(menuBien);
        bar.add(menuLocation);
        bar.add(menuApplication);

        setJMenuBar(bar);
    }

    private void construirePanel() {
        panelGeneral = new JPanel(new GridLayout(1, 2)); // 2 cases
        panelGauche = new JPanel();
        panelDroite = new JPanel();

        panelGauche.setLayout(new BoxLayout(panelGauche, BoxLayout.PAGE_AXIS));
        panelDroite.setLayout(new BoxLayout(panelDroite, BoxLayout.PAGE_AXIS));

        panelGauche.add(new JLabel("Raccourcis :"));
        panelGauche.add(Box.createRigidArea(new Dimension(0, 10)));

        btnClientAjouter = new JButton("Ajouter Client");
        btnBienAjouter = new JButton("Ajouter Bien");
        btnLocationAjouter = new JButton("Ajouter Location");
        btnRefresh = new JButton("Rafraichir");

        btnClientAjouter.addActionListener(this);
        btnBienAjouter.addActionListener(this);
        btnLocationAjouter.addActionListener(this);
        btnRefresh.addActionListener(this);

        panelGauche.add(btnClientAjouter);
        panelGauche.add(Box.createRigidArea(new Dimension(0, 5)));
        panelGauche.add(btnBienAjouter);
        panelGauche.add(Box.createRigidArea(new Dimension(0, 5)));
        panelGauche.add(btnLocationAjouter);
        panelGauche.add(Box.createRigidArea(new Dimension(0, 5)));
        panelGauche.add(btnRefresh);

        panelGauche.add(Box.createRigidArea(new Dimension(0, 15)));
        panelGauche.add(new JLabel("Utilise le menu en haut"));
        panelGauche.add(new JLabel("pour Modifier/Supprimer/Lister"));

        panelDroite.add(new JLabel("Affichage :"));
        zoneAffichage = new JTextArea(18, 40);
        zoneAffichage.setEditable(false);
        scroll = new JScrollPane(zoneAffichage);
        panelDroite.add(scroll);

        panelGeneral.add(panelGauche);
        panelGeneral.add(panelDroite);

        setContentPane(panelGeneral);
        afficherEtatGeneral();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(itemQuitter)) {
            System.exit(0);
        }

        if (e.getSource().equals(btnClientAjouter) || e.getSource().equals(itemClientAjouter)) {
            actionAjouterClient();
        }

        if (e.getSource().equals(itemClientModifier)) {
            actionModifierClient();
        }

        if (e.getSource().equals(itemClientSupprimer)) {
            actionSupprimerClient();
        }

        if (e.getSource().equals(itemClientLister)) {
            actionListerClients();
        }

        if (e.getSource().equals(btnBienAjouter) || e.getSource().equals(itemBienAjouter)) {
            actionAjouterBien();
        }

        if (e.getSource().equals(itemBienSupprimer)) {
            actionSupprimerBien();
        }

        if (e.getSource().equals(itemBienRechercheSurface)) {
            actionRechercheSurface();
        }

        if (e.getSource().equals(itemBienLoues)) {
            actionAfficherBiensLoues();
        }

        if (e.getSource().equals(itemBienNonLoues)) {
            actionAfficherBiensNonLoues();
        }

        if (e.getSource().equals(btnLocationAjouter) || e.getSource().equals(itemLocationAjouter)) {
            actionAjouterLocation();
        }

        if (e.getSource().equals(itemLocationSupprimer)) {
            actionSupprimerLocation();
        }

        if (e.getSource().equals(itemLocationLister)) {
            actionListerLocations();
        }

        if (e.getSource().equals(btnRefresh)) {
            afficherEtatGeneral();
        }
    }

    private void actionAjouterClient() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Id du client :");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);

            String nom = JOptionPane.showInputDialog(this, "Nom :");
            if (nom == null) return;

            String prenom = JOptionPane.showInputDialog(this, "Prenom :");
            if (prenom == null) return;

            Client c = new Client(id, nom, prenom);
            agence.ajouterClient(c);

            JOptionPane.showMessageDialog(this, "Client ajoute !");
            afficherEtatGeneral();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Id invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actionModifierClient() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Id du client a modifier :");
            if (idStr == null) return;

            int id = Integer.parseInt(idStr);
            Client c = agence.rechercherClientParId(id);

            if (c == null) {
                JOptionPane.showMessageDialog(this, "Client introuvable.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            String nouveauNom = JOptionPane.showInputDialog(this, "Nouveau nom :", c.getNom());
            if (nouveauNom == null) return;

            String nouveauPrenom = JOptionPane.showInputDialog(this, "Nouveau prenom :", c.getPrenom());
            if (nouveauPrenom == null) return;

            c.setNom(nouveauNom);
            c.setPrenom(nouveauPrenom);

            JOptionPane.showMessageDialog(this, "Client modifie !");
            afficherEtatGeneral();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Id invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actionSupprimerClient() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Id du client a supprimer :");
            if (idStr == null) return;

            int id = Integer.parseInt(idStr);
            Client c = agence.rechercherClientParId(id);

            if (c == null) {
                JOptionPane.showMessageDialog(this, "Client introuvable.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int rep = JOptionPane.showConfirmDialog(this,
                    "Supprimer : " + c + " ?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (rep == JOptionPane.YES_OPTION) {
                agence.supprimerClient(c);
                JOptionPane.showMessageDialog(this, "Client supprime !");
                afficherEtatGeneral();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Id invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actionListerClients() {
        ArrayList<Client> liste = agence.getClientsTries();
        StringBuilder sb = new StringBuilder();
        sb.append("CLIENTS (A->Z)\n");
        sb.append("---------------------------\n");
        for (Client c : liste) {
            sb.append("Id=").append(c.getId()).append(" : ").append(c).append("\n");
        }
        zoneAffichage.setText(sb.toString());
    }

    private void actionAjouterBien() {
        String[] types = {"Appartement", "Maison", "Garage"};
        String choix = (String) JOptionPane.showInputDialog(
                this,
                "Type de bien :",
                "Ajouter Bien",
                JOptionPane.QUESTION_MESSAGE,
                null,
                types,
                types[0]
        );
        if (choix == null) return;

        try {
            String ref = JOptionPane.showInputDialog(this, "Reference (unique) :");
            if (ref == null) return;

            String adresse = JOptionPane.showInputDialog(this, "Adresse :");
            if (adresse == null) return;

            double tarif = Double.parseDouble(JOptionPane.showInputDialog(this, "Tarif mensuel :"));
            double surface = Double.parseDouble(JOptionPane.showInputDialog(this, "Surface totale :"));

            Bien b = null;

            if (choix.equals("Appartement")) {
                int chambres = Integer.parseInt(JOptionPane.showInputDialog(this, "Nombre de chambres :"));
                int etage = Integer.parseInt(JOptionPane.showInputDialog(this, "Etage :"));
                int meuble = JOptionPane.showConfirmDialog(this, "Meuble ?", "Appartement", JOptionPane.YES_NO_OPTION);
                int balcon = JOptionPane.showConfirmDialog(this, "Balcon ?", "Appartement", JOptionPane.YES_NO_OPTION);

                b = new Appartement(ref, adresse, tarif, surface, chambres, etage,
                        meuble == JOptionPane.YES_OPTION, balcon == JOptionPane.YES_OPTION);
            }

            if (choix.equals("Maison")) {
                int nbEtages = Integer.parseInt(JOptionPane.showInputDialog(this, "Nombre d'etages :"));
                int jardin = JOptionPane.showConfirmDialog(this, "Jardin ?", "Maison", JOptionPane.YES_NO_OPTION);

                b = new Maison(ref, adresse, tarif, surface, nbEtages, jardin == JOptionPane.YES_OPTION);
            }

            if (choix.equals("Garage")) {
                int numero = Integer.parseInt(JOptionPane.showInputDialog(this, "Numero emplacement :"));
                b = new Garage(ref, adresse, tarif, surface, numero);
            }

            agence.ajouterBien(b);
            JOptionPane.showMessageDialog(this, "Bien ajoute !");
            afficherEtatGeneral();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valeur numerique invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actionSupprimerBien() {
        String ref = JOptionPane.showInputDialog(this, "Reference du bien a supprimer :");
        if (ref == null) return;

        Bien cible = null;

        for (Bien b : agence.getBiensNonLoues()) {
            if (b.getReference().equals(ref)) {
                cible = b;
                break;
            }
        }
        if (cible == null) {
            for (Bien b : agence.getBiensLoues()) {
                if (b.getReference().equals(ref)) {
                    cible = b;
                    break;
                }
            }
        }

        if (cible == null) {
            JOptionPane.showMessageDialog(this, "Bien introuvable.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int rep = JOptionPane.showConfirmDialog(this,
                "Supprimer : " + cible + " ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (rep == JOptionPane.YES_OPTION) {
            agence.supprimerBien(cible);
            JOptionPane.showMessageDialog(this, "Bien supprime !");
            afficherEtatGeneral();
        }
    }

    private void actionRechercheSurface() {
        try {
            String s = JOptionPane.showInputDialog(this, "Surface minimale (m²) :");
            if (s == null) return;

            double surfaceMin = Double.parseDouble(s);

            ArrayList<Bien> res = agence.rechercherBiensParSurface(surfaceMin);

            StringBuilder sb = new StringBuilder();
            sb.append("BIENS avec surface >= ").append(surfaceMin).append("\n");
            sb.append("---------------------------\n");
            for (Bien b : res) {
                sb.append(b.toString()).append(" | Loue=").append(b.isEnLocation()).append("\n");
            }
            zoneAffichage.setText(sb.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Surface invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actionAfficherBiensLoues() {
        ArrayList<Bien> res = agence.getBiensLoues();
        StringBuilder sb = new StringBuilder();
        sb.append("BIENS LOUES\n");
        sb.append("---------------------------\n");
        for (Bien b : res) {
            sb.append(b.toString())
              .append(" | Date debut : ")
              .append(b.getDateDebutLocation())
              .append("\n");
        }
        zoneAffichage.setText(sb.toString());
    }

    private void actionAfficherBiensNonLoues() {
        ArrayList<Bien> res = agence.getBiensNonLoues();
        StringBuilder sb = new StringBuilder();
        sb.append("BIENS NON LOUES\n");
        sb.append("---------------------------\n");
        for (Bien b : res) {
            sb.append(b.toString()).append("\n");
        }
        zoneAffichage.setText(sb.toString());
    }

    private void actionAjouterLocation() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Id client :");
            if (idStr == null) return;
            int id = Integer.parseInt(idStr);

            Client c = agence.rechercherClientParId(id);
            if (c == null) {
                JOptionPane.showMessageDialog(this, "Client introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String ref = JOptionPane.showInputDialog(this, "Reference du bien :");
            if (ref == null) return;

            Bien bienChoisi = null;
            for (Bien b : agence.getBiensNonLoues()) {
                if (b.getReference().equals(ref)) {
                    bienChoisi = b;
                    break;
                }
            }

            if (bienChoisi == null) {
                JOptionPane.showMessageDialog(this, "Bien introuvable ou deja loue.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String dateStr = JOptionPane.showInputDialog(this, "Date debut (AAAA-MM-JJ) :");
            if (dateStr == null) return;

            LocalDate date = LocalDate.parse(dateStr);

            boolean ok = agence.ajouterLocation(c, bienChoisi, date);
            if (!ok) {
                JOptionPane.showMessageDialog(this, "Ce bien est deja en location !", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Location ajoutee !");
            afficherEtatGeneral();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Id invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Date invalide (format AAAA-MM-JJ).", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actionSupprimerLocation() {
        ArrayList<Location> locs = agence.getLocations();
        if (locs.size() == 0) {
            JOptionPane.showMessageDialog(this, "Aucune location.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Locations :\n");
        for (int i = 0; i < locs.size(); i++) {
            Location l = locs.get(i);
            sb.append(i).append(" : ")
              .append(l.getClient())
              .append(" -> ")
              .append(l.getBien().getReference())
              .append(" (").append(l.getDateDebut()).append(")")
              .append("\n");
        }

        try {
            String choix = JOptionPane.showInputDialog(this, sb.toString() + "\nIndex a supprimer :");
            if (choix == null) return;

            int index = Integer.parseInt(choix);

            if (index < 0 || index >= locs.size()) {
                JOptionPane.showMessageDialog(this, "Index invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Location l = locs.get(index);
            agence.supprimerLocation(l);
            JOptionPane.showMessageDialog(this, "Location supprimee !");
            afficherEtatGeneral();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Index invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actionListerLocations() {
        ArrayList<Location> locs = agence.getLocations();
        StringBuilder sb = new StringBuilder();
        sb.append("LOCATIONS\n");
        sb.append("---------------------------\n");
        for (Location l : locs) {
            sb.append(l.getClient())
              .append(" -> ")
              .append(l.getBien().toString())
              .append(" | debut=")
              .append(l.getDateDebut())
              .append("\n");
        }
        zoneAffichage.setText(sb.toString());
    }

    private void afficherEtatGeneral() {
        StringBuilder sb = new StringBuilder();
        sb.append("ETAT GENERAL\n");
        sb.append("===========================\n\n");

        sb.append("Clients : ").append(agence.getClientsTries().size()).append("\n");
        for (Client c : agence.getClientsTries()) {
            sb.append(" - ").append(c.getId()).append(" : ").append(c).append("\n");
        }

        sb.append("\nBiens loues : ").append(agence.getBiensLoues().size()).append("\n");
        for (Bien b : agence.getBiensLoues()) {
            sb.append(" - ").append(b.getReference())
              .append(" | debut=").append(b.getDateDebutLocation())
              .append("\n");
        }

        sb.append("\nBiens non loues : ").append(agence.getBiensNonLoues().size()).append("\n");
        for (Bien b : agence.getBiensNonLoues()) {
            sb.append(" - ").append(b.getReference()).append("\n");
        }

        sb.append("\nLocations : ").append(agence.getLocations().size()).append("\n");

        zoneAffichage.setText(sb.toString());
    }
}
