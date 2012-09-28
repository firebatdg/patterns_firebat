import java.util.Arrays;


public class RowCol {


	int[][] pixels;
	public RowCol(){


		this.pixels  = new int[][]{
				{0,0,0,0,0,0,0,0,0,0},
				{0,1,1,1,0,0,0,0,0,0},
				{0,1,1,1,1,1,0,0,0,0},
				{0,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,0,0},
				{0,0,1,1,1,1,1,1,0,0},
				{0,0,1,1,1,1,1,1,0,0},
				{0,0,1,1,1,1,1,1,0,0},
				{0,0,0,0,1,1,1,0,0,0},
				{0,0,0,0,0,0,0,0,0,0}};

	}

	public int[][] getRowCols(){

		int[] rows = new int[this.pixels.length];
		int[] cols = new int[this.pixels[0].length];

		for(int i=0; i<this.pixels.length;i++){
			for(int j=0; j<this.pixels[i].length; j++){
				rows[j] += this.pixels[i][j];
				cols[i] += this.pixels[i][j];
			}
		}

		return new int[][]{rows,cols};
		//int i=0;
		//for (int[] a : this.pixels){
		//	System.out.printf("%s | [%d]\n", Arrays.toString(a),cols[i++]);
		//}
		//System.out.println("------------------------------------");
		//System.out.println(Arrays.toString(rows));
	}
	public static void main(String[] args) {
		
		RowCol test = new RowCol();
		//test.printRowCols();

	}

}
