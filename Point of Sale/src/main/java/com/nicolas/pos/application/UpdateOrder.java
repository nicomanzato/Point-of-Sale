package com.nicolas.pos.application;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.nicolas.pos.model.Order;
import com.nicolas.pos.model.Product;
import com.nicolas.pos.utilities.LoginController;
import com.nicolas.pos.utilities.WindowsUtilities;
import com.nicolas.pos.dao.DaoFactory;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class UpdateOrder extends JFrame {

	private static final long serialVersionUID = -3087332566331177772L;
	private JPanel contentPane;
	private JTable tableOrderedProducts;
	private Order updateOrder;

	/**
	 * Create the frame.
	 */
	public UpdateOrder(Long updateOrderId) {
		if (LoginController.getLoggedInUser().canUpdateOrder()) {
			
			updateOrder = LoginController.getLoggedInUser().getOrderById(updateOrderId);
			
			setResizable(false);
			this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
			setBounds(100, 100, 860, 500);
			this.setTitle("Update Order");
			WindowsUtilities.centreWindow(this);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			JPanel OrderedProductsPanel = new JPanel();
			OrderedProductsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			
			JPanel panel = new JPanel();
			panel.setBackground(SystemColor.info);
			panel.setForeground(SystemColor.desktop);
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 461, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
						.addComponent(OrderedProductsPanel, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
							.addComponent(OrderedProductsPanel, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JButton btnCreateOrder = new JButton("Update Order");
			btnCreateOrder.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					
					if (updateOrder.isEmpty()){
						
						JOptionPane.showMessageDialog(null, "The order is empty", "Error", JOptionPane.INFORMATION_MESSAGE);
						
					}else{
						
						LoginController.getLoggedInUser().updateOrder(updateOrder);
						
						JOptionPane.showMessageDialog(null, "Order updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
						
						dispose();
						
					}
				}
			});
			
			JLabel lblProducts = new JLabel("Products");
			lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblTotal = new JLabelObserver("Total: $"+updateOrder.getPrice(), updateOrder);
			lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JScrollPane scrollPane = new JScrollPane();
			GroupLayout gl_OrderedProductsPanel = new GroupLayout(OrderedProductsPanel);
			gl_OrderedProductsPanel.setHorizontalGroup(
				gl_OrderedProductsPanel.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, gl_OrderedProductsPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_OrderedProductsPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
							.addComponent(lblProducts, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
							.addGroup(Alignment.TRAILING, gl_OrderedProductsPanel.createSequentialGroup()
								.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
								.addComponent(btnCreateOrder, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
			);
			gl_OrderedProductsPanel.setVerticalGroup(
				gl_OrderedProductsPanel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_OrderedProductsPanel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblProducts)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_OrderedProductsPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnCreateOrder)
							.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
			);
			
			tableOrderedProducts = new JTableOrderedProduct(updateOrder);
			tableOrderedProducts.setBackground(SystemColor.info);
			scrollPane.setViewportView(tableOrderedProducts);
			OrderedProductsPanel.setLayout(gl_OrderedProductsPanel);
			contentPane.setLayout(gl_contentPane);
			
			List<Product> products = DaoFactory.getProductDao().getProducts();
			
			for (Product product: products) {
				
				JButtonProduct button = new JButtonProduct(product, updateOrder);
				panel.add(button);
			}  
		} else { 
			
			JOptionPane.showMessageDialog(null, "You don't have permission to do this action", "Success", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}

}