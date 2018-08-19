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
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.nicolas.pos.utilities.LoginController;
import com.nicolas.pos.utilities.WindowsUtilities;

public class ChangePasswordWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField oldPasswordTextField;
	private JPasswordField newPasswordTextField;
	private JPasswordField repeatPasswordField;
	

	/**
	 * Create the application.
	 */
	public ChangePasswordWindow() {
		setResizable(false);
		setType(Type.POPUP);
		this.setTitle("Point of Sale - Login");
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
		setBounds(100, 100, 400, 250);
		WindowsUtilities.centreWindow(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNombre = new JLabel("Old Password");
		
		oldPasswordTextField = new JPasswordField();
		oldPasswordTextField.setColumns(10);
		
		JLabel lblPrecio = new JLabel("New Password");
		
		newPasswordTextField = new JPasswordField();
		newPasswordTextField.setColumns(10);
		
		JLabel label = new JLabel("Repeat Password");
		
		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setColumns(10);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
				String encryptedOldPassword = LoginController.encryptPassword(oldPasswordTextField.getText());
				String newPassword  = newPasswordTextField.getText();
				String repeatPassword = repeatPasswordField.getText();
								
				if ((LoginController.getLoggedInUser().getPassword().equals(encryptedOldPassword)) && (newPassword.equals(repeatPassword))) {
					
					LoginController.getLoggedInUser().changePassword(encryptedOldPassword, newPassword);
					
					JOptionPane.showMessageDialog(null, "Your password has been changed", "Success", JOptionPane.INFORMATION_MESSAGE);
					
					dispose();
					
				} else {
				
					JOptionPane.showMessageDialog(null, "Passwords provided do not match", "Error", JOptionPane.INFORMATION_MESSAGE);
				
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(73)
							.addComponent(btnChangePassword, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
							.addGap(80))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(20))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblPrecio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(15)))
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(newPasswordTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
								.addComponent(repeatPasswordField, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
								.addComponent(oldPasswordTextField, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
							.addGap(20))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(oldPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecio)
						.addComponent(newPasswordTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(repeatPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addComponent(btnChangePassword)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
