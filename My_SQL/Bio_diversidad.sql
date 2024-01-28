-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 28-11-2022 a las 01:29:11
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
-- Estructura de tabla para la tabla `Bio_diversidad`
--

CREATE TABLE `Bio_diversidad` (
  `IDbiodiv` int(11) NOT NULL,
  `Categoria` varchar(30) NOT NULL,
  `Nombre_especie` varchar(50) NOT NULL,
  `Descrip` varchar(1000) NOT NULL,
  `Dis_geo` varchar(300) NOT NULL,
  `Estatus` varchar(300) NOT NULL,
  `Historia` varchar(1000) NOT NULL,
  `Autores` varchar(300) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Bio_diversidad`
--

INSERT INTO `Bio_diversidad` (`IDbiodiv`, `Categoria`, `Nombre_especie`, `Descrip`, `Dis_geo`, `Estatus`, `Historia`, `Autores`) VALUES
(1, 'Ave', 'Pajaro', 'Vuela', 'Parque Irekua', 'Vivo', 'Vive en el parque', 'Test'),
(2, 'Azulejo', 'Mordecai', 'Vuela', 'Zona centro', 'Vivo', 'Vive en el parque', 'Test'),
(3, 'Mapache', 'Rigby', 'Mapache cafe', 'Parque', 'Dormido', 'Amigo de mordecai', 'Test'),
(4, 'Paleta', 'Papaleta', 'paleta', 'parque', 'Muerto', 'Es una Paleta con vigote', 'Test');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Bio_diversidad`
--
ALTER TABLE `Bio_diversidad`
  ADD PRIMARY KEY (`IDbiodiv`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Bio_diversidad`
--
ALTER TABLE `Bio_diversidad`
  MODIFY `IDbiodiv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
