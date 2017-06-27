package venda.maluca.dao;

import venda.maluca.model.Venda;

public class VendaDAO extends Persistencia {
	
	public void adicionar(Venda v){
		this.conect();
		this.execSql(" insert into vendas(desconto, valorTotal, idFormaPagamento, idCarrinho) values "+
					 " (" + v.getDesconto() +", "+ v.getValorTotal() +", "+ v.getFormaPagamento().getCodigo() +", " +
					 		v.getCarrinho().getCodigo()
					 		+ " )"	);
		this.disconect();
	}
	
	
	
	
	
}
