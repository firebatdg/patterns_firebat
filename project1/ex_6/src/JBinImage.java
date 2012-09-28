import javax.swing.*;
import java.awt.*;

public class JBinImage extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int[][] img;
	
	private double max_x;
	private double max_y;
	
	private double dx;
	private double dy;
	
	private Color[] colors;
	
	public JBinImage(){
		super();
		
		colors = new Color[8];
		colors[0] = Color.black;
		colors[1] = Color.white;
		colors[2] = Color.green;
		colors[3] = Color.red;
		
	}
	
	public void setImg(int[][] img){
		this.img = img;
		//calculate dimensions
		max_x = this.getBounds().width;
		max_y = this.getBounds().height;
		
		
		
		
		dx = max_x / img[0].length;
		dy = max_y / img.length;
		
		
		
		
	}
	@Override
	public void paint(final Graphics g){
		
		//grid with image square pixels
		
		double cur_x=0;
		double cur_y=0;
		for(int y=0;y<img.length;y++){
			cur_x=0;
			for(int x=0;x<img[y].length;x++){
				
				
				g.setColor(this.colors[img[y][x]]);
				
				g.fillRect((int)cur_x, (int)cur_y, (int)dx, (int)dy);
				
				cur_x += dx;
			}
			cur_y +=dy;
			
		
		}
		
		
		
	}
	
	
	
}
