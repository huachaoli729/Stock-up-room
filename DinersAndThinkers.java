/**
* This program contain the main method for "The Dining Philosphers Problem1": ,
*    ¡°Five silent philosophers sit at a round table with bowls of spaghetti. Forks are placed between each pair 
*     of adjacent philosophers. ¡­
*     
*    Each philosopher must alternately think and eat. However, a philosopher can only eat spaghetti when he has
*    both left and right forks. Each fork can be held by only one philosopher and so a philosopher can use the 
*    fork only if it is not being used by another philosopher. After he finishes eating, he needs to put down 
*    both forks so they become available to others. A philosopher can take the fork on his right or the one on 
*    his left as they become available, but cannot start eating before getting both of them.¡±
*  
* It will prompt the user for the number of profs and their names. As well, it will prompt for the number of 
* forks and the number of bibs that are available. You can assume that these numbers are all positive. It will 
* then create all the Prof threads and let them run to completion. 
* 
* 
* The Porf class is a thread, it contains a run method which will cycle through each of the following four stages
* until a prof is done eating all of his/her noodles and finished writing all of his/her program:
*    a) Sleep. Print a message indicating that the prof is sleeping, and sleep for a random time of [0, 100) 
*       milliseconds.
*    b) Program. Print a message indicating that the prof is programming, and how many lines of code, as well
*       as the current total lines. The prof will create a random number of lines of code. The number of lines
*       should be in [0, 20) and it takes one millisecond to create each line of code.
*    c) Hungry. Print a message indicating that the prof is hungry. This state is fairly instantaneous, and 
*       moves directly to the next one (i.e. without relinquishing control).
*    d) Eat. A prof needs both a fork and a bib in order to eat. He/she must try to obtain one of these and
*       then the other, at random. Once he/she has both a fork and a bib, print a message indicating that 
*       the prof is eating, and how many noodles, plus total of noodles so far. He/she will eat some random 
*       number of noodles, in [2, 10], unless there is only 1 left, in which case that one will just be eaten.
*       The time taken to eat is 1 millisecond per noodle. When eating is finished the prof must put the fork
*       and bib back, in random order.
* 
* 
* 
* The Basket class implements the concept of a basket of items. It contains a name for the items and the number
* that are available. You must also implement a method to get an item and a method to return an item. When a prof
* got the item, then the item is not available. 
*
* @author Huachao Li, Student ID: 1310232
* AUCSC 350 Assignment3, 2015.Mar.27th
* 
* Contents:
* main(String[]),
*      This method excutes 4 initialed threads and a peacemaker, and run the threads and the peacemaker in certain 
*      amount of times(threads run 20 times as default set, peacemaker runs when not all the threads stop running), and
*      figure out the number of available cores,and eventually find out who is the winner (Which thread ends first is 
*      considered as the winner) in the threads. The peacemaker will shout "* PEACE, PEACE, PEACE" every 6 times, and 
*      stop itself right after when other threads are done.
* 
* 
* 
* 
* 
*/
import java.util.Scanner;

public class DinersAndThinkers extends Thread 
{	
	/**
	 * This method prompts the user for the number of profs and their names. As well, it prompts for the number of 
     * forks and the number of bibs that are available. Assume that these numbers are all positive. It will 
     * then create all the Prof threads and let them run to completion.
	 * @param args-This is command ling arguements.
	 */
	public  static void main (String args[])
	{
		Scanner in =new Scanner(System.in);
		//Initialize the number of the profs.
		System.out.println("How many profs:");
		int numOfProf = in.nextInt();
		
		//Clean the buffer
		in.nextLine();
		
		//Set an array for prof's name list.
		String [] list= new String[numOfProf];
		//Initialize the name of the profs.
		for(int i = 0; i< numOfProf; i++)
		{
			System.out.println("Enter Prof "+(i+1) +"'s name:");
			list[i] = in.nextLine();
		}
		System.out.println("How many forks:");
		int numOfFork = in.nextInt();
		System.out.println("How many bibs:");
		int numOfBib = in.nextInt();
		Basket a = new Basket(list[0], numOfFork, numOfBib);
		
		//Start threads for all the profs
		Prof [] profs = new Prof[numOfProf];
		for (int i = 0; i < profs.length; i++)
		{
			String name = list[i];
			System.out.println("Creating "+ name);
			//Each prof has 200 lines of code and 85 noodles to complete
			profs[i] = new Prof(name, 200, 85);
			profs[i].start();
		}
		
		for (int i=0; i<profs.length; i++)
		{
		    try
		    {
		    	profs[i].join();
		    } catch (InterruptedException e) {System.out.println("Out of time********************");}
		}	
	}	
}
