package notes.service.test.math;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;

public class Test {

	public static void main(String[] args) {
//		Long a = 1L;
//		Long b = 1L;
//		Set<Long> num = new HashSet<Long>();
//		num.add(a);
//		num.add(b);
//		System.out.println(num);
//		Map<Long,Long> ages = new HashMap<Long,Long>();
//		ages.put(1L, 2L);
//		ages.put(1L, 3L);
//		ages.put(null, null);
//		
//		System.out.println(ages);
//		
//		String str = "\\\12345678\t";
//		System.out.println(str.length());
		
//		double d = 50.200;
//		DecimalFormat df = new DecimalFormat("0.00");
//		double j = df.format(d);
//		System.out.println();
		
//		double   f   =   111231.5585;  
//		double   f1   =   new   BigDecimal(f).setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();  
//		System.out.println(f1);
		
		Double a = 1D;
		Double b = 0D;
		Double c = 0D;
		System.out.println(c/c);
		
		System.out.println(new Timestamp(System.currentTimeMillis()+5000L));
		
	}
	@Scheduled(cron="0 * * * * *")
	void test() {
		
	}
}
