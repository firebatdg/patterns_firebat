

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
			this.img = BMPDecoder.read(new File(image));
		} catch (IOException e) {}
		
		//viewer = new ImageViewer(this.img, "Metodo de Kittler");
		
	}
	public Kittler(BufferedImage img){
		this.img = img;
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
	
	private double mu_0(int k, int p0, int[] hist){
		double sum = 0;
		for(int i =0; i<=k; i++){
			sum += (double)(i * hist[i]);
		}
		
		return sum / (double)p0;
	}
	private double mu_1(int k, int p1, int[] hist){
		double sum = 0;
		for(int i =k+1; i<256; i++){
			sum += (double)(i * hist[i]);
		}
		
		return sum / (double)p1;
	}
	
	private double var_0(int k, int p0, double mu_0, int[] hist){
		double sum = 0;
		for(int i =0; i<=k; i++){
			sum += (double)(i - mu_0) * (double)(i - mu_0) * (double)hist[i];
		}
		return sum / (double)p0;
	}
	private double var_1(int k, int p1, double mu_1, int[] hist){
		double sum = 0;
		for(int i = k+1; i<256; i++){
			sum += (double)(i - mu_1) * (double)(i - mu_1) * (double)hist[i];
		}
		return sum / (double)p1;
	}
	
	private double J(int k, int p0, int p1, double var_0, double var_1){
		
		double sigma_0 = (double)Math.sqrt(var_0);
		double sigma_1 = (double)Math.sqrt(var_1);
		
		return (1 + 2.0 * (p0 * Math.log(sigma_0)+ p1 * Math.log(sigma_1)) - 2.0 * (p0 * Math.log(p0) + p1*Math.log(p1)));
		
	}
	
	public int calculate_threshold(){
		
		int[] hist = this.c.get_histogram(this.img);
		
		double min = Double.MAX_VALUE;
		
		for(int i=0;i<256;i++){
			
			int p_0 = this.p0(i, hist);
			int p_1 = this.p1(i, hist);
			
			double mu0 = this.mu_0(i, p_0, hist);
			double mu1 = this.mu_1(i, p_1, hist);
			
			double var0 = this.var_0(i, p_0, mu0, hist);
			double var1 = this.var_1(i, p_1, mu1, hist);
			
			double j = this.J(i, p_0, p_1, var0, var1);
			//System.out.println(j);
			
			if( j < min && j!=Double.NEGATIVE_INFINITY){
				this.threshold = i;
				min = j;
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
		
		Kittler k = new Kittler("green.bmp");
		k.threshold();
		ImageViewer viewer = new ImageViewer(k.img, "Metodo de Kittler");

	}

}
