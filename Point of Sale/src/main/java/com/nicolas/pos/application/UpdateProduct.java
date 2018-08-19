package com.nicolas.pos.application;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nicolas.pos.model.Product;
import com.nicolas.pos.utilities.LoginController;
import com.nicolas.pos.utilities.WindowsUtilities;
import com.nicolas.pos.dao.DaoFactory;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateProduct extends JFrame {

	private static final long serialVersionUID = 4159303708270824392L;
	private JPanel contentPane;
	private JTextField productNameTextField;
	private JTextField productValueTextField;

	public UpdateProduct(final Product updateProduct) {
		
		if (LoginController.getLoggedInUser().canUpdateProduct()) {
		
			setResizable(false);
			setType(Type.POPUP);
			this.setTitle("Update Product");
			this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			setBounds(100, 100, 294, 228);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			WindowsUtilities.centreWindow(this);
			JLabel lblNombre = new JLabel("Name");
			
			productNameTextField = new JTextField();
			productNameTextField.setColumns(10);
			productNameTextField.setText(updateProduct.getName());
			
			JLabel lblPrecio = new JLabel("Price");
			
			productValueTextField = new JTextField();
			productValueTextField.setColumns(10);
			productValueTextField.setText(String.valueOf(updateProduct.getPrice()));
			
			JButton btnCrearProducto = new JButton("Update Product");
			btnCrearProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					updateProduct.setName(productNameTextField.getText());
					updateProduct.setPrice(Float.valueOf(productValueTextField.getText()));
					
					DaoFactory.getProductDao().update(updateProduct);
					
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