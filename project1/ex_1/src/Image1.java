import java.util.*;
public class Image1 {

	
	private int[][] pixels;
	
	public Image1(){
		
		//Ejercicio
		/*this.pixels  = new int[][]{
					   {0,0,0,0,0,0,0,0,0,0},
					   {0,1,1,1,0,0,0,0,0,0},
					   {0,1,1,1,1,1,1,1,0,0},
					   {0,1,1,1,1,1,1,1,0,0},
					   {0,0,1,1,1,0,0,1,1,0},
					   {0,0,1,1,1,0,0,1,1,0},
					   {0,0,0,1,1,1,1,1,1,0},
					   {0,0,0,1,1,1,1,1,1,0},
					   {0,0,0,0,0,0,0,0,0,0},
					   {0,0,0,0,0,0,0,0,0,0}};
		*/
		
		//Presentacion
		this.pixels = new int[][]{
								  {0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,1,1,1,1,1,1,1,0,0},
								  {0,0,1,1,1,1,1,1,1,0,0},
								  {0,0,1,1,0,0,0,1,1,0,0},
								  {0,0,1,1,0,0,0,1,1,0,0},
								  {0,1,1,1,1,1,1,1,1,1,0},
								  {0,1,1,1,1,1,1,1,1,1,0},
								  {0,0,0,1,1,1,1,1,0,0,0},
								  {0,0,0,1,1,1,1,1,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0}};
								  
		
		
	}
	
	public int count1s(){
		int count = 0;
		for (int i=0; i < this.pixels.length; i++){
			for (int j=0; j < this.pixels[i].length; j++){
				
				count += this.pixels[i][j];
			}
		}
	
		return count;
	}
	
	public int count8neighbor(int x, int y){
		//should only get called for pixels with 1 value
		//wont bother to check that
		int count = 0;
		for(int i=-1; i<2; i++){
			for(int j=-1; j<2; j++){
				try{
					count += this.pixels[y+i][x+j];
				}catch(ArrayIndexOutOfBoundsException e){}
			}
		}
		return count-1;
	}
	
	public int[][] getOutline(){
		
		//this doesnt work at all XD
		int[][] output = new int[this.pixels.length][this.pixels[0].length];
		
		for(int i=0; i<this.pixels.length; i++){
			for(int j=0; j<this.pixels[i].length; j++){
				if (this.pixels[i][j] == 1 && this.count8neighbor(i, j) != 8){
					output[i][j] = 1;
				}else{
					output[i][j] = 0;
				}
			}
		}
		return output;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Image1 img = new Image1();
		System.out.println(img.count1s());
		System.out.println(img.count8neighbor(1, 1));
		System.out.println(img.count8neighbor(2, 2));
		int[][] contorno = img.getOutline();
		for(int[] a:contorno){
			System.out.println(Arrays.toString(a));
		}
	}

}
