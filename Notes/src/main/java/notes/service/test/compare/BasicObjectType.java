package notes.service.test.compare;



public class BasicObjectType implements Comparable<BasicObjectType>{
	private Long id;
	private Long age;
	private String name;
	
	public BasicObjectType(Long age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	@Override
	public int compareTo(BasicObjectType o) {
		if (this.age.equals(o.getAge())){
	        return 0;
	    }else if (this.age >o.getAge()){
	        return 1;
	    }else {
	        return -1;
	    }
	}
	@Override
	public String toString() {
		return "Man [age=" + age + ", name=" + name + "]";
	}
	
	
	
}