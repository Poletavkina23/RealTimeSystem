import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Temperature 
{
   int temp = 300;
	
	public Temperature()
   {
	   Timer myTimer;
   	   myTimer = new Timer();
   	    Random rnd = new Random();  
   	    myTimer.schedule(new TimerTask() {
   		public void run() 
   		{
   			if(temp > 120) temp = temp - rnd.nextInt(18); // +1-2 добавить
   			
   		}
   	}, 0, 1000); // каждые 5 секунд
   }
	
	public int getTemp()
	{
		return temp;
	}
	
	public void setTemp(int t)
	{
		temp = t;
	}
}
