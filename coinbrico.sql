-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 03, 2018 at 08:34 
-- Server version: 5.6.25
-- PHP Version: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coinbrico`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) NOT NULL,
  `cl_nom` varchar(45) DEFAULT NULL,
  `cl_prenom` varchar(45) DEFAULT NULL,
  `cl_date_creation` date DEFAULT NULL,
  `cl_tel` varchar(45) DEFAULT NULL,
  `cl_mail` varchar(150) DEFAULT NULL,
  `cl_password` varchar(45) DEFAULT NULL,
  `cl_etat` varchar(45) DEFAULT NULL,
  `cl_voie` varchar(100) DEFAULT NULL,
  `cl_code_postal` varchar(45) DEFAULT NULL,
  `cl_ville` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id_client`, `cl_nom`, `cl_prenom`, `cl_date_creation`, `cl_tel`, `cl_mail`, `cl_password`, `cl_etat`, `cl_voie`, `cl_code_postal`, `cl_ville`) VALUES
(3, 'Karine', 'Amali', '2018-03-17', '0744532968', 'karine.hello@gmail.com', '12456', NULL, '6, rue singer', '75016', 'Paris'),
(4, 'Naciri', 'Ella', '2018-03-26', '0754332277', 'ella@gmail.com', '1234', NULL, '3, rue bluet', '75013', 'Paris');

-- --------------------------------------------------------

--
-- Table structure for table `document`
--

