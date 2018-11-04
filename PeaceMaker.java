
/**
* This method begins its message after one or more Arguers have given a message and ends as soon as all Arguers are 
* done. This means that the peace message cannot be the first message; nor can it occur if all of the Arguers are 
* finished. Normally the PeaceMaker will only whisper ¡°* Peace, peace, peace¡± whenever it gets cycle time. However,
* if it has gone unheard five times, then on the sixth time it will shout ¡°* PEACE, PEACE, PEACE¡±. It then settles 
* down, and goes into whisper mode, shouting only every 6 times.
* @author Huachao Li, Student ID: 1310232
* AUCSC 350 Assignment1, 2015.Jan.28th
*
*
*
*
*
*/
import java.util.Random;

public class PeaceMaker extends Thread
{
	private String name;
	private static int counter = 0;
	
	/**
     * This method begins its message after one or more Arguers have given a message and ends as soon as all Arguers are 
     * done. This means that the peace message cannot be the first message; nor can it occur if all of the Arguers are 
     * finished. Normally the PeaceMaker will only whisper ¡°* Peace, peace, peace¡± whenever it gets cycle time. However,
     * if it has gone unheard five times, then on the sixth time it will shout ¡°* PEACE, PEACE, PEACE¡±. It then settles 
     * down, and goes into whisper mode, shouting only every 6 times.
	 * @param name-registered as the acutual peacemaker's string value.
	 */
    public PeaceMaker(String name) 
    { 
        this.name = name;       
    }
    
    /**
     * This method begins its message after one or more Arguers have given a message and ends as soon
     *  as all Arguers are done. This means that the peace message cannot be the first message;
     * nor can it occur if all of the Arguers are finished. Normally the PeaceMaker will only whisper
     * ¡°* Peace, peace, peace¡± whenever it gets cycle time. However, if it has gone unheard five times, 
     * then on the sixth time it will shout ¡°* PEACE, PEACE, PEACE¡±. It then settles down, and goes into 
     * whisper mode, shouting only every 6 times.
     */
	public void run() 
    {
        	System.out.println("=== \"" + name + "\" is now running ===");
        	while (Thread.activeCount() >2)
        	{
        		//When PeaceMaker needs to shout.
        		if ((counter%6==0)&(counter>0))
        		{
        			System.out.println("* PEACE, PEACE, PEACE");
        			counter++;
        		}
        		//When PeaceMaker whispers.
        		else
        		{
        			System.out.println("* "+name);
        			counter++;
        		}
            	//Try to sleep (between 1 to 100 ms) to give chance for other threads to run.
        		try {
                	Random rn = new Random();
                	int rm = rn.nextInt(100-1)+1;
                            Thread.sleep(rm);
                    } catch (InterruptedException e) { 
                            e.printStackTrace(); 
                    }
        	}
        	
        	//Stop the peacemaker when other threads are all done.
        	if (Thread.activeCount() ==2)
        	{
        		System.out.println("=== \"" + name + "\" is now done ===");
        	}
            
          
    } 

}
