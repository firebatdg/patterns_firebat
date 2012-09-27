

import net.sf.image4j.codec.bmp.BMPDecoder;
import java.io.*;
import java.awt.image.*;

public class Kittler {

	
	public BufferedImage img;
	private int threshold = 0;
	private Common c = new Common();
	//private ImageViewer viewer;
	
	public Kittler(String image){
		
		
		try {
			this.img = BMPDecoder.read(new File("imag001.bmp"));
		} catch (IOException e) {}
		
		//viewer = new ImageViewer(this.img, "Metodo de Kittler");
		
	}
	
	
	
	private int p0(int k, int[] hist){
		int out = 0;
		for(int i=0;i<=k;i++){
			out += hist[i];
		}
		return out;
	}
	private int p1(int k, int[] hist){
		int out=0;
		for(int i=k+1;i<256;i++){
			out += hist[i];
		}
		return out;
	}
	
	private float mu_0(int k, int p0, int[] hist){
		float sum = 0;
		for(int i =0; i<=k; i++){
			sum += (float)(i * hist[i]);
		}
		
		return sum / (float)p0;
	}
	private float mu_1(int k, int p1, int[] hist){
		float sum = 0;
		for(int i =k+1; i<256; i++){
			sum += (float)(i * hist[i]);
		}
		
		return sum / (float)p1;
	}
	
	private float var_0(int k, int p0, float mu_0, int[] hist){
		float sum = 0;
		for(int i =0; i<=k; i++){
			sum += (float)(i - mu_0) * (float)(i - mu_0) * (float)hist[i];
		}
		return sum / (float)p0;
	}
	private float var_1(int k, int p1, float mu_1, int[] hist){
		float sum = 0;
		for(int i = k+1; i<256; i++){
			sum += (float)(i - mu_1) * (float)(i - mu_1) * (float)hist[i];
		}
		return sum / (float)p1;
	}
	
	private double J(int k, int p0, int p1, float var_0, float var_1){
		
		float sigma_0 = (float)Math.sqrt(var_0);
		float sigma_1 = (float)Math.sqrt(var_1);
		
		return (1 + 2.0 * (p0 * Math.log(sigma_0)+ p1 * Math.log(sigma_1)) - 2.0 * (p0 * Math.log(p0) + p1*Math.log(p1)));
		
	}
	
	public void calculate_threshold(){
		
		int[] hist = this.c.get_histogram(this.img);
		
		double min = Double.MAX_VALUE;
		
		for(int i=0;i<256;i++){
			
			int p_0 = this.p0(i, hist);
			int p_1 = this.p1(i, hist);
			
			float mu0 = this.mu_0(i, p_0, hist);
			float mu1 = this.mu_1(i, p_1, hist);
			
			float var0 = this.var_0(i, p_0, mu0, hist);
			float var1 = this.var_1(i, p_1, mu1, hist);
			
			double j = this.J(i, p_0, p_1, var0, var1);
			//System.out.println(j);
			
			if( j < min && j!=Double.NEGATIVE_INFINITY){
				this.threshold = i;
				min = j;
			}
			
			
		}
		
	}
	
	public void threshold(){
		this.calculate_threshold();
		System.out.println(this.threshold);
		this.c.apply_threshold(this.img, this.threshold);
		//this.viewer.render.repaint();
		
	}
	

	
	public static void main(String[] args) {
		
		Kittler k = new Kittler("imag001.bmp");
		k.threshold();
		ImageViewer viewer = new ImageViewer(k.img, "Metodo de Kittler");

	}

}
