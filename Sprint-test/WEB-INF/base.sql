CREATE DATABASE exam_spring;
\c exam_spring;


CREATE TABLE voiture (
  id SERIAL PRIMARY KEY,
  modele VARCHAR(50)
);


CREATE TABLE chauffeur (
  id SERIAL PRIMARY KEY,
  nom VARCHAR(50),
  prenom VARCHAR(50)
);


CREATE TABLE trajet (
  id SERIAL PRIMARY KEY,
  depart VARCHAR(50),
  arrivee VARCHAR(50),
  voiture INT REFERENCES voiture(id),
  chauffeur INT REFERENCES chauffeur(id),
  date_depart DATE,
  heure_depart VARCHAR(10),
  etat INT
);


CREATE OR REPLACE VIEW trajetVoiture AS
  SELECT 
    voiture.modele,
    trajet.*
  FROM 
    voiture
    JOIN trajet ON voiture.id = trajet.voiture;


INSERT INTO chauffeur 
(nom, prenom) 
VALUES 
('Rabe', 'Faritra');


INSERT INTO voiture 
(modele)
VALUES
('4L');


CREATE TABLE validTrajet (
  trajet INT REFERENCES trajet(id),
  date_arrivee DATE,
  heure_arrivee VARCHAR(20)
);
