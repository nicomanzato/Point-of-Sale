package com.nicolas.pos.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.nicolas.pos.utilities.LoginController;
import com.nicolas.pos.utilities.WindowsUtilities;

public class LoginWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				
		try {
			LoginWindow frame = new LoginWindow();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		setResizable(false);
		setType(Type.POPUP);
		this.setTitle("Point of Sale - Login");
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setBounds(100, 100, 294, 228);
		WindowsUtilities.centreWindow(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNombre = new JLabel("Username");
		
		usernameTextField = new JTextField();
		usernameTextField.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Password");
		
		passwordTextField = new JPasswordField();
		passwordTextField.setColumns(10);
		
		JButton btnCrearProducto = new JButton("Login");
		btnCrearProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				if (LoginController.login(usernameTextField.getText(), passwordTextField.getText())) {
					
					new Main().setVisible(true);;
					
					dispose();
				
				} else {
					
					passwordTextField.setText("");
					
					JOptionPane.showMessageDialog(null, "Username or password invalid", "Error", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
				
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblPrecio))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordTextField)
								.addComponent(usernameTextField, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
							.addGap(20))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(73)
							.addComponent(btnCrearProducto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(80))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(usernameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecio)
						.addComponent(passwordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
					.addComponent(btnCrearProducto)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	

}
