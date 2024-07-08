import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


class PainelImagem extends JPanel {
    private ImageIcon imagem;
    private int x, y;

    public PainelImagem(ImageIcon imagemIcon) {
        imagem = imagemIcon;
        x = 0; // Posição padrão
        y = 0; // Posição padrão
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        imagem.paintIcon(this, g, x, y);
    }

    public void setPosicaoImagem(int posX, int posY) {
        x = posX;
        y = posY;
        repaint(); // Atualiza o painel para redesenhar a imagem na nova posição
    }
}
