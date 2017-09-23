package br.ufpr.engsoft.pedidoprodutos.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.ufpr.engsoft.pedidoprodutos.Cliente;
import br.ufpr.engsoft.pedidoprodutos.ItemPedido;
import br.ufpr.engsoft.pedidoprodutos.Pedido;
import br.ufpr.engsoft.pedidoprodutos.Produto;
import br.ufpr.engsoft.pedidoprodutos.ui.table.ItemPedidoModel;

public class PanelPedido extends JPanel {
	JTextField inputCPFCliente;
	JTextField inputCodProduto;
	JLabel lblDescProduto;
	JLabel lblNomeCliente;
	ItemPedidoModel model;
	JTable table;
	JTextField inputQtd;
	
	public PanelPedido() {
		
		
		setLayout(null);
		
		JLabel lblCpf_1 = new JLabel("CPF:");
		lblCpf_1.setBounds(54, 33, 27, 16);
		add(lblCpf_1);
		
		inputCPFCliente = new JTextField();
		inputCPFCliente.setBounds(93, 27, 116, 22);
		inputCPFCliente.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent event) {
				if (inputCPFCliente.getText().length() >= 11 ) // limit textfield to 3 characters
					event.consume();
				
			}
			
		});

		add(inputCPFCliente);
		inputCPFCliente.setColumns(11);
		
		lblNomeCliente = new JLabel("");
		lblNomeCliente.setBounds(224, 29, 212, 21);
		add(lblNomeCliente);
		
		JLabel lblIdProduto = new JLabel("Id Produto:");
		lblIdProduto.setBounds(17, 62, 64, 16);
		add(lblIdProduto);
		
		inputCodProduto = new JTextField();
		inputCodProduto.setBounds(93, 56, 116, 22);
		inputCodProduto.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent event) {
				if (inputCodProduto.getText().length() >= 11 ) // limit textfield to 3 characters
					event.consume();
				
			}
			
		});
		add(inputCodProduto);
		inputCodProduto.setColumns(11);
		
		JButton btnBuscarProduto = new JButton("Buscar Produto");
		btnBuscarProduto.addActionListener(e -> { 
			
			JPanel panelBuscaProduto = new PanelBuscaProdutoUI(this);
			
			final JDialog frame = new JDialog((JFrame)getRootPane().getParent(), "Buscar Produtos", true);
			frame.setLayout(null);
			
			frame.setContentPane(panelBuscaProduto);
			frame.setBounds(110, 120, 619, 349);
			frame.setPreferredSize(new Dimension(619, 349));
			frame.pack();
			frame.setVisible(true);
			frame.revalidate();
			frame.repaint();
						
			panelBuscaProduto.repaint();
			
		});
		btnBuscarProduto.setBounds(441, 58, 127, 24);
		add(btnBuscarProduto);
		
		JButton btnBuscarCliente = new JButton("Buscar Cliente");
		btnBuscarCliente.setBounds(441, 24, 127, 25);
		btnBuscarCliente.addActionListener(e -> { 
		JPanel panelBuscaCliente = new PanelBuscaClienteUI(this);
		
		final JDialog frame = new JDialog((JFrame)getRootPane().getParent(), "Buscar Cliente", true);
			frame.setLayout(null);
			
			frame.setContentPane(panelBuscaCliente);
			frame.setBounds(110, 120, 619, 349);
			frame.setPreferredSize(new Dimension(619, 349));
			frame.pack();
			frame.setVisible(true);
			frame.revalidate();
			frame.repaint();
						
			panelBuscaCliente.repaint();
			
		});
		add(btnBuscarCliente);
		
		lblDescProduto = new JLabel("");
		lblDescProduto.setBounds(221, 62, 208, 16);
		add(lblDescProduto);
		
		model = new ItemPedidoModel();
		
		table = new JTable(model);
		table.setColumnSelectionAllowed(true);
		table.setBounds(104, 284, 1, 1);
		
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(12, 91, 79, 16);
		add(lblQuantidade);
		
		inputQtd = new JTextField();
		inputQtd.setBounds(94, 87, 85, 22);
		inputQtd.setColumns(11);
		inputQtd.addKeyListener(new KeyListener() {
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
				
				if (!press || inputQtd.getText().length() >= 11 ) // limit textfield to 3 characters
					event.consume();
				
			}
			
		});
		add(inputQtd);
				
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(94, 122, 79, 25);
		btnIncluir.addActionListener(e -> {
			if (checkValues() ) {
				ItemPedido item = new ItemPedido();
				item.setQuantidade(Integer.parseInt(inputQtd.getText()));
				item.setProduto(new Produto());
				item.getProduto().setId(Integer.parseInt(inputCodProduto.getText()));
				item.getProduto().setDescricao(lblDescProduto.getText());
				model.setData(item);
				inputQtd.setText("");
				inputCodProduto.setText("");
				lblDescProduto.setText("");
				refreshTable();
			}
		});
		
		add(btnIncluir);
		
		
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
//	        	inputId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
//	        	inputDescrProduto.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
//	        	lblId.setVisible(true);
//	        	inputId.setVisible(true);
	        }
	    });
		
		JScrollPane scrollPane2 = new JScrollPane(table);
		scrollPane2.setBounds(19, 152, 581, 146);
		add(scrollPane2);
		
		JButton btnSalvar_1 = new JButton("Salvar");
		btnSalvar_1.setBounds(502, 317, 97, 25);
		add(btnSalvar_1);
		
		JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(54, 320, 398, 16);
		add(errorLabel);
		
		btnSalvar_1.addActionListener(
				e -> {
					Pedido ped = new Pedido();
					ped.setCliente(new Cliente());
					ped.getCliente().setCpf(inputCPFCliente.getText());
					
					Date d = new Date();
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					ped.setData(format.format(d));
					ped.setItens(model.getData());
					try {
			
						ped.savePedido();
						model.getData().clear();
						errorLabel.setText("");
						inputQtd.setText("");
						inputCPFCliente.setText("");
						inputCodProduto.setText("");
						lblDescProduto.setText("");
						lblNomeCliente.setText("");
						refreshTable();
						JOptionPane.showMessageDialog(null, "Pedido gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception ex) {
						errorLabel.setText(ex.getMessage());
					}
				});
	}
	
	private void refreshTable() {
		table.repaint();
		table.revalidate();
	}
	
	private boolean checkValues() {
		
		if (inputCPFCliente.getText().trim().equals("") ||
				lblDescProduto.getText().trim().equals("") ||
			    inputQtd.getText().trim().equals("") || inputQtd.getText().trim().equals("0") ) {
			return false;
		}
		return true;
	}
}
