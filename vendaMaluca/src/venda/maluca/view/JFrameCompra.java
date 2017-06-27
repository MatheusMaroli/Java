package venda.maluca.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import venda.maluca.dao.ProdutoDAO;
import venda.maluca.model.Carrinho;
import venda.maluca.model.CarrinhoProduto;
import venda.maluca.model.Produto;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class JFrameCompra extends JFrame {

	private static final long serialVersionUID = 2293749262896739888L;
	private JLabel lblQuantidade;
	private JLabel lblEstoque;
	private JPanel contentPane;
	private JLabel lblValorProduto;
	private JLabel lblLabelTotal;
	private JTable table;
	private JTextField textFieldQtdade;
	private JComboBox<Produto> comboBoxProduto = new JComboBox<Produto>();
	
	private Carrinho carrinho;

	public JFrameCompra() {
		setTitle("Compra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 535, 376);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		lblLabelTotal = new JLabel("0");
		lblLabelTotal.setBounds(332, 33, 144, 25);
		lblLabelTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(lblLabelTotal);
		
		JButton btnIniciarCompra = new JButton("Iniciar Compra");
		btnIniciarCompra.setBounds(10, 11, 151, 23);
		btnIniciarCompra.addActionListener(new ActionIniciarCompra());
		getContentPane().add(btnIniciarCompra);
		
		table = new JTable();
		table.setBounds(331, 96, 188, 230);
		getContentPane().add(table);
		
		comboBoxProduto = new JComboBox<Produto>();
		comboBoxProduto.setBounds(10, 83, 311, 20);
		comboBoxProduto.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				AtualizarTela();
			}
		});
		getContentPane().add(comboBoxProduto);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(10, 58, 46, 14);
		getContentPane().add(lblProduto);
		
		textFieldQtdade = new JTextField();
		textFieldQtdade.setBounds(10, 135, 86, 20);
		getContentPane().add(textFieldQtdade);
		textFieldQtdade.setColumns(10);
		
		lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(10, 114, 86, 14);
		getContentPane().add(lblQuantidade);
		
		JLabel lblValor = new JLabel("Valor Produto");
		lblValor.setBounds(106, 114, 86, 14);
		getContentPane().add(lblValor);
		
		lblValorProduto = new JLabel("");
		lblValorProduto.setBounds(106, 138, 95, 14);
		lblValorProduto.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblValorProduto);
		
		JButton btnAdiconar = new JButton("Adicionar");
		btnAdiconar.setBounds(10, 177, 89, 23);
		btnAdiconar.addActionListener( new ActionAdicionar());
		getContentPane().add(btnAdiconar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(103, 177, 89, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.setBounds(180, 11, 142, 23);
		btnFinalizarCompra.addActionListener(new ActionFinalizarCompra());
		getContentPane().add(btnFinalizarCompra);
		
		JLabel lblValorTotalCampra = new JLabel("Valor Total Campra");
		lblValorTotalCampra.setBounds(332, 15, 144, 14);
		getContentPane().add(lblValorTotalCampra);
		
		JLabel lblEstoque1 = new JLabel("Estoque");
		lblEstoque1.setBounds(202, 114, 70, 14);
		getContentPane().add(lblEstoque1);
		
		lblEstoque = new JLabel("");
		lblEstoque.setBounds(202, 138, 119, 14);
		lblEstoque.setFont(new Font("Tahoma", Font.BOLD, 15));
		getContentPane().add(lblEstoque);
		
		contentPane.setLayout(null);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(430, 69, 89, 23);
		btnRemover.addActionListener(new ActionRemoverProduto());
		contentPane.add(btnRemover);
		this.carregarProdutoaComboBox();
	}
	
	public void carregarDadosGrid(){		
		DefaultTableModel dados = new DefaultTableModel();
		dados.setColumnIdentifiers(new Object[]{"Produto"});
		
		for(CarrinhoProduto p : carrinho.getProdutos() )
			dados.addRow(new String[] {p.getProduto().toString()});
		table.setModel(dados);
	}
	
	
	private void AtualizarTela(){
		Produto p = (Produto)comboBoxProduto.getSelectedItem();
		lblEstoque.setText(p.getEstoque().toString());
		lblValorProduto.setText(p.getValor().toString());
		
	}
	
	private void carregarProdutoaComboBox(){
		List<Produto> produtos = new ProdutoDAO().listar(0);
		for(Produto p : produtos)
			comboBoxProduto.addItem(p);
	}
	
	private Boolean validarCompraIniciada(){
		if (carrinho == null){
			JOptionPane.showMessageDialog(null, "Atenção!! Necessario Iniciar a Compra");
			return false;
		}
		return true;
			
	}
	
	private Boolean validacoes(Produto p){
		if (!validarCompraIniciada())
			return false;
		if (!validaEstoque(p))
			return false;
		if(!validarQuantidadeAdicionar(p))
			return false;
		
		return true;
	}
	
	private Boolean validarQuantidadeAdicionar(Produto p){
		if (textFieldQtdade.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Anteção !!! Informe a quantidade");
			return false;
		}
		if (Integer.parseInt(textFieldQtdade.getText()) <= 0){
			JOptionPane.showMessageDialog(null, "Anteção !!! Quantidade deve ser maior que 0");
			return false;
		}
		
		if (Integer.parseInt(textFieldQtdade.getText()) > p.getEstoque()){
			JOptionPane.showMessageDialog(null, "Anteção !!! Quantidade informada é maior que quantidade em estoque");
			return false;
		}
		return true;	
		
	}
	
	private Boolean validaEstoque(Produto p){
		if (! p.temEstoque()){
			JOptionPane.showMessageDialog(null, "Atenção!! Produto Não Possui Estoque");
			return false;
		}else return true;
	}
	
	private class ActionAdicionar extends AbstractAction{
	
		private static final long serialVersionUID = 8446526967094195759L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Produto produto = (Produto)comboBoxProduto.getSelectedItem();
			if (validacoes(produto)){
				carrinho.adicionar(new CarrinhoProduto(carrinho, 
									Integer.parseInt(textFieldQtdade.getText()),
									(Integer.parseInt(textFieldQtdade.getText()) * produto.getValor()),
									produto
						));
				produto.baixarEstoque(Integer.parseInt(textFieldQtdade.getText()));
				atualizarValorTotalCompra();
				carregarDadosGrid();
			}

		}
	}
	
	private void atualizarValorTotalCompra(){
		lblLabelTotal.setText(carrinho.getTotal().toString());
	}
	
	private class ActionFinalizarCompra extends AbstractAction {
		private static final long serialVersionUID = -8494750134482801283L;

		@Override
		public void actionPerformed(ActionEvent e) {
			carrinho.salvar(carrinho);
			new JFrameVenda(carrinho).setVisible(true);

		}
	}
	
	private class ActionIniciarCompra extends AbstractAction{

		private static final long serialVersionUID = -8502783226388140243L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (carrinho != null){
				JOptionPane.showMessageDialog(null, "Compra Ja foi Iniciada. Finalize a compra antes de iniciar outra.");
				return;
			}
			carrinho = new Carrinho();
			carrinho.setUsuario(JFramePrincipal.getUsuarioLogado());

		}
	}
	
	private class ActionRemoverProduto extends AbstractAction{

		private static final long serialVersionUID = 8517006705499310372L;
		@Override
		public void actionPerformed(ActionEvent e) {
			carrinho.getProdutos().remove(comboBoxProduto.getSelectedIndex());
			comboBoxProduto.remove(comboBoxProduto.getSelectedIndex());
			atualizarValorTotalCompra();


		}
	}
	
	
}
