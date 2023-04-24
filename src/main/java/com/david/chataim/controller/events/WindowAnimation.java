package com.david.chataim.controller.events;

import javax.swing.JFrame;

public class WindowAnimation {
	
	public static void openWindowAnimation(JFrame frame) {
		new Thread(new Runnable() {
            @Override
            public void run() {
            	int perSecond = 6;
            	int currentFrame = 0;
            	while (true) {
            		try {
                        Thread.sleep(10);
                    }//TRY
                    catch (InterruptedException e) {e.printStackTrace();}//CATCH
            		
            		currentFrame += perSecond;
            		if (currentFrame < 100) {
            			
            		} else {
            			frame.setOpacity(1f);
            			break;
            		}//IF
                    frame.setOpacity(currentFrame / 100f);
            	}//WHILE
            }
        }).start();
	}//FUN
	
	public static Thread closeWindowAnimation(JFrame frame) {
		Thread anim = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int f=100; f>0; f-=6) {
                    try {
                        Thread.sleep(10);
                    }//TRY
                    catch (InterruptedException e) {e.printStackTrace();}//CATCH
                    frame.setOpacity(f / 100f);
                }//FOR
            }
        });
		anim.start();
		
		return anim;
	}//FUN
}//CLASS