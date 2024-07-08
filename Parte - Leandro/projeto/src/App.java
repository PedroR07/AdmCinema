import javax.swing.SwingUtilities;

public class App {
   public static void main(String[] args) {
        // Criando e exibindo a janela
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MinhaTela().setVisible(true);
            }
        });
    }
}
