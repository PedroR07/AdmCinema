package CineMais.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import CineMais.controller.TelaGestaoCardapioController;

public class TelaGestaoCardapio extends JFrame {

    private JPanel mainPanel;
    private JButton btnAddItem;
    private JButton btnRemoveItem;
    private JButton btnUpdateItem;
    private JButton btnVoltar; 
    private JTable cardapioTable;
    private JScrollPane scrollPane;
    private JTextField txtItemName;
    private JTextField txtItemPrice;
    private JTextField txtItemSize;
    private DefaultTableModel tableModel;

    private TelaGestaoCardapioController controller;

    public TelaGestaoCardapio() {
        controller = new TelaGestaoCardapioController();
        initComponents();
        controller.loadCardapio(tableModel);
        setTitle("Editor do Cardápio");
    }

    private void initComponents() {
        setTitle("Gestão de Cardápio");
        setSize(700, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));

        tableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Valor", "Volume"}, 0);
        cardapioTable = new JTable(tableModel);
        cardapioTable.setFont(new Font("Arial", Font.PLAIN, 14));
        cardapioTable.setRowHeight(25);
        scrollPane = new JScrollPane(cardapioTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 1, 10, 10));

        btnAddItem = new JButton("Adicionar Item");
        btnRemoveItem = new JButton("Remover Item");
        btnUpdateItem = new JButton("Alterar Item");
        btnVoltar = new JButton("Voltar");

        styleAddButton(btnAddItem);
        styleButton(btnRemoveItem);
        styleUpdateButton(btnUpdateItem);
        styleBackButton(btnVoltar);

        buttonsPanel.add(btnAddItem);
        buttonsPanel.add(btnRemoveItem);
        buttonsPanel.add(btnUpdateItem);
        buttonsPanel.add(btnVoltar);

        mainPanel.add(buttonsPanel, BorderLayout.EAST);

        JPanel itemDetailsPanel = new JPanel();
        itemDetailsPanel.setLayout(new GridLayout(3, 2, 10, 10));
        itemDetailsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblName = new JLabel("Nome:");
        JLabel lblPrice = new JLabel("Valor:");
        JLabel lblSize = new JLabel("Volume:");

        txtItemName = new JTextField();
        txtItemPrice = new JTextField();
        txtItemSize = new JTextField();

        styleLabel(lblName);
        styleLabel(lblPrice);
        styleLabel(lblSize);

        itemDetailsPanel.add(lblName);
        itemDetailsPanel.add(txtItemName);
        itemDetailsPanel.add(lblPrice);
        itemDetailsPanel.add(txtItemPrice);
        itemDetailsPanel.add(lblSize);
        itemDetailsPanel.add(txtItemSize);

        mainPanel.add(itemDetailsPanel, BorderLayout.NORTH);

        add(mainPanel);

        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addItem(txtItemName.getText(), txtItemPrice.getText(), txtItemSize.getText(), tableModel);
            }
        });

        btnRemoveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = cardapioTable.getSelectedRow();
                if (selectedRow != -1) {
                    controller.removeItem(selectedRow, tableModel);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um item para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnUpdateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = cardapioTable.getSelectedRow();
                if (selectedRow != -1) {
                    controller.updateItem(selectedRow, txtItemName.getText(), txtItemPrice.getText(), txtItemSize.getText(), tableModel);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um item para alterar.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TelaAdm().setVisible(true);
            }
        });
    }

    private void styleAddButton(JButton button) {
        button.setBackground(new Color(40, 167, 69));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void styleUpdateButton(JButton button) {
        button.setBackground(new Color(255, 193, 7));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void styleBackButton(JButton button) {
        button.setBackground(new Color(220, 53, 69));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 16));
    }
}
