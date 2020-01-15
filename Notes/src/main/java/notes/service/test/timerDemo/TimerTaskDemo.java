package notes.service.test.timerDemo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class TimerTaskDemo {
    public static void main(String[] args) throws ParseException {
//    	Scanner scanner = new Scanner(System.in);
//    	while(true)
//    	{
//    		String dateStr = scanner.nextLine();
//    		timmerTest(dateStr);
//    	}    
    	
//    	timmeres1(2000L);
    	Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("系统正在运行……");
                //find schema if time equals do ,
                //if not don't do
//                timer.cancel();
            }
        },0L,2000L); 
    }

    /**
     * 系统正在运行……2019-11-11 10:12:00
系统正在运行……2019-11-11 10:12:30
系统正在运行……2019-11-11 10:12:40
系统正在运行……2019-11-11 10:12:50
系统正在运行……2019-11-11 10:13:00

     * @author xuanweilun   
     * @date 2019年11月11日 上午10:13:16 
     * @Title: timmerTest 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param timeStr
     * @throws ParseException void
     * @throws
     */
    public static void timmerTest(String timeStr) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        Date date = sf.parse(timeStr);
        System.out.println(timeStr);
        System.out.println(date);
        

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("系统正在运行……"+timeStr);
                    //find schema if time equals do ,
                    //if not don't do
                }
            }, date); //在指定的日期运行一次定时任务
    }
    
    
    public static void timmerest(String timeStr) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        Date date = sf.parse(timeStr);
        System.out.println(timeStr);
        System.out.println(date);
        

        Timer timer = new Timer();
//        timer.schedule(new SchemaCircleTimerTask(1L,new Timestamp(System.currentTimeMillis())), date); //在指定的日期运行一次定时任务
    }
    
    public static void timmeres1(Long delay) throws ParseException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("系统正在运行……");
                //find schema if time equals do ,
                //if not don't do
                timer.cancel();
            }
        },2000,delay); 
 }
    
    
    public static void timmeres2(Long delay) throws ParseException {
    	String str = "2019-11-11 15:25:00";
    	SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        Date date = sf.parse(str);
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
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("系统正在运行……");
                //find schema if time equals do ,
                //if not don't do
                timer.cancel();
//                timer.
            }
        },date,3000L); 
    }
    
    public static void timmeres3(Long delay) throws ParseException {
    	String str = "2019-11-11 15:57:00";
    	SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        Date date = sf.parse(str);
        Timer timer = new Timer();
        SchemaCircleTimerTask schemaCircleTimerTask = new SchemaCircleTimerTask("endTimer",null);
        schemaCircleTimerTask.setTimer(timer);
        timer.schedule(schemaCircleTimerTask,date,2000L); 
        System.out.println(timer);
    }
    
}
