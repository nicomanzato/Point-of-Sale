package com.nicolas.pos.application;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nicolas.pos.dao.DaoFactory;
import com.nicolas.pos.model.Product;
import com.nicolas.pos.utilities.LoginController;
import com.nicolas.pos.utilities.WindowsUtilities;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewProduct extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField productNameTextField;
	private JTextField productValueTextField;

	/**
	 * Create the frame.
	 */
	public NewProduct() {
		
		if (LoginController.getLoggedInUser().getUserRole().canCreateProduct()) {
		
			setResizable(false);
			setType(Type.POPUP);
			this.setTitle("New Product");
			this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			setBounds(100, 100, 294, 228);
			WindowsUtilities.centreWindow(this);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			JLabel lblNombre = new JLabel("Name");
			
			productNameTextField = new JTextField();
			productNameTextField.setColumns(10);
			
			JLabel lblPrecio = new JLabel("Price");
			
			productValueTextField = new JTextField();
			productValueTextField.setColumns(10);
			
			JButton btnCrearProducto = new JButton("Create Product");
			btnCrearProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
									
					DaoFactory.getProductDao().save(new Product(productNameTextField.getText(),Float.valueOf(productValueTextField.getText())));
					
					productNameTextField.setText("");
					productValueTextField.setText("");
					
					dispose();
					
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
									.addComponent(productValueTextField)
									.addComponent(productNameTextField, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
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
							.addComponent(productNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPrecio)
							.addComponent(productValueTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
						.addComponent(btnCrearProducto)
						.addContainerGap())
			);
			contentPane.setLayout(gl_contentPane);
		} else {
			
			JOptionPane.showMessageDialog(null, "You don't have permission to do this action", "Success", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}

}
