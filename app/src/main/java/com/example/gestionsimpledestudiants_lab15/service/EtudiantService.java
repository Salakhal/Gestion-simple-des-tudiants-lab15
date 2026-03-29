package com.example.gestionsimpledestudiants_lab15.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.gestionsimpledestudiants_lab15.classes.Etudiant;
import com.example.gestionsimpledestudiants_lab15.util.MySQLiteHelper;

public class EtudiantService {

    // Noms de la table et des colonnes (pour éviter de se tromper en les tapant)
    private static final String TABLE_NAME = "etudiant";
    private static final String KEY_ID = "id";
    private static final String KEY_NOM = "nom";
    private static final String KEY_PRENOM = "prenom";

    private static final String[] COLUMNS = {KEY_ID, KEY_NOM, KEY_PRENOM};

    private final MySQLiteHelper helper;

    public EtudiantService(Context context) {
        this.helper = new MySQLiteHelper(context);
    }

    // Méthode pour AJOUTER un étudiant (INSERT)
    public void create(Etudiant e) {
        // On ouvre la base de données en mode "Écriture"
        SQLiteDatabase db = this.helper.getWritableDatabase();

        // ContentValues est comme une boîte où on range les valeurs avant de les envoyer à la BDD
        ContentValues values = new ContentValues();
        values.put(KEY_NOM, e.getNom());
        values.put(KEY_PRENOM, e.getPrenom());

        // On insère les données et on ferme la connexion
        db.insert(TABLE_NAME, null, values);
        Log.d("insert", e.getNom()); // Affiche un message dans le Logcat
        db.close();
    }

    // Méthode pour MODIFIER un étudiant (UPDATE) - non utilisée dans l'interface, mais utile !
    public void update(Etudiant e) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, e.getId());
        values.put(KEY_NOM, e.getNom());
        values.put(KEY_PRENOM, e.getPrenom());

        // Met à jour la ligne où l'ID correspond à celui de l'étudiant
        db.update(TABLE_NAME, values, "id = ?", new String[]{String.valueOf(e.getId())});
        db.close();
    }

    // Méthode pour CHERCHER un étudiant par son ID (SELECT)
    public Etudiant findById(int id) {
        Etudiant e = null;
        // On ouvre la base en mode "Lecture"
        SQLiteDatabase db = this.helper.getReadableDatabase();

        // Le Cursor est un outil qui lit les résultats de la requête ligne par ligne
        Cursor c = db.query(TABLE_NAME, COLUMNS, "id = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        // Si on trouve un résultat (moveToFirst vérifie s'il y a une première ligne)
        if (c != null && c.moveToFirst()) {
            e = new Etudiant();
            // On récupère les données des colonnes 0 (id), 1 (nom), 2 (prenom)
            e.setId(c.getInt(0));
            e.setNom(c.getString(1));
            e.setPrenom(c.getString(2));
            c.close();
        }
        db.close();
        return e; // Retourne l'étudiant trouvé, ou 'null' s'il n'existe pas
    }

    // Méthode pour SUPPRIMER un étudiant (DELETE)
    public void delete(Etudiant e) {
        SQLiteDatabase db = this.helper.getWritableDatabase();
        // Supprime la ligne où l'ID correspond
        db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(e.getId())});
        db.close();
    }

    // Méthode pour RÉCUPÉRER TOUS les étudiants (SELECT *)
    public List<Etudiant> findAll() {
        List<Etudiant> eds = new ArrayList<>();
        String req = "select * from " + TABLE_NAME;

        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor c = db.rawQuery(req, null);

        // On parcourt toutes les lignes trouvées dans la table
        if (c != null && c.moveToFirst()) {
            do {
                Etudiant e = new Etudiant();
                e.setId(c.getInt(0));
                e.setNom(c.getString(1));
                e.setPrenom(c.getString(2));
                eds.add(e); // On ajoute chaque étudiant à la liste
                Log.d("Liste", e.getId() + " : " + e.getNom() + " " + e.getPrenom());
            } while (c.moveToNext()); // On passe à la ligne suivante
            c.close();
        }
        db.close();
        return eds; // Retourne la liste complète
    }
}