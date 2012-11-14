import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import net.sf.image4j.codec.bmp.BMPDecoder;
public class ColorTag {


	public BufferedImage img;
	public BufferedImage color_img;
	public Point2D.Double center1;
	public ColorTag(String filename){
		try {
			this.img = BMPDecoder.read(new File(filename));
			this.color_img = BMPDecoder.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public double getColor(){
		
		Common c = new Common();
		c.makeGreyScale(this.img);
		if(c.get_average(img) > 110){
			c.reverse(this.img);
		}
		Kittler k = new Kittler(this.img);
		k.threshold();
		
		CountObjects co = new CountObjects();
		int[][] matrix = c.get_matrix_from_image(this.img);
		int total = co.count_using_labels(matrix);
		
		System.out.println("Total: " + total);
		
		Moments img_moment = new Moments(this.img, matrix);
		int working_region = -100;
		int max = -100;
		for(int i=2;i<total+2;i++){
			int area = img_moment.getArea(i);
			System.out.printf("Max: %d region: %d\n", area, i);
			if(img_moment.getArea(i)> max){
				working_region=i;
				max = img_moment.getArea(i);
			}
		}
		System.out.println("WR: " + working_region);
		//buscar un pixel de la region
		for(int y=0;y<matrix.length;y++){
			for(int x=0;x<matrix[y].length;x++){
				if (matrix[y][x] == working_region){
					this.center1 = new Point2D.Double(x,y);
					break;
				}
			
			}
			if(this.center1 != null){
				break;
			}
		}
		this.center1 = img_moment.getCentroid(working_region);
		System.out.println(this.getSaturation((int)this.center1.x, (int)this.center1.y));
		return this.getHue((int)this.center1.x, (int)this.center1.y);
		
		
	}
	
	public double getHue(int x, int y){
		
		int rgb = this.color_img.getRGB(x,y);
		int r = (rgb & 0xFF0000) >> 16;
		int g = (rgb & 0xFF00) >> 8;
		int b = (rgb & 0xFF);
		
		if(r >= g && g >=b){
			return 60.0 * (g-b)/(r-b);
		}else if(g >r && r >=b){
			return 60.0 * (2 - (r-b)/(g-b));
		}else if (g >=b && b > r){
			return 60.0 * (2 + (b-r)/(g-r));
		}else if (b >g && g> r){
			return 60.0 * (4 -(g-r)/(b-r));
		}else if(b >r && r >=g){
			return 60.0 * (4 + (r-g)/(b-g));
		}else if(r >= b && b > g){
			return 60.0 * (6 - (b-g)/(r-g));
		}else{
			return -1;
		}
		
	}
	
	public double getSaturation(int x, int y){
		
		int rgb = new Common().greyLevel(this.color_img.getRGB(x, y)) & 0xFF;
		return rgb;//(rgb > 100) ? 1.0 : 0.0;
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColorTag c = new ColorTag("banana.bmp");
		System.out.println("Hue: " + c.getColor());
		ImageViewer viewer = new ImageViewer(c.img, "Centroide");
		viewer.render.marks.add(c.center1);
		
	}

}
