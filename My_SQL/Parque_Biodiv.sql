-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 28-11-2022 a las 01:30:44
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
-- Estructura de tabla para la tabla `Parque_Biodiv`
--

CREATE TABLE `Parque_Biodiv` (
  `IDrelacion` int(11) NOT NULL,
  `IDparq` int(11) NOT NULL,
  `IDdiv` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Parque_Biodiv`
--

INSERT INTO `Parque_Biodiv` (`IDrelacion`, `IDparq`, `IDdiv`) VALUES
(1, 1, 2),
(24, 2, 3),
(26, 2, 1),
(27, 1, 3),
(30, 3, 3),
(32, 3, 4),
(33, 1, 4),
(35, 3, 2),
(36, 3, 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Parque_Biodiv`
--
ALTER TABLE `Parque_Biodiv`
  ADD PRIMARY KEY (`IDrelacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Parque_Biodiv`
--
ALTER TABLE `Parque_Biodiv`
  MODIFY `IDrelacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
