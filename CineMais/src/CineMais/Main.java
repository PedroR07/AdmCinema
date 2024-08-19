package CineMais;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import CineMais.controller.ColetorLixoIngressos;
import CineMais.view.TelaInicial;

public class Main {
    public static void main(String[] args) {
        try {
            // Define o Look and Feel Nimbus
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace(); // Tratar a exceção
        }
        
        // Cria e exibe a tela inicial
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        ColetorLixoIngressos.executarColeta();
    }
}
