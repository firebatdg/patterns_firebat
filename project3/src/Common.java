import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import net.sf.image4j.codec.bmp.BMPDecoder;

public class Common {

	
	public void printImage(int[][] img){
		for(int[] a:img){
			System.out.println(Arrays.toString(a));
		}
	}
	
	public BufferedImage readImage(String image){
		try {
			return BMPDecoder.read(new File(image));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public int greyLevel(int rgb){
		return rgb & 0xFF;
	}
	
	public int colorToGrey(int rgb){
		return ((rgb & 0xFF) + ((rgb & 0xFF00) >> 8) + ((rgb & 0xFF0000) >>16))/3;
		//return rgb & 0xFF;
	}
	
	
	public int[] getBinaryFlat(BufferedImage img){
		
		int size = img.getHeight() * img.getWidth();
		int width = img.getWidth();
		int[] output = new int[size];
		
		for(int y=0;y<img.getHeight();y++){
			for(int x=0;x<img.getWidth(); x++){
				output[y* width + x] = (img.getRGB(x, y) & 0xFF) == 0xFF ? 1 : 0;
			}
		}
		
		return output;
		
		
	}
	
	
	public void addNoise(BufferedImage img, int percent){
		
		double p = (double)percent / 100.0;
		
		for(int y=0;y<img.getHeight();y++){
			for(int x=0;x<img.getWidth(); x++){
				if (Math.random() <= p){
					int color = Math.random() > 0.5 ? 0xFFFFFF : 0;
					img.setRGB(x, y, color);
				}
			}
		}
	}
	
	public void makeGreyScale(BufferedImage img){
		for(int y=0; y<img.getHeight();y++){
			for(int x=0; x<img.getWidth();x++){
				int old_rgb = this.colorToGrey(img.getRGB(x, y));
				int new_rgb = old_rgb  + (old_rgb <<8) + (old_rgb<<16);
				img.setRGB(x, y, new_rgb);
			}
		}
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
				int rgb = this.greyLevel(img.getRGB(x, y));
				out[this.greyLevel(rgb)]++;
			}
		}
		
		return out;
	}
	
	public double get_average(BufferedImage img){
		
		int[] hist = this.get_histogram(img);
		
		double out=0;
		int total= img.getHeight() * img.getWidth();
		for(int i=0;i<hist.length;i++){
			
			out += i * (double)hist[i]/(double)total; 
		}
		
		return out;
	}
	
	public void reverse(BufferedImage img){
		
		for(int y=0;y<img.getHeight();y++){
			for(int x=0;x<img.getWidth();x++){
				img.setRGB(x, y, 255 - img.getRGB(x, y));
			}
		}
		
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
