package venda.maluca.dao;

import venda.maluca.model.Parcela;

public class ParcelaDAO extends Persistencia{

	public void adicionar(Parcela p){
		String pago;
		if ( p.getPaga())
			pago = "S";
		else pago = "N"; 
		this.conect();
		this.execSql(" insert into venda_parcelas (idVenda, valor, pago) " +
		             " values( "+ p.getVenda().getCodigo() + ", " + p.getValor() + ", '"+ pago +"' )");				
		this.disconect();
	}
	
}
