-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mar 11 Avril 2017 à 20:17
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
-- Structure de la table `adresse`
--

CREATE TABLE `adresse` (
  `idAdresse` int(11) NOT NULL,
  `adresse` varchar(25) DEFAULT NULL,
  `codePostale` int(11) DEFAULT NULL,
  `ville` varchar(25) DEFAULT NULL,
  `idAgence` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `adresse`
--

INSERT INTO `adresse` (`idAdresse`, `adresse`, `codePostale`, `ville`, `idAgence`) VALUES
(1, 'rue massena', 69006, 'lyon', NULL),
(2, 'rue alexis perroncel', 69100, 'vileurbanne', NULL),
(3, 'avenue jean jaures', 69003, 'lyon', 1),
(4, 'cours emile zola', 69100, 'villeurbanne', NULL),
(5, 'cours vitton', 69006, 'lyon', NULL),
(6, 'rue du parc', 69006, 'lyon', NULL),
(7, 'rue duguseclin', 69006, 'lyon', NULL),
(8, 'rue de la tete d\'or', 69006, 'lyon', NULL),
(9, 'rue gervais buissiere', 69100, 'villeurbanne', NULL),
(10, 'rue de l\'opera', 69001, 'lyon', NULL),
(11, 'rue crequis', 69006, 'lyon', NULL),
(12, 'cours lafayette', 69003, 'lyon', NULL),
(13, 'boulevard des brotteaux', 69006, 'lyon', NULL),
(14, 'cours suchet', 69002, 'lyon', NULL),
(15, 'montee de choulans', 69005, 'lyon', NULL),
(16, 'avenur camille rousset', 69102, 'bron', NULL),
(17, 'rue de la victoire', 69230, 'saint genix laval', NULL),
(18, 'rue des capucins', 69001, 'lyon', NULL),
(19, 'rue test', 30000, 'nimes', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

CREATE TABLE `agence` (
  `idAgence` int(11) NOT NULL,
  `dateCreation` date DEFAULT NULL,
  `idAdresse` int(11) DEFAULT NULL,
  `idGerant` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `agence`
--

INSERT INTO `agence` (`idAgence`, `dateCreation`, `idAdresse`, `idGerant`) VALUES
(1, '2017-04-11', 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `cartebancaire`
--

CREATE TABLE `cartebancaire` (
  `idCarte` int(11) NOT NULL,
  `typeCarte` varchar(25) DEFAULT NULL,
  `idCompte` int(11) DEFAULT NULL,
  `idVisaPremier` int(11) DEFAULT NULL,
  `idVisaElectron` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `idClient` int(11) NOT NULL,
  `typeClient` varchar(25) DEFAULT NULL,
  `idClientEntreprise` int(11) DEFAULT NULL,
  `idClientParticulier` int(11) DEFAULT NULL,
  `idConseiller` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`idClient`, `typeClient`, `idClientEntreprise`, `idClientParticulier`, `idConseiller`, `id`) VALUES
(1, 'entreprise', 1, NULL, 1, 4),
(2, 'particulier', NULL, 1, 1, 5),
(3, 'particulier', NULL, 2, 2, 6),
(4, 'entreprise', 2, NULL, 1, 7),
(5, 'particulier', NULL, 3, 1, 8),
(6, 'entreprise', 3, NULL, 1, 9),
(7, 'particulier', NULL, 4, 2, 10),
(8, 'particulier', NULL, 5, 1, 11),
(9, 'particulier', NULL, 6, 1, 12),
(10, 'particulier', NULL, 7, 1, 13),
(11, 'entreprise', 4, NULL, 2, 14),
(12, 'entreprise', 5, NULL, 1, 15),
(13, 'entreprise', 6, NULL, 1, 16),
(14, 'particulier', NULL, 8, 2, 17),
(15, 'particulier', NULL, 9, 2, 18);

-- --------------------------------------------------------

--
-- Structure de la table `cliententreprise`
--

CREATE TABLE `cliententreprise` (
  `idClientEntreprise` int(11) NOT NULL,
  `idClient` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `cliententreprise`
--

INSERT INTO `cliententreprise` (`idClientEntreprise`, `idClient`) VALUES
(1, 1),
(2, 4),
(3, 6),
(4, 11),
(5, 12),
(6, 13);

-- --------------------------------------------------------

--
-- Structure de la table `clientparticulier`
--

CREATE TABLE `clientparticulier` (
  `idClientParticulier` int(11) NOT NULL,
  `idClient` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `clientparticulier`
--

INSERT INTO `clientparticulier` (`idClientParticulier`, `idClient`) VALUES
(1, 2),
(2, 3),
(3, 5),
(4, 7),
(5, 8),
(6, 9),
(7, 10),
(8, 14),
(9, 15);

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `idCompte` int(11) NOT NULL,
  `numeroCompte` int(11) DEFAULT NULL,
  `solde` double DEFAULT NULL,
  `dateOuverture` date DEFAULT NULL,
  `typeCompte` varchar(25) DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL,
  `idCarte` int(11) DEFAULT NULL,
  `idCompteCourant` int(11) DEFAULT NULL,
  `idCompteEpargne` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`idCompte`, `numeroCompte`, `solde`, `dateOuverture`, `typeCompte`, `idClient`, `idCarte`, `idCompteCourant`, `idCompteEpargne`) VALUES
(1, 10203, 130, '2017-03-09', 'courant', 2, NULL, 1, NULL),
(2, 20304, 5000000, '2017-04-04', 'courant', 1, NULL, 2, NULL),
(3, 30405, 2600, '2017-02-15', 'epargne', 2, NULL, NULL, 1),
(4, 40506, 8000, '2017-03-28', 'courant', 5, NULL, 3, NULL),
(5, 50607, 0, '2016-10-12', 'courant', 9, NULL, 4, NULL),
(6, 70809, 9000, '2017-04-02', 'courant', 11, NULL, 5, NULL),
(7, 80706, 10000, '2016-09-27', 'epargne', 14, NULL, NULL, 2);

-- --------------------------------------------------------

--
-- Structure de la table `comptecourant`
--

CREATE TABLE `comptecourant` (
  `idCompteCourant` int(11) NOT NULL,
  `decouvert` double DEFAULT NULL,
  `idCompte` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `comptecourant`
--

INSERT INTO `comptecourant` (`idCompteCourant`, `decouvert`, `idCompte`) VALUES
(1, 1000, 1),
(2, 1000, 2),
(3, 1000, 4),
(4, 1000, 5),
(5, 1000, 6);

-- --------------------------------------------------------

--
-- Structure de la table `compteepargne`
--

CREATE TABLE `compteepargne` (
  `idCompteEpargne` int(11) NOT NULL,
  `remuneration` double DEFAULT NULL,
  `idCompte` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `compteepargne`
--

INSERT INTO `compteepargne` (`idCompteEpargne`, `remuneration`, `idCompte`) VALUES
(1, 0.03, 3),
(2, 0.03, 7);

-- --------------------------------------------------------

--
-- Structure de la table `connectionconseiller`
--

CREATE TABLE `connectionconseiller` (
  `idConnection` int(11) NOT NULL,
  `login` varchar(25) DEFAULT NULL,
  `pwd` varchar(25) DEFAULT NULL,
  `idConseiller` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `connectionconseiller`
--

INSERT INTO `connectionconseiller` (`idConnection`, `login`, `pwd`, `idConseiller`) VALUES
(2, 'bourne', 'bourne', 1),
(3, 'MEspuche', 'gtm', 2);

-- --------------------------------------------------------

--
-- Structure de la table `connectiongerant`
--

CREATE TABLE `connectiongerant` (
  `idConnection` int(11) NOT NULL,
  `login` varchar(25) DEFAULT NULL,
  `pwd` varchar(25) DEFAULT NULL,
  `idGerant` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `connectiongerant`
--

INSERT INTO `connectiongerant` (`idConnection`, `login`, `pwd`, `idGerant`) VALUES
(1, 'gerant', 'gerant', 1);

-- --------------------------------------------------------

--
-- Structure de la table `conseiller`
--

CREATE TABLE `conseiller` (
  `idConseiller` int(11) NOT NULL,
  `idGerant` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
  `idConnection` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `conseiller`
--

INSERT INTO `conseiller` (`idConseiller`, `idGerant`, `id`, `idConnection`) VALUES
(1, 1, 1, 2),
(2, 1, 2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `credit`
--

CREATE TABLE `credit` (
  `idCredit` int(11) NOT NULL,
  `typeCredit` varchar(25) DEFAULT NULL,
  `idCreditImmo` int(11) DEFAULT NULL,
  `idCreditConso` int(11) DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `creditconso`
--

CREATE TABLE `creditconso` (
  `idCreditConso` int(11) NOT NULL,
  `idCredit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `creditimmo`
--

CREATE TABLE `creditimmo` (
  `idCreditImmo` int(11) NOT NULL,
  `idCredit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `gerant`
--

CREATE TABLE `gerant` (
  `idGerant` int(11) NOT NULL,
  `id` int(11) DEFAULT NULL,
  `idAgence` int(11) DEFAULT NULL,
  `idConnection` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `gerant`
--

INSERT INTO `gerant` (`idGerant`, `id`, `idAgence`, `idConnection`) VALUES
(1, 3, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `localisation`
--

CREATE TABLE `localisation` (
  `idLocalisation` int(11) NOT NULL,
  `ville` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `id` int(11) NOT NULL,
  `nom` varchar(25) DEFAULT NULL,
  `prenom` varchar(25) DEFAULT NULL,
  `telephone` varchar(25) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `idConseiller` int(11) DEFAULT NULL,
  `idGerant` int(11) DEFAULT NULL,
  `idClient` int(11) DEFAULT NULL,
  `idAdresse` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`, `telephone`, `email`, `idConseiller`, `idGerant`, `idClient`, `idAdresse`) VALUES
(1, 'bourne', 'bourne', '0606060606', 'bourne.bourne@gmail.com', 1, NULL, NULL, 1),
(2, 'espuche', 'marine', '0707070707', 'espuche.marine@gmail.com', 2, NULL, NULL, 2),
(3, 'gerard', 'gerard', '0808080808', 'gerard.gerard@gmail.com', NULL, 1, NULL, 4),
(4, 'salomon', 'vincent', '0909090909', 'salomon.vincent@gmail.com', NULL, NULL, 1, 5),
(5, 'jeanjacquot', 'pierre', '0101010101', 'jeanjacquot.pierre@gmail.com', NULL, NULL, 2, 6),
(6, 'emin', 'perrine', '0202020202', 'emine.perrine@gmail.com', NULL, NULL, 3, 7),
(7, 'izard', 'jerome', '0303030303', 'izard.jerome@gmail.com', NULL, NULL, 4, 8),
(8, 'ayraud', 'florent', '0505050505', 'ayraud.vincent@gmail.com', NULL, NULL, 5, 9),
(9, 'othmane', 'guillaume', '0404040404', 'othmane.guillaume@gmail.com', NULL, NULL, 6, 10),
(10, 'le gal', 'vincent', '0707060605', 'legal.vincent@gmail.com', NULL, NULL, 7, 11),
(11, 'martin', 'emmanuel', '0405060708', 'martin.emmanuel@gmail.com', NULL, NULL, 8, 12),
(12, 'marin', 'florent', '0102030405', 'marin.florent@gmail.com', NULL, NULL, 9, 13),
(13, 'villar', 'theo', '0203040506', 'villar.theo@gmail.com', NULL, NULL, 10, 14),
(14, 'berny', 'stephane', '0305080907', 'berny.stephane@gmail.com', NULL, NULL, 11, 15),
(15, 'christian', 'eddy', '0606050504', 'christian.eddy@gmail.com', NULL, NULL, 12, 16),
(16, 'test', 'test', '0000000000', 'test.test@gmail.com', NULL, NULL, 13, 17),
(17, 'dupond', 'jean', '0303030201', 'dupond.jean@gmail.com', NULL, NULL, 14, 18),
(18, 'henry', 'charles', '0505020101', 'henry.charles@gmail.com', NULL, NULL, 15, 19);

-- --------------------------------------------------------

--
-- Structure de la table `placement`
--

CREATE TABLE `placement` (
  `idPlacement` int(11) NOT NULL,
  `idClient` int(11) DEFAULT NULL,
  `idLocalisation` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `visaelectron`
--

CREATE TABLE `visaelectron` (
  `idVisaElectron` int(11) NOT NULL,
  `idCarte` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `visapremier`
--

CREATE TABLE `visapremier` (
  `idVisaPremier` int(11) NOT NULL,
  `idCarte` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`idAdresse`),
  ADD KEY `FK_adresse_idAgence` (`idAgence`);

--
-- Index pour la table `agence`
--
ALTER TABLE `agence`
  ADD PRIMARY KEY (`idAgence`),
  ADD KEY `FK_agence_idAdresse` (`idAdresse`),
  ADD KEY `FK_agence_idGerant` (`idGerant`);

--
-- Index pour la table `cartebancaire`
--
ALTER TABLE `cartebancaire`
  ADD PRIMARY KEY (`idCarte`),
  ADD KEY `FK_carteBancaire_idCompte` (`idCompte`),
  ADD KEY `FK_carteBancaire_idVisaPremier` (`idVisaPremier`),
  ADD KEY `FK_carteBancaire_idVisaElectron` (`idVisaElectron`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`),
  ADD KEY `FK_client_idClientEntreprise` (`idClientEntreprise`),
  ADD KEY `FK_client_idClientParticulier` (`idClientParticulier`),
  ADD KEY `FK_client_idConseiller` (`idConseiller`),
  ADD KEY `FK_client_id` (`id`);

--
-- Index pour la table `cliententreprise`
--
ALTER TABLE `cliententreprise`
  ADD PRIMARY KEY (`idClientEntreprise`),
  ADD KEY `FK_clientEntreprise_idClient` (`idClient`);

--
-- Index pour la table `clientparticulier`
--
ALTER TABLE `clientparticulier`
  ADD PRIMARY KEY (`idClientParticulier`),
  ADD KEY `FK_clientParticulier_idClient` (`idClient`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`idCompte`),
  ADD KEY `FK_compte_idClient` (`idClient`),
  ADD KEY `FK_compte_idCarte` (`idCarte`),
  ADD KEY `FK_compte_idCompteCourant` (`idCompteCourant`),
  ADD KEY `FK_compte_idCompteEpargne` (`idCompteEpargne`);

--
-- Index pour la table `comptecourant`
--
ALTER TABLE `comptecourant`
  ADD PRIMARY KEY (`idCompteCourant`),
  ADD KEY `FK_compteCourant_idCompte` (`idCompte`);

--
-- Index pour la table `compteepargne`
--
ALTER TABLE `compteepargne`
  ADD PRIMARY KEY (`idCompteEpargne`),
  ADD KEY `FK_compteEpargne_idCompte` (`idCompte`);

--
-- Index pour la table `connectionconseiller`
--
ALTER TABLE `connectionconseiller`
  ADD PRIMARY KEY (`idConnection`),
  ADD KEY `FK_connectionConseiller_idConseiller` (`idConseiller`);

--
-- Index pour la table `connectiongerant`
--
ALTER TABLE `connectiongerant`
  ADD PRIMARY KEY (`idConnection`),
  ADD KEY `FK_connectionGerant_idGerant` (`idGerant`);

--
-- Index pour la table `conseiller`
--
ALTER TABLE `conseiller`
  ADD PRIMARY KEY (`idConseiller`),
  ADD KEY `FK_conseiller_idGerant` (`idGerant`),
  ADD KEY `FK_conseiller_id` (`id`),
  ADD KEY `FK_conseiller_idConnection` (`idConnection`);

--
-- Index pour la table `credit`
--
ALTER TABLE `credit`
  ADD PRIMARY KEY (`idCredit`),
  ADD KEY `FK_credit_idCreditImmo` (`idCreditImmo`),
  ADD KEY `FK_credit_idCreditConso` (`idCreditConso`),
  ADD KEY `FK_credit_idClient` (`idClient`);

--
-- Index pour la table `creditconso`
--
ALTER TABLE `creditconso`
  ADD PRIMARY KEY (`idCreditConso`),
  ADD KEY `FK_creditConso_idCredit` (`idCredit`);

--
-- Index pour la table `creditimmo`
--
ALTER TABLE `creditimmo`
  ADD PRIMARY KEY (`idCreditImmo`),
  ADD KEY `FK_creditImmo_idCredit` (`idCredit`);

--
-- Index pour la table `gerant`
--
ALTER TABLE `gerant`
  ADD PRIMARY KEY (`idGerant`),
  ADD KEY `FK_gerant_id` (`id`),
  ADD KEY `FK_gerant_idAgence` (`idAgence`),
  ADD KEY `FK_gerant_idConnection` (`idConnection`);

--
-- Index pour la table `localisation`
--
ALTER TABLE `localisation`
  ADD PRIMARY KEY (`idLocalisation`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_personne_idConseiller` (`idConseiller`),
  ADD KEY `FK_personne_idGerant` (`idGerant`),
  ADD KEY `FK_personne_idClient` (`idClient`),
  ADD KEY `FK_personne_idAdresse` (`idAdresse`);

--
-- Index pour la table `placement`
--
ALTER TABLE `placement`
  ADD PRIMARY KEY (`idPlacement`),
  ADD KEY `FK_placement_idClient` (`idClient`),
  ADD KEY `FK_placement_idLocalisation` (`idLocalisation`);

--
-- Index pour la table `visaelectron`
--
ALTER TABLE `visaelectron`
  ADD PRIMARY KEY (`idVisaElectron`),
  ADD KEY `FK_visaElectron_idCarte` (`idCarte`);

--
-- Index pour la table `visapremier`
--
ALTER TABLE `visapremier`
  ADD PRIMARY KEY (`idVisaPremier`),
  ADD KEY `FK_visaPremier_idCarte` (`idCarte`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `adresse`
--
ALTER TABLE `adresse`
  MODIFY `idAdresse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT pour la table `agence`
--
ALTER TABLE `agence`
  MODIFY `idAgence` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `cartebancaire`
--
ALTER TABLE `cartebancaire`
  MODIFY `idCarte` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT pour la table `cliententreprise`
--
ALTER TABLE `cliententreprise`
  MODIFY `idClientEntreprise` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `clientparticulier`
--
ALTER TABLE `clientparticulier`
  MODIFY `idClientParticulier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `idCompte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT pour la table `comptecourant`
--
ALTER TABLE `comptecourant`
  MODIFY `idCompteCourant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `compteepargne`
--
ALTER TABLE `compteepargne`
  MODIFY `idCompteEpargne` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `connectionconseiller`
--
ALTER TABLE `connectionconseiller`
  MODIFY `idConnection` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `connectiongerant`
--
ALTER TABLE `connectiongerant`
  MODIFY `idConnection` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `conseiller`
--
ALTER TABLE `conseiller`
  MODIFY `idConseiller` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `credit`
--
ALTER TABLE `credit`
  MODIFY `idCredit` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `creditconso`
--
ALTER TABLE `creditconso`
  MODIFY `idCreditConso` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `creditimmo`
--
ALTER TABLE `creditimmo`
  MODIFY `idCreditImmo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `gerant`
--
ALTER TABLE `gerant`
  MODIFY `idGerant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `localisation`
--
ALTER TABLE `localisation`
  MODIFY `idLocalisation` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT pour la table `placement`
--
ALTER TABLE `placement`
  MODIFY `idPlacement` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `visaelectron`
--
ALTER TABLE `visaelectron`
  MODIFY `idVisaElectron` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `visapremier`
--
ALTER TABLE `visapremier`
  MODIFY `idVisaPremier` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD CONSTRAINT `FK_adresse_idAgence` FOREIGN KEY (`idAgence`) REFERENCES `agence` (`idAgence`);

--
-- Contraintes pour la table `agence`
--
ALTER TABLE `agence`
  ADD CONSTRAINT `FK_agence_idAdresse` FOREIGN KEY (`idAdresse`) REFERENCES `adresse` (`idAdresse`),
  ADD CONSTRAINT `FK_agence_idGerant` FOREIGN KEY (`idGerant`) REFERENCES `gerant` (`idGerant`);

--
-- Contraintes pour la table `cartebancaire`
--
ALTER TABLE `cartebancaire`
  ADD CONSTRAINT `FK_carteBancaire_idCompte` FOREIGN KEY (`idCompte`) REFERENCES `compte` (`idCompte`),
  ADD CONSTRAINT `FK_carteBancaire_idVisaElectron` FOREIGN KEY (`idVisaElectron`) REFERENCES `visaelectron` (`idVisaElectron`),
  ADD CONSTRAINT `FK_carteBancaire_idVisaPremier` FOREIGN KEY (`idVisaPremier`) REFERENCES `visapremier` (`idVisaPremier`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_client_id` FOREIGN KEY (`id`) REFERENCES `personne` (`id`),
  ADD CONSTRAINT `FK_client_idClientEntreprise` FOREIGN KEY (`idClientEntreprise`) REFERENCES `cliententreprise` (`idClientEntreprise`),
  ADD CONSTRAINT `FK_client_idClientParticulier` FOREIGN KEY (`idClientParticulier`) REFERENCES `clientparticulier` (`idClientParticulier`),
  ADD CONSTRAINT `FK_client_idConseiller` FOREIGN KEY (`idConseiller`) REFERENCES `conseiller` (`idConseiller`);

--
-- Contraintes pour la table `cliententreprise`
--
ALTER TABLE `cliententreprise`
  ADD CONSTRAINT `FK_clientEntreprise_idClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`);

--
-- Contraintes pour la table `clientparticulier`
--
ALTER TABLE `clientparticulier`
  ADD CONSTRAINT `FK_clientParticulier_idClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`);

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `FK_compte_idCarte` FOREIGN KEY (`idCarte`) REFERENCES `cartebancaire` (`idCarte`),
  ADD CONSTRAINT `FK_compte_idClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`),
  ADD CONSTRAINT `FK_compte_idCompteCourant` FOREIGN KEY (`idCompteCourant`) REFERENCES `comptecourant` (`idCompteCourant`),
  ADD CONSTRAINT `FK_compte_idCompteEpargne` FOREIGN KEY (`idCompteEpargne`) REFERENCES `compteepargne` (`idCompteEpargne`);

--
-- Contraintes pour la table `comptecourant`
--
ALTER TABLE `comptecourant`
  ADD CONSTRAINT `FK_compteCourant_idCompte` FOREIGN KEY (`idCompte`) REFERENCES `compte` (`idCompte`);

--
-- Contraintes pour la table `compteepargne`
--
ALTER TABLE `compteepargne`
  ADD CONSTRAINT `FK_compteEpargne_idCompte` FOREIGN KEY (`idCompte`) REFERENCES `compte` (`idCompte`);

--
-- Contraintes pour la table `connectionconseiller`
--
ALTER TABLE `connectionconseiller`
  ADD CONSTRAINT `FK_connectionConseiller_idConseiller` FOREIGN KEY (`idConseiller`) REFERENCES `conseiller` (`idConseiller`);

--
-- Contraintes pour la table `connectiongerant`
--
ALTER TABLE `connectiongerant`
  ADD CONSTRAINT `FK_connectionGerant_idGerant` FOREIGN KEY (`idGerant`) REFERENCES `gerant` (`idGerant`);

--
-- Contraintes pour la table `conseiller`
--
ALTER TABLE `conseiller`
  ADD CONSTRAINT `FK_conseiller_id` FOREIGN KEY (`id`) REFERENCES `personne` (`id`),
  ADD CONSTRAINT `FK_conseiller_idConnection` FOREIGN KEY (`idConnection`) REFERENCES `connectionconseiller` (`idConnection`),
  ADD CONSTRAINT `FK_conseiller_idGerant` FOREIGN KEY (`idGerant`) REFERENCES `gerant` (`idGerant`);

--
-- Contraintes pour la table `credit`
--
ALTER TABLE `credit`
  ADD CONSTRAINT `FK_credit_idClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`),
  ADD CONSTRAINT `FK_credit_idCreditConso` FOREIGN KEY (`idCreditConso`) REFERENCES `creditconso` (`idCreditConso`),
  ADD CONSTRAINT `FK_credit_idCreditImmo` FOREIGN KEY (`idCreditImmo`) REFERENCES `creditimmo` (`idCreditImmo`);

--
-- Contraintes pour la table `creditconso`
--
ALTER TABLE `creditconso`
  ADD CONSTRAINT `FK_creditConso_idCredit` FOREIGN KEY (`idCredit`) REFERENCES `credit` (`idCredit`);

--
-- Contraintes pour la table `creditimmo`
--
ALTER TABLE `creditimmo`
  ADD CONSTRAINT `FK_creditImmo_idCredit` FOREIGN KEY (`idCredit`) REFERENCES `credit` (`idCredit`);

--
-- Contraintes pour la table `gerant`
--
ALTER TABLE `gerant`
  ADD CONSTRAINT `FK_gerant_id` FOREIGN KEY (`id`) REFERENCES `personne` (`id`),
  ADD CONSTRAINT `FK_gerant_idAgence` FOREIGN KEY (`idAgence`) REFERENCES `agence` (`idAgence`),
  ADD CONSTRAINT `FK_gerant_idConnection` FOREIGN KEY (`idConnection`) REFERENCES `connectiongerant` (`idConnection`);

--
-- Contraintes pour la table `personne`
--
ALTER TABLE `personne`
  ADD CONSTRAINT `FK_personne_idAdresse` FOREIGN KEY (`idAdresse`) REFERENCES `adresse` (`idAdresse`),
  ADD CONSTRAINT `FK_personne_idClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`),
  ADD CONSTRAINT `FK_personne_idConseiller` FOREIGN KEY (`idConseiller`) REFERENCES `conseiller` (`idConseiller`),
  ADD CONSTRAINT `FK_personne_idGerant` FOREIGN KEY (`idGerant`) REFERENCES `gerant` (`idGerant`);

--
-- Contraintes pour la table `placement`
--
ALTER TABLE `placement`
  ADD CONSTRAINT `FK_placement_idClient` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`),
  ADD CONSTRAINT `FK_placement_idLocalisation` FOREIGN KEY (`idLocalisation`) REFERENCES `localisation` (`idLocalisation`);

--
-- Contraintes pour la table `visaelectron`
--
ALTER TABLE `visaelectron`
  ADD CONSTRAINT `FK_visaElectron_idCarte` FOREIGN KEY (`idCarte`) REFERENCES `cartebancaire` (`idCarte`);

--
-- Contraintes pour la table `visapremier`
--
ALTER TABLE `visapremier`
  ADD CONSTRAINT `FK_visaPremier_idCarte` FOREIGN KEY (`idCarte`) REFERENCES `cartebancaire` (`idCarte`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
