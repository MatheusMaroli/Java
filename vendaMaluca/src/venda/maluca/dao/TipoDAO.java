package venda.maluca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import venda.maluca.model.TipoProduto;;

public class TipoDAO extends Persistencia {
	public void adicionar(TipoProduto tipo){
		this.conect();
		this.execSql("insert into tipos (descricao) values('"+tipo.getDescricao()+"')");
		this.disconect();
	}
	
	public Vector<TipoProduto> listar(Integer codigo){
		Vector<TipoProduto> listaTodos = new Vector<TipoProduto>();
		TipoProduto tipos;
		ResultSet resultado = null;
		try{
			conect();
			resultado = query("select idTipo, descricao from tipos where idTipo = "+codigo.toString()+" or "+codigo.toString()+" = '0'");		
			
			while(resultado.next()){
				tipos = new TipoProduto();
				tipos.setCodigo(resultado.getLong("idTipo"));
				tipos.setDescricao(resultado.getString("descricao"));
				
				listaTodos.add(tipos);
			}
		}catch (SQLException erro) {
			System.out.println("Erro ao obter os dados do registro\n" + erro.getMessage());
		} finally {
			disconect();
		}
		return listaTodos;
	}


}
