CREATE DATABASE mais;
\c mais;


CREATE SEQUENCE incPaysan;
CREATE SEQUENCE incTerrain;
CREATE SEQUENCE incParcelle;
CREATE SEQUENCE incEngrais;


CREATE TABLE paysan (
  id VARCHAR(30) PRIMARY KEY,
  nom VARCHAR(50) NOT NULL,
  age INTEGER NOT NULL
);


CREATE TABLE hauteur (
  max NUMERIC NOT NULL
);


CREATE TABLE prix (
  valeur NUMERIC NOT NULL
);


CREATE TABLE terrain (
  id VARCHAR(30) PRIMARY KEY,
  titre VARCHAR(50) NOT NULL,
  surface NUMERIC NOT NULL
);


CREATE TABLE parcelle (
  id VARCHAR(30) PRIMARY KEY,
  titre VARCHAR(50) NOT NULL,
  surface NUMERIC NOT NULL,
  terrain VARCHAR(30) NOT NULL REFERENCES terrain(id),
  paysan VARCHAR(30) NOT NULL REFERENCES paysan(id)
);


CREATE TABLE etude (
  terrain VARCHAR(30) NOT NULL REFERENCES terrain(id),
  parcelle VARCHAR(30) NOT NULL REFERENCES parcelle(id),
  date DATE NOT NULL,
  nombre INT NOT NULL,
  couleur INT NOT NULL,
  hauteur NUMERIC NOT NULL,
  bourgeons INT NOT NULL
);


CREATE TABLE engrais (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nom VARCHAR(50)
);


CREATE TABLE parcelleEngrais (
  engrais INT NOT NULL REFERENCES engrais(id),
  poids DOUBLE NOT NULL,
  date DATE NOT NULL,
  parcelle VARCHAR(20) NOT NULL
);


CREATE TABLE recolte (
  parcelle VARCHAR(20) NOT NULL REFERENCES parcelle(id),
  poids NUMERIC NOT NULL
);


CREATE TABLE achatEngrais (
  engrais INT NOT NULL,
  achat DATE NOT NULL,
  quantite NUMBER NOT NULL,
  prix NUMBER NOT NULL
);







create table effetAdditif (
  min INT NOT NULL,
  max INT NOT NULL,
  effet INT nOT NULL,
  engrais INT NOT NULL
);


1142.85 'p2'
1834.92 'p3'


INSERT INTO effetAdditif VALUES
(0, 25, 0, '1'),
(26, 50, -5, '1'),
(51, 100, 10, '1'),
(0, 25, 10, '2'),
(26, 50, -5, '2'),
(51, 100, -30, '2'),
(0, 25, -10, '3'),
(26, 50, 5, '3'),
(51, 100, 0, '3');








-- postgres
delete from etude;
delete from recolte;
delete from parcelle;
delete from terrain;

-- MYSQL
delete from parcelleEngrais;
delete from engrais;

-- ORACLE
delete from achatEngrais;
