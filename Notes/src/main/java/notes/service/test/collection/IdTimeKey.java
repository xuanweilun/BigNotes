package notes.service.test.collection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdTimeKey {
	private Long institutionId;
	private Timestamp updateTime;
	
	public IdTimeKey(Long institutionId, Timestamp updateTime) {
		super();
		this.institutionId = institutionId;
		this.updateTime = updateTime;
	}
	public Long getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(Long institutionId) {
		this.institutionId = institutionId;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "MapModel [institutionId=" + institutionId + ", updateTime=" + updateTime + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((institutionId == null) ? 0 : institutionId.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdTimeKey other = (IdTimeKey) obj;
		if (institutionId == null) {
			if (other.institutionId != null)
				return false;
		} else if (!institutionId.equals(other.institutionId))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}
	
	public static void main(String[] args) {
//		Timestamp now = new Timestamp(System.currentTimeMillis());
//		Map<IdTimeKey,Double> scoreMap = new HashMap<IdTimeKey,Double>();
//		scoreMap.put(new IdTimeKey(1L, now), 10D);
//		System.out.println(scoreMap.get(new IdTimeKey(1L, now)));
//		System.out.println(new IdTimeKey(1L, now));
		
		Map<List<Object>,Double> scoreMap2 = new HashMap<List<Object>,Double>();
		List<Object> one = new ArrayList<Object>();
		one.add("zhang");
		one.add(2);
		scoreMap2.put(one, 90D);
		System.out.println(scoreMap2.get(one));
		List<Object> tow = new ArrayList<Object>();
		tow.add("zhang");
		tow.add(2);
		System.out.println(scoreMap2.get(tow));
		
	}
}