CREATE TABLE IF NOT EXISTS `document` (
  `id_doc` int(11) NOT NULL,
  `id_ objet` varchar(15) NOT NULL,
  `d_date_creation` date DEFAULT NULL,
  `d_actif` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `objet`
--

CREATE TABLE IF NOT EXISTS `objet` (
  `id_objet` varchar(15) NOT NULL,
  `id_pl` int(11) NOT NULL,
  `id_produit` int(11) NOT NULL,
  `o_libelle` varchar(45) DEFAULT NULL,
  `o_prix` float DEFAULT NULL,
  `o_amende_dj` float DEFAULT NULL,
  `o_caution` float DEFAULT NULL,
  `o_date_creation` date DEFAULT NULL,
  `o_etat` varchar(45) DEFAULT NULL,
  `o_date_der_maj` date DEFAULT NULL,
  `o_u_der_maj` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `objet`
--

INSERT INTO `objet` (`id_objet`, `id_pl`, `id_produit`, `o_libelle`, `o_prix`, `o_amende_dj`, `o_caution`, `o_date_creation`, `o_etat`, `o_date_der_maj`, `o_u_der_maj`) VALUES
('JAR_BROY_001', 1, 5, 'BROYEUR BRANCHES ESSENCE 55mm', 117, 100, 400, '2018-04-03', 'VALIDE', '2018-04-03', 1),
('JAR_BROY_002', 0, 5, 'BROYEUR BRANCHES ESSENCE 65mm', 117, 100, 400, '2018-04-03', 'VALIDE', '2018-04-03', 1),
('Marteau_001', 0, 6, 'Marteau Strauss', 117, 100, 400, '2018-04-03', 'VALIDE', '2018-04-03', 1),
('Marteau_002', 1, 6, 'Mini marteau 10cm', 117, 100, 400, '2018-04-03', 'VALIDE', '2018-04-03', 1),
('Rabot_001', 0, 7, 'Rabot 40 couteaux réversible', 117, 100, 400, '2018-04-03', 'VALIDE', '2018-04-03', 1),
('Rabot_002', 1, 7, 'Raboteuse à parquet', 117, 100, 400, '2018-04-03', 'VALIDE', '2018-04-03', 1);

-- --------------------------------------------------------

--
-- Table structure for table `param_globaux`
--

CREATE TABLE IF NOT EXISTS `param_globaux` (
  `dr_max_location` int(11) NOT NULL,
  `dr_min_location` int(11) NOT NULL,
  `nb_max_objet_loues` int(11) NOT NULL,
  `nb_max_objet_reserves` int(11) NOT NULL,
  `dr_max_retrait` int(11) NOT NULL,
  `date_der_maj` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `point_location`
--

CREATE TABLE IF NOT EXISTS `point_location` (
  `id_pl` int(11) NOT NULL,
  `pl_nom` varchar(45) DEFAULT NULL,
  `pl_tel` varchar(45) DEFAULT NULL,
  `pl_voie` varchar(100) DEFAULT NULL,
  `pl_code_postal` varchar(45) DEFAULT NULL,
  `pl_ville` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `point_location`
--

INSERT INTO `point_location` (`id_pl`, `pl_nom`, `pl_tel`, `pl_voie`, `pl_code_postal`, `pl_ville`) VALUES
(0, 'MAGASIN_CHATOU', '0234252787', ' 6 RUE LAMY', '78400', 'CHATOU'),
(1, 'MAGASIN_PARIS', '0234252755', ' Porte Dauphine', '75016', 'Paris');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `id_produit` int(11) NOT NULL,
  `p_libelle` varchar(45) DEFAULT NULL,
  `p_type` varchar(45) DEFAULT NULL,
  `p_desc_technique` varchar(500) DEFAULT NULL,
  `p_defaut` varchar(500) DEFAULT NULL,
  `p_date_der_maj` date DEFAULT NULL,
  `p_u_der_maj` int(11) DEFAULT NULL,
  `p_prix` float DEFAULT NULL,
  `p_amende_dj` float DEFAULT NULL,
  `p_caution` float DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id_produit`, `p_libelle`, `p_type`, `p_desc_technique`, `p_defaut`, `p_date_der_maj`, `p_u_der_maj`, `p_prix`, `p_amende_dj`, `p_caution`) VALUES
(1, 'x', 'Jardinage', 'Matériel de jardinage', 'x', '2018-03-25', NULL, 12, 12, 12),
(2, 'r', 'Constuction', 'Matériel de constuction', 'x', '2018-03-28', NULL, 12, 13, 12),
(3, 'a', 'Menuiserie', 'Matériel de Menuiserie', 'x', '2018-04-01', NULL, 2, 2, 2),
(4, 'Tondeuses électriques', 'JARDIN', ' ', 'AUCUN', '2018-04-02', 1, 120, 100, 400),
(5, 'Broyeur branches', 'JARDIN', 'BROYEUR BRANCHES ESSENCE', 'AUCUN', '2018-04-02', 1, 120, 100, 400),
(6, 'Marteaux', 'Construction', ' ', 'AUCUN', '2018-04-02', 1, 10, 12, 40),
(7, 'Rabot à planer', 'Menuiserie', '', 'AUCUN', '2018-04-02', 1, 12, 10, 30);

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `id_resa` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_objet` varchar(15) NOT NULL,
  `r_date_resa` date DEFAULT NULL,
  `r_duree_prevue` int(11) DEFAULT NULL,
  `r_date_retrait` date DEFAULT NULL,
  `r_date_retour` date DEFAULT NULL,
  `r_amende_degrad` float DEFAULT NULL,
  `r_etat` varchar(45) DEFAULT NULL,
  `r_date_lim_retour` date DEFAULT NULL,
  `r_date_lim_retrait` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_user` int(11) NOT NULL,
  `id_pl` int(11) NOT NULL,
  `u_nom` varchar(45) DEFAULT NULL,
  `u_prenom` varchar(45) DEFAULT NULL,
  `u_tel` varchar(45) DEFAULT NULL,
  `u_date_creation` date DEFAULT NULL,
  `u_mail` varchar(150) DEFAULT NULL,
  `u_password` varchar(45) DEFAULT NULL,
  `u_profile` varchar(45) DEFAULT NULL,
  `u_etat` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`),
  ADD UNIQUE KEY `cl_mail` (`cl_mail`);

--
-- Indexes for table `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`id_doc`),
  ADD KEY `fk_document_objet1_idx` (`id_ objet`);

--
-- Indexes for table `objet`
--
ALTER TABLE `objet`
  ADD PRIMARY KEY (`id_objet`),
  ADD KEY `fk_objet_produit1_idx` (`id_produit`),
  ADD KEY `fk_objet_point_location1_idx` (`id_pl`);

--
-- Indexes for table `point_location`
--
ALTER TABLE `point_location`
  ADD PRIMARY KEY (`id_pl`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id_produit`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_resa`),
  ADD KEY `fk_reservation_client_idx` (`id_client`),
  ADD KEY `fk_reservation_objet1_idx` (`id_objet`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `fk_utilisateur_point_location1_idx` (`id_pl`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id_client` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `id_produit` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `fk_document_objet1` FOREIGN KEY (`id_ objet`) REFERENCES `objet` (`id_objet`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `objet`
--
ALTER TABLE `objet`
  ADD CONSTRAINT `fk_objet_point_location1` FOREIGN KEY (`id_pl`) REFERENCES `point_location` (`id_pl`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_objet_produit1` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id_produit`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `fk_reservation_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_reservation_objet1` FOREIGN KEY (`id_objet`) REFERENCES `objet` (`id_objet`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `fk_utilisateur_point_location1` FOREIGN KEY (`id_pl`) REFERENCES `point_location` (`id_pl`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
