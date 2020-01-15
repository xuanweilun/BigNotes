package notes.service.test;

public class Number {

	public static String getSimleCh(int src) {
	        final String num[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
	        final String unit[] = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
	        String dst = "";
	        int count = 0;
	        while(src > 0) {
	            dst = (num[src % 10] + unit[count]) + dst;
	            src = src / 10;
	            count++;
	        }
	        return dst.replaceAll("零[千百十]", "零").replaceAll("零+万", "万")
	                .replaceAll("零+亿", "亿").replaceAll("亿万", "亿零")
	                .replaceAll("零+", "零").replaceAll("零$", "");
	}
	
	   public static String getMoneyCh(double x)
	    {
	        String yuan="亿千百拾万千百拾元角分";
	        String digit="零壹贰叁肆伍陆柒捌玖";
	        String result="";
	        int y=(int)Math.round(x*100-0.5);

	        int dec=y%100;
	        y=y/100;
	        String money=String.valueOf(y);
	        if(y==0)
	        {
	            return result+"零元";
	        }

	        if(dec==0)
	            result="整"+result;
	        else
	        {
	            int a=dec/10;
	            int b=dec%10;
	            if(a!=0)
	                result=result+digit.charAt(a)+"角";
	            if(b!=0)
	                result=result+digit.charAt(b)+"分";

	        }
	        if(y==10)

	        {
	            result="拾元"+result;
	            return result;
	        }
	        else
	        {
	            int j=money.length()-1;
	            int k=8;
	            while(j>=0)
	            {
	                if(money.charAt(j)=='0')
	                {
	                    j--;k--;

	                }
	                result=digit.charAt(money.charAt(j)-'0')+""+yuan.charAt(k)+""+result;
	                j--;
	                k--;
	            }
	            return result;
	        }




	    }

	public static void main(String[] args) {
		System.out.println(getSimleCh(1234));
		System.out.println(getMoneyCh(1234.3));
	}
}
