package venda.maluca.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import venda.maluca.dao.CarrinhoDAO;

public class Carrinho {
	
	private Long codigo;
	private Date data;
	private Usuario usuario;
	private List<CarrinhoProduto> produtos = new ArrayList<>();
	
	public Carrinho() {
		this.data = new Date();
		this.produtos = new ArrayList<CarrinhoProduto>();
	}

	public Long getCodigo() {
		return codigo;
	}

	public Usuario getUsuario(){
		return this.usuario;
	}
	
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	
	public void setProdutos(List<CarrinhoProduto> c){
		this.produtos = c;
	}
	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public List<CarrinhoProduto> getProdutos(){
		return this.produtos;
	}
	
	public Double getTotal() {
		Double total = new Double(0);
		for(CarrinhoProduto cp : produtos)
			total = total + cp.getValorTotal();
		return total;
	}
	
	public void adicionar(CarrinhoProduto produto) {
		this.produtos.add(produto);
	}
	
	public void remover(CarrinhoProduto produto) {
		this.produtos.remove(produto);
	}
	
	public void alterar(CarrinhoProduto produto, int posicao) {
		this.produtos.set(posicao, produto);
	}
	
	
	public void salvar(Carrinho c){
		CarrinhoDAO carrinhoDao = new CarrinhoDAO();
		carrinhoDao.adicionar(c);
		this.setCodigo(carrinhoDao.getUltimoCodigo("idCarrinho", "carrinhos"));		
		
		
		new CarrinhoProduto().salvar(c.produtos);
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}