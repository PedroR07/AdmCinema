package CineMais.controller;

import CineMais.view.TelaError;

public class ErrorController {

    private TelaError telaError;

    public ErrorController() {
        // Inicializa a tela de erro
        telaError = new TelaError();
    }

    /**
     * Exibe a tela de erro.
     */
    public void exibirTelaError() {
        telaError.setVisible(true);
    }

    /**
     * Oculta a tela de erro.
     */
    public void ocultarTelaError() {
        telaError.setVisible(false);
    }

    /**
     * Configura a mensagem de erro na tela.
     *
     * @param titulo   O título da mensagem de erro.
     * @param mensagem A mensagem de erro.
     */
    public void configurarMensagem(String titulo, String mensagem) {
        telaError.setTitulo(titulo);
        telaError.setMensagem(mensagem);
    }

    // Main para testar o controller
    public static void main(String[] args) {
        // Cria uma instância do controlador de erro e exibe a tela de erro
        ErrorController controller = new ErrorController();
        controller.configurarMensagem("Senha/Usuário Incorreto(s)", "Tente novamente.");
        controller.exibirTelaError();
    }
}
