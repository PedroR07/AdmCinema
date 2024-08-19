package CineMais.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CineMais.view.TelaConsultaIngresso;
import CineMais.view.TelaFilmesEmCartaz;
import CineMais.view.TelaInicial;
import CineMais.view.TelaUser;

public class UserController {

    private TelaUser telaUser;

    public UserController(TelaUser telaUser) {
        this.telaUser = telaUser;
        initController();
    }

    private void initController() {
        telaUser.getBtnVerFilmes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaFilmesEmCartaz();
            }
        });

        telaUser.getBtnTicket().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaConsultaIngresso();
            }
        });

        telaUser.getBtnVoltar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTelaInicial();
            }
        });
    }

    private void mostrarTelaFilmesEmCartaz() {
        TelaFilmesEmCartaz telaFilmesEmCartaz = new TelaFilmesEmCartaz();
        telaUser.setVisible(false);
        telaFilmesEmCartaz.setVisible(true);
    }

    private void mostrarTelaConsultaIngresso() {
        TelaConsultaIngresso telaConsultaIngresso = new TelaConsultaIngresso();
        telaUser.setVisible(false);
        telaConsultaIngresso.setVisible(true);
    }

    private void voltarParaTelaInicial() {
        TelaInicial telaInicial = new TelaInicial();
        telaUser.setVisible(false);
        telaInicial.setVisible(true);
    }
}
