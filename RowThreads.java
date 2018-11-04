/**
* This method extends Thread. The RowThread do parallel multiplication, with one thread per row of your first matrix. Each thread will 
* do all of the calculations with one row of the first matrix (and every column of the second matrix).
* @author Huachao Li, Student ID: 1310232
* AUCSC 350 Assignment2, 2015.Mar.6th
* 
* 
* 
* 
* 
*/
public class RowThreads extends Thread
{
	double[][] m1;
	double[][] m2;
	private int row;
	private int coln1;
	private int coln2;
	private double[][] result;

	/**
     * This method extends Thread. The RowThread do parallel multiplication, with one thread per row of your first matrix. Each thread will 
     * do all of the calculations with one row of the first matrix (and every column of the second matrix).
	 * @param row- the row index of the calculating row.
	 * @param m1- the first matrix of the multiplication.
	 * @param m2- the second matrix of the multiplication.
	 * @param result- the matrix to store the result of the calculation.
	 */
	RowThreads(int row, double[][] m1, double[][] m2, double[][] result) 
	{
		this.m1 = m1;
		this.m2 = m2;
		this.row = row;
		//coln1 == row2;
		this.coln1 = m1[0].length;
		this.coln2 = m2[0].length;
		this.result = result;

	}
	/**
     * This method does parallel multiplication, with one thread per row of your first matrix. Each thread will 
     * do all of the calculations with one row of the first matrix (and every column of the second matrix).
	 */
	public void run()
	{
		int rowNum = row;
		//Calculate the row result.
		for(int j = 0; j < coln2; j++)
		{
			for(int k = 0; k < coln1; k++)
			{
				result[rowNum][j] += m1[rowNum][k]*m2[k][j];
			}
		}
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