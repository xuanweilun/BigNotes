package notes.service.test.timerDemo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class SchemaSwitchServiceImpl implements SchemaSwitchService,InitializingBean{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static Map<Long,Timer> schemaTimerMap = new HashMap<Long,Timer>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("intint--xuan-------------------------------------+++");
	}
	
	@Override
	public void save(Long schemaId, Cycle cycle) {
		//1.check param
		if(null == cycle) {
			logger.info("null input");
			return;
		}
		
		//2.transerfer
		Date beginTime = new Date(cycle.getStartMonth().getTime());//起始月份
		Timer startTimer = new Timer(); 
		
		//3.filling
		SchemaCircleTimerTask fillTask = new SchemaCircleTimerTask("filling",cycle);
		startTimer.schedule(fillTask,beginTime, cycle.getFillingDeadline());
	
		//4.audit 
		SchemaCircleTimerTask reviewTask = new SchemaCircleTimerTask("review",cycle);
		startTimer.schedule(reviewTask,beginTime, cycle.getReviewDeadline());
		
		//5.appeal
		SchemaCircleTimerTask appealTask = new SchemaCircleTimerTask("appeal",cycle);
		startTimer.schedule(appealTask,beginTime, cycle.getAppealDeadline());
		
		//6.publicity
		SchemaCircleTimerTask publicTask = new SchemaCircleTimerTask("publicity",cycle);
		startTimer.schedule(publicTask,beginTime, cycle.getPublicityDeadline());
		
		schemaTimerMap.put(schemaId, startTimer);
	}

	@Override
	public void update(Long schemaId, Cycle cycle) {
		//1.check param 
		if(null == schemaId || null == cycle) {
			return;
		}
		//2.get param
		Timer timer = schemaTimerMap.get(schemaId);
		if(null != timer) {
			timer.cancel();
			timer = null;
		}
		//3.
		this.save(schemaId,cycle);
	}
	
	
	


}
