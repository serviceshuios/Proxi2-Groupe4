-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mar 11 Avril 2017 à 15:09
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `proxiv2`
--

-- --------------------------------------------------------

--
-- Structure de la table `connectionconseiller`
--

CREATE TABLE `connectionconseiller` (
  `idConnection` int(11) NOT NULL,
  `login` varchar(25) DEFAULT NULL,
  `pwd` varchar(25) DEFAULT NULL,
  `idConseiller` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `connectionconseiller`
--
ALTER TABLE `connectionconseiller`
  ADD PRIMARY KEY (`idConnection`),
  ADD KEY `FK_connectionConseiller_idConseiller` (`idConseiller`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `connectionconseiller`
--
ALTER TABLE `connectionconseiller`
  MODIFY `idConnection` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `connectionconseiller`
--
ALTER TABLE `connectionconseiller`
  ADD CONSTRAINT `FK_connectionConseiller_idConseiller` FOREIGN KEY (`idConseiller`) REFERENCES `conseiller` (`idConseiller`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
