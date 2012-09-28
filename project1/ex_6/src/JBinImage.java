import javax.swing.*;
import java.awt.*;

public class JBinImage extends JComponent{

	private int[][] img;
	
	private double max_x;
	private double max_y;
	
	private double dx;
	private double dy;
	
	
	
	
	public void setImg(int[][] img){
		this.img = img;
		//calculate dimensions
		max_x = this.getBounds().width;
		max_y = this.getBounds().height;
		
		dx = max_x / img[0].length;
		dy = max_y / img.length;
		
		
	}
	
	public void paintComponent(Graphics g){
		
		//grid with image square pixels
		
		double cur_x=0;
		double cur_y=0;
		for(int y=0;y<img.length;y++){
			cur_x=0;
			for(int x=0;x<img[y].length;x++){
				
				if(img[y][x]==0){
					g.setColor(Color.black);
				}else{
					g.setColor(Color.white);
				}
				g.drawRect((int)cur_x, (int)cur_y, (int)dx, (int)dy);
				
				cur_x += dx;
			}
			cur_y +=dy;
		}
		
		
		
	}
	
	
	
}
