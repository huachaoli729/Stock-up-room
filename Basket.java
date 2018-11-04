/**
 * This method implements the concept of a basket of items. It contains a name for the items and the number
 * that are available. It also also implements a method to get item(a fork or a bib) and a method to return
 * item(a fork or a bib). When the item is empty, which means the item is not available, the prof have to
 * wait to get the item.
 * 
 * @author Huachao Li, Student ID: 1310232
 * AUCSC 350 Assignment3, 2015.Mar.27th
 * 
 * 
 * 
 * 
 * 
 */
import java.util.Random;

public class Basket
{
	private static Integer numOfFork;  //Number of the forks in the basket, all prof share the fork
	private static Integer numOfBib;  //Number of the bibs in the basket, all prof share the bib
	private String name;
	private static String setBasket = "NO";  //determine wheather the basket are prepared with forks and bibs or not
	
	/**
     * This method implements the concept of a basket of items. It contains a name for the items and the number
     * that are available. It also also implements a method to get item(a fork or a bib) and a method to return
     * item(a fork or a bib). When the item is empty, which means the item is not available, the prof have to
     * wait to get the item. 
	 * 
	 * 
	 * @param name- name of the prof
	 * @param numOfFork- number of the fork in the basket
	 * @param numOfBib- number of the bib in the basket
	 */
	public Basket(String name, int numOfFork,int numOfBib)
	{
		this.name = name;
		//Initialize the number of forks and bibs, if it is not been initialized.
		if (setBasket.equals("NO"))
		{
			this.numOfFork = numOfFork;
			this.numOfBib = numOfBib; 
			setBasket = "Yes";
		}
	}
	
	/**
	 * This method is to return the boolean value to indicate wheather the prof got the fork or not.
	 * @return- return the boolean value to determine wheather the prof got the fork or not.  
	 */
	public boolean getFork()
	{

		boolean hasFork = false;
		synchronized(numOfFork)
		{
			if(numOfFork> 0)
			{ 
				numOfFork = numOfFork -1;
				hasFork = true;
				System.out.println("=== Prof "+name +" got Fork");
			}
			//Make the prof to wait for the fork.
			else
			{
				try
				{
				    Random rand = new Random();
				    //Waiting for the fork
					System.out.println("=== Prof " +name +" is waiting for Fork.");
					numOfFork.wait(rand.nextInt(20));
				}catch (InterruptedException e) {}
			}
		}
		return hasFork;
	}
	
	/**
	 * This method is to return the boolean value to indicate wheather the prof got the bib or not.
	 * @return- return the boolean value to determine wheather the prof got the bib or not.  
	 */
	public boolean getBib()
	{
		boolean hasBib = false;
		synchronized(numOfBib)
		{
		    if(numOfBib> 0)
			{
		    	//Prof got the fork. numOfFork--
		    	numOfBib = numOfBib -1;
		    	hasBib = true;
				System.out.println("=== Prof "+name +" got Bib");
			}
		    //Make the prof to wait for the fork.
		    else
		    {
		    	try
				{
		    		Random rand = new Random();
		    		//Waiting for the bib
					System.out.println("=== Prof " +name +" is waiting for Bib.");
					numOfBib.wait(rand.nextInt(20));
				}catch (InterruptedException e) {}
			}
		}
		return hasBib;
	}
	
	/**
	 * This method is to return the fork, numOfFork++ each time when it is been called.
	 */
	public void returnFork()
	{
		synchronized(numOfFork)
		{
			////Prof returned the fork. numOfFork++
			numOfFork = numOfFork +1;
			System.out.println("=== Prof "+name +" Returned Fork");
		}
	}
	
	/**
	 * This method is to return the bib, numOfBib++ each time when it is been called.
	 */
	public void returnBib()
	{
		synchronized(numOfBib)
		{
			////The prof returned the bib. numOfBib++
			numOfBib = numOfBib +1;
			System.out.println("=== Prof "+name +" Returned Bib");
		}
	}
}
