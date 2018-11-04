/**
 * This method extends thread, it contains a run method which will cycle through each of the following four stages
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
 * @author Huachao Li, Student ID: 1310232
 * AUCSC 350 Assignment3, 2015.Mar.27th
 * 
 * 
 * 
 * 
 * 
 * 
 */
import java.util.Random;

public class Prof extends Thread
{
	private String name;
	private int numOfCode;
	private int numOfNoodle;
	private int totalCode = 0;
	private int totalNoodle = 0;
	private int numIprogram;
	private int numIeat;
	/**
	 * This method extends thread, it contains a run method which will cycle through each of the following four stages
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
	 * @param name- The prof name
	 * @param numOfCode- The number of Codes left to program.
	 * @param numOfNoodle- The number of Noodles left to eat.
	 */
	public Prof (String name, int numOfCode, int numOfNoodle)
	{
		this.name = name;                //Name of the prof
		this.numOfCode = numOfCode;      //Number of lines of code left to program
		this.numOfNoodle = numOfNoodle;  //Number of noodles left to eat
	}
	
	/**
     * This method extends thread, it contains a run method which will cycle through each of the following four stages
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
	 */
	public void run()
	{
		Random rand = new Random();
	    while (true)
	    {
            try
            {
            	//Try to sleep for a random time of [0, 100) milliseconds. Status change to sleep.
	    		int time = rand.nextInt(100);
	    		System.out.println("Prof "+ name + " is sleeping");
	    		sleep(time);
	    	}
	    	catch (InterruptedException e) {}
            
	    	if(numOfCode > 0)
		    {
	    		yield();
	    		//Status changes program, time to program the code
	    		program();
		    }
	    	
	    	if(numOfNoodle > 0)
		    {
	    		yield();
	    		//Status changes to hungry, time to eat noodles.
		    	System.out.println("Prof " +name +" is hungry");
		    	//Time to eat. Status changes to eat
		    	eat();
		    }

	    	//Print the message if all done.
	    	if((numOfCode == 0) && (numOfNoodle ==0))
		    {
	    		System.out.println("===============>Prof " +name +" is ALL done");
		    	break;
		    }
	    }    
	}
	
	/**
	 * This method is to make the prof to program the codes for one time.
	 */
	public void program()
	{
		if(numOfCode > 0)
		{
			Random rand = new Random();
			//The number of lines of code to program is in [0, 20)
			numIprogram = rand.nextInt(20);
			Basket x = new Basket(name,2,2);
			if (x.getBib())
		    {
				//Prof got the bib.
		        if (numOfCode <= numIprogram)
		        { 
		        	//Program all the lines of code left
		    	    totalCode = totalCode + numOfCode;    
		    	    System.out.println("Prof " +name +" is programming " +numOfCode +" lines of code.  Total written: "+totalCode);
		    	    //Each line of codes take 1 millisecond to program.
		    	    try
				    {
				    	sleep(numOfCode);
				    }catch (InterruptedException e) {}
		    	    //All lines of code are programed by the prof
		    	    numOfCode = 0;
		    	    //Prof returned the bib
		    	    x.returnBib();
		        }
		        else{
		    	    numOfCode = numOfCode - numIprogram;
		    	    totalCode = totalCode + numIprogram;
		    	    System.out.println("Prof " +name +" is programming " +numIprogram +" lines of code.  Total written: "+totalCode);
		    	    //Each line of code take 1 millisecond to program.
		    	    try
				    {
				    	sleep(numIprogram);
				    }catch (InterruptedException e) {}
		    	    //Prof returned the bib
		    	    x.returnBib();
		        }
		    }
		}
	}
	
	/**
	 * This method is to make the prof to eat the noodles for one time.
	 */
	public void eat()
	{
		if(numOfNoodle > 0)
		{
			Random rand = new Random();
			//The number of lines to program is in [2, 10]
			numIeat = 2 +rand.nextInt(9);
			Basket x = new Basket(name,2,2);
		    if (x.getFork())
		    {
				//Prof got the fork.
			    if (numOfNoodle <= numIeat)
			    { 
				    //Eat all the noodles left
				    totalNoodle = totalNoodle + numOfNoodle;
				    System.out.println("Prof " +name +" is eating " +numOfNoodle +" noodles! YUM YUM BURP   Total eaten: "+totalNoodle);
				    //Each noodles take 1 millisecond to eat.
				    try
				    {
				    	sleep(numOfNoodle);
				    }catch (InterruptedException e) {}
				    //All noodles are eaten by the prof
			        numOfNoodle = 0;
			        //Prof returned the fork.
				    x.returnFork();
			    }
			    else
			    {
				    numOfNoodle = numOfNoodle - numIeat;
				    totalNoodle = totalNoodle + numIeat;
				    System.out.println("Prof " +name +" is eating " +numIeat +" noodles! YUM YUM BURP   Total eaten: "+totalNoodle);
				    //Each noodles take 1 millisecond to eat.
				    try
				    {
				    	sleep(numIeat);
				    }catch (InterruptedException e) {}
				    //Prof returned the fork.
				    x.returnFork();
			    }
		    }
		}
	}
}
