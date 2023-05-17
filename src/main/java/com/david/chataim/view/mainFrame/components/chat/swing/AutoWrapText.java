package com.david.chataim.view.mainFrame.components.chat.swing;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class AutoWrapText extends StyledEditorKit {

    private static final long serialVersionUID = 1L;
    
    
	@Override
    public ViewFactory getViewFactory() { return new WarpColumnFactory(); }//FUN

    private class WarpColumnFactory implements ViewFactory {
        @Override
        public View create(Element elmnt) {
            String kind = elmnt.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new WarpLabelView(elmnt);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elmnt);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(elmnt, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elmnt);
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elmnt);
                }//IF
            }//IF
            
            return new LabelView(elmnt);
        }//EVENT
    }//CLASS

    private class WarpLabelView extends LabelView {
    	
        public WarpLabelView(Element elem) { super(elem); }//Constructor
        

        @Override
        public float getMinimumSpan(int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0;
                case View.Y_AXIS:
                    return super.getMinimumSpan(axis);
                default:
                    throw new IllegalArgumentException("Invalid Axis:" + axis);
            }//SWITCH
        }//FUN
    }//CLASS
}//CLASS