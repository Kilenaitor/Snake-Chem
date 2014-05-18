package chemSnake;

import java.awt.*;
import javax.swing.*;
import chemSnake.graphics.*;

public class Frame extends JFrame
{
	/**
	 * @author Kyle Minshall
	 * @date May 17
	 * @version 1.0
	 * @project Chem Final
	 */
	private static final long serialVersionUID = 1L;

	public Frame(){
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("Snake");
	    setResizable(false);
	    
		init();
		
	}	
	public void init() {
		setLayout(new GridLayout(1, 1, 0, 0));
		
		Screen s = new Screen();
		add(s);
		
		pack();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main (String[] args)
	{
		new Frame();
		
	}
}
