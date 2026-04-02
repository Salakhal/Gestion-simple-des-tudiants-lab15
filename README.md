# 🎓 Gestion Simple des Étudiants - Lab 15


Une application Android native et élégante développée en Java, conçue pour gérer une base de données d'étudiants en utilisant **SQLite**. Ce projet illustre parfaitement les concepts de base de la persistance des données sur Android.

---

## ✨ Fonctionnalités Principales (CRUD)

L'application permet d'effectuer les opérations fondamentales sur une base de données :

* **➕ Création (Create) :** Ajout d'un nouvel étudiant avec son Nom et son Prénom. L'ID est généré automatiquement (Auto-increment).
* **🔍 Lecture (Read) :** Recherche instantanée d'un étudiant spécifique grâce à son identifiant unique (ID).
* **🗑️ Suppression (Delete) :** Suppression définitive d'un profil étudiant de la base de données via son ID.
* **📋 Liste Initiale :** Auto-génération de 5 étudiants par défaut au premier lancement pour faciliter les tests.

---

## 🛠️ Architecture et Technologies

Ce projet est structuré de manière propre et modulaire, séparant la logique métier de l'interface utilisateur :

* **Interface Utilisateur (UI) :** `activity_main.xml` conçu avec un design moderne basé sur des *CardViews* pour une meilleure expérience utilisateur (Material Design).
* **Modèle de données :** `Etudiant.java` (POJO représentant l'entité).
* **Gestion de la Base de Données :** `MySQLiteHelper.java` (Création et mise à jour de la table `etudiant`).
* **Couche Service (DAO) :** `EtudiantService.java` (Encapsulation des requêtes SQL : `insert`, `query`, `delete`, `rawQuery`).
* **Langage :** Java
* **IDE :** Android Studio

---
 ## 📸 Captures d'écran et Démo Vidéo

### 📱 Captures d'écran
Voici un aperçu de l'interface élégante de l'application :


<img width="340" height="682" alt="image" src="https://github.com/user-attachments/assets/de10b920-0747-479a-a83b-2d4c3182879f" />



---

### 🎬 Vidéo de Démonstration

Découvrez comment l'application fonctionne en temps réel. Cliquez sur l'image ci-dessous pour lancer la vidéo :







https://github.com/user-attachments/assets/d1a68615-6f4a-4354-9b43-eb0510d238f9




