import java.util.Random;

public class Matrix2{
	public static void main(String[] args){
		int row1 = 60;
		int coln1 = 70;
		int coln2 = 80;
		
		double[][] m1 = new double[row1][coln1];
		double[][] m2 = new double[coln1][coln2];
		
		//generate random pair of matrixes;
		//System.out.println("The first matrix is: ");
		for(int i = 0; i < row1; i++)
		{
			//System.out.println("\n");
			for(int j = 0; j < coln1; j++)
			{
				m1[i][j] = (double) (Math.random());
				//System.out.print(m1[i][j] + "   ");
			}
		}
		//System.out.println("\n");
		
		//System.out.println("The second matrix is: ");
		for(int k = 0; k < coln1; k++)
		{
			//System.out.println("\n");
			for(int l = 0; l < coln2; l++)
			{
				m2[k][l] = (double) (Math.random());
				//System.out.print(m2[k][l] + " ");
			}
		}
		//System.out.println("\n");
		
		Serial s1 = new Serial(m1, m2);
		s1.start();
		System.out.println("Number of CPUS available: " + Runtime.getRuntime().availableProcessors());
		


		//ManyThreads starts;
		ManyThreads mt1 = new ManyThreads(m1, m2, row1, coln2);
		long now = System.currentTimeMillis();
		
		for(int k = 0; k < row1; k++)
		{
			for(int l = 0; l < coln2; l++)
			{
				mt1 = new ManyThreads(m1, m2, k, l);
				mt1.start();
			}

		}
		
		
		long mTime = System.currentTimeMillis() - now;
		System.out.println("\n The ManyThreads' runtime is: " +mTime +"ms.");
		
		
		//RowThread starts;
		RowThreads rt1 = new RowThreads(m1, m2, coln2);
		
		long now2 = System.currentTimeMillis();
		
		for(int k = 0; k < row1; k++)
		{
				rt1 = new RowThreads(m1, m2, k);
				rt1.start();
		}
		
		long rTime = System.currentTimeMillis() - now2;
		System.out.println("\n The RowThreads' runtime is: " +rTime +"ms.");
		
	}

}
