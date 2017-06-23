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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
public class JFramePrincipal extends JFrame {

	private static final long serialVersionUID = 4740331679726391934L;
	private JPanel contentPane;
	private static Usuario usuarioLogado;
	private final Action action = new SwingAction();

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
		
		JMenu mnCompras = new JMenu("Compras");
		mnCompras.setAction(action);
		menuBar.add(mnCompras);
		mnCompras.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				new JFrameCompra().setVisible(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
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
	
	public static Usuario getUsuarioLogado(){
		return usuarioLogado;
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Compra");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "opa");
		}
	}
}
