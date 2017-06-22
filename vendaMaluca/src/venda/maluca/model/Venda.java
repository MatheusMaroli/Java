package venda.maluca.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {
	
	private Long codigo;
	private Date data;
	private Double desconto;
	private FormaPagamento formaPagamento;
	private List<Parcela> parcelas = new ArrayList<>();		
	
	public Venda()
	{
		this.data = new Date();
		this.parcelas = new ArrayList<Parcela>();
		this.formaPagamento = new FormaPagamento();
	}

	public Long getCodigo() {
		return codigo;
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

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
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
}
