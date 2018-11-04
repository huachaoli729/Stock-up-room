/**
 * This method is to excute 3 methods to calculate the the multiplication of two matrix : Serial, ManyThreads 
 * and RowThread. Then, each method gives the runtime of the calculation.
 * 
 * The Serial class take the regular, serial multiplication.
 * 
 * The ManyThreads class do parallel multiplication, with one thread per cell in your answer matrix.
 * 
 * The RowThread do parallel multiplication, with one thread per row of your first matrix. Each thread will 
 * do all of the calculations with one row of the first matrix (and every column of the second matrix).
 * 
 * @author Huachao Li, Student ID: 1310232
 * AUCSC 350 Assignment1, 2015.Mar.6th
 * 
 * Contents:
 * main(String[]),
 *      This method is to excute 3 methods to calculate the the multiplication of two matrix : Serial, ManyThreads 
 *      and RowThread. Then, each method gives the runtime of individual's calculation.
 * 
 * 
 * 
 * 
 * 
 */
import java.util.Random;

/**
 * This method is to excute 3 methods to calculate the the multiplication of two matrix. 
 * The Serial method take the regular, serial multiplication. 
 * The ManyThreads method parallel multiplication, with one thread per cell in your answer matrix.
 * This will mean that each thread calculates one entry of the answer matrix.
 * The RowThread method do parallel multiplication, with one thread per row of your first matrix. Each thread will do all of the calculations with one row of the first matrix (and every column of the second matrix).
 *
 * 
 * @param args-This is command ling arguements.
 * @throws InterruptedException
 */

public class Matrix{

	public static void main(String[] args) throws InterruptedException
	{
		int row1 = 60;
		//coln1 = row2
		int coln1 = 70;
		int coln2 = 80;
		
		double[][] m1 = new double[row1][coln1];
		double[][] m2 = new double[coln1][coln2];
		double[][] result = new double[row1][coln2];
 		Random rand = new Random();
		//generate random pair of matrixes;
		
		//Initialize the valures into the first matrix.
		for(int i = 0; i < row1; i++)
		{
			for(int j = 0; j < coln1; j++)
			{
				m1[i][j] = rand.nextDouble();
			}
		}
		//System.out.println("The first matrix is: " +toString(m1));
		//System.out.println("\n");
		
		//Initialize the valures into the second matrix.
		for(int k = 0; k < coln1; k++)
		{
			for(int l = 0; l < coln2; l++)
			{
				m2[k][l] = rand.nextDouble();
			}
		}
		//System.out.println("The second matrix is: " +toString(m2));
		//System.out.println("\n");
		System.out.println("Number of CPUS available: " + Runtime.getRuntime().availableProcessors());
		
		//Serial starts;
		Serial s1 = new Serial(m1, m2, result);
		s1.start();
		//Serial ends;
		
		//RowThread starts;
		long startR = System.currentTimeMillis(); 
        int threadcount = 0;
        int NUM_OF_THREADS = row1;
        Thread[] rt = new Thread[NUM_OF_THREADS];
        try
        {
        	for(int row = 0 ; row < row1; row++)
			{
        		// creating thread for multiplications
	            rt[threadcount] = new Thread(new RowThreads (row, m1, m2, result));
	            rt[threadcount].start(); //thread start
	            rt[threadcount].join(); // joining threads
	            threadcount++;  
	        }   
	    }
	    catch (InterruptedException ie){}
        //Calculate the runtime for RowThreads.
		long rTime = System.currentTimeMillis() - startR;
		System.out.println("\n The RowThreads' runtime is: " +rTime +" ms.");
		//RowThreads ends;

		//ManyThreads starts;
        int threadcount2 = 0;
        int NUM_OF_THREADS2 = row1*coln2;
        Thread[] mt = new Thread[NUM_OF_THREADS2];
		long startM = System.currentTimeMillis();
        try
        {
        	for(int row = 0 ; row < row1; row++)
        	{
        		for (int col = 0 ; col < coln2; col++ )
        		{
        			// creating thread for multiplications
                	mt[threadcount2] = new Thread(new ManyThreads (row, col, m1, m2, result));
                	mt[threadcount2].start(); //thread start
                	mt[threadcount2].join(); // joining threads
                    threadcount2++;
                }    
            }   
        }
        catch (InterruptedException ie){}
        //Calculate the runtime for ManyThreads.
		long mTime = System.currentTimeMillis() - startM;
		System.out.println("\n The ManyThreads' runtime is: " +mTime +"ms.");
		//ManyThreads ends;
		
	}
	
	/**
	 * This method is to transfer the double value based matrix into String as output for printing.
	 * 
	 * @param matrix- The matrix which needs to transfer into Strings.
	 * @return- return the String type of the matrix
	 */
	public static String toString(double[][] theMatrix) 
	{
		double matrix[][] = theMatrix;
	    String str = "\n";
		int row = matrix.length;
		int coln = matrix[0].length;

	    for (int i=0; i < row; i++)
	    {
	       for (int j=0; j < coln; j++)
	       {
	          str = str + matrix[i][j] + "  ";
	       }
	       str = str + "\n";
	    }
	    return str;
	}
}
