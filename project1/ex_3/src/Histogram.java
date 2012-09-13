import java.util.Arrays;


public class Histogram {

	
	public Histogram(){
		
		
	}
	
	private float[] getData(int[][] img, int n){
		
		float[] values = new float[n];
		int[] count = new int[n];
		
		for(int i=0; i < img.length; i++){
			for(int j=0; j < img[i].length; j++){
				count[img[i][j]]++;
			}
		}
		
		
		for(int i=0;i<values.length;i++){
			values[i] = (float)count[i]/ (float)(img.length * img.length) * 100.0f;
		}
		
		return values;
	}
	
	
	public void printHistogram(int[][] img, int n){
		
		float[] data = this.getData(img, n);
		System.out.println(Arrays.toString(data));
		
		for(int i=10; i>= 0; i--){
			
			System.out.print("|");
			for( int j = 0; j<data.length; j++){
				if((int)data[j] / 5 > i){
					System.out.print("+");
				}else{
					System.out.print(" ");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
		
		for(int i=0; i<=n ; i++){
			System.out.print("--");
		}
		System.out.println();
		for(int i=0; i<n ; i++){
			System.out.printf("|%d",i);
		}
		System.out.println();
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		int[][] imgA = new int[][]{
				   {0,0,0,0,0,0,0,0,0,0},
				   {0,1,1,1,1,1,1,1,1,0},
				   {0,1,2,2,2,2,2,2,1,0},
				   {0,1,2,3,3,3,3,3,1,0},
				   {0,1,2,3,6,5,4,3,1,0},
				   {0,1,2,3,6,5,4,3,1,0},
				   {0,1,2,3,3,3,3,3,1,0},
				   {0,1,2,2,2,2,2,2,1,0},
				   {0,1,1,1,1,1,1,1,1,0},
				   {0,0,0,0,0,0,0,0,0,0}};
		
		Histogram h = new Histogram();
		h.printHistogram(imgA, 8);
		
	}

}
