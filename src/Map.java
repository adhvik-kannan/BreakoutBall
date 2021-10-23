import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Map {

	protected int width;
	protected int height;
	protected int map[][];
	private Color[] colors = {Color.YELLOW, Color.GREEN, Color.ORANGE, Color.RED};
	
	public Map(int cols, int rows ) {
		map = new int[rows][cols];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				map[i][j] = 1;
			}
		}
		width = 700 / cols;
		height = 300 / rows;
	}
	
	public void draw(Graphics2D g) {
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j] > 0) {
					if(i==0 || i==1) {
						g.setColor(colors[3]);
					}
					else if(i==2 || i==3) g.setColor(colors[2]);
					else if(i==4 || i==5) g.setColor(colors[1]);
					else g.setColor(colors[0]);
					//g.setColor(Color.WHITE);
					g.fillRect(j * width + 150, i * height + 50, width, height);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.BLACK);
					g.drawRect(j*width+150, i*height+50, width, height);
				}
			}
		}	
	}
	
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}
	
	
	
}
