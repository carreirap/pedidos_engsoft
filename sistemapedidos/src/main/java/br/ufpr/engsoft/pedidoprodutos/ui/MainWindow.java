package br.ufpr.engsoft.pedidoprodutos.ui;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.ufpr.engsoft.pedidoprodutos.Cliente;
import br.ufpr.engsoft.pedidoprodutos.MyException;
import br.ufpr.engsoft.pedidoprodutos.Pedido;
import br.ufpr.engsoft.pedidoprodutos.Produto;
import br.ufpr.engsoft.pedidoprodutos.ui.table.ClienteTableModel;
import br.ufpr.engsoft.pedidoprodutos.ui.table.ItemPedidoModel;
import br.ufpr.engsoft.pedidoprodutos.ui.table.PedidoModel;
import br.ufpr.engsoft.pedidoprodutos.ui.table.ProdutoTableModel;
import br.ufpr.engsoft.pedidoprodutos.util.ValidaCPF;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JPanel panelCliente;
	private JPanel panelProduto;
	private PanelPedido panelPedido;
	private JPanel panelConsultaPedido;
	private JTextField textField;
	private JTextField textField_1;
	JComboBox<String> comboBoxCpfCliente;
	//private JTextField inputCPFCliente;
	
	
	
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
		mntmCadastrar.addActionListener(
				ev -> {removePanels(); createPanelPedido();}
				);
		
		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mnPedido.add(mntmConsultar);
		mntmConsultar.addActionListener(
				ev -> {removePanels(); createPanelConsultaPedido();}
				);
		
		
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
	
		//createPanelPedido();
		
		createPanelConsultaPedido();
		
		//createPanelBuscaProduto();
	}
	
	private void removePanels() {
		if (panelCliente != null) { 
			contentPane.remove(panelCliente);
			panelCliente = null;
		}
		if (panelProduto != null) { 
			contentPane.remove(panelProduto); 
			panelProduto = null;
		}
		if (panelPedido != null) {
			contentPane.remove(panelPedido);
			panelPedido = null;
		}
		
		if (panelConsultaPedido != null) {
			contentPane.remove(panelConsultaPedido);
			panelConsultaPedido = null;
		}
		
	}
	
	private void createPanelPedido() {
		panelPedido = new PanelPedido();
		contentPane.add(panelPedido, "name_panelPedio");
		
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	private void createPanelConsultaPedido() {
		panelConsultaPedido = new JPanel();
		contentPane.add(panelConsultaPedido, "name_panelConsultaPedio");
		panelConsultaPedido.setLayout(null);
		
		JLabel lblConsultaPedido = new JLabel("Consulta Pedido");
		lblConsultaPedido.setBounds(243, 5, 147, 16);
		panelConsultaPedido.add(lblConsultaPedido);
		
		JLabel lblCpf_1 = new JLabel("CPF:");
		lblCpf_1.setBounds(41, 47, 40, 16);
		panelConsultaPedido.add(lblCpf_1);
		
		JLabel lblNomeCliente = new JLabel("");
		lblNomeCliente.setBounds(243, 46, 228, 22);
		panelConsultaPedido.add(lblNomeCliente);
		
		Cliente cli = new Cliente();
		List<Cliente> lst = cli.listarClientes();
		comboBoxCpfCliente = new JComboBox<String>();
		comboBoxCpfCliente.addItem("Selecione CPF");
		for(Cliente c: lst)
			comboBoxCpfCliente.addItem(c.getCpf());
		comboBoxCpfCliente.setBounds(77, 44, 147, 22);
		comboBoxCpfCliente.addActionListener(new ActionListener() {
			 
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JComboBox<String> combo = (JComboBox<String>) event.getSource();
		        String selectedBook = (String) combo.getSelectedItem();
		        Cliente cli = new Cliente();
		        cli.setCpf(selectedBook);
		        try {
					cli.consultarCPF();
					lblNomeCliente.setText(cli.getNome() + " " + cli.getSobreNome());
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (MyException e) {
					e.printStackTrace();
				}
		    }
		});
		panelConsultaPedido.add(comboBoxCpfCliente);
		
		
//		inputCPFCliente = new JTextField();
//		inputCPFCliente.setBounds(93, 44, 168, 22);
//		inputCPFCliente.addKeyListener(new KeyListener() {
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//			}
//
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//			}
//
//			@Override
//			public void keyTyped(KeyEvent event) {
//				char key = event.getKeyChar();
//				boolean press = (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_DELETE || Character
//						.isDigit(key));
//				
//				if (!press || inputCPFCliente.getText().length() >= 11 ) // limit textfield to 3 characters
//					event.consume();
//				
//			}
//			
//		});
//
//		panelConsultaPedido.add(inputCPFCliente);
//		inputCPFCliente.setColumns(10);
		
		PedidoModel model = new PedidoModel();
		ItemPedidoModel modelItem = new ItemPedidoModel();
		
		JTable table = new JTable(model);
		table.setColumnSelectionAllowed(true);
		table.setBounds(104, 284, 1, 1);
		
		JTable tableItem = new JTable(modelItem);
		tableItem.setColumnSelectionAllowed(true);
		tableItem.setBounds(104, 284, 1, 1);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	Pedido pedido = new Pedido();
	        	pedido.setId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
	        	try {
					pedido.buscarPedidoCompleto();
					modelItem.setDatas(pedido.getItens());
					tableItem.repaint();
					tableItem.revalidate();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
	        }
	    });
		
		JScrollPane scrollPane2 = new JScrollPane(table);
		scrollPane2.setBounds(18, 82, 581, 118);
		panelConsultaPedido.add(scrollPane2);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(502, 43, 97, 25);
		btnConsultar.addActionListener(e -> {
			Pedido ped = new Pedido();
			try {
				System.out.println((String) comboBoxCpfCliente.getSelectedItem() + " " + comboBoxCpfCliente.getSelectedIndex());
				if (comboBoxCpfCliente.getSelectedIndex() == 0 ) 
					return;
				ped.setCliente(new Cliente());
				ped.getCliente().setCpf((String) comboBoxCpfCliente.getSelectedItem());
				List<Pedido> lstt = ped.consultaPedido();
				model.setDatas(lstt);
				table.repaint();
				table.revalidate();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//refreshTable();
		
		});
		panelConsultaPedido.add(btnConsultar);
		
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        }
	    });
		
		JScrollPane scrollPaneItem = new JScrollPane(tableItem);
		scrollPaneItem.setBounds(18, 235, 581, 118);
		panelConsultaPedido.add(scrollPaneItem);
		
		JLabel lblItensDoPedido = new JLabel("Itens do Pedido");
		lblItensDoPedido.setBounds(18, 213, 116, 16);
		panelConsultaPedido.add(lblItensDoPedido);
		
		
		
			/*****/
		
		contentPane.revalidate();
		contentPane.repaint();
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
		inputDescrProduto.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent event) {
				if (inputDescrProduto.getText().length() >= 45 ) // limit textfield to 3 characters
					event.consume();
			}
			
		});
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
						if (inputDescrProduto.getText().equals("")) {
							return;
						}
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
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				});
		panelProduto.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(316, 158, 97, 25);
		btnExcluir.addActionListener(
				e -> {
					try {
						if (inputId.getText().equals("") ) 
							return;
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
						if (ex.getMessage().contains("foreign key no action")) {
							JOptionPane.showMessageDialog(null, "Produto não pode ser excluído, existe uma associação com um ou mais Pedidos", "Erro", JOptionPane.ERROR_MESSAGE);
							inputId.setText("");
							inputId.setVisible(false);
							lblId.setVisible(false);
							inputDescrProduto.setText("");
						} else {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
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
		inputCPF.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent event) {
				char key = event.getKeyChar();
				boolean press = (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_DELETE || Character
						.isDigit(key));
				
				if (!press || inputCPF.getText().length() >= 11 ) // limit textfield to 3 characters
					event.consume();
			}
			
		});

		panelCliente.add(inputCPF);
		inputCPF.setColumns(11);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(53, 47, 46, 16);
		panelCliente.add(lblNome);
		
		JTextField inputNomecliente = new JTextField();
		inputNomecliente.setBounds(104, 44, 116, 22);
		inputNomecliente.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent event) {
				if (inputNomecliente.getText().length() >= 30 ) // limit textfield to 3 characters
					event.consume();
			}
			
		});

		panelCliente.add(inputNomecliente);
		inputNomecliente.setColumns(30);
		contentPane.add(panelCliente, "name_9242687062646");
		
		JLabel lblSobreNome = new JLabel("Sobre Nome:");
		lblSobreNome.setBounds(12, 79, 85, 16);
		panelCliente.add(lblSobreNome);
		
		JTextField inputSobreNome = new JTextField();
		inputSobreNome.setBounds(103, 76, 199, 22);
		inputSobreNome.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent event) {
				if (inputSobreNome.getText().length() >= 50 ) // limit textfield to 3 characters
					event.consume();
			}
			
		});
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
						
						if (ValidaCPF.isCPF(inputCPF.getText().trim())) {
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
						} else {
							JOptionPane.showMessageDialog(null, "CPF informado é invalido!", "Erro", JOptionPane.ERROR_MESSAGE);
							inputCPF.requestFocus();
						}
						
					} catch (SQLException ex) {
						if (ex.getMessage().contains("unique constraint")) {
							JOptionPane.showMessageDialog(null, "Cliente já cadastrado com o CPF informado!","Erro", JOptionPane.ERROR_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, ex.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
						}
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
						if (ex.getMessage().contains("foreign key no action")) {
							JOptionPane.showMessageDialog(null, "Cliente não pode ser excluído, existe uma associação com um ou mais Pedidos", "Erro", JOptionPane.ERROR_MESSAGE);
							inputCPF.setText("");
							inputNomecliente.setText("");
							inputSobreNome.setText("");
							lblIdCliente.setText("");
						} else {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
						}
						
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
