package br.ufpr.engsoft.pedidoprodutos.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.ufpr.engsoft.pedidoprodutos.ui.table.ClienteTableModel;
import br.ufpr.engsoft.pedidoprodutos.ui.table.ProdutoTableModel;


public class PanelBuscaClienteUI extends JPanel {
	
	
	
	public PanelBuscaClienteUI(PanelPedido panelPedido) {
		
		setLayout(null);
		
		ClienteTableModel model = new ClienteTableModel();
		model.listarClientes();
		
		JTable table = new JTable(model);
		table.setColumnSelectionAllowed(true);
		table.setBounds(104, 284, 1, 1);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	panelPedido.inputCPFCliente.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
	        	panelPedido.lblNomeCliente.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
	        	close();
	        }
	    });
		
		JScrollPane scrollPane2 = new JScrollPane(table);
		scrollPane2.setBounds(12, 49, 581, 221);
		add(scrollPane2);
		
		JLabel lblListagemDeProdutos = new JLabel("Listagem de Clientes");
		lblListagemDeProdutos.setBounds(13, 24, 146, 16);
		add(lblListagemDeProdutos);
		
	}
	
	private void close() {
		this.setVisible(false);
		((JDialog)this.getRootPane().getParent()).dispose();
		
		
	}
}
