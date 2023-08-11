INSERT INTO hauteur
(max)
VALUES
(30);


INSERT INTO prix
(valeur)
VALUES
(1500);


INSERT INTO terrain 
(id, titre, surface)
VALUES
('terrain00000001', 'Ankadifotsy', 3);


INSERT INTO terrain 
(id, titre, surface)
VALUES
('terrain00000002', 'Mahanoro', 1);


INSERT INTO paysan
(id, nom, age)
VALUES
('paysan00000001', 'Rakoto', 28);


INSERT INTO parcelle 
(id, titre, surface, terrain, paysan)
VALUES
('parcelle00000001', 'Parcelle 1', 1, 'terrain00000001', 'paysan00000001'),
('parcelle00000002', 'Parcelle 2', 2, 'terrain00000001', 'paysan00000001');

INSERT INTO parcelle 
(id, titre, surface, terrain, paysan)
VALUES
('parcelle00000003', 'Parcelle 1', 1, 'terrain00000002', 'paysan00000001');


INSERT INTO etude 
(terrain, parcelle, date, nombre, couleur, hauteur, bourgeons)
VALUES
('terrain00000001', 'parcelle00000001', '2023-12-01', '150', '10', '2.5', '2'),
('terrain00000001', 'parcelle00000002', '2023-12-01', '250', '15', '4', '5');

INSERT INTO etude 
(terrain, parcelle, date, nombre, couleur, hauteur, bourgeons)
VALUES
('terrain00000002', 'parcelle00000003', '2023-12-01', '200', '30', '4.5', '4');

INSERT INTO etude 
(terrain, parcelle, date, nombre, couleur, hauteur, bourgeons)
VALUES
('terrain00000002', 'parcelle00000003', '2023-12-15', '180', '20', '4', '3');

INSERT INTO etude 
(terrain, parcelle, date, nombre, couleur, hauteur, bourgeons)
VALUES
('terrain00000001', 'parcelle00000001', '2023-12-15', '150', '35', '4', '3'),
('terrain00000001', 'parcelle00000002', '2023-12-15', '250', '40', '5.5', '4');

INSERT INTO engrais 
(id, nom)
VALUES 
(1, 'Engrais A'), 
(2, 'Engrais B'), 
(3, 'Engrais C');

INSERT INTO parcelleEngrais
(engrais, poids, date, parcelle)
VALUES
('1', 20, '2023-12-01', 'parcelle00000001'),
('2', 10, '2023-12-01', 'parcelle00000001'),
('3', 10, '2023-12-01', 'parcelle00000001'),
('1', 30, '2023-12-01', 'parcelle00000002'),
('2', 30, '2023-12-01', 'parcelle00000002'),
('3', 40, '2023-12-01', 'parcelle00000002');

INSERT INTO parcelleEngrais
(engrais, poids, date, parcelle)
VALUES
('1', 20, '2023-12-10', 'parcelle00000001'),
('2', 10, '2023-12-10', 'parcelle00000001'),
('3', 10, '2023-12-10', 'parcelle00000001'),
('1', 30, '2023-12-10', 'parcelle00000002'),
('2', 30, '2023-12-10', 'parcelle00000002'),
('3', 40, '2023-12-10', 'parcelle00000002');


INSERT INTO recolte 
(parcelle, poids)
VALUES
('parcelle00000001', 450),
('parcelle00000002', 700);
