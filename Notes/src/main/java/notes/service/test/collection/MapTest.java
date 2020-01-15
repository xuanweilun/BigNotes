
/**@Title: MapTest.java 
 * @Package com.brt.license.test 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author xuanweilun 
 * @date 2019年8月30日 上午10:24:40 
 * @version V1.0   
 */
 
package notes.service.test.collection;

import java.util.HashMap;
import java.util.Map;

/**@ClassName: MapTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author xuanweilun
 * @date 2019年8月30日 上午10:24:40 
 */

public class MapTest {

	public static void main(String[] args) {
		Map<Long,String> ids = new HashMap<Long,String>();
		ids.put(1L, "yi");
		System.out.println(ids.values());
		System.out.println(ids.get(1L));
		ids.remove(1L);
		System.out.println(ids.get(1L));
		System.out.println(ids.values());
	}
}
