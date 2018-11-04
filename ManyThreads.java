/**
* This method extends Thread. It does parallel multiplication, with one thread per cell in your answer matrix.
* @author Huachao Li, Student ID: 1310232
* AUCSC 350 Assignment2, 2015.Mar.6th
* 
* 
* 
* 
* 
*/
public class ManyThreads extends Thread {
	private double[][] m1;
	private double[][] m2;
	//coln1= row2
	private int coln1;
	private double[][] result;
	private static int row;
	private static int coln;

	/**
	 * This method extends Thread. It does parallel multiplication, with one thread per cell in your answer matrix.
	 * @param row- the row index of the calculating cell.
	 * @param coln- the column index of the calculating row result.
	 * @param m1- the first matrix of the multiplication.
	 * @param m2- the second matrix of the multiplication.
	 * @param result- the matrix to store the result of the calculation.
	 */
	ManyThreads(int row, int coln, double[][] m1, double[][] m2, double[][] result)
	{
		this.m1 = m1;
		this.m2 = m2;
		this.row = row;
		this.coln = coln;
		//coln1 == row2;
		this.coln1 = m1[0].length;
		this.result = result;
	}

	/**
	 * This method extends Thread. It does parallel multiplication, with one thread per cell in your answer matrix.
	 */
	public void run()
	{
		//Calculate the cell result.
		for(int k=0; k < coln1; k++)
		{
			result[row][coln] += m1[row][k]*m2[k][coln];
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