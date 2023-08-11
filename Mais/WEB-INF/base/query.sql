INSERT INTO terrain
(id, titre, surface)
VALUES
('t1', 'Analavory', 5);


INSERT INTO parcelle
(id, titre, surface, terrain, paysan)
VALUES
('p1', 'P1', 1, 't1', 'paysan00000001'),
('p2', 'P2', 2, 't1', 'paysan00000001'),
('p3', 'P3', 2, 't1', 'paysan00000001');


INSERT INTO recolte
(parcelle, poids)
VALUES
('p1', 1000),
('p2', 1142.85),
('p3', 1834.92);


INSERT INTO etude 
(terrain, parcelle, date, nombre, couleur, hauteur, bourgeons)
VALUES
('t1', 'p1', '2023-07-13', '350', '50', '15', '6'),
('t1', 'p2', '2023-07-13', '600', '50', '12', '5'),
('t1', 'p3', '2023-07-13', '850', '20', '17', '4');



-- MYSQL
INSERT INTO engrais 
(id, nom)
VALUES
('1', 'A'),
('2', 'B'),
('3', 'C');


INSERT INTO parcelleEngrais
(engrais, poids, date, parcelle)
VALUES
('1', '20', '2023-06-01', 'p1'),
('2', '10', '2023-06-01', 'p1'),
('3', '70', '2023-06-01', 'p1'),
('1', '15', '2023-06-16', 'p2'),
('2', '25', '2023-06-16', 'p2'),
('3', '60', '2023-06-16', 'p2'),
('1', '60', '2023-06-30', 'p3'),
('2', '20', '2023-06-30', 'p3'),
('3', '20', '2023-06-30', 'p3');




-- ORACLE
INSERT INTO achatEngrais VALUES
('1', DATE '2023-06-01', 20, 500),
('2', DATE '2023-06-01', 10, 500),
('3', DATE '2023-06-01', 70, 500),
('1', DATE '2023-06-16', 15, 550),
('2', DATE '2023-06-16', 25, 550),
('3', DATE '2023-06-16', 60, 550),
('1', DATE '2023-06-30', 60, 1000),
('2', DATE '2023-06-30', 20, 1000),
('3', DATE '2023-06-30', 20, 1000);




A: 20 + 15 + 60 = 95 -> 200 + 171.42 + 1100 = 1471.2
B: 10 + 25 + 20 = 55 -> 100 + 285 + 366 = 751
C: 70 + 60 + 20 = 150 -> 700 + 685.71 + 367 = 1752