package chemSnake.entities;

import java.awt.*;

public class BodyPart {
	
	private int xCoor, yCoor, width, height;

	public BodyPart(int xCorr, int yCorr, int tileSize) {
		this.xCoor = xCorr;
		this.yCoor = yCorr;
		width = tileSize;
		height = tileSize;
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(xCoor * width, yCoor * height, width, height);
		g.setColor(Color.GREEN);
		g.fillRect(xCoor * width + 2, yCoor * height + 2, width - 4, height - 4);
		
	}

	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
 }
