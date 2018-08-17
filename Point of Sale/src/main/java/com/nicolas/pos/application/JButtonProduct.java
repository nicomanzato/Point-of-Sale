package com.nicolas.pos.application;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import com.nicolas.pos.model.Order;
import com.nicolas.pos.model.Product;
import com.nicolas.pos.utilities.WindowsUtilities;

public class JButtonProduct extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	
	public JButtonProduct(final Product product, final Order order){
		super(product.getName());
		this.setProduct(product);
		this.addMouseListener(new MouseAdapter()
		{
			
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getButton() == MouseEvent.BUTTON1) // Left Click
		    {
				order.addProduct(product);
		    }	    
			else if(WindowsUtilities.isRightClick(e)) // Right Click 
		    {
		    	order.removeProduct(product);
		    }
			
		}

		});
		
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	

}