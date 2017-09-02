package br.ufpr.engsoft.pedidoprodutos.ui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufpr.engsoft.pedidoprodutos.Cliente;
import br.ufpr.engsoft.pedidoprodutos.ui.table.ClienteTableModel;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JPanel panelCliente;
	private JPanel panelProduto;
	private JTextField textField;
	
	
//	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 449);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		JMenuItem mntmCadastro = new JMenuItem("Cadastro");
		mntmCadastro.addActionListener(
			ev -> { removePanels(); createClientePanel(); }	
		);
		mnCliente.add(mntmCadastro);
		
		JMenu mnPedido = new JMenu("Pedido");
		menuBar.add(mnPedido);
		
		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar");
		mnPedido.add(mntmCadastrar);
		
		JMenu mnProduto = new JMenu("Produto");
		menuBar.add(mnProduto);
		
		JMenuItem mntmCadastrar_1 = new JMenuItem("Cadastrar");
		mnProduto.add(mntmCadastrar_1);
		mntmCadastrar_1.addActionListener(
				e -> {removePanels(); createPanelProduto();});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
						
			
		//createPanelProduto();
		
		createClientePanel();
		
	}
	
	private void removePanels() {
		if (panelCliente != null) { 
			contentPane.remove(panelCliente);
		}
		if (panelProduto != null) { 
			contentPane.remove(panelProduto); 
		}
		
	}

	private void createPanelProduto() {
		
		panelProduto = new JPanel();
		panelProduto.setBounds(10, 11, 593, 357);
		panelProduto.setLayout(null);
		contentPane.add(panelProduto);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(47, 52, 41, 14);
		panelProduto.add(lblNewLabel);
		
		JTextField inputId = new JTextField();
		inputId.setBounds(111, 49, 86, 20);
		panelProduto.add(inputId);
		inputId.setColumns(10);
		
		JTextField inputDescrProduto = new JTextField();
		inputDescrProduto.setBounds(110, 102, 116, 22);
		panelProduto.add(inputDescrProduto);
		inputDescrProduto.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(17, 105, 69, 16);
		panelProduto.add(lblDescricao);
				
		contentPane.revalidate();
		contentPane.repaint();
	}

	private void createClientePanel() {
		panelCliente = new JPanel();
		
		panelCliente.setLayout(null);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(53, 114, 38, 16);
		panelCliente.add(lblCpf);
		
		JTextField inputCPF = new JTextField();
		inputCPF.setBounds(103, 111, 147, 22);
		panelCliente.add(inputCPF);
		inputCPF.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(53, 47, 46, 16);
		panelCliente.add(lblNome);
		
		JTextField inputNomecliente = new JTextField();
		inputNomecliente.setBounds(104, 44, 116, 22);
		panelCliente.add(inputNomecliente);
		inputNomecliente.setColumns(10);
		System.out.println("XXXXXXXXXXXXxxxxxxxx");
		contentPane.add(panelCliente, "name_9242687062646");
		
		JLabel lblSobreNome = new JLabel("Sobre Nome:");
		lblSobreNome.setBounds(12, 79, 85, 16);
		panelCliente.add(lblSobreNome);
		
		JTextField inputSobreNome = new JTextField();
		inputSobreNome.setBounds(103, 76, 199, 22);
		panelCliente.add(inputSobreNome);
		inputSobreNome.setColumns(10);
		
		
		ClienteTableModel model = new ClienteTableModel();
		
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(204, 158, 97, 25);
		btnSalvar.addActionListener(
				e -> {
					try {
						Cliente cli = new Cliente();
						cli.setCpf(inputCPF.getText());
						cli.setNome(inputNomecliente.getText());
						cli.setSobreNome(inputSobreNome.getText());
						cli.salvarCliente();
						model.listarClientes();
					} catch (SQLException ex) {
						//message error when saving cliente
					}
				});
		panelCliente.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(316, 158, 97, 25);
		panelCliente.add(btnExcluir);
		
		
		
		JTable table = new JTable(model);
		table.setColumnSelectionAllowed(true);
		table.setBounds(104, 284, 1, 1);
				
		JScrollPane scrollPane2 = new JScrollPane(table);
		scrollPane2.setBounds(22, 192, 581, 146);
		panelCliente.add(scrollPane2);
		
		panelCliente.setVisible(true);
		
		contentPane.revalidate();
		contentPane.repaint();
	}
}
