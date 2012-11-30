import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.geom.*;

public class ImageRender extends Canvas{

	
	public BufferedImage img;
	public ArrayList<Point2D.Double> marks = new ArrayList<Point2D.Double>();
	
	
	public ImageRender(BufferedImage img){
		this.img = img;
	}
	

	public void paint(Graphics g){
		g.drawImage(this.img,0, 0, null);
		g.setColor(Color.red);
		for(Point2D.Double point: this.marks){
		g.fillOval((int)point.x -3,(int) point.y - 3, 6, 6);
		}
	}
	


}
