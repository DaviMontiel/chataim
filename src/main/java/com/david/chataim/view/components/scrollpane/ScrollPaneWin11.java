package com.david.chataim.view.components.scrollpane;

import java.awt.Container;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Objects;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

public class ScrollPaneWin11 extends JScrollPane {

	private static final long serialVersionUID = 1L;
	

	public ScrollPaneWin11() {
        getVerticalScrollBar().setUI(new ScrollBarWin11UI());
        getHorizontalScrollBar().setUI(new ScrollBarWin11UI());
        setLayout(new ScrollLayout());
    }//Constructor

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }//FUN

    @Override
    public void updateUI() {
        super.updateUI();
        setComponentZOrder(getVerticalScrollBar(), 0);
        setComponentZOrder(getHorizontalScrollBar(), 1);
        setComponentZOrder(getViewport(), 2);
        getVerticalScrollBar().setOpaque(false);
        getHorizontalScrollBar().setOpaque(false);
    }//FUN

    private class ScrollLayout extends ScrollPaneLayout {

		private static final long serialVersionUID = 1L;
		

		@Override
        public void layoutContainer(Container parent) {
            super.layoutContainer(parent);
            if (parent instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) parent;
                Rectangle rec = scroll.getViewport().getBounds();
                Insets insets = parent.getInsets();
                int rhHeight = 0;
                if (scroll.getColumnHeader() != null) {
                    Rectangle rh = scroll.getColumnHeader().getBounds();
                    rhHeight = rh.height;
                }
                rec.width = scroll.getBounds().width - (insets.left + insets.right);
                rec.height = scroll.getBounds().height - (insets.top + insets.bottom) - rhHeight;
                if (Objects.nonNull(viewport)) {
                    viewport.setBounds(rec);
                }
                if (!Objects.isNull(hsb)) {
                    Rectangle hrc = hsb.getBounds();
                    hrc.width = rec.width;
                    hsb.setBounds(hrc);
                }
            }//IF
        }//FUN
    }//CLASS
}//CLASS