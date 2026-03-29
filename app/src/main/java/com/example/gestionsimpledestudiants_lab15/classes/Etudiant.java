package com.example.gestionsimpledestudiants_lab15.classes;

public class Etudiant {
    // Les attributs (caractéristiques) d'un étudiant.
    // Ils sont "private" pour la sécurité (Encapsulation).
    private int id;
    private String nom;
    private String prenom;

    // Constructeur utilisé pour CRÉER un nouvel étudiant depuis l'interface.
    // On ne demande pas l'ID car la base de données (SQLite) va le générer automatiquement.
    public Etudiant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    // Constructeur vide.
    // Il est OBLIGATOIRE quand on veut récupérer les données de la base de données
    // et reconstruire l'objet Etudiant étape par étape.
    public Etudiant() { }

    // --- Les Getters et Setters ---
    // Ce sont des méthodes qui permettent de lire (get) ou modifier (set) les attributs privés.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}