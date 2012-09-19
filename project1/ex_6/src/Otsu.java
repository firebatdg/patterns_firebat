
import net.sf.image4j.codec.bmp.BMPDecoder;
import java.io.*;
import java.awt.image.*;

public class Otsu {

	
	private BufferedImage img;
	private int threshold = 120;
	private Common c = new Common();
	private ImageViewer viewer;
	
	public Otsu(String image){
		
		
		try {
			this.img = BMPDecoder.read(new File("imag001.bmp"));
		} catch (IOException e) {}
		
		viewer = new ImageViewer(this.img);
		
	}
	
	
	public void threshold(){
		
		this.c.apply_threshold(this.img, this.threshold);
		this.viewer.render.repaint();
		
	}
	

	
	public static void main(String[] args) {
		
		Otsu o = new Otsu("imag001.bmp");
		o.threshold();

	}

}
