-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 28-11-2022 a las 01:28:20
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
-- Estructura de tabla para la tabla `Administrador`
--

CREATE TABLE `Administrador` (
  `IDadmin` int(50) NOT NULL,
  `Nombre` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Pasword` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Administrador`
--

INSERT INTO `Administrador` (`IDadmin`, `Nombre`, `Pasword`) VALUES
(10, 'yo', '123'),
(18, 'AdminTEST', '123');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Administrador`
--
ALTER TABLE `Administrador`
  ADD PRIMARY KEY (`IDadmin`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Administrador`
--
ALTER TABLE `Administrador`
  MODIFY `IDadmin` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
