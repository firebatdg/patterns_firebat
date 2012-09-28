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
	
	
	public JBinImage(){
		super();
	}
	
	public void setImg(int[][] img){
		this.img = img;
		//calculate dimensions
		max_x = this.getBounds().width;
		max_y = this.getBounds().height;
		
		System.out.printf("%f,%f\n",max_x, max_y);
		
		
		dx = max_x / img[0].length;
		dy = max_y / img.length;
		
		System.out.printf("%f,%f\n",dx, dy);
		
		
	}
	@Override
	public void paint(final Graphics g){
		
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
				g.fillRect((int)cur_x, (int)cur_y, (int)dx, (int)dy);
				System.out.printf("%f,%f = %d\n",cur_x, cur_y, img[y][x]);
				cur_x += dx;
			}
			cur_y +=dy;
			
		
		}
		
		
		
	}
	
	
	
}
