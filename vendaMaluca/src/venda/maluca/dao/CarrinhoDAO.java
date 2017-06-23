package venda.maluca.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.ResultSet;

import venda.maluca.model.Carrinho;
//import venda.maluca.model.Produto;

public class CarrinhoDAO extends Persistencia {

	public void adicionar(Carrinho c){
		
		this.conect();
		this.execSql(" insert into carrinhos (idUsuario, total) values (" +c.getUsuario().getCodigo().toString() +
																	       c.getTotal() +		") ");
		this.disconect();
	}
	

	public List<Carrinho> listar(Integer id){
		try
		{
			List<Carrinho> resultado = new ArrayList<Carrinho>();
			this.conect();
			ResultSet r = (ResultSet) this.query(" select idCarrinho, "
					                  + "idUsuario, "
					                  + "data "
					                  + "from carrinhos "
					                  + "where idCarrinho = " + id.toString() + " or " + id.toString() + " = 0 ");
			while (r.next()){
				Carrinho c = new Carrinho();
				c.setCodigo(r.getLong("idCarrinho"));
				c.setData(r.getDate("data"));
				c.setUsuario(new UsuarioDAO().buscar(r.getInt("idUsuario")));
				c.setProdutos(new CarrinhoProdutoDAO().listar(r.getInt("idCarrinho")));
			}
			return resultado;
		}catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Erro ao Carrega Carrinho de Compras " + e.toString());
			return null;
		}
		finally {
			this.disconect();
		}
	}
	
	public Carrinho buscar(Integer codigo){
		return listar(codigo).get(0);
	}
	
}
