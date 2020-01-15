package notes.service.test.compare;

import java.sql.Timestamp;


public class ObjectType implements Comparable<ObjectType>{
	
	private Timestamp age;
	private String name;
	
	public ObjectType(Timestamp age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	public Timestamp getAge() {
		return age;
	}
	public void setAge(Timestamp age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(ObjectType o) {
		if(this.age != null && o.getAge() != null){
			return this.age.compareTo(o.getAge());
		}else if(o.getAge() == null && this.age == null){
			return 0;
		}else if(o.getAge() == null && this.age != null){
			return 1;
		}else {
			return -1;
		}
	}
	@Override
	public String toString() {
		return "ObjectType [age=" + age + ", name=" + name + "]";
	}
	
	
}