package com.example.gestionsimpledestudiants_lab15.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// SQLiteOpenHelper est une classe d'Android qui facilite la gestion de la base de données.
public class MySQLiteHelper extends SQLiteOpenHelper {

    // Version de la base de données (à augmenter si on change la structure des tables plus tard)
    private static final int DATABASE_VERSION = 1;
    // Le nom du fichier qui sera sauvegardé dans le téléphone
    private static final String DATABASE_NAME = "ecole";

    // La requête SQL pour créer la table "etudiant"
    // "autoincrement" signifie que SQLite donnera les IDs automatiquement (1, 2, 3...)
    private static final String CREATE_TABLE_ETUDIANT =
            "create table etudiant(" +
                    "id INTEGER primary key autoincrement," +
                    "nom TEXT," +
                    "prenom TEXT)";

    // Le constructeur qui initialise la base de données
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Cette méthode est appelée UNE SEULE FOIS, lors de la création de la base de données.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // On exécute la requête SQL pour créer la table
        db.execSQL(CREATE_TABLE_ETUDIANT);
    }

    // Cette méthode est appelée si on change la DATABASE_VERSION (ex: pour ajouter une nouvelle table)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // On supprime l'ancienne table si elle existe
        db.execSQL("DROP table if exists etudiant");
        // On recrée la nouvelle table
        this.onCreate(db);
    }
}