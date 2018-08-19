package com.nicolas.pos.application;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
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

import com.nicolas.pos.model.Order;
import com.nicolas.pos.utilities.LoginController;
import com.nicolas.pos.utilities.WindowsUtilities;
import com.nicolas.pos.dao.DaoFactory;

public class JTableManagerOrder extends JTable implements Observer{

	private static final long serialVersionUID = -1266891863842442712L;

	class PopUpMenu extends JPopupMenu {

		private static final long serialVersionUID = 5413625012055643522L;
		JTableManagerOrder table;
	    public PopUpMenu(final JTableManagerOrder table){
	        
	    	this.table = table;
	    	JMenuItem updateMenuItem = new JMenuItem("Update");
	        
	    	updateMenuItem.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseReleased(MouseEvent e) {
			    	
			    	Long idOrder = Long.valueOf(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
			    	
			    	final JFrame frame = new UpdateOrder(idOrder);
					
					frame.setVisible(true);
			    }
			});
	    	
	    	add(updateMenuItem);
	        
	    	JMenuItem deleteMenuItem = new JMenuItem("Delete");
	    	
	    	deleteMenuItem.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseReleased(MouseEvent e) {
			 
	                if (LoginController.getLoggedInUser().canDeleteOrder()) {
			    	
				    	int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to delete this order?","Warning",JOptionPane.YES_NO_OPTION);
		                
		                if(dialogResult == JOptionPane.YES_OPTION){
		                	
		                	Order order=null;
					    	
					    	Long idOrder = Long.valueOf(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
					    	
					    	order = DaoFactory.getOrderDao().getOrder(idOrder);
	
		                	LoginController.getLoggedInUser().deleteOrder(order);
	
		                }
	                
	                } else JOptionPane.showMessageDialog(null, "You don't have permission to do this action", "Success", JOptionPane.INFORMATION_MESSAGE);
			    }
			});
	    	
	        add(deleteMenuItem);
	    }
	}
	
	public JTableManagerOrder(){
		
		this.setModel(createModel());
		this.setEnabled(false);
		
		this.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {
		    	
		    	JTableManagerOrder table = ((JTableManagerOrder)e.getSource());
		    			    	
		        if (WindowsUtilities.isRightClick(e) && e.getComponent() instanceof JTable ) {
			    	
		        	int r = table.rowAtPoint(e.getPoint());
		            if (r >= 0 && r < table.getRowCount()) {
		            	((JTableManagerOrder)e.getSource()).setRowSelectionInterval(r, r);
		            } else {
		            	((JTableManagerOrder)e.getSource()).clearSelection();
		            }
		        	
		            JPopupMenu popup = new PopUpMenu(table);
		            popup.show(e.getComponent(), e.getX(), e.getY());
		        }
		    }
		});
		
		DaoFactory.getOrderDao().addObserver(this);
		DaoFactory.getUserDao().addObserver(this);
		
	}
	
	public TableModel createModel(){
		
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID","Date/Hour", "Total Price"
				}
			);
		
		this.setModel(model);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		
		this.setRowSorter(sorter);
		
		List<Order> orders = DaoFactory.getOrderDao().getOrders();
		
		for (Order order : orders) {
			
			model.addRow(new Object[] { order.getOrderId() ,order.getDate(), "$"+order.getPrice() });
			
		}
		
		return model;
		
	}

	public void update(Observable o, Object arg) {
		
		this.setModel(this.createModel());
	}
}