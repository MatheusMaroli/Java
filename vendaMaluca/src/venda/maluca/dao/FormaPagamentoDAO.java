package venda.maluca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import venda.maluca.model.FormaPagamento;

public class FormaPagamentoDAO extends Persistencia {

	public void adicionar(FormaPagamento f){
		this.conect();
		this.execSql("insert into formas_pagamento (forma) values('"+f.getDescricao()+"')");
		this.disconect();
	}
	
	public List<FormaPagamento> listar(Integer codigo){
		List<FormaPagamento> listaTodos = new ArrayList<FormaPagamento>();
		ResultSet resultado = null;
		try{
			conect();
			resultado = query("select idFormaPagamento, forma from formas_pagamento where idFormaPagamento = "+codigo.toString()+" or "+codigo.toString()+" = '0'");		
			
			while(resultado.next()){
				FormaPagamento forma = new FormaPagamento();
				forma.setCodigo(resultado.getLong("idFormaPagamento"));
				forma.setDescricao(resultado.getString("forma"));
				
				listaTodos.add(forma);
			}
		}catch (SQLException erro) {
			System.out.println("Erro ao obter os dados do registro\n" + erro.getMessage());
		} finally {
			disconect();
		}
		return listaTodos;		
	}
	
	
}
