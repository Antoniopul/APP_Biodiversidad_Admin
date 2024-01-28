-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 28-11-2022 a las 01:29:55
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
-- Estructura de tabla para la tabla `Fotografias`
--

CREATE TABLE `Fotografias` (
  `IDfoto` int(11) NOT NULL,
  `ID_asociada` int(11) NOT NULL,
  `Categoria` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `Foto` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `NombreF` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Autores` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Fotografias`
--

INSERT INTO `Fotografias` (`IDfoto`, `ID_asociada`, `Categoria`, `Foto`, `NombreF`, `Autores`) VALUES
(1, 2, 'Biodiversidad', 'https://biodivparques.000webhostapp.com/uploads/Azulejo.png', 'Azul ejo', 'Test'),
(2, 2, 'Biodiversidad', 'https://biodivparques.000webhostapp.com/uploads/Mordecai.png', 'Mordecai', 'Test'),
(3, 4, 'Biodiversidad', 'https://biodivparques.000webhostapp.com/uploads/Papaleta.png', 'Papaleta', 'Test'),
(4, 3, 'Biodiversidad', 'https://biodivparques.000webhostapp.com/uploads/Rigby.png', 'Rigby', 'Test'),
(6, 2, 'Parque', 'https://biodivparques.000webhostapp.com/uploads/Vista Aérea.png', 'Vista Aérea', 'Test'),
(7, 3, 'Parque', 'https://biodivparques.000webhostapp.com/uploads/Casa de Benson.png', 'Casa de Benson', 'Test'),
(8, 3, 'Parque', 'https://biodivparques.000webhostapp.com/uploads/Parque.png', 'Parque', 'Test'),
(10, 1, 'Parque', 'https://biodivparques.000webhostapp.com/uploads/Irekua Noche.png', 'Irekua Noche', 'Test');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Fotografias`
--
ALTER TABLE `Fotografias`
  ADD PRIMARY KEY (`IDfoto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Fotografias`
--
ALTER TABLE `Fotografias`
  MODIFY `IDfoto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
