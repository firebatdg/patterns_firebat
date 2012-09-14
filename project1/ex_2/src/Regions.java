import java.util.*;
public class Regions {


	public void flood_fill(int[][] image, int x, int y, int target, int replace){


		try{
			if(image[y][x] == target){
					image[y][x] = replace;
			}else{
				return;
			}
		}catch(ArrayIndexOutOfBoundsException e){return;}

		flood_fill(image, x+1, y, target, replace);
		flood_fill(image, x-1, y, target, replace);
		flood_fill(image, x, y+1, target, replace);
		flood_fill(image, x, y-1, target, replace);


	}
	public Node getNeighbor(int[][] img, int x, int y){

		for(int dy=-1; dy<2; dy++){
			for(int dx=-1; dx<2; dx++){
				try{
					if(img[y+dy][x+dx] == 1){
						return new Node(x+dx,y+dy);
					}
				}catch(ArrayIndexOutOfBoundsException e){}
			}
		}
		return null;

	}
	public void mark_regions(int[][] img){

		//flood-fill outter 0s with -1s
		this.flood_fill(img, 0, 0, 0,-1);

		for(int i=0;i<img.length;i++){
			for(int j=0;j<img[i].length; j++){

				if(img[i][j]==0){

					//look for a 1

					Node pos = this.getNeighbor(img, j, i);
					if(pos != null){
						flood_fill(img,pos.x, pos.y, 1,3);
					}

				}
			}
		}

		//replace 1-s for 2-s

		for(int i=0;i<img.length;i++){
			for(int j=0;j<img[i].length; j++){
				if(img[i][j]==1){

					img[i][j]=2;
				}
			}
		}

		//reverse flood-fill -1s to 0s
		this.flood_fill(img, 0, 0, -1,0);

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] imgA= new int[][]{
								  {0,0,0,0,0,0,0,0,0,0},
								  {0,1,1,1,0,0,0,0,0,0},
								  {0,1,1,0,0,0,1,1,0,0},
								  {0,0,0,0,1,1,1,1,1,0},
								  {0,0,0,0,1,1,1,1,1,0},
								  {0,0,1,0,1,1,1,1,1,0},
								  {0,1,1,0,1,1,0,0,1,0},
								  {0,1,1,0,0,1,1,1,1,0},
								  {0,1,1,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0}};

		Regions r = new Regions();
		r.mark_regions(imgA);

		for(int[] tmp: imgA){
			System.out.println(Arrays.toString(tmp));
		}

	}

}



class Node{

	public int x;
	public int y;
	public Node(int x, int y){
		this.x = x;
		this.y = y;
	}
}

