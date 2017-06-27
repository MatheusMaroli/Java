package venda.maluca.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import venda.maluca.dao.FormaPagamentoDAO;
import venda.maluca.model.Carrinho;
import venda.maluca.model.CarrinhoProduto;
import venda.maluca.model.FormaPagamento;
import venda.maluca.model.Produto;
import venda.maluca.model.Venda;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class JFrameVenda extends JFrame {

	private static final long serialVersionUID = 3104427484281872081L;
	private JPanel contentPane;
	private JComboBox<FormaPagamento> comboBoxFormaPagamento;
	private JComboBox<Integer> comboBoxParcela;
	private JLabel lblValorParcela;
	private JTable tableItens;
	private JLabel lblValorDesconto;
	private JLabel lblValorTotal;
	
	private Venda venda;

	private void carregarFormaPagamento(){
		List<FormaPagamento> forma =  new FormaPagamentoDAO().listar(0);
		for(FormaPagamento f : forma)
			comboBoxFormaPagamento.addItem(f);
	}
	
	private void carregarParcela(){
		for(Integer i=1; i<=12; i++)
			comboBoxParcela.addItem(i);
	}
	
	private void atualizaValorParcela(){
		this.venda.setQuantidadeParcela(comboBoxParcela.getItemAt(comboBoxParcela.getSelectedIndex()));
		lblValorParcela.setText(this.venda.calculaValorParcela().toString());
	}
	
	public JFrameVenda(Carrinho carrinho) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDescItens = new JLabel("Itens");
		lblDescItens.setBounds(217, 24, 46, 14);
		contentPane.add(lblDescItens);
		tableItens = new JTable();
		tableItens.setBounds(215, 46, 208, 204);
		contentPane.add(tableItens);
		
		JButton btnFinalizar = new JButton("Comprar");
		btnFinalizar.setBounds(107, 204, 98, 23);
		btnFinalizar.addActionListener(new ActionComprar());
		contentPane.add(btnFinalizar);
		
		JLabel lbl = new JLabel("Valor Parcela");
		lbl.setBounds(10, 154, 170, 14);
		contentPane.add(lbl);
		
		lblValorParcela = new JLabel("New label");
		lblValorParcela.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblValorParcela.setBounds(10, 179, 170, 14);
		contentPane.add(lblValorParcela);
		
		JButton btnDesconto = new JButton("Desconto");
		btnDesconto.setBounds(10, 204, 89, 23);
		btnDesconto.addActionListener(new ActionDesconto());
		contentPane.add(btnDesconto);
		
		JLabel lblDesconto = new JLabel("Desconto");
		lblDesconto.setBounds(107, 154, 62, 14);
		contentPane.add(lblDesconto);
		
		lblValorDesconto = new JLabel("");
		lblValorDesconto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblValorDesconto.setBounds(107, 179, 73, 14);
		contentPane.add(lblValorDesconto);
		
		lblValorTotal = new JLabel("New label");
		lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValorTotal.setBounds(10, 11, 208, 14);
		contentPane.add(lblValorTotal);
		
		JLabel lblFormaPagamento = new JLabel("Forma Pagamento");
		lblFormaPagamento.setBounds(10, 46, 170, 14);
		contentPane.add(lblFormaPagamento);
		
		comboBoxFormaPagamento = new JComboBox<FormaPagamento>();
		comboBoxFormaPagamento.setBounds(10, 67, 170, 20);
		
		contentPane.add(comboBoxFormaPagamento);
		
		JLabel lblParcelas = new JLabel("Parcelas");
		lblParcelas.setBounds(10, 98, 46, 14);
		contentPane.add(lblParcelas);
		
		comboBoxParcela = new JComboBox<Integer>();
		comboBoxParcela.setBounds(10, 123, 170, 20);
		contentPane.add(comboBoxParcela);
		
		
		this.venda = new Venda();
		this.venda.setCarrinho(carrinho);
		this.venda.setValorTotal(carrinho.getTotal());
		this.venda.calcularDesconto();
		lblValorTotal.setText("Valor Total: R$"+ this.venda.getValorTotal().toString() );
		
		carregarFormaPagamento();
		CarregarDadosGrid();
		carregarParcela();	
		atualizaValorParcela();
		venda.setFormaPagamento((FormaPagamento) comboBoxFormaPagamento.getSelectedItem());
		comboBoxParcela.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				atualizaValorParcela();
			}
		});
		
		comboBoxFormaPagamento.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				venda.setFormaPagamento((FormaPagamento) comboBoxFormaPagamento.getSelectedItem());
				
			}
		});
		
		
	}
	
	private void CarregarDadosGrid(){
		DefaultTableModel dados = new DefaultTableModel();
		dados.setColumnIdentifiers(new Object[]{"Itens"});
		for(CarrinhoProduto p : venda.getCarrinho().getProdutos() )
			dados.addRow(new String[] {p.getProduto().getNome()});
		tableItens.setModel(dados);		
	}
	
	private class ActionComprar extends AbstractAction{

		private static final long serialVersionUID = -2873207186790886197L;

		@Override
		public void actionPerformed(ActionEvent e) {
			venda.finalizar();
		}


	}
	
	private class ActionDesconto extends AbstractAction{

		private static final long serialVersionUID = -1449759630959170000L;

		@Override
		public void actionPerformed(ActionEvent e) {
			lblValorDesconto.setText(venda.getDesconto().toString() + "%");
			lblValorTotal.setText("Valor Total: R$ "+ venda.getValorDesconto().toString() );
			lblValorParcela.setText(venda.calculaValorParcela().toString());
		}
	}
}

