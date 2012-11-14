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
	
	public String getColor(){
		
		Common c = new Common();
		c.makeGreyScale(this.img);
		Otsu o = new Otsu(this.img);
		o.threshold();
		
		CountObjects co = new CountObjects();
		int[][] matrix = c.get_matrix_from_image(this.img);
		int total = co.count_using_labels(matrix);
		
		Moments img_moment = new Moments(this.img, matrix);
		int working_region = -1;
		int max = 0;
		for(int i=2;i<total+2;i++){
			if(img_moment.getArea(i)> max){
				working_region=i;
				max = img_moment.getArea(i);
			}
		}
		
		this.center1 = img_moment.getCentroid(working_region);
		return this.getHue((int)this.center1.x, (int)this.center1.y) + "";
		
		
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
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ColorTag c = new ColorTag("apple.bmp");
		System.out.println("Hue: " + c.getColor());
		ImageViewer viewer = new ImageViewer(c.color_img, "Centroide");
		viewer.render.marks.add(c.center1);
		
	}

}
