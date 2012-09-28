import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

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
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.image.BufferedImage;
import java.io.File;


public class GUI extends JFrame {

	private JPanel contentPane;
	
	//Exercise 1
	private Image1 image1 = new Image1();
	//Exercise 2
	private Regions regions = new Regions();
	//Exercise 4
	private RowCol rowcol = new RowCol();
	//Excercise 5
	private Morph morph = new Morph();
	//Exercise 6
	private Common c = new Common();
	private Otsu otsu = new Otsu("imag001.bmp");
	private Kittler kittler = new Kittler("imag001.bmp");
	private Sahoo sahoo = new Sahoo("imag001.bmp");
	
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
		setSize(new Dimension(820, 820));
		setTitle("Tarea 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 820);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
	
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0,0,700,700);
	
		contentPane.add(tabbedPane);
		
		/*###################################
		 * 
		 *  EXCERCISE 1 
		 * 
		 * ##################################
		 */
		
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
		
		
		/*###################################
		 * 
		 *  EXCERCISE 2
		 * 
		 * ##################################
		 */
		
		
		JPanel ex_2 = new JPanel();
		tabbedPane.addTab("Ejercicio 2",ex_2);
		ex_2.setLayout(null);
		
		JLabel lblNewLabel2 = new JLabel("Ejercicio 2: ");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(37, 24, 586, 15);
		ex_2.add(lblNewLabel2);
		
		//Image Container
		JPanel p2_ImgContainer = new JPanel();
		p2_ImgContainer.setBounds(50, 100, 300, 300);
		final JBinImage jimage2 = new JBinImage();
		jimage2.setBounds(new Rectangle(0, 0, 300, 300));
		jimage2.setImg(this.regions.imgA);
		p2_ImgContainer.add(jimage2);
		ex_2.add(p2_ImgContainer);
		
		Label label2 = new Label("Imagen");
		label2.setBounds(50, 73, 68, 21);
		ex_2.add(label2);
		
