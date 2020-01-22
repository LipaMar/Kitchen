package main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MenuFrame extends JFrame{
	public final int FRAME_WIDTH=600;
	public final int FRAME_HEIGHT=500;
	public MenuFrame() {
		setTitle("Kuchnia");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
}
