
import java.io.*;
import java.awt.geom.Point2D;
import java.awt.image.*;

public class Moments {

	/**
	 * @param args
	 */
	
	
	public BufferedImage img;
	private Common c;
	int[][] img_matrix;
	public Moments(String image){
		
		this.c = new Common();
		this.img = this.c.readImage(image);
		this.setup();
		
	}
	
	public void setup(){
		//TODO change later
		Otsu o = new Otsu(this.img);
		int threshold = o.calculate_threshold();
		this.c.apply_threshold(this.img, threshold);
		this.img_matrix = this.c.get_matrix_from_image(this.img);
		CountObjects co = new CountObjects();
		co.count_using_labels(this.img_matrix);
	}
	
	private int f(int n){
		if (n == 0){
			return 1;
		}
		return n*f(n-1);
	}
	
	private int combinations(int n, int k){
		return f(n)/ (f(k) * f(n-k));
	}
	
	
	public int getArea(){
		return this.getArea(1);
	}
	public int getArea(int region){
		
		return this.getMoment(region,0,0);
		
	}
	
	public int getMoment(int powx, int powy){
		return this.getMoment(1,powx,powy);
	}
	
	public int getMoment(int region, int powx, int powy){
		
		int sum = 0;
		for(int i=0;i<this.img_matrix.length;i++){
			for(int j=0;j<this.img_matrix[i].length; j++){
				if (this.img_matrix[i][j] == region){
					
					sum += Math.pow(i,powy) * Math.pow(j, powx);
				}
			}
		}
		
		return sum;
	}
	
	public double getEta(int region, int powx, int powy){
		
		double u00 = this.getMu(region, 0,0);
		double uxy = this.getMu(region, powx,powy);
		
		return uxy / Math.pow(u00, (1.0 + (powx+powy)/2.0));
	}
	public double getMu(int region, int powx, int powy){
		Point2D.Double center = this.getCentroid(region);
		
		int x =(int) center.x;
		int y = (int)center.y;
		
		int sum = 0;
		for(int i=0;i<this.img_matrix.length;i++){
			for(int j=0;j<this.img_matrix[i].length; j++){
				if (this.img_matrix[i][j] == region){
					
					sum += Math.pow(j - x, powx) * Math.pow(i - y, powy);
				}
			}
		}
		
		return sum;
		
	}
	
	
	public double getHu1(int region){
		
		return this.getEta(region, 2,0) + this.getEta(region,0,2);
	}
	public double getHu2(int region){
		
		double eta20 = this.getEta(region,2,0);
		double eta02 = this.getEta(region,0,2);
		double eta11 = this.getEta(region, 1,1);
		return Math.pow( eta20 - eta02 ,2) + Math.pow( 2 * eta11, 2);
	}
	
	public double getFlusser1(int region){
		
		double mu00 = this.getMu(region, 0,0);
		double mu20 = this.getMu(region, 2,0);
		double mu02 = this.getMu(region, 0,2);
		double mu11 = this.getMu(region, 1,1);
		
		return (mu20 * mu02 - mu11) / Math.pow(mu00, 4);
		
	}
	
	public double getFlusser2(int region){
		
		double mu00 = this.getMu(region, 0,0);
		double mu11 = this.getMu(region, 1,1);
		double mu12 = this.getMu(region, 1,2);
		
		double mu20 = this.getMu(region, 2,0);
		double mu02 = this.getMu(region, 0,2);
		double mu21 = this.getMu(region, 2,1);
		
		double mu30 = this.getMu(region, 3,0);
		double mu03 = this.getMu(region, 0,3);
		
		return (mu30 * mu30 * mu03 * mu03 - 6 * mu30 * mu21 * mu12 * mu03 + 4 * mu30*mu12*mu12 + 4*mu21*mu21*mu03  - 3*mu21*mu21*mu12*mu12)/Math.pow(mu00, 10);
		
		
	}
	
	
	public double getFlusser3(int region){
		
		double mu00 = this.getMu(region, 0,0);
		double mu11 = this.getMu(region, 1,1);
		double mu12 = this.getMu(region, 1,2);
		
		double mu20 = this.getMu(region, 2,0);
		double mu02 = this.getMu(region, 0,2);
		double mu21 = this.getMu(region, 2,1);
		
		double mu30 = this.getMu(region, 3,0);
		double mu03 = this.getMu(region, 0,3);
		
		return (mu20 * (mu21 * mu03 - mu12 * mu12) - mu11 * (mu30*mu03 - mu21 * mu12) + mu02 * (mu30*mu12 - mu21 * mu21)) / Math.pow(mu00, 7);
		
		
		
		
	}
	
	public double getFlusser4(int region){
		
		double mu00 = this.getMu(region, 0,0);
		double mu11 = this.getMu(region, 1,1);
		double mu12 = this.getMu(region, 1,2);
		
		double mu20 = this.getMu(region, 2,0);
		double mu02 = this.getMu(region, 0,2);
		double mu21 = this.getMu(region, 2,1);
		
		double mu30 = this.getMu(region, 3,0);
		double mu03 = this.getMu(region, 0,3);
		
		return (Math.pow(mu20, 3) * Math.pow(mu03, 2) - 6 * mu30 * mu11 * mu12 * mu03 
				-6 * Math.pow(mu20, 2) * mu02 * mu21 * mu03 + 9 * Math.pow(mu20, 2) * mu02 * Math.pow(mu12, 2)
				+ 12 * mu20 * Math.pow(mu11, 2) * mu21 * mu03 + 6 * mu20 * mu11 * mu02 * mu30 * mu03
				-18 * mu20 * mu11 * mu02 * mu21 * mu12 - 8 * Math.pow(mu11, 3) * mu30 * mu03 
				-6 * mu20 * Math.pow(mu02, 2) * mu30 * mu12 + 9 * mu20 * Math.pow(mu02, 2 ) * Math.pow(mu21, 2)
				+12 * Math.pow(mu11,2) * mu02 * mu30 * mu12 -6 * mu11 * Math.pow(mu02, 2) * mu30 * mu21
				+ Math.pow(mu02,3) * Math.pow(mu30, 2))/Math.pow(mu00, 11);
				
		
		
		
		
	}
	
	
	
	
	public Point2D.Double getCentroid(int region){
		
		int M00 = this.getMoment(region, 0,0);
		int M10 = this.getMoment(region, 1,0);
		int M01 = this.getMoment(region, 0,1);
		
		int x = M10 / M00;
		int y = M01 / M00;
		
		return new Point2D.Double(x,y);
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Moments m = new Moments("imag001.bmp");
		Point2D.Double center1 = m.getCentroid(2);
		Point2D.Double center2 = m.getCentroid(3);
	
		
		
		System.out.println(m.getHu1(2));
		System.out.println(m.getHu1(3));
		System.out.println(m.getHu2(2));
		System.out.println(m.getHu2(3));
		System.out.println(m.getFlusser4(2));
		System.out.println(m.getFlusser4(3));
		ImageViewer viewer = new ImageViewer(m.img, "Centroide");
		viewer.render.marks.add(center1);
		viewer.render.marks.add(center2);
		
	}

}
