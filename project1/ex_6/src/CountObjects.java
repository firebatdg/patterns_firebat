import java.awt.image.BufferedImage;
import java.util.Arrays;


public class CountObjects {

	/**
	 * @param args
	 */
	
	public int[][] GetBinaryMatrix(String filename, String threshold_method){
		
		BufferedImage img;
		
		if(threshold_method == "otsu"){
			Otsu o = new Otsu(filename);
			o.threshold();
			img = o.img;
			
		}else if(threshold_method == "kittler"){
			
			Kittler k = new Kittler(filename);
			k.threshold();
			img = k.img;
			
		}else if (threshold_method == "sahoo"){
			
			Sahoo s = new Sahoo(filename);
			s.threshold();
			img = s.img;
			
		}else{
			return new int[][]{{-1}};
		}
		
		Common c = new Common();
		int[][] matrix = c.get_matrix_from_image(img);
		return matrix;
		
	}
	
	public int count_using_tetrapixels(int[][] matrix){
		
		//Metodo del ejercicio 5
		Morph m = new Morph();
		return m.countObj(matrix);
	}
	
	public int count_using_labels(int[][] matrix){
		
		Regions r = new Regions();
		int current_label = 2;
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[i].length;j++){
				if (matrix[i][j] == 1){
					r.flood_fill(matrix, j, i, 1, current_label++);
				}
			}
		}
		return current_label-2;
		
		
	}
	
	
	
	public static void main(String[] args) {
		CountObjects counter = new CountObjects();
		Common c= new Common();
		int[][] matrixOtsu = counter.GetBinaryMatrix("imag103.bmp", "otsu");
		//c.printImage(matrixOtsu);
		

		int[][] matrixKittler = counter.GetBinaryMatrix("imag103.bmp", "kittler");
	//	int[][] matrixSahoo = counter.GetBinaryMatrix("imag001.bmp", "sahoo");
		
		System.out.println("Number of Objects");
		System.out.println(counter.count_using_tetrapixels(matrixKittler));
		System.out.println(counter.count_using_labels(matrixOtsu));
		
		
	}

}
