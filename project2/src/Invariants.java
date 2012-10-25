import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class Invariants {

	
	
	public double media(ArrayList<Double> data){
		
		double sum = 0.0;
		int total = 0;
		
		for(double n : data){
			sum += n;
			total++;
		}
		return sum / total;
	}

	public double desv_std(ArrayList<Double> data){
		double mean = this.media(data);
		double sum = 0;
		int total = 0;
		
		for(double n : data){
			sum += (n-mean)*(n-mean);
			total++;
		}
		return Math.sqrt(sum/total);
	}
	
	public void getReport(int id, String basepath){


		//\begin{center}
		// \begin{tabular}{ | l | l | l | l | l | l | l |}
		//\hline 
		//Object & Hu1 & Hu2 & Flusser1 & Flusser2 & Flusser3 & Flusser4 \\ \hline
		//IMAG0201.BMP & 0.193967 & 0.000052 & 0.000000 & 0.000000 & 0.000000 & 0.000000 \\ \hline 


		File folder = new File(basepath + id);
		File[] listOfFiles = folder.listFiles(); 
		Arrays.sort(listOfFiles);


		System.out.println("\\begin{center}");
		System.out.println("\\begin{tabular}{ | l | l | l | l | l | l | l |}");
		System.out.println("\\hline");
		System.out.println("Object & Hu1 & Hu2 & Flusser1 & Flusser2 & Flusser3 & Flusser4 \\\\ \\hline");


		ArrayList<Double> hu_1 = new ArrayList<Double>();
		ArrayList<Double> hu_2 = new ArrayList<Double>(); 
		ArrayList<Double> flusser_1 = new ArrayList<Double>(); 
		ArrayList<Double> flusser_2 = new ArrayList<Double>();
		ArrayList<Double> flusser_3 = new ArrayList<Double>(); 
		ArrayList<Double> flusser_4 = new ArrayList<Double>(); 
		

		for (int i = 0; i < listOfFiles.length; i++) 
		{

			if (listOfFiles[i].isFile()) 
			{
				String file = listOfFiles[i].getName();
				Moments m = new Moments(basepath+id+"/"+file);
				double hu1 = m.getHu1(m.working_region);
				double hu2 = m.getHu2(m.working_region);
				double flusser1 = m.getFlusser1(m.working_region);
				double flusser2 = m.getFlusser2(m.working_region);
				double flusser3 = m.getFlusser3(m.working_region);
				double flusser4 = m.getFlusser4(m.working_region);
				
				hu_1.add(hu1);
				hu_2.add(hu2);
				flusser_1.add(flusser1);
				flusser_2.add(flusser2);
				flusser_3.add(flusser3);
				flusser_4.add(flusser4);
				
				System.out.printf("%s & %.10f & %.10f & %.10f & %.10f & %.10f & %.10f \\\\ \\hline \n", file, hu1, hu2, flusser1, flusser2, flusser3, flusser4);
			}
		}
		
		System.out.println("\\hline");

		double m_hu1 = this.media(hu_1);
		double m_hu2 = this.media(hu_2);
		double m_flusser1 = this.media(flusser_1);
		double m_flusser2 = this.media(flusser_2);
		double m_flusser3 = this.media(flusser_3);
		double m_flusser4 = this.media(flusser_4);
		
		//Media
		System.out.printf("Media & %.10f & %.10f & %.10f & %.10f & %.10f & %.10f \\\\ \\hline \n", m_hu1, m_hu2, m_flusser1, m_flusser2, m_flusser3, m_flusser4);
		
		double s_hu1 = this.desv_std(hu_1);
		double s_hu2 = this.desv_std(hu_2);
		double s_flusser1 = this.desv_std(flusser_1);
		double s_flusser2 = this.desv_std(flusser_2);
		double s_flusser3 = this.desv_std(flusser_3);
		double s_flusser4 = this.desv_std(flusser_4);
		
		//StdDev
		System.out.printf("Desv Std & %.10f & %.10f & %.10f & %.10f & %.10f & %.10f \\\\ \\hline \n", s_hu1, s_hu2, s_flusser1, s_flusser2, s_flusser3, s_flusser4);
		
		
		
		System.out.println("\\end{tabular}");
		System.out.println("\\end{center}");


	}





	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Invariants().getReport(1, "images/obj");

	}

}
