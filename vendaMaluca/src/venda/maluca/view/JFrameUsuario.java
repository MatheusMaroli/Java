package venda.maluca.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import venda.maluca.dao.UsuarioDAO;
import venda.maluca.model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class JFrameUsuario extends JFrame {

	private static final long serialVersionUID = 1222520291458127290L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JTextField textFieldLogin;
	private JTextField textFieldSenha;
	private final Action actionSalvarUsuario = new SwingActionSalvaUsuario();
	private final Action actionCancelar = new SwingActionCancelar();



	public JFrameUsuario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(34, 11, 46, 14);
		contentPane.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(34, 25, 348, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(34, 57, 46, 14);
		contentPane.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(34, 71, 348, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(34, 102, 46, 14);
		contentPane.add(lblLogin);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(34, 117, 173, 20);
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(215, 102, 46, 14);
		contentPane.add(lblSenha);
		
		textFieldSenha = new JPasswordField();
		textFieldSenha.setBounds(217, 117, 165, 20);
		contentPane.add(textFieldSenha);
		textFieldSenha.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setAction(actionSalvarUsuario);
		btnSalvar.setBounds(34, 148, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(133, 148, 89, 23);
		btnCancelar.setAction(actionCancelar);
		contentPane.add(btnCancelar);
	}

	private void limparCampos(){
		textFieldNome.setText("");
		textFieldSenha.setText("");
		textFieldEmail.setText("");
		textFieldLogin.setText("");	
	}
	
	private class SwingActionCancelar extends AbstractAction {

		private static final long serialVersionUID = 5975691777418915786L;
		public SwingActionCancelar() {
			putValue(NAME, "Cancelar");
			putValue(SHORT_DESCRIPTION, "Cancelar");
		}
		public void actionPerformed(ActionEvent e) {
			limparCampos();
		}
	}
	
	private class SwingActionSalvaUsuario extends AbstractAction {

		private static final long serialVersionUID = 20741252953886841L;
		public SwingActionSalvaUsuario() {
			putValue(NAME, "Salvar");
			putValue(SHORT_DESCRIPTION, "Salvar Usuário");
		}
		public void actionPerformed(ActionEvent e) {
			try{
				new UsuarioDAO().adicionar( new Usuario(
						textFieldNome.getText(),
						textFieldSenha.getText(),
						textFieldEmail.getText(),
						textFieldLogin.getText()));
				JOptionPane.showMessageDialog(null, "Usuário Salvo com sucesso");
				limparCampos();
			} catch(Exception except){
				JOptionPane.showMessageDialog(null, "Atenção !! Erro ao salvar novo usuário. " + except.getMessage());
			}
		
		}
	}
}
