package CineMais.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CineMais.model.CatalogoFilmes;
import CineMais.view.TelaAdm;
import CineMais.view.TelaGerirSessoesAtivas;
import CineMais.view.TelaGestaoCardapio;
import CineMais.view.TelaGestaoUsuarios;
import CineMais.view.TelaInicial;

public class AdmController {

    private TelaAdm telaAdm;

    public AdmController(TelaAdm telaAdm) {
        this.telaAdm = telaAdm;
        initializeListeners();
    }

    private void initializeListeners() {
        telaAdm.getSessoesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSessoesScreen();
            }
        });

        telaAdm.getCatalogoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCatalogoScreen();
            }
        });

        telaAdm.getCardapioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCardapioScreen();
            }
        });

        telaAdm.getSenhaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGestaoUsuariosScreen();
            }
        });

        telaAdm.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToTelaInicial();
            }
        });
    }

    private void openSessoesScreen() {
        TelaGerirSessoesAtivas telaGerirSessoesAtivas = new TelaGerirSessoesAtivas();
        telaAdm.setVisible(false); // Oculta a tela atual
        telaGerirSessoesAtivas.setVisible(true); // Exibe a nova tela
    }

    private void openCatalogoScreen() {
        CatalogoFilmes catalogoFilmes = new CatalogoFilmes();
        telaAdm.setVisible(false); // Oculta a tela atual
        catalogoFilmes.setVisible(true); // Exibe a nova tela
    }

    private void openCardapioScreen() {
        TelaGestaoCardapio telaGestaoCardapio = new TelaGestaoCardapio();
        telaAdm.setVisible(false); // Oculta a tela atual
        telaGestaoCardapio.setVisible(true); // Exibe a nova tela
    }

    private void openGestaoUsuariosScreen() {
        TelaGestaoUsuarios telaGestaoUsuarios = new TelaGestaoUsuarios();
        telaAdm.setVisible(false); // Oculta a tela atual
        telaGestaoUsuarios.setVisible(true); // Exibe a nova tela
    }

    private void goBackToTelaInicial() {
        TelaInicial telaInicial = new TelaInicial();
        telaAdm.setVisible(false); // Oculta a tela atual
        telaInicial.setVisible(true); // Exibe a tela inicial
    }
}
