//Ex1:

public class Vehicule {
    private String marque;
    private String modele;
    private int annee;
    private boolean disponible;

    // Constructeur principal
    public Vehicule(String marque, String modele, int annee, boolean disponible) {
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.disponible = disponible;
    }

    // Constructeur sans disponibilité (par défaut disponible = true)
    public Vehicule(String marque, String modele, int annee) {
        this(marque, modele, annee, true);
    }

    // Getters & Setters
    public String getMarque() { return marque; }
    public String getModele() { return modele; }
    public int getAnnee() { return annee; }
    public boolean isDisponible() { return disponible; }

    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    // Méthode d'affichage
    public void afficher() {
        System.out.println("Véhicule : " + marque + " " + modele + " (" + annee + ") - "
                + (disponible ? "Disponible" : "Loué"));
    }
}


//Ex2:

public class Agence {
    private Vehicule[] vehicules;
    private int compteur;

    public Agence(int taille) {
        vehicules = new Vehicule[taille];
        compteur = 0;
    }

    public void ajouterVehicule(Vehicule v) {
        if (compteur < vehicules.length) {
            vehicules[compteur++] = v;
        } else {
            System.out.println("Aucune place disponible dans l'agence !");
        }
    }

    public void afficherDisponibles() {
        System.out.println("\n--- Véhicules disponibles ---");
        for (Vehicule v : vehicules) {
            if (v != null && v.isDisponible()) {
                v.afficher();
            }
        }
    }
}


//Ex3:

// Voiture
public class Voiture extends Vehicule {
    private int nbPlaces;

    public Voiture(String marque, String modele, int annee, int nbPlaces) {
        super(marque, modele, annee);
        this.nbPlaces = nbPlaces;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("→ Type : Voiture | Places : " + nbPlaces);
    }
}

// Moto
public class Moto extends Vehicule {
    private int cylindree;

    public Moto(String marque, String modele, int annee, int cylindree) {
        super(marque, modele, annee);
        this.cylindree = cylindree;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("→ Type : Moto | Cylindrée : " + cylindree + "cc");
    }
}

// Camion
public class Camion extends Vehicule {
    private double capacite;

    public Camion(String marque, String modele, int annee, double capacite) {
        super(marque, modele, annee);
        this.capacite = capacite;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("→ Type : Camion | Capacité : " + capacite + " tonnes");
    }
}

// Vélo
public class Velo extends Vehicule {
    private String type;

    public Velo(String marque, String modele, int annee, String type) {
        super(marque, modele, annee);
        this.type = type;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("→ Type : Vélo | Catégorie : " + type);
    }
}


//Ex4:

public class TestPolymorphisme {
    public static void main(String[] args) {
        Vehicule[] liste = {
            new Voiture("Toyota", "Corolla", 2020, 5),
            new Moto("Yamaha", "MT-07", 2022, 689),
            new Camion("Renault", "Maxity", 2019, 3.5),
            new Velo("Btwin", "Speed 500", 2023, "Route")
        };

        for (Vehicule v : liste) {
            v.afficher(); // Appelle la bonne méthode selon le type réel
            System.out.println();
        }
    }
}


//Ex5:

public interface Louable {
    void louer();
    void retourner();
}

public class Voiture extends Vehicule implements Louable {
    private int nbPlaces;

    public Voiture(String marque, String modele, int annee, int nbPlaces) {
        super(marque, modele, annee);
        this.nbPlaces = nbPlaces;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("→ Type : Voiture | Places : " + nbPlaces);
    }

    @Override
    public void louer() {
        if (isDisponible()) {
            setDisponible(false);
            System.out.println(getModele() + " louée avec succès !");
        } else {
            System.out.println(getModele() + " déjà louée !");
        }
    }

    @Override
    public void retourner() {
        if (!isDisponible()) {
            setDisponible(true);
            System.out.println(getModele() + " retournée !");
        }
    }
}

public class Moto extends Vehicule implements Louable {
    private int cylindree;

    public Moto(String marque, String modele, int annee, int cylindree) {
        super(marque, modele, annee);
        this.cylindree = cylindree;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("→ Type : Moto | Cylindrée : " + cylindree + "cc");
    }

    @Override
    public void louer() {
        if (isDisponible()) {
            setDisponible(false);
            System.out.println(getModele() + " louée !");
        }
    }

    @Override
    public void retourner() {
        if (!isDisponible()) {
            setDisponible(true);
            System.out.println(getModele() + " retournée !");
        }
    }
}


//Ex6:

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agence agence = new Agence(10);
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n=== MENU AGENCE DE LOCATION ===");
            System.out.println("1. Ajouter un véhicule");
            System.out.println("2. Afficher tous les véhicules disponibles");
            System.out.println("3. Louer un véhicule");
            System.out.println("4. Retourner un véhicule");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Marque : "); String marque = sc.nextLine();
                    System.out.print("Modèle : "); String modele = sc.nextLine();
                    System.out.print("Année : "); int annee = sc.nextInt();
                    sc.nextLine();
                    Vehicule v = new Vehicule(marque, modele, annee);
                    agence.ajouterVehicule(v);
                    break;
                case 2:
                    agence.afficherDisponibles();
                    break;
                case 3:
                    System.out.print("Modèle à louer : ");
                    String m = sc.nextLine();
                    for (Vehicule veh : agence.vehicules) {
                        if (veh instanceof Louable && veh.getModele().equalsIgnoreCase(m)) {
                            ((Louable) veh).louer();
                        }
                    }
                    break;
                case 4:
                    System.out.print("Modèle à retourner : ");
                    String r = sc.nextLine();
                    for (Vehicule veh : agence.vehicules) {
                        if (veh instanceof Louable && veh.getModele().equalsIgnoreCase(r)) {
                            ((Louable) veh).retourner();
                        }
                    }
                    break;
            }

        } while (choix != 0);

        System.out.println("Merci d’avoir utilisé l’agence !");
        sc.close();
    }
}
