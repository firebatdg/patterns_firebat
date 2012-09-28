import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;


public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setSize(new Dimension(800, 800));
		setTitle("Tarea 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
	
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0,0,700,700);
		
		JPanel ex_1 = new JPanel();
		tabbedPane.addTab("Ejercicio 1",ex_1);
		ex_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ejercicio 1: ");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(37, 24, 586, 15);
		ex_1.add(lblNewLabel);
		
		JPanel p1_ImgContainer = new JPanel();
		p1_ImgContainer.setBounds(100, 100, -169, 153);
		ex_1.add(p1_ImgContainer);
		JPanel ex_2 = new JPanel();
		tabbedPane.addTab("Ejercicio 2",ex_2);
	
		contentPane.add(tabbedPane);
	}
}
