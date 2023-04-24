package simlationGame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class GUI {
	
	private JLabel label1, label2, label3;
    private JPanel panel;
    private JFrame frame;
    
    JLabel dayLabel;
	JLabel hourLabel;
	JLabel yearLabel;

	
	
	//Constructor
	public GUI(byte windowType) {
		frame = new JFrame();
		
		//simulation time window
		if(windowType == 0) {
			
		
		
			dayLabel = new JLabel("Current Day of simulation: ");
			hourLabel = new JLabel("Current Hour of simulation: ");
			yearLabel = new JLabel("Current Year of simulation: ");
		
		
			JPanel panel = new JPanel();
			panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 50, 100));
			panel.setLayout(new GridLayout(0,1));
			panel.add(hourLabel);
			panel.add(dayLabel);
			panel.add(yearLabel);
		
			//frame stuff
			frame.add(panel, BorderLayout.CENTER);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setTitle("Simulation Time");
			frame.pack();
			frame.setMinimumSize(new Dimension(450, 300));
			frame.setVisible(true);
		
		}
		
	
		//List of Pops in world
		else {if(windowType == 1) {
			
		}}
	
		
		
		
	///end of constructor
	}
	
	
	public void updateTime(byte hour, int day, int year) {
	    hourLabel.setText("Current Hour of simulation: " + hour);
	    dayLabel.setText("Current Day of simulation: " + day);
	    yearLabel.setText("Current Year of simulation: " + year);
	}
	
	
	
	
}
