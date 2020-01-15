package notes.service.test.compare;


public class Basic implements Comparable<Basic>{
	private int id;
	private int age;
	private String person;
	private String address;
	
	public Basic() {
		super();
	}
	public Basic(int age, String person) {
		this.age = age;
		this.person = person;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public int compareTo(Basic o) {
		if (this.age == o.getAge()){
	        return 0;
	    }else if (this.age >o.getAge()){
	        return 1;
	    }else {
	        return -1;
	    }
	}
	@Override
	public String toString() {
		return "Basic [age=" + age + ", person=" + person + "]";
	}
	
	
}
