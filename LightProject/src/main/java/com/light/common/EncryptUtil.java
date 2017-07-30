package com.light.common;

import java.security.MessageDigest;

/**
 * 加密工具
 * <p>Title: EncryptUtil</p>
 * <p>Description: </p>
 * <p>Company: Weiying</p> 
 * @author	TobyHan
 * @date	2016年11月19日上午9:56:02
 * @version 1.0
 */
public class EncryptUtil {

	public static String toMD5(String plainText,int digit) {
		 String finalStr = null;
	     try {
	        //生成实现指定摘要算法的 MessageDigest 对象。
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        //使用指定的字节数组更新摘要。
	        md.update(plainText.getBytes());
	        //通过执行诸如填充之类的最终操作完成哈希计算。
	        byte b[] = md.digest();
	        //生成具体的md5密码到buf数组
	        int i;
	        StringBuffer buf = new StringBuffer("");
	        for (int offset = 0; offset < b.length; offset++) {
	          i = b[offset];
	          if (i < 0)
	            i += 256;
	          if (i < 16)
	            buf.append("0");
	          buf.append(Integer.toHexString(i));
	        }
	        //System.out.println("32位: " + buf.toString());// 32位的加密
	        //System.out.println("16位: " + buf.toString().substring(8, 24));// 16位的加密，其实就是32位加密后的截取
	        if(digit==32)
	        	finalStr =  buf.toString();
	        else
	        	finalStr =  buf.toString().substring(8, 24);

	     } 
	     catch (Exception e) {
	       e.printStackTrace();
	     }
	     finally{
	    	 return finalStr;
	     }
	   }
	   
	   public static void main(String agrs[]) {
		   System.out.println(toMD5("123456",32));//加密LXD
	   }
}
