package CineMais;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class TelaAddFilme extends JFrame {

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextArea jTextAreaSinopse;
    private JButton jButtonOK;
    private JButton jButtonEscolherArquivo;
    private JButton jButtonVoltar;
    private JFileChooser jFileChooser;
    private File arquivoSelecionado;

    public TelaAddFilme() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 500));

        jLabel1 = new JLabel("Adicionar Filme ao Catálogo:");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 18));

        jLabel2 = new JLabel("Nome:");
        jTextField1 = new JTextField(20);

        jLabel3 = new JLabel("Diretor:");
        jTextField2 = new JTextField(20);

        jLabel4 = new JLabel("Ano de Lançamento:");
        jTextField3 = new JTextField(20);

        jLabel5 = new JLabel("Gênero:");
        jTextField4 = new JTextField(20);

        jLabel6 = new JLabel("Sinopse:");
        jTextAreaSinopse = new JTextArea(5, 20);
        jTextAreaSinopse.setLineWrap(true);
        jTextAreaSinopse.setWrapStyleWord(true);
        JScrollPane scrollPaneSinopse = new JScrollPane(jTextAreaSinopse);

        jLabel7 = new JLabel("Selecione o Poster:");

        jButtonOK = new JButton("OK");
        jButtonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarFilme();
            }
        });

        jButtonEscolherArquivo = new JButton("Escolher Arquivo");
        jFileChooser = new JFileChooser();
        jButtonEscolherArquivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = jFileChooser.showOpenDialog(TelaAddFilme.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    arquivoSelecionado = jFileChooser.getSelectedFile();
                }
            }
        });

        jButtonVoltar = new JButton("Voltar");
        jButtonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retorna para a tela anterior (por exemplo, a tela principal)
                CatalogoFilmes telaCat = new CatalogoFilmes(); // Substitua por sua tela anterior
                TelaAddFilme.this.setVisible(false);
                telaCat.setVisible(true);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(scrollPaneSinopse)
                            .addComponent(jButtonEscolherArquivo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonOK, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonVoltar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(scrollPaneSinopse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jButtonEscolherArquivo))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonOK)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonVoltar)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void adicionarFilme() {
        String nome = jTextField1.getText();
        String diretor = jTextField2.getText();
        String ano = jTextField3.getText();
        String genero = jTextField4.getText();
        String sinopse = jTextAreaSinopse.getText();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinemais", "root", "");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO filmografia (Nome, Diretor, Ano, Genero, Sinopse) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nome);
            stmt.setString(2, diretor);
            stmt.setString(3, ano);
            stmt.setString(4, genero);
            stmt.setString(5, sinopse);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        copiarImagemParaDiretorio(id);
                        JOptionPane.showMessageDialog(this, "Filme adicionado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Falha ao obter o ID do filme.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar filme ao banco de dados: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void copiarImagemParaDiretorio(int id) {
        if (arquivoSelecionado != null) {
            File destino = new File("imagens/", id + getFileExtension(arquivoSelecionado));

            try {
                Files.copy(arquivoSelecionado.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(this, "Imagem copiada e renomeada com sucesso!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao copiar a imagem: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum arquivo selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaAddFilme().setVisible(true));
    }
}
