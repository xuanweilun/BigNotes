package notes.service.test.timerDemo;

import java.sql.Timestamp;

public class Cycle {

	private Timestamp startMonth; //起始月份
	private Timestamp endMonth; //终止月份
	private Boolean isLastDay; //是否选择每月最后一天
	private Long fillingDeadline; //填报期截止日期
	private Long reviewDeadline; //审核期截止日期
	private Long appealDeadline; //申诉期截止日期
	private Long publicityDeadline; //公示期截止日期
	public Timestamp getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(Timestamp startMonth) {
		this.startMonth = startMonth;
	}
	public Timestamp getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(Timestamp endMonth) {
		this.endMonth = endMonth;
	}
	public Boolean getIsLastDay() {
		return isLastDay;
	}
	public void setIsLastDay(Boolean isLastDay) {
		this.isLastDay = isLastDay;
	}
	public Long getFillingDeadline() {
		return fillingDeadline;
	}
	public void setFillingDeadline(Long fillingDeadline) {
		this.fillingDeadline = fillingDeadline;
	}
	public Long getReviewDeadline() {
		return reviewDeadline;
	}
	public void setReviewDeadline(Long reviewDeadline) {
		this.reviewDeadline = reviewDeadline;
	}
	public Long getAppealDeadline() {
		return appealDeadline;
	}
	public void setAppealDeadline(Long appealDeadline) {
		this.appealDeadline = appealDeadline;
	}
	public Long getPublicityDeadline() {
		return publicityDeadline;
	}
	public void setPublicityDeadline(Long publicityDeadline) {
		this.publicityDeadline = publicityDeadline;
	}

	
	
}
