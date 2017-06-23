package venda.maluca.view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import venda.maluca.dao.ProdutoDAO;
import venda.maluca.dao.TipoDAO;
import venda.maluca.model.Produto;
import venda.maluca.model.TipoProduto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTable;

public class JFrameProduto extends JFrame {

	
	

	private static final long serialVersionUID = 7403916341035215503L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldValor;
	private JTextField textFieldEstoque;
	private JTable table;
	private JTextField textFieldBuscar;
	private JComboBox<TipoProduto> comboBoxTipoProduto;
	

	private void CarregarTipoProduto(){
		TipoDAO tipo = new TipoDAO();
		for(TipoProduto tp : tipo.listar(0))
			comboBoxTipoProduto.addItem(tp);
			
	}

	private Boolean ValidarCampoVazio(){
		if ( textFieldNome.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Atenção!! O campo Nome não pode ser vazio");
			textFieldNome.setFocusable(true);
			return false;
		}
		else if ( textFieldValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Atenção!! O campo Valor não pode ser vazio");
			textFieldValor.setFocusable(true);
			return false;
		}
		else if ( textFieldEstoque.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Atenção!! O campo Estoque não pode ser vazio");
			textFieldEstoque.setFocusable(true);
			return false;
		}
		else return true;
	}
	
	private void limparCampos(){
		textFieldEstoque.setText("");
		textFieldValor.setText("");
		textFieldNome.setText("");
	}
	

	public void CarregarDadosGrid(ArrayList<Produto> list){
		Object[] colunas = new Object[] {"Produto", "Estoque", "Preço"};
		Object[][] dados = new Object[list.size()][3];
		
		for(int i=0; i< list.size(); i++){
			dados[i][0] = list.get(i).getNome();
			dados[i][1] = list.get(i).getEstoque().toString();
			dados[i][2] = list.get(i).getValor().toString();
		}
		
		table.setModel(new DefaultTableModel(dados, colunas));

		
	}
	
	public JFrameProduto() {
		
		setTitle("Cadastro de Produtos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(28, 11, 69, 14);
		contentPane.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(26, 30, 369, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldValor = new JTextField();
		textFieldValor.setBounds(180, 74, 109, 20);
		contentPane.add(textFieldValor);
		textFieldValor.setColumns(10);
		
		textFieldEstoque = new JTextField();
		textFieldEstoque.setBounds(299, 74, 96, 20);
		contentPane.add(textFieldEstoque);
		textFieldEstoque.setColumns(10);
		
		comboBoxTipoProduto = new JComboBox<TipoProduto>();
		comboBoxTipoProduto.setBounds(28, 74, 142, 20);
		contentPane.add(comboBoxTipoProduto);
		
		JLabel lblTipoProduto = new JLabel("Tipo Produto");
		lblTipoProduto.setBounds(28, 59, 142, 14);
		contentPane.add(lblTipoProduto);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(180, 61, 109, 14);
		contentPane.add(lblValor);
		
		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setBounds(299, 61, 96, 14);
		contentPane.add(lblEstoque);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(28, 105, 89, 23);
		btnSalvar.addActionListener(new ActionSalvar());
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(125, 105, 89, 23);
		btnCancelar.addActionListener(new ActionCancelar());
		contentPane.add(btnCancelar);
		
		table = new JTable();
		table.setBounds(10, 184, 406, 156);
		contentPane.add(table);
		
		textFieldBuscar = new JTextField();
		textFieldBuscar.setBounds(10, 159, 327, 20);
		contentPane.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(347, 158, 69, 23);
		btnBuscar.addActionListener(new ActionBuscar());
		contentPane.add(btnBuscar);
		CarregarTipoProduto();
		CarregarDadosGrid((ArrayList<Produto>) new ProdutoDAO().listar(0));
	}
	
	private class ActionSalvar extends AbstractAction {

		private static final long serialVersionUID = 424657095365056563L;

		@Override
		public void actionPerformed(ActionEvent e) {
		   if (ValidarCampoVazio()){
			   
		   
			  ProdutoDAO produto = new ProdutoDAO();
			  produto.adicionar(new Produto(textFieldNome.getText(), 
					   								  Double.parseDouble(textFieldValor.getText()),
					   								  Double.parseDouble(textFieldEstoque.getText()),
					   								  (TipoProduto)comboBoxTipoProduto.getSelectedItem()
					                      ));
			   CarregarDadosGrid((ArrayList<Produto>)produto.listar(0));
		}
			
		}
		
	}
	
	private class ActionCancelar extends AbstractAction {

		private static final long serialVersionUID = 8859940798320141084L;

		@Override
		public void actionPerformed(ActionEvent e) {
			limparCampos();
		}
		
	}
	
	private class ActionBuscar extends AbstractAction {
		
		private static final long serialVersionUID = -2318800225016632387L;

		@Override
		public void actionPerformed(ActionEvent e) {
			CarregarDadosGrid((ArrayList<Produto>)new ProdutoDAO().listar(textFieldBuscar.getText()));
		}
		
	}
}
