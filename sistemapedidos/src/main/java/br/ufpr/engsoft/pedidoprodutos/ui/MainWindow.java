package br.ufpr.engsoft.pedidoprodutos.ui;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufpr.engsoft.pedidoprodutos.ui.table.ClienteTableModel;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JPanel panelCliente;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
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
			ev -> { createClientePanel(); }	
		);
		mnCliente.add(mntmCadastro);
		
		JMenu mnPedido = new JMenu("Pedido");
		menuBar.add(mnPedido);
		
		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar");
		mnPedido.add(mntmCadastrar);
		
		JMenu mnProduto = new JMenu("Produto");
		menuBar.add(mnProduto);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		createClientePanel();
		
	}

	private void createClientePanel() {
		panelCliente = new JPanel();
		
		panelCliente.setLayout(null);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(66, 47, 40, 16);
		panelCliente.add(lblCpf);
		
		JTextField inputCPF = new JTextField();
		inputCPF.setBounds(104, 44, 215, 22);
		panelCliente.add(inputCPF);
		inputCPF.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(54, 79, 40, 16);
		panelCliente.add(lblNome);
		
		JTextField inputNomecliente = new JTextField();
		inputNomecliente.setBounds(104, 76, 116, 22);
		panelCliente.add(inputNomecliente);
		inputNomecliente.setColumns(10);
		System.out.println("XXXXXXXXXXXXxxxxxxxx");
		contentPane.add(panelCliente, "name_9242687062646");
		
		JLabel lblSobreNome = new JLabel("Sobre Nome:");
		lblSobreNome.setBounds(12, 114, 79, 16);
		panelCliente.add(lblSobreNome);
		
		JTextField inputSobreNome = new JTextField();
		inputSobreNome.setBounds(104, 111, 199, 22);
		panelCliente.add(inputSobreNome);
		inputSobreNome.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(204, 158, 97, 25);
		panelCliente.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(316, 158, 97, 25);
		panelCliente.add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 212, 547, 122);
		panelCliente.add(scrollPane);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setViewportView(scrollBar);
		
		table_2 = new JTable();
		scrollPane.setColumnHeaderView(table_2);
		
		
				
		
		panelCliente.setVisible(true);
		
		contentPane.revalidate();
		contentPane.repaint();
	}
}
