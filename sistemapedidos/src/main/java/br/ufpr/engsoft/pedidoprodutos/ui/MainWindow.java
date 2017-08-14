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
	private JPanel panelProduto;
	private JTable table;
	
	
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
						
			
		
		
		//createClientePanel();
		
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
		inputId.setBounds(86, 49, 86, 20);
		panelProduto.add(inputId);
		inputId.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setBounds(20, 86, 78, 14);
		panelProduto.add(lblDescrio);
		
		JTextField inputDescr = new JTextField();
		inputDescr.setBounds(86, 80, 234, 20);
		panelProduto.add(inputDescr);
		inputDescr.setColumns(10);
		
		panelProduto.setVisible(true);
		
		contentPane.revalidate();
		contentPane.repaint();
	}

	private void createClientePanel() {
		panelCliente = new JPanel();
		
		panelCliente.setLayout(null);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(68, 47, 32, 16);
		panelCliente.add(lblCpf);
		
		JTextField inputCPF = new JTextField();
		inputCPF.setBounds(104, 44, 215, 22);
		panelCliente.add(inputCPF);
		inputCPF.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(62, 79, 40, 16);
		panelCliente.add(lblNome);
		
		JTextField inputNomecliente = new JTextField();
		inputNomecliente.setBounds(104, 76, 116, 22);
		panelCliente.add(inputNomecliente);
		inputNomecliente.setColumns(10);
		System.out.println("XXXXXXXXXXXXxxxxxxxx");
		contentPane.add(panelCliente, "name_9242687062646");
		
		JLabel lblSobreNome = new JLabel("Sobre Nome:");
		lblSobreNome.setBounds(20, 114, 80, 16);
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
		
		JTable table_2 = new JTable();
		scrollPane.setColumnHeaderView(table_2);
		
		panelCliente.setVisible(true);
		
		contentPane.revalidate();
		contentPane.repaint();
	}
}
