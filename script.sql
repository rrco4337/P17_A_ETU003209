create database examen_servlet;
use examen_servlet;

create table Prevision(
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL,
    montant INT 
);

create table Depense(
    id INT AUTO_INCREMENT PRIMARY KEY,
    idPrevision INT NOT NULL,
    montantDepense INT,
    FOREIGN KEY (idPrevision) REFERENCES Prevision(id)
);

