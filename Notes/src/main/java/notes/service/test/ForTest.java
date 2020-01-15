package notes.service.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import notes.rest.viewObject.TreeNode;

public class ForTest {

//	public static void main(String[] args) {
//		List<TreeNode> names = new ArrayList<TreeNode>();
//		TreeNode treeNode1 = new TreeNode();
//		treeNode1.setId(1);
//		TreeNode treeNode2 = new TreeNode();
//		treeNode2.setId(2);
//		names.add(treeNode1);
//		names.add(treeNode2);
//		names.forEach(name ->{
//			System.out.println(name.getId());
//		} );
//		//
//		Map<Long,TreeNode> map = new HashMap<Long,TreeNode>();
//		map.put(1L, treeNode1);
//		map.put(2L, treeNode2);
//		map.forEach((key,value) ->{
//			System.out.println(key);
//			System.out.println(value);
//		});
//		
//		
//		
//		
//	}
	public static void main(String[] args) {
//		Set<TreeNode> names = new HashSet<TreeNode>();
//		TreeNode treeNode1 = new TreeNode();
//		treeNode1.setId(1);
//		treeNode1.setName(name);
//		TreeNode treeNode2 = new TreeNode();
//		treeNode2.setId(1);
//		names.add(treeNode1);
//		names.add(treeNode2);
//		System.out.println(names);
//		Timestamp b = new Timestamp(System.currentTimeMillis());
//		Timestamp a = new Timestamp(System.currentTimeMillis());
//		System.out.println(a.hashCode());
//		System.out.println(b.hashCode());
//		System.out.println(a.equals(b));
		
//		System.out.println(24*60*60*1000);
		
		List<Integer> list = new ArrayList<Integer>();
//		int times = 10000;
//		for(int i = 0;i<times;i++) {
//			list.add(100);
//		}
//		long currentTimeMillis = System.currentTimeMillis();
//		for(Integer value:list) {
//			String a = String.valueOf(value);
//		}
//		long currentTimeMillis2 = System.currentTimeMillis();
//		System.out.println(currentTimeMillis2 - currentTimeMillis + "t");
//		for(int i = 0,lenth = list.size();i<lenth;i++) {
//			String a = String.valueOf(list.get(i));
//		}
//		long currentTimeMillis3 = System.currentTimeMillis();
//		System.out.println(currentTimeMillis3 - currentTimeMillis2 + "t");
		
//		long
		
		List<Long> a = new ArrayList<Long>();
		a.add(1L);
		a.add(2L);
		a.add(1L);
		Collections.sort(a);
		System.out.println(a);
		System.out.println(a.indexOf(2L) +1);

	}
	
	
	
}
