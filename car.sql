-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 18, 2016 at 05:49 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `license_plate` varchar(15) NOT NULL,
  `manufacturer` varchar(15) NOT NULL,
  `model` varchar(20) NOT NULL,
  `year` year(4) NOT NULL,
  `color` varchar(15) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `picture_name` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`license_plate`, `manufacturer`, `model`, `year`, `color`, `price`, `picture_name`) VALUES
('ABC0001', 'Subaru', 'Impreza', 2016, 'blue', '18295.00', 'subaru_impreza.jpg'),
('ABC0002', 'Kia', 'Forte Koup', 2013, 'black', '19890.00', 'kia_forte_koup.jpg'),
('ABC0003', 'Tesla', 'Model S', 2015, 'red', '61000.00', 'tesla_model_s.jpg'),
('ABC0004', 'Jeep', 'Cherokee', 2016, 'white', '23395.00', 'jeep_cherokee.jpg'),
('ABC0005', 'Dodge', 'Journey', 2012, 'black', '20995.00', 'dodge_journey.jpg'),
('ABC0006', 'Hyundai', 'Veloster', 2016, 'red', '18000.00', 'hyundai_veloster.jpg'),
('ABC0007', 'Honda', 'CR-V', 2015, 'silver', '23745.00', 'honda_cr-v.jpg'),
('ABC0008', 'Toyota', 'Camry', 2016, 'black', '23070.00', 'toyota_camry.jpg'),
('ABC0009', 'Chevrolet', 'Camaro', 2013, 'yellow', '25700.00', 'chevrolet_camaro.jpg'),
('ABC0010', 'Ford', 'Fusion', 2015, 'white', '22260.00', 'ford_fusion.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`license_plate`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
