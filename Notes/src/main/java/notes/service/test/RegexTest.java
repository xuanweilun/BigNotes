
/**@Title: RegexTest.java 
 * @Package com.brt.license.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xuanweilun 
 * @date 2019年8月7日 上午11:45:28 
 * @version V1.0   
 */
 
package notes.service.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**@ClassName: RegexTest 
 * @Description: 正则表达式学习 
 * @author xuanweilun
 * @date 2019年8月7日 上午11:45:28  
 */

public class RegexTest {

	public static void main(String[] args) {
		test();
	}
	// '-'的意义就是从什么到什么之间
	//'[]'的意义就 是
	private static void test(){
		Pattern pattern = Pattern.compile("[a-z]");
		Matcher matcher = pattern.matcher("fasd2f 9a2B29g6");
		while(matcher.find()){
			String constant = matcher.group();
			System.out.println(constant);
		}
	}
}
