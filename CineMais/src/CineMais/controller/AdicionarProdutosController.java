package CineMais.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import CineMais.model.ConexaoDB;

public class AdicionarProdutosController {

    private int idIngresso;

    public AdicionarProdutosController(int idIngresso) {
        this.idIngresso = idIngresso;
    }

    public void loadProducts(DefaultTableModel model) {
        try (Connection con = ConexaoDB.getConnection()) {
            String sql = "SELECT nome, valor, volume FROM produtos_cinema";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                double valor = rs.getDouble("valor");
                String volume = rs.getString("volume");

                String valorFormatado = String.format("R$ %.2f", valor);

                model.addRow(new Object[]{nome, volume, valorFormatado});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarProduto(String nomeProduto, double precoProduto, int quantidade, double valorTotal, String produtoTexto) {
        try (Connection con = ConexaoDB.getConnection()) {
            // Atualizar o valor do ingresso
            String sqlAtualizarValor = "UPDATE ingressos SET Valor = Valor + ? WHERE `ID Ingresso` = ?";
            PreparedStatement stmtValor = con.prepareStatement(sqlAtualizarValor);
            stmtValor.setDouble(1, valorTotal);
            stmtValor.setInt(2, idIngresso);
            stmtValor.executeUpdate();
    
            // Buscar os produtos comprados existentes
            String sqlAdicionarProduto = "SELECT `produtos comprados` FROM ingressos WHERE `ID Ingresso` = ?";
            PreparedStatement stmtSelect = con.prepareStatement(sqlAdicionarProduto);
            stmtSelect.setInt(1, idIngresso);
            ResultSet rs = stmtSelect.executeQuery();
            String produtosComprados = "";
            if (rs.next()) {
                produtosComprados = rs.getString("produtos comprados");
                if (produtosComprados != null && !produtosComprados.isEmpty()) {
                    // Adicionar o nome do produto repetidamente conforme a quantidade
                    StringBuilder sb = new StringBuilder(produtosComprados);
                    for (int i = 0; i < quantidade; i++) {
                        sb.append("\n\n").append(produtoTexto);
                    }
                    produtosComprados = sb.toString();
                } else {
                    // Criar uma nova string com o nome do produto repetidamente conforme a quantidade
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < quantidade; i++) {
                        sb.append(produtoTexto).append("\n\n");
                    }
                    produtosComprados = sb.toString().trim();
                }
            }
    
            // Atualizar o campo de produtos comprados no banco de dados
            String sqlAtualizarProdutos = "UPDATE ingressos SET `produtos comprados` = ? WHERE `ID Ingresso` = ?";
            PreparedStatement stmtProdutos = con.prepareStatement(sqlAtualizarProdutos);
            stmtProdutos.setString(1, produtosComprados);
            stmtProdutos.setInt(2, idIngresso);
            stmtProdutos.executeUpdate();
    
            JOptionPane.showMessageDialog(null, "Produto adicionado ao ingresso com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
