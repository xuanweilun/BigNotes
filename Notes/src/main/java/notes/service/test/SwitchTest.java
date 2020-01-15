
/**@Title: SwitchTest.java 
 * @Package com.brt.license.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xuanweilun 
 * @date 2019年8月5日 上午9:54:20 
 * @version V1.0   
 */
 
package notes.service.test;


/**@ClassName: SwitchTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author xuanweilun
 * @date 2019年8月5日 上午9:54:20  
 */

public class SwitchTest {

//	public static void main(String[] args) {
//		int i = 0;
//		int num = 3;
//		while (num>0)
//		{
//			System.out.printf("++++++++++++++++++++++++++++++++++\n");
//			switch (i)
//			{
//			case 0:			
//				System.out.printf("i = %d\n", i++);
//				i++;
//			case 1:			
//				System.out.printf("i = %d\n", i);
//				i = i + 2;
//				continue;
//			case 2:			
//				System.out.printf("i = %d\n", i--);
//				i--;
//				break;
//			case 3:
//				System.out.printf("i = %d\n", i);
//				continue;
//			case 4:
//				System.out.printf("i = %d\n", i);
//				i++;
//				break;
//			default:
//				System.out.printf("i = %d\n", i);
//				i = 2;			
//				break;
//	 
//			}		
//			System.out.printf("----------------------------------\n");
//			num--;
//			System.out.printf("num = %d\n", num);
//		}
//
//	}
	
	public static void main(String[] args) {
		String type = "xuan";
		switch(type) {
		case "xuan":
			System.out.println("123xuan");
			break;
		case "wei":
			System.out.println("wei");
			break;
		case "lun":
			System.out.println("lun");
			;break;
		default: break;
		}
		
	}
	
	
	
	
}
