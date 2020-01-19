package notes.service.test.code;

import java.util.Arrays;
import java.util.Base64;

public class Base64Test {

	public static void main(String[] args) {
		String password = "px";
		byte[] encode = Base64.getEncoder().encode(password.getBytes());
		System.out.println(new String(encode));
		
		byte[] decode = Base64.getDecoder().decode("49ceb653d31a21990f2bbb4c5653ff93");
		System.out.println(new String(decode));
		
		
		int [] a = {1,2,3};
		System.out.println(Arrays.toString(a));
		int [] b = new int[a.length];
		System.arraycopy(a, 0, b, 0, a.length);
		System.out.println(Arrays.toString(b));
		
		System.out.println(0xff);
	}
}