		JButton btnCargarImagenA = new JButton("Cargar Imagen A");
		btnCargarImagenA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				regions.reset();
				jimage2.setImg(regions.imgA);
				jimage2.repaint();
			}
		});
		btnCargarImagenA.setBounds(362, 107, 227, 25);
		ex_2.add(btnCargarImagenA);
		
		JButton btnCargarImagenB = new JButton("Cargar Imagen B");
		btnCargarImagenB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regions.reset();
				jimage2.setImg(regions.imgB);
				jimage2.repaint();
			}
		});
		btnCargarImagenB.setBounds(362, 144, 227, 25);
		ex_2.add(btnCargarImagenB);
		
		JButton btnEtiquetarComponentes = new JButton("Etiquetar Componentes");
		btnEtiquetarComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regions.mark_regions(jimage2.img);
				jimage2.repaint();
			}
		});
		btnEtiquetarComponentes.setBounds(362, 181, 227, 25);
		ex_2.add(btnEtiquetarComponentes);
		
		JLabel lblVerdeSimplementeConectadas = new JLabel("Verde: Simplemente Conectadas");
		lblVerdeSimplementeConectadas.setBounds(60, 413, 290, 15);
		ex_2.add(lblVerdeSimplementeConectadas);
		
		JLabel lblRojoMultiplemeneteConectadas = new JLabel("Rojo: Multiplemenete Conectadas");
		lblRojoMultiplemeneteConectadas.setBounds(60, 440, 290, 15);
		ex_2.add(lblRojoMultiplemeneteConectadas);
		
		
		
		/*###################################
		 * 
		 *  EXCERCISE 3
		 * 
		 * ##################################
		 */
		
		JPanel ex_3 = new JPanel();
		tabbedPane.addTab("Ejercicio 3",ex_3);
		ex_3.setLayout(null);
		
		
		/*###################################
		 * 
		 *  EXCERCISE 4
		 * 
		 * ##################################
		 */
		
		JPanel ex_4 = new JPanel();
		tabbedPane.addTab("Ejercicio 4",ex_4);
		ex_4.setLayout(null);
		
		
		JLabel lblNewLabel4 = new JLabel("Ejercicio 4: ");
		lblNewLabel4.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel4.setBounds(37, 24, 586, 15);
		ex_4.add(lblNewLabel4);
		
		//Image Container
		JPanel p4_ImgContainer = new JPanel();
		p4_ImgContainer.setBounds(50, 100, 300, 300);
		final JBinImage jimage4 = new JBinImage();
		jimage4.setBounds(new Rectangle(0, 0, 300, 300));
		jimage4.setImg(this.rowcol.pixels);
		p4_ImgContainer.add(jimage4);
		ex_4.add(p4_ImgContainer);
		
		Label label4 = new Label("Imagen");
		label4.setBounds(50, 73, 68, 21);
		ex_4.add(label4);
		
		JLabel lblPixelesConValor_1 = new JLabel("Pixeles con valor 1 en filas");
		lblPixelesConValor_1.setBounds(378, 100, 300, 15);
		ex_4.add(lblPixelesConValor_1);
		
		JLabel lblPixelesConValor_2 = new JLabel("Pixeles con valor 1 en columnas");
		lblPixelesConValor_2.setBounds(378, 159, 300, 15);
		ex_4.add(lblPixelesConValor_2);
		
		int[][]rc = this.rowcol.getRowCols();
		JLabel lbl_rows = new JLabel(Arrays.toString(rc[0]));
		lbl_rows.setBounds(388, 127, 323, 15);
		ex_4.add(lbl_rows);
		
		JLabel lbl_cols = new JLabel(Arrays.toString(rc[1]));
		lbl_cols.setBounds(388, 186, 323, 15);
		ex_4.add(lbl_cols);
		
		
		/*###################################
		 * 
		 *  EXCERCISE 5
		 * 
		 * ##################################
		 */
		
		JPanel ex_5 = new JPanel();
		tabbedPane.addTab("Ejercicio 5",ex_5);
		ex_5.setLayout(null);
		
		
		
		JLabel lblNewLabel5 = new JLabel("Ejercicio 5: ");
		lblNewLabel5.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel5.setBounds(37, 24, 586, 15);
		ex_5.add(lblNewLabel5);
		
		//Image Container
		JPanel p5_ImgContainer = new JPanel();
		p5_ImgContainer.setBounds(50, 100, 300, 300);
		final JBinImage jimage5 = new JBinImage();
		jimage5.setBounds(new Rectangle(0, 0, 300, 300));
		jimage5.setImg(this.morph.img);
		p5_ImgContainer.add(jimage5);
		ex_5.add(p5_ImgContainer);
		
		Label label5 = new Label("Imagen");
		label5.setBounds(50, 73, 68, 21);
		ex_5.add(label5);
		
		JButton btnSepararComponentes = new JButton("Erosion + Dilatacion");
		btnSepararComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jimage5.setImg(morph.split_objects());
				jimage5.repaint();
				
			}
		});
		btnSepararComponentes.setBounds(389, 100, 272, 25);
		ex_5.add(btnSepararComponentes);
		
		final JLabel lbl_comp5 = new JLabel("");
		lbl_comp5.setBounds(679, 145, 70, 15);
		ex_5.add(lbl_comp5);
		
		JButton btnContarComponentes = new JButton("Contar Componentes");
		btnContarComponentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbl_comp5.setText(morph.countObj(jimage5.img)+"");
				
			}
		});
		btnContarComponentes.setBounds(389, 140, 272, 25);
		ex_5.add(btnContarComponentes);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jimage5.setImg(morph.img);
				jimage5.repaint();
			}
		});
		btnReset.setBounds(389, 177, 272, 25);
		ex_5.add(btnReset);
		
		
		
		
		
		
		/*###################################
		 * 
		 *  EXCERCISE 6
		 * 
		 * ##################################
		 */
		
		JPanel ex_6 = new JPanel();
		tabbedPane.addTab("Ejercicio 6",ex_6);
		ex_6.setLayout(null);
		
		JLabel lblNewLabel6 = new JLabel("Ejercicio 6: ");
		lblNewLabel6.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel6.setBounds(37, 24, 586, 15);
		ex_6.add(lblNewLabel6);
		
		//Image Container
		JPanel p6_ImgContainer_1 = new JPanel();
		p6_ImgContainer_1.setBounds(50, 100, 300, 300);
		
		final ImageRender jimage6 = new ImageRender(c.readImage("imag001.bmp"));
		jimage6.setBounds(new Rectangle(0, 0, 300, 300));
	
		p6_ImgContainer_1.add(jimage6);
		ex_6.add(p6_ImgContainer_1);
		
		Label label6 = new Label("Imagen");
		label6.setBounds(50, 73, 68, 21);
		ex_6.add(label6);
		
		JPanel panel = new JPanel();
		panel.setBounds(393, 100, 300, 300);
		ex_6.add(panel);
		
		otsu.threshold();
		final ImageRender imageRender_Otsu = new ImageRender(otsu.img);
		imageRender_Otsu.setBounds(new Rectangle(0, 0, 300, 300));
		panel.add(imageRender_Otsu);
		
		Label label_1 = new Label("Otsu");
		label_1.setBounds(393, 73, 68, 21);
		ex_6.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(50, 439, 300, 300);
		ex_6.add(panel_1);
		
		kittler.threshold();
		final ImageRender imageRender_Kittler = new ImageRender(kittler.img);
		imageRender_Kittler.setBounds(new Rectangle(0, 0, 300, 300));
		panel_1.add(imageRender_Kittler);
		
		Label label_2 = new Label("Kittler");
		label_2.setBounds(50, 412, 68, 21);
		ex_6.add(label_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(393, 439, 300, 300);
		ex_6.add(panel_2);
		
		sahoo.threshold();
		final ImageRender imageRender_Sahoo = new ImageRender(sahoo.img);
		imageRender_Sahoo.setBounds(new Rectangle(0, 0, 300, 300));
		panel_2.add(imageRender_Sahoo);
		
		Label label_3 = new Label("Sahoo");
		label_3.setBounds(393, 412, 68, 21);
		ex_6.add(label_3);
		
		JButton btnCargarImagen = new JButton("Cargar Imagen");
		btnCargarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String filename = System.getProperty("user.dir");
				BmpFilter bmps = new BmpFilter();
				JFileChooser fc = new JFileChooser(new File(filename));
				fc.setFileFilter(bmps);

				fc.showOpenDialog(contentPane);
				File selFile = fc.getSelectedFile();
				String path = selFile.getAbsolutePath();
				
				jimage6.img = c.readImage(path);
				jimage6.repaint();
				
				otsu = new Otsu(path);
				otsu.threshold();
				imageRender_Otsu.img = otsu.img;
				imageRender_Otsu.repaint();
				
				kittler = new Kittler(path);
				kittler.threshold();
				imageRender_Kittler.img = kittler.img;
				imageRender_Kittler.repaint();
				

				sahoo = new Sahoo(path);
				sahoo.threshold();
				imageRender_Sahoo.img = sahoo.img;
				imageRender_Sahoo.repaint();
				
			}
		});
		btnCargarImagen.setBounds(577, 19, 182, 25);
		ex_6.add(btnCargarImagen);
		
		
		
		/*###################################
		 * 
		 *  EXCERCISE 7 y 8
		 * 
		 * ##################################
		 */
		
		JPanel ex_7 = new JPanel();
		tabbedPane.addTab("Ejercicio 7 y 8",ex_7);
		ex_7.setLayout(null);
		
		
		
	}
}

class BmpFilter extends FileFilter{
    @Override
public boolean accept(File f){
    return f.getName().toLowerCase().endsWith(".bmp")||f.isDirectory();
}
    @Override
    public String getDescription(){
        return "Bmp files (*.bmp)";
    }
}
