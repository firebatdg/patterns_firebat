
import net.sf.image4j.codec.bmp.BMPDecoder;
import java.io.*;
import java.awt.image.*;

public class Otsu {

	
	public BufferedImage img;
	private int threshold = 0;
	private Common c = new Common();
//	private ImageViewer viewer;
	
	public Otsu(String image){
		
		
		try {
			this.img = BMPDecoder.read(new File(image));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//viewer = new ImageViewer(this.img, "Metodo de Otsu");
		
	}
	public Otsu(BufferedImage img){
		this.img = img;
	}

	
	public int calculate_threshold(){
		
		int[] hist = this.c.get_histogram(this.img);
		
		float max = 0;
		int weight_background = 0;
		int weight_foreground = 0;
		int sum = 0;
		float sum_background = 0.0f;
		
		for(int i=0;i<256;i++){
			sum += i * hist[i];
		}
		
		for(int i=0;i<256;i++){
			weight_background += hist[i];
			if(weight_background == 0) continue;
			weight_foreground = img.getHeight() * img.getWidth() - weight_background;
			if(weight_foreground == 0) break;
			
			sum_background += (float) (i * hist[i]);
			
			float mean_background = sum_background / weight_background;
			float mean_foreground = (sum - sum_background) / weight_foreground;
			
			float tmp = (float)weight_background * (float)weight_foreground  * (mean_background - mean_foreground) * (mean_background - mean_foreground);
			
			if(tmp > max){
				max = tmp;
				this.threshold = i;
			}
			
			
		}
		return this.threshold;
	}
	
	public void threshold(){
		this.calculate_threshold();
		System.out.println(this.threshold);
		this.c.apply_threshold(this.img, this.threshold);
		//this.viewer.render.repaint();
		
	}
	

	
	public static void main(String[] args) {
		
		//Otsu o = new Otsu("green.bmp");
		Common c = new Common();
		//c.makeGreyScale(o.img);
	//	System.out.println(o.img.getHeight());
	//	o.threshold();
		
		ImageViewer viewer = new ImageViewer(c.readImage("green.bmp"), "Metodo de Otsu");
		
	}

}
