
/**@Title: Pattern_Test.java 
 * @Package com.brt.license.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xuanweilun 
 * @date 2019年8月2日 下午5:20:20 
 * @version V1.0   
 */
 
package notes.service.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**@ClassName: Pattern_Test 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author xuanweilun
 * @date 2019年8月2日 下午5:20:20  
 */

public class Pattern_Test {

	public static void main(String[] args) {
		
		String expression = "a1fad23r3;.ui";
		
		String regular = "[1]";
		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(expression);
//		pattern.
//		System.out.println(matcher.find());
//		System.out.println(matcher.end());
		
		
	}
}
