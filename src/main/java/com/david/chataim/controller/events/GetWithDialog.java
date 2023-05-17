package com.david.chataim.controller.events;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.david.chataim.controller.Controller;

public class GetWithDialog {

	public static File showFileDialog() {
		FileDialog fileDialog = new FileDialog(new Frame(), "Selecciona un archivo");
    	fileDialog.setMode(FileDialog.LOAD);
    	fileDialog.setFile("*.png;*.jpg;*.jpeg");
    	fileDialog.setVisible(true);
    	
    	if (fileDialog.getFile() == null) {
    		return null;
    	}//IF
    	
    	return new File(fileDialog.getDirectory(), fileDialog.getFile());
	}//FUN

    public static Object[] showAsciiDialog() {
    	Object[] ascii = new Object[2];
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(Controller.s().getAscii().size() / 4, 3));

        for (Map.Entry<Integer, String> entry : Controller.s().getAscii().entrySet()) {
            JButton button = new JButton(entry.getValue());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	ascii[0] = entry.getKey();
                	ascii[1] = entry.getValue();
                	
                	JOptionPane.getRootFrame().dispose();
                }
            });
            panel.add(button);
        }//FOR

        JOptionPane.showMessageDialog(null, panel);
        
        return ascii;
    }//FUN
}//CLASS