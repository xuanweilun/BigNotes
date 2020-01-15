
/**@Title: Map_Test.java 
 * @Package com.brt.license.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xuanweilun 
 * @date 2019年8月19日 下午12:09:51 
 * @version V1.0   
 */
 
package notes.service.test.collection;

import java.util.HashMap;
import java.util.Map;

import notes.service.test.Person;

/**@ClassName: Map_Test 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author xuanweilun
 * @date 2019年8月19日 下午12:09:51 
 */

public class Map_Test {

	/**
	 * @author xuanweilun   
	 * @date 2019年8月19日 下午12:12:51 
	 * @Title: getFilterMapParam 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param a
	 * @return Map<String,Object>
	 * @throws
	 */
	public static Map<String,Object> getFilterParams(Object ...a){
		//1.check param
		if(0 == a.length || null == a){
			System.err.println("input param is null");
			return null;
		}
		//2.check odd
		if(0 != a.length%2){
			System.err.println("getFilterMapParam method has an odd number of parameters");
			return null;
		}
		//3.set map
		Map<String,Object> filterParam = new HashMap<String,Object>();
		for(int var = 0;var<a.length;var = var+2){
			// if key is not String types
			if(var%2 == 0 && !(a[var] instanceof String)){
				System.err.println("key can not be other types except String");
				return null;
			}
			String varName = (String)a[var];
			Object varValue = a[var+1];
			if(null == varName || "".equals(varName) ){
				System.err.println("variable name can not be null");
				return null;
			}
			if(null == varValue){
				System.err.println("variable value can not be null");
				return null;
			}
			//if value is String value
			if(null != varValue && varValue instanceof String){
				if("".equals((String)varValue)){
					System.err.println("variable value can not be null");
					return null;
				}
			}
			filterParam.put(varName, varValue);
		}
		//4.return
		if(filterParam.isEmpty()){
			return null;
		}
		return filterParam;
	}
	
	public static void main(String[] args) {
		Map<String,Object> values = getFilterParams("key1","value1","21",new Person());
		System.out.println(values);
	}
}
