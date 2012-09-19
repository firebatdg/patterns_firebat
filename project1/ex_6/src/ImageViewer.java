import javax.swing.*;
import net.sf.image4j.codec.bmp.BMPDecoder;
import java.awt.*;
import java.io.*;
import java.awt.image.*;



public class ImageViewer {

	
	private JFrame frame;
	private BufferedImage img;
	public ImageRender render;
	
	public ImageViewer(BufferedImage img){
		
		
		
		this.frame = new JFrame("Ejercicio 1");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		this.img = img;
		this.render = new ImageRender(this.img);
		
		this.frame.getContentPane().add(render, BorderLayout.CENTER);
		
		
		this.frame.setSize(600,600);
		this.frame.setVisible(true);
		
		

		
		System.out.println("Hello!");
	
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		
		

	}

}
