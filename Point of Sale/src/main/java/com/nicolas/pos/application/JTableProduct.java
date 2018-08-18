package com.nicolas.pos.application;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.nicolas.pos.model.Product;
import com.nicolas.pos.utilities.LoginController;
import com.nicolas.pos.utilities.WindowsUtilities;
import com.nicolas.pos.dao.DaoFactory;

public class JTableProduct extends JTable implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	class PopUpMenu extends JPopupMenu {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		JTableProduct table;
	    public PopUpMenu(final JTableProduct table){
	        
	    	this.table = table;
	    	JMenuItem updateMenuItem = new JMenuItem("Update");
	        
	    	updateMenuItem.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseReleased(MouseEvent e) {
			    	
			    	
			    	Product product=null;
			    	
			    	int idProduct = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
			    			
			    	product = DaoFactory.getProductDao().getProduct(idProduct);
			    	
			    	final JFrame frame = new UpdateProduct(product);
					
					frame.setVisible(true);
			    }
			});
	    	
	    	add(updateMenuItem);
	        
	    	JMenuItem deleteMenuItem = new JMenuItem("Delete");
	    	
	    	deleteMenuItem.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseReleased(MouseEvent e) {
			    	
			    	if ( LoginController.getLoggedInUser().getUserRole().canDeleteProduct() ) {
			 
		                int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to delete this product?","Warning",JOptionPane.YES_NO_OPTION);
		                
		                if(dialogResult == JOptionPane.YES_OPTION){
		                	
		                	Product product=null;
					    	
					    	int idProduct = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
					    	
					    	product = DaoFactory.getProductDao().getProduct(idProduct);
	
					    	try{
					    	
					    		DaoFactory.getProductDao().delete(product);
	
					    	}catch(Exception e1){
					    		
					    		
					    		JOptionPane.showMessageDialog(null, "It's not possible to delete this product", "Error", JOptionPane.INFORMATION_MESSAGE);
					    		
					    	}
		                }
	                } else JOptionPane.showMessageDialog(null, "You don't have permission to do this action", "Success", JOptionPane.INFORMATION_MESSAGE);
			    }
			});
	    	
	        add(deleteMenuItem);
	    }
	}
	
	public JTableProduct(){
		
		this.setModel(createModel());
		this.setEnabled(false);
		
		this.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	
		    	JTableProduct table = ((JTableProduct)e.getSource());
		    	
		        if (WindowsUtilities.isRightClick(e) && e.getComponent() instanceof JTable ) {
		        	
		        	int r = table.rowAtPoint(e.getPoint());
		            if (r >= 0 && r < table.getRowCount()) {
		            	((JTableProduct)e.getSource()).setRowSelectionInterval(r, r);
		            } else {
		            	((JTableProduct)e.getSource()).clearSelection();
		            }
		        	
		            JPopupMenu popup = new PopUpMenu(table);
		            popup.show(e.getComponent(), e.getX(), e.getY());
		        }
		    }
		});
		
		DaoFactory.getProductDao().addObserver(this);
		
	}
	
	public TableModel createModel(){
		
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID","Name", "Price"
				}
			);
		
		this.setModel(model);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		
		this.setRowSorter(sorter);
		
		for (Product product : DaoFactory.getProductDao().getProducts()) {
			model.addRow(new Object[] { product.getProductId() ,product.getName(), "$"+product.getPrice() });
		}
		
		return model;
		
	}

	public void update(Observable arg0, Object arg1) {
		
		this.setModel(this.createModel());
		
	}
	
}