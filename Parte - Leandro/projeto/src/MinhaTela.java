import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MinhaTela extends JFrame {

    public MinhaTela() {
        // Configurações básicas da janela
        setTitle("Minha Tela com Imagens-Botão");
        setSize(600, 400); // Ajustando o tamanho para comportar várias imagens lado a lado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando um JPanel com FlowLayout para organizar as imagens-botoes lado a lado
        JPanel painelImagens = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Alinhamento à esquerda com espaçamento de 10 pixels

        // Adicionando as imagens-botoes ao painel
        adicionarBotaoImagem(painelImagens, "/imagens/debi.jpg", 180, 320, "Tela 1");
        adicionarBotaoImagem(painelImagens, "/imagens/esparta.jpg", 180, 320, "Tela 2");
        // Adicione quantas imagens-botoes você precisar aqui...

        // Adicionando o painel de imagens-botoes à janela
        add(painelImagens);
    }

    private void adicionarBotaoImagem(JPanel painel, String caminhoImagem, int largura, int altura, String telaDestino) {
        // Carregando a imagem
        URL imagemUrl = getClass().getResource(caminhoImagem);
        if (imagemUrl != null) {
            ImageIcon imagemIcon = new ImageIcon(imagemUrl);
            Image imagemRedimensionada = imagemIcon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            ImageIcon imagemRedimensionadaIcon = new ImageIcon(imagemRedimensionada);

            // Criando um JButton com a imagem como ícone
            JButton botaoImagem = new JButton(imagemRedimensionadaIcon);

            // Configurando ação para o botão
            botaoImagem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Aqui você pode implementar o redirecionamento para outra tela
                    JOptionPane.showMessageDialog(MinhaTela.this, "Você clicou na imagem-botão para " + telaDestino);
                    // Exemplo de redirecionamento para outra janela:
                    // dispose(); // Fecha a janela atual
                    // new OutraTela().setVisible(true); // Abre a nova tela
                }
            });

            // Adicionando o botão ao painel
            painel.add(botaoImagem);
        } else {
            System.err.println("Imagem não encontrada: " + caminhoImagem);
        }
    }
}
