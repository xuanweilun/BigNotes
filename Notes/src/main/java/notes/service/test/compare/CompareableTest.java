package notes.service.test.compare;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompareableTest{
	
	public static void main(String[] args) {
		//1.排序类型测试
//		CompareableTest.collectionTypeTest();
		
		//2.基本数据类型测试
//		CompareableTest.basicDataTypeCompareTest();
		
		//3.基本类型的封装类测试
//		CompareableTest.BasicDataTypeCompareTest();
		
		//4.对象类型测试
		CompareableTest.ObjectTypeCompareTest();
		
		
	}
	
	

	/**
	 * 排序类型测试
	 * 2019年10月4日 下午3:46:11
	 */
	private static void collectionTypeTest() {
		List<Basic> basicList = new ArrayList<Basic>();
		basicList.add(new Basic(11,"小明"));
		basicList.add(new Basic(12,"小黄"));
		basicList.add(new Basic(9,"小绿"));
		System.out.println("正常: "+basicList);
		//1.反序
		Collections.reverse(basicList);
		System.out.println("反序: "+basicList);
		//2.升序
		Collections.sort(basicList);
		System.out.println("升序: "+basicList);
		//3.降序
		Collections.sort(basicList,Collections.reverseOrder());
		System.out.println("降序: "+basicList);
	}
	
	/**
	 * 基本数据类型测试
	 * 2019年10月4日 下午3:37:54
	 */
	private static void basicDataTypeCompareTest() {
		List<Basic> basicList = new ArrayList<Basic>();
		basicList.add(new Basic(11,"小明"));
		basicList.add(new Basic(12,"小黄"));
		basicList.add(new Basic(9,"小绿"));
		System.out.println(basicList);
		Collections.sort(basicList);
		System.out.print(basicList);
	}
	
	/**
	 * 对象基本类型测试
	 * 2019年10月4日 下午3:54:44
	 */
	private static void BasicDataTypeCompareTest() {
		List<BasicObjectType> basicDataList = new ArrayList<BasicObjectType>();
		basicDataList.add(new BasicObjectType(11L,"小明"));
		basicDataList.add(new BasicObjectType(9L,"小黄"));
		basicDataList.add(new BasicObjectType(9L,"小绿"));
		System.out.println(basicDataList);
		Collections.sort(basicDataList);
		System.out.print(basicDataList);
	}
	
	/**
	 * 对象类型比较测试
	 * 2019年10月4日 下午3:57:18
	 */
	private static void ObjectTypeCompareTest() {
		List<ObjectType> basicDataList = new ArrayList<ObjectType>();
		basicDataList.add(new ObjectType(new Timestamp(1002182516000L),"小黄"));
		basicDataList.add(new ObjectType(new Timestamp(970646516000L),"小明"));
		basicDataList.add(new ObjectType(new Timestamp(1033718516000L),"小绿"));
		System.out.println(basicDataList);
		Collections.sort(basicDataList);
		System.out.print(basicDataList);
	}
	
}
