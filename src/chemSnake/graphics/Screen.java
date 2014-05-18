package chemSnake.graphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import chemSnake.entities.Apples;
import chemSnake.entities.BodyPart;

public class Screen extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final int WIDTH = 800, HEIGTH = 800;
	public Thread thread;
	public boolean running;
	
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	
	private Apples apple;
	private ArrayList<Apples> apples;
	
	private Random r;
	
	private int xCoor = 39, yCoor = 39;
	private int size = 5;
	
	private boolean right = true, left = false, up = false, down = false;
	private boolean paused = false;
	
	public int ticks = 0;
	
	public int speed = 500000;
	
	private Key key;
	
	public Screen()
	{
		setFocusable(true);
		key = new Key();
		addKeyListener(key);
		setPreferredSize(new Dimension(WIDTH, HEIGTH));
		
		snake = new ArrayList<BodyPart>();
		apples = new ArrayList<Apples>();
		
		r = new Random();
		
		start();
	}
	
	public void start() {
		running = true;
		thread = new Thread(this, "Game Loop");
		
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tick()
	{
		if(snake.size() == 0)
		{
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
			
		}
		
		if(apples.size() == 0)
		{
			int xCor = r.nextInt(79);
			int yCor = r.nextInt(79);
			
			apple = new Apples(xCor, yCor, 10);
			apples.add(apple);
		}
		
		for(int i = 0; i < apples.size(); i++)
		{
			if(xCoor == apples.get(i).getxCor() && yCoor == apples.get(i).getyCor())
			{
				if(size < 20) size += 2;
				else if(size > 20) size++;
				apples.remove(0);
				i--;
				if(speed > 200000)	speed -= 20000;
			}
		}
		
		for(int i = 0; i < snake.size(); i++)
		{
			if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor())
			{
				if(i != snake.size()-1)
				{
					stop();
				}
			}
		}
		
		if(xCoor < 0 || xCoor > 79 || yCoor < 0 || yCoor > 79)
		{
			if(xCoor < 0) xCoor = 79;
			if(xCoor > 79) xCoor = 0;
			if(yCoor < 0) yCoor = 79;
			if(yCoor > 79) yCoor = 0;
		}
		
		ticks++;
		
		if(ticks > speed)
		{
			if(right) xCoor++;
			if(left) xCoor--;
			if(up) yCoor--;
			if(down) yCoor++;
			
			ticks = 0;
		
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
			
			if(snake.size() > size)
			{
				snake.remove(0);
			}
		}
		
		
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGTH);
		g.setColor(Color.black);
		for(int i = 0; i < WIDTH / 10; i++)
		{
			g.drawLine(i*10, 0, i*10, HEIGHT);
		}
		for(int i = 0; i < HEIGHT / 10; i++){
			g.drawLine(0, i*10, WIDTH, i*10);
		}
		
		for(int i = 0; i < snake.size(); i++)
		{
			snake.get(i).draw(g);
		}
		
		for(int i = 0; i < apples.size(); i++)
		{
			apples.get(0).draw(g);
		}
		
		for(int i = 0; i < apples.size(); i++)
		{
			apples.get(i).draw(g);
		}
	}
	
	public void run(){
		while(running)
		{
			tick();
			repaint();
		}
	}
	
	private class Key implements KeyListener{

		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_RIGHT && !left && !paused)
			{
				up = false;
				down = false;
				right = true;
			}
			if(key == KeyEvent.VK_LEFT && !right && !paused)
			{
				up = false;
				down = false;
				left = true;
			}
			if(key == KeyEvent.VK_UP && !down && !paused)
			{
				left = false;
				right = false;
				up = true;
			}
			if(key == KeyEvent.VK_DOWN && !up && !paused)
			{
				left = false;
				right = false;
				down = true;
			}
			if(key == KeyEvent.VK_P)
			{
				if(!paused) {
					thread.suspend();
					paused = true;			
				}
				else{
					thread.resume();
					paused = false;
				}
			}
		}
		
		public void keyReleased(KeyEvent arg0){
			
		}
		
		public void keyTyped(KeyEvent arg0){
			
		}
	}
}
