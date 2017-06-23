package venda.maluca.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import venda.maluca.model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JFrameLogin extends JFrame {

	private static final long serialVersionUID = -5806254228105389402L;
	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JTextField textFieldSenha;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameLogin frame = new JFrameLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrameLogin() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 333, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setBounds(70, 11, 106, 14);
		contentPane.add(lblUsurio);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(70, 30, 183, 20);
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(70, 61, 46, 14);
		contentPane.add(lblSenha);
		
		textFieldSenha = new JPasswordField();
		textFieldSenha.setBounds(70, 77, 183, 20);
		contentPane.add(textFieldSenha);
		textFieldSenha.setColumns(10);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(164, 113, 89, 23);
		contentPane.add(btnSair);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(70, 113, 89, 23);
		contentPane.add(btnEntrar);
		
		btnEntrar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario u = new Usuario().logar(textFieldLogin.getText(), textFieldSenha.getText());
				if (u != null)
				{
					JFramePrincipal principal = new JFramePrincipal(u);
					principal.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Atenção !! Usuário ou senha Invalida");
				}
					
				
			}
		});
		
		btnSair.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

}
