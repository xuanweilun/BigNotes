/**@Title: MvExpression_Test.java 
 * @Package com.brt.license.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xuanweilun 
 * @date 2019年8月2日 下午3:46:23 
 * @version V1.0   
 */

package notes.service.test.adviator;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.mvel2.MVEL;
import org.mvel2.compiler.ExecutableAccessor;
import org.mvel2.compiler.ExecutableLiteral;

import notes.service.test.Person;

/**
 * @ClassName: MvExpression_Test
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author xuanweilun
 * @date 2019年8月2日 下午3:46:23
 */

public class MvelTest {
	
	

	public static void main(String[] args) {
		
		ternaryOperationTest();  //三元运算符测试（成功）
		
		ifElseFormulaTest(); //if else 语句测试（成功）
		
		symbolTest();//符号格式测试（成功）
		
		objectAttributeTest();//对象属性测试 （成功）
		
		throwExceptionTest(); //抛出异常测试(成功)
		
	}
	
	private static String expression = "";
	private static Map<String,Double> variableNames = new HashMap<String,Double>();
	
	/**
	 * @author xuanweilun   
	 * @date 2019年8月7日 上午10:59:40 
	 * @Title: ternaryOperationTest 
	 * @Description: 三元运算符号测试（支持）
	 * @throws
	 */
	private static void ternaryOperationTest(){
		expression = "Y>10?M:G";
		
		variableNames.put("Y", 10D);
		variableNames.put("M", 2D);
		variableNames.put("G", 20D);

		Object result = MVEL.eval(expression, variableNames);
		System.out.println(result);
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2019年8月7日 上午10:59:16 
	 * @Title: ifElseFormulaTest 
	 * @Description: if else 语句 测试(支持)
	 * @throws
	 */
	private static void ifElseFormulaTest(){
		expression = "if(x>=10){return 1} else{return 0}";
//		expression = "x>=10?1:0";
		variableNames.put("x", 10D);
		Integer result = (Integer)MVEL.eval(expression, variableNames);
		System.out.println(result);
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2019年8月7日 上午10:58:29 
	 * @Title: symbolTest 
	 * @Description: 违规符号测试（支持）
	 * @throws
	 */
	private static void symbolTest(){
		expression = "1 <= Y <= 10 ? 100:20";
		variableNames.put("Y", 4D);
		variableNames.put("M", 0D);
		Integer result = (Integer)MVEL.eval(expression, variableNames);
		System.out.println(result);
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2019年8月7日 上午11:01:10 
	 * @Title: objectAttributeTest 
	 * @Description: 对象属性测试(支持)
	 * @throws
	 */
	private static void objectAttributeTest(){
		
		Person person = new Person();
		person.setPersonName("zhangsan");
		person.setPersonHome("guilin");
		Map<String,Person> persons = new HashMap<String,Person>();
		persons.put("person", person);
		
		expression = "'zhangsanguilin' == (person.personName+person.personHome)";
		
		Boolean result = (Boolean) MVEL.eval(expression, persons);
		System.out.println(result);
	}
	
	/**
	 * @author xuanweilun   
	 * @date 2019年8月7日 上午11:05:44 
	 * @Title: throwExceptionTest 
	 * @Description: 抛出异常测试
	 * @throws
	 */
	private static void throwExceptionTest(){
		expression = "(1 <= Y <=] 10";
		Map<String, Double> keyWardValue = new HashMap<String, Double>();
		keyWardValue.put("Y", 4D);
		keyWardValue.put("M", 0D);
		try{
			Object compiled =  MVEL.compileExpression(expression);
//			MVEL.executeExpression(compiled);
			Boolean b = (Boolean) MVEL.executeExpression(compiled, keyWardValue);
			System.out.println(b);
//			DecimalFormat df = new DecimalFormat( "0.0000");  //小数位限制
//			System.out.println("intResult=" + df.format(intResult));
		}catch(Exception e){
			String msg = e.getLocalizedMessage();
			StackTraceElement [] stackTrace = e.getStackTrace();
			for(int traceNum = 0;traceNum<stackTrace.length;traceNum++){
				System.out.println(stackTrace[traceNum].getMethodName());
			}
			System.out.println(msg);
			System.out.println("格式错误");
		}
	}
	
}
