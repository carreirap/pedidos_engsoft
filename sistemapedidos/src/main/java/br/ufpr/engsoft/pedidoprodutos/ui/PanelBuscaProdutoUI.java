package br.ufpr.engsoft.pedidoprodutos.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.ufpr.engsoft.pedidoprodutos.ui.table.ProdutoTableModel;


public class PanelBuscaProdutoUI extends JPanel {
	
	
	
	public PanelBuscaProdutoUI(PanelPedido panelPedido) {
		
		//contentPane.add(panelBuscaProduto, "buscaProduto");
		setLayout(null);
		
		ProdutoTableModel model = new ProdutoTableModel();
		model.listarProdutos();
		
		JTable table = new JTable(model);
		table.setColumnSelectionAllowed(true);
		table.setBounds(104, 284, 1, 1);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	panelPedido.inputCodProduto.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
	        	panelPedido.lblDescProduto.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
//	        	inputId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
//	        	inputDescrProduto.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
//	        	lblId.setVisible(true);
//	        	inputId.setVisible(true);
	        	close();
	        }
	    });
		
		
		
		JScrollPane scrollPane2 = new JScrollPane(table);
		scrollPane2.setBounds(12, 49, 581, 221);
		add(scrollPane2);
		
		JLabel lblListagemDeProdutos = new JLabel("Listagem de Produtos");
		lblListagemDeProdutos.setBounds(13, 24, 146, 16);
		add(lblListagemDeProdutos);
		
	}
	
	private void close() {
		this.setVisible(false);
		((JDialog)this.getRootPane().getParent()).dispose();
		
		
	}
}
