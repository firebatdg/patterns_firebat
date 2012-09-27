

import net.sf.image4j.codec.bmp.BMPDecoder;
import java.io.*;
import java.awt.image.*;

public class Sahoo {

	
	public BufferedImage img;
	private int threshold = 0;
	private Common c = new Common();
	//private ImageViewer viewer;
	
	public Sahoo(String image){
		
		
		try {
			this.img = BMPDecoder.read(new File("imag001.bmp"));
		} catch (IOException e) {}
		
	//	viewer = new ImageViewer(this.img, "Metodo de Kittler");
		
	}
	
	
	
	private int pDelta_0(int k, int[] hist){
		int out = 0;
		for(int i=0;i<=k;i++){
			out += hist[i];
		}
		return out;
	}
	private int pDelta_1(int k, int[] hist){
		int out=0;
		for(int i=k+1;i<256;i++){
			out += hist[i];
		}
		return out;
	}
	
	private double h_0(int k, int pDelta0, int[] hist, double alpha){
		double sum = 0;
		for(int i=0;i<=k;i++){
			sum += Math.pow(((double)hist[i]/(double)pDelta0),alpha);
		}
		return 1.0/(1.0-alpha) * Math.log(sum);
	}
	
	private double h_1(int k, int pDelta1, int[] hist, double alpha){
		double sum = 0;
		for(int i=k+1;i<256;i++){
			sum += Math.pow(((double)hist[i]/(double)pDelta1),alpha);
		}
		return 1.0/(1.0-alpha) * Math.log(sum);
	}
	
	
	
	

	private double J(int k, double alpha, int[] hist){
		

			int pDelta0 = this.pDelta_0(k, hist);
			int pDelta1 = this.pDelta_1(k, hist);
			
			double h0 = this.h_0(k, pDelta0, hist, alpha);
			double h1 = this.h_1(k, pDelta1, hist, alpha);
			
			
			return h0 + h1;
			
		
	}
	
	public void calculate_threshold(){
		
		int[] hist = this.c.get_histogram(this.img);
		
		double max = Double.MIN_VALUE;
		
		for(int i=0;i<256;i++){
			
		
			
			double j = this.J(i, 0.1, hist);
			//System.out.println(j);
			
			if( j > max){
				this.threshold = i;
				max = j;
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
		
		Sahoo k = new Sahoo("imag001.bmp");
		k.threshold();
		ImageViewer viewer = new ImageViewer(k.img, "Metodo de Sahoo");

	}

}
