-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 12:37 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `unitrecords`
--

CREATE TABLE `unitrecords` (
  `recordID` int(5) NOT NULL,
  `accountNo` varchar(6) NOT NULL,
  `consumerName` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `district` varchar(100) NOT NULL,
  `consumedUnits` int(10) NOT NULL,
  `year` varchar(10) NOT NULL,
  `month` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `unitrecords`
--

INSERT INTO `unitrecords` (`recordID`, `accountNo`, `consumerName`, `address`, `district`, `consumedUnits`, `year`, `month`) VALUES
(1, 'C12345', 'Nihara Ekkanayke', '23, Youth place,Nugegoda', 'Colombo', 200, '2022', 'January'),
(2, 'C98765', 'Omala Koralage', '23,Avenue Road', 'Colombo', 150, '2022', 'February'),
(3, 'G12345', 'Chamodhi Kaveendhya Gembi', '45,Battharamulla', 'Gampha', 250, '2022', 'April');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `unitrecords`
--
ALTER TABLE `unitrecords`
  ADD PRIMARY KEY (`recordID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `unitrecords`
--
ALTER TABLE `unitrecords`
  MODIFY `recordID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
