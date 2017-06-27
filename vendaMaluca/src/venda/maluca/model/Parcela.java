package venda.maluca.model;

public class Parcela {
	
	private Long codigo;
	private Venda venda;
	private Double valor;
	private Boolean paga;
	
	public Parcela() {
		this.paga = false;
	}
	
	public Parcela(Venda venda, Double valor) {
		this.venda = venda;
		this.valor = valor;
		this.paga = false;
	}
	
	public Venda getVenda(){
		return this.venda;
	}
	
	public void setVenda(Venda v){
		this.venda = v;
	}
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Boolean getPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}
}
