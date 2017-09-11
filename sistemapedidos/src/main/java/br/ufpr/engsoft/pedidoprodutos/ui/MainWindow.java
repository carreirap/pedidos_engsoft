package br.ufpr.engsoft.pedidoprodutos.ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.ufpr.engsoft.pedidoprodutos.Cliente;
import br.ufpr.engsoft.pedidoprodutos.Produto;
import br.ufpr.engsoft.pedidoprodutos.ui.table.ClienteTableModel;
import br.ufpr.engsoft.pedidoprodutos.ui.table.ProdutoTableModel;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JPanel panelCliente;
	private JPanel panelProduto;
	private PanelPedido panelPedido;
	
	
	
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
		
		//createClientePanel();
	
		createPanelPedido();
		
		//createPanelBuscaProduto();
	}
	
	private void removePanels() {
		if (panelCliente != null) { 
			contentPane.remove(panelCliente);
		}
		if (panelProduto != null) { 
			contentPane.remove(panelProduto); 
		}
		if (panelPedido != null) {
			contentPane.remove(panelProduto);
		}
		
	}
	
	private void createPanelPedido() {
		panelPedido = new PanelPedido();
		contentPane.add(panelPedido, "name_panelPedio");
		
		
	}

	private void createPanelProduto() {
		
		panelProduto = new JPanel();
		panelProduto.setBounds(10, 11, 593, 357);
		panelProduto.setLayout(null);
		contentPane.add(panelProduto);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(64, 52, 28, 14);
		lblId.setVisible(false);
		panelProduto.add(lblId);
		
		JLabel inputId = new JLabel();
		inputId.setBounds(100, 46, 86, 20);
		inputId.setVisible(false);
		panelProduto.add(inputId);
		//inputId.setColumns(10);
		
		JTextField inputDescrProduto = new JTextField();
		inputDescrProduto.setBounds(100, 79, 195, 22);
		panelProduto.add(inputDescrProduto);
		inputDescrProduto.setColumns(45);
		
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(22, 85, 69, 16);
		panelProduto.add(lblDescricao);
		
		ProdutoTableModel model = new ProdutoTableModel();
		model.listarProdutos();
		
		JTable table = new JTable(model);
		table.setColumnSelectionAllowed(true);
		table.setBounds(104, 284, 1, 1);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	inputId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
	        	inputDescrProduto.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
	        	lblId.setVisible(true);
	        	inputId.setVisible(true);
	        }
	    });
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(204, 158, 97, 25);
		btnSalvar.addActionListener(
				e -> {
					try {
						Produto pro = new Produto();
						pro.setDescricao(inputDescrProduto.getText().trim());
						
						if (!inputId.getText().equals("")) {
							pro.setId(Integer.parseInt(inputId.getText().trim()));
							pro.alterar();
						} else {
							pro.salvar();
						}
						model.listarProdutos();
						
						inputId.setText("");
						inputId.setVisible(false);
						lblId.setVisible(false);
						inputDescrProduto.setText("");
												
						table.repaint();
						table.revalidate();
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				});
		panelProduto.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(316, 158, 97, 25);
		btnExcluir.addActionListener(
				e -> {
					try {
						Produto prod = new Produto();
						prod.setId(Integer.parseInt(inputId.getText()));
						prod.deletar();
						model.listarProdutos();
						
						inputId.setText("");
						inputDescrProduto.setText("");
						inputId.setVisible(false);
						lblId.setVisible(false);
						table.repaint();
						table.revalidate();
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				});
		panelProduto.add(btnExcluir);
				
		JScrollPane scrollPane2 = new JScrollPane(table);
		scrollPane2.setBounds(22, 192, 581, 146);
		panelProduto.add(scrollPane2);
				
		contentPane.revalidate();
		contentPane.repaint();
	}

	private void createClientePanel() {
		panelCliente = new JPanel();
		
		panelCliente.setLayout(null);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(58, 114, 38, 16);
		panelCliente.add(lblCpf);
		
		JTextField inputCPF = new JTextField();
		inputCPF.setBounds(103, 111, 147, 22);
		panelCliente.add(inputCPF);
		inputCPF.setColumns(11);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(53, 47, 46, 16);
		panelCliente.add(lblNome);
		
		JTextField inputNomecliente = new JTextField();
		inputNomecliente.setBounds(104, 44, 116, 22);
		panelCliente.add(inputNomecliente);
		inputNomecliente.setColumns(30);
		contentPane.add(panelCliente, "name_9242687062646");
		
		JLabel lblSobreNome = new JLabel("Sobre Nome:");
		lblSobreNome.setBounds(12, 79, 85, 16);
		panelCliente.add(lblSobreNome);
		
		JTextField inputSobreNome = new JTextField();
		inputSobreNome.setBounds(103, 76, 199, 22);
		panelCliente.add(inputSobreNome);
		inputSobreNome.setColumns(50);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(74, 18, 20, 16);
		lblId.setVisible(false);
		panelCliente.add(lblId);
		
		
		JLabel lblIdCliente = new JLabel("");
		lblIdCliente.setBounds(106, 18, 56, 16);
		lblIdCliente.setVisible(false);
		panelCliente.add(lblIdCliente);
		
		ClienteTableModel model = new ClienteTableModel();
		model.listarClientes();
		
		JTable table = new JTable(model);
		table.setColumnSelectionAllowed(true);
		table.setBounds(104, 284, 1, 1);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            inputNomecliente.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
	            inputSobreNome.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				inputCPF.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				lblIdCliente.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				lblIdCliente.setVisible(true);
				lblId.setVisible(true);
	        }
	    });
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(204, 158, 97, 25);
		btnSalvar.addActionListener(
				e -> {
					try {
						Cliente cli = new Cliente();
						cli.setCpf(inputCPF.getText().trim());
						cli.setNome(inputNomecliente.getText().trim());
						cli.setSobreNome(inputSobreNome.getText().trim());
						
						if (!lblIdCliente.getText().equals("")) {
							cli.setId(Integer.parseInt(lblIdCliente.getText().trim()));
							cli.alterar();
						} else {
							cli.salvarCliente();
						}
						model.listarClientes();
						
						inputCPF.setText("");
						inputNomecliente.setText("");
						inputSobreNome.setText("");
						lblIdCliente.setText("");
												
						table.repaint();
						table.revalidate();
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				});
		panelCliente.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(316, 158, 97, 25);
		btnExcluir.addActionListener(
				e -> {
					try {
						Cliente cli = new Cliente();
						cli.setId(Integer.parseInt(lblIdCliente.getText()));
						cli.deletarCliente();
						model.listarClientes();
						
						inputCPF.setText("");
						inputNomecliente.setText("");
						inputSobreNome.setText("");
						lblIdCliente.setText("");
						lblIdCliente.setVisible(false);
						lblId.setVisible(false);
						table.repaint();
						table.revalidate();
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				});
		panelCliente.add(btnExcluir);
				
		JScrollPane scrollPane2 = new JScrollPane(table);
		scrollPane2.setBounds(22, 192, 581, 146);
		panelCliente.add(scrollPane2);
		
		panelCliente.setVisible(true);
		
		contentPane.revalidate();
		contentPane.repaint();
	}
}
