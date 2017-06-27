package venda.maluca.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import venda.maluca.model.TipoProduto;
import venda.maluca.dao.TipoDAO;

public class JFrameTipoProduto extends JFrame {

	private static final long serialVersionUID = 5867179696122988165L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	


	public void CarregarDadosGrid(Integer tipo){
		TipoDAO tipoProduto = new TipoDAO();				
		DefaultTableModel dados = new DefaultTableModel();
		dados.setColumnIdentifiers(new Object[]{"Tipo Produto"});
		for(TipoProduto tp : tipoProduto.listar(tipo))
			dados.addRow(new String[] {tp.toString()});
		table.setModel(dados);
	}
	
	public JFrameTipoProduto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(272, 34, 89, 23);
		contentPane.add(btnAdicionar);
		
		textField = new JTextField();
		textField.setBounds(70, 37, 196, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTipoProduto = new JLabel("Tipo Produto");
		lblTipoProduto.setBounds(70, 21, 192, 14);
		contentPane.add(lblTipoProduto);
		
		table = new JTable();
		table.setBounds(10, 68, 414, 182);
		contentPane.add(table);
		
		btnAdicionar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TipoDAO().adicionar(new TipoProduto(textField.getText()));
				CarregarDadosGrid(0);
			}
		});
		
		this.CarregarDadosGrid(0);
	}
}
