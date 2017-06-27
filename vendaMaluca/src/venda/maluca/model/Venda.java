package venda.maluca.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import venda.maluca.dao.ParcelaDAO;
import venda.maluca.dao.VendaDAO;

public class Venda {
	
	private Long codigo;
	private Date data;
	private Double desconto;
	private Double valorTotal;
	private FormaPagamento formaPagamento;
	private Integer quantidadeParcela;
	private Carrinho carrinho;
	private List<Parcela> parcelas = new ArrayList<Parcela>();		
	
	public Venda()
	{
		this.quantidadeParcela = 0;
		this.valorTotal = 0.0;
		this.desconto = 0.0;
		this.data = new Date();
		
		this.parcelas = new ArrayList<Parcela>();
		this.formaPagamento = new FormaPagamento();
	}

	public Long getCodigo() {
		return this.codigo;
	}
	
	public void setQuantidadeParcela(Integer i){
		this.quantidadeParcela = i;
	}
	
	public Double calculaValorParcela(){
		return this.getValorDesconto() / this.quantidadeParcela;
	}
	
	public void setValorTotal(Double total){
		this.valorTotal = total;
	}
	
	public Double getValorTotal(){
		return this.valorTotal;
		
	}
	
	public Double getValorDesconto(){
		if(this.desconto == 0)
			return this.valorTotal;
		else
			return this.valorTotal - (this.valorTotal * this.desconto);	
	}
	
	
	public Carrinho getCarrinho(){
		return this.carrinho;
	}
	
	public void setCarrinho(Carrinho c){
		this.carrinho = c;
	}
	
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getDesconto() {
		return desconto;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}
	
	public void adicionar(Parcela parcela) {
		this.parcelas.add(parcela);
	}
	
	public void remover(Parcela parcela) {
		this.parcelas.remove(parcela);
	}
	
	public void alterar(Parcela parcela, int posicao) {
		this.parcelas.set(posicao, parcela);
	}

	public void calcularDesconto() {
		if (this.valorTotal > 1000)
			this.desconto = 0.1;
		else if(this.valorTotal > 7000 && this.valorTotal < 10000)
			this.desconto = 0.2;
		else
		if (this.valorTotal > 10000)
			this.desconto = 0.25;
		else this.desconto = 0.0;
	}
	
	private void parcelar(){
		for (int i=0; i<this.quantidadeParcela; i++){
			parcelas.add(new Parcela(this, this.calculaValorParcela()));
		}
	}
	
	private void salvar(){
		VendaDAO vendaDao = new VendaDAO();
		vendaDao.adicionar(this);
		this.setCodigo(vendaDao.getUltimoCodigo("idVenda", "vendas"));
		
	}
	
	public void salvarParcelas(){
		ParcelaDAO parcelaDao = new ParcelaDAO();
		for(Parcela p : parcelas)
		{
			parcelaDao.adicionar(p);
		}
	}
	public void finalizar() {
		this.parcelar();
		this.salvar();
		this.salvarParcelas();
		
	}
	
	
	

}
