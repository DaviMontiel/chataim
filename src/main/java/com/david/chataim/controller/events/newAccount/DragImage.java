package com.david.chataim.controller.events.newAccount;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;

import com.david.chataim.view.components.ImagePanel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DragImage extends DropTarget {

	private static final long serialVersionUID = 1L;

	private ImagePanel imagePanel;
	
	
	@Override
	public synchronized void drop(DropTargetDropEvent evt) {
        try {
            evt.acceptDrop(DnDConstants.ACTION_COPY);
            @SuppressWarnings("unchecked")
			List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
            if (droppedFiles.get(0).getAbsolutePath().contains(".PNG") ||
	        		droppedFiles.get(0).getAbsolutePath().contains(".png") ||
	        		droppedFiles.get(0).getAbsolutePath().contains(".jpg") ||
	        		droppedFiles.get(0).getAbsolutePath().contains(".jpeg")) {
            	
            	imagePanel.setImage(new ImageIcon(droppedFiles.get(0).getAbsolutePath()));
            	imagePanel.repaint();
            }//IF
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//EVENT
}//CLASS