-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-05-2016 a las 17:19:22
-- Versión del servidor: 5.6.26
-- Versión de PHP: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `iw2016ujob`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curriculum`
--

CREATE TABLE IF NOT EXISTS `curriculum` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `formacion` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `dni` varchar(9) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `curriculum`
--

INSERT INTO `curriculum` (`id`, `descripcion`, `formacion`, `version`, `dni`) VALUES
(1, 'descripcion_0', 'formacion_0', 0, NULL),
(2, 'descripcion_1', 'formacion_1', 0, NULL),
(3, 'descripcion_2', 'formacion_2', 0, NULL),
(4, 'descripcion_3', 'formacion_3', 0, NULL),
(6, 'descripcion_5', 'formacion_5', 0, NULL),
(7, 'descripcion_6', 'formacion_6', 0, NULL),
(8, 'descripcion_7', 'formacion_7', 0, NULL),
(9, 'descripcion_8', 'formacion_8', 0, NULL),
(10, 'descripcion_9', 'formacion_9', 0, NULL),
(11, 'descripcion_2147483647', 'formacion_2147483647', 0, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `demandante`
--

CREATE TABLE IF NOT EXISTS `demandante` (
  `dni` varchar(9) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `experiencia` varchar(255) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `sexo` varchar(255) NOT NULL,
  `telefono` varchar(9) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `usuario` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `demandante`
--

INSERT INTO `demandante` (`dni`, `direccion`, `email`, `experiencia`, `fecha_nacimiento`, `nombre`, `sexo`, `telefono`, `version`, `usuario`) VALUES
('30000000L', 'test@test.com', 'test@test.com', 'lots', '2016-05-16', 'DemandanteTest', 'Hombre', '956321137', 1, '30000000L'),
('34589903V', 'bailalololol', 'mantec@goaoao.com', 'Bombero', '2016-05-03', 'manteca', 'mujer', '564324567', 0, '56798834O'),
('45319987X', 'el puerto', 'troli@test.com', 'bigdata', '2016-05-04', 'troli', 'hombre', '689459399', 0, '49356690X');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE IF NOT EXISTS `empresa` (
  `cif` varchar(9) NOT NULL,
  `email` varchar(255) NOT NULL,
  `n_empleados` int(11) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `otros` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`cif`, `email`, `n_empleados`, `nombre`, `otros`, `version`) VALUES
('32345590F', 'empresa@hola.com', 200, 'Empresita S.L.', 'Buscamos personal capacitado', 0),
('55690034G', 'frutita@gmail.com', 10, 'Fruteros S.L.', 'Trabajadores con gran experiencia', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `experiencia`
--

CREATE TABLE IF NOT EXISTS `experiencia` (
  `id` int(11) NOT NULL,
  `fecha_fin` varchar(10) NOT NULL,
  `fecha_inicio` varchar(10) NOT NULL,
  `nombre_empresa` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion`
--

CREATE TABLE IF NOT EXISTS `inscripcion` (
  `id` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  `fecha_inscripcion` date NOT NULL,
  `version` int(11) DEFAULT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `id_oferta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `localizacion`
--

CREATE TABLE IF NOT EXISTS `localizacion` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `cif_empresa` varchar(9) DEFAULT NULL,
  `oferta` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `localizacion`
--

INSERT INTO `localizacion` (`id`, `nombre`, `version`, `cif_empresa`, `oferta`) VALUES
(1, 'Jerez', 0, '32345590F', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `oferta`
--

CREATE TABLE IF NOT EXISTS `oferta` (
  `id` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  `fecha_caducidad` date NOT NULL,
  `fecha_inicio` date NOT NULL,
  `n_vacantes` int(11) NOT NULL,
  `perfil` varchar(255) NOT NULL,
  `puesto` varchar(255) NOT NULL,
  `sueldo` float NOT NULL,
  `tipo_contrato` varchar(50) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `cif_empresa` varchar(9) DEFAULT NULL,
  `localizacion` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `oferta`
--

INSERT INTO `oferta` (`id`, `estado`, `fecha_caducidad`, `fecha_inicio`, `n_vacantes`, `perfil`, `puesto`, `sueldo`, `tipo_contrato`, `version`, `cif_empresa`, `localizacion`) VALUES
(6, 1, '2016-05-28', '2016-05-05', 5, 'Ingeniero Informático', 'Programador', 600, 'Fijo', 0, '32345590F', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `titulacion`
--

CREATE TABLE IF NOT EXISTS `titulacion` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `dni` varchar(9) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `titulacion`
--

INSERT INTO `titulacion` (`id`, `descripcion`, `version`, `dni`) VALUES
(1, 'Ingeniero Informático', 0, '45319987X'),
(2, 'Policia', 0, '34589903V');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `dni` varchar(9) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enable` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `empresa` varchar(9) DEFAULT NULL,
  `user_role` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`dni`, `email`, `enable`, `name`, `password`, `username`, `version`, `empresa`, `user_role`) VALUES
