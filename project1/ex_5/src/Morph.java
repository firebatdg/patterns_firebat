import java.util.*;
public class Morph {

	
	public int[][] erosion(int[][] original, int[][] pattern){
		
		int[][] out = new int[original.length][original[0].length];
		
		int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
		
		
		for(int i=0; i< original.length; i++){
			for(int j=0; j<original[i].length; j++){
				if(original[i][j] == 1){
					int px = 1;
					for(int k=0;k<8;k++){
						try{
							System.out.printf("%d & %d\n", pattern[1+dy[k]][1+dx[k]], original[i+dy[k]][j+dx[k]]);
							if(pattern[1+dy[k]][1+dx[k]] == 1 && original[i+dy[k]][j+dx[k]] == 0 ){
								px = 0;
								break;
							}
						}catch(ArrayIndexOutOfBoundsException e){
							px = 0;
							break;
						}
					}
					out[i][j] = px;
				}
			}
		}
		return out;
		
	}
	
public int[][] dilate(int[][] original, int[][] pattern){
		
		int[][] out = new int[original.length][original[0].length];
		
		int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
		
		
		for(int i=0; i< original.length; i++){
			for(int j=0; j<original[i].length; j++){
				if(original[i][j] == 1){
					out[i][j] = 1;
					for(int k=0;k<8;k++){
						try{
							System.out.printf("%d & %d\n", pattern[1+dy[k]][1+dx[k]], original[i+dy[k]][j+dx[k]]);
							if(pattern[1+dy[k]][1+dx[k]] == 1  ){
								out[i+dy[k]][j+dx[k]] = 1;
								
							}
						}catch(ArrayIndexOutOfBoundsException e){
							
							continue;
						}
					}
					
				}
			}
		}
		return out;
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 int array[][]= {{0,0,0,0,0,0,0,0,0,0},
                 {0,1,1,1,0,0,0,0,0,0},
                 {0,1,1,1,0,0,0,0,0,0},
                 {0,1,1,1,0,0,0,0,0,0},
                 {0,0,0,1,1,0,0,0,0,0},
                 {0,0,0,0,1,0,0,0,0,0},
                 {0,0,0,0,1,1,1,1,1,0},
                 {0,0,0,0,0,0,1,1,1,0},
                 {0,0,0,0,0,0,1,1,1,0},
                 {0,0,0,0,0,0,0,0,0,0}};
		Morph m = new Morph();
		
		int[][] pattern = {{0,1,0}, {1,1,1}, {0,1,0}};
		int[][] out = m.erosion(array, pattern);
		int[][] out2 = m.dilate(out, pattern);
		
		for(int[] a:out2){
			System.out.println(Arrays.toString(a));
		}
	}

}
