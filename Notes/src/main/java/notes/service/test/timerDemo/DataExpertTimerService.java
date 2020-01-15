package notes.service.test.timerDemo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DataExpertTimerService {

	public static void main(String[] args) {
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("系统正在运行……");
                //find schema if time equals do ,
                //if not don't do
//                timer.cancel();
            }
        },new Date(),2000L); 
        System.out.println("me");
	}
	
	
	
	
}
