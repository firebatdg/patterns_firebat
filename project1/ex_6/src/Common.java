import java.awt.image.*;

public class Common {

	
	
	
	public int greyLevel(int rgb){
		return rgb & 0xFF;
	}
	
	public void apply_threshold(BufferedImage img, int threshold){
		
		for(int y=0; y<img.getHeight();y++){
			for(int x=0; x<img.getWidth();x++){
				
				if (this.greyLevel(img.getRGB(x, y)) > threshold){
					img.setRGB(x, y, 0xFFFFFF);
				}else{
					img.setRGB(x,y, 0x0);
				}
				
			}
		}
		
	}


}
