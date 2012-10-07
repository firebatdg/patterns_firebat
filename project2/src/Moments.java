
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
	
	
	public int getArea(){
		return this.getArea(1);
	}
	public int getArea(int region){
		
		return this.getMoment(1,0,0);
		
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
	
	public Point2D.Double getCentroid(int region){
		
		int u00 = this.getMoment(region, 0,0);
		int u10 = this.getMoment(region, 1,0);
		int u01 = this.getMoment(region, 0,1);
		
		int x = u10 / u00;
		int y = u01 / u00;
		
		return new Point2D.Double(x,y);
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Moments m = new Moments("imag001.bmp");
		Point2D.Double center1 = m.getCentroid(2);
		Point2D.Double center2 = m.getCentroid(3);
		
		ImageViewer viewer = new ImageViewer(m.img, "Centroide");
		viewer.render.marks.add(center1);
		viewer.render.marks.add(center2);
		
	}

}
