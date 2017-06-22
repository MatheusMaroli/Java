package venda.maluca.model;


public class Produto {
	
	private Long codigo;
	private String nome;
	private Double valor;
	private Double estoque;
	private TipoProduto tipo;
	
	public Produto(String nome, Double valor, Double estoque, TipoProduto tipo) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.estoque = estoque;
		this.tipo = tipo;
	}

	public Produto() {
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getEstoque() {
		return estoque;
	}

	public void setEstoque(Double estoque) {
		this.estoque = estoque;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}	
	
	public String toString(){
		return this.getNome();
	}
}
