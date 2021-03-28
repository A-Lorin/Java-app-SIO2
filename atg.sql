-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 21 jan. 2021 à 20:39
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `atg`
--

-- --------------------------------------------------------

--
-- Structure de la table `bureau`
--

DROP TABLE IF EXISTS `bureau`;
CREATE TABLE IF NOT EXISTS `bureau` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fonction` varchar(50) NOT NULL,
  `titre` varchar(30) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `adresse` varchar(80) NOT NULL,
  `cp` varchar(6) NOT NULL,
  `ville` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telPortable` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `bureau`
--

INSERT INTO `bureau` (`id`, `fonction`, `titre`, `nom`, `prenom`, `adresse`, `cp`, `ville`, `email`, `telPortable`) VALUES
(1, 'Présidente', 'Madame', 'SCHUMACHER', 'Elisa', '', '22300', 'Lannion', '', ''),
(2, 'Vice Présidente', 'Madame', 'LAIR', 'Gwenaelle', 'rue de Kervegan - Servel', '22300', 'Lannion', 'gwenaelle.lair@wanadoo.fr', '0602010101'),
(3, 'Secrétaire', 'Monsieur', 'CORBE', 'Henri', '', '22300', 'Pleumeur Bodou', 'philippe.logiou@gmail.com', '0602030302');

-- --------------------------------------------------------

--
-- Structure de la table `cotisation`
--

DROP TABLE IF EXISTS `cotisation`;
CREATE TABLE IF NOT EXISTS `cotisation` (
  `montant` double NOT NULL,
  UNIQUE KEY `montant` (`montant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cotisation`
--

INSERT INTO `cotisation` (`montant`) VALUES
(15);

-- --------------------------------------------------------

--
-- Structure de la table `cotiser`
--

DROP TABLE IF EXISTS `cotiser`;
CREATE TABLE IF NOT EXISTS `cotiser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idMembre` int(11) NOT NULL,
  `dateVersement` date NOT NULL,
  `somme_versee` double NOT NULL,
  `recuEmail` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `idMembre` (`idMembre`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cotiser`
--

INSERT INTO `cotiser` (`id`, `idMembre`, `dateVersement`, `somme_versee`, `recuEmail`) VALUES
(1, 1, '2019-12-09', 15, 1),
(2, 2, '2020-11-25', 26, 0),
(4, 14, '2020-11-26', 45, 0),
(5, 1, '2020-12-11', 15, 0),
(6, 15, '2020-12-16', 15, 0);

-- --------------------------------------------------------

--
-- Structure de la table `membres`
--

DROP TABLE IF EXISTS `membres`;
CREATE TABLE IF NOT EXISTS `membres` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(40) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `cp` varchar(6) NOT NULL,
  `ville` varchar(50) NOT NULL,
  `pays` varchar(4) NOT NULL,
  `telFixe` varchar(10) NOT NULL,
  `telPortable` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `membres`
--

INSERT INTO `membres` (`id`, `titre`, `nom`, `prenom`, `adresse`, `cp`, `ville`, `pays`, `telFixe`, `telPortable`, `email`) VALUES
(1, 'Monsieur', 'GERMAIN', 'Lionel', '3 rue des écoles', '22300', 'LANNION', 'FR', '0296545898', '', 'germainlio3@gmail.com'),
(2, 'Madame', 'LAMBERTIN', 'Anne', '27 allée des acacias', '22200', 'GUINGAMP', 'FR', '0296925544', '0602015589', 'lambertin.a@hotmail.fr'),
(14, 'Monsieur', 'TEST', 'TESt', '74 rue des test', '12420', 'QUIMPER', 'RU', '0202020202', '06', 'ekimaxett-3509@yopmail.com'),
(15, 'Madame', 'GERMAIN', 'john', '3 rue des écoles', '22300', 'LANNION', 'FR', '0296545898', '', 'germainlio3@gmail.com');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cotiser`
--
ALTER TABLE `cotiser`
  ADD CONSTRAINT `cotiser_ibfk_1` FOREIGN KEY (`idMembre`) REFERENCES `membres` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
