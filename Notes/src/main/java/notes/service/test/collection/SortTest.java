package notes.service.test.collection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortTest {

	public static void main(String[] args) {
//		List<Integer> values = new ArrayList<Integer>();
//		
//		values.add(5);
//		values.add(3);
//		values.add(9);
//		values.add(1);
//		System.out.println(values);
////		Collections.sort(values);
//		Collections.sort(values, Collections.reverseOrder());
//		System.out.println(values);

		Timestamp t = new Timestamp(System.currentTimeMillis()+10);
		Timestamp t2 = new Timestamp(System.currentTimeMillis());
		System.out.println(t2.before(t));
	}
}
