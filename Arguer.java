/**
* This method extends Thread. Its constructor takes line of text containing its argument. The argument prints the
* standard output MAX(default set is 20 time) times. Each time an Arguer prints its argument, it must relinquish 
* control so that another thread may have a chance to execute. It stops after MAX statements, indicates that it
* is done, and asks whether it is the winner.
* @author Huachao Li, Student ID: 1310232
* AUCSC 350 Assignment1, 2015.Jan.28th
* 
* 
* 
* 
* 
*/
import java.util.Random;

public class Arguer extends Thread 
{
	private String name; 
	private static String winner = "No";
	
	/**
	 * This method takes line of text containing its argument. The argument prints the standard output MAX (default
     * set is 20 time) times. Each time an Arguer prints its argument, it must relinquish control so  that another
     * thread may have a chance to execute. It stops after MAX statements, indicates that is done, and asks whether
     * it is the winner.
	 * @param name-registered as the acutual thread's string value.
	 */
    public Arguer(String name) 
    { 
	        this.name = name; 
	} 

    /**
     * This method takes line of text containing its argument. The argument prints the standard output MAX (default
     * set is 20 time) times. Each time an Arguer prints its argument, it must relinquish control so  that another
     * thread may have a chance to execute. It stops after MAX statements, indicates that is done, and asks whether
     * it is the winner.
     */
	public void run()
	{
		System.out.println("=== \"" + name+ "\" is now running ===");
		for (int i = 0; i < 20; i++) 
        {
			//When the first time to run the thread.
			if (i == 0)
            {
				System.out.println(name);
            }
			//When the last time to run the thread.
			else if (i == 19)
			{
				System.out.println(name);
				//Find out the winner.
				synchronized(winner)
                {
                //If there is no winner yet, register the current thread as the winner.
                	if (winner.equals("No"))
                	{
                		winner = name;
                	}
                }
            }
            //When neither first nor last time to run the thread.
            else
            {
            	System.out.println(name);
            }
			//Try to sleep (between 1 to 100 ms) to give chance for other threads or peacemaker to run.
            try 
            {
            	Random rn = new Random();
                int rm = rn.nextInt(100-1)+1;
                Thread.sleep(rm);
            } catch (InterruptedException e)
            {
            	e.printStackTrace();
            }
        }
		System.out.println("=== \"" + name + "\" grabbed lock ===");
		System.out.println("=== \"" + name + "\" is now done ===");
    } 

	/**
	 * This method is to return the value of winner.
	 * @return-return the value of winner.
	 */
	public String getWinner()
	{
		return winner;
	}	   	
}
