package venda.maluca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import venda.maluca.model.Produto;
import venda.maluca.model.TipoProduto;

public class ProdutoDAO extends Persistencia {
	public void adicionar(Produto p){
		this.conect();
		this.execSql(" insert into produtos (nome, valor, estoque, idTipo) "
				   + " values ('" + p.getNome() +"',"+p.getValor()+", " +p.getEstoque() + "," + p.getTipo().getCodigo()+" )"
				);
		this.disconect();
	}
	
	public void atualizar(Produto p){
		this.conect();
		this.execSql(" update produtos set valor = " + p.getValor() + ", estoque = " + p.getEstoque() +
					 " where idproduto = " + p.getCodigo() );
	}
	
	public List<Produto> listar (Integer Codigo){
		try
		{
			List<Produto> listaTodos = new ArrayList<Produto>();
			this.conect();
			ResultSet resultado = this.query(" select pr.idProduto, "
					+ "pr.nome, "
					+ "pr.valor, "
					+ "pr.estoque, "
					+ "pr.idTipo, "
					+ "t.descricao "
					+ " from produtos pr"
					+ " join tipos t on pr.idTipo = t.idTipo"
					+ " where idProduto = " + Codigo.toString() + " or " +  Codigo.toString() + " = 0" );
			
			while (resultado.next()){
				Produto p = new Produto();
				p.setCodigo(resultado.getLong("idProduto"));
				p.setNome(resultado.getString("nome"));
				p.setValor(resultado.getDouble("valor"));
				p.setEstoque(resultado.getDouble("estoque"));
				TipoProduto tipo = new TipoProduto(resultado.getLong("idTipo"), resultado.getString("descricao"));
				p.setTipo(tipo);	
				listaTodos.add(p);
			
			}
			return listaTodos;
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Erro ao Carregar Produto " + e.toString());
			return null;
		}finally{

			this.disconect();
		}
	}
	
	public List<Produto> listar (String filtro){
		try
		{
			List<Produto> listaTodos = new ArrayList<Produto>();
			this.conect();
			ResultSet resultado = this.query(" select pr.idProduto, "
					+ "pr.nome, "
					+ "pr.valor, "
					+ "pr.estoque, "
					+ "pr.idTipo, "
					+ "t.descricao "
					+ " from produtos pr"
					+ " join tipos t on pr.idTipo = t.idTipo"
					+ " where pr.nome like '%" +  filtro.toString() + "%' " );
			
			while (resultado.next()){
				Produto p = new Produto();
				p.setCodigo(resultado.getLong("idProduto"));
				p.setNome(resultado.getString("nome"));
				p.setValor(resultado.getDouble("valor"));
				p.setEstoque(resultado.getDouble("estoque"));
				TipoProduto tipo = new TipoProduto(resultado.getLong("idTipo"), resultado.getString("descricao"));
				p.setTipo(tipo);	
				listaTodos.add(p);
			
			}
			return listaTodos;
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Erro ao Carregar Produto " + e.toString());
			return null;
		}finally{

			this.disconect();
		}
	}
	
	
	public Produto buscar (Integer codigo) {
		return listar(codigo).get(0);
	}
	
}
