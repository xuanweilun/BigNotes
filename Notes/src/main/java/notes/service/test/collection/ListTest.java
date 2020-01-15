package notes.service.test.collection;

import java.util.HashSet;
import java.util.Set;

public class ListTest {

	public static void main(String[] args) {
		Set<Long> ids = new HashSet<Long>();
		System.out.println(ids);
		ids.add(1L);
		ids.add(null);
		System.out.println(ids);
		System.out.println(ids.size());
		System.out.println(ids.contains(1l));
	}
}
