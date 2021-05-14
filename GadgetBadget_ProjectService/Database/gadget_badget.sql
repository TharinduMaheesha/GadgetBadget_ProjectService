-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: May 14, 2021 at 05:12 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gadget_badget`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `adminId` int(11) NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `clientId` int(11) NOT NULL,
  PRIMARY KEY (`clientId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`clientId`) VALUES
(0),
(1003),
(1004);

-- --------------------------------------------------------

--
-- Table structure for table `finished`
--

DROP TABLE IF EXISTS `finished`;
CREATE TABLE IF NOT EXISTS `finished` (
  `pid` int(11) NOT NULL,
  `researcherId` int(11) NOT NULL,
  `cartId` int(11) DEFAULT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`pid`,`researcherId`),
  KEY `FK_cartID_clientId` (`cartId`),
  KEY `pid` (`pid`),
  KEY `fk_jdjd` (`researcherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `finished`
--

INSERT INTO `finished` (`pid`, `researcherId`, `cartId`, `price`) VALUES
(10013, 1005, NULL, 0),
(10040, 1002, NULL, 20),
(10041, 1002, NULL, 20),
(10042, 1002, NULL, 100),
(10045, 1002, NULL, 70),
(10047, 1002, NULL, 15),
(10048, 1002, NULL, 7);

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE IF NOT EXISTS `project` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `researcherId` int(11) NOT NULL,
  `topic` varchar(50) NOT NULL,
  `status` varchar(10) NOT NULL,
  `clientid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`,`researcherId`),
  KEY `FK_researcherID` (`researcherId`),
  KEY `fk_some` (`clientid`)
) ENGINE=InnoDB AUTO_INCREMENT=10051 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`pid`, `researcherId`, `topic`, `status`, `clientid`) VALUES
(10001, 1001, 'Food delivery App', 'paid', NULL),
(10008, 1002, 'Artificial Intelligence', 'Not Paid', NULL),
(10013, 1005, 'Internet of things', 'Not Paid', NULL),
(10014, 1002, 'Nexscape virus scanner', 'Not paid', NULL),
(10015, 1001, 'Traffic controller system', 'Not Paid', NULL),
(10016, 1005, 'Web designing tool', 'Not Paid', NULL),
(10017, 1001, 'Flow chart designer', 'paid', NULL),
(10018, 1000, 'Online shopping App', 'Not Paid', NULL),
(10019, 1001, 'Vehicle rental system', 'Not Paid', NULL),
(10023, 1002, 'Error detecting software', 'Not paid', NULL),
(10024, 10005, 'Data Science and applications', 'Not paid', NULL),
(10028, 1002, 'jj', 'Not Paid', 1003),
(10029, 1002, 'jj', 'Not Paid', NULL),
(10030, 1002, 'jj', 'Not Paid', NULL),
(10031, 1002, 'gg', 'Not Paid', NULL),
(10032, 1002, 'ss', 'Not Paid', NULL),
(10040, 1002, 'eee', 'Not Paid', NULL),
(10041, 1002, 'gg', 'Not Paid', NULL),
(10042, 1002, 'testingFin', 'Not Paid', NULL),
(10045, 1002, 'bsfd', 'Not Paid', NULL),
(10046, 1002, 'bsfd', 'Not Paid', NULL),
(10047, 1002, 'scccccs', 'Not Paid', NULL),
(10048, 1002, 'aaa', 'Not Paid', NULL),
(10049, 1002, 'aaa', 'Not Paid', NULL),
(10050, 1002, 'test', 'Not Paid', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `researcher`
--

DROP TABLE IF EXISTS `researcher`;
CREATE TABLE IF NOT EXISTS `researcher` (
  `researcherId` int(11) NOT NULL,
  PRIMARY KEY (`researcherId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `researcher`
--

INSERT INTO `researcher` (`researcherId`) VALUES
(1000),
(1001),
(1002),
(1005);

-- --------------------------------------------------------

--
-- Table structure for table `unfinished`
--

DROP TABLE IF EXISTS `unfinished`;
CREATE TABLE IF NOT EXISTS `unfinished` (
  `pid` int(11) NOT NULL,
  `researcherId` int(11) NOT NULL,
  `requiredAmount` float NOT NULL,
  PRIMARY KEY (`pid`,`researcherId`),
  KEY `requiredAmount` (`requiredAmount`),
  KEY `fk_proj` (`researcherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `unfinished`
--

INSERT INTO `unfinished` (`pid`, `researcherId`, `requiredAmount`) VALUES
(10050, 1002, 0),
(10049, 1002, 7),
(10046, 1002, 70),
(10016, 1005, 50000),
(10018, 1000, 250000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `cardNumber` bigint(20) NOT NULL,
  `CVV` int(11) NOT NULL,
  `expDate` date NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `userName`, `email`, `firstName`, `lastName`, `cardNumber`, `CVV`, `expDate`) VALUES
(1001, 'kamal_d', 'kamald@gmail.com', 'Kamal', 'Dissanayake', 4567893748596782, 4562, '2023-04-12'),
(1000, 'amal_p', 'amalp@gmail.com', 'Amal', 'Perera', 1678293674893784, 6567, '2022-09-07'),
(1002, 'sunil_h', 'sunilh@gmail.com', 'Sunil', 'Hewage', 2678935678925673, 8793, '2023-01-11'),
(1003, 'gayasha_f', 'gayashaf@gmail.com', 'Gayasha', 'Fernando', 3678294678905672, 5646, '2022-11-17'),
(1004, 'tharini_j', 'tharinij@gmail.com', 'Tharini', 'Jayakody', 5698737892678356, 3456, '2023-03-08'),
(1005, 'mahesh_m', 'mahesh@gmail.com', 'Mahesh', 'Bandara', 12345678945878569, 222, '2021-04-30');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `finished`
--
ALTER TABLE `finished`
  ADD CONSTRAINT `fk_proj1` FOREIGN KEY (`pid`) REFERENCES `project` (`pid`) ON DELETE CASCADE;

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `fk_some` FOREIGN KEY (`clientid`) REFERENCES `client` (`clientId`) ON DELETE CASCADE;

--
-- Constraints for table `unfinished`
--
ALTER TABLE `unfinished`
  ADD CONSTRAINT `fk_proj2` FOREIGN KEY (`pid`) REFERENCES `project` (`pid`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
