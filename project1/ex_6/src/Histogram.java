import java.util.Arrays;
import java.awt.image.*;


public class Histogram {


	public Histogram(){


	}

	private double[] getData(int[][] img, int n){

		double[] values = new double[n];
		int[] count = new int[n];

		for(int i=0; i < img.length; i++){
			for(int j=0; j < img[i].length; j++){
				count[img[i][j]]++;
			}
		}


		for(int i=0;i<values.length;i++){
			values[i] = (double)count[i]/ (double)(img.length * img.length) * 100.0f;
		}

		return values;
	}

	private double[] getData(BufferedImage img, int n){

	 double[] values = new double[n];
    int[] count = new int[n];

    for(int i=0; i < img.getHeight(); i++){
      for(int j=0; j < img.getWidth(); j++){
        count[img.getRGB(j,i) & 0xFF]++;
      }
    }


    for(int i=0;i<values.length;i++){
      values[i] = (double)count[i]/ (double)(img.getWidth() * img.getHeight()) * 2000.0f;
    }

    return values;



	}

	public double[][] getHistData(int[][] img, int n){

		double [][] out = new double[6][n];

		for(int i=0;i<n;i++){
			out[0][i] = i;
		}

		out[1] = this.getData(img, n);
		out[2] = this.getData(img, n);
		out[3] = this.getData(img, n);
		out[4] = this.getData(img, n);
		out[5] = this.getData(img, n);

		return out;


	}


	public BufferedImage getHistogramImage(BufferedImage img, int n){

		double[] data = this.getData(img, n);
		BufferedImage out =new BufferedImage( n+1, 101, BufferedImage.TYPE_INT_RGB);
		for(int i=0;i<99;i++){
			for(int x=0;x<n;x++){

				int y =100 - i;
				if(data[x] > y){
					out.setRGB(x,101-y,0x0);
				}else{

					out.setRGB(x,101-y,0xFFFFFF);
				}
			}

		}

		return out;
	}


	public void printHistogram(int[][] img, int n){

		double[] data = this.getData(img, n);
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

	public double[] plain_list(int[][] img ){
		double[] out = new double[img.length * img[0].length];

		int k=0;
		for(int i=0;i<img.length;i++){
			for(int j=0;j<img[i].length;j++){
				out[k++] = img[i][j];
			}
		}

		return out;
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
