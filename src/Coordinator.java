import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Coordinator {

	private static JFrame frame;
	
	public static void main(String[] args) {
		Gameplay gamePlay = new Gameplay();
		frame = new JFrame();
		frame.setBounds(300, 0, 1000, 800);
		frame.setTitle("Breakout Ball");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePlay);
		frame.setVisible(true);
		
	}
}
