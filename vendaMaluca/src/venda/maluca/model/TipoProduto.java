package venda.maluca.model;

public class TipoProduto {
	
	private Long codigo;
	private String descricao;
	
	public TipoProduto () {
		
	}
	
	public TipoProduto (String descricao) {
		this.setDescricao(descricao);
	}
	
	
	public TipoProduto (Long codigo, String descricao) {
		this.setCodigo(codigo);
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

	@Override
	public String toString() {
		return this.getDescricao();
	}
	
	
}
