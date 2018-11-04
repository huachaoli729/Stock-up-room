/**
* This method extends Thread. It takes the regular, serial multiplication.
* @author Huachao Li, Student ID: 1310232
* AUCSC 350 Assignment2, 2015.Mar.6th
* 
* 
* 
* 
* 
*/
public class Serial extends Thread{
	double[][] m1;
	double[][] m2;
	private int row1;
	private int coln1;
	private int coln2;
	private double[][] result;

	/**
	 * This method takes the regular, serial multiplication.
	 * @param m1- the first matrix of the multiplication.
	 * @param m2- the second matrix of the multiplication.
	 * @param result- the matrix to store the result of the calculation.
	 */
	Serial(double[][] m1, double[][] m2, double[][] result) {
	this.m1 = m1;
	this.m2 = m2;
	this.row1 = m1.length;
	//coln1 == row2;
	this.coln1 = m1[0].length;
	this.coln2 = m2[0].length;
	this.result = result;
	}
	
	/**
	 * This method runs the regular, serial multiplication.
	 */
	public void run()
	{
		long startS = System.currentTimeMillis();
		//Calculate the result.
		for (int i = 0; i < row1; i++)
		{
			for(int j = 0; j < coln2; j++)
			{
			    for(int k = 0; k < coln1; k++)
			    {
			    	result[i][j] += m1[i][k]*m2[k][j];
			    }
			}
		}
		//Calculate the runtime for Serial.
		long sTime = System.currentTimeMillis() - startS;
		//Print the runtime for Serial. 
		System.out.println("\n The Serial's runtime is: " +sTime +"ms.");
	}
	
	/**
	 * This method is to returen the result matrix value.
	 * @return- return the result matrix value.
	 */
	public double[][] getResult()
	{
		return result;
	}
}
