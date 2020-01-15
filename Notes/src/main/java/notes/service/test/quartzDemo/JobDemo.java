package notes.service.test.quartzDemo;
//package com.brt.onlineSchemaCreation.core.service.cycle.job;
//
//import org.quartz.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author vividzc
// * @date 2018/6/14 23:18
// */
//@Service
//public class JobAndTriggerServiceImpl{
//
//    /**
//     * Scheduler代表一个调度容器,一个调度容器可以注册多个JobDetail和Trigger.当Trigger和JobDetail组合,就可以被Scheduler容器调度了
//     */
//
//    @Autowired
//    Scheduler scheduler;
//
//
//    /**
//     * 添加一个任务
//     * @param job
//     * @throws SchedulerException
//     */
//    public void addJob(BaseJob job) throws SchedulerException {
//
//        /** 创建JobDetail实例,绑定Job实现类
//        * JobDetail 表示一个具体的可执行的调度程序,job是这个可执行调度程序所要执行的内容
//        * 另外JobDetail还包含了这个任务调度的方案和策略**/
//        // 指明job的名称，所在组的名称，以及绑定job类
//        JobDetail jobDetail = JobBuilder.newJob(job.getClass())
//                .withIdentity(job.getJobKey())
//                .withDescription(job.getDescription())
//                .usingJobData(job.getDataMap())
//                .build();
//
//        /**
//         * Trigger代表一个调度参数的配置,什么时候去调度
//         */
//        //定义调度触发规则, 使用cronTrigger规则
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity(job.getJobName(),job.getJobGroup())
//                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
//                .startNow()
//                .build();
//        //将任务和触发器注册到任务调度中去
//        scheduler.scheduleJob(jobDetail,trigger);
//        //判断调度器是否启动
//        if(!scheduler.isStarted()){
//            scheduler.start();
//        }
////        log.info(String.format("定时任务:%s.%s-已添加到调度器!", job.getJobGroup(),job.getJobName()));
//    }
//
//    /**
//     * 根据任务名和任务组名来暂停一个任务
//     * @param jobName
//     * @param jobGroupName
//     * @throws SchedulerException
//     */
//    public void pauseJob(String jobName,String jobGroupName) throws SchedulerException {
//        scheduler.pauseJob(JobKey.jobKey(jobName,jobGroupName));
//    }
//
//    /**
//     * 根据任务名和任务组名来恢复一个任务
//     * @param jobName
//     * @param jobGroupName
//     * @throws SchedulerException
//     */
//    public void resumeJob(String jobName,String jobGroupName) throws SchedulerException {
//        scheduler.resumeJob(JobKey.jobKey(jobName,jobGroupName));
//    }
//
//    public void rescheduleJob(String jobName,String jobGroupName,String cronExpression,String description) throws SchedulerException {
//
//        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
//        // 表达式调度构建器
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
//
//        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//
//        // 按新的cronExpression表达式重新构建trigger
//        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withDescription(description).withSchedule(scheduleBuilder).build();
////        if(trigger == null){
////            throw new PowerYourselfException(ResponseCode.JOB_HAS_REMINDED);
////        }
//        // 按新的trigger重新设置job执行
//        scheduler.rescheduleJob(triggerKey, trigger);
//    }
//
//    /**
//     * 根据任务名和任务组名来删除一个任务
//     * @param jobName
//     * @param jobGroupName
//     * @throws SchedulerException
//     */
//    public void deleteJob(String jobName,String jobGroupName) throws SchedulerException {
//        TriggerKey triggerKey = TriggerKey.triggerKey(jobName,jobGroupName);
//        scheduler.pauseTrigger(triggerKey); //先暂停
//        scheduler.unscheduleJob(triggerKey); //取消调度
//        boolean flag = scheduler.deleteJob(JobKey.jobKey(jobName,jobGroupName)); //删除任务
//    }
//
//}