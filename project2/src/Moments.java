
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
	int working_region;
	public Moments(String image){
		
		this.c = new Common();
		this.img = this.c.readImage(image);
		if(c.get_average(img) > 100){
			this.c.reverse(this.img);
		}
		System.out.printf("Avg: %f\n", c.get_average(img));
		this.setup();
		
	}
	
	public void setup(){
		//TODO change later
		Kittler k= new Kittler(this.img);
		int threshold = k.calculate_threshold();
		this.c.apply_threshold(this.img, threshold);
		this.img_matrix = this.c.get_matrix_from_image(this.img);
		CountObjects co = new CountObjects();
		int total = co.count_using_labels(this.img_matrix);
		int max = 0;
		for(int i=2;i<total+2;i++){
			if(this.getArea(i)> max){
				this.working_region=i;
			}
		}
		
		
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
	
	public Point2D.Double getCentroid(int region){
		
		int M00 = this.getMoment(region, 0,0);
		int M10 = this.getMoment(region, 1,0);
		int M01 = this.getMoment(region, 0,1);
		
		double x = (double)M10 / (double)M00;
		double y = (double)M01 / (double)M00;
		
		return new Point2D.Double(x,y);
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Moments m = new Moments("images/obj5/IMAG0501.BMP");
		
		Point2D.Double center1 = m.getCentroid(m.working_region);

	
		
		
		System.out.println(m.getHu1(m.working_region));
		System.out.println(m.getHu2(m.working_region));

		ImageViewer viewer = new ImageViewer(m.img, "Centroide");
		viewer.render.marks.add(center1);

		
	}

}
