package venda.maluca.model;


public class FormaPagamento {
	
	private Long codigo;
	private String descricao;
	
	public FormaPagamento() {
		//nada faz
	}
	
	public String toString(){
		return this.getDescricao();
	}
	
	public FormaPagamento(String descricao) {
	
		this.descricao = descricao;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}		

}
