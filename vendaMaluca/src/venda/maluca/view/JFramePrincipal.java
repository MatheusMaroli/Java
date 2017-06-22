package venda.maluca.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import venda.maluca.model.Usuario;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;


public class JFramePrincipal extends JFrame {

	private static final long serialVersionUID = 4740331679726391934L;
	private JPanel contentPane;
	private Usuario usuarioLogado;

	public JFramePrincipal(Usuario u) {
		usuarioLogado = u;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Cadastro");
		menuBar.add(mnNewMenu);
		
		JMenuItem jMenuItemTipoProduto = new JMenuItem("Tipo Produto");
		mnNewMenu.add(jMenuItemTipoProduto);
		jMenuItemTipoProduto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 JFrameTipoProduto frameTipoProduto = new JFrameTipoProduto();
				 frameTipoProduto.setVisible(true);
				 
				
			}
		});
		
		JMenuItem mntmFormasDePagamento = new JMenuItem("Formas de Pagamento");
		mnNewMenu.add(mntmFormasDePagamento);
		
		mntmFormasDePagamento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new JFrameFormaPagamento().setVisible(true);
				
			}
		});
		
		JMenuItem mntmProdutos = new JMenuItem("Produtos");
		mntmProdutos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
				new JFrameProduto().setVisible(true);				
			}
		});
		mnNewMenu.add(mntmProdutos);
		
		JMenu mnVetrine = new JMenu("Vetrine");
		menuBar.add(mnVetrine);
		
		JMenu mnAdmin = new JMenu("Admin");
		menuBar.add(mnAdmin);
		
		JMenuItem mntmUsurios = new JMenuItem("Usu\u00E1rios");
		mntmUsurios.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new JFrameUsuario().setVisible(true);	
			}
		});
		
		
		mnAdmin.add(mntmUsurios);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
	}
	
	public Usuario getUsuarioLogado(){
		return this.usuarioLogado;
	}

}
