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
		
		
		LM m = new LM(5,3);
	
		int[][] X = {{1,0,1,0,1},
					 {1,1,0,0,1},
					 {1,0,1,1,0}
					};
		
		int[][] Y = {{1,0,0},
					 {0,1,0},
					 {0,0,1}};
		
	
		m.learn(X[0],Y[0]);
		m.learn(X[1],Y[1]);
		m.learn(X[2],Y[2]);
		m.debug();
		System.out.println(Arrays.toString(m.retrieve(new int[]{1,0,1,0,1})));
	}
}
