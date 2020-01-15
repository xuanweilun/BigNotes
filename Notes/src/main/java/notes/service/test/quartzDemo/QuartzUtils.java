//package notes.service.test.quartzDemo;
//
//import java.sql.Timestamp;
//
//import org.quartz.CronScheduleBuilder;
//import org.quartz.CronTrigger;
//import org.quartz.JobBuilder;
//import org.quartz.JobDetail;
//import org.quartz.JobKey;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.SchedulerFactory;
//import org.quartz.TriggerBuilder;
//import org.quartz.TriggerKey;
//import org.quartz.impl.StdSchedulerFactory;
//
///**
// * 
// * @author Administrator
// *
// */
//public class QuartzUtils {
//
//	private static SchedulerFactory sfact=new StdSchedulerFactory();
//	
//	
//    public static void modifyJobTime(String triggerName,
//            String triggerGroupName, String time) {
//        try {
//            Scheduler sched = QuartzUtils.getScheduler();
//            TriggerKey triggerKey = new TriggerKey(triggerName);
//            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
//            if (trigger == null) {
//                return;
//            }
//            String oldTime = trigger.getCronExpression();
//            if (!oldTime.equalsIgnoreCase(time)) {
//                CronTrigger ct = (CronTrigger) trigger;
//                // 修改时间
//                ct.setCronExpression(time);
//                // 重启触发器
//                sched.resumeTrigger(triggerName, triggerGroupName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//	
//	public static boolean deleteJob(String string) throws SchedulerException {
//		Scheduler scheduler = QuartzUtils.getScheduler();
//		JobKey jobKey  = new JobKey(string);
////		scheduler.unscheduleJob(triggerKey);
//		boolean success = scheduler.deleteJob(jobKey);
//		return success;
//	}
//
//	public static boolean pauseTrigger(String string) throws SchedulerException {
//		Scheduler scheduler = QuartzUtils.getScheduler();
//		TriggerKey T = new TriggerKey(string);
//		scheduler.pauseTrigger(T); //停止
//		return true;
//	}
//
//	public static CronTrigger getTrigger(Long schemaId,String time) {
//		CronTrigger trigger = (CronTrigger) TriggerBuilder
//                .newTrigger()
//                .withIdentity(schemaId.toString())   //创建一个标识符
////                .startAt(new Date())//什么时候开始触发
//                .withSchedule(CronScheduleBuilder.cronSchedule(time))
//                .build();
//		return trigger;
//	}
//
//
//	public static String getCron(Timestamp timestamp) {
//		String cron = CronUtils.getCron(timestamp);
//		return cron;
//	}
//
//	/**
//	 * @author xuanweilun   
//	 * @date 2019年11月12日 下午3:44:52 
//	 * @Title: getJobDetail 
//	 * @Description: TODO(这里用一句话描述这个方法的作用) 
//	 * @param schemaId
//	 * @return JobDetail
//	 * @throws
//	 */
//	public static JobDetail getJobDetail(Long schemaId) {
//		JobDetail jobDetail=JobBuilder
//				.newJob(MyJob.class)
//				.withIdentity(schemaId.toString())
//                .build();
//		return jobDetail;
//	}
//
//	/**
//	 * @author xuanweilun   
//	 * @date 2019年11月12日 下午3:44:47 
//	 * @Title: getScheduler 
//	 * @Description: TODO(这里用一句话描述这个方法的作用) 
//	 * @return
//	 * @throws SchedulerException Scheduler
//	 * @throws
//	 */
//	public static Scheduler getScheduler() throws SchedulerException {
//		Scheduler scheduler= sfact.getScheduler();
//		if(!scheduler.isShutdown()) {
//			scheduler.start();  
//		}
//		return scheduler;
//	}
//}
