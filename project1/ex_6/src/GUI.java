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
import java.awt.Rectangle;
import java.awt.Label;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;


public class GUI extends JFrame {

	private JPanel contentPane;
	
	//Exercise 1
	private Image1 image1 = new Image1();
	
	
	
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
	
		contentPane.add(tabbedPane);
		
		//Excercise 1
		JPanel ex_1 = new JPanel();
		tabbedPane.addTab("Ejercicio 1",ex_1);
		ex_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ejercicio 1: ");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(37, 24, 586, 15);
		ex_1.add(lblNewLabel);
		
		//Image Container
		JPanel p1_ImgContainer = new JPanel();
		p1_ImgContainer.setBounds(50, 100, 300, 300);
		final JBinImage jimage1 = new JBinImage();
		jimage1.setBounds(new Rectangle(0, 0, 300, 300));
		jimage1.setImg(this.image1.pixels);
		p1_ImgContainer.add(jimage1);
		ex_1.add(p1_ImgContainer);
		
		Label label = new Label("Imagen");
		label.setBounds(50, 73, 68, 21);
		ex_1.add(label);
		
		/*
		 *  Pixeles Valor 1
		 * 
		 */
		
		JLabel lblPixelesConValor = new JLabel("Pixeles con Valor 1");
		lblPixelesConValor.setBounds(409, 107, 177, 15);
		ex_1.add(lblPixelesConValor);
		
		
		JLabel lbl_ones = new JLabel("New label");
		lbl_ones.setText(this.image1.count1s()+"");
		lbl_ones.setBounds(419, 134, 70, 15);
		ex_1.add(lbl_ones);
		
		/*
		 *  Vecindad 8
		 */
		
		JLabel lblVecindadPara = new JLabel("Vecindad 8 para un pixel (x,y)");
		lblVecindadPara.setBounds(409, 174, 214, 15);
		ex_1.add(lblVecindadPara);
		
		final JLabel lbl_vecindad8 = new JLabel("New label");
		lbl_vecindad8.setBounds(419, 233, 257, 15);
		ex_1.add(lbl_vecindad8);
		
		String[] items1x = new String[this.image1.pixels[0].length];
		for(int i=0;i<items1x.length;i++){
			items1x[i]=i+"";
		}
		final JComboBox cbo_1_x = new JComboBox(items1x);
		cbo_1_x.setBounds(419, 197, 42, 24);
		ex_1.add(cbo_1_x);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(469, 201, 70, 15);
		ex_1.add(lblX);
		
		String[] items1y = new String[this.image1.pixels.length];
		for(int i=0;i<items1y.length;i++){
			items1y[i]=i+"";
		}
		final JComboBox cbo_1_y = new JComboBox(items1y);
		cbo_1_y.setBounds(490, 197, 42, 24);
		ex_1.add(cbo_1_y);
		
		JLabel lblY = new JLabel("Y");
		lblY.setBounds(539, 201, 70, 15);
		ex_1.add(lblY);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = cbo_1_x.getSelectedIndex();
				int y = cbo_1_y.getSelectedIndex();
				if(image1.pixels[y][x]!=1){
					lbl_vecindad8.setText("El pixel seleccionado no es 1");
				}else{
					lbl_vecindad8.setText(image1.count8neighbor(x, y) + "");
				}
			}
		});
		btnMostrar.setBounds(559, 197, 117, 25);
		ex_1.add(btnMostrar);
		
		/*
		 *  Contorno
		 * 
		 */
		
		JLabel lblContornoDeLa = new JLabel("Contorno de la Imagen");
		lblContornoDeLa.setBounds(409, 281, 241, 15);
		ex_1.add(lblContornoDeLa);
		
		JButton button = new JButton("Mostrar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				jimage1.setImg(image1.getOutline());
				jimage1.repaint();
			}
		});
		button.setBounds(410, 308, 117, 25);
		ex_1.add(button);
		
		JButton btnReestablecer = new JButton("Reset");
		btnReestablecer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jimage1.setImg(image1.pixels);
				jimage1.repaint();
			}
		});
		btnReestablecer.setBounds(533, 308, 117, 25);
		ex_1.add(btnReestablecer);
		
		
		JPanel ex_2 = new JPanel();
		tabbedPane.addTab("Ejercicio 2",ex_2);
	}
	
}
