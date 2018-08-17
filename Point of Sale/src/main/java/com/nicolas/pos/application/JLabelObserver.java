package com.nicolas.pos.application;

import java.util.Observable;
import java.util.Observer;
import com.nicolas.pos.model.*;
import javax.swing.JLabel;

public class JLabelObserver extends JLabel implements Observer{

	private static final long serialVersionUID = 4800012631071688229L;

	public void update(Observable arg0, Object arg1) {
		
		Order order = (Order) arg0;
		
		this.setText("Total: $"+order.getPrice());
		
	}
	
	public JLabelObserver(String text, Order order){
		
		super(text);
		order.addObserver(this);
		
	}
	

}