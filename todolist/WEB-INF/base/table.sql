CREATE DATABASE todolist;
\c todolist;


CREATE TABLE utilisateur (
  id VARCHAR(30) PRIMARY KEY,
  pseudO VARCHAR(50) NOT NULL,
  mot_de_passe VARCHAR(50) NOT NULL
);


CREATE TABLE imageUtilisateur (
  utilisateur VARCHAR(30) NOT NULL REFERENCES utilisateur(id),
  photo VARCHAR(50) NOT NULL,
  mise_a_jour TIMESTAMP NOT NULL
);


CREATE TABLE langage (
  id VARCHAR(30) PRIMARY KEY,
  nom VARCHAR(50) NOT NULL,
  couleur VARCHAR(30) NOT NULL
);


CREATE TABLE projet (
  id VARCHAR(30) PRIMARY KEY,
  titre VARCHAR(30) NOT NULL,
  description TEXT NOT NULL,
  importance INT NOT NULL,
  creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  langage VARCHAR(30) NOT NULL REFERENCES langage(id),
  utilisateur VARCHAR(30) NOT NULL REFERENCES utilisateur(id)
);


CREATE TABLE tacheEtat (
  id INT PRIMARY KEY,
  nom VARCHAR(50)
);


CREATE TABLE tache (
  id VARCHAR(30) PRIMARY KEY,
  titre VARCHAR(40) NOT NULL,
  description TEXT NOT NULL,
  importance INT NOT NULL,
  estimation INTEGER NOT NULL,
  projet VARCHAR(30) NOT NULL REFERENCES projet(id),
  etat INT NOT NULL REFERENCES tacheEtat(id)
);