CREATE VIEW parcelleTerrain AS
  SELECT
    terrain.titre AS titreTerrain,
    terrain.surface AS surfaceTerrain,
    parcelle.*
  FROM
    terrain
    JOIN parcelle ON terrain.id = parcelle.terrain;

CREATE VIEW derniereDateEtude AS
  SELECT 
    MAX(date) AS date,
    terrain
  FROM
    etude 
    GROUP BY terrain;

CREATE VIEW nombreParcellesTerrains AS
  SELECT
    COUNT(id) AS nombre,
    terrain
  FROM
    parcelle
    GROUP BY terrain;

CREATE VIEW terrainAfficher AS
  SELECT
    terrain.*,
    nombreParcellesTerrains.nombre,
    derniereDateEtude.date
  FROM
    terrain
    JOIN nombreParcellesTerrains ON terrain.id = nombreParcellesTerrains.terrain
    JOIN derniereDateEtude ON terrain.id = derniereDateEtude.terrain;

CREATE VIEW parcellePaysan AS
  SELECT
    parcelle.id AS parcelle,
    paysan.*
  FROM 
    parcelle 
    JOIN paysan ON parcelle.paysan = paysan.id;

CREATE VIEW poidsTotalEngrais AS
  SELECT
    engrais,
    SUM(poids),
    parcelle
  FROM 
    parcelleEngrais
    GROUP BY engrais, parcelle;

CREATE VIEW prixEngrais AS
  SELECT
    engrais,
    SUM(prix) / COUNT(engrais) AS prix
  FROM 
    achatEngrais
    GROUP BY engrais;
