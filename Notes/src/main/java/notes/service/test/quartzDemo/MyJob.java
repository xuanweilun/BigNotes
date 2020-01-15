package notes.service.test.quartzDemo;

import java.sql.Timestamp;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

public class MyJob implements Job{

	private Long schemaId;
	
	public Long getSchemaId() {
		return schemaId;
	}

	public void setSchemaId(Long schemaId) {
		this.schemaId = schemaId;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println();
		Trigger trigger = context.getTrigger();
		System.out.println("end Time = "+new Timestamp(trigger.getEndTime().getTime()));
		System.out.println("final Time = "+new Timestamp(trigger.getFinalFireTime().getTime()));
		System.out.println("next fire Time = "+trigger.getNextFireTime());
	}

}
