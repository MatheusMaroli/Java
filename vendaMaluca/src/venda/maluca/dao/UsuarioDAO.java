package venda.maluca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import venda.maluca.model.Usuario;

public class UsuarioDAO extends Persistencia{
	
	public void adicionar(Usuario u){
		this.conect();
		this.execSql(" insert into usuarios (nome, email, login, senha) "
				+ " values('"+ u.getNome() +"', '" + u.getEmail() + "', '" + u.getLogin()+ "', '" + u.getSenha() +"')" );
		this.disconect();
	}
	
	public List<Usuario> listar(Integer codigo){
		try
		{
			List<Usuario> listaTodos = new ArrayList<Usuario>();
			this.conect();
			ResultSet resultado = this.query(" select idUsuario, "
					+ " nome, "
					+ " email,"
					+ " login,"
					+ " senha "
					+ " from usuarios"
					+ " where idUsuario = " + codigo.toString() + " or " +  codigo.toString() + " = 0" );
			
			while (resultado.next()){
				Usuario u = new Usuario();
				u.setCodigo(resultado.getLong("idUsuario"));
				u.setNome(resultado.getString("nome"));
				u.setEmail(resultado.getString("email"));
				u.setLogin(resultado.getString("login"));
				u.setSenha(resultado.getString("senha"));
				listaTodos.add(u);
			}
			
			return listaTodos;
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Carregar Usuários. " + e.toString());
			return null;
		}finally{
			this.disconect();
		}
		
		
	}
	
	public Usuario buscar(Integer codigo){
		List<Usuario> users = this.listar(codigo);
		return users.get(0);
	}
	
	public Usuario buscar(String login, String senha){
		List<Usuario> users = this.listar(0);
		for(Usuario u : users)
			if (u.getLogin().equals(login) && u.getSenha().equals(senha))
				return u;
		return null;
	}
	
	
	
	
}
