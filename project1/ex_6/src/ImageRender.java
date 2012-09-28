import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageRender extends Canvas{

	
	public BufferedImage img;
	
	public ImageRender(BufferedImage img){
		this.img = img;
	}
	

	public void paint(Graphics g){
		g.drawImage(this.img,0, 0, null);
	}
	


}
