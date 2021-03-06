package venda.maluca.model;

import java.util.List;

import javax.swing.JOptionPane;

import venda.maluca.dao.CarrinhoProdutoDAO;
import venda.maluca.dao.ProdutoDAO;

public class CarrinhoProduto {
	private Long codigo;
	private Carrinho carrinho;
	private Integer quantidade;
	private Double valorTotal;
	private Produto produto;
	
	public Long getCodigo() {
		return codigo;
	}
	

	public CarrinhoProduto() {
		// TODO Auto-generated constructor stub
	}
	
	

	public CarrinhoProduto(Carrinho carrinho, Integer quantidade, Double valorTotal, Produto produto) {
		super();
		this.carrinho = carrinho;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
		this.produto = produto;
	}


	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public void salvar(List<CarrinhoProduto> produtos){
		CarrinhoProdutoDAO persistencia = new CarrinhoProdutoDAO();
		ProdutoDAO produtoPercistencia = new ProdutoDAO();
		for(CarrinhoProduto p : produtos)
		{
			persistencia.adicionar(p);
			produtoPercistencia.atualizar(p.getProduto());
		}
			
	}
}
