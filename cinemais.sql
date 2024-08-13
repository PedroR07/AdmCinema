-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 13/08/2024 às 15:48
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `cinemais`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `filmografia`
--

CREATE TABLE `filmografia` (
  `ID` int(11) NOT NULL,
  `Nome` text NOT NULL,
  `Diretor` text NOT NULL,
  `Genero` text NOT NULL,
  `Ano` text NOT NULL,
  `Sinopse` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `filmografia`
--

INSERT INTO `filmografia` (`ID`, `Nome`, `Diretor`, `Genero`, `Ano`, `Sinopse`) VALUES
(1, 'Ela', 'Spike Jonze', 'Drama e Romance', '2013', 'Em Ela, Theodore (Joaquin Phoenix) é um escritor solitário, que acaba de comprar um novo sistema operacional para seu computador. Para a sua surpresa, ele acaba se apaixonando pela voz deste programa informático, dando início a uma relação amorosa entre ambos.'),
(2, 'Bastardos Inglórios', 'Quentin Tarantino', 'Ação', '2009', 'Durante a Segunda Guerra Mundial, na França, um grupo de judeus americanos conhecidos como Bastardos espalha o terror entre o terceiro Reich. Ao mesmo tempo, Shosanna, uma judia que fugiu dos nazistas, planeja vingança quando um evento em seu cinema reunirá os líderes do partido.');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `filmografia`
--
ALTER TABLE `filmografia`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `filmografia`
--
ALTER TABLE `filmografia`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
