import java.util.Random;

public class ApplePicker extends Thread{
    private static int allApples = 0;
    private int numIpick;
    private int totalOnMyTree;
    private String myName;
    private int numLeft;

    ApplePicker(String name, int num, int total){
	numIpick = num;
	totalOnMyTree = total;
	numLeft = total;
	myName = name;
    }//Constructor

    public void pick(){

	System.out.println(myName + " Picking:" + numIpick + " numLeft: " + numLeft);

	if (numLeft > 0){
	    if (numLeft <= numIpick){ //pick them all, so done
		allApples = allApples + numLeft;
		numLeft = 0;
		//System.out.println("Just set numLeft to 0 for " + myName);

	    }
	    else{
		numLeft = numLeft - numIpick;
		allApples = allApples + numIpick;
		//System.out.println("numLeft changed, now for " + myName + " " + numLeft);
	    }
	}//big if
    }//pick

    static public int getAllApples(){
	return allApples;
    }//getAllApples

    public void run() {
		Random rand = new Random();
	int x = 0;
	while (true){
	    //yield();
try{
	    sleep(rand.nextInt(6));
}
catch (InterruptedException e) {}
	    pick();
	    if (numLeft == 0)
		break;
	}
    }

}//class ApplePicker
