-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 14-Mar-2021 às 10:45
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE projectojsp;

CREATE TABLE `categoria` (
  `idCat` int(11) NOT NULL,
  `nomeCat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------


CREATE TABLE `endereco` (
  `idEndereco` int(11) NOT NULL,
  `enderecoId` int(11) NOT NULL,
  `provincia` varchar(255) NOT NULL,
  `distrito` varchar(255) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `telefone2` varchar(15) DEFAULT NULL,
  `bairro` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------


CREATE TABLE `produto` (
  `idProd` int(11) NOT NULL,
  `nomeProd` varchar(150) NOT NULL,
  `quantProd` int(10) NOT NULL,
  `valorProd` decimal(18,2) NOT NULL,
  `categoria_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------


CREATE TABLE `usuario` (
  `idUser` int(11) NOT NULL,
  `nomeCompleto` varchar(200) NOT NULL,
  `biUser` varchar(15) NOT NULL,
  `telefoneUser` varchar(15) NOT NULL,
  `nomeUser` varchar(20) NOT NULL,
  `senhaUser` varchar(20) NOT NULL,
  `imagem` longblob DEFAULT NULL,
  `curriculo` longblob DEFAULT NULL,
  `contentType` text DEFAULT NULL,
  `contentTypeCv` text DEFAULT NULL,
  `imageMin` longblob DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `perfil` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--


ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idCat`);


ALTER TABLE `endereco`
  ADD PRIMARY KEY (`idEndereco`),
  ADD KEY `enderecoId` (`enderecoId`);


ALTER TABLE `produto`
  ADD PRIMARY KEY (`idProd`),
  ADD KEY `categoria_id` (`categoria_id`);


ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

ALTER TABLE `categoria`
  MODIFY `idCat` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `endereco`
  MODIFY `idEndereco` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `produto`
  MODIFY `idProd` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `usuario`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--


ALTER TABLE `endereco`
  ADD CONSTRAINT `endereco_ibfk_1` FOREIGN KEY (`enderecoId`) REFERENCES `usuario` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `produto`
  ADD CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`idCat`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
