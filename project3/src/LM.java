import java.awt.image.BufferedImage;
import java.util.Arrays;




public class LM{

	private int vectorSize;
	private int outputSize;
	private int[][] lm;
	public LM(int vectorSize, int outputSize){

		this.vectorSize = vectorSize;
		this.outputSize = outputSize;

		this.lm = new int[outputSize][vectorSize];

	}

	public void learn(int[] input, int[] expected){

		for(int i=0; i < outputSize; i++ ){
			for(int j=0; j< vectorSize; j++){

				if(input[j] == 1 && expected[i] == 1){
					this.lm[i][j]++;
				}else if (input[j] == 0 && expected[i] == 1){
					this.lm[i][j]--;
				}

			}
		}

	}

	public int[] retrieve(int[] input){

		int[] vector = this.multiply(input);
		int max = Integer.MIN_VALUE;
		
		for(int x: vector){
			if (x > max){
				max = x;
			}
		}
		
		for(int i=0;i<vector.length;i++){
			if(vector[i]==max){
				vector[i] = 1;
			}else{
				vector[i] = 0;
			}
		}
		return vector;
	}

	private int[] multiply(int[] input){

		int[] output = new int[outputSize];

		for(int k=0;k<this.outputSize;k++){
			
				for(int i=0; i< this.vectorSize; i++){
					output[k] += this.lm[k][i] * input[i];
				}
			
		}
		return output;
	}

	public void debug(){
		
		for(int[] a : this.lm){
			System.out.println(Arrays.toString(a));
		}
		
	}

	public static void main(String[] args){
		
		
		
	
//		int[][] X = {{1,0,1,0,1},
//					 {1,1,0,0,1},
//					 {1,0,1,1,0}
//					};
//		
//		int[][] Y = {{1,0,0},
//					 {0,1,0},
//					 {0,0,1}};
//		
//	
//		m.learn(X[0],Y[0]);
//		m.learn(X[1],Y[1]);
//		m.learn(X[2],Y[2]);
//		m.debug();
		
		
		Common c = new Common();
		BufferedImage img1 = c.readImage("1.bmp");
		BufferedImage img2 = c.readImage("2.bmp");
		BufferedImage img3 = c.readImage("3.bmp");
		BufferedImage img4 = c.readImage("4.bmp");
		BufferedImage img5 = c.readImage("5.bmp");
		
		
		int[][] Y = {{1,0,0,0,0},
				 	 {0,1,0,0,0},
				 	 {0,0,1,0,0},
				 	 {0,0,0,1,0},
				 	 {0,0,0,0,1}
				};
		
		int[] image1 = c.getBinaryFlat(img1);
		int[] image2 = c.getBinaryFlat(img2);
		int[] image3 = c.getBinaryFlat(img3);
		int[] image4 = c.getBinaryFlat(img4);
		int[] image5 = c.getBinaryFlat(img5);
		LM m = new LM(image1.length,5);
		
		m.learn(image1, Y[0]);
		m.learn(image2, Y[1]);
		m.learn(image3, Y[2]);
		m.learn(image4, Y[3]);
		m.learn(image5, Y[4]);
		
		c.addNoise(img3, 77);
		image3 = c.getBinaryFlat(img3);
		ImageViewer v = new ImageViewer(img3, "Noise");
		
		
		System.out.println(Arrays.toString(m.retrieve(image3)));
	}
}
