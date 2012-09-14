import java.util.*;
public class Regions {

	
	public void flood_fill(int[][] image, int x, int y, int target, int replace){
		
		
		Stack<Node> s = new Stack<Node>();
		
		if(image[y][x] != target){
			return;
		}
		s.push(new Node(x,y));
		
		while(!s.empty()){
			try{
				Node tmp = s.pop();
				System.out.println(tmp);
				if(image[tmp.y][tmp.x] == target){
					image[tmp.y][tmp.x] = replace;
					System.out.printf("(%d,%d)\n", tmp.y, tmp.x);
				}else{
					continue;
				}
			}catch(ArrayIndexOutOfBoundsException e){
				continue;
			}
			
			
			//left
			int i=0;
			while(true){
				
				try{
					i--;
					if(image[y][x+i] == target){
						image[y][x+i] = replace;
						System.out.printf("[%d,%d]\n", y, x+i);
					}else{
						break;
					}
				}catch(ArrayIndexOutOfBoundsException e){
					break;
				}
				
			}
			//right
			i=0;
			while(true){
				
				try{
					i++;
					if(image[y][x+i] == target){
						image[y][x+i] = replace;
						System.out.printf("[%d,%d]\n", y, x+i);
					}else{
						break;
					}
				}catch(ArrayIndexOutOfBoundsException e){
					break;
				}
				
			}
			//System.out.println(s);
			
			Node up = new Node(x,y-1);
			if(!s.contains(up)){
				s.push(up);
			}
			
			Node down = new Node(x,y+1);
			if(!s.contains(down)){
				s.push(down);
			}
			
			
			
		}
		
		
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
								  {0,1,1,0,1,1,1,1,1,0},
								  {0,1,1,0,0,1,1,1,1,0},
								  {0,1,1,0,0,0,0,0,0,0},
								  {0,0,0,0,0,0,0,0,0,0}};
		
		Regions r = new Regions();
		r.flood_fill(imgA, 0, 0, 0, -1);
		
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
	@Override
	public boolean equals(Object t){
		 if (this == t)
		        return true;
		    if (t == null)
		        return false;
		    if (getClass() != t.getClass())
		        return false;
		    
		return this.x == ((Node)t).x && this.y == ((Node)t).y;
	}
	
	public String toString(){
		return "{" + this.y + "," + this.x + "}";
	}
}
