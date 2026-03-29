package com.example.gestionsimpledestudiants_lab15;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestionsimpledestudiants_lab15.classes.Etudiant;
import com.example.gestionsimpledestudiants_lab15.service.EtudiantService;

public class MainActivity extends AppCompatActivity {

    // Déclaration des éléments graphiques de notre interface
    private EditText nom;
    private EditText prenom;
    private Button add;

    private EditText id;
    private Button rechercher;
    private Button supprimer;
    private TextView res;

    // Méthode simple pour vider les champs "Nom" et "Prenom" après un ajout
    void clear() {
        nom.setText("");
        prenom.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Lie ce code avec le fichier design (activity_main.xml)
        setContentView(R.layout.activity_main);

        // On crée une instance de notre service pour manipuler la BDD
        final EtudiantService es = new EtudiantService(this);

        // Ajout par défaut de 5 étudiants si la base est vide (pour tester facilement)
        if (es.findAll().isEmpty()) {
            es.create(new Etudiant("ALAMI", "ALI"));
            es.create(new Etudiant("RAMI", "AMAL"));
            es.create(new Etudiant("SAFI", "MHMED"));
            es.create(new Etudiant("SELAOUI", "REDA"));
            es.create(new Etudiant("ALAMI", "WAFA"));
            Toast.makeText(this, "5 étudiants ajoutés par défaut !", Toast.LENGTH_SHORT).show();
        }

        // On fait le lien entre les variables Java et les IDs déclarés dans le fichier XML
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        add = findViewById(R.id.bn);

        id = findViewById(R.id.id);
        rechercher = findViewById(R.id.load);
        supprimer = findViewById(R.id.delete);
        res = findViewById(R.id.res);

        // --- ACTION 1 : Que faire quand on clique sur "Valider" (Ajouter) ---
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On récupère le texte tapé par l'utilisateur
                String txtNom = nom.getText().toString().trim();
                String txtPrenom = prenom.getText().toString().trim();

                // Sécurité : on vérifie que les champs ne sont pas vides
                if(txtNom.isEmpty() || txtPrenom.isEmpty()){
                    Toast.makeText(MainActivity.this, "Veuillez remplir les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                // On crée et on ajoute l'étudiant dans la BDD
                es.create(new Etudiant(txtNom, txtPrenom));
                clear(); // On vide les champs
                Toast.makeText(MainActivity.this, "Étudiant ajouté", Toast.LENGTH_SHORT).show();
            }
        });

        // --- ACTION 2 : Que faire quand on clique sur "Chercher" ---
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = id.getText().toString().trim();

                if (txt.isEmpty()) {
                    res.setText("");
                    Toast.makeText(MainActivity.this, "Saisir un id", Toast.LENGTH_SHORT).show();
                    return;
                }

                // On cherche l'étudiant en convertissant le texte en nombre entier (Integer)
                Etudiant e = es.findById(Integer.parseInt(txt));

                if (e == null) {
                    res.setText(""); // On vide la zone de résultat
                    Toast.makeText(MainActivity.this, "Étudiant introuvable", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Si on le trouve, on affiche son nom et son prénom
                res.setText(e.getNom() + " " + e.getPrenom());
            }
        });

        // --- ACTION 3 : Que faire quand on clique sur "Supprimer" ---
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = id.getText().toString().trim();

                if (txt.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Saisir un id", Toast.LENGTH_SHORT).show();
                    return;
                }

                Etudiant e = es.findById(Integer.parseInt(txt));

                if (e == null) {
                    Toast.makeText(MainActivity.this, "Aucun étudiant à supprimer", Toast.LENGTH_SHORT).show();
                    return;
                }

                // On le supprime de la BDD
                es.delete(e);
                res.setText(""); // On efface son nom de l'écran
                Toast.makeText(MainActivity.this, "Étudiant supprimé", Toast.LENGTH_SHORT).show();
            }
        });
    }
}