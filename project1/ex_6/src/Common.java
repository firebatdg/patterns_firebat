import java.awt.image.*;

public class Common {

	
	
	
	public int greyLevel(int rgb){
		return rgb & 0xFF;
	}
	
	public void apply_threshold(BufferedImage img, int threshold){
		
		for(int y=0; y<img.getHeight();y++){
			for(int x=0; x<img.getWidth();x++){
				
				if (this.greyLevel(img.getRGB(x, y)) >= threshold){
					img.setRGB(x, y, 0xFFFFFF);
				}else{
					img.setRGB(x,y, 0x0);
				}
				
			}
		}
		
	}

	public int[] get_histogram(BufferedImage img){
		
		int[] out = new int[256];
		for(int y=0; y<img.getHeight();y++){
			for(int x=0; x<img.getWidth();x++){
				int rgb = img.getRGB(x, y);
				out[this.greyLevel(rgb)]++;
			}
		}
		
		return out;
	}
	
	public int[][] get_matrix_from_image(BufferedImage img){
		int[][] out = new int[img.getHeight()][img.getWidth()];
		
		for(int y=0; y<img.getHeight();y++){
			for(int x=0; x<img.getWidth();x++){
				
				
				out[y][x] = (img.getRGB(x, y) & 0xFF )== 0 ? 0 : 1;
			}
			
		}
		
		return out;
		
	}

}
