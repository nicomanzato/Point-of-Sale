package com.nicolas.pos.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.nicolas.pos.model.Order;
import com.nicolas.pos.model.OrderedProduct;
import com.nicolas.pos.model.Product;

public class JTableOrderedProduct extends JTable implements Observer{

	private static final long serialVersionUID = -5584131696702896957L;
	final int QUANTITY_COLUMN = 2;
	final int PRICE_COLUMN = 3;
	List<Product> products = new ArrayList<Product>();
	
	public JTableOrderedProduct(Order order){
		
		this.setModel(createModel(order));
		order.addObserver(this);
		this.setEnabled(false);
		
	}
	
	public TableModel createModel(Order order){
		
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Name","Price","Quantity", "Total Price"
				}
			);
		
		this.setModel(model);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		
		this.setRowSorter(sorter);

		for (OrderedProduct product : order.getProducts()) {
			((DefaultTableModel)this.getModel()).addRow(new Object[] { product.getProduct().getName(), product.getPrice() ,product.getQuantity(), "$"+product.calculateSubTotal()  });
		}
		
		return model;
		
	}
	
	public void update(Observable arg0, Object arg1) {
		
		Order order = (Order) arg0;
		
		this.setModel(createModel(order));
		
	}
}