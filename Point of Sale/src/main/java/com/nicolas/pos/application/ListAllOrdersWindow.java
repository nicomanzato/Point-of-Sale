package com.nicolas.pos.application;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.nicolas.pos.utilities.LoginController;
import com.nicolas.pos.utilities.WindowsUtilities;

import java.awt.BorderLayout;

public class ListAllOrdersWindow extends JFrame{

	private static final long serialVersionUID = -5390708509532009327L;
	private JTable table;

	public ListAllOrdersWindow() {
		
		if (LoginController.getLoggedInUser().canUpdateOrder()) {
			
			setResizable(true);
			
			this.setTitle("List of all Orders");
			
			this.setBounds(100, 100, 450, 300);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			WindowsUtilities.centreWindow(this);
			
			table = new JTableManagerOrder();
			getContentPane().add(table, BorderLayout.CENTER);
		
		} else {
		
			JOptionPane.showMessageDialog(null, "You don't have permission to do this action", "Success", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}

}
