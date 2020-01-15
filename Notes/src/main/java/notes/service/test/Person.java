
/**@Title: Person.java 
 * @Package com.brt.license.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xuanweilun 
 * @date 2019年8月2日 下午5:44:53 
 * @version V1.0   
 */
 
package notes.service.test;


/**@ClassName: Person 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author xuanweilun
 * @date 2019年8月2日 下午5:44:53  
 */

public class Person {
	
	private String personName;
	private String personHome;
	
	
	public String getPersonName() {
		return personName;
	}


	public void setPersonName(String personName) {
		this.personName = personName;
	}


	public String getPersonHome() {
		return personHome;
	}


	public void setPersonHome(String personHome) {
		this.personHome = personHome;
	}


	@Override
	public String toString() {
		return "Person [personName=" + personName + ", personHome=" + personHome + "]";
	}

	
}
