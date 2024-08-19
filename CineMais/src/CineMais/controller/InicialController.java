package CineMais.controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CineMais.model.ConexaoDB;
import CineMais.view.TelaAdm;
import CineMais.view.TelaError;
import CineMais.view.TelaInicial;
import CineMais.view.TelaUser;

public class InicialController {

    private TelaInicial telaInicial;

    public InicialController(TelaInicial telaInicial) {
        this.telaInicial = telaInicial;
        initController();
    }

    private void initController() {
        telaInicial.getjButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaCliente();
            }
        });

        telaInicial.getjButton2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaLogon();
            }
        });

        telaInicial.getjButton3().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogon();
            }
        });

        telaInicial.getjButtonVoltar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTelaInicial();
            }
        });
    }

    private void mostrarTelaCliente() {
        TelaUser telaCliente = new TelaUser();
        telaCliente.setVisible(true);
        telaInicial.setVisible(false);
    }

    private void mostrarTelaLogon() {
        CardLayout cl = (CardLayout) telaInicial.getjPanel1().getLayout();
        cl.show(telaInicial.getjPanel1(), "card3");
    }

    private void realizarLogon() {
        String senha = new String(telaInicial.getjPasswordField1().getPassword());
        String user = telaInicial.getjTextPane1().getText();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexaoDB.getConnection();
            String sql = "SELECT * FROM users WHERE `Usuário` = ? AND `Senha` = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                TelaAdm telaAdm = new TelaAdm();
                telaAdm.setVisible(true);
                telaInicial.setVisible(false);
            } else {
                TelaError telaError = new TelaError();
                telaError.setVisible(true);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void voltarParaTelaInicial() {
        ((CardLayout) telaInicial.getjPanel1().getLayout()).show(telaInicial.getjPanel1(), "card2");
    }
}
