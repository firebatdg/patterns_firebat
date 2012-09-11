import javax.swing.*;
import java.awt.*;

public class Ui {

	
	private JFrame frame;
	
	public Ui(){
		
		
		
		this.frame = new JFrame("Ejercicio 1");
		
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().add(new Label(), BorderLayout.CENTER);

		
		this.frame.setSize(600,600);
		this.frame.setVisible(true);
		

		
		System.out.println("Hello!");
	
	}
	
	
	public static void main(String[] args) {
		
		Ui test = new Ui();
		System.out.println("Done");

	}

}
