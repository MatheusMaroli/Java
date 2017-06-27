package venda.maluca.dao;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import venda.maluca.model.CarrinhoProduto;

public class CarrinhoProdutoDAO extends Persistencia {
	
	public void adicionar(CarrinhoProduto cp){
		this.conect();
		this.execSql(" insert into carrinho_produtos (idProduto, quantidade, valorTotal, idCarrinho) " +
					 "  values ("+ cp.getProduto().getCodigo() + ", " + cp.getQuantidade() + ", " + cp.getValorTotal()+
					            ", "+ cp.getCarrinho().getCodigo() +"  )"
					 );
		this.disconect();
	}
	
	public List<CarrinhoProduto> listar(Integer codCarrinho){
		try {
			List<CarrinhoProduto> resultado = new ArrayList<CarrinhoProduto>();
			this.conect();
			ResultSet r = this.query(" select "
						+ "idCarrinhoProduto, "
						+ "idProduto, "
						+ "quantidade, "
						+ "valorTotal"
						+ "from carrinho_produtos where idCarrinho = " + codCarrinho.toString() + " "
						);
			while (r.next()){
				
				CarrinhoProduto cp = new CarrinhoProduto();				
				cp.setCodigo(r.getLong("idCarrinhoProduto"));
				cp.setQuantidade(r.getInt("quantidade"));
				cp.setValorTotal(r.getDouble("valorTotal"));
				cp.setProduto(new ProdutoDAO().buscar(r.getInt("idProduto")));
				resultado.add(cp);
			}
			return resultado;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar Produto do Carrinho." + e.toString());
			return null;
		} finally{
			this.disconect();
		}
	}
	
	
}
