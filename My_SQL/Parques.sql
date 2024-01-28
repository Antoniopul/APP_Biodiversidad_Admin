-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 28-11-2022 a las 01:30:21
-- Versión del servidor: 10.5.16-MariaDB
-- Versión de PHP: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `id19695682_biodiversidad`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Parques`
--

CREATE TABLE `Parques` (
  `IDparque` int(11) NOT NULL,
  `Nombre_parque` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Historia` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `Area` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `Perimetro` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `Calle` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `Colonia` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `Municipio` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Estado` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Latitud` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Longitud` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Colindacias` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Areas_recreo` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Parques`
--

INSERT INTO `Parques` (`IDparque`, `Nombre_parque`, `Historia`, `Area`, `Perimetro`, `Calle`, `Colonia`, `Municipio`, `Estado`, `Latitud`, `Longitud`, `Colindacias`, `Areas_recreo`) VALUES
(1, 'Irekua', 'Parque nuevo ', '150 x 150', '80m cubicos', 'Insurgentes', 'Álvaro Obregón ', 'Irapuato', 'Guanajuato', '20°41\'08.1\"N 101°21\'26.3\"W', 'longitud -101.35736279283888', 'Av. Guerrero, Jardines de Irapuato, 36660', '5 hectáreas grandes '),
(2, 'Iteskua', 'itesi', 'itesi', '50mtr', 'D', 'frente al E', 'Parque', 'Itesi', '458', '1145', 'Edificio E y D', 'Plantas'),
(3, 'Parque PAPLETA', 'parque', '100m', '150mc', 'calle paleta', 'paleta', 'Iua', 'Eua', '1515', '54151', 'A lado de otro parque', '2000mtrs');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Parques`
--
ALTER TABLE `Parques`
  ADD PRIMARY KEY (`IDparque`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Parques`
--
ALTER TABLE `Parques`
  MODIFY `IDparque` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
