


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
				
				if(input[i] == 1 && expected[j] == 1){
					this.lm[i][j]++;
				}else if (input[i] == 0 && expected[j] == 1){
					this.lm[i][j]--;
				}
				
			}
		}
		
	}
	
	public int[] retrieve(int[] input){
		
		
		return null;
	}
	
	private int[][] multiply()
	
	
	

}
