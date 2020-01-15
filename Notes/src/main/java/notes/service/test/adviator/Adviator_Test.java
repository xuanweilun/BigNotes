
/**@Title: Adviator.java 
 * @Package com.brt.license.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xuanweilun 
 * @date 2019年8月6日 上午9:06:32 
 * @version V1.0   
 */
 
package notes.service.test.adviator;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.exception.ExpressionRuntimeException;


/**@ClassName: Adviator 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author xuanweilun
 * @date 2019年8月6日 上午9:06:32  
 */

public class Adviator_Test {

	/**@author xuanweilun   
	 * @date 2019年8月6日 上午9:06:32 
	 * @Title: main 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param args void
	 * @throws 
	 */

//	public static void main(String[] args) {
//		Map<String ,Object > keyWardValue = new HashMap<String,Object>();
//		
//		keyWardValue.put("f", 4.5D);
//		keyWardValue.put("c", 5.6);
//		Object b =  AviatorEvaluator.execute("5.5/2*f", keyWardValue ,true);
//		System.out.println(b);
//		
//		String formula = "(f*c)/5";
//		keyWardValue.put("f", 4.5D);
//		keyWardValue.put("c", 5.6);
//		try{
//			Expression expression = AviatorEvaluator.compile(formula,new Boolean(true));
//			Double result = (Double)expression.execute(keyWardValue);
//			System.out.println(result);
//			
//		}catch(Exception e){
//			System.out.println(e.getLocalizedMessage());
//		}
////		System.out.println(b);
//
//	}
	
	
	public static void main(String[] args) {
		Map<String ,Object > keyWardValue = new HashMap<String,Object>();
		String formula = "(x-N)/(M-N)*F";
		keyWardValue.put("x", 10D);
		keyWardValue.put("N", 5D);
		keyWardValue.put("M", 50D);
		keyWardValue.put("F", 30);
//		try{
//			Long begin = System.currentTimeMillis();
//			System.out.println(begin);
//			Expression expression = AviatorEvaluator.compile(formula,true);
//			Double result = (Double)expression.execute(keyWardValue);
//			DecimalFormat df = new DecimalFormat( "0.000");  //小数位限制
//			String strResult = df.format(result);
//			System.out.println(strResult);
//			System.out.println(System.currentTimeMillis()-begin);
//		}catch(Exception e){
//			System.out.println(e.getLocalizedMessage());
//		}
//			
		formula = "if(x>N){return M}else{return F}";
		Expression expression = AviatorEvaluator.compile(formula,true);
		Double result = (Double)expression.execute(keyWardValue);
		System.out.println(result);
	}

}
