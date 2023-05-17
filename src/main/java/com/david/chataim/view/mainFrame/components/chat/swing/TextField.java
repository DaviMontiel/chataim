package com.david.chataim.view.mainFrame.components.chat.swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class TextField extends JTextPane {

    private static final long serialVersionUID = 1L;

    private String hint = "";
    private final Animator animator;
    private float animate;
    private boolean show = true;
    
    
	public void setHint(String hint) {
        this.hint = hint;
        repaint();
    }//FUN

    public TextField() {
        setOpaque(false);
        setBorder(new EmptyBorder(9, 1, 9, 1));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(new Color(255, 255, 255));
        setSelectionColor(new Color(200, 200, 200, 100));
        autoWrapText();
        animator = new Animator(350, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (show) {
                    animate = fraction;
                } else {
                    animate = 1f - fraction;
                }//IF
                
                repaint();
            }

            @Override
            public void end() {
                show = !show;
                repaint();
            }
        });
        
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!getText().equals("")) {
                    if (show) {
                        if (animator.isRunning() == false) {
                            stop();
                            animator.start();
                        }//IF
                    } else if (animator.isRunning()) {
                        stop();
                        animator.start();
                    }//IF
                }//IF
            }//EVENT

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (getText().equals("")) {
                    stop();
                    animator.start();
                }//IF
            }//EVENT

            @Override
            public void changedUpdate(DocumentEvent e) {}//EVENT
        });
    }//Constructor

    private void autoWrapText() {
        setEditorKit(new AutoWrapText());
    }//FUN

    private void stop() {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0f);
        }//IF
    }//FUN

    @Override
    public void paint(Graphics g) {
        if (!hint.equals("")) {
            Graphics2D g2 = (Graphics2D) g.create();
            
            int h = getHeight();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            
            g2.setColor(new Color(170, 170, 170));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - animate));
            g2.drawString(hint, ins.left + (animate * 30), h / 2 + fm.getAscent() / 2 - 1);
            g2.dispose();
        }//IF
        
        super.paint(g);
    }//FUN
}//CLASS