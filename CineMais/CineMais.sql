-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 19/08/2024 às 04:28
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
(3, 'Três Cores: Vermelho', 'Krzysztof Kieślowski', 'Drama', '1994', 'O filme é o terceiro e último episódio da trilogia \"Três Cores\", que explora temas relacionados às cores da bandeira francesa e seus valores simbólicos. \"Vermelho\" segue a história de uma jovem modelo chamada Valentine, que, após um acidente de carro, se encontra envolvida na vida de um recluso juiz de direito que vive isolado. Ao longo do filme, Valentine descobre que o juiz está secretamente espionando a vida dos seus vizinhos, revelando uma série de conexões e coincidências que exploram a complexidade das relações humanas e a busca por compreensão e conexão.'),
(4, 'O Senhor dos Anéis: A Sociedade do Anel', 'Peter Jackson', 'Ação / Aventura / Fantasia', '2001', 'No início da era da Terra-média, um jovem hobbit chamado Frodo Bolseiro é incumbido de uma missão crucial: destruir um poderoso anel que pode trazer a ruína ao mundo. Acompanhado por um grupo de heróis diversos, conhecidos como a Sociedade do Anel, Frodo e seus companheiros enfrentam desafios e perigos imensos enquanto tentam evitar que o sombrio Senhor das Trevas, Sauron, recupere o anel e retorne ao poder. O filme é a primeira parte da trilogia épica baseada na obra de J.R.R. Tolkien.'),
(5, 'O Senhor dos Anéis: As Duas Torres', 'Peter Jackson', 'Ação / Aventura / Fantasia', '2002', 'A segunda parte da trilogia \"O Senhor dos Anéis\" continua a jornada épica pela Terra-média. Após a separação da Sociedade do Anel, os membros seguem caminhos distintos: Frodo Bolseiro e Samwise Gamgee continuam sua perigosa missão para destruir o Um Anel, enquanto Aragorn, Legolas e Gimli se unem para ajudar os habitantes de Rohan na luta contra o exército de Saruman. O filme apresenta batalhas épicas e o crescente conflito entre o bem e o mal enquanto os heróis enfrentam novos desafios e inimigos.'),
(6, 'O Senhor dos Anéis: O Retorno do Rei', 'Peter Jackson', 'Ação / Aventura / Fantasia', '2003', 'No final da trilogia \"O Senhor dos Anéis\", a Terra-média está à beira da destruição enquanto a batalha final contra o Senhor das Trevas, Sauron, se aproxima. Frodo Bolseiro e Samwise Gamgee continuam sua jornada até a Montanha da Perdição para destruir o Um Anel, enquanto Aragorn se prepara para assumir seu destino como rei e liderar a luta contra as forças de Sauron. Com batalhas épicas e momentos emocionantes, o filme culmina na resolução da saga, mostrando a luta pela liberdade e a esperança que persiste mesmo diante das maiores adversidades.'),
(7, 'A Rede Social', 'David Fincher', 'Drama / Biografia', '2010', 'O filme narra a criação e o desenvolvimento do Facebook, desde os primeiros dias de Mark Zuckerberg como estudante de Harvard até a explosão global da rede social. A história aborda a ambição, os conflitos e as controvérsias legais que surgiram à medida que Zuckerberg, interpretado por Jesse Eisenberg, construiu uma das plataformas de mídia social mais influentes do mundo. O enredo explora as complexidades das relações pessoais e profissionais de Zuckerberg e o impacto da sua invenção na sociedade moderna.'),
(8, 'A Vida Invisível', 'Karim Aïnouz', 'Drama', '2019', 'Ambientado no Rio de Janeiro dos anos 1950, o filme segue a vida de duas irmãs, Eurídice e Guida Gusmão, que são separadas pela força das normas sociais e familiares. Eurídice, a irmã mais velha, é forçada a se conformar com as expectativas de uma vida tradicional, enquanto Guida busca liberdade e aventura fora de casa. A trama explora as complexidades da feminilidade, a opressão e o desejo de autonomia das mulheres em um período em que suas vozes eram frequentemente silenciadas. O filme é uma adaptação do livro homônimo de Martha Batalha.'),
(9, 'Antes do Amanhecer', 'Richard Linklater', 'Drama / Romance', '1995', 'O filme segue Jesse, um jovem americano, e Céline, uma estudante francesa, que se conhecem em um trem e decidem passar uma noite juntos em Viena antes de seguir seus caminhos separados. Ao longo dessa única noite, eles compartilham conversas profundas e íntimas sobre a vida, o amor e seus sonhos, formando uma conexão inesperada e significativa. \"Before Sunrise\" explora o impacto de um encontro efêmero e como momentos de conexão genuína podem marcar nossas vidas para sempre.'),
(10, 'Central do Brasil', 'Walter Salles', 'Drama', '1998', 'O filme segue a história de Dora, uma ex-professora que trabalha como escritora de cartas na estação Central do Brasil no Rio de Janeiro. Ela escreve cartas para pessoas analfabetas e se vê envolvida na vida de um menino chamado Josué, que fica órfão após a morte de sua mãe. Dora, inicialmente relutante, acaba se tornando responsável por Josué e os dois embarcam em uma jornada pelo Brasil para encontrar o pai do menino, em uma busca que revela muito sobre suas próprias vidas e esperanças. \"Central do Brasil\" é uma comovente história sobre a relação entre um adulto e uma criança, e a jornada emocional e física que eles empreendem juntos.'),
(11, 'Blade Runner: Caçador de Andróides', 'Ridley Scott', 'Ficção Científica / Noir', '1982', 'Em um futuro distópico no ano de 2019, o detetive Rick Deckard é um \"blade runner\", uma espécie de caçador de recompensas encarregado de capturar e \"aposentar\" replicantes, seres artificiais quase indistinguíveis dos humanos. Esses replicantes são ilegais na Terra e foram criados para servir como trabalhadores em colônias espaciais. Deckard é chamado para caçar um grupo de replicantes que retornaram à Terra em busca de uma forma de prolongar sua vida. O filme explora temas complexos sobre identidade, humanidade e a ética da inteligência artificial, apresentando um visual futurista e uma atmosfera sombria e estilizada.'),
(12, 'Collateral ', 'Michael Mann', 'Ação / Crime / Suspense', '2004', 'O filme segue Max, um taxista de Los Angeles, que se vê em uma situação perigosa quando um assassino profissional, Vincent, entra em seu carro e o obriga a ser seu motorista durante uma noite de trabalho. Vincent, interpretado por Tom Cruise, está em uma missão para eliminar cinco testemunhas de um caso judicial. Max, interpretado por Jamie Foxx, tenta desesperadamente encontrar uma forma de escapar e salvar a própria vida enquanto se vê envolvido em uma série de eventos violentos e mortais. O filme combina suspense e ação, explorando o jogo de gato e rato entre o taxista e o assassino.'),
(13, 'De Olhos Bem Fechados', 'Stanley Kubrick', 'Drama / Mistério / Suspense', '1999', 'O filme segue Dr. Bill Harford, interpretado por Tom Cruise, que após uma festa de Natal, descobre que sua esposa, Alice (interpretada por Nicole Kidman), teve pensamentos sobre infidelidade. Esse evento desencadeia uma série de acontecimentos que levam Bill a se envolver em uma sociedade secreta e a um mundo de excessos e mistérios. A trama explora temas de desejo, traição e o impacto do comportamento humano nas relações pessoais. \"De Olhos Bem Fechados\" é conhecido por seu estilo visual distinto e sua abordagem enigmática dos temas que aborda.'),
(14, 'Brilho Eterno de uma Mente Sem Lembranças', 'Michel Gondry', 'Drama / Romance / Ficção Científica', '2004', 'O filme conta a história de Joel e Clementine, um casal que decide apagar as memórias de seu relacionamento após uma dolorosa separação. Utilizando um procedimento científico fictício que permite apagar memórias específicas, eles tentam se livrar da dor de sua relação passada. No entanto, enquanto Joel revisita suas memórias de Clementine durante o processo de apagamento, ele percebe o quanto ainda a ama e tenta lutar para manter suas lembranças. \"Brilho Eterno de uma Mente Sem Lembranças\" explora temas complexos sobre amor, memória e o impacto das relações em nossas vidas.'),
(15, 'Interestelar', 'Christopher Nolan', 'Ficção Científica / Drama / Aventura', '2014', 'O filme se passa em um futuro próximo, onde a Terra está enfrentando uma crise ambiental que ameaça a sobrevivência da humanidade. Cooper, um ex-piloto da NASA e engenheiro, é recrutado para uma missão espacial para encontrar um novo lar para a humanidade. Junto com uma equipe de astronautas, ele viaja por um buraco de minhoca perto de Saturno para explorar planetas em outra galáxia. \"Interestelar\" explora temas de amor, sacrifício e a relação entre pai e filha, enquanto lida com conceitos complexos de física teórica, como buracos de minhoca e viagens no tempo.'),
(16, 'Missão: Impossível', 'Brian De Palma', 'Ação / Espionagem / Suspense', '1996', 'O filme segue Ethan Hunt, um agente da Impossible Mission Force (IMF), que é acusado de trair sua equipe durante uma missão que dá errado. Com sua equipe sendo eliminada e a sua vida em perigo, Ethan deve encontrar os verdadeiros traidores e limpar seu nome. Usando sua habilidade em infiltração, disfarces e tecnologia avançada, ele enfrenta uma série de desafios e reviravoltas para desvendar uma conspiração global. O filme é conhecido por suas cenas de ação intensas e sequências de espionagem emocionantes.'),
(17, 'O Iluminado', 'Stanley Kubrick', 'Terror / Suspense', '1980', 'O filme segue Jack Torrance, um escritor que aceita um emprego como zelador de inverno em um hotel isolado nas montanhas, o Overlook Hotel. Ele se muda para lá com sua esposa Wendy e seu filho Danny, que possui habilidades psíquicas, conhecidas como \"o brilho\", que permitem que ele veja eventos sobrenaturais. À medida que o isolamento e o ambiente perturbador do hotel começam a afetar Jack, ele gradualmente sucumbe à influência sobrenatural do lugar e se torna uma ameaça perigosa para sua família. O filme é famoso por sua atmosfera tensa, direção de Kubrick e pela icônica atuação de Jack Nicholson.'),
(18, 'O Silêncio dos Inocentes', 'Jonathan Demme', 'Drama / Crime / Suspense', '1991', 'O filme segue Clarice Starling, uma jovem agente do FBI, que é designada para entrevistar o Dr. Hannibal Lecter, um psicopata e ex-médico, que está preso por crimes de canibalismo e assassinato. O FBI busca a ajuda de Lecter para capturar um outro assassino em série conhecido como \"Buffalo Bill\", que está sequestrando e matando mulheres. Durante as entrevistas, Clarice e Lecter desenvolvem uma relação complexa, e o filme explora temas de manipulação psicológica e inteligência criminal. \"O Silêncio dos Inocentes\" é aclamado por suas performances intensas, especialmente de Anthony Hopkins como Hannibal Lecter, e é considerado um clássico do gênero thriller psicológico.'),
(19, 'O Labirinto do Fauno', 'Guillermo del Toro', 'Fantasia / Drama / Guerra', '2006', 'Ambientado na Espanha pós-Guerra Civil, o filme segue Ofélia, uma jovem garota que se muda com sua mãe para a casa do novo padrasto, um brutal capitão do exército franquista. Ofélia descobre um labirinto misterioso e encontra um fauno que lhe revela que ela é a reencarnação de uma princesa perdida. Para retornar ao seu reino subterrâneo, ela deve completar três tarefas perigosas e enfrentar desafios que misturam o mundo real com o fantástico. O filme combina elementos de conto de fadas com a dura realidade da guerra, explorando temas de inocência, coragem e a luta entre o bem e o mal.'),
(20, 'Scarface', 'Brian De Palma', 'Crime / Drama', '1983', 'O filme segue Tony Montana, um imigrante cubano que chega a Miami e rapidamente se envolve no mundo do tráfico de drogas. Ambicioso e implacável, Tony sobe na hierarquia do crime organizado, tornando-se um dos maiores traficantes da cidade. No entanto, seu desejo de poder e sucesso o leva a uma vida de excessos e violência, resultando em um eventual colapso de seu império. \"Scarface\" é conhecido por suas performances intensas, especialmente a de Al Pacino como Tony Montana, e por seu retrato visceral e estilizado do mundo do crime.'),
(21, 'Se7en', 'David Fincher', 'Crime / Drama / Suspense', '1995', 'O filme segue dois detetives, Somerset e Mills, enquanto investigam uma série de assassinatos brutais inspirados nos sete pecados capitais: gula, avareza, preguiça, luxúria, ira, inveja e orgulho. À medida que os detetives se aprofundam no caso, eles se deparam com um assassino meticuloso e enigmático que parece ter um plano maior em mente. O filme é conhecido por seu tom sombrio e atmosférico, e seu final impactante e perturbador.'),
(22, 'Whiplash', 'Damien Chazelle', 'Drama / Música', '2014', 'O filme segue Andrew Neyman, um jovem baterista de jazz que entra em uma prestigiada escola de música e se torna aluno de Fletcher, um maestro extremamente exigente e abusivo. Fletcher usa métodos intensos e muitas vezes cruéis para extrair o máximo desempenho de seus alunos. O filme explora a dinâmica complexa entre mestre e aprendiz, e as tensões e desafios que surgem quando a busca pela perfeição artística entra em conflito com o bem-estar pessoal. \"Whiplash\" é conhecido por suas performances intensas e pelo retrato visceral do mundo da música.'),
(23, 'Trabalhar Cansa', 'Juliana Rojas e Marco Dutra', 'Drama / Terror', '2011', 'O filme segue a história de Clara e Henrique, um jovem casal que está tentando melhorar sua vida ao abrir uma loja de roupas em São Paulo. Clara, no entanto, começa a enfrentar uma série de problemas físicos e psicológicos, enquanto a loja, que se torna cada vez mais opressiva e cheia de mistérios, começa a ter um impacto negativo em sua vida. \"Trabalhar Cansa\" combina elementos de drama e terror psicológico para explorar as pressões e os desafios do cotidiano e o impacto que isso pode ter sobre a saúde mental e física das pessoas.'),
(24, 'Titanic', 'James Cameron', 'Drama / Romance', '1997', 'O filme é uma narrativa épica que combina uma história de amor fictícia com o trágico naufrágio do RMS Titanic em 1912. Ele segue Jack Dawson, um artista pobre, e Rose DeWitt Bukater, uma jovem rica que está prestes a se casar com um homem poderoso e possessivo. Durante a viagem inaugural do Titanic, Jack e Rose se encontram e se apaixonam, enfrentando as rígidas convenções sociais e o desastre iminente. O filme é conhecido por suas impressionantes sequências de efeitos visuais, a recriação detalhada do navio e a icônica música tema \"My Heart Will Go On\", interpretada por Celine Dion.'),
(25, 'Homem-Aranha 2', 'Sam Raimi', 'Ação / Aventura / Super-herói', '2004', 'O filme é a sequência de \"Spider-Man\" (2002) e segue o jovem Peter Parker enquanto ele continua a lutar contra o crime como o Homem-Aranha. Peter enfrenta novos desafios tanto em sua vida pessoal quanto como super-herói. Ele lida com as dificuldades de manter sua identidade secreta e os problemas decorrentes da sua vida como estudante e amigo. O vilão principal é o Dr. Otto Octavius, também conhecido como Doutor Octopus, um cientista brilhante com tentáculos mecânicos que se torna um perigoso adversário após um experimento fracassado. O filme é aclamado por sua profundidade emocional, a evolução dos personagens e as cenas de ação inovadoras.'),
(26, 'Ratatouille', 'Brad Bird', 'Animação / Aventura / Comédia', '2007', 'O filme segue Remy, um rato com um paladar refinado e um sonho de se tornar um grande chef de cozinha. Apesar das dificuldades de ser um rato na cidade de Paris, ele se infiltra na cozinha do restaurante mais renomado da cidade e forma uma parceria improvável com Linguini, um jovem lavador de pratos que não sabe cozinhar. Juntos, eles criam pratos excepcionais e enfrentam desafios enquanto Remy tenta esconder seu segredo e alcançar seu sonho de ser um chef. \"Ratatouille\" é conhecido por sua animação vibrante, história cativante e mensagem inspiradora sobre seguir seus sonhos, independentemente das adversidades.'),
(27, 'WALL-E', 'Andrew Stanton', 'Animação / Aventura / Ficção Científica', '2008', 'O filme se passa em um futuro distante onde a Terra foi abandonada e está coberta de lixo, devido à poluição e ao consumismo desenfreado. WALL-E é um robô de coleta de lixo que passa seus dias compactando resíduos e sonhando com algo mais. Sua rotina muda quando ele encontra EVE, uma robô de busca enviada à Terra para encontrar sinais de vida. WALL-E se apaixona por EVE e a acompanha em uma jornada para salvar a humanidade e reverter os danos ao planeta. O filme é elogiado por seu visual impressionante, sua mensagem ecológica e sua abordagem inovadora da narrativa.'),
(28, 'A Viagem de Chihiro', 'Hayao Miyazaki', 'Animação / Fantasia / Aventura', '2001', 'O filme segue Chihiro, uma menina de 10 anos que, ao se mudar para uma nova cidade com seus pais, encontra um mundo mágico e misterioso após se perder em um parque temático abandonado. Seus pais são transformados em porcos por uma bruxa chamada Yubaba, e Chihiro deve trabalhar em uma casa de banhos mágica para encontrar uma forma de salvá-los e retornar ao mundo real. Ao longo da jornada, ela encontra uma variedade de criaturas fantásticas e enfrenta desafios que a ajudam a crescer e amadurecer. \"A Viagem de Chihiro\" é aclamado por sua rica animação, narrativa envolvente e profundidade emocional.'),
(29, 'Ponyo', 'Hayao Miyazaki', 'Animação / Fantasia / Aventura', '2008', 'O filme segue a história de Ponyo, uma pequena princesa do mar que deseja viver como uma humana após conhecer e fazer amizade com um garoto chamado Sosuke. Quando Ponyo se transforma em uma menina humana, ela provoca um desequilíbrio nos oceanos e no mundo natural. A história explora a luta de Ponyo e Sosuke para restaurar a ordem e equilibrar os mundos aquático e terrestre, enquanto enfrentam desafios e descobrem o poder do amor e da amizade. \"Ponyo\" é conhecido por sua animação vibrante e seu apelo encantador tanto para crianças quanto para adultos.');

-- --------------------------------------------------------

--
-- Estrutura para tabela `ingressos`
--

CREATE TABLE `ingressos` (
  `ID Ingresso` int(11) NOT NULL,
  `Nome da Pessoa` text NOT NULL,
  `Telefone` text NOT NULL,
  `ID Sessao` int(11) NOT NULL,
  `ID Filme` int(11) NOT NULL,
  `Valor` double NOT NULL,
  `Produtos Comprados` text NOT NULL
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
(24, 'Amendoin', 3, '500g');

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
(2, 'Leandro', '123', 'Leandro Rosa');

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
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de tabela `ingressos`
--
ALTER TABLE `ingressos`
  MODIFY `ID Ingresso` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `produtos_cinema`
--
ALTER TABLE `produtos_cinema`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de tabela `sessoes`
--
ALTER TABLE `sessoes`
  MODIFY `ID Sessao` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
