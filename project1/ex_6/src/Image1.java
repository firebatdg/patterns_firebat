import java.util.*;
public class Image1 {

	
	public int[][] pixels;
	
	public Image1(){
		
		//Ejercicio
		this.pixels  = new int[][]{
					   {0,0,0,0,0,0,0,0,0,0},
					   {0,1,1,1,0,0,0,0,0,0},
					   {0,1,1,1,1,1,0,0,0,0},
					   {0,1,1,1,1,1,1,1,0,0},
					   {0,0,1,1,1,1,1,1,0,0},
					   {0,0,1,1,0,0,1,1,0,0},
					   {0,0,1,1,0,0,1,1,0,0},
					   {0,0,1,1,1,1,1,1,0,0},
					   {0,0,0,0,1,1,1,1,0,0},
					   {0,0,0,0,0,0,0,0,0,0}};
		
		
		//Presentacion
		/*this.pixels = new int[][]{
								  {0,0,0,0,0,0,0,0,0,0,0},
								  {0,0,1,1,1,1,1,1,1,0,0},
								  {0,0,1,1,1,1,1,1,1,0,0},
								  {0,0,1,1,0,0,0,1,1,0,0},
								  {0,0,1,1,0,0,0,1,1,0,0},
								  {0,1,1,1,1,1,1,1,1,1,0},
								  {0,1,1,1,1,1,1,1,1,1,0},
								  {0,0,0,1,1,1,1,1,0,0,0},
								  {0,0,0,1,1,1,1,1,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0,0}};*/
								  
		
		
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
		
		int[][] output = new int[this.pixels.length][this.pixels[0].length];
		
		int start_x = -1;
		int start_y = -1;
		for(int i=0; i<this.pixels.length; i++){
			for(int j=0; j<this.pixels[i].length; j++){
				
				//find starting pixel
				if (this.pixels[i][j] == 1){
					start_x = j;
					start_y = i;
					output[i][j]=1;
					break;
				}
				
			}
			if(start_x != -1){
				break;
			}
		}
		int cur_x = start_x;
		int cur_y = start_y;
		
		//clockwise
		//8-connected
		int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
		
	
		
		do{
			
			//8 neighbor with 1
			for(int i=0;i<dx.length;i++){
				if(this.pixels[cur_y+dy[i]][cur_x+dx[i]] == 1){
					int offset = i-1;
					
					if (offset == -1){
						offset = dx.length-1;
					}
					
					try{
						
						if(this.pixels[cur_y+dy[offset]][cur_x+dx[offset]] == 0){
							
							//corners - para 4-connected
							if(i % 2 == 1){
								offset = (i+1) % dx.length;
								if(this.pixels[cur_y+dy[offset]][cur_x+dx[offset]] == 1){
									output[cur_y+dy[offset]][cur_x+dx[offset]] =1;
								}
							}
							
							
							cur_y += dy[i];
							cur_x += dx[i];
							output[cur_y][cur_x] = 1;
							//System.out.printf("(%d,%d)\n", cur_y, cur_x);
							
						
							break;
						}
					}catch(ArrayIndexOutOfBoundsException e){}
				}
			}
			
			
		} while (cur_x != start_x || cur_y != start_y);
		
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
