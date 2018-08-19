package com.nicolas.pos.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import com.nicolas.pos.model.User;
import com.nicolas.pos.utilities.LoginController;
import com.nicolas.pos.utilities.WindowsUtilities;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1911356068599819226L;
	private JPanel contentPane;
	private JTable table;
	private JTable productTable;
	private User userLoggedIn;

	public MainWindow() {
		
		userLoggedIn = LoginController.getLoggedInUser();
		
		try{
			this.setTitle("Point of Sale");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1440, 768);
			
			WindowsUtilities.centreWindow(this);
			
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			
			JMenu mnNewMenu = new JMenu("Products");
			menuBar.add(mnNewMenu);
			
			JMenuItem mntmCreateProduct = new JMenuItem("Create Product");
			mntmCreateProduct.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
					JFrame newProductFrame = new NewProductWindow();
					
					newProductFrame.setVisible(true);
					
				}
			});
			
			mnNewMenu.add(mntmCreateProduct);
			
			JMenu mnOrders = new JMenu("Orders");
			menuBar.add(mnOrders);
			
			JMenuItem mntmNewMenuItem = new JMenuItem("Create Order");
			mntmNewMenuItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					
					final JFrame frame = new NewOrderWindow();
					
					frame.setVisible(true);
					
				}
			});
			mnOrders.add(mntmNewMenuItem);
			
			JMenuItem mntmListAllOrdersItem = new JMenuItem("List all Orders");
			mntmListAllOrdersItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					
					final JFrame frame = new ListAllOrdersWindow();
					
					frame.setVisible(true);
					
				}
			});
			mnOrders.add(mntmListAllOrdersItem);
			
			
			
			JMenu mnSettings = new JMenu("Settings");
			menuBar.add(mnSettings);
			
			JMenuItem mntmChangePasswordMenuItem = new JMenuItem("Change Password");
			mntmChangePasswordMenuItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					
					final JFrame frame = new ChangePasswordWindow();
					
					frame.setVisible(true);
					
				}
			});
			
			mnSettings.add(mntmChangePasswordMenuItem);

			
			JMenuItem mntmNewUserMenuItem = new JMenuItem("Create new User");
			mntmNewUserMenuItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					
					final JFrame frame = new NewUserWindow();
					
					frame.setVisible(true);
					
				}
			});
			
			mnSettings.add(mntmNewUserMenuItem);
			
			contentPane = new JPanel();
			contentPane.setToolTipText("Point of Sale");
			contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			setContentPane(contentPane);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(240, 240, 240));
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.setBackground(SystemColor.info);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
						.addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
							.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
							.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
						.addContainerGap())
			);
			
			JLabel lblProducts = new JLabel("Products");
			lblProducts.setFont(new Font("Tahoma", Font.PLAIN, 16));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			
			JButton btnCreateProduct = new JButton("Create Product");
			GroupLayout gl_panel_2 = new GroupLayout(panel_2);
			gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
							.addComponent(btnCreateProduct, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
							.addComponent(scrollPane_1)
							.addComponent(lblProducts, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_2.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblProducts)
						.addGap(18)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
						.addComponent(btnCreateProduct)
						.addContainerGap())
			);
			
			btnCreateProduct.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
					JFrame newProductFrame = new NewProductWindow();
					
					newProductFrame.setVisible(true);
					
				}
			});
			
			productTable = new JTableProduct();
			productTable.setBackground(SystemColor.info);
			scrollPane_1.setViewportView(productTable);
			panel_2.setLayout(gl_panel_2);
			
			JLabel lblPos = new JLabel("Hello, "+userLoggedIn.getUsername());
			lblPos.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblPos, GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
						.addContainerGap())
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(5)
						.addComponent(lblPos)
						.addContainerGap(391, Short.MAX_VALUE))
			);
			panel_1.setLayout(gl_panel_1);
			
			JLabel lblOrders = new JLabel("Orders");
			lblOrders.setFont(new Font("Tahoma", Font.PLAIN, 16));
			
			JScrollPane scrollPane = new JScrollPane();
			
			JButton btnNewButton = new JButton("Create Order");
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					final JFrame frame = new NewOrderWindow();
					
					frame.setVisible(true);
					
				}
			});
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
							.addComponent(scrollPane)
							.addComponent(lblOrders, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblOrders, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
						.addGap(12)
						.addComponent(btnNewButton)
						.addContainerGap())
			);
			
			table = new JTableOrder();
			scrollPane.setViewportView(table);
			table.setBorder(new LineBorder(new Color(0, 0, 0)));
			table.setBackground(SystemColor.info);
			panel.setLayout(gl_panel);
			contentPane.setLayout(gl_contentPane);
		}
		catch(Exception e){
			
			JOptionPane.showMessageDialog(null, "Ups, something went wrong :)", "Error", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
			
		}
	}
}