('30000000L', 'test@test.com', b'1', 'DemandanteTest', 'DemandanteTest', 'DemandanteTest', 0, '32345590F', 3),
('32088008L', 'test@test.com', b'1', 'DemandantePrueba', 'Demandante', 'Demandante', 0, NULL, 3),
('34000000L', 'test@test.com', b'1', 'GestorEtt', 'GestorEtt', 'GestorEtt', 0, '32345590F', 2),
('45055555L', 'test@test.com', b'1', 'GestorEmpresa', 'GestorEmpresa', 'GestorEmpresa', 0, '32345590F', 4),
('45326690X', 'ruben@test.com', b'1', 'rubencito', 'Administrador', 'Administrador', 0, NULL, 1),
('45399923C', 'soy@gmail.com', b'1', 'soyyotio', 'soydemandante', 'soydemandante', 0, NULL, 3),
('49356690X', 'troli@test.com', b'1', 'troliperoli', 'troli666', 'troli666', 0, NULL, 3),
('56798834O', 'mantc@loco.com', b'1', 'mantecaoole', 'manteca666', 'manteca666', 0, NULL, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `user_role`
--

INSERT INTO `user_role` (`id`, `name`, `version`) VALUES
(1, 'Admin', 10),
(2, 'GestorETT', 0),
(3, 'Demandante', 10),
(4, 'GestorEmpresa', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `curriculum`
--
ALTER TABLE `curriculum`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_3lsuepj8b40ln5o3h4aagkmpx` (`dni`);

--
-- Indices de la tabla `demandante`
--
ALTER TABLE `demandante`
  ADD PRIMARY KEY (`dni`),
  ADD KEY `FK_easbdttsurflhhldddb36shvn` (`usuario`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`cif`),
  ADD UNIQUE KEY `UK_2fqlxbcs4h827hio1qam0dhd3` (`nombre`);

--
-- Indices de la tabla `experiencia`
--
ALTER TABLE `experiencia`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_4myjdgdlx7jtdkefw04kxsyna` (`dni`),
  ADD KEY `FK_ds2ahd6b1aihkkodv9n1ggn1k` (`id_oferta`);

--
-- Indices de la tabla `localizacion`
--
ALTER TABLE `localizacion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_sk97mp4u767elwfmkx9g4m88j` (`nombre`),
  ADD KEY `FK_glwxgjkr1i4syso303c8l4kmr` (`cif_empresa`),
  ADD KEY `FK_1mcmylu72y69tgop1ygbu8art` (`oferta`);

--
-- Indices de la tabla `oferta`
--
ALTER TABLE `oferta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_6sb9faiy0v85610equd5vwem9` (`cif_empresa`),
  ADD KEY `FK_5m6k3hjnv4ytgy9agn0rwopnw` (`localizacion`);

--
-- Indices de la tabla `titulacion`
--
ALTER TABLE `titulacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_4mjq3qq2sqv6xyr0qgnsevfki` (`dni`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`dni`),
  ADD KEY `FK_51lycc5ugu3w2p95hwqb4byfu` (`empresa`),
  ADD KEY `FK_lymayy5bxgau375u0hesc9h6v` (`user_role`);

--
-- Indices de la tabla `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `curriculum`
--
ALTER TABLE `curriculum`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `localizacion`
--
ALTER TABLE `localizacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `oferta`
--
ALTER TABLE `oferta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `titulacion`
--
ALTER TABLE `titulacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `user_role`
--
ALTER TABLE `user_role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `curriculum`
--
ALTER TABLE `curriculum`
  ADD CONSTRAINT `FK_3lsuepj8b40ln5o3h4aagkmpx` FOREIGN KEY (`dni`) REFERENCES `demandante` (`dni`);

--
-- Filtros para la tabla `demandante`
--
ALTER TABLE `demandante`
  ADD CONSTRAINT `FK_easbdttsurflhhldddb36shvn` FOREIGN KEY (`usuario`) REFERENCES `users` (`dni`);

--
-- Filtros para la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD CONSTRAINT `FK_4myjdgdlx7jtdkefw04kxsyna` FOREIGN KEY (`dni`) REFERENCES `demandante` (`dni`),
  ADD CONSTRAINT `FK_ds2ahd6b1aihkkodv9n1ggn1k` FOREIGN KEY (`id_oferta`) REFERENCES `oferta` (`id`);

--
-- Filtros para la tabla `localizacion`
--
ALTER TABLE `localizacion`
  ADD CONSTRAINT `FK_1mcmylu72y69tgop1ygbu8art` FOREIGN KEY (`oferta`) REFERENCES `oferta` (`id`),
  ADD CONSTRAINT `FK_glwxgjkr1i4syso303c8l4kmr` FOREIGN KEY (`cif_empresa`) REFERENCES `empresa` (`cif`);

--
-- Filtros para la tabla `oferta`
--
ALTER TABLE `oferta`
  ADD CONSTRAINT `FK_5m6k3hjnv4ytgy9agn0rwopnw` FOREIGN KEY (`localizacion`) REFERENCES `localizacion` (`id`),
  ADD CONSTRAINT `FK_6sb9faiy0v85610equd5vwem9` FOREIGN KEY (`cif_empresa`) REFERENCES `empresa` (`cif`);

--
-- Filtros para la tabla `titulacion`
--
ALTER TABLE `titulacion`
  ADD CONSTRAINT `FK_4mjq3qq2sqv6xyr0qgnsevfki` FOREIGN KEY (`dni`) REFERENCES `demandante` (`dni`);

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK_51lycc5ugu3w2p95hwqb4byfu` FOREIGN KEY (`empresa`) REFERENCES `empresa` (`cif`),
  ADD CONSTRAINT `FK_lymayy5bxgau375u0hesc9h6v` FOREIGN KEY (`user_role`) REFERENCES `user_role` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
