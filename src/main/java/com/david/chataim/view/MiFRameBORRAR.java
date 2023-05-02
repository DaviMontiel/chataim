package com.david.chataim.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import com.david.chataim.controller.Controller;
import com.david.chataim.controller.Dimens;
import com.david.chataim.view.main.UsersWindow;

import lombok.Getter;

public class MiFRameBORRAR {

    @Getter private UsersWindow frame = new UsersWindow(null);

    class MainPanel extends JPanel {

        public MainPanel() {
            setBackground(Color.gray);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }
    }

    class BorderPanel extends JPanel {

        private JLabel label;
        int pX, pY;

        public BorderPanel() {
            label = new JLabel(" X ");
            label.setOpaque(true);
            label.setBackground(Color.RED);
            label.setForeground(Color.WHITE);

            setBackground(Color.black);
            setLayout(new FlowLayout(FlowLayout.RIGHT));

            add(label);

            label.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    System.exit(0);
                }
            });
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    // Get x,y and store them
                    pX = me.getX();
                    pY = me.getY();

                }

                 public void mouseDragged(MouseEvent me) {

                    frame.setLocation(frame.getLocation().x + me.getX() - pX,
                            frame.getLocation().y + me.getY() - pY);
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent me) {

                    frame.setLocation(frame.getLocation().x + me.getX() - pX,
                            frame.getLocation().y + me.getY() - pY);
                }
            });
        }
    }

//    class OutsidePanel extends JPanel {
//
//        public OutsidePanel() {
//            setLayout(new BorderLayout());
//            add(new MainPanel(), BorderLayout.CENTER);
//            add(new BorderPanel(), BorderLayout.PAGE_START);
//            setBorder(new LineBorder(Color.BLACK, 5));
//        }
//    }

    private void createAnsShowGui() {
        ComponentResizer cr = new ComponentResizer();
        cr.setMinimumSize(new Dimension(300, 300));
        cr.setMaximumSize(new Dimension(Dimens.WINDOW_W+500, Dimens.WINDOW_H+500));
        cr.registerComponent(frame);
        cr.setSnapSize(new Dimension(10, 10));
//        frame.setUndecorated(true);
//        frame.add(new OutsidePanel());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Controller.s().init();
            	MiFRameBORRAR d = new MiFRameBORRAR();
            	d.createAnsShowGui();
            	Controller.s().setCurrentFrame(d.getFrame());
            }
        });
    }//MAIN
}//CLASS