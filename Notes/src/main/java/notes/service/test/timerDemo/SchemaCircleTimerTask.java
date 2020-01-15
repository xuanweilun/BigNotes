package notes.service.test.timerDemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SchemaCircleTimerTask extends TimerTask{
	
	private String type;
	
	private Cycle cycle;
	
	private Timer timer;
	
	private Long schemaId;

	
	public static final String fillingDeadline = "filling"; //填报期截止日期
	public static final String reviewDeadline = "review"; //审核期截止日期
	public static final String appealDeadline = "appeal"; //申诉期截止日期
	public static final String publicityDeadline = "publicity"; //公示期截止日期
	public static final String endTime = "endTimer";
	
	
	public SchemaCircleTimerTask(String type,Cycle cycle) {
		super();
		this.type = type;
		this.cycle = cycle;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Cycle getCycle() {
		return cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}
	
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Long getSchemaId() {
		return schemaId;
	}


	public void setSchemaId(Long schemaId) {
		this.schemaId = schemaId;
	}


	@Override
	public void run() {
		switch(this.type) {
			case fillingDeadline:
				turnToFilling();
				break;
			case reviewDeadline:
				turnToAudit();
				break;
			case appealDeadline:
				turnToAppeal();
				break;
			case publicityDeadline:
				turnToPublicity();
				break;
			case endTime:
				endTimer();
				break;
			default: break;
		}
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2019年11月11日 下午2:38:41 
	 * @Title: turnToFilling 
	 * @Description: TODO(这里用一句话描述这个方法的作用)  void
	 * @throws
	 */
	void turnToFilling() {
		
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2019年11月11日 下午2:39:53 
	 * @Title: turnToAudit 
	 * @Description: TODO(这里用一句话描述这个方法的作用)  void
	 * @throws
	 */
	void turnToAudit() {
		
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2019年11月11日 下午2:40:27 
	 * @Title: turnToAppeal 
	 * @Description: TODO(这里用一句话描述这个方法的作用)  void
	 * @throws
	 */
	void turnToAppeal() {
		
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2019年11月11日 下午2:40:31 
	 * @Title: turnToPublicity 
	 * @Description: TODO(这里用一句话描述这个方法的作用)  void
	 * @throws
	 */
	void turnToPublicity() {
		
	}
	
	
	
	void endTimer() {
		SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
		System.out.print(sf.format(new Date()));
		System.out.println("方案"+schemaId+"结束转换");
		this.timer.cancel();
		timer = null;
		System.out.println(timer);
	}
	
}
