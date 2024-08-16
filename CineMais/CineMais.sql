-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 16/08/2024 às 06:41
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
(2, 'Bastardos Inglórios', 'Quentin Tarantino', 'Ação', '2009', 'Durante a Segunda Guerra Mundial, na França, um grupo de judeus americanos conhecidos como Bastardos espalha o terror entre o terceiro Reich. Ao mesmo tempo, Shosanna, uma judia que fugiu dos nazistas, planeja vingança quando um evento em seu cinema reunirá os líderes do partido.'),
(3, 'Three Colors: Red', 'Krzysztof Kieślowski', 'Drama', '1994', 'Uma jovem modelo atropela um cachorro e quando procura o dono do animal conhece um juiz aposentado que passa a maior parte de seu tempo ouvindo as conversas telefônicas de seus vizinhos. A partir desse encontro, os dois formam uma estranha amizade.');

-- --------------------------------------------------------

--
-- Estrutura para tabela `ingressos`
--

CREATE TABLE `ingressos` (
  `ID Ingresso` int(11) NOT NULL,
  `Nome da Pessoa` text NOT NULL,
  `Telefone` text NOT NULL,
  `ID Sessao` int(11) NOT NULL,
  `ID Filme` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `produtos_cinema`
--

CREATE TABLE `produtos_cinema` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `valor` double NOT NULL,
  `volume` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produtos_cinema`
--

INSERT INTO `produtos_cinema` (`id`, `nome`, `valor`, `volume`) VALUES
(1, 'Coca-Cola', 5, '500ml'),
(2, 'Pepsi', 5, '500 ml'),
(3, 'Sprite', 5, '500 ml'),
(4, 'Agua Mineral', 3, '500ml'),
(5, 'Suco de Laranja', 6, '400 ml'),
(6, 'Suco de Maca', 6, '400g'),
(7, 'Cerveja', 8, '500 ml'),
(8, 'Cha Gelado', 4.5, '500ml'),
(9, 'Milkshake de Chocolate', 7, '300 ml'),
(10, 'Milkshake de Baunilha', 7, '300 ml'),
(11, 'Milkshake de Morango', 7, '300 ml'),
(12, 'Wrap de Frango', 7.5, '400g'),
(13, 'Pipoca Salgada', 4, '200 g'),
(14, 'Pipoca Doce', 4.5, '200 g'),
(15, 'Nachos com Queijo', 6.5, '250 g'),
(16, 'Coxinha', 3, '60 g'),
(17, 'Empada', 3.5, '80 g'),
(18, 'Risole', 3, '70 g'),
(19, 'Churros', 4, '100 g'),
(20, 'Mini Hot Dogs', 5.5, '120 g'),
(21, 'Pizza em Fatias', 6, '150 g'),
(22, 'Wrap de Frango', 7.5, '400g'),
(23, 'Chocolate Amargo', 2.7, '50 g'),
(24, 'Amendoins', 3, '100 g'),
(26, 'Wrap de Frango', 7.5, '400g');

-- --------------------------------------------------------

--
-- Estrutura para tabela `sessoes`
--

CREATE TABLE `sessoes` (
  `ID Sessao` int(11) NOT NULL,
  `Sala` int(11) NOT NULL,
  `Numero Poltronas` int(11) NOT NULL,
  `Horario` text NOT NULL,
  `ID Filme` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `Usuário` text NOT NULL,
  `Senha` text NOT NULL,
  `Pessoa` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `users`
--

INSERT INTO `users` (`ID`, `Usuário`, `Senha`, `Pessoa`) VALUES
(1, 'Pedro', '123', 'Pedro Cesar Rocha'),
(8, 'Leandro', '123', 'Leandro Rosa');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `filmografia`
--
ALTER TABLE `filmografia`
  ADD PRIMARY KEY (`ID`);

--
-- Índices de tabela `ingressos`
--
ALTER TABLE `ingressos`
  ADD PRIMARY KEY (`ID Ingresso`);

--
-- Índices de tabela `produtos_cinema`
--
ALTER TABLE `produtos_cinema`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `sessoes`
--
ALTER TABLE `sessoes`
  ADD PRIMARY KEY (`ID Sessao`);

--
-- Índices de tabela `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `filmografia`
--
ALTER TABLE `filmografia`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de tabela `ingressos`
--
ALTER TABLE `ingressos`
  MODIFY `ID Ingresso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `produtos_cinema`
--
ALTER TABLE `produtos_cinema`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de tabela `sessoes`
--
ALTER TABLE `sessoes`
  MODIFY `ID Sessao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
