package venda.maluca.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import venda.maluca.dao.FormaPagamentoDAO;
import venda.maluca.model.FormaPagamento;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class JFrameFormaPagamento extends JFrame {


	private static final long serialVersionUID = 4724556616779824673L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private final Action action = new SwingActionAdicionar();


	public JFrameFormaPagamento() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFormaDePagamento = new JLabel("Forma de Pagamento");
		lblFormaDePagamento.setBounds(53, 31, 108, 14);
		contentPane.add(lblFormaDePagamento);
		
		textField = new JTextField();
		textField.setBounds(53, 52, 235, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setAction(action);
		btnAdicionar.setBounds(298, 51, 89, 23);
		contentPane.add(btnAdicionar);
		
		table = new JTable();
		table.setBounds(10, 83, 414, 167);
		contentPane.add(table);
		CarregarDadosGrid(0);
	}
	
	public void CarregarDadosGrid(Integer codigo){
		FormaPagamentoDAO formaPagamento = new 	FormaPagamentoDAO();			
		DefaultTableModel dados = new DefaultTableModel();
		dados.setColumnIdentifiers(new Object[]{"Forma Pagamento"});
		for(FormaPagamento fp : formaPagamento.listar(codigo))
			dados.addRow(new String[] {fp.toString()});
		table.setModel(dados);
		
		
	}
	

	private class SwingActionAdicionar extends AbstractAction {

		private static final long serialVersionUID = 7154742507656873885L;
		public SwingActionAdicionar() {
			putValue(NAME, "Adicionar");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		
			if (! textField.getText().isEmpty())
				new FormaPagamentoDAO().adicionar(new FormaPagamento(textField.getText()));
			else
				JOptionPane.showMessageDialog(null, "Atenção !! Necessario informar uma descrição");
			CarregarDadosGrid(0);	
		}
	}
}
