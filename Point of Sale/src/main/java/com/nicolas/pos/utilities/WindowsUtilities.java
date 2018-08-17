package com.nicolas.pos.utilities;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

public class WindowsUtilities{

	public static boolean isRightClick(MouseEvent e) {
		
		//SwingUtilities and e.IsControlDown are used for MacOs users
    	
        return (e.isPopupTrigger() || (SwingUtilities.isRightMouseButton(e) || e.isControlDown()));
        
	}
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
}
