-- --------------------------------------------------------
-- Host:                         
-- Server version:               10.4.12-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping data for table transactions.exchange_rates: ~13 rows (approximately)
/*!40000 ALTER TABLE `exchange_rates` DISABLE KEYS */;
INSERT INTO `exchange_rates` (`id`, `source_currency`, `target_currency`, `exchange_rate`) VALUES
	(17, 'XPF', 'USD', 0.00986678790449415),
	(18, 'XPF', 'BGN', 0.016358285801891515),
	(19, 'CAD', 'BGN', 1.264371918505554),
	(20, 'CAD', 'AED', 2.8011027603326584),
	(21, 'USD', 'CAD', 1.311255),
	(22, 'BYR', 'CAD', 0.00006690076530612246),
	(23, 'AED', 'CAD', 0.3436123048191016),
	(24, 'BGN', 'CAD', 0.76568583558097),
	(25, 'XPF', 'CAD', 0.012389252385028382),
	(26, 'AED', 'CAD', 0.3436123048191016),
	(27, 'AED', 'CAD', 0.3436123048191016),
	(28, 'AED', 'CAD', 0.3436123048191016),
	(29, 'XPF', 'CAD', 0.012342084799226865);
/*!40000 ALTER TABLE `exchange_rates` ENABLE KEYS */;

-- Dumping data for table transactions.transactions: ~13 rows (approximately)
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` (`id`, `date_of_creation`, `source_amount`, `exchange_rate_id`, `target_amount`) VALUES
	(17, '2021-04-09', 3, 17, 0.02960036371348245),
	(18, '2021-04-09', 3, 18, 0.04907485740567455),
	(19, '2021-04-09', 3, 19, 3.793115755516662),
	(20, '2021-04-09', 3, 20, 8.403308280997976),
	(21, '2021-04-10', 3, 21, 3.933765),
	(22, '2021-04-10', 3, 22, 0.00020070229591836737),
	(23, '2021-04-10', 60, 23, 20.616738289146095),
	(24, '2021-04-10', 3, 24, 2.29705750674291),
	(25, '2021-04-11', 3, 25, 0.03716775715508515),
	(26, '2021-04-11', 3, 26, 1.0308369144573049),
	(27, '2021-04-11', 3, 27, 1.0308369144573049),
	(28, '2021-04-11', 3, 28, 1.0308369144573049),
	(29, '2021-04-12', 7, 29, 0.08639459359458805);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